package bhx.common;

import javax.swing.*;
import java.awt.*;

public class MProgresBar extends JPanel {

	private static final long serialVersionUID = 1L;

	private JProgressBar progresBar = new JProgressBar(0, 100);

	public MProgresBar() {
		this.setLayout(new BorderLayout());
		this.add(progresBar, BorderLayout.CENTER);
		this.progresBar.setStringPainted(true);
	}

	@Override
	public void show() {
		progresBar.setVisible(true);
	}

	@Override
	public void hide() {
		progresBar.setVisible(false);
	}

	public void setText(String text) {
		progresBar.setString(text);
	}

	public void setVale(int value) {
		progresBar.setValue(value);
	}

	public void setIndeterminate(boolean indeterminate) {
		progresBar.setIndeterminate(indeterminate);
	}
}
