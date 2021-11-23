# README

## 0. Preface

* 本文档主要包含以下内容：
  * 1 成果展示
  * 2 测试样例通过情况
  * 3 sonarqube 测评情况

---

## 1. 成果展示

### 1.1 跳过 flower 或 rock

![part3JumpOver](http://markdown-pictures-huzzh3.oss-accelerate.aliyuncs.com/img/seTraining/stage1/part3JumpOver.gif)

---

### 1.2 目标位上有花朵则会占据

![jumperOccupyFlower](http://markdown-pictures-huzzh3.oss-accelerate.aliyuncs.com/img/seTraining/stage1/jumperOccupyFlower.gif)

---

### 1.3目标位置在地图外边则会转弯 

![part3TurnEdge](http://markdown-pictures-huzzh3.oss-accelerate.aliyuncs.com/img/seTraining/stage1/part3TurnEdge.gif)

---

### 1.4 jumper 正对地图边缘则会转弯

![part3TurnEdge2](http://markdown-pictures-huzzh3.oss-accelerate.aliyuncs.com/img/seTraining/stage1/part3TurnEdge2.gif)

---

### 1.5 目标位置上有 actor 则会转弯

![part3Bug1](http://markdown-pictures-huzzh3.oss-accelerate.aliyuncs.com/img/seTraining/stage1/part3Bug1.gif)

---

### 1.6 目标位置上有 jumper 则会转弯

![part3Jumper1](http://markdown-pictures-huzzh3.oss-accelerate.aliyuncs.com/img/seTraining/stage1/part3Jumper1.gif)

---

### 1.7 当前一格上有 actor 则不能跳过

![part3CantJump](http://markdown-pictures-huzzh3.oss-accelerate.aliyuncs.com/img/seTraining/stage1/part3CantJump.gif)

---

## 2. 测试样例全部通过

![part3Junit](http://markdown-pictures-huzzh3.oss-accelerate.aliyuncs.com/img/seTraining/stage1/part3Junit.png)

* 我一共在 `JumperTest` 中写了 9 个测试样例，其中包括：	

  1. 能够正常跳过花朵
  2. 能够正常跳过石头
  3. 石头在目标位置，jumper则会转弯
  4. 花朵在目标位置，jumper则会占据花朵
  5. 目标位置在地图外边，则会转弯
  6. jumper 正对着地图边缘，则会转弯
  7. 目标位置上有 actor，则会转弯
  8. 目标位置上有 jumper，则会转弯
  9. 正对着 actor（不是 flower 或 rock），则无法跳过，只能转弯
* 测试样例的详情请参考 `JumperTest` 文件。

## 3. Sonarqube 分析情况

* 分析结果如下图所示：

![part3SonarQube](http://markdown-pictures-huzzh3.oss-accelerate.aliyuncs.com/img/seTraining/stage1/part3SonarQube.png)

* 代码的注释率超过了 10%，符合要求，如下图所示：

![par3SonarQubeComments](http://markdown-pictures-huzzh3.oss-accelerate.aliyuncs.com/img/seTraining/stage1/par3SonarQubeComments.png)

* 代码的重复行数为 0 ，不到 10 行，符合要求：

![part3SonarQubeDuplications](http://markdown-pictures-huzzh3.oss-accelerate.aliyuncs.com/img/seTraining/stage1/part3SonarQubeDuplications.png)

* 代码的 RCI (Rules Compliance Index) ，可以计算出来，`RCI = 1 - 7 / 461 = 98.48% ` ，超过了 60 %，符合要求。

