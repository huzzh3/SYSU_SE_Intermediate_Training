package imageProcessing;

import static org.junit.Assert.*;

import java.awt.Image;
import java.io.IOException;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import org.junit.Test;
import imagereader.IImageIO;
import imagereader.IImageProcessor;

public class ImageProcessorTest {
	private String testImagepath1 = "./src/img/1.bmp";
	private String testImagepath2 = "./src/img/2.bmp";
	private String goalDirectory  = "./src/goal/";

	@Test
	public void testShowChanelR() throws IOException {
		IImageIO imageioer                           = new ImplementImageIO();
		IImageProcessor processor                    = new ImplementImageProcessor();
		Image    sourceImage                         = null;
		Image    redImageGoal                        = null;
		Image    redImageProcessed                   = null;
		
		// Read the image
		sourceImage  = imageioer.myRead(testImagepath1);
		redImageGoal = imageioer.myRead(goalDirectory + "1_red_goal.bmp");
		
		// Get red rgb image
		redImageProcessed = processor.showChanelR(sourceImage);
		
		// Test if equal
		testIfEqual(redImageGoal, redImageProcessed);
		
		// Read another image
		sourceImage  = imageioer.myRead(testImagepath2);
		redImageGoal = imageioer.myRead(goalDirectory + "2_red_goal.bmp");
		
		// Get red rgb image
		redImageProcessed = processor.showChanelR(sourceImage);
		
		// Test if equal
		testIfEqual(redImageGoal, redImageProcessed);
		
	}

	@Test
	public void testShowChanelG() throws IOException {
		IImageIO imageioer                           = new ImplementImageIO();
		IImageProcessor processor                    = new ImplementImageProcessor();
		Image    sourceImage                         = null;
		Image    greenImageGoal                      = null;
		Image    greenImageProcessed                 = null;
		
		// Read the image
		sourceImage    = imageioer.myRead(testImagepath1);
		greenImageGoal = imageioer.myRead(goalDirectory + "1_green_goal.bmp");
		
		// Get red rgb image
		greenImageProcessed = processor.showChanelG(sourceImage);
		
		// Test if equal
		testIfEqual(greenImageGoal, greenImageProcessed);
		
		// Read another image
		sourceImage    = imageioer.myRead(testImagepath2);
		greenImageGoal = imageioer.myRead(goalDirectory + "2_green_goal.bmp");
		
		// Get red rgb image
		greenImageProcessed = processor.showChanelG(sourceImage);
		
		// Test if equal
		testIfEqual(greenImageGoal, greenImageProcessed);
	}

	@Test
	public void testShowChanelB() throws IOException {
		IImageIO imageioer                           = new ImplementImageIO();
		IImageProcessor processor                    = new ImplementImageProcessor();
		Image    sourceImage                         = null;
		Image    blueImageGoal                       = null;
		Image    blueImageProcessed                  = null;
		
		// Read the image
		sourceImage   = imageioer.myRead(testImagepath1);
		blueImageGoal = imageioer.myRead(goalDirectory + "1_blue_goal.bmp");
		
		// Get red rgb image
		blueImageProcessed = processor.showChanelB(sourceImage);
		
		// Test if equal
		testIfEqual(blueImageGoal, blueImageProcessed);
		
		// Read another image
		sourceImage   = imageioer.myRead(testImagepath2);
		blueImageGoal = imageioer.myRead(goalDirectory + "2_blue_goal.bmp");
		
		// Get red rgb image
		blueImageProcessed = processor.showChanelB(sourceImage);
		
		// Test if equal
		testIfEqual(blueImageGoal, blueImageProcessed);
	}

	@Test
	public void testShowGray() throws IOException {
		IImageIO imageioer                           = new ImplementImageIO();
		IImageProcessor processor                    = new ImplementImageProcessor();
		Image    sourceImage                         = null;
		Image    grayImageGoal                       = null;
		Image    grayImageProcessed                  = null;
		
		// Read the image
		sourceImage   = imageioer.myRead(testImagepath1);
		grayImageGoal = imageioer.myRead(goalDirectory + "1_gray_goal.bmp");
		
		// Get red rgb image
		grayImageProcessed = processor.showGray(sourceImage);
		
		// Test if equal
		testIfEqual(grayImageGoal, grayImageProcessed);
		
		// Read another image
		sourceImage   = imageioer.myRead(testImagepath2);
		grayImageGoal = imageioer.myRead(goalDirectory + "2_gray_goal.bmp");
		
		// Get red rgb image
		grayImageProcessed = processor.showGray(sourceImage);
		
		// Test if equal
		testIfEqual(grayImageGoal, grayImageProcessed);
	}
	
	public void testIfEqual(Image img1, Image img2) {
		BufferedImage buffeImg1      = null;
		BufferedImage buffeImg2      = null;
		Graphics2D    bGr4buffeImg1  = null;
		Graphics2D    bGr4buffeImg2  = null;
		
		// Convert the Image to bufferedImage
		buffeImg1      = new BufferedImage(img1.getWidth(null), img1.getHeight(null), BufferedImage.TYPE_INT_RGB);
		buffeImg2      = new BufferedImage(img2.getWidth(null), img2.getHeight(null), BufferedImage.TYPE_INT_RGB);
		bGr4buffeImg1  = buffeImg1.createGraphics();
		bGr4buffeImg2  = buffeImg2.createGraphics();
		bGr4buffeImg1.drawImage(img1, 0, 0, null);
		bGr4buffeImg2.drawImage(img2, 0, 0, null);
		bGr4buffeImg1.dispose();
		bGr4buffeImg2.dispose();
		
		// Test if equal
		assertEquals(img1.getHeight(null), img2.getHeight(null));
		assertEquals(img1.getWidth(null), img2.getWidth(null));
		for (int y = 0; y < img1.getHeight(null); y++) {
			for (int x = 0; x < img1.getWidth(null); x++) {
				int pixel1 = buffeImg1.getRGB(x, y);
				int pixel2 = buffeImg2.getRGB(x, y);
				
				assertEquals(pixel1, pixel2);
			}
		}
	}

}


