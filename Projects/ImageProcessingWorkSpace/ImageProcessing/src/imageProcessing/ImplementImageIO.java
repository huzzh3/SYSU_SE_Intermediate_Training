package imageProcessing;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.awt.image.ImageProducer;
import java.awt.image.MemoryImageSource;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.imageio.stream.FileImageInputStream;

import imagereader.IImageIO;

public class ImplementImageIO implements IImageIO {
	
	private static final int BMP_LENGTH_BYTE = 54;	// There're 54 bytes in bmp file header and information header
	private static final int[] bitsNeedTobeTransfer = {24, 16, 8, 0};

	@Override
	public Image myRead(String filepath) throws IOException {
		/* 
		 * You need to read Bitmap from the filepath
		 */
		
		// Necessary variables
		File  file                 = null;
		Image image                = null;
		byte  fileAndInfoHeader[]  = new byte[BMP_LENGTH_BYTE]; // Used to store binary file header and information header
		byte  imageData[]          = null;						// Used to store image data (the pixel information of the location)
		int   pixel[]			   = null;						// One-dimension array to store pixel information (after trimming)
		int   size 				   = 0;
		int   width                = 0;
		int   height               = 0; 
		int   cur				   = 0;							// Used to scan the image data
		int   nullCharacterNum     = 0;							// The number of null-character in every row
		
		// Read the file, and store it to the fileAndInfoHeader
		file = new File(filepath);
		FileImageInputStream inputStream = new FileImageInputStream(file);
		inputStream.read(fileAndInfoHeader, 0, BMP_LENGTH_BYTE);
		
		// According to the BMP structure, we can get its size, width and height
		size = (int)(
				      (fileAndInfoHeader[37] & 0xff) << bitsNeedTobeTransfer[0] |
				      (fileAndInfoHeader[36] & 0xff) << bitsNeedTobeTransfer[1] |
				      (fileAndInfoHeader[35] & 0xff) << bitsNeedTobeTransfer[2] |
				   	  (fileAndInfoHeader[34] & 0xff) << bitsNeedTobeTransfer[3] 
                    );
		height = (int)(
					      (fileAndInfoHeader[25] & 0xff) << bitsNeedTobeTransfer[0] |
					      (fileAndInfoHeader[24] & 0xff) << bitsNeedTobeTransfer[1] |
					      (fileAndInfoHeader[23] & 0xff) << bitsNeedTobeTransfer[2] |
					   	  (fileAndInfoHeader[22] & 0xff) << bitsNeedTobeTransfer[3] 
					  );
		width = (int)(
					      (fileAndInfoHeader[21] & 0xff) << bitsNeedTobeTransfer[0] |
					      (fileAndInfoHeader[20] & 0xff) << bitsNeedTobeTransfer[1] |
					      (fileAndInfoHeader[19] & 0xff) << bitsNeedTobeTransfer[2] |
					   	  (fileAndInfoHeader[18] & 0xff) << bitsNeedTobeTransfer[3] 
					 );
		// You need to calculate how much null-characters to be inserted
		nullCharacterNum = size / height - 3 * width;						// size (bytes)
		
		// Initialize the imageData to get the data of image
		imageData = new byte[size];
		inputStream.read(imageData, 0, size);
		
		// Trim the image data and store them in the pixel array
		pixel = new int[height * width];
		// Scan
		for (int i = height - 1; i >= 0; i--) {
			for (int j = 0; j < width; j++) {
				// Get RGB
				pixel[i * width + j] = (int)(
												(0xff)                      << bitsNeedTobeTransfer[0] |
												(imageData[cur + 2] & 0xff) << bitsNeedTobeTransfer[1] |
												(imageData[cur + 1] & 0xff) << bitsNeedTobeTransfer[2] |
												(imageData[cur + 0] & 0xff) << bitsNeedTobeTransfer[3]
											);
				// Move forward
				cur = cur + 3;
			}
			// Passed the null character
			cur = cur + nullCharacterNum;
		}
		
		// End reading
		inputStream.close();
		
		// Create image
		image = Toolkit.getDefaultToolkit().createImage((ImageProducer) new MemoryImageSource(width, height, pixel, 0, width));
		
		
		return image;
	}

	@Override
	public Image myWrite(Image image, String filepath) throws IOException {
		// Necessary variables
		File file              = null;
		BufferedImage bImage   = null;
		Graphics2D    graphics = null;
		
		// Read the file you want to save
		file = new File(filepath + ".bmp");
		
		// Create a bufferedImage can hold image data
		bImage = new BufferedImage(image.getWidth(null), image.getHeight(null), BufferedImage.TYPE_INT_RGB);
		
		// Use Graphics2D to store the data in bufferedImage
		graphics = bImage.createGraphics();
		graphics.drawImage(image, 0, 0, null);
		graphics.dispose();
		
		// Output the image
		ImageIO.write(bImage, "bmp", file);
		
		return image;
	}

}
