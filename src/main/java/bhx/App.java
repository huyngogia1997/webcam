package bhx;

import bhx.common.Const;
import bhx.presentation.view.MainLayout;
import bhx.utility.resources.MRes;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import javax.swing.*;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.awt.*;
import java.io.IOException;
import java.io.InputStream;

/**
 * Hello world!
 *
 */
public class App {

	private static MainLayout mainLayout = null;

	private static Dimension mainFrameSize = new Dimension(800, 500);


	public static void main(String[] args) {
		createAndShowGUI();

	}


	private static Document loadXML(InputStream inputStream)
			throws ParserConfigurationException, SAXException, IOException {
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		DocumentBuilder db = dbf.newDocumentBuilder();
		InputSource is = new InputSource(inputStream);
		return db.parse(is);
	}

	private static void createAndShowGUI() {
		mainLayout = MainLayout.ins();
		mainLayout.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mainLayout.setMinimumSize(mainFrameSize);
		mainLayout.setPreferredSize(mainFrameSize);
		mainLayout.setVisible(true);
		mainLayout.setLocationRelativeTo(null);
		mainLayout.pack();
		// mainLayout.setExtendedState(JFrame.MAXIMIZED_BOTH);
	}

	public void test() {
		System.out.println("Testsss");
	}

	private static long ret;
	private static String INIT_FAIL_MESSAGE = "";

}
