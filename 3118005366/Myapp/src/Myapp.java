import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

public class Myapp {
    public static void main(String[] args){
        int i=0;
        int j=0;
        for(int k=0;k<args.length;k++){
            if(args[k].equals("-n"))i=k+1;
            if(args[k].equals("-r"))j=k+1;
        }
        int n = Integer.parseInt(args[i]);//题目个数
        int r = Integer.parseInt(args[j]);//数值范围
        sysout(n,r);
    }

    public static Map<String, Set<String>> Data(int n,int r){
        int x1,x2,y1,y2,z1,z2,op,i;
        char o1,o2;
        String re=null;
        String s;
        Map<String, Set<String>> map = new HashMap<>();
        for(int j=0;j<n;j++){
            i=randomNumber(0,2);
            x1=randomNumber(0,r);
            x2=randomNumber(1,r);
            y1=randomNumber(0,r);
            y2=randomNumber(1,r);
            z1=randomNumber(0,r);
            z2=randomNumber(1,r);
            o1=Symbol();
            o2=Symbol();
            op=randomNumber(0,3);
            if(i==0){
                s=Q1(x1,x2,y1,y2,o1);
                re=operation(o1,x1,x2,y1,y2);
            }
            else {
                s=Q2(x1,x2,y1,y2,z1,z2,o1,o2,op);
                if(op==0) {
                    if(o1=='×'){
                        re=operation(o2,x1*y1,x2*y2,z1,z2);
                    }
                    else if(o1=='÷'){
                        if(y1!=0) {
                            re = operation(o2, x1 * y2, x2 * y1, z1, z2);
                        }
                    }
                    else if(o2=='×'){
                        re=operation(o1,x1,x2,y1*z1,y2*z2);
                    }
                    else if(o2=='÷'){
                        if(z1!=0){
                            re=operation(o1,x1,x2,y1*z2,y2*z1);
                        }
                    }
                    else if(o1=='+'){
                        re=operation(o2,x1*y2+y1*x2,x2*y2,z1,z2);
                    }
                    else if(o1=='-'){
                        re=operation(o2,x1*y2-x2*y1,y2*x2,z1,z2);
                    }
                }
                else if(op==1){
                    if(o1=='×'){
                        re=operation(o2,x1*y1,x2*y2,z1,z2);
                    }
                    else if(o1=='÷'){
                        if(y1!=0){
                            re=operation(o2,x1*y2,x2*y1,z1,z2);
                        }
                    }
                    else if(o1=='+'){
                        re=operation(o2,x1*y2+y1*x2,x2*y2,z1,z2);
                    }
                    else if(o1=='-'){
                        re=operation(o2,x1*y2-x2*y1,y2*x2,z1,z2);
                    }
                }
                else{
                    if(o2=='×'){
                        re=operation(o1,x1,x2,y1*z1,y2*z2);
                    }
                    else if(o2=='÷'){
                        re=operation(o1,x1,x2,y1*z2,y2*z1);
                    }
                    else if(o2=='+'){
                        re=operation(o1,x1,x2,y1*z2+y2*z1,y2*z2);
                    }
                    else if(o2=='-'){
                        re=operation(o1,x1,x2,y1*z2-y2*z1,y2*z2);
                    }
                }
            }
            if(re!=null) {
                if (!isDuplicate(map,s,re)) {
                    Set<String> ques = null;
                    if (map.containsKey(r)) {
                        ques = map.get(r);
                    } else {
                        ques = new HashSet<>();
                    }
                    ques.add(s);
                    map.put(re, ques);
                }
                else j--;
            }
            else j--;
        }
        return map;
    }

    //输出题目和答案文件
    public static void sysout(int n,int r){
        int i = 0;
        Map<String, Set<String>> map = Data(n,r);
        Set<String> keySet = map.keySet();
        for (String answer : keySet) {
            Set<String> ques = map.get(answer);
            for(String que : ques){
                i++;
                FileWriter fw = null;
                try {
                    File f=new File("Exersies.txt");//题目写入
                    fw = new FileWriter(f, true);
                } catch (IOException e) {
                    e.printStackTrace();
                }if(que!=null) {
                    PrintWriter pw = new PrintWriter(fw);
                    pw.println(i+"."+" "+que+"\n");
                    pw.flush();
                    try {
                        fw.flush();
                        pw.close();
                        fw.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                FileWriter fn = null;
                try {
                    File f=new File("Answer.txt");//答案写入
                    fn = new FileWriter(f, true);
                } catch (IOException e) {
                    e.printStackTrace();
                }if(answer!=null) {
                    PrintWriter pn = new PrintWriter(fn);
                    pn.println(i+"."+" "+answer+"\n");
                    pn.flush();
                    try {
                        fn.flush();
                        pn.close();
                        fn.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    //生成随机数
    public static int randomNumber(int min,int max){
        return (int)(Math.random()*(max-min)+min);
    }

    //随机生成运算符
    public static char Symbol(){
        int random=randomNumber(1,5);
        if(random==1)return '+';
        else if(random==2)return '-';
        else if(random==3)return '×';
        return '÷';
    }

    //四则运算
    public static String operation(char o,int x1,int x2,int y1,int y2){
        String r = null;
        int m=x2*y2;
        int x3=x1*y2;
        int y3=x2*y1;
        if(o=='+'&&x3+y3>=0)r=dataGeneration(x3+y3,m);
        else if(o=='-'&&x3-y3>=0)r=dataGeneration(x3-y3,m);
        else if(o=='×'&&x1*y1>=0)r=dataGeneration(x1*y1,m);
        else if(o=='÷'&&y3>0&&x3>=0)r=dataGeneration(x3,y3);
        return r;
    }

    //运算数据生成和假分数转化为真分数或者自然数
    public static String dataGeneration(int a,int b){
        String string=null;
        if(a>=b&&b!=0){
            int c=a%b;
            if(c==0){
                string=Integer.toString(a/b);
            }
            else string=Integer.toString(a/b)+"'"+fracReduction(c,b);
        }
        else if(a<b){
            if(a==0){
                string=Integer.toString(0);
            }
            else string=fracReduction(a,b);
        }
        return string;
    }

    //真分数化为最简分数
    public static String fracReduction(int numerator, int denominator) {
        //找到最大公约数，然后分别处以最大公约数
        int m = numerator;
        int n = denominator;
        int r;
        while (numerator > 0){
            r = denominator % numerator;
            denominator = numerator;
            numerator = r;
        }
        return m/denominator + "/" + n/denominator;
    }

    //两个数的四则运算
    public static String Q1(int x1,int x2,int y1,int y2,char o1){
        String Sx,Sy,So;
        Sx=dataGeneration(x1,x2);
        Sy=dataGeneration(y1,y2);
        So=Character.toString(o1);
        String s=Sx+" "+So+" "+Sy;
        return s;
    }

    //三个数的四则运算
    public static String Q2(int x1,int x2,int y1,int y2,int z1,int z2,char o1,char o2,int op){
        String Sx,Sy,Sz,So1,So2,s;
        Sx=dataGeneration(x1,x2);
        Sy=dataGeneration(y1,y2);
        Sz=dataGeneration(z1,z2);
        So1=Character.toString(o1);
        So2=Character.toString(o2);
        if(op==0){
            s=Sx+" "+So1+" "+Sy+" "+So2+" "+Sz;
        }
        else if(op==1){
            s="("+Sx+" "+So1+" "+Sy+")"+" "+So2+" "+Sz;
        }
        else {
            s=Sx+" "+So1+" "+"("+Sy+" "+So2+" "+Sz+")";
        }
        return s;
    }

    /**
     * 反转字符串
     * @param str
     * @return String
     */
    public static String reverse(String str)
    {
        return new StringBuffer(str).reverse().toString();
    }

    /**
     *  判断题目是否和前面生成的重复
     * @param map 以答案为键、相同答案的问题集合为值
     * @param que 要判断是否重复的题目
     * @param answer 要判断的题目的答案
     * @return boolean   true：重复；    false：不重复
     */
    public static boolean isDuplicate(Map<String, Set<String>> map, String que, String answer){
        Set<String> questions = new HashSet<>();
        if(map.containsKey(answer))
            //questions：答案相同的题目集合
            questions = map.get(answer);
        else
            return false;

        for(String question : questions){
            if(question.length()==que.length()){
                //1+2 = 1+2 && 1+2+3 = 1+2+3
                if (que.equals(question))
                    return true;
                //1+2 = 2+1
                if(que.length()==5 && reverse(que).equals(question))
                    return true;

                if(que.length()==12){
                    //que:(1+2)+3
                    if(que.startsWith("(")){
                        //question:(2+1)+3
                        if(question.startsWith("(") && question.charAt(8)==que.charAt(8) && question.charAt(10) == que.charAt(10)) {
                            if (question.charAt(3) == que.charAt(3)) {
                                //(1*2)+3 = (1*2)+3 && (1*2)+3 = (2*1)+3
                                if ((question.charAt(1) == que.charAt(1) && question.charAt(5) == que.charAt(5)) || (question.charAt(5) == que.charAt(1) && question.charAt(1) == que.charAt(5))) {
                                    return true;
                                }
                            }
                        }
                        //question:1+2+3
                        if(!question.contains("(")){
                            //question:1+2+3 && 1*2-3
                            if(question.charAt(6)=='+' || question.charAt(6)=='-'){
                                if(question.charAt(6)==que.charAt(8) && question.charAt(8) == que.charAt(10)) {
                                    if(question.charAt(2) == que.charAt(3)) {
                                        //(1*2)+3 = 1*2+3 && (1*2)+3 = 2*1+3
                                        if((question.charAt(0) == que.charAt(1) && question.charAt(4) == que.charAt(5)) || (question.charAt(4) == que.charAt(1) && question.charAt(0) == que.charAt(5))) {
                                            return true;
                                        }
                                    }
                                }
                            }
                            //question:1+2*3
                            else if(question.charAt(2)=='+' || question.charAt(2)=='-'){
                                if(question.charAt(0)==que.charAt(10) && question.charAt(2) == que.charAt(8)) {
                                    if(question.charAt(6) == que.charAt(3)){
                                        //(1*2)+3 = 3+1*2 && (1*2)+3 = 3+2*1
                                        if((question.charAt(4) == que.charAt(1) && question.charAt(8) == que.charAt(5)) || (question.charAt(8) == que.charAt(1) && question.charAt(4) == que.charAt(5))){
                                            return true;
                                        }
                                    }
                                }
                            }
                        }
                        //question:1+(2+3)
                        if(question.charAt(4)=='('){
                            if(question.charAt(0)==que.charAt(10) && question.charAt(2)==que.charAt(8)){
                                if(question.charAt(7)==que.charAt(3)){
                                    //(1+2)+3 = 3+(1+2) && (1+2)+3 = 3+(2+1)
                                    if((question.charAt(5)==que.charAt(1) && question.charAt(9)==que.charAt(5)) || (question.charAt(9)==que.charAt(1) && question.charAt(5)==que.charAt(5))){
                                        return true;
                                    }
                                }
                            }
                        }
                    }

                    //que:1+(2+3)
                    if(que.charAt(4)=='('){
                        //question:1+(2+3)
                        if(question.charAt(4)=='(' && question.charAt(0)==que.charAt(0) && question.charAt(2)==que.charAt(2)) {
                            if (question.charAt(7) == que.charAt(7)) {
                                //1+(2+3) = 1+(3+2)
                                if ((question.charAt(5) == que.charAt(5) && question.charAt(9) == que.charAt(9)) || (question.charAt(9) == que.charAt(5) && question.charAt(5) == que.charAt(9))) {
                                    return true;
                                }
                            }
                        }
                        //question:1+2+3
                        if(!question.contains("(")){
                            //question:1+2+3 && 1*2+3
                            if(question.charAt(6)=='+' || question.charAt(6)=='-') {
                                if (question.charAt(6) == que.charAt(2) && question.charAt(8) == que.charAt(0)) {
                                    if(question.charAt(2)==que.charAt(7)){
                                        //1+(2*3) = 3*2+1
                                        if((question.charAt(0)==que.charAt(5) && question.charAt(4)==que.charAt(9)) || (question.charAt(4)==que.charAt(5) && question.charAt(0)==que.charAt(9))){
                                            return true;
                                        }
                                    }
                                }
                            }
                            //question:1+2*3
                            else if(question.charAt(2)=='+' || question.charAt(2)=='-'){
                                if (question.charAt(0) == que.charAt(0) && question.charAt(2) == que.charAt(2)){
                                    if(question.charAt(6)==que.charAt(7)){
                                        //3+(1*2) = 3+1*2 && 3+(1*2) = 3+2*1
                                        if((question.charAt(4)==que.charAt(5) && question.charAt(8)==que.charAt(9)) || (question.charAt(8)==que.charAt(5) && question.charAt(4)==que.charAt(9))){
                                            return true;
                                        }
                                    }
                                }
                            }
                        }
                        //question:(1+2)+3
                        if(question.startsWith("(")){
                            if(question.charAt(10)==que.charAt(0) && question.charAt(8)==que.charAt(2)){
                                if(question.charAt(3)==que.charAt(7)){
                                    //3+(1+2) = (1+2)+3 && 3+(1+2) = (2+1)+3
                                    if((question.charAt(1)==que.charAt(5) && question.charAt(5)==que.charAt(9)) || (question.charAt(5)==que.charAt(5) && question.charAt(1)==que.charAt(9))){
                                        return true;
                                    }
                                }
                            }
                        }
                    }

                    //que:1+2+3
                    if(!que.contains("(")){
                        //question:(2+1)+3
                        if(question.startsWith("(")){
                            //que:1+2+3 && 1*2-3
                            if(que.charAt(6)=='+' || que.charAt(6)=='-'){
                                if(que.charAt(6)==question.charAt(8) && que.charAt(8) == question.charAt(10)) {
                                    if(que.charAt(2) == question.charAt(3)) {
                                        //1*2+3 = (1*2)+3 && 2*1+3 = (1*2)+3
                                        if((que.charAt(0) == question.charAt(1) && que.charAt(4) == question.charAt(5)) || (que.charAt(4) == question.charAt(1) && que.charAt(0) == question.charAt(5))) {
                                            return true;
                                        }
                                    }
                                }
                            }
                            //que:1+2*3
                            else if(que.charAt(2)=='+' || que.charAt(2)=='-'){
                                if(que.charAt(0)==question.charAt(10) && que.charAt(2) == question.charAt(8)) {
                                    if(que.charAt(6) == question.charAt(3)){
                                        //3+1*2 = (1*2)+3 && 3+2*1 = (1*2)+3
                                        if((que.charAt(4) == question.charAt(1) && que.charAt(8) == question.charAt(5)) || (que.charAt(8) == question.charAt(1) && que.charAt(4) == question.charAt(5))){
                                            return true;
                                        }
                                    }
                                }
                            }
                        }
                        //question:1+(2+3)
                        if(question.charAt(4)=='('){
                            //que:1+2+3 && 1*2+3
                            if(que.charAt(6)=='+' || que.charAt(6)=='-') {
                                if (que.charAt(6) == question.charAt(2) && que.charAt(8) == question.charAt(0)) {
                                    if(que.charAt(2)==question.charAt(7)){
                                        //3*2+1 = 1+(2*3) && 3*2+1 = 1+(3*2)
                                        if((que.charAt(0)==question.charAt(5) && que.charAt(4)==question.charAt(9)) || (que.charAt(4)==question.charAt(5) && que.charAt(0)==question.charAt(9))){
                                            return true;
                                        }
                                    }
                                }
                            }
                            //que:1+2*3
                            else if(que.charAt(2)=='+' || que.charAt(2)=='-'){
                                if (que.charAt(0) == question.charAt(0) && que.charAt(2) == question.charAt(2)){
                                    if(que.charAt(6)==question.charAt(7)){
                                        //3+1*2 = 3+(1*2) && 3+2*1 = 3+(1*2)
                                        if((que.charAt(4)==question.charAt(5) && que.charAt(8)==question.charAt(9)) || (que.charAt(8)==question.charAt(5) && que.charAt(4)==question.charAt(9))){
                                            return true;
                                        }
                                    }
                                }
                            }
                        }
                        //question:1+2+3
                        if(!question.contains("(")){
                            //que:1+2+3 && 1*2+3
                            if(que.charAt(6)=='+' || que.charAt(6)=='-'){
                                //question:1+2+3 && 1*2+3
                                if(question.charAt(6)=='+' || question.charAt(6)=='-'){
                                    if(que.charAt(6)==question.charAt(6) && que.charAt(8)==question.charAt(8)){
                                        if(que.charAt(2)==question.charAt(2)){
                                            if((que.charAt(0)==question.charAt(0) && que.charAt(4)==question.charAt(4)) || (que.charAt(4)==question.charAt(0) && que.charAt(0)==question.charAt(4))){
                                                return true;
                                            }
                                        }
                                    }
                                }
                                //question:1+2*3
                                else if(question.charAt(2)=='+' || question.charAt(2)=='-'){
                                    if(que.charAt(6)==question.charAt(2) && que.charAt(8)==question.charAt(0)){
                                        if(que.charAt(2)==question.charAt(6)){
                                            if((que.charAt(0)==question.charAt(4) && que.charAt(4)==question.charAt(8)) || (que.charAt(4)==question.charAt(4) && que.charAt(0)==question.charAt(8))){
                                                return true;
                                            }
                                        }
                                    }
                                }
                            }
                            //que:1+2*3
                            if(que.charAt(2)=='+' || que.charAt(2)=='-'){
                                //question:1+2+3 && 1*2+3
                                if(question.charAt(6)=='+' || question.charAt(6)=='-'){
                                    if(que.charAt(0)==question.charAt(8) && que.charAt(2)==question.charAt(6)){
                                        if(que.charAt(6)==question.charAt(2)){
                                            if((que.charAt(4)==question.charAt(0) && que.charAt(8)==question.charAt(4)) || (que.charAt(8)==question.charAt(0) && que.charAt(4)==question.charAt(4))){
                                                return true;
                                            }
                                        }
                                    }
                                }
                                //question:1+2*3
                                else if(question.charAt(2)=='+' || question.charAt(2)=='-'){
                                    if(que.charAt(0)==question.charAt(0) && que.charAt(2)==question.charAt(2)){
                                        if(que.charAt(6)==question.charAt(6)){
                                            if((que.charAt(4)==question.charAt(4) && que.charAt(8)==question.charAt(8)) || (que.charAt(8)==question.charAt(4) && que.charAt(4)==question.charAt(8))){
                                                return true;
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        return false;
    }
}
