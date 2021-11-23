# part 2 Bug Variations

18327034 胡泽钊 19级软件工程

## Question 1

### 问题

> What is the role of the instance variable sideLength?

### 回答

* `sideLength` 变量的作用是指定 BoxBug **所运动的矩形轨迹的边长**
* 相关源代码如下：

```java
// @file: GridWorld/boxBug/BoxBug.java
// @line: 43~56

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
        turn();
        steps = 0;
    }
}
```

* 其运行的逻辑如下：
  * 一开始，每个 `BoxBug` 的 `step` 被初始化为 0，意在表示 `BoxBug` 还没有移动过
  * 之后 `act` 函数开始执行，它的判断逻辑如下：
    * 如果 `step` < `sideLength` && `canMove() == true` ，即此时 `BoxBug` 的移动距离小于我们所设置的矩形轨迹的边长，且此时 `BoxBug` 的下一位置合法，那么就执行 `move` 函数，并且让 `step` 自增
    * 否则，此时 `BoxBug` 就执行两次 `turn` 函数，意思是转向 90 度，然后清空 `step`
  * 最终表现为，假如我们指定 `BoxBug` 的运行轨迹的周长为 3，**在没有障碍物的情况下**，它每移动 3 个距离，就会转向 90 度一次，然后再移动 3 个单位的距离，然后再转向 90 度一次，......，一直这样持续下去
* 其运行效果如下面动图所示：

![boxBugRun1](http://markdown-pictures-huzzh3.oss-accelerate.aliyuncs.com/img/seTraining/stage1/boxBugRun1.gif)

---

## Question 2

### 问题

> What is the role of the instance variable steps?

### 回答

* 正如我们上面回答的，`steps` 变量是用于记录 `BoxBug` 已经移动的距离，当`steps` >= `sideLength` 的时候，`BoxBug`就会转向，并清空`steps`，进行下一次计数
* 具体的源代码如下：

```java
// @file: GridWorld/boxBug/BoxBug.java
// @line: 43~56

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
        turn();
        steps = 0;
    }
}
```

---

## Question 3

### 问题

> Why is the turn method called twice when steps becomes equal to sideLength?

### 回答

* 正如我们在前两问中说的那样，之所以要执行两次 `turn` 函数，是因为想要让 `BoxBug`最终表现为转向 90 度，因为`turn` 函数每次只转向 45 度，所以要调用两次`turn`函数，才能够转向 90 度
* 相关源代码如下所示：

```java
// @file: GridWorld/boxBug/BoxBug.java
// @line: 43~56

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
        turn();
        steps = 0;
    }
}
```

---

## Question 4

### 问题

> Why can the move method be called in the BoxBug class when there is no move method in the BoxBug code?

### 回答

* 因为 `BoxBug.java` 的头部引入了 `Bug` 的包，如下面源码所示：

```java
// @file: GridWorld/boxBug/BoxBug.java
// @line: 19

import info.gridworld.actor.Bug;
```

* 而 `Bug`的包中包含了 move 函数，如下面源码所示：

```java
  // @file: gridWorld/GridWorldCode/gridworld.jar
  // @line: 无标示，打开对应的 jar 文件，即可找到 move 函数
  
  // Method descriptor #23 ()V
  // Stack: 3, Locals: 5
  public void move();
     0  aload_0 [this]
     1  invokevirtual info.gridworld.actor.Bug.getGrid() : info.gridworld.grid.Grid [9]
     4  astore_1 [gr]
     5  aload_1 [gr]
     6  ifnonnull 10
     9  return
    10  aload_0 [this]
    11  invokevirtual info.gridworld.actor.Bug.getLocation() : info.gridworld.grid.Location [10]
    14  astore_2 [loc]
    15  aload_2 [loc]
    16  aload_0 [this]
    17  invokevirtual info.gridworld.actor.Bug.getDirection() : int [7]
    20  invokevirtual info.gridworld.grid.Location.getAdjacentLocation(int) : info.gridworld.grid.Location [11]
    23  astore_3 [next]
    24  aload_1 [gr]
    25  aload_3 [next]
    26  invokeinterface info.gridworld.grid.Grid.isValid(info.gridworld.grid.Location) : boolean [12] [nargs: 2]
    31  ifeq 42
    34  aload_0 [this]
    35  aload_3 [next]
    36  invokevirtual info.gridworld.actor.Bug.moveTo(info.gridworld.grid.Location) : void [13]
    39  goto 46
    42  aload_0 [this]
    43  invokevirtual info.gridworld.actor.Bug.removeSelfFromGrid() : void [14]
    46  new info.gridworld.actor.Flower [15]
    49  dup
    50  aload_0 [this]
    51  invokevirtual info.gridworld.actor.Bug.getColor() : java.awt.Color [16]
    54  invokespecial info.gridworld.actor.Flower(java.awt.Color) [17]
    57  astore 4 [flower]
    59  aload 4 [flower]
    61  aload_1 [gr]
    62  aload_2 [loc]
    63  invokevirtual info.gridworld.actor.Flower.putSelfInGrid(info.gridworld.grid.Grid, info.gridworld.grid.Location) : void [18]
    66  return
```

* 正因为 BoxBug 引入了 Bug 的包，且 Bug 的包中包含了 move 函数，所以 BoxBug 就可以调用 move 函数

---

## Question 5

### 问题

> After a BoxBug is constructed, will the size of its square pattern always be the same? Why or why not?

### 回答

* 不一定，`BoxBug`的矩形轨迹与`BoxBug`的 `sideLength` 有关，而 `sideLength` 又和用户在初始化`BoxBug`的时候指定的参数值有关
* 故对于不同的两个`BoxBug1`和`BoxBug2`，如果用户在构造它们的参数不同的，那么其最终运动的轨迹的表现也不同
* 在`BoxBugRunner`中，默认的构造如下所示：

```java
// @file: GridWorld/boxBug/BoxBugRunner.java
// @line: 30~39

public static void main(String[] args)
{
    ActorWorld world = new ActorWorld();
    BoxBug alice = new BoxBug(6);
    alice.setColor(Color.ORANGE);
    BoxBug bob = new BoxBug(3);
    world.add(new Location(7, 8), alice);
    world.add(new Location(5, 5), bob);
    world.show();
}
```

* 可以看到，一个`BoxBug`的`sideLength`设置为6，另一个则是设置为3，最终的运动结果如下：

![boxBugRun1](http://markdown-pictures-huzzh3.oss-accelerate.aliyuncs.com/img/seTraining/stage1/boxBugRun1.gif)

---

## Quesiton 6

### 问题

> Can the path a BoxBug travels ever change? Why or why not?

### 回答

* 可以，如果此时有新的 actor ，出现在 BoxBug 的运动轨迹上，那么 BoxBug 就会转向，并选择新的路径，如下图所示：

![boxBugBarrier](http://markdown-pictures-huzzh3.oss-accelerate.aliyuncs.com/img/seTraining/stage1/boxBugBarrier.gif)

* 可以看到，红色的 BoxBug 在路上遇到了青色的 BoxBug，于是它不得不转向，选择别的路径去行走

---

## Question 7

### 问题

> When will the value of steps be zero?

### 回答

* 正如我们在之前的问题中回答的那样， `steps` 变量是用来计数 `BoxBug` 的移动次数的，再最一开始 Bug 初始化的时候，其 `steps` 就被初始化为 0，如下面源码所示：

```java
    // @file: GridWorld/boxBug/BoxBug.java
	// @line: 27~38

	private int steps;
    private int sideLength;

    /**
     * Constructs a box bug that traces a square of a given side length
     * @param length the side length
     */
    public BoxBug(int length)
    {
        steps = 0;
        sideLength = length;
    }
```

* 随后，当 `steps == sideLength`或者下个位置不能移动的时候，`steps` 也会被设置为 0，如下面源码所示：

```java
// @file: GridWorld/boxBug/BoxBug.java
// @line: 43~56

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
        turn();
        steps = 0;
    }
}
```

