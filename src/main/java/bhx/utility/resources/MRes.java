package bhx.utility.resources;

import java.awt.Image;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Base64;

import javax.swing.ImageIcon;

public class MRes {
	public static URL getResourceURL(String path) {
		return MRes.class.getClassLoader().getResource(path);
	}

	public static InputStream getResourceStream(String path) {
		return MRes.class.getResourceAsStream(path);
	}

	public static ImageIcon getImageIcon(String name) {
		URL url = getResourceURL("resources/images/" + name);
		return (url != null) ? new ImageIcon(url) : new ImageIcon(getResourceURL("resources/images/noimage.png"));
	}

	public static Image getImage(String name) {
		ImageIcon icon = getImageIcon(name);
		return icon != null ? icon.getImage() : null;
	}
	
	public static String encoder(String imagePath) throws IOException  {
		String base64Image = "";
		File file = new File(imagePath);
		
		FileInputStream imageInFile = new FileInputStream(file);
		byte[] imageData = new byte[(int) file.length()];
		imageInFile.read(imageData);
		base64Image = Base64.getEncoder().encodeToString(imageData);
		return base64Image;
	}
}
