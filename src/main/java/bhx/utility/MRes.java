package bhx.utility;

import javax.swing.*;
import java.awt.*;
import java.io.InputStream;
import java.net.URL;

public class MRes {
	public static URL getResourceURL(String path) {
		return MRes.class.getClassLoader().getResource(path);
	}

	public static InputStream getResourceStream(String path) {
		return MRes.class.getClassLoader().getResourceAsStream(path);
	}

	public static ImageIcon getImageIcon(String name) {
		URL url = getResourceURL("resources/images/" + name);
		return (url != null) ? new ImageIcon(url) : new ImageIcon(getResourceURL("resources/images/noimage.png"));
	}

	public static Image getImage(String name) {
		ImageIcon icon = getImageIcon(name);
		return icon != null ? icon.getImage() : null;
	}
}
