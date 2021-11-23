# 0. Preface

* 本文档包含以下三个部分：

  1. 阶段二 Part 5 的 `SparseBoundedGrid` 的链表实现说明及展示。
     1. 思路
     2. 成果展示
  2. 阶段二 Part 5 的 `SparseBoundedGrid` 的 `Map` 实现说明及展示。
     1. 思路
     2. 成果展示
     3. `Matrix` 上的表格的填写
  3. 阶段二 Part 5 的 `UnboundedGrid` 的可拓展版本的实现及展示。
     1. 思路
     2. 成果展示
     3. `Matrix` 的问题回答
  4. SonarQube 检查

---

# 1. 链表实现 SparseBoundedGrid

## 1.1 思路

* 其实这一部分的链表实现比较简单，很多地方都可以直接复用原来的 `BoundedGrid` 的代码。
* 首先按照 `matrix` 上的提示，我们在结构体 `SparseGridNode` 定义以下三个属性：

```java
	private Object occupant;
	private int col;
	private SparseGridNode next;
```

* 其中 `occupant` 用来存放物体，`col` 用来存放行号，`next` 用来存放指向下一个节点的指针。
* 之后再实现上述这三种属性的接口函数即可，这一部分比较简单，不多赘述。

* 之后，我们最重要的是实现 `SparseBoundedGrid` 类，它是 `AbstractGrid` 的子类，其中比较难的部分应该是 `getOccupiedLocations` , `get` , `put` 和 `remove` 函数的实现，碍于篇幅，这里主要描述 `remove` 函数的实现方法，其他的三个函数与此类似。

* 在 `remove` 函数中，我们要先判断传入的参数是否合法，如下所示：

```java
// check the loc
if (!isValid(loc)) {
    throw new IllegalArgumentException("Location " + loc + " is not valid.");
}
```

* 如果传入的位置不合法，那么我们就抛出异常。
* 其次，我们的 `remove` 函数需要返回被移除的 `Object` ，所以我们需要使用 `get` 函数获取到需要返回的 `Object` 如下面所示：

```java
E obj = get(loc);

// If not found, return null
if (obj == null) {
    return null;
}
```

* 接下来，我们需要将该 `Object` 从链表中移除，具体的移除办法就是设置两个指针，其中一格指针指向另一个指针的下一个元素，然后不断的遍历链表，找到对应的节点，将该节点的前后两个节点链接起来，就可以实现删除操作了，如下面源码所示：

```java
		// Search the grid
		SparseGridNode tempPtr = occupantArray[loc.getRow()];
		
		if (tempPtr != null) {
			if (tempPtr.getColumn() == loc.getCol()) {
				// Check the first node
				occupantArray[loc.getRow()] = tempPtr.getNext();
			}
			else {
				SparseGridNode tempPtrNext = tempPtr.getNext();
				
				// Search the location
				while (tempPtrNext != null && tempPtrNext.getColumn() != loc.getCol()) {
					tempPtrNext = tempPtrNext.getNext();
					tempPtr     = tempPtr.getNext();
				}
				
				// If found , remove the occupant
				if (tempPtrNext != null) {
					tempPtr.setNext(tempPtrNext.getNext());
				}
			}
		}
```

* 最后再将，我们之前 `get` 到的 `Object` 返回即可。

## 1.2 成果展示

![SparseGrid2](http://markdown-pictures-huzzh3.oss-accelerate.aliyuncs.com/img/seTraining/stage1/SparseGrid2.gif)

* 可以看到我们的 `SparseGrid` 是可以正常运行的，`Critter` 能够正常“吃掉” `Actor` 说明我们的 `remove` 函数没有问题，`Bug` 能够放置花朵，说明我们的 `put` 函数没有问题，同时 `Bug` 能够不移动到地图外面，说明我们的 `isValid` 函数也没有问题。

---

# 2. HashMap 实现 SparseBoundedGrid

## 2.1 思路

* 由于 `Java` 自带有 `HashMap` 的函数哭，所以使用哈希来实现 `SparseBoundedGrid` 还是非常简单的，同样，我们以 `remove` 函数作为例子来说明如何使用 `HashMap`。
* 首先需要在类中定义 `HashMap` 的结构体和对应的属性，如下面所示：

```java
	// We use the map to store the element
	private Map<Location, E> occupantMap;
	// Some attributes of the grid
	private int numRows;
	private int numCols;
```

* 而如果我们要实现 `remove` 函数的话，我们可以直接用 `HashMap` 的 `remove` 函数即可，如下面所示：

```java
	@Override
	public E remove(Location loc) {
		// Check the loc
		if (loc == null) {
			throw new NullPointerException("loc == null");
		}
		
		// Use hashtable to remove
		return occupantMap.remove(loc);
	}
```

* 可以看到，相较于上面的链表实现， `HashMap` 的实现较为简单，因为有现成的函数。

---

## 2.2 结果展示

![hashMapRun](http://markdown-pictures-huzzh3.oss-accelerate.aliyuncs.com/img/seTraining/stage1/hashMapRun.gif)

* 可以看到我们的**哈希实现**的 `SparseGrid` 是可以正常运行的：
  * `Critter` 能够正常“吃掉” `Actor` 说明我们的 `remove` 函数没有问题。
  * `Bug` 能够放置花朵，说明我们的 `put` 函数没有问题。
  * 同时 `Bug` 能够不移动到地图外面，说明我们的 `isValid` 函数也没有问题。

---

## 2.3 Matrix 上的表格的完善

* 通过简单的分析，完整的表格如下面所示：


| method                      | SparseGridNode version | LinkedList<OccupantInCol> version | HashMap version | TreeMap version |
| :----------------------------- | :----------------: | :---------------------------: | :---------: | :---------: |
| getNeighbors                 |        O(c)        |             O(c)              |    O(1)     |   O(logn)   |
| getEmptyAdjacentLocations    |        O(c)        |             O(c)              |    O(1)     |   O(logn)   |
| getOccupiedAdjacentLocations |        O(c)        |             O(c)              |    O(1)     |   O(logn)   |
| getOccupiedLocations         |       O(r+n)       |            O(r+n)             |    O(n)     |    O(n)     |
| get                          |        O(c)        |             O(c)              |    O(1)     |   O(logn)   |
| put                          |        O(c)        |             O(c)              |    O(1)     |   O(logn)   |
| remove                       |        O(c)        |             O(c)              |    O(1)     |   O(logn)   |

* 其中 `r` 是行数， `c` 是列数， `n` 是被占据的位置数。

---

# 3. 可拓展 UnBoundedGrid 实现

## 3.1 思路

* 这一部分，我们主要用数组来存储 `Object` ，如果发现 `Object` 超出原有的界限，那么我们就定义一个新数组，该新数组是原数组的边长的两倍，且所有原数组的数据都会拷贝过来，最后用新数组来代替原数组。
* 一开始，我们要初始化数组的大小为 16x16 ，如下面源码所示：

```java
	// We use occupantArray to store item
	private Object[][] occupantArray;
	// The dimension of the occupantArray
	private int dim;
	
	// Constructor
	public UnboundedGrid2() {
		// Initialize the gird to be 16 x 16
		dim = 16;
		occupantArray = new Object[dim][dim];
	}
```

* `occupantArrya` 是我们存储对象的二维数组，`dim` 是 `Grid` 的物理边长（逻辑边长为 $+\infty$），一开始的物理边长为 16 。
* 碍于篇幅，这里我们只介绍 `put` 函数的实现，具体思路就是，如果 `put` 的位置在物理边长外面，那么我们就拓展整个 `Grid` ，如下面源码所示：

```java
	@Override
	public E put(Location loc, E obj) {
		// Check the location
		if (loc == null) {
			throw new NullPointerException("loc == null");
		}
		if (obj == null) {
			throw new NullPointerException("obj == null");
		}
		
		// If new location is out of the array, resize the array
		if (loc.getRow() >= dim || loc.getCol() >= dim) {
			resize(loc);
		}
		
		// Add the object to the grid
		E oldOccupant = get(loc);
		occupantArray[loc.getRow()][loc.getCol()] = obj;
		
		return oldOccupant;
	}
```

* 注意，拓展完地图，还需要把原来需要添加的 `Object` 添加进去。

* 而我们的 `resize` 的函数的实现也比较简单，只需要一直将原数组的物理边长乘2，知道可以容纳所需要添加的 `Object` 为止，然后新申请一个数组用来存放 `Object` ，最后将原有的 `Object` 拷贝过去，再更新 `Grid` 的属性即可，如下面源码所示：

```java
		private void resize(Location loc) {
		// double the size until it's greater than needed
		int size = dim;
		while (loc.getRow() >= size || loc.getCol() >= size) {
			size = size * 2;
		}
		
		// Create  a new array
		Object[][] newArray = new Object[size][size];
		
		// Copy over the old contents
		for (int row = 0; row < dim; row++) {
			for (int col = 0; col < dim; col++) {
				newArray[row][col] = occupantArray[row][col];
			}
		}
		
		// update
		occupantArray = newArray;
		dim           = size; 
	}
```

---

## 3.2 成果展示

![unBoundedGrid2](http://markdown-pictures-huzzh3.oss-accelerate.aliyuncs.com/img/seTraining/stage1/unBoundedGrid2.gif)

* 可以看到，我们新的这个 `UnBoundedGrid` 是可以正常使用的，没有出现什么问题，故此也可以看出我们的实现是正确的。

---

## 3.3 Matrix 问题回答

### 3.3.1 问题 1

#### 问题

>What is the Big-Oh efficiency of the get method?

#### 回答

* 由于我们直接通过二维数组来获取数据，所以 `get` 函数的时间复杂度是 $O(1)$ 。

---

### 3.3.2 问题 2

#### 问题

> What is the efficiency of the put method when the row and column index values are within the current array bounds?

#### 回答

* 由于不需要 `resize` 的时候，我们可以直接通过二维数组来存数据，所以此时的 `put` 函数的时间复杂度是 $O(1)$ 。

---

### 3.3.3 问题 3

#### 问题

> What is the efficiency when the array needs to be resized?

#### 回答

* 因为需要遍历原来的二维数组，才能够把原来的所有数据存放到新的数组中，所以 `resize` 的时间复杂度是 $O(dim * dim)$

---

# 4. SonarQube 检查

![part5SonarQube](http://markdown-pictures-huzzh3.oss-accelerate.aliyuncs.com/img/seTraining/stage1/part5SonarQube.png)

* 可以看到，我们的 `SonarQube` 检查是通过了的。

![part5SonarQubeComments](http://markdown-pictures-huzzh3.oss-accelerate.aliyuncs.com/img/seTraining/stage1/part5SonarQubeComments.png)

* 我们的注释率为 17.2%，超过了 10%，符合要求。

![part5SonarQubeDuplications](http://markdown-pictures-huzzh3.oss-accelerate.aliyuncs.com/img/seTraining/stage1/part5SonarQubeDuplications.png)

* 我们的重复的行数为 0 ，不到 10 行，符合要求。

* 同时我们代码的 `RCI (Rules Compliance Index)` ，可以计算出来 `RCI = 1 - 18/461 = 96.10% ` 超过了 60 %，符合要求。

---

