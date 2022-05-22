package me.jfz.swingframework;

import com.formdev.flatlaf.FlatDarculaLaf;
import com.formdev.flatlaf.FlatDarkLaf;
import com.formdev.flatlaf.FlatIntelliJLaf;
import com.formdev.flatlaf.FlatLaf;
import com.formdev.flatlaf.FlatLightLaf;
import com.formdev.flatlaf.extras.FlatAnimatedLafChange;
import com.formdev.flatlaf.extras.FlatSVGIcon;
import com.formdev.flatlaf.extras.FlatSVGUtils;
import com.formdev.flatlaf.extras.components.FlatButton;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JComboBox;
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
     private static JComboBox<String> lookAndFeelComboBox;
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
            JMenuBar menuBar = new JMenuBar();
            createMenuBar(jf, menuBar);

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
     * 创建菜单栏
     *
     * @param jf JFrame
     * @param menuBar 菜单栏
     */
    private static void createMenuBar(JFrame jf, JMenuBar menuBar) {
        // 文件
        JMenu fileMenu = new JMenu();
        fileMenu.setText("文件");
        fileMenu.setMnemonic('F');
        // 文件-导入配置
        JMenuItem openMenuItem = new JMenuItem();
        openMenuItem.setText("导入配置...");
        openMenuItem.setMnemonic('I');
        fileMenu.add(openMenuItem);
        // 文件-导出配置
        JMenuItem saveAsMenuItem = new JMenuItem();
        saveAsMenuItem.setText("导出配置...");
        saveAsMenuItem.setMnemonic('S');
        fileMenu.add(saveAsMenuItem);
        fileMenu.addSeparator();
        // 文件-退出
        JMenuItem exitMenuItem = new JMenuItem();
        exitMenuItem.setText("退出");
        exitMenuItem.setMnemonic('X');
        fileMenu.add(exitMenuItem);
        menuBar.add(fileMenu);

        // 视图
        JMenu viewMenu = new JMenu();
        viewMenu.setText("视图");
        viewMenu.setMnemonic('V');
        // 视图-状态栏
        JCheckBoxMenuItem statusMenuItem = new JCheckBoxMenuItem();
        statusMenuItem.setText("状态栏");
        viewMenu.add(statusMenuItem);
        menuBar.add(viewMenu);

        // 设置
        JMenu optionsMenu = new JMenu();
        optionsMenu.setText("设置");
        optionsMenu.setMnemonic('O');
        // 设置-主题
        JMenuItem themeMenuItem = new JMenuItem();
        themeMenuItem.setText("主题");
        themeMenuItem.setMnemonic('T');
        themeMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // 点击按钮, 显示新的一个窗口
                showThemeWindow(jf);
            }
        });
        optionsMenu.add(themeMenuItem);
        // 设置-插件
        JMenuItem pluginsMenuItem = new JMenuItem();
        pluginsMenuItem.setText("插件");
        pluginsMenuItem.setMnemonic('E');
        optionsMenu.add(pluginsMenuItem);
        optionsMenu.addSeparator();
        // 设置-设置
        JMenuItem optionsMenuItem = new JMenuItem();
        optionsMenuItem.setText("设置");
        optionsMenuItem.setMnemonic('P');
        optionsMenu.add(optionsMenuItem);
        menuBar.add(optionsMenu);

        // 帮助
        JMenu helpMenu = new JMenu();
        helpMenu.setText("帮助");
        helpMenu.setMnemonic('H');
        // 帮助-在线帮助
        JMenuItem onlineMenuItem = new JMenuItem();
        onlineMenuItem.setText("在线帮助");
        onlineMenuItem.setMnemonic('E');
        helpMenu.add(onlineMenuItem);
        // 帮助-检查更新
        JMenuItem updateMenuItem = new JMenuItem();
        updateMenuItem.setText("检查更新");
        updateMenuItem.setMnemonic('U');
        helpMenu.add(updateMenuItem);
        // 帮助-报告错误
        JMenuItem reportMenuItem = new JMenuItem();
        reportMenuItem.setText("报告错误");
        reportMenuItem.setMnemonic('R');
        helpMenu.add(reportMenuItem);
        helpMenu.addSeparator();
        // 帮助-关于
        JMenuItem aboutMenuItem = new JMenuItem();
        aboutMenuItem.setText("关于");
        aboutMenuItem.setMnemonic('A');
        helpMenu.add(aboutMenuItem);
        menuBar.add(helpMenu);

        // 用户按钮 靠右
        FlatButton usersButton = new FlatButton();
        ImageIcon flatSVGIcon = new FlatSVGIcon("users.svg");
        usersButton.setIcon(flatSVGIcon);
        // usersButton.setIcon(new ImageIcon("D:/Jfz/头像/异想家poi(310x310)无手.jpg"));
        usersButton.setButtonType(FlatButton.ButtonType.toolBarButton);
        usersButton.setFocusable(false);
        usersButton.addActionListener(e -> JOptionPane.showMessageDialog(null, "Hello User! How are you?", "User",
            JOptionPane.INFORMATION_MESSAGE));
        menuBar.add(Box.createGlue());
        menuBar.add(usersButton);

        jf.setJMenuBar(menuBar);
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

    public static void showThemeWindow(JFrame relativeWindow) {
        // 创建一个新窗口
        JFrame newJFrame = new JFrame("主题设置");
        newJFrame.setSize(250, 250);
        // 把新窗口的位置设置到 relativeWindow 窗口的中心
        newJFrame.setLocationRelativeTo(relativeWindow);
        // 点击窗口关闭按钮, 执行销毁窗口操作（如果设置为 EXIT_ON_CLOSE, 则点击新窗口关闭按钮后, 整个进程将结束）
        newJFrame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        // 窗口设置为不可改变大小
        newJFrame.setResizable(false);
        JPanel panel = new JPanel(new GridLayout(1, 1));
        // 在新窗口中显示一个标签
        JLabel label = new JLabel("这是一个窗口");

        label.setFont(new Font(null, Font.PLAIN, 25));
        label.setHorizontalAlignment(SwingConstants.CENTER);
        label.setVerticalAlignment(SwingConstants.TOP);
        panel.add(label);

        // 需要选择的条目
        String[] listData = new String[]{FlatLightLaf.class.getName(),
            FlatDarkLaf.class.getName(),
            FlatIntelliJLaf.class.getName(),
            FlatDarculaLaf.class.getName(),
            "com.sun.java.swing.plaf.windows.WindowsLookAndFeel"};

        // 创建一个下拉列表框
        lookAndFeelComboBox = new JComboBox<String>(listData);

        // 添加条目选中状态改变的监听器
        lookAndFeelComboBox.addItemListener(e1 -> {
            // 只处理选中的状态
            if (e1.getStateChange() == ItemEvent.SELECTED) {
                System.out.println("选中: " + lookAndFeelComboBox.getSelectedIndex() + " = " + lookAndFeelComboBox.getSelectedItem());
                // 修改主题事件
                lookAndFeelComboBox.addActionListener(e2 -> lookAndFeelChanged(lookAndFeelComboBox.getSelectedItem()));
            }
        });
        // 设置默认选中的条目
        lookAndFeelComboBox.setSelectedIndex(0);
        panel.add(lookAndFeelComboBox);

        newJFrame.setContentPane(panel);
        newJFrame.setVisible(true);
    }

    // 修改主题事件
    private static void lookAndFeelChanged(Object aaa) {
        String lafClassName = (String) aaa;
        if( lafClassName == null )
            return;

        if( lafClassName.equals( UIManager.getLookAndFeel().getClass().getName() ) )
            return;

        EventQueue.invokeLater( () -> {
            try {
                FlatAnimatedLafChange.showSnapshot();

                // change look and feel
                UIManager.setLookAndFeel( lafClassName );

                // clear custom default font when switching to non-FlatLaf LaF
                if( !(UIManager.getLookAndFeel() instanceof FlatLaf) )
                    UIManager.put( "defaultFont", null );

                // update all components
                FlatLaf.updateUI();
                FlatAnimatedLafChange.hideSnapshotWithAnimation();

                // increase size of frame if necessary
                // int width = frame.getWidth();
                // int height = frame.getHeight();
                // Dimension prefSize = frame.getPreferredSize();
                // if( prefSize.width > width || prefSize.height > height )
                //     frame.setSize( Math.max( prefSize.width, width ), Math.max( prefSize.height, height ) );

            } catch( Exception ex ) {
                // LoggingFacade.INSTANCE.logSevere( null, ex );
            }
        } );
    }
}

