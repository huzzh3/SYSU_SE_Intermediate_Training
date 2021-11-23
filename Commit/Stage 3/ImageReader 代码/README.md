# ImageReader

## 0 作者信息

* 学号：胡泽钊

* 姓名：18327034

* 年级：2019级
* 专业：软件工程

---

## 1 思路分析

### 1.1 读取 `BMP` 文件

* 根据 `BMP` 文件结构的组成，我们可以把读取 `BMP` 的过程分为以下几步：

1. 以字节为单位读取 `BMP` 文件头和数据头，该部分大小为 54 字节，将读取到的信息存放在 `fileAndInfoHeader` 数组内。
2. `fileAndInfoHeader` 数组的 34~37 字节是 `BMP` 文件的大小（以字节为单位），将其保存在变量 `size` 中；第 22~25 位是 `BMP` 文件的高度（以像素为单位），将其保存在 `height` 中；第 18~21 位是 `BMP` 文件的宽度（以像素为单位），将其保存在 `width` 中。
3. 获取空字符的数量，为了确保每行的字节数一定要是 4 的倍数，`BMP` 会填上若干个 0 ，要计算这些 0 所占的字节数，可以通过 `size / height` 获得每行的字节数，同时 `3 * width` 是每行像素的 `RGB` 信息所占的字节数（注意 `RGB` 每个通道占一个字节），故 `size / height -  3 * width` 即空字符的字节数，保存在 `nullCharacterNum` 变量中。
4. 根据 `size` 的大小，创建一个以 `byte` 为单位存储信息的数组，记为 `imageData` ，用于记录图片的信息。将 `BMP` 文件的剩余部分读入 `imageData` 数组中保存起来。
5. 创建一个大小为 `height * width` 的数组 `pixel` ，用于存放每个像素的 `RGB` 信息。用**从下往上，从左往右**形式扫描 `imageData` 数组，扫描到的 `RGB` 信息通过位处理得到最终的 `RGB` 值，存放在 `pixel` 数组中。
6. 最终结合 `createImage` 函数和 `pixel` 数组，创建一个图像 `image` ，并返回 `image` ，即可实现 `BMP` 的图像读取。

---

### 1.2 写出 `BMP` 文件

* `BMP` 文件的写出就比较简单了，因为这部分允许使用 `Java` 自带的 `api`，故我们使用 `BufferedImage` 和 `Graphics2D` 即可实现 `BMP` 文件的写出，这里不再赘述。

---

### 1.3 提取 RGB 色彩通道

* 这一部分比较简单，可以直接使用 `Java` 自带的 `RGBImageFilter` 接口，将其中的 `filterRGB` 方法重载，即可实现色彩通道的提取，如红色通道的色彩信息是 `RGB` 信息的第 3~4 个字节（1~2 是 `alpha` 信息，即不透明度），故提取红色通道的代码如下：

```java
	public int filterRGB(int x, int y, int rgb) {
		
		if (RGBchoose == "RED") {
			return (rgb & 0xffff0000);
		}
		......
	}
```

* 其他几个通道与此类似。

---

### 1.4 获取灰度图像

* 只需要通过上述方法获取到三个通道的颜色信息，再通过 `NTSC` 的转换公式，即可实现获取灰度图像，具体代码如下：

```java
@Override
public int filterRGB(int x, int y, int rgb) {

    ......
        else if (RGBchoose == "GRAY") {
            int grayRGB = (int)(
                ((rgb & 0x00ff0000) >> 16) * 0.299 +
                ((rgb & 0x0000ff00) >> 8 ) * 0.587 +
                ((rgb & 0x000000ff) >> 0 ) * 0.114
            );

            return (
                (rgb & 0xff000000) + (grayRGB << 16) + (grayRGB << 8) + (grayRGB << 0)
            );
        }


    return 0;
}
```

---

## 2 代码运行结果 & Junit 测试

* 由于这一部分由 `TA检查` 完成，故这里不再赘述。

---

## 3 sonar 代码分析

* 分析结果如下：

![imageProcessSonar](http://markdown-pictures-huzzh3.oss-accelerate.aliyuncs.com/img/seTraining/stage1/imageProcessSonar.png)

* 代码注释率如下：

![imageProcessComment](http://markdown-pictures-huzzh3.oss-accelerate.aliyuncs.com/img/seTraining/stage1/imageProcessComment.png)

* 而我们的代码的注释率为 11.9 %，超过了 10 % ，符合要求。

![imageProcessDuplication](http://markdown-pictures-huzzh3.oss-accelerate.aliyuncs.com/img/seTraining/stage1/imageProcessDuplication.png)

* 而我们代码的 `Duplicated Lines` 为 0 ，不到 10 行，符合要求。

* 同时我们代码的 `RCI (Rules Compliance Index)` ，可以计算出来 `RCI = 1 - 14/461 = 97.96% ` 超过了 60 %，符合要求。

---

