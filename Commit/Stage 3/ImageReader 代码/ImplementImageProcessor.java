package imageProcessing;

import java.awt.Image;
import java.awt.Toolkit;
import java.awt.image.FilteredImageSource;
import java.awt.image.RGBImageFilter;

import imagereader.IImageProcessor;

public class ImplementImageProcessor implements IImageProcessor {

	public Image showChanelR(Image image) {
		ColorFilter redFilter = new ColorFilter("RED");
		
		return Toolkit.getDefaultToolkit().createImage(new FilteredImageSource(image.getSource(), redFilter));
	}

	public Image showChanelG(Image image) {
		ColorFilter greenFilter = new ColorFilter("GREEN");
		
		return Toolkit.getDefaultToolkit().createImage(new FilteredImageSource(image.getSource(), greenFilter));
	}

	public Image showChanelB(Image image) {
		ColorFilter blueFilter = new ColorFilter("BLUE");
		
		return Toolkit.getDefaultToolkit().createImage(new FilteredImageSource(image.getSource(), blueFilter));
	}

	public Image showGray(Image image) {
		ColorFilter grayFilter = new ColorFilter("GRAY");
		
		return Toolkit.getDefaultToolkit().createImage(new FilteredImageSource(image.getSource(), grayFilter));
	}

}

class ColorFilter extends RGBImageFilter {
	private String RGBchoose;
	
	public ColorFilter (String choose) {
		RGBchoose                = choose;
		canFilterIndexColorModel = true;
	}

	@Override
	public int filterRGB(int x, int y, int rgb) {
		
		if (RGBchoose.equals("RED")) {
			return (rgb & 0xffff0000);
		}
		else if (RGBchoose.equals("GREEN")) {
			return (rgb & 0xff00ff00);
		}
		else if (RGBchoose.equals("BLUE")) {
			return (rgb & 0xff0000ff);
		}
		else if (RGBchoose.equals("GRAY")) {
			int grayRGB = (int)(
									((rgb & 0x00ff0000) >> 16) * 0.299 +
									((rgb & 0x0000ff00) >> 8 ) * 0.587 +
									((rgb & 0x000000ff) >> 0 ) * 0.114
							   );
			
			return (
						(rgb & 0xff000000) + (grayRGB << 16) + (grayRGB << 8) + (grayRGB)
				   );
		}
		
		
		return 0;
	}
	
}