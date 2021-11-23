# MazeBug

## 0 作者信息

* 学号：18327034
* 姓名：胡泽钊
* 年级：2019级
* 专业：软件工程

---

## 1 思路解析

* 本题目其实如果把思路缕清了，就十分简单，接下来一步步解析我们所用到的函数。

### 1.1 `act` 函数

* `act` 函数只需要在原来的基础上稍作修改，具体的逻辑如下：

1. 如果 `isEnd == true` ，此时游戏结束，输出所需要的步数。
2. 如果 `started == false`，此时游戏还未开始，现在要初始化游戏，具体的初始化的步骤如下：
   1. 通过起点和终点的位置，初始化 `NumberOfDirectionChoices` 数组，以方便后面进行方向预测。
   2. 初始化树 `crossLocation`，该树以栈的形式保存，其中每个节点里面都保存了**没有分叉的位置路径**。若某次行动有多个选择的位置可以走，那么新的位置将会保存在新的节点中。
3. 如果 `willMove == true` ，表示此时可以行动，那么就 `move()`，并把移动步数加一。
4. 如果上面几种情况都不符合，那么就表示现在的 `MazeBug` 走到死路了，此时需要 `getBack()`，并把移动步数加一。

---

### 1.2 `initialDirectionPrediction` 函数

* 该函数的作用是初始化 `NumberOfDirectionChoices` 数组，为后续的方向预测做准备，其运行的逻辑如下：

1. 获取到 `MazeBug` 的位置，存放在 `currentLocation`。
2. 获取到终点的位置，存放在 `endPointLocation`。
3. 根据两个位置之间行数差和列数差，对 `NumberOfDirectionChoices` 数组进行调整，如当终点位置的行数小于此时位置的行数，就说明终点在现在位置的北面，所以 `NumberOfDirectionChoices[0]` 就要减去行差（相当于加上行差的绝对值），表示往北走更可能走到终点。同时 `NumberOfDirectionChoices[2]` 也要加上行差（相当于减去行差的绝对值），表示向南走不太可能走到终点。

---

### 1.3 `findMostLikelyLocation` 函数

* 上述工作做完以后，接下来在 `move` 函数中，就要通过 `NumberOfDirectionChoices` 数组中的值来判断哪一个方向更为可能走到终点，然后往那个方向上去靠就好了。

* 具体的实现方式由 `findMostLikelyLocation` 函数实现，其实现的逻辑如下：

1. 先找到此时可以移动的下一位置，放入 `findMostLikelyLocation` 的参数中，记为 `locs`。
2. 如果 `locs` 中只有一个位置，那么没得选，只能走那个位置了。
3. 如果 `locs` 中有多个位置，比如现在可以往上走，也可以往右走，就先把 `NumberOfDirectionChoices[0]` 和 `NumberOfDirectionChoices[1]` 加起来，放在 `total` 中。
4. 之后获得一个 `[1, total]` 的随机数，根据随机数所处的区间挑选方向。

---

### 1.4 `getBack` 函数

* `getBack` 函数的作用是，当 `MazeBug` 走到思路的时候，能够通过 `getBack` 函数返回到上一个分叉处。
* 返回的方式就是通过树 `crossLocation` 实现的，每次走到思路，就从 `crossLocation` 中弹出最近的 `Node` ，其中包含了所有**不含岔路**的路径，然后跟着这个路径一步步走到岔路口即可。
* 注意每次 `getBack` 都需要修改 `NumberOfDirectionChoices` ，以提高后续的预测精度。

---

## 2 成果演示

* 由于这一部分由 `TA` 检查，故不再赘述。

---

## 3 SonarQube 检查

* 检查结果如下：

![MazeBugSonarQube](http://markdown-pictures-huzzh3.oss-accelerate.aliyuncs.com/img/seTraining/stage1/MazeBugSonarQube.png)

* 代码注释率如下：

![MazeBugComment](http://markdown-pictures-huzzh3.oss-accelerate.aliyuncs.com/img/seTraining/stage1/MazeBugComment.png)

* 而我们的代码的注释率为 17.7 %，超过了 10 % ，符合要求。

* 重复代码数量如下：

![MazeBugDuplication](http://markdown-pictures-huzzh3.oss-accelerate.aliyuncs.com/img/seTraining/stage1/MazeBugDuplication.png)

* 而我们代码的 `Duplicated Lines` 为 0 ，不到 10 行，符合要求。

* 同时我们代码的 `RCI (Rules Compliance Index)` ，可以计算出来 `RCI = 1 - 27/461 = 94.15% ` 超过了 60 %，符合要求。

---

