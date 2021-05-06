package bhx.presentation.view;

import bhx.presentation.component.CheckComponent;
import bhx.presentation.component.RegisterComponent;
import bhx.utility.Constant;
import bhx.utility.MRes;

import javax.swing.*;
import java.awt.*;

public class MainLayout extends JFrame {
    private static final long serialVersionUID = 1L;
    private static MainLayout ins = null;
    private RegisterComponent registerTab;
    private CheckComponent checkComponent;

    public static MainLayout ins() {
        if (ins == null) {
            ins = new MainLayout();
            ins.setTitle("demo");
        }
        return ins;
    }
    private MainLayout() {
        initGUI();
    }
    private void initGUI() {
        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.add(createHeaderPanel(), BorderLayout.PAGE_START);
        mainPanel.add(createTabPanel(), BorderLayout.CENTER);
        this.getContentPane().add(mainPanel, BorderLayout.CENTER);
        this.pack();
    }

    private JPanel createHeaderPanel() {
        JPanel headerPanel = new JPanel();
        headerPanel.setBackground(Constant.bhxBackgroundColor);
        headerPanel.add(new JLabel(MRes.getImageIcon("bhxHeader.png")));
        headerPanel.setBorder(BorderFactory.createEmptyBorder(0, 10, 10, 10));
        headerPanel.setLayout(new BoxLayout(headerPanel, BoxLayout.LINE_AXIS));
        headerPanel.add(Box.createHorizontalGlue());
        headerPanel.add(Box.createRigidArea(new Dimension(10, 0)));
        return headerPanel;
    }

    private JPanel createTabPanel() {
        registerTab = RegisterComponent.init();
        checkComponent = CheckComponent.init();

        JPanel tabPanel = new JPanel();
        tabPanel.setBorder(BorderFactory.createEmptyBorder(0, 10, 10, 10));
        tabPanel.setLayout(new BoxLayout(tabPanel, BoxLayout.LINE_AXIS));
        JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
        ImageIcon icon = bhx.utility.resources.MRes.getImageIcon("tablayout.png");
        tabbedPane.addTab("Register", icon, registerTab);
        tabbedPane.addTab("Check", icon, checkComponent);
        tabPanel.add(tabbedPane);
        return tabPanel;
    }

}
