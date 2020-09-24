package 蜘蛛纸牌;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;

    public class SpiderMenuBar extends JMenuBar implements ActionListener {
        Spider main = null;
        public SpiderMenuBar(Spider spider) {
            this.main=spider;
            //设置菜单栏及各个选项
            JMenu menu1 = new JMenu("菜单");
            JMenu menu2 = new JMenu("难度");
            JMenu menu3 = new JMenu("帮助");
            JMenuItem item1 = new JMenuItem("开始");
            JMenuItem item2 = new JMenuItem("退出");
            JMenuItem item3 = new JMenuItem("简单");
            JMenuItem item4 = new JMenuItem("中等");
            JMenuItem item5 = new JMenuItem("困难");
            JMenuItem item6 = new JMenuItem("规则");
            JMenuItem item7 = new JMenuItem("声明");
            menu1.add(item1);
            menu1.add(item2);
            menu2.add(item3);
            menu2.add(item4);
            menu2.add(item5);
            menu3.add(item6);
            menu3.add(item7);
            this.add(menu1);
            this.add(menu2);
            this.add(menu3);
            item1.addActionListener(this);
            item2.addActionListener(this);
            item3.addActionListener(this);
            item4.addActionListener(this);
            item5.addActionListener(this);
            item6.addActionListener(this);
            item7.addActionListener(this);
        }
        public void actionPerformed(ActionEvent e) {
            String str = e.getActionCommand();
            if ("规则".equals(str)) {
                JOptionPane.showMessageDialog(null, "将电脑多次分发给你的牌按照相同的花色由大至小排列起来。直到桌面上的牌全都消失。", "规则", JOptionPane.INFORMATION_MESSAGE);
            } else if ("声明".equals(str)) {
                JOptionPane.showMessageDialog(null, "该游戏中，纸牌的图片来自于Windows XP的纸牌游戏，图片权属于原作者所有！", "声明", JOptionPane.INFORMATION_MESSAGE);
            } else if ("开始".equals(str)) {
                main.newGame();
            } else if ("退出".equals(str)) {
                System.exit(0);
            } else if ("简单".equals(str)) {
                main.setGrade(1);
                main.initCards();
                main.newGame();
            } else if ("中等".equals(str)) {
                main.setGrade(2);
                main.initCards();
                main.newGame();
            } else if ("困难".equals(str)) {
                main.setGrade(3);
                main.initCards();
                main.newGame();
            }
        }

    }