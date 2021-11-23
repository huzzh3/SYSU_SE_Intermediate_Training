# README 

## 0. Preface

* 本文档是关于**软件工程中级实训**第二阶段 Part4 的运行说明，将通过动图和图片来展示最终的运行结果。
* 本文档第一部分包含了六个需要完成的类的演示，第二部分包含了 `SonarQube` 的分析结果。

---

## 1. 六个类的结果展示

### 1.1 ModifiedChameleonCritter

#### 1.1.1思路

* 只需要在原来的代码的基础上加上 `darken()` 函数，该函数会获取到原颜色的 `RGB` ，然后乘上一个用户自定义的系数，即可实现颜色变暗，对应的源码如下面所示：

```java
    // You can get darken() from flower.class
	public void darken() {
		// Get self color
		Color color = getColor();
		
		// Get RGB and change
		int red   = (int) (color.getRed() * DARKEN_FACTOR);
		int green = (int) (color.getGreen() * DARKEN_FACTOR);
		int blue  = (int) (color.getBlue() * DARKEN_FACTOR);
		
		// Set the color
		setColor(new Color(red, green, blue));
	}
```

---

#### 1.1.2 运行结果

![modifiedChameleonCritterRun1](http://markdown-pictures-huzzh3.oss-accelerate.aliyuncs.com/img/seTraining/stage1/modifiedChameleonCritterRun1.gif)

* 注意看右边的 `ChameleonCritter` 在一段时间后会运行到左上角，然后会它会有一段时间无法接触到 `Rock` ，导致其不能更新颜色，因此它的颜色会逐渐变暗。

---

### 1.2 ChameleonKid

#### 1.2.1 思路

* 该问题就是让 `ChameleonKid` 只能通过前面和后面的物体改变颜色。
* 其实也很好实现，只需要把 `CrabCritter` 的部分函数复制过来使用即可，如下面源码所示：

```java
    public ArrayList<Actor> getActors()
    {
    	// You need to return actors
        ArrayList<Actor> actors = new ArrayList<Actor>();
        
        // Initialize the directions for getLocationsInDirections
        int[] dirs = { Location.AHEAD, Location.HALF_CIRCLE };
        
        // Get the actors
        for (Location loc : getLocationsInDirections(dirs))
        {
            Actor a = getGrid().get(loc);
            if (a != null)
                actors.add(a);
        }

        // Return
        return actors;
    }
```

* 然后再让 `ChameleonKid`  继承  `ModifiedChameleonCritter` 即可。

---

#### 1.2.2 运行结果

![chameleonKidRun1](http://markdown-pictures-huzzh3.oss-accelerate.aliyuncs.com/img/seTraining/stage1/chameleonKidRun1.gif)

* 我们的使用了两个 `ChameleonKid` 来颜色：
  * 第一个 `ChameleonKid` 被包围在 9 个不同颜色的石头中，可以看到它只会根据前面的蓝色石头和后面的红色石头变色。
  * 第二个 `ChameleonKid` 则是自由移动，可以看到它没东西给它变色的时候，它的颜色就会慢慢变暗。

---

### 1.3 RockHound

#### 1.3.1 思路

* `RockHound` 的作用是吃掉周围的 `Rock` ，其实也很简单，只需要重写一下 `processActors()`，从传入的参数 `actors` 中找到是 `Rock` 的实例，将其移除即可，如下面源码所示：

```java
	// RockHound would remove the rocks around
	public void processActors(ArrayList<Actor> actors) {
		
		// Process the actor in the actors
		for (Actor actor : actors) {
			if (actor instanceof Rock) {
				// Remove the rock
				actor.removeSelfFromGrid();
			}
		}
	}
```

---

#### 1.3.2 运行结果

![rockHoundRun1](http://markdown-pictures-huzzh3.oss-accelerate.aliyuncs.com/img/seTraining/stage1/rockHoundRun1.gif)

* 可以看到 `RockHound` 能正常的把 `Rock` 吃掉。

---

### 1.4 BlusterCritter

#### 1.4.1 思路

* 只需要重写一下 `getActors()` 函数，获取范围内 `5*5` 内的 `actors`，如下面源码所示：

```java
	// Get the actors two steps away
    public ArrayList<Actor> getActors()
    {
    	// You need to return actors
        ArrayList<Actor> actors = new ArrayList<Actor>();
        
        // Get the current location
        Location currentLocation = getLocation();
        
        // Get the actors
        for (int row = currentLocation.getRow() - 2; row <= currentLocation.getRow() + 2; row++) {
        	for (int col = currentLocation.getCol() - 2; col <= currentLocation.getCol() + 2; col++) {
        		Location tempLocation = new Location(row, col);
        		
        		// Check the validity of the location
        		if (getGrid().isValid(tempLocation)) {
        			Actor actor = getGrid().get(tempLocation);
        			
        			// Check if there's actor and it can't be self
        			if (actor != null && actor != this) {
        				actors.add(actor);
        			}
        		}
        	}
        }
        
        // Return
        return actors;
    }
```

* 然后再重写一下 `processActors`，遍历传来的 `actors` ，计算里面的 `critters` 的数量，然后根据数量来决定是 `lighten` 还是 `darken` 即可，如下面源码所示：

```java
	// ProcessActors() would process the actors around
    public void processActors(ArrayList<Actor> actors)
    {
    	// Count used to count the number of critters
    	int count = 0;
    	
    	// Get the number of critters
    	for (Actor actor: actors) {
    		if (actor instanceof Critter) {
    			count++;
    		}
    	}
    	
    	// Check the count to determine to lighten or darken
    	if (count < courageFactor) {
    		lighten();
    	}
    	else {
    		darken();
    	}
    }
```

#### 1.4.2 运行结果

![blusterCritterRun](http://markdown-pictures-huzzh3.oss-accelerate.aliyuncs.com/img/seTraining/stage1/blusterCritterRun.gif)

* 我设置的 `BlusterCritter` 有两个，初始化都是灰色的，而其他蓝色的 `Actor` 则是 `Critter`，左边的 `BlusterCritter` 的 `courageFactor` 为 4 ，而右边的 `courageFactor` 为 20，所以左边的 `BlusterCritter` 会逐渐变暗，右边的 `BlusterCritter` 会逐渐变量。

---

### 1.5 QuickCrab

#### 1.5.1 思路

* 这个部分也比较简单，主要就是重写 `getMoveLocations()` 函数即可，重写的办法就是先获取到左边一格空间，如果该空间合法且上面没有 `actor`，那么再获取下一个左边的空间，如果新的空间也合法且没有 `actor` 在上面，那么就加入要返回的数组中，右边也是同理。
* 如果要返回的数组里面没有 `Object` ，那么就返回父类中的 `getMoveLocations()` 即可，如下所示：

```java
    public ArrayList<Location> getMoveLocations()
    {
    	// Initialize the locations to be returned
        ArrayList<Location> locations = new ArrayList<Location>();
        
        // Get the grid
        Grid<Actor> grid = getGrid();
        
        // Get current location
        Location currentLocation = getLocation();
        
        // Get the left temp location
        Location tempLocation = currentLocation.getAdjacentLocation(getDirection() + Location.LEFT);
        
        // Check the location
        if (grid.isValid(tempLocation) && grid.get(tempLocation) == null) {
        	// Get the next temp location
        	Location tempLocationTwoStepsAway = tempLocation.getAdjacentLocation(getDirection() + Location.LEFT);
        	
        	// Check that location
        	if (grid.isValid(tempLocationTwoStepsAway) && grid.get(tempLocationTwoStepsAway) == null) {
        		locations.add(tempLocationTwoStepsAway);
        	}
        }
        
        // Get the right temp location
        tempLocation = currentLocation.getAdjacentLocation(getDirection() + Location.RIGHT);
        
        // Check the location
        if (grid.isValid(tempLocation) && grid.get(tempLocation) == null) {
        	// Get the next temp location
        	Location tempLocationTwoStepsAway = tempLocation.getAdjacentLocation(getDirection() + Location.RIGHT);
        	
        	// Check that location
        	if (grid.isValid(tempLocationTwoStepsAway) && grid.get(tempLocationTwoStepsAway) == null) {
        		locations.add(tempLocationTwoStepsAway);
        	}
        }
        
        // Check the locations
        if (locations.isEmpty()) {
        	return super.getMoveLocations();
        }
        else {
        	return locations;
        }
```

#### 1.5.2 运行结果

![quickCrabRun](http://markdown-pictures-huzzh3.oss-accelerate.aliyuncs.com/img/seTraining/stage1/quickCrabRun.gif)

* 蓝色的就是我们刚才写好的 `QuickCrab`，而红色的则是普通的 `CrabCritter` ，可以看到 `QuickCrab` 一次性可以移动两格，而 `CrabCritter` 一次性只能移动一格。

---

### 1.6 KingCrab

#### 1.6.1 思路

* `KingCrab` 的功能是让它左前、右前、正前的 `Actor` 远离它移动一格，**移动完毕之后，它自己也需要移动** 。
* 实现起来也比较简单，重写一下 `processActors ` 即可，如果不能 `Actor` 不能移动，那么就把它从地图上移除。

```java
	// You need to override the processActors function
	public void processActors(ArrayList<Actor> actors) {
		for (Actor actor : actors) {
			if (!moveAway(actor)) {
				actor.removeSelfFromGrid();
			}
		}
	}
```

* 为了让 `Actor` 远离 `KingCrab` 移动，我们还需要计算目标位置和 `KingCrab` 的位置之间的距离是否大于 1，计算的函数如下：

```java
	public double distance(Location currentLocation, Location location) {
		// Get the attribute
		int currentRow = currentLocation.getRow();
		int currentCol = currentLocation.getCol();
		int targetRow  = location.getRow();
		int targetCol  = location.getCol();
		
		// Calculate the distance
		
		double distance = Math.sqrt((currentRow - targetRow) * (currentRow - targetRow) + (currentCol - targetCol) * (currentCol - targetCol));
		
		return distance;
	}
```

* 然后把计算的函数放在 `moveAway` 进行调用即可。

#### 1.6.2 运行结果

![kingCrabRun](http://markdown-pictures-huzzh3.oss-accelerate.aliyuncs.com/img/seTraining/stage1/kingCrabRun.gif)

* 我们一共有两只 `KingCrab` ，颜色为粉色：
  * 右边的 `KingCrab` 则演示它是如何让 `Actor` 移动的。
  * 一开始，可以看到左上角的 `KingCrab` 对应的 `Actor` 中的 `Crab` 和 `Bug` 都没有地方移动了，所以就被移除了。

---

## 2. SonarQube 分析

![part4SonarQube](http://markdown-pictures-huzzh3.oss-accelerate.aliyuncs.com/img/seTraining/stage1/part4SonarQube.png)

* `SonarQube` 测试通过了，那 2 个 `Security Hotspot` 是 `gridworld` 给的原代码的类中的 `random()` 函数导致的，而我们自己写的是没有问题的。

![part4CommentLine](http://markdown-pictures-huzzh3.oss-accelerate.aliyuncs.com/img/seTraining/stage1/part4CommentLine.png)

* 而我们的代码的注释率为 18.6 %，超过了 10 % ，符合要求。

![part4Duplication](http://markdown-pictures-huzzh3.oss-accelerate.aliyuncs.com/img/seTraining/stage1/part4Duplication.png)

* 而我们代码的 `Duplicated Lines` 为 0 ，不到 10 行，符合要求。
* 同时我们代码的 `RCI (Rules Compliance Index)` ，可以计算出来 `RCI = 1 - 40/461 = 91.31% ` 超过了 60 %，符合要求。

---

