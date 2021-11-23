# Part2 README

## 0. 作者信息

18327034 胡泽钊 19级软件工程

---

## 1. CircleBug

### 1.1 思路

只需要把 `BoxBug.java` 中的 `act` 函数中的两个 `turn` 函数删掉其中 1 个即可，如下面源码所示：

```java
	public void act()
    {
        if (steps < sideLength && canMove())
        {
            move();
            steps++;
        }
        else
        {
            turn();
            steps = 0;
        }
    }
```

### 1.2 运行结果

![circleBugRun](http://markdown-pictures-huzzh3.oss-accelerate.aliyuncs.com/img/seTraining/stage1/circleBugRun.gif)

* 可以看到修改后的 `circleBug` 运行轨迹不再是矩形，而是“类似于”圆形的一个图形轨迹。

---

## 2. SpiralBug

### 2.1 思路

* `SpiralBug` 最终运行的图案应该是螺旋形的，这个比较好实现，只需要在每次需要转向的时候，使 `sideLength` 加 1就可以了，如下所示：

```java
	public void act() {
		if (steps < sideLength && canMove()) {
            move();
            steps++;
        }
		else {
			turn();
			turn();
			
			steps = 0;
			
			sideLength = sideLength + 1;
		}
	}
```

* 为了能够更好的观察到效果，我们需要在 `runner` 中让地图变得无限大，具体的操作如下：

```java
public class SpiralBugRunner {
	public static void main(String[] args) {
		ActorWorld world = new ActorWorld(new UnboundedGrid<Actor>());
		
		SpiralBug alice = new SpiralBug();
		alice.setColor(Color.ORANGE);
		
		world.add(new Location(15, 15), alice);
		
		world.show();
	}
}
```

* 只需要让 `ActorWorld` 的参数为 `UnboundedGrid` 即可。

### 2.2 运行结果

![spiralBugRun](http://markdown-pictures-huzzh3.oss-accelerate.aliyuncs.com/img/seTraining/stage1/spiralBugRun.gif)

* 可以看到最终 `Bug` 的运行轨迹是螺旋状的。


---

## 3. ZBug

### 3.1 思路

* `ZBug` 最终要画出一个 `Z` 字的形状，这其实很好实现，只需要设置好 `Bug` 的方向，并且每次移动了一定的距离之后，就设置新的方向就好了。
* 我们将 `ZBug` 的方向初始化为“东”，即 90 度方向，如下面源码所示：

```java
	public ZBug(int length) {
		steps      = 0;
		sideLength = length;
		counter    = 0;
		
		this.setDirection(90);
	}
```

* 然后，第一次转向之后，`Bug`的方向为西南方向，即 225 度；同理，第二次则是 90 度，如下面源码所示：

```java
else if (steps == sideLength && canMove() && counter <= 2) {
			
			if (counter == 0) {
				this.setDirection(225);
			}
			else if (counter == 1) {
				this.setDirection(90);
			}
			
			steps = 0;
			
			counter = counter + 1;
}
```

* 当转向完毕之后，只需要让 `Bug` 不作任何事情，就可以表现的像是停止：

```java
		else if (!canMove() || counter > 2) {
			// empty
		}
```

### 3.2 运行结果

![zBugRun](http://markdown-pictures-huzzh3.oss-accelerate.aliyuncs.com/img/seTraining/stage1/zBugRun.gif)

---

## 4. DancingBug

### 4.1 思路

* `DancingBug` 最终的效果为，`DancingBug`每次转向不再是固定的次数，而是传入一个数组，用于告诉`DancingBug`每次需要转向的次数。
* 那么实现起来也比较简单，只需要修改一下`BoxBug`的构造函数和`act`函数即可，在构造函数中，我们需要传入一个数组用于表示转向次数，还有数组的长度，用于遍历数组，具体的构造函数如下所示：

```java
    public DancingBug(int length, int[] arrayInput, int arrayInputLength)
    {
        steps        = 0;
        sideLength   = length;
        turnArray    = arrayInput;
        arrayLength  = arrayInputLength;
        counter      = 0;
    }
```

* 其次，在`act`函数中，每次需要转向的时候都要访问`turnArray`中的数组，以给出转向次数，那么我们可以使用 `for` 循环来指定转向的次数，具体的实现如下：

```java
    public void act()
    {
        if (steps < sideLength && canMove())
        {
            move();
            steps++;
        }
        else
        {
        	for (int i = 0; i < turnArray[counter]; i++) {
        		turn();
        	}
        	
        	counter = (counter + 1) % arrayLength;
            steps = 0;
        }
    }
```

* 注意 counter 每次都需要取模，以防止溢出。

* 还要再编写 `Runner` ，在 `Runner` 中，我们初始化了一个大小为 5 的数组，其中存放的数据分别是 1、2、3、4、5，表示其转向的角度分别为 45，90，135，180，225，具体的实现如下：

```java
public class DancingBugRunner {
	public static void main(String[] args) {
		ActorWorld world = new ActorWorld();
		
		int[] turnArrayInput = new int[5];
		turnArrayInput[0] = 1;
		turnArrayInput[1] = 2;
		turnArrayInput[2] = 3;
		turnArrayInput[3] = 4;
		turnArrayInput[4] = 5;
		
		DancingBug alice = new DancingBug(4, turnArrayInput, 5);
		
		world.add(new Location(7, 1), alice);
		
		world.show();
	}
}
```

### 4.2 运行结果

![dancingBugRun](http://markdown-pictures-huzzh3.oss-accelerate.aliyuncs.com/img/seTraining/stage1/dancingBugRun.gif)

* 可以看到，`Bug` 每次转向，不再是固定的转向次数，而是变化的次数。

---

## 5. SonarQube 检查

* 检查结果如下图所示：

![sonarqube4Stage2Part2](http://markdown-pictures-huzzh3.oss-accelerate.aliyuncs.com/img/seTraining/stage1/sonarqube4Stage2Part2.png)

* 可以看到我们的 sonarQube 检查也顺利通过了。

![commentRate4Stage2Part2](http://markdown-pictures-huzzh3.oss-accelerate.aliyuncs.com/img/seTraining/stage1/commentRate4Stage2Part2.png)

* 从 sonarqube 的结果我们也可以看到 comments rate 17.2%，超过了 10%，符合要求。

![duplications4Stage2Part2](http://markdown-pictures-huzzh3.oss-accelerate.aliyuncs.com/img/seTraining/stage1/duplications4Stage2Part2.png)

* 从 sonarqube 的结果我们也可以看到我们的 Duplications Line 为 0，没有达到 10 行，符合要求。

* 由于新版的 sonarqube 将 RCI (Rules Compliance Index) 废弃了，取而代之的是直接向我们展示我们的 Bug，Smell 等代码需要改进的地方的数量，虽然看不到 RCI 了，但我们可以用别的方式计算我们的 RCI，下图中是我们启用的 rules 的数量：

![rules4Java](http://markdown-pictures-huzzh3.oss-accelerate.aliyuncs.com/img/seTraining/stage1/rules4Java.png)

* 而我们程序一共有 17 处 Code Smells，总共有 461 条 rules，所以我们的 `RCI = 1 - 17 / 461 = 96.31% `，超过 60 %，符合要求。

