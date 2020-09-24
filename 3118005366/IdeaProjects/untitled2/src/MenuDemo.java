import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class MenuDemo extends JFrame implements ActionListener{
    JButton b;
    JLabel l;
    public static void main(String[] args) {
        MenuDemo demo = new MenuDemo();
        demo.go();
    }
    public void go() {
        this.setSize(400, 400);
        this.setVisible(true);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JPanel jp1=new JPanel();
        JPanel jp2=new JPanel();
        setLayout(new GridLayout(2,1));
        b=new JButton("按钮");
        b.addActionListener(this);
        jp1.add(b);
        l=new JLabel("你好");
        jp2.add(l);
        this.add(jp1);
        this.add(jp2);
        this.setVisible(true);
    }
    public void actionPerformed(ActionEvent e) {
        if (l.getText() == "你好") {
            l.setText("再见");
        } else {
            l.setText("你好");
        }
    }
}


