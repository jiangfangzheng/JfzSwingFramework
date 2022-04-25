package me.jfz.swingframework;

import com.formdev.flatlaf.FlatLightLaf;
import com.formdev.flatlaf.extras.FlatSVGIcon;
import com.formdev.flatlaf.extras.FlatSVGUtils;
import com.formdev.flatlaf.extras.components.FlatButton;

import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.Box;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.WindowConstants;

/**
 * 描述
 *
 * @author Sandeepin
 * @since 2022/4/24 0024
 */
public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            try {
                UIManager.setLookAndFeel(new FlatLightLaf());
            } catch (Exception ex) {
                System.err.println("Failed to initialize LaF");
            }

            JFrame jf = new JFrame("JfzSwingFramework");
            jf.setSize(640, 480);
            jf.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
            jf.setLocationRelativeTo(null);
            jf.setIconImages(FlatSVGUtils.createWindowIconImages("/logo.svg"));

            // 菜单栏
            JMenuBar menuBar1 = new JMenuBar();

            // 菜单栏中菜单项
            JMenu fileMenu = new JMenu();
            fileMenu.setText("File");
            fileMenu.setMnemonic('F');
            JMenuItem newMenuItem = new JMenuItem("123");
            JMenuItem openMenuItem = new JMenuItem("345");
            fileMenu.add(newMenuItem);
            fileMenu.add(openMenuItem);
            JMenu optionsMenu = new JMenu();
            optionsMenu.setText("Options");
            optionsMenu.setMnemonic('O');
            menuBar1.add(fileMenu);
            menuBar1.add(optionsMenu);

            // 菜单栏增加用户按钮 靠右
            FlatButton usersButton = new FlatButton();
            usersButton.setIcon(new FlatSVGIcon("/users.svg"));
            usersButton.setButtonType(FlatButton.ButtonType.toolBarButton);
            usersButton.setFocusable(false);
            usersButton.addActionListener(e -> JOptionPane.showMessageDialog(null, "Hello User! How are you?", "User",
                JOptionPane.INFORMATION_MESSAGE));
            menuBar1.add(Box.createGlue());
            menuBar1.add(usersButton);

            jf.setJMenuBar(menuBar1);

            // 创建选项卡面板
            final JTabbedPane tabbedPane = new JTabbedPane();
            // 创建第 i 个选项卡（选项卡只包含 标题）
            for (int i = 0; i < 3; i++) {
                tabbedPane.addTab("Tab" + i, createTextPanel("Tab" + i));
            }
            // 设置默认选中的选项卡
            tabbedPane.setSelectedIndex(0);
            jf.setContentPane(tabbedPane);
            jf.setVisible(true);
        });
        System.out.println("JfzSwingFramework poi!");
    }

    /**
     * 创建一个面板，面板中心显示一个标签，用于表示某个选项卡需要显示的内容
     *
     * @param text 文本
     * @return JComponent
     */
    private static JComponent createTextPanel(String text) {
        // 创建面板, 使用一个1行1列的网格布局（为了让标签的宽高自动撑满面板）
        JPanel panel = new JPanel(new GridLayout(1, 1));
        // 创建标签
        JLabel label = new JLabel(text);
        label.setFont(new Font(null, Font.PLAIN, 50));
        label.setHorizontalAlignment(SwingConstants.CENTER);
        // 添加标签到面板
        panel.add(label);
        return panel;
    }
}
