# The Critter Class

## Set 7

> The source code for the Critter class is in the critters directory

---

## Question 1

### 问题

> What methods are implemented in Critter?

### 回答

* `Critter` 类实现的方法有：
  * `Critter()`，构造函数，作用是初始化 `Critter`，实际上是空实现。
  * `act()`，即让 `Critter` 执行它的操作。
  * `getActors()`，即获取到 `Critter` 周围的 `Actors`。
  * `processActors()`，即输入一系列 `ArrayList<Actor>`，然后遍历这个 `ArrayList` ，执行一系列操作。
  * `getMoveLocations()`，获取到 `Critter` 周围空白的区域，表示这些空白的区域都是 `Critter` 可以移动到的区域。
  * `selectMoveLocation()`，输入一系列 `Locations`，随机选取一个区域。
  * `makeMove()`，输入一个 `Location`，如果不为 `null`，那么 `Critter` 就会被移除，否则 `Critter` 就会移动到该位置上。

---

## Question 2

### 问题

> What are the five basic actions common to all critters when they act?

### 回答

* 在 `act` 函数中有五个基本的共同操作，如下：
  * `getActors()`，获取到周围的 `actors` 。
  * `processActors()`，对输入的 `actors` 进行对应的操作。
  * `getMoveLocations()` ，获取到可以移动到的区域。
  * `selectMoveLocation()`，随机选择一个区域。
  * `makeMove()`，让 `Critter` 进行移动。

---

## Question 3

### 问题

> Should subclasses of Critter override the getActors method? Explain.

### 回答

* 需要的，部分 `Critter` 的子类需要重写 `getActors` ，因为部分子类并不需要获取到所有的周围的 `Actors` ，如 `CrabCritter` 子类只需要获取到它正前方、左前方、右前方的 `Actors`，这时候子类就需要重写 `getActors` ，如下面代码所示：

```java
    // @file: gridWorld/GridWorldCode/projects/critters/CrabCritter.java
    // @line: 44~57

	public ArrayList<Actor> getActors()
    {
        ArrayList<Actor> actors = new ArrayList<Actor>();
        int[] dirs =
            { Location.AHEAD, Location.HALF_LEFT, Location.HALF_RIGHT };
        for (Location loc : getLocationsInDirections(dirs))
        {
            Actor a = getGrid().get(loc);
            if (a != null)
                actors.add(a);
        }

        return actors;
    }
```

---

## Question 4

### 问题

> Describe the way that a critter could process actors.

### 回答

* `Critter` 可以对 `Actors` 有多种操作，如：
  * 可以吃掉周围的 `Actors` 或者让周围的 `Actors` 移动
  * `ChameloenCritter` 将周围的 `Actors` 变色，如下面代码所示：

```java
    // @file: gridWorld/GridWorldCode/projects/critters/ChameleonCritter.java
    // @line: 36~45
    
    public void processActors(ArrayList<Actor> actors)
    {
        int n = actors.size();
        if (n == 0)
            return;
        int r = (int) (Math.random() * n);

        Actor other = actors.get(r);
        setColor(other.getColor());
    }
```

---

## Question 5

### 问题

> What three methods must be invoked to make a critter move? Explain each of these methods.

### 回答

* `Critter` 移动用到了以下三个函数：
  * `getMoveLocations()`，该函数会返回 `Criiter` 周围的一系列空白相邻区域。
  * `selectMoveLocation()`，如果将 `getMoveLocations()` 返回的区域作为参数传入 `selectMoveLocation()`，那么该函数就会随机选取其中一个空白区域并返回。
  * `makeMove()`，如果将 `selectMoveLocation()` 返回的区域作为参数传入 `makeMove()`，那么该函数就会让 `Critter` 移动到该区域。 

---

## Question 6

### 问题

> Why is there no Critter constructor?

### 回答

* 准确来说并不是没有 `Critter` 的构造函数，而是 `Critter` 的构造函数来自于 `Actor`。
* 因为原作者没有写 `Critter` 的构造函数，所以 `Java` 就自动写了一个默认的 `Critter` 构造函数，由于 `Critter` 是 `Actor` 的子类，所以这个默认的构造函数是通过 `super()` 实现的，即调用父类 `Actor` 的构造函数。
* 所以 `Critter ` 的构造函数就是 `Actor` 的构造函数，因此默认的 `Critter` 的颜色是蓝色的，且头朝向 `NORTH` 。

---

# Extending the Critter Class

## Set 8

> The source code for the ChameleonCritter class is in the critters directory

---

## Question 1

### 问题

> Why does act cause a ChameleonCritter to act differently from a Critter even though ChameleonCritter does not override act?

### 回答

* 为了回答这个问题，我们首先要明白 `act` 函数干了什么：
  * `act` 函数调用了 `getActors` ， `processActors` ， `getMoveLocations`， `selectMoveLocation`，`makeMove` 函数。
  * 而 `ChameleonCritter` 重写了 `processActor` 和 `makeMove` 函数，因此 `act` 函数也受到了影响，故会产生不一样的行为结果。
* `Critter` 的 `processActor` 函数移除了它周围不是 `Rock` 或者 `Critter` 的 `Actors`，而 `ChameleonCritter` 的 `processActor` 则是随机挑选一个周围的 `Actor` ，然后将自己的颜色变成和该 `Actor` 相同的颜色，如下面源码所示：

```java
    // @file: gridWorld/GridWorldCode/projects/critters/ChameleonCritter.java
    // @line: 36~45
    
    public void processActors(ArrayList<Actor> actors)
    {
        int n = actors.size();
        if (n == 0)
            return;
        int r = (int) (Math.random() * n);

        Actor other = actors.get(r);
        setColor(other.getColor());
    }
```

* `Critter` 的 `makMove` 函数只是单纯的让 `Critter` 移动到该位置上，而 `ChameleonCritter` 还会改变自己的朝向，让自己的头朝向目标位置，如下面源码所示：

```java
    // @file: gridWorld/GridWorldCode/projects/critters/ChameleonCritter.java
    // @line: 50~54
    
    public void makeMove(Location loc)
    {
        setDirection(getLocation().getDirectionToward(loc));
        super.makeMove(loc);
    }
```

---

## Question 2

### 问题

> Why does the makeMove method of ChameleonCritter call super.makeMove?

### 回答

* `ChameleonCritter` 首先在 `makeMove()` 中改变自己的头的朝向，然后调用 `super.makeMove()` ，使其真正得移动到对应的 `Location` 上。

---

## Question 3

### 问题

> How would you make the ChameleonCritter drop flowers in its old location when it moves?

### 回答

* 要想让 `ChameleonCritter` 在旧的位置上留下 `Flower` ，只需要修改一下 `makeMove ` 函数即可，只需要使用一个临时变量 `oldLocation` 用于记录下 `ChameleonCritter` 的旧位置，然后 `ChameleonCritter`  移动之后，再在 `oldLocation` 上留下 `Flower` 即可，具体的源码如下图所示：

```java
    public void makeMove(Location loc)
    {
    	Location oldLocation = getLocation();
    	
        setDirection(getLocation().getDirectionToward(loc));
        super.makeMove(loc);
        
        if (!oldLocation.equals(loc)) {
        	Flower flower = new Flower(getColor());
        	flower.putSelfInGrid(getGrid(), oldLocation);
        }
    }
```

* 注意，`!oldLocation.equals(loc)` 这个判断条件是需要的，这是为了避免 `ChameleonCritter` 在没有移动的情况下还留下了 `Flower` ，具体的 `GUI` 的演示如下所示：

![part4ChameleonCritterMakeMoveOverride](http://markdown-pictures-huzzh3.oss-accelerate.aliyuncs.com/img/seTraining/stage1/part4ChameleonCritterMakeMoveOverride.gif)

---

## Question 4

### 问题

> Why doesn’t ChameleonCritter override the getActors method?

### 回答

* 因为 `ChameleonCritter` 的 `processActors()` 需要处理的对象包括了其周围的所有对象，所以它可以直接沿用 `Critter` 的 `getActors()` 函数获取所有的周围对象进行处理，而不需要重写。

---

## Question 5

### 问题

> Which class contains the getLocation method?

### 回答

* `Actor` 类包含了 `getLocation()` 方法，且所有继承了 `Actor` 的子类也继承了 `getLocation()` 方法，该方法在 `Actor` 类的位置如下面源码所示：

```java
  // @file: gridWorld/GridWorldCode/gridworld/Actor
  // @line: 无标示，只需要找到对应的 getLocation 函数即可
  
  // Method descriptor #71 ()Linfo/gridworld/grid/Location;
  // Stack: 1, Locals: 1
  public info.gridworld.grid.Location getLocation();
    0  aload_0 [this]
    1  getfield info.gridworld.actor.Actor.location : info.gridworld.grid.Location [6]
    4  areturn
      Line numbers:
        [pc: 0, line: 104]
      Local variable table:
        [pc: 0, pc: 5] local: this index: 0 type: info.gridworld.actor.Actor
```

---

## Question 6

### 问题

> How can a Critter access its own grid?

### 回答

* `Critter` 可以用它继承到的 `getGrid()` 函数来获得它的 `grid` 。

---

# Another Critter

## Set 9

## Question 1

### 问题

> Why doesn’t CrabCritter override the processActors method?

### 回答

* `CrabCritter` 的 `processActors()` 函数的目的就是“吃掉”相邻的（只包含左前方、右前方、正前方的） `Actors` （除了 `Rock` 和 `Critter`），这与 `Critter` 本身的 `processActors()` 是一致的，所以不需要重写。

---

## Question 2

### 问题

> Describe the process a CrabCritter uses to find and eat other actors. Does it always eat all neighboring actors? Explain.

### 回答

* `CrabCritter` 的 `getActors()` 函数会获取到 “左前方、右前方、正前方” 的 `Actors` ，然后将其作为参数传入 `processActors()` ，之后在该函数中，`CrabCritter` 会“吃掉”那些传入的 `Actors` ，而其他相邻位置的 `Actors` 则不会受影响，具体的源码如下图所示：

```java
    // @file: gridWorld/GridWorldCode/projects/critters/CrabCritter
    // @line: 44~57
    
    public ArrayList<Actor> getActors()
    {
        ArrayList<Actor> actors = new ArrayList<Actor>();
        int[] dirs =
            { Location.AHEAD, Location.HALF_LEFT, Location.HALF_RIGHT };
        for (Location loc : getLocationsInDirections(dirs))
        {
            Actor a = getGrid().get(loc);
            if (a != null)
                actors.add(a);
        }

        return actors;
    }
```

---

## Question 3

### 问题

> Why is the getLocationsInDirections method used in CrabCritter?

### 回答

* 我们可以看到使用了 `getLocationInDirections` 的位置如下面所示：

```java
    // @file: gridWorld/GridWorldCode/projects/critters/CrabCritter
    // @line: 49~54

	for (Location loc : getLocationsInDirections(dirs))
    {
        Actor a = getGrid().get(loc);
        if (a != null)
            actors.add(a);
    }
```

* 可以看到这里使用这个函数的目的是为了 “遍历”，`dirs` 包含了 “左前方、右前方、正前方“ 的位置，传入该函数作为参数，然后 `getLocationInDirections` 会得到对应的 `Locations` ，之后 `for` 循环会遍历所有的 `Locations` ，然后吃掉对应的 `Actors`。

---

## Question 4

### 问题

> If a CrabCritter has location (3, 4) and faces south, what are the  possible locations for actors that are returned by a call to the  getActors method?

### 回答

* `getActors()` 函数会返回 `(4,3) ， (4,4) ， (4,5)` 位置上的 `Actors` 。

---

## Question 5

### 问题

> What are the similarities and differences between the movements of a CrabCritter and a Critter?

### 回答

* 相同点：
  * `CrabCritter` 和 `Critter` 在移动的时候都不会改变它们的方向。
  * `CrabCritter` 和 `Critter` 都会随机选取一个合法位置进行移动。
* 不同点：
  * `CrabCritter` 只能够向左或向右移动，而 `Critter` 可以向四面八方移动。
  * `CrabCritter` 不能移动的话，它就会随机向右或向左转向，而 `Critter` 不能移动的时候，它不会转向。

---

## Question 6

### 问题

> How does a CrabCritter determine when it turns instead of moving?

### 回答

* 如果 `makeMove()` 函数传入的参数 `loc` 等于它现在的位置（也就是说 `CrabCritter` 现在没有别的合法位置可以移动了），那么 `CrabCritter` 就会选择转向，如下面源码所示：

```java
        // @file: gridWorld/GridWorldCode/projects/critters/CrabCritter
        // @line: 79~88
        
        if (loc.equals(getLocation()))
        {
            double r = Math.random();
            int angle;
            if (r < 0.5)
                angle = Location.LEFT;
            else
                angle = Location.RIGHT;
            setDirection(getDirection() + angle);
        }
```

---

## Question 7

### 问题

> Why don’t the CrabCritter objects eat each other?

### 回答

* 因为 `CrabCritter` 继承了 `Critter` 的 `processActors()` 函数，该函数中，`Critter` 只会吃掉不是 `Rock` 或 `Critter` 的对象。所以 `CrabCritter` 不会吃掉 `CrabCritter` ，具体的 `processActors()` 源码如下所示：

```java
  // @file: gridWorld/GridWorldCode/gridworld/Critter
  // @line: 无标示，只需要找到对应的 processActors 函数即可
  
  public void processActors(java.util.ArrayList actors);
     0  aload_1 [actors]
     1  invokevirtual java.util.ArrayList.iterator() : java.util.Iterator [10]
     4  astore_2 [i$]
     5  aload_2 [i$]
     6  invokeinterface java.util.Iterator.hasNext() : boolean [11] [nargs: 1]
    11  ifeq 45
    14  aload_2 [i$]
    15  invokeinterface java.util.Iterator.next() : java.lang.Object [12] [nargs: 1]
    20  checkcast info.gridworld.actor.Actor [13]
    23  astore_3 [a]
    24  aload_3 [a]
    25  instanceof info.gridworld.actor.Rock [14]
    28  ifne 42
    31  aload_3 [a]
    32  instanceof info.gridworld.actor.Critter [15]
    35  ifne 42
    38  aload_3 [a]
    39  invokevirtual info.gridworld.actor.Actor.removeSelfFromGrid() : void [16]
    42  goto 5
    45  return
```

---

