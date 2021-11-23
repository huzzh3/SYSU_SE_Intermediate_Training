# Inception: clarify the details of the problem:

## Question 1

### 问题

> What will a jumper do if the location in front of it is empty, but the location two cells in front contains a flower or a rock?

### 回答

* 当 `jumper` 的前面一格是空的，但前面两格有 `flower` 或 `rock` 的时候，`jumper` 可以选择遇到 `rock` 的时候转弯，遇到 `flower` 的时候则直接跳到该区域。

---

## Question 2

### 问题

> What will a jumper do if the location two cells in front of the jumper is out of the grid?

### 回答

* 当 `jumper` 前面两个的位置在地图外面的话，那么 `jumper` 会直接选择转弯。

---

## Question 3

### 问题

> What will a jumper do if it is facing an edge of the grid?

### 回答

* 当 `jumper` 正面对地图边缘的话，那么 `jumper` 会直接转弯。

---

## Question 4

### 问题

> What will a jumper do if another actor (not a flower or a rock) is in the cell that is two cells in front of the jumper?

### 回答

* 我的做法是，当 `jumper` 的前两个格子上有 `actor` 的话（不是 `flower` 也不是 `rock`），那么我会让 `jumper` 转弯。

---

## Question 5

### 问题

> What will a jumper do if it encounters another jumper in its path?

### 回答

* 我的做法是，当 `jumper` （称为 `jumper1`）的前两个格子上有 `jumper`（称为 `jumper2`） ，那么我会让 `jumper1` 转弯。

---

## Question 6

###  问题

> Are there any other tests the jumper needs to make?

### 回答

* 如果 `jumper` 正对着的格子上面有其他的 `actor`，根据要求，并没有指出此时 `jumper` 会采取什么行为，我将会设计 `jumper` 不能够跳过 `actor`，它只能跳过 `flower` 或者 `actor`。

