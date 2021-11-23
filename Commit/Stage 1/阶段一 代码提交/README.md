# 0. 序言

* ！！！注意由于压缩包过大！故我删除了所有项目中的 lib 文件夹中的 jar 文件，如果要复现代码，请修改 build.xml 文件下的 copy target 的文件路径！
* 该 README.md 文档将从多个方面展示我的成果是否符合 “阶段一 代码提交” 的各个要求
* 碍于篇幅限制，本报告将不会详细包含 ANT、JUnit、SonarQube 的原理与用法，而是展示如何我们的程序使用了 ANT、JUnit、SonarQube 之后的输出结果
* 详细的 ANT、JUnit、SonarQube 的原理与用法将会包含在 “自学报告(studyreport.md)” 中
* 除此之外，本报告还会包含 calculator 的运行结果
* 本 README 文档的结构如下：

  1. 源码展示

  2. “阶段一 代码提交” 的评分细节及其结果展示


# 1. 源码

## 1.1 HelloWorld 项目

### 1.1.1 HelloWorld 的源码

```java
package helloWorld;

public class HelloWorld {

	public boolean isGreater(int num1, int num2) {
		return num1 > num2;
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("hello, world!");
	}

}
```

* 由于后面要用 JUnit 测试，我在这里加了一个函数 isGreater ，用于后面的 JUnit 测试

### 1.1.2 build.xml 源码 （用于 ANT）

```xml
<project name="HelloWorld" basedir="." default="main">
	
	<property name="src.dir" value="src"/>
	
	<property name="build.dir" value="build"/>
	<property name="classes.dir" value="${build.dir}/classes"/>
	<property name="jar.dir" value="${build.dir}/jar"/>
	
	<property name="lib.dir" value="lib"/>
	
	<property name="reports.dir" value="reports"/>
	
	<property name="main-class" value="helloWorld.HelloWorld"/>
	
	<target name="clean">
		<delete dir="${build.dir}"/>
		<delete dir="${lib.dir}"/>
		<delete dir="${reports.dir}"></delete>
	</target>
	
	<target name="create">
		<mkdir dir="${build.dir}"/>
		<mkdir dir="${lib.dir}"/>
		<mkdir dir="${reports.dir}"/>
	</target>
	
	<target name="copy" depends="create">
		<copy overwrite="true" todir="lib">
			<fileset dir="/snap/eclipse/48/plugins/org.junit_4.12.0.v201504281640" includes="junit.jar"></fileset>
		</copy>
		<copy overwrite="true" todir="lib">
			<fileset dir="/snap/eclipse/48/plugins" includes="*.jar"></fileset>
		</copy>
	</target>
	
	<path id="classpath">
		<path>
			<fileset dir="${lib.dir}" includes="*.jar"></fileset>
		</path>
		<pathelement location="${src.dir}"></pathelement>
		<pathelement location="${classes.dir}"></pathelement>
	</path>
	
	
	<target name="compile" depends="copy">
		<mkdir dir="${classes.dir}"/>
		
		<javac classpathref="classpath" includeantruntime="true" srcdir="${src.dir}" destdir="${classes.dir}"/>
	</target>
	
	<target name="jar" depends="compile">
		<mkdir dir="${jar.dir}"/>
		<jar destfile="${jar.dir}/${ant.project.name}.jar" basedir="${classes.dir}">
			<manifest>
				<attribute name="Main-Class" value="${main-class}"/>
			</manifest>
		</jar>
	</target>
	
	<target name="run" depends="jar">
		<java jar="${jar.dir}/${ant.project.name}.jar" fork="true"/>
		
		<junit>
			<classpath refid="classpath"></classpath>
			<formatter type="brief" usefile="false"/>
			<test name="helloWorld.HelloWorldTest"></test>
		</junit>
	</target>
	
	<target name="clean-build" depends="clean,jar"/>
	
	<target name="main" depends="clean,run"/>
	
</project>
```

* 该 build.xml 文件函数的运行依赖是：
  * main
    * clean
    * run
      * jar
        * compile

### 1.1.3 HelloWorldTest 源码（用于 JUnit）

```java
package helloWorld;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class HelloWorldTest {

	@Test
	void testIsGreater() {
		System.out.println("Test");
		HelloWorld helloworld = new HelloWorld();
		assertTrue("Num 1 is greater than Num 2", helloworld.isGreater(4, 3));
	}

}
```

* 我们这里只针对 isGreater 函数进行测试，测试当我们输入 “num1 == 4” 和 “num2 == 3” 的时候，其是否能够正常返回 “true”

## 1.2 Calculator 项目

### 1.2.1 Calculator 源码

```java
package calculator;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class Calculator {
	// Global variable
	final static int PLUS = 1;
	final static int MINUS = 2;
	final static int MUL = 3;
	final static int DIV = 4;
	static int operator = 1;
	static double res;
	
	public static double calculate(double num1, double num2) {
		if (operator == PLUS) {
			res = num1 + num2;
		}
		else if (operator == MINUS) {
			res = num1 - num2;
		}
		else if (operator == MUL) {
			res = num1 * num2;
		}
		else if (operator == DIV) {
			res = num1 / num2;
		}
		
		return res;
	}

	public static void main(String[] args) {

		
		// Set the num1TextField
		JTextField num1TextField = new JTextField(10);
		
		// Set the operatorLaebl
		JLabel operatorLabel = new JLabel("+");
		operatorLabel.setHorizontalAlignment(SwingConstants.CENTER);
		
		// Set the num2TextField
		JTextField num2TextField = new JTextField(10);
		
		// Set the assignLaebl
		JLabel assignLabel = new JLabel("=");
		assignLabel.setHorizontalAlignment(SwingConstants.CENTER);
		
		// Set the resultLabel
		JLabel resultLabel = new JLabel("res");
		resultLabel.setHorizontalAlignment(SwingConstants.CENTER);
		
		// Set the 4 operatorButtons
		JButton plusButton  = new JButton("+");
		JButton minusButton = new JButton("-");
		JButton mulButton   = new JButton("*");
		JButton divButton   = new JButton("/");
		
		// Set the Button Listener
		plusButton.addActionListener((ActionListener) new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				operatorLabel.setText("+");
				operator = PLUS;
			}
		});
		minusButton.addActionListener((ActionListener) new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				operatorLabel.setText("-");
				operator = MINUS;
			}
		});
		mulButton.addActionListener((ActionListener) new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				operatorLabel.setText("*");
				operator = MUL;
			}
		});
		divButton.addActionListener((ActionListener) new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				operatorLabel.setText("/");
				operator = DIV;
			}
		});
		
		// Set OK Button
		JButton OKButton    = new JButton("OK");
		
		OKButton.addActionListener((ActionListener) new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				
				double num1 = Double.valueOf(num1TextField.getText());
				double num2 = Double.valueOf(num2TextField.getText());
				
				res = calculate(num1, num2);
				
				resultLabel.setText(Double.toString(res));
			}
		});
		
		// Set the window
		JFrame calculatorWindow = new JFrame("Calculator");
		calculatorWindow.setBounds(100, 100, 400, 200);
		calculatorWindow.setVisible(true);
		calculatorWindow.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
	
		// Set the panel
		JPanel calculatorPanel = new JPanel();
		calculatorPanel.setLayout(new GridLayout(2, 5, 5, 5));
		
		calculatorPanel.add(num1TextField);
		calculatorPanel.add(operatorLabel);
		calculatorPanel.add(num2TextField);
		calculatorPanel.add(assignLabel);
		calculatorPanel.add(resultLabel);
		calculatorPanel.add(plusButton);
		calculatorPanel.add(minusButton);
		calculatorPanel.add(mulButton);
		calculatorPanel.add(divButton);
		calculatorPanel.add(OKButton);
		
		// Make up
		calculatorWindow.add(calculatorPanel);
	}

}
```

### 1.2.2 build.xml 源码 （用于 ANT）

```xml
<?xml version="1.0" encoding="UTF-8"?>
<project name="Calculator" basedir="." default="main">
	
	<property name="src.dir" value="src"/>
	
	<property name="build.dir" value="build"/>
	<property name="classes.dir" value="${build.dir}/classes"/>
	<property name="jar.dir" value="${build.dir}/jar"/>
	
	<property name="lib.dir" value="lib"/>
	
	<property name="reports.dir" value="reports"/>
	
	<property name="main-class" value="calculator.Calculator"/>
	
	<target name="clean">
		<delete dir="${build.dir}"/>
		<delete dir="${lib.dir}"/>
		<delete dir="${reports.dir}"></delete>
	</target>
	
	<target name="create">
		<mkdir dir="${build.dir}"/>
		<mkdir dir="${lib.dir}"/>
		<mkdir dir="${reports.dir}"/>
	</target>
	
	<target name="copy" depends="create">
		<copy overwrite="true" todir="lib">
			<fileset dir="/snap/eclipse/48/plugins/org.junit_4.12.0.v201504281640" includes="junit.jar"></fileset>
		</copy>
		<copy overwrite="true" todir="lib">
			<fileset dir="/snap/eclipse/48/plugins" includes="*.jar"></fileset>
		</copy>
	</target>
	
	<path id="classpath">
		<path>
			<fileset dir="${lib.dir}" includes="*.jar"></fileset>
		</path>
		<pathelement location="${src.dir}"></pathelement>
		<pathelement location="${classes.dir}"></pathelement>
	</path>
	
	
	<target name="compile" depends="copy">
		<mkdir dir="${classes.dir}"/>
		
		<javac classpathref="classpath" includeantruntime="true" srcdir="${src.dir}" destdir="${classes.dir}"/>
	</target>
	
	<target name="jar" depends="compile">
		<mkdir dir="${jar.dir}"/>
		<jar destfile="${jar.dir}/${ant.project.name}.jar" basedir="${classes.dir}">
			<manifest>
				<attribute name="Main-Class" value="${main-class}"/>
			</manifest>
		</jar>
	</target>
	
	<target name="run" depends="jar">
		<junit>
			<classpath refid="classpath"></classpath>
			<formatter type="brief" usefile="false"/>
			<test name="calculator.CalculatorTest"></test>
		</junit>

		<java jar="${jar.dir}/${ant.project.name}.jar" fork="true"/>
	</target>
	
	<target name="clean-build" depends="clean,jar"/>
	
	<target name="main" depends="clean,run"/>
	
</project>
```

### 1.2.3 CalculatorTest 源码 （用于 JUnit）

```java
package calculator;

import static org.junit.Assert.*;
import org.junit.Test;

public class CalculatorTest {

	@Test
	public void testMain() {
		Calculator calculator = new Calculator();
		
		calculator.operator = Calculator.PLUS;
		assertEquals(100, (int)calculator.calculate(40, 60));
		System.out.println("plus function is OK");
		
		calculator.operator = Calculator.MINUS;
		assertEquals(70, (int)calculator.calculate(100, 30));
		System.out.println("minus function is OK");
		
		calculator.operator = Calculator.MUL;
		assertEquals(500, (int)calculator.calculate(25, 20));
		System.out.println("multiple function is OK");
		
		calculator.operator = Calculator.DIV;
		assertEquals(10, (int)calculator.calculate(300, 30));
		System.out.println("divide function is OK");
		
	}

}
```

# 2. 评分要求及其结果展示

## 2.1 是否使用了 ANT 

### 2.1.1 HelloWorld 编译结果

* ANT 的运行结果如下：

![antForHelloWorld](http://markdown-pictures-huzzh3.oss-accelerate.aliyuncs.com/img/seTraining/stage1/antForHelloWorld.png)

* 可以看到我们的 build.xml 编写的是正确的，其完成了文件的编译，jar 打包，运行
* 运行结果也是对的，输出了 “hello, world!”

### 2.2.2 Calculator 编译结果

![antForCalculator](http://markdown-pictures-huzzh3.oss-accelerate.aliyuncs.com/img/seTraining/stage1/antForCalculator.png)

* 可以看到我们的编译结果正常，运行结果请看下文的 “2.4 是否完成了 Java 小程序”

---

## 2.2 是否使用了 JUnit

### 2.2.1 HelloWorld 测试结果：

![jUnitForHelloWorld](http://markdown-pictures-huzzh3.oss-accelerate.aliyuncs.com/img/seTraining/stage1/jUnitForHelloWorld.png)

* 我在 ant 的代码中加入了 jUnit 的指令，这样就可以直接用 ant 来调用 jUnit
* 可以看到，我们的 jUnit 的运行结果没有显示错误，说明我们的测试结果是对的

### 2.2.2 Calculator 测试结果：

![calculatorTest](http://markdown-pictures-huzzh3.oss-accelerate.aliyuncs.com/img/seTraining/stage1/calculatorTest.png)

* 在上图中我们看到，我们测试了计算器的四则运算，结果均正确

---

## 2.3 环境是否配置正确

![enviroment](http://markdown-pictures-huzzh3.oss-accelerate.aliyuncs.com/img/seTraining/stage1/enviroment.png)

* 可以看到，这里我们的 JAVA_HOME 和 ANT 的环境配置正确

---

## 2.4 是否完成了 Java 小程序

* Java 小程序运行画面如下：

![calculator](http://markdown-pictures-huzzh3.oss-accelerate.aliyuncs.com/img/seTraining/stage1/calculator.png)

* 我们测试一下计算结果是否正确，先输入 40 + 60：

![calculatorPlus](http://markdown-pictures-huzzh3.oss-accelerate.aliyuncs.com/img/seTraining/stage1/calculatorPlus.png)

* 再尝试一下 100 - 30：

![calculatorMinus](http://markdown-pictures-huzzh3.oss-accelerate.aliyuncs.com/img/seTraining/stage1/calculatorMinus.png)

* 再尝试一下 25 * 20：

![calculatorMul](http://markdown-pictures-huzzh3.oss-accelerate.aliyuncs.com/img/seTraining/stage1/calculatorMul.png)

* 最后再尝试一下 300 / 30 ：

![calculatorDiv](http://markdown-pictures-huzzh3.oss-accelerate.aliyuncs.com/img/seTraining/stage1/calculatorDiv.png)

---

## 2.5 成功配置SonarQube，并成功分析Java小程序

### 2.5.1 pro.xml 源码

```xml
<project xmlns="http://maven.apache.org/POM/4.0.0" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 https://maven.apache.org/xsd/maven-4.0.0.xsd">
  <properties>
     <maven.compiler.source>1.8</maven.compiler.source>
     <maven.compiler.target>1.8</maven.compiler.target>
  </properties>
  
 
  <modelVersion>4.0.0</modelVersion>
  <groupId>huzzh3</groupId>
  <artifactId>calculator</artifactId>
  <version>0.0.1-SNAPSHOT</version>
  
  <dependencies>
	 <dependency>
	    <groupId>junit</groupId>
	    <artifactId>junit</artifactId>
	    <version>4.12</version>
	    <scope>test</scope>
	 </dependency>
	 
	 <dependency>
	    <groupId>org.sonarsource.scanner.maven</groupId>
	    <artifactId>sonar-maven-plugin</artifactId>
	    <version>3.9.0.2155</version>
	 </dependency>
  </dependencies>
  
</project>
```

### 2.5.2 运行结果

![sonarqubeCalculator](http://markdown-pictures-huzzh3.oss-accelerate.aliyuncs.com/img/seTraining/stage1/sonarqubeCalculator.png)

* 可以看到我们的代码运行没有问题

---

## 2.6 成功运行 BugRunner

* 成功运行了 BugRunner ，只需要在 eclipse 中加入对应的 jar 文件，然后点击运行即可，如下图所示：

![bugRunnerRun](http://markdown-pictures-huzzh3.oss-accelerate.aliyuncs.com/img/seTraining/stage1/bugRunnerRun.png)

