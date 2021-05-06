package bhx.utility;

import java.awt.Color;
import javax.swing.JButton;

public class StyleUtils {

	public static void primaryButton(JButton button) {
		button.setBackground(new Color(91, 192, 222));
	}

	public static void dangerButton(JButton button) {
		button.setBackground(new Color(217, 83, 79));
	}

	public static void normalButton(JButton button) {
		button.setBackground(new Color(92, 184, 92));
	}
	
	public static void commonButton(JButton button) {
		button.setBackground(new Color(0, 87, 216));
	}
	
	public static void switchButton(JButton button) {
		button.setBackground(new Color(255,255,255));
	}

	public static Color getLinkColor() {
		return new Color(66, 139, 202);
	}
}
