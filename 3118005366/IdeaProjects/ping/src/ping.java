import javax.swing.JOptionPane;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.nio.charset.Charset;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;



public class ping extends JFrame implements ActionListener,KeyListener {

    JTextField input;//输入文本处
    JTextArea output_text;//输出结果处
    JScrollPane scroll_bar;//滚动条
    JButton confirm;//确认按钮
    JButton empty;//清空按钮
    int timeOut=3000;//设置3秒超时
    int pingTimes=4;//设置ping次数

    //交互界面设置方法
    public void start_screen(){
        //窗口设置
        this.setTitle("ping程序----3118005366赖晋启");//窗口标题
        this.setSize(600,620);//设置窗口大小
        this.setLocationRelativeTo(null);//窗口居中
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//窗口可关闭按钮
        this.setLayout(null);//固定布局
        //输入文本处提示标题
        JLabel input_prompt=new JLabel("请输入你需要ping的ip地址：");
        input_prompt.setBounds(30,6,160,50);
        this.add(input_prompt);
        //文本输入处
        input=new JTextField();
        input.setBounds(190,17,270,30);
        this.add(input);
        //结果输出处
        output_text=new JTextArea();
        scroll_bar=new JScrollPane(output_text,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);//显示滚动条
        scroll_bar.setBounds(30,100,530,460);
        this.add(scroll_bar);
        output_text.setEditable(false);//禁止用户修改文本区
        //输入确认按钮
        confirm=new JButton("确认");
        confirm.setBounds(470,17,70,30);
        this.add(confirm);
        //清空屏幕信息按钮
        empty=new JButton("清空");
        empty.setBounds(30,60,70,30);
        this.add(empty);
        //注册监听
        input.addKeyListener(this);
        confirm.addActionListener(this);
        confirm.setActionCommand("确认");
        empty.addActionListener(this);
        empty.setActionCommand("清空");
        //窗口可视化
        this.setVisible(true);
    }

    //按钮响应方法
    public void actionPerformed(ActionEvent e) {
        if(e.getActionCommand().equals("确认")){//点击确认键输入IP地址
            judge(input.getText());
        }
        if(e.getActionCommand().equals("清空")){//点击清空清空文本框内容
            output_text.setText("");
            input.setText("");
        }
    }

    //键盘响应方法
    @Override
    public void keyReleased(KeyEvent e) {
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if(e.getKeyCode()==KeyEvent.VK_ENTER){//按回车键输入IP地址
            judge(input.getText());
        }
    }

    //判断IP地址是否正确,若正确执行ping
    public void judge(String inputData){
        String judgeData="^(1\\d{2}|2[0-4]\\d|25[0-5]|[1-9]\\d|[1-9])\\."+"(1\\d{2}|2[0-4]\\d|25[0-5]|[1-9]\\d|\\d)\\."+"(1\\d{2}|2[0-4]\\d|25[0-5]|[1-9]\\d|\\d)\\."+"(1\\d{2}|2[0-4]\\d|25[0-5]|[1-9]\\d|\\d)$";//正则表达式定义IP地址格式
        String judheDataParagraph="^(1\\d{2}|2[0-4]\\d|25[0-5]|[1-9]\\d|[1-9])\\."+"(1\\d{2}|2[0-4]\\d|25[0-5]|[1-9]\\d|\\d)\\."+"(1\\d{2}|2[0-4]\\d|25[0-5]|[1-9]\\d|\\d)\\."+"(1\\d{2}|2[0-4]\\d|25[0-5]|[1-9]\\d|\\d)"+"[-]"+"(1\\d{2}|2[0-4]\\d|25[0-5]|[1-9]\\d|[1-9])\\."+"(1\\d{2}|2[0-4]\\d|25[0-5]|[1-9]\\d|\\d)\\."+"(1\\d{2}|2[0-4]\\d|25[0-5]|[1-9]\\d|\\d)\\."+"(1\\d{2}|2[0-4]\\d|25[0-5]|[1-9]\\d|\\d)$";//正则表达式定义IP地址段
        if(inputData.matches(judgeData)) {
            doping(inputData);
        }
        else if(inputData.matches(judheDataParagraph)) {
            joinTeam(inputData);
        }
        else {
            error();
        }
    }

    //IP地址错误提示窗口
    public void error(){
        JOptionPane.showMessageDialog(null, "输入的IP地址或地址段无效!", "警告", JOptionPane.WARNING_MESSAGE);
    }

    //IP地址段入队
    public void joinTeam(String inputData){
        String[] ip = inputData.split("-");//建立字符串数组，分为起始IP和结束IP
        String[] ip0 = ip[0].split("\\.");//建立字符串数组，把起始IP以“.”分为4组数据
        String[] ip1 = ip[1].split("\\.");//建立字符串数组，把结束IP以“.”分为4组数据
        int start0 = Integer.parseInt(ip0[0]);//把起始IP第一组数据转换为整形
        int end0 = Integer.parseInt(ip1[0]);//把结束IP第一组数据转换为整型
        int start1 = Integer.parseInt(ip0[1]);
        int end1 = Integer.parseInt(ip1[1]);
        int start2 = Integer.parseInt(ip0[2]);
        int end2 = Integer.parseInt(ip1[2]);
        int start3 = Integer.parseInt(ip0[3]);
        int end3 = Integer.parseInt(ip1[3]);
        int i, j, k, l;
        String m;
        String ipAddress;//定义IP地址段的每一个IP地址
        if(end0>=start0) {//结束IP第一组数据大于起始IP第一组数据，IP地址段有效，开始入队
            for (l = start0; l <= end0; l++) {
                if (end0 == start0) {
                    for (k = start1; k <= end1; k++) {
                        if (end1 == start1) {
                            for (j = start2; j <= end2; j++) {
                                if (end2 == start2) {
                                    for (i = start3; i <= end3; i++) {//起始IP与结束IP第一二三组数据相等，遍历ip0[3]到ip1[3]的IP地址，并且执行ping
                                        m = String.valueOf(i);
                                        ipAddress = ip0[0] + "." + ip0[1] + "." + ip0[2] + "." + m;
                                        doping(ipAddress);
                                    }
                                } else {//起始IP与结束IP第一二组数据相等，第三组数据不相等，遍历"ip1[0].ip1[1].ip1[2].0"之前的所有IP地址，并且执行ping
                                    for (j = start2; j <= end2; j++) {
                                        for (i = start3; i <= 255; i++) {
                                            m = String.valueOf(i);
                                            ipAddress = ip0[0] + "." + ip0[1] + "." + ip0[2] + "." + m;
                                            doping(ipAddress);
                                        }
                                        start2++;
                                        start3 = 0;
                                        ip0[2] = String.valueOf(start2);
                                    }
                                }
                            }
                        } else {//起始IP与结束IP第一组数据相等，第二组数据不相等，遍历"ip1[0].ip1[1].0.0"之前的所有IP地址，并且执行ping
                            for (k = start1; k <= end1; k++) {
                                for (j = start2; j <= 255; j++) {
                                    for (i = start3; i <= 255; i++) {
                                        m = String.valueOf(i);
                                        ipAddress = ip0[0] + "." + ip0[1] + "." + ip0[2] + "." + m;
                                        doping(ipAddress);
                                    }
                                    start2++;
                                    start3 = 0;
                                    ip0[2] = String.valueOf(start2);
                                }
                                start1++;
                                start2 = 0;
                                ip0[1] = String.valueOf(start1);
                            }
                        }
                    }
                } else {//起始IP与结束IP第一组数据不相等，遍历"ip1[0].0.0.0"之前的所有IP地址，并且执行ping
                    for (l = start0; l <= end0; l++) {
                        for (k = start1; k <= 255; k++) {
                            for (j = start2; j <= 255; j++) {
                                for (i = start3; i <= 255; i++) {
                                    m = String.valueOf(i);
                                    ipAddress = ip0[0] + "." + ip0[1] + "." + ip0[2] + "." + m;
                                    doping(ipAddress);
                                }
                                start2++;
                                start3 = 0;
                                ip0[2] = String.valueOf(start2);
                            }
                            start1++;
                            start2 = 0;
                            ip0[1] = String.valueOf(start1);
                        }
                        start0++;
                        start1 = 0;
                        ip0[0] = String.valueOf(start0);
                    }
                }
            }
        }
        else {
            error();
        }
    }

    //执行ping线程
    public void doping(String ip) {
        if(pingInetAddress(ip)){
            output_text.append(ip+"    网络可达");
        }
        else {
            output_text.append(ip+"    网络不可达");
        }
        pingCmd(ip);
        if(pingConsole(ip)){
            output_text.append("\n访问成功\n\n\n\n");
        }
        else{
            output_text.append("\n访问失败\n\n\n\n");
        }
    }

    //查看IP地址是否可达
    public boolean pingInetAddress(String ip) {
        try {
            InetAddress inetAddress = InetAddress.getByName(ip);
            if (inetAddress.isReachable(timeOut)) {
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    //调用cmd执行ping
    public void pingCmd(String ip)  {
        String line ;
        try {
            Process process = Runtime.getRuntime().exec("ping " + ip);
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(process.getInputStream(), Charset.forName("GBK")));//读取cmd执行ping的信息
            while ((line = bufferedReader.readLine()) != null) output_text.append("\n"+line+"\n");//输出在cmd读取的信息
        } catch (Exception ex) {
            output_text.append("\n"+ex.getMessage()+"\n");//读取错误信息并输出
        }
    }

    //调用控制台执行ping
    public boolean pingConsole(String ip) {
        BufferedReader bufferedReader = null;
        Runtime runtime = Runtime.getRuntime();
        String string = "ping " + ip + " -n " + pingTimes    + " -w " + timeOut;
        try {
            Process process = runtime.exec(string);
            if (process == null) {
                return false;
            }
            bufferedReader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            int count = 0;
            String line ;
            while ((line = bufferedReader.readLine()) != null) {
                count += judgeLine(line);
            }
            return count == pingTimes;
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        } finally {
            try {
                bufferedReader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    //判断line格式是否正确
    private int judgeLine(String line) {
        Pattern pattern = Pattern.compile("(\\d+ms)(\\s+)(TTL=\\d+)",    Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(line);
        while (matcher.find()) {
            return 1;
        }
        return 0;
    }

    //主方法
    public static void main(String[] args)  {
        ping p=new ping();
        p.start_screen();
    }
}




