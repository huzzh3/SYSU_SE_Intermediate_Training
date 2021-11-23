# Part 3

## The Location Class Set 3

### Preface 

Assume the following statements to answer the following questions:

> Location loc1 = new Location(4,3); 
> Location loc2 = new Location(3,4);

---

### Question 1

#### 问题

> How would you access the row value for loc1? 

#### 回答

* 只需要通过 `loc1.getRow()` 即可获取 `loc1` 的行号。在 `Location.class` 中有专门的函数供用户使用，如下所示：

```java
  // @file: GridWorldCode/gridworld.jar/Location.class
  // @line: 无标识，只需要打开文件，找到对应的 getRow 函数即可

  public int getRow();
    0  aload_0 [this]
    1  getfield info.gridworld.grid.Location.row : int [2]
    4  ireturn
```

* 可以看到 `getRow` 函数仅仅只是获取到了 `Location` 类的变量 `row` 而已。

---

### Question 2

#### 问题

> What is the value of b after the following statement is executed? 
>
> > boolean b = loc1.equals(loc2);

#### 回答

* 该指令会返回 `false`，首先 `equals` 函数的作用是判断两个 `Location` 是否相等，其具体实现如下：

```java
  // @file: GridWorldCode/gridworld.jar/Location.class
  // @line: 无标识，只需要打开文件，找到对应的 equals 函数即可

  public boolean equals(java.lang.Object other);
     0  aload_1 [other]
     1  instanceof info.gridworld.grid.Location [4]
     4  ifne 9
     7  iconst_0
     8  ireturn
     9  aload_1 [other]
    10  checkcast info.gridworld.grid.Location [4]
    13  astore_2 [otherLoc]
    14  aload_0 [this]
    15  invokevirtual info.gridworld.grid.Location.getRow() : int [5]
    18  aload_2 [otherLoc]
    19  invokevirtual info.gridworld.grid.Location.getRow() : int [5]
    22  if_icmpne 40
    25  aload_0 [this]
    26  invokevirtual info.gridworld.grid.Location.getCol() : int [6]
    29  aload_2 [otherLoc]
    30  invokevirtual info.gridworld.grid.Location.getCol() : int [6]
    33  if_icmpne 40
    36  iconst_1
    37  goto 41
    40  iconst_0
    41  ireturn
```

* 可以看到 `equals` 的运行逻辑如下：
  * 首先判断 `other` 是不是 `Location` 型的数据：
    * 如果是，那么就继续执行
    * 如果不是，就返回 0
  * 然后获取 `this` 和 `other` 的 `row` 进行比较：
    * 如果相等，就继续执行
    * 如果不相等，则返回 0
  * 然后获取 `this` 和 `other` 的 `col` 进行比较：
    * 如果相等，就返回 1
    * 如果不相等，则返回 0
* 很明显，根据 `equals` 的逻辑，我们可以明确知道，`boolean b = loc1.equals(loc2);` 的返回值为 `false`

---

### Question 3

#### 问题

> What is the value of loc3 after the following statement is exectued?
>
> >  Location loc3 = loc2.getAdjacentLocation(Location.SOUTH); 

#### 回答

* 该指令的返回值为 `(4,4)`，我们可以找到 `getAdjacentLocation` 的函数实现如下：

```java
  // @file: GridWorldCode/gridworld.jar/Location.class
  // @line: 无标识，只需要打开文件，找到对应的 getAdjacentLocation 函数即可 

  public info.gridworld.grid.Location getAdjacentLocation(int direction);
      0  iload_1 [direction]
      1  bipush 22
      3  iadd
      4  sipush 360
      7  irem
      8  istore_2 [adjustedDirection]
      9  iload_2 [adjustedDirection]
     10  ifge 19
     13  wide
     14  iinc 2 360 [adjustedDirection]
     19  iload_2 [adjustedDirection]
     20  bipush 45
     22  idiv
     23  bipush 45
     25  imul
     26  istore_2 [adjustedDirection]
     27  iconst_0
     28  istore_3 [dc]
     29  iconst_0
     30  istore 4 [dr]
     32  iload_2 [adjustedDirection]
     33  bipush 90
     35  if_icmpne 43
     38  iconst_1
     39  istore_3 [dc]
     40  goto 134
     43  iload_2 [adjustedDirection]
     44  sipush 135
     47  if_icmpne 58
     50  iconst_1
     51  istore_3 [dc]
     52  iconst_1
     53  istore 4 [dr]
     55  goto 134
     58  iload_2 [adjustedDirection]
     59  sipush 180
     62  if_icmpne 71
     65  iconst_1
     66  istore 4 [dr]
     68  goto 134
     71  iload_2 [adjustedDirection]
     72  sipush 225
     75  if_icmpne 86
     78  iconst_m1
     79  istore_3 [dc]
     80  iconst_1
     81  istore 4 [dr]
     83  goto 134
     86  iload_2 [adjustedDirection]
     87  sipush 270
     90  if_icmpne 98
     93  iconst_m1
     94  istore_3 [dc]
     95  goto 134
     98  iload_2 [adjustedDirection]
     99  sipush 315
    102  if_icmpne 113
    105  iconst_m1
    106  istore_3 [dc]
    107  iconst_m1
    108  istore 4 [dr]
    110  goto 134
    113  iload_2 [adjustedDirection]
    114  ifne 123
    117  iconst_m1
    118  istore 4 [dr]
    120  goto 134
    123  iload_2 [adjustedDirection]
    124  bipush 45
    126  if_icmpne 134
    129  iconst_1
    130  istore_3 [dc]
    131  iconst_m1
    132  istore 4 [dr]
    134  new info.gridworld.grid.Location [4]
    137  dup
    138  aload_0 [this]
    139  invokevirtual info.gridworld.grid.Location.getRow() : int [5]
    142  iload 4 [dr]
    144  iadd
    145  aload_0 [this]
    146  invokevirtual info.gridworld.grid.Location.getCol() : int [6]
    149  iload_3 [dc]
    150  iadd
    151  invokespecial info.gridworld.grid.Location(int, int) [7]
    154  areturn
```

* 函数太长了，碍于篇幅，我们就不一一分析了，大概的运行逻辑，就是根据输入的 `direction` 和当前所在的 `location` 来给临时变量 `dr` 赋值，最终根据 `dr` 来初始化一个 `Location` 实例，返回给函数
* 那么由于我们输入的是 `Location.SOUTH` ，那么很自然的，通过上面的函数，就可以得到一个 `col` 与原位置一直，但是 `row` 会比原位置多 1 的一个新位置，也就是 `(4, 4)`

---

### Question 4

#### 问题

> What is the value of dir after the following statement is executed? 
>
> > int dir = loc1.getDirectionToward(new Location(6,5)); 

#### 回答

* 执行完该语句，会得到返回值 `135` ，即东南方向，我们先来分析 `getDirectionToward` 的源码：

```java
  // @file: GridWorldCode/gridworld.jar/Location.class
  // @line: 无标识，只需要打开文件，找到对应的 getDirectionToward 函数即可 

  public int getDirectionToward(info.gridworld.grid.Location target);
     0  aload_1 [target]
     1  invokevirtual info.gridworld.grid.Location.getCol() : int [6]
     4  aload_0 [this]
     5  invokevirtual info.gridworld.grid.Location.getCol() : int [6]
     8  isub
     9  istore_2 [dx]
    10  aload_1 [target]
    11  invokevirtual info.gridworld.grid.Location.getRow() : int [5]
    14  aload_0 [this]
    15  invokevirtual info.gridworld.grid.Location.getRow() : int [5]
    18  isub
    19  istore_3 [dy]
    20  iload_3 [dy]
    21  ineg
    22  i2d
    23  iload_2 [dx]
    24  i2d
    25  invokestatic java.lang.Math.atan2(double, double) : double [8]
    28  invokestatic java.lang.Math.toDegrees(double) : double [9]
    31  d2i
    32  istore 4 [angle]
    34  bipush 90
    36  iload 4 [angle]
    38  isub
    39  istore 5 [compassAngle]
    41  iinc 5 22 [compassAngle]
    44  iload 5 [compassAngle]
    46  ifge 55
    49  wide
    50  iinc 5 360 [compassAngle]
    55  iload 5 [compassAngle]
    57  bipush 45
    59  idiv
    60  bipush 45
    62  imul
    63  ireturn
```

* `getDirectionToward` 的运行逻辑如下：

  * 首先会将当前位置的`row`和输入的参数位置 `target`的`row` 进行一个相减，得到它们的差，存放在临时变量 `dx` 中

  * 同理也会对 `col` 进行相减处理，得到的差会存放在临时变量 `dy` 中
  * 然后将得到的 `dx` 和 `dy` 转换为 `double` 型的数据，作为参数传入 `atan2` 函数，得到它们的夹角
  * 然后将夹角输入到 `toDegrees` 的函数中，得到以度数表示的数据，然后转换为 `int` 类型，存放在 `angle` 变量中
  * 最后进行简单的处理，得到方位角，保存在变量 `compassAngle` 中，然后返回
* 根据上面的逻辑，我们不难得出，`int dir = loc1.getDirectionToward(new Location(6,5)); ` 的结果是 135

---

### Question 5

#### 问题

> How does the getAdjacentLocation method know which adjacent location to return?

#### 回答

* 正如我们上面分析的那样， `getAdjacentLocation` 函数会根据传入的参数 `direction` 知晓自己应该获取哪一块相邻的 `Location`
* 通过上面的 `getAdjacentLocation` 我们不难知道，传入的 `direction` 可能不是刚好是方位角，那么这个时候 ``getAdjacentLocation`` 函数就会根据靠近 `direction` 最近的方位角进行判断，根据这个最近的方位角，来获取到对应的相邻单元格作为返回值

---

## The Grid Interface Set 4

### Question 1

#### 问题

> How can you obtain a count of the objects in a grid? How can you obtain a count of the empty locations in a bounded grid?

#### 回答

* 我们设 `gr` 是一个 `Grid` 的实例，那么根据 `gr.getOccupiedLocations().size()` 函数就可以得到 `Grid` 的物体个数，`getOccupiedLocations()` 的源码如下：

```java
  // @file: GridWorldCode/gridworld/BoundedGrid
  // @line: 无标识，只需要打开文件，找到对应的 getOccupiedLocations 函数即可 

  public java.util.ArrayList getOccupiedLocations();
     0  new java.util.ArrayList [12]
     3  dup
     4  invokespecial java.util.ArrayList() [13]
     7  astore_1 [theLocations]
     8  iconst_0
     9  istore_2 [r]
    10  iload_2 [r]
    11  aload_0 [this]
    12  invokevirtual info.gridworld.grid.BoundedGrid.getNumRows() : int [9]
    15  if_icmpge 67
    18  iconst_0
    19  istore_3 [c]
    20  iload_3 [c]
    21  aload_0 [this]
    22  invokevirtual info.gridworld.grid.BoundedGrid.getNumCols() : int [11]
    25  if_icmpge 61
    28  new info.gridworld.grid.Location [14]
    31  dup
    32  iload_2 [r]
    33  iload_3 [c]
    34  invokespecial info.gridworld.grid.Location(int, int) [15]
    37  astore 4 [loc]
    39  aload_0 [this]
    40  aload 4 [loc]
    42  invokevirtual info.gridworld.grid.BoundedGrid.get(info.gridworld.grid.Location) : java.lang.Object [16]
    45  ifnull 55
    48  aload_1 [theLocations]
    49  aload 4 [loc]
    51  invokevirtual java.util.ArrayList.add(java.lang.Object) : boolean [17]
    54  pop
    55  iinc 3 1 [c]
    58  goto 20
    61  iinc 2 1 [r]
    64  goto 10
    67  aload_1 [theLocations]
    68  areturn
```

* 那么通过源码，我们很容易分析得到，该源码的作用就是遍历 `Grid` 的每一个位置，判断上面有没有 `Object`，如果有的话，那么就加到 `theLocations` 这个数组中，没有的话就查看下一个位置
* 那么最终返回的数组，就会保存所有的 `Object` 的信息，那么我们只需要用 `Java` 内置的数组的 `size()` 函数，就可以得到 `Object` 的数量
* 那么根据这个逻辑，如果我们想要得到所有的空白位置的数量，只需要用总格子数，减去占据的格子数，就可以得到空白位置的数量，其方法如下：


> gr.getNumRows()*gr.getNumCols() - gr.getOccupiedLocations().size()

---

### Question 2

#### 问题

> How can you check if location (10,10) is in the grid? 

#### 回答

* 很简单，只需要调用函数 `isValid` 就可以知道 `(10, 10)`这个位置是否在 `grid` 中了，指令如下所示：

```java
gr.isValid(new Location(10,10))
```

* `isValid` 的源码如下：

```java
  // @file: GridWorldCode/gridworld/BoundedGrid.class
  // @line: 无标识，只需要打开文件，找到对应的 isValid 函数即可 

  public boolean isValid(info.gridworld.grid.Location loc);
     0  iconst_0
     1  aload_1 [loc]
     2  invokevirtual info.gridworld.grid.Location.getRow() : int [8]
     5  if_icmpgt 42
     8  aload_1 [loc]
     9  invokevirtual info.gridworld.grid.Location.getRow() : int [8]
    12  aload_0 [this]
    13  invokevirtual info.gridworld.grid.BoundedGrid.getNumRows() : int [9]
    16  if_icmpge 42
    19  iconst_0
    20  aload_1 [loc]
    21  invokevirtual info.gridworld.grid.Location.getCol() : int [10]
    24  if_icmpgt 42
    27  aload_1 [loc]
    28  invokevirtual info.gridworld.grid.Location.getCol() : int [10]
    31  aload_0 [this]
    32  invokevirtual info.gridworld.grid.BoundedGrid.getNumCols() : int [11]
    35  if_icmpge 42
    38  iconst_1
    39  goto 43
    42  iconst_0
    43  ireturn
```

* 可以看到 `isValid` 函数的作用如下：
  * 获取到传入的参数 `loc` 的行号和列号
  * 获取到网格 `grid` 的行数和列数
  * 然后判断的 `loc` 的行号和列号是否合法，如果合法就返回 1，如果不合法就返回 0
* 那么很明显，如果 `(10, 10)` 在网格中的话，该函数就会返回 `ture` ，否则就会返回 `false`

---

### Question 3

#### 问题

> Grid contains method declarations, but no code is supplied in the methods.  Why?  Where can you find the implementations of these methods? 

#### 回答

* `Grid` 的文件里面的所有函数都没有对应的实现，只有函数的声明，原因是 `Grid` 仅仅是一个接口文件，它的作用仅在于告诉其他函数它们必须实现的方法。
* 如果想要找到对应的函数实现的话，可以在 `AbstractGrid` 、`BoundedGrid` 和 `UnBoundedGrid` 中找到。
* 但是需要注意的是， `AbstractGrid` 仅仅只是一个抽象类，也就是说它只实现了 `Grid` 函数，而  `BoundedGrid` 和 `UnBoundedGrid` 函数则是该抽象类的扩展，它们其中包含了很多 `Grid` 没有声明的函数。

---

### Question 4

#### 问题

> All methods that return multiple objects return them in an ArrayList.  Do you think it would be a better design to return the objects in an array? Explain your answer. 

#### 回答

* 我个人认为使用 `array` 要比使用 `ArrayList` 效果会差一点，因为 `array` 在使用之前必须要制定它的大小，而 `ArrayList` 不用，而我们在使用函数的时候，如 `getOccupiedLocations()` ，在没有变量存储 `Object` 的个数的时候，我们是无法事先得知该返回的数组的大小的，也就无从对 `array` 进行初始化。
* 所以在这里，使用 `ArrayList` 进行动态分配要更为方便一点。

---

## The Actor Class Set 5

### Question 1

#### 问题

> Name three properties of every actor.

#### 回答

* 每个 `actor` 至少都包含 `color`，`direction`，`location` 三个属性

---

### Question 2

#### 问题

> When an actor is constructed, what is its direction and color

#### 回答

* 默认的 `direction` 是 `North`；默认的 `color` 是 `BLUE`，原因如下面源码所示：

```java
  // @file: GridWorldCode/gridworld/Actor.class
  // @line: 无标识，只需要打开文件，找到对应的 Actor 函数即可 

  public Actor();
     0  aload_0 [this]
     1  invokespecial java.lang.Object() [1]
     4  aload_0 [this]
     5  getstatic java.awt.Color.BLUE : java.awt.Color [2]
     8  putfield info.gridworld.actor.Actor.color : java.awt.Color [3]
    11  aload_0 [this]
    12  iconst_0
    13  putfield info.gridworld.actor.Actor.direction : int [4]
    16  aload_0 [this]
    17  aconst_null
    18  putfield info.gridworld.actor.Actor.grid : info.gridworld.grid.Grid [5]
    21  aload_0 [this]
    22  aconst_null
    23  putfield info.gridworld.actor.Actor.location : info.gridworld.grid.Location [6]
    26  return
```

* 从上面的源码就很好的证实了，初始化的 `Color` 为 `BLUE`
* 同时，可以看到 `direction` 的值为 0，这也就对应着方位角的 `NORTH`

---

### Question 3

#### 问题

> Why do you think that the Actor class was created as a class instead of an interface? 

#### 回答

* 可以从两个角度来回答这个问题：
  * 首先，在 `Actor.class` 中不仅仅有函数声明，还有函数实现，而接口文件往往是不包含函数实现的。
  * 其次，我们可以直接用 `Actor` 来声明一个实例，且该实例不仅具备属性，还具备对应的函数操作，如果 `Actor` 是一个接口文件的话，它是不能够用来声明一个实例的。

---

### Question 4

#### 问题

> (a) Can an actor put itself into a grid twice without removing itself? 
>
> (b) Can an actor remove itself from a grid twice?  
>
> (c) Can an actor be placed into a grid, remove itself, and then put itself back? Try it out. What happens? 

#### 回答

* (a) 不能，一个实例不能够被放入 `Grid` 两次，在没有被移除的情况下，源码如下：

```java
  // @file: GridWorldCode/gridworld/Actor.class
  // @line: 无标识，只需要打开文件，找到对应的 putSelfInGrid 函数即可 

  public void putSelfInGrid(info.gridworld.grid.Grid gr, info.gridworld.grid.Location loc);
     0  aload_0 [this]
     1  getfield info.gridworld.actor.Actor.grid : info.gridworld.grid.Grid [5]
     4  ifnull 17
     7  new java.lang.IllegalStateException [7]
    10  dup
    11  ldc <String "This actor is already contained in a grid."> [8]
    13  invokespecial java.lang.IllegalStateException(java.lang.String) [9]
    16  athrow
    17  aload_1 [gr]
    18  aload_2 [loc]
    19  invokeinterface info.gridworld.grid.Grid.get(info.gridworld.grid.Location) : java.lang.Object [10] [nargs: 2]
    24  checkcast info.gridworld.actor.Actor [11]
    27  astore_3 [actor]
    28  aload_3 [actor]
    29  ifnull 36
    32  aload_3 [actor]
    33  invokevirtual info.gridworld.actor.Actor.removeSelfFromGrid() : void [12]
    36  aload_1 [gr]
    37  aload_2 [loc]
    38  aload_0 [this]
    39  invokeinterface info.gridworld.grid.Grid.put(info.gridworld.grid.Location, java.lang.Object) : java.lang.Object [13] [nargs: 3]
    44  pop
    45  aload_0 [this]
    46  aload_1 [gr]
    47  putfield info.gridworld.actor.Actor.grid : info.gridworld.grid.Grid [5]
    50  aload_0 [this]
    51  aload_2 [loc]
    52  putfield info.gridworld.actor.Actor.location : info.gridworld.grid.Location [6]
    55  return
```

* 从上面的函数实现可以看到，如果我们尝试将相同的实例，重复放入 `Grid` 中，就会报错，返回 `IllegalStateException`

* (b) 不能，同一个实例也不能移除自己两次，具体源码如下：

```java
  // @file: GridWorldCode/gridworld/Actor.class
  // @line: 无标识，只需要打开文件，找到对应的 removeSelfFromGrid 函数即可 

  public void removeSelfFromGrid();
     0  aload_0 [this]
     1  getfield info.gridworld.actor.Actor.grid : info.gridworld.grid.Grid [5]
     4  ifnonnull 17
     7  new java.lang.IllegalStateException [7]
    10  dup
    11  ldc <String "This actor is not contained in a grid."> [14]
    13  invokespecial java.lang.IllegalStateException(java.lang.String) [9]
    16  athrow
    17  aload_0 [this]
    18  getfield info.gridworld.actor.Actor.grid : info.gridworld.grid.Grid [5]
    21  aload_0 [this]
    22  getfield info.gridworld.actor.Actor.location : info.gridworld.grid.Location [6]
    25  invokeinterface info.gridworld.grid.Grid.get(info.gridworld.grid.Location) : java.lang.Object [10] [nargs: 2]
    30  aload_0 [this]
    31  if_acmpeq 69
    34  new java.lang.IllegalStateException [7]
    37  dup
    38  new java.lang.StringBuilder [15]
    41  dup
    42  invokespecial java.lang.StringBuilder() [16]
    45  ldc <String "The grid contains a different actor at location "> [17]
    47  invokevirtual java.lang.StringBuilder.append(java.lang.String) : java.lang.StringBuilder [18]
    50  aload_0 [this]
    51  getfield info.gridworld.actor.Actor.location : info.gridworld.grid.Location [6]
    54  invokevirtual java.lang.StringBuilder.append(java.lang.Object) : java.lang.StringBuilder [19]
    57  ldc <String "."> [20]
    59  invokevirtual java.lang.StringBuilder.append(java.lang.String) : java.lang.StringBuilder [18]
    62  invokevirtual java.lang.StringBuilder.toString() : java.lang.String [21]
    65  invokespecial java.lang.IllegalStateException(java.lang.String) [9]
    68  athrow
    69  aload_0 [this]
    70  getfield info.gridworld.actor.Actor.grid : info.gridworld.grid.Grid [5]
    73  aload_0 [this]
    74  getfield info.gridworld.actor.Actor.location : info.gridworld.grid.Location [6]
    77  invokeinterface info.gridworld.grid.Grid.remove(info.gridworld.grid.Location) : java.lang.Object [22] [nargs: 2]
    82  pop
    83  aload_0 [this]
    84  aconst_null
    85  putfield info.gridworld.actor.Actor.grid : info.gridworld.grid.Grid [5]
    88  aload_0 [this]
    89  aconst_null
    90  putfield info.gridworld.actor.Actor.location : info.gridworld.grid.Location [6]
    93  return
```

* 可以看到，如果同一个实例尝试移除自己两次，那么就会报错，返回 IllegalStateException
* (c) 可以，实例可以先被放入 `grid` 中，然后再被移除，然后再放入网格中，我们写一段 Java 代码进行验证：

```java
public class BugRunner
{
    public static void main(String[] args)
    {
        ActorWorld world = new ActorWorld();
        Grid gr = world.getGrid();
        
        Bug bug = new Bug();
        world.add(bug);
        bug.removeSelfFromGrid();
        bug.putSelfInGrid(gr, new Location(7, 1));
        
        world.show();
    }
}
```

* 最后运行结果也正常，如下面动图所示：

![stage3Report1](http://markdown-pictures-huzzh3.oss-accelerate.aliyuncs.com/img/seTraining/stage1/stage3Report1.gif)

* 可以看到运行结果没有问题。

---

### Question 5

#### 问题

> How can an actor turn 90 degrees to the right? 

#### 回答

* 这个比较简单，我们在完成 ZBug 的时候，也使用了对应的函数，让其角度旋转 90 度，其做法如下：

```java
setDirection(getDirection() + 90); 
```

* 只需要先用 getDirection 函数获取到现在的方向，然后加上 90 度，然后再重新设置回去就可以了。

---

##  Extending the Actor Class Set 6

### Question 1

#### 问题

> Which statement(s) in the canMove method ensures that a bug does not try to move out of its grid?

#### 回答

* 找到 canMove 函数，得到以下结果：

```java
  // @file: GridWorldCode/gridworld/Bug.class
  // @line: 无标识，只需要打开文件，找到对应的 canMove 函数即可  

  public boolean canMove();
     0  aload_0 [this]
     1  invokevirtual info.gridworld.actor.Bug.getGrid() : info.gridworld.grid.Grid [9]
     4  astore_1 [gr]
     5  aload_1 [gr]
     6  ifnonnull 11
     9  iconst_0
    10  ireturn
    11  aload_0 [this]
    12  invokevirtual info.gridworld.actor.Bug.getLocation() : info.gridworld.grid.Location [10]
    15  astore_2 [loc]
    16  aload_2 [loc]
    17  aload_0 [this]
    18  invokevirtual info.gridworld.actor.Bug.getDirection() : int [7]
    21  invokevirtual info.gridworld.grid.Location.getAdjacentLocation(int) : info.gridworld.grid.Location [11]
    24  astore_3 [next]
    25  aload_1 [gr]
    26  aload_3 [next]
    27  invokeinterface info.gridworld.grid.Grid.isValid(info.gridworld.grid.Location) : boolean [12] [nargs: 2]
    32  ifne 37
    35  iconst_0
    36  ireturn
    37  aload_1 [gr]
    38  aload_3 [next]
    39  invokeinterface info.gridworld.grid.Grid.get(info.gridworld.grid.Location) : java.lang.Object [19] [nargs: 2]
    44  checkcast info.gridworld.actor.Actor [20]
    47  astore 4 [neighbor]
    49  aload 4 [neighbor]
    51  ifnull 62
    54  aload 4 [neighbor]
    56  instanceof info.gridworld.actor.Flower [15]
    59  ifeq 66
    62  iconst_1
    63  goto 67
    66  iconst_0
    67  ireturn
```

* 可以看到，该函数通过 `isValid` 函数来判断下一个移动位置是否合法，从而确保 bug 不会移动到地图外围
* 转化为 Java 指令，则如下所示：

```java
if(!gr.isValid(next))  {
    return false; 
}
```

---

### Question 2

#### 问题

> Which statement(s) in the canMove method determines that a bug will not walk into a rock?

#### 回答

* 碍于篇幅，不再粘贴源码，通过上一问的源码，我们可以得知，每次 bug 调用 canMove 函数的时候，都会获取下一个位置上的 object，如果 object == null，或者 object == flower，那么就会移动，否则就会不移动，bug 就是通过这种方法来避免撞到石头上的。
* 转换为 Java 语句如下所示：

```java
Actor neighbor = gr.get(next); 
if (neighbor == null) {
    return true;
}
else if (instanceOf(neighbor) == Flower) {
    return true;
}
else {
    return false;
}
```

---

### Question 3

#### 问题

> Which methods of the Grid interface are invoked by the canMove method and why?

#### 回答

* 可以看到 canMove 函数用到了 Gird 的两个接口 ： isValid 和 get，它们都有在上述源码中出现，如下所示：

```java
    27 invokeinterface info.gridworld.grid.Grid.isValid(info.gridworld.grid.Location) : boolean [12] [nargs: 2]

    39  invokeinterface info.gridworld.grid.Grid.get(info.gridworld.grid.Location) : java.lang.Object [19] [nargs: 2]
```

* isValid 函数是用来判断下一个位置是否合法的
* get 函数是用来获取下一个位置上的 Object 的

---

### Question 4

#### 问题

> Which method of the Location class is invoked by the canMove method and why? 

#### 回答

* 可以看到 canMove 函数用到了 Location 的一个接口：getAdjacentLocation ，如下面源码所示：

```java
    21  invokevirtual info.gridworld.grid.Location.getAdjacentLocation(int) : info.gridworld.grid.Location [11]
```

* getAdjacentLocation 函数的作用就是根据传入的参数，来获取相邻的格子的坐标数据。

---

### Question 5

#### 问题

> Which methods inherited from the Actor class are invoked by the canMove method?

#### 回答

* canMove 调用了从 Actor 类继承下来的函数有： getLocation，getDirection，getGrid，其在源码中的表现有：

```java
     1  invokevirtual info.gridworld.actor.Bug.getGrid() : info.gridworld.grid.Grid [9]
     
     12  invokevirtual info.gridworld.actor.Bug.getLocation() : info.gridworld.grid.Location [10]
     
     18  invokevirtual info.gridworld.actor.Bug.getDirection() : int [7]
```

---

### Question 6

#### 问题

> What happens in the move method when the location immediately in front of the bug is out of the grid? 

#### 回答

* 这个问题很简单，实际上我在 Stage 1 的时候也发现了这个问题，当 bug 前面一个位置在地图外围的时候，如果这个时候，我们强行使用 move 函数，那么 bug 就会移除它自己，如下面动图所示：

![bugWiththeEdge1](http://markdown-pictures-huzzh3.oss-accelerate.aliyuncs.com/img/seTraining/stage1/bugWiththeEdge1.gif)

---

### Question 7

#### 问题

> Is the variable loc needed in the move method, or could it be avoided by calling getLocation() muliple 
> times? 

#### 回答

* loc 变量是需要的，不能够用 getLocation 代替，因为在上面的源码中我们可以看到，在 bug 移动之后，它需要在原来的位置上放置一个 flower，那么就必须用 loc 变量保存上一位置，才能够在上一位置保存 flower

---

### Question 8

#### 问题

> Why do you think the flowers that are dropped by a bug have the same color as the bug? 

#### 回答

* 从源码中，我们也可以分析出，某种颜色的 bug 会留下相同颜色的 flower，如下面 move 源码所示：

```java
  // @file: GridWorldCode/gridworld/Bug.class
  // @line: 无标识，只需要打开文件，找到对应的 move 函数即可  

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

* 注意源码第 51 行，它会用 getColor 获取到 bug 的颜色，然后再赋值给 flower，所以 bug 会留下相同颜色的 flower

---

### Question 9

#### 问题

>  When a bug removes itself from the grid, will it place a flower into its previous location?

#### 回答

* 这个问题，我个人感觉有点歧义，如果说我们是手动调用 removeSelfFromGrid 函数的话，那么 bug 就不会留下花朵。
* 如果是在 move 函数中调用 removeSelfFromGrid 的话，那么 bug 就会留下花朵。

---

### Question 10

#### 问题

> Which statement(s) in the move method places the flower into the grid at the bug’s previous location?

#### 回答

* 回顾我们刚才放上的 move 源码，其中放置 flower 的指令如下：

```java
    50  aload_0 [this]
    51  invokevirtual info.gridworld.actor.Bug.getColor() : java.awt.Color [16]
    54  invokespecial info.gridworld.actor.Flower(java.awt.Color) [17]
    57  astore 4 [flower]
    59  aload 4 [flower]
    61  aload_1 [gr]
    62  aload_2 [loc]
    63  invokevirtual info.gridworld.actor.Flower.putSelfInGrid(info.gridworld.grid.Grid, info.gridworld.grid.Location) : void [18]
```

* 如果转换为 Java 源码的，则如下所示：

```java
Flower flower = new Flower(getColor());
flower.putSelfInGrid(gr, loc);
```

---

### Question 11

#### 问题

> If a bug needs to turn 180 degrees, how many times should it call the turn method? 

#### 回答

* 因为每次 bug 使用 turn 函数之后，它只会调转 45 度，所以如果你需要调转 180 度的话，只需要调用 4 次turn 函数即可。
