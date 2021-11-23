# Part5：Grid Data Structures

## Step1：The AbstractGrid Class

## Set 10

> The source code for the AbstractGrid class is in Appendix D.

---

### Question 1

#### 问题

> Where is the isValid method specified? Which classes provide an implementation of this method?

#### 回答

* `isValid()` 函数在 `Grid<E> Interface` 中被声明，如下图所示：

![gridEInterface](http://markdown-pictures-huzzh3.oss-accelerate.aliyuncs.com/img/seTraining/stage1/gridEInterface.png)

* 而 `isValid` 函数在 `BoundedGrid` 类和 `UnBoundedGrid` 类中被实现，如下面源码所示：

```java
// @file: Appendix D 第 62 页
// @line: 在第 62 页即可找到

public boolean isValid(Location loc)
{
    return 0 <= loc.getRow() && loc.getRow() < getNumRows()
    && 0 <= loc.getCol() && loc.getCol() < getNumCols();
}
```

```java
// @file: Appendix D 第 64 页
// @line: 在第 64 页即可找到

public boolean isValid(Location loc)
{
	return true;
}
```

---

### Question 2

#### 问题

> Which AbstractGrid methods call the isValid method? Why don’t the other methods need to call it?

#### 回答

* `getValidAdjacentLocations` 函数会调用 `isValid` 函数，如下面源码所示：

```java
// @file: Appendix D 第 60 页
// @line: 在第 60 页即可找到

public ArrayList<Location> getValidAdjacentLocations(Location loc)
{
    ArrayList<Location> locs = new ArrayList<Location>();
    int d = Location.NORTH;
    for (int i = 0; i < Location.FULL_CIRCLE / Location.HALF_RIGHT; i++)
    {
        Location neighborLoc = loc.getAdjacentLocation(d);
        if (isValid(neighborLoc))
        locs.add(neighborLoc);
        d = d + Location.HALF_RIGHT;
    }
    return locs;
}
```

* 其他函数，如 `getEmptyAdjacentLocations` 和 `getOccupiedAdjacentLocations` 不是不调用 `isValid` ，而是它们是间接调用 `isValid`，它们先调用 `getValidAdjacentLocations`，再通过 `getValidAdjacentLocations` 调用 `isValid`。

---

### Question 3

#### 问题

> Which methods of the Grid interface are called in the getNeighbors  method? Which classes provide implementations of these methods?

#### 回答

* `getNeighbors` 调用了 `Grid` 接口的 `get` 函数和 `getOccupiedAdjacentLocations` 函数，如下面源码所示：

```java
// @file: Appendix D 第 60 页
// @line: 在第 60 页即可找到

public ArrayList<E> getNeighbors(Location loc)
{
    ArrayList<E> neighbors = new ArrayList<E>();
    for (Location neighborLoc : getOccupiedAdjacentLocations(loc))
        neighbors.add(get(neighborLoc));
    return neighbors;
}
```

* 而 `get` 函数和 `getOccupiedAdjacentLocations` 函数是在  `BoundedGrid` 类和 `UnBoundedGrid` 类中被实现的，如下面源码所示：

```java
// @file: Appendix D 第 63 页
// @line: 在第 63 页即可找到

public ArrayList<Location> getOccupiedLocations()
{
	ArrayList<Location> theLocations = new ArrayList<Location>();
    // Look at all grid locations.
    for (int r = 0; r < getNumRows(); r++)
    {
    	for (int c = 0; c < getNumCols(); c++)
    	{
        // If there's an object at this location, put it in the array.
        Location loc = new Location(r, c);
        if (get(loc) != null)
        	theLocations.add(loc);
		}
	}
	return theLocations;
}
public E get(Location loc)
{
	if (!isValid(loc))
		throw new IllegalArgumentException("Location " + loc + " is not valid");
	return (E) occupantArray[loc.getRow()][loc.getCol()]; // unavoidable warning
}
```

```java
// @file: Appendix D 第 64 页
// @line: 在第 64 页即可找到

public ArrayList<Location> getOccupiedLocations()
{
    ArrayList<Location> a = new ArrayList<Location>();
    for (Location loc : occupantMap.keySet())
    	a.add(loc);
    return a;
}
public E get(Location loc)
{
    if (loc == null)
    	throw new NullPointerException("loc == null");
    return occupantMap.get(loc);
}
```

---

### Question 4

#### 问题

> Why must the get method, which returns an object of type E, be used in  the getEmptyAdjacentLocations method when this method returns locations, not objects of type E?

#### 回答

* 因为当传入的位置上没有 `Object` 的时候，`get` 函数会返回 `null`，而 `getEmptyAdjacentLocations` 函数需要返回所有没有 `Object` 的相邻位置，且 `get` 函数是唯一具有识别对应位置上是否有 `Object` 的功能的函数，所以 `getEmptyAdjacentLocations` 必须使用 `get` 函数。

---

### Question 5

#### 问题

> What would be the effect of replacing the constant Location.HALF_RIGHT  with Location.RIGHT in the two places where it occurs in the  getValidAdjacentLocations method?

#### 回答

* 假设目前传入 `getValidAdjacentLocations` 不在地图边缘，也就是说此时有效位置有 8 个，如果我们用 `Location.RIGHT` 替代了 `Location.HALF_RIGHT` ，那么有效位置就会被降低到 4 个，它们分别是 `NORTH`，`SOUTH`，`EAST`，`WEST`。

---

## Step2：The BoundedGrid Class

## Set 11

> The source code for the BoundedGrid class is in Appendix D.

### Question 1

#### 问题

> What ensures that a grid has at least one valid location?

#### 回答

* 当用户企图输入小于 0 的 `rows` 和 小于 0 的 `cols` 到 `BoundedGrid` 的构造函数中，使 `BoundedGrid` 不存在有效位置，那么这个时候就会报出 `IllegalArgumentException` 错误，如下面源码所示：

```java
// @file: Appendix D 第 62 页
// @line: 在第 62 页即可找到

public BoundedGrid(int rows, int cols)
{
    if (rows <= 0)
    	throw new IllegalArgumentException("rows <= 0");
    if (cols <= 0)
    	throw new IllegalArgumentException("cols <= 0");
    occupantArray = new Object[rows][cols];
}
```

* 此时，就能保证 `Grid` 有至少一个有效位置。

---

### Question 2

#### 问题

> How is the number of columns in the grid determined by the getNumCols method? What assumption about the grid makes this possible?

#### 回答

* `getNumCols()` 函数通过 `occupantAarray[0].length` 来获取到行数，如下面源码所示：

```java
// @file: Appendix D 第 62 页
// @line: 在第 62 页即可找到

public int getNumCols()
{
    // Note: according to the constructor precondition, numRows() > 0, so
    // theGrid[0] is non-null.
    return occupantArray[0].length;
}
```

* 构造函数初始化 `occupantAarray` ，因为合法的 `Grid` 至少要有 1 行 1 列，所以 `occupantAarray[0]` 是一定可以访问到的，而 `occupantAarray[0]` 存放的是第一行的所有数据，所以 `occupantAarray[0].length` 自然就是列数了。

---

### Question 3

#### 问题

> What are the requirements for a Location to be valid in a BoundedGrid?

#### 回答

* 一个 `Location` 要想合法，那么它就必须保证它的行号大于等于 0，且小于行数，同理，它的列号要大于等于 0 ，且小于列数，如下面源码所示：

```java
// @file: Appendix D 第 62 页
// @line: 在第 62 页即可找到

public boolean isValid(Location loc)
{
    return 0 <= loc.getRow() && loc.getRow() < getNumRows()
    && 0 <= loc.getCol() && loc.getCol() < getNumCols();
}
```

---

> In the next four questions, let r = number of rows, c = number of columns, and n = number of occupied locations.

---

### Question 4

#### 问题

> What type is returned by the getOccupiedLocations method? What is the time complexity (Big-Oh) for this method?

#### 回答

* `getOccupiedLocations` 函数返回 `ArrayList<Location>`，如下面源码所示：

```java
// @file: Appendix D 第 63 页
// @line: 在第 63 页即可找到

public ArrayList<Location> getOccupiedLocations()
{
	ArrayList<Location> theLocations = new ArrayList<Location>();
    // Look at all grid locations.
    for (int r = 0; r < getNumRows(); r++)
    {
    	for (int c = 0; c < getNumCols(); c++)
        {
            // If there's an object at this location, put it in the array.
            Location loc = new Location(r, c);
            if (get(loc) != null)
            	theLocations.add(loc);
        }
    }
	return theLocations;
}
```

* 很明显，时间复杂度为 $$O(rc)$$，因为每一行每一列的元素都需要被访问一次。 

---

### Question 5

#### 问题

> What type is returned by the get method? What parameter is needed? What is the time complexity (Big-Oh) for this method?

#### 回答

* `get` 函数返回的是 `E` 类型的数据，`E` 类型的数据表示的是 `occupantArray` 中存放的数据类型，如下面源码所示：

```java
// @file: Appendix D 第 63 页
// @line: 在第 63 页即可找到

public E get(Location loc)
{
    if (!isValid(loc))
    	throw new IllegalArgumentException("Location " + loc + " is not valid");
    return (E) occupantArray[loc.getRow()][loc.getCol()]; // unavoidable warning
}
```

* 由于它是通过二维数组直接访问数据，所以它的时间复杂度为 $O(1)$ 。

---

### Question 6

#### 问题

> What conditions may cause an exception to be thrown by the put method? What is the time complexity (Big-Oh) for this method?

#### 回答

* 如果 `Object` 要被添加到的 `Location` 不在这个 `Grid` 上的话，那么就会抛出 `IllegalArgumentException`
* 如果 `Object` 为 `null` 的话，那么就会抛出 `NullPointerException`，具体的源码如下所示：

```java
// @file: Appendix D 第 63 页
// @line: 在第 63 页即可找到

public E put(Location loc, E obj)
{
    if (!isValid(loc))
    	throw new IllegalArgumentException("Location " + loc+ " is not valid");
    if (obj == null)
    	throw new NullPointerException("obj == null");
    // Add the object to the grid.
    E oldOccupant = get(loc);
    occupantArray[loc.getRow()][loc.getCol()] = obj;
    return oldOccupant;
}
```

* 由于它是通过二维数组直接访问数据，所以它的时间复杂度为 $O(1)$ 。

---

### Question 7

#### 问题

> What type is returned by the remove method? What happens when an attempt is made to remove an item from an empty location? What is the time  complexity (Big-Oh) for this method?

#### 回答

* `remove` 函数返回的是 `E` 类型的数据，`E` 类型的数据表示的是 `occupantArray` 中存放的数据类型，如下面源码所示：

```java
// @file: Appendix D 第 63 页
// @line: 在第 63 页即可找到

public E remove(Location loc)
{
    if (!isValid(loc))
    	throw new IllegalArgumentException("Location " + loc + " is not valid");
    	
    // Remove the object from the grid.
    E r = get(loc);
    occupantArray[loc.getRow()][loc.getCol()] = null;
    return r;
}
```

* 通过上面的源码，我们可以分析出，如果尝试移除一个原本就是空的位置的话，那么该函数就会返回 `null` 。
* 由于它是通过二维数组直接访问数据，所以它的时间复杂度为 $O(1)$ 。

---

### Question 8

#### 问题

> Based on the answers to questions 4, 5, 6, and 7, would you consider this an efficient implementation? Justify your answer.

#### 回答

* 很明显，这种实现方式是一种搞笑的实现方式，因为除了 `getOccupiedLocations` 函数的时间复杂度为 $O(rc)$ 以外，其他几个函数的时间复杂度都是 $O(1)$ 。
* 但是，每一个 `UnBoundedGrid` 都存放着部分未被使用的空间的数据，即使这个空间上没有 `Object` ，但它仍旧会以 `null` 的形式被保存下来，这样也就导致了浪费了部分的空间。因此可以使用 `HashMap` 进行改善，使其只保存了被占用的空间的数据，同时访问数据也只需要 $O(1)$ 的复杂度。

---

## Step3：The UnboundedGrid Class

## Set 12

> The source code for the UnboundedGrid class is in Appendix D.

---

### Question 1

#### 问题

> Which method must the Location class implement so that an instance of  HashMap can be used for the map? What would be required of the Location  class if a TreeMap were used instead? Does Location satisfy these  requirements?

#### 回答

* 要使用 `HashMap` ，则 `Location` 类需要实现 `hashCode` 函数，即哈希函数，如果输入两个相同的位置到 `hashCode` 函数中，则必须得到相同的值，如果输入两个不同的位置，那么就要尽可能得到不同的值。其次还需要实现 `equals ` 函数，用来判断两个位置是否相等，也可以用来判断两个位置是否不等。
* 要使用 `TreeMap`， 则 `Location` 类需要实现保证它的 `keys` 是 `comparable` 的，什么叫做 `comparable` ，那就是当我要比较两个位置是否相同的时候，我可以直接使用一个 `compareTo` 函数来比较直接比较两个位置的 `key` ，同时如果两个位置相同 `compareTo` 会返回 0 ，否则会返回非零值。 
* 很明显 `Location` 类是符合上述定义的，请见下面的 `Location` 类的定义：

![locationDefine](http://markdown-pictures-huzzh3.oss-accelerate.aliyuncs.com/img/seTraining/stage1/locationDefine.png)

* 可以看到，在该定义中，包含了我们上述所说的所有要求。

---

### Question 2

#### 问题

> Why are the checks for null included in the get, put, and remove  methods? Why are no such checks included in the corresponding methods  for the BoundedGrid?

#### 回答

* 因为 `UnboundedGrid` 使用 `HashMap` 作为它的数据结构，来保存 `Grid` 中的数据。由于 `HashMap` 只存放非空的数据，所以 `isValid` 函数对于所有 `location` 只会返回 `true` ，因为它无法保存 `null` 的数据，故在不遍历 `HashMap` 的情况下，无法直接判断一个位置是否合法 。所以 `get` , `put`, and `remove` 函数不能直接通过 `isValid` 函数来判断某个位置是否合法，必须要先检查该位置是否为 `null` ，若为 `null`，则会抛出 `NullPointerException` 。
* 而在 `BoundedGrid` 中，它是使用二位数组 `occupantArray` 来存储数据的，这时，我们就可以直接用 `isValid` 来判断一个位置是否合法，所以 `get` , `put`, and `remove` 函数就可以调用`isValid`函数来判断，而不需要单独再判断一个位置是否为 `null` ，若为 `null` ，`isValid` 函数会抛出 `NullPointerException` 异常。

---

### Question 3

#### 问题

> What is the average time complexity (Big-Oh) for the three methods: get, put, and remove? What would it be if a TreeMap were used instead of a  HashMap?

#### 回答

* 因为直接通过 `HashMap` 进行操作，所以 `get` ， `put` ， `remove` 的时间复杂度是 $O(1)$
* 如果使用的是 `TreeMap` 进行操作的话，由于树形结构的性质，`get` ， `put` ， `remove` 的时间复杂度是 $O(\log n)$，其中 `n` 是节点数。

---

### Question 4

#### 问题

> How would the behavior of this class differ, aside from time complexity, if a TreeMap were used instead of a HashMap?

#### 回答

* 我们以 `getOccupiedLocations` 函数为例：
  * 如果我们使用的是 `HashMap` ，可以通过 `HashMap` 上的 `keys` 得到对应的 `Locations` ，这些 `keys` 都是通过哈希函数 `HashCode`  计算得到的，最终得到的数组的顺序取决于从哪个位置开始遍历 `HashMap` 。
  * 如果我们使用的是 `TreeMap` ，它将数据存储在一个平衡二叉树，一般都是通过中序遍历的方式来遍历二叉树，同时在 `compareTo` 函数中是以行序优先的方式定义的，所以最终遍历的结果也是以行序优先的方式递增呈现的。

---

### Question 5

#### 问题

> Could a map implementation be used for a bounded grid? What advantage,  if any, would the two-dimensional array implementation that is used by  the BoundedGrid class have over a map implementation?

#### 回答

---

* 可以用 `map` 来实现 `bounded grid` 。如果使用 `map` 的话，`getOccupiedLocations` 要遍历所有的节点，故其时间复杂度为 $O(n)$ ，`n ` 是节点数量。
* 而使用二维数组的好处是，如果这个 `Grid` 非常得满的话，使用 `map` 就会很占内存，因为它既要存储物体信息，又要存储位置信息；而使用二维数组所需要的空间就会少一点，因为它只需要记录物体信息。 
