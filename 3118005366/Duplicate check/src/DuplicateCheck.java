import java.io.*;

public class DuplicateCheck {
    public static void main(String[] args)throws IOException {
        duplicateChecking(args[0],args[1],args[2]);
    }

    /*将论文转化为字符串*/
    public static String change(String s0) throws IOException {
        StringBuffer buffer=new StringBuffer();
        String s=null;
        String add=s0;
        BufferedReader bf=new BufferedReader(new FileReader(add));
        while((s=bf.readLine())!=null){
            buffer.append(s.trim());
        }
        String str=buffer.toString();//将论文转化为字符串
        String string=str.replaceAll("\\s*","");//除去字符串中所有空白字符，包括空格、换行等。
        return string;
    }

    //论文查重算法
    public static void duplicateChecking(String sX,String s,String sOut) throws IOException {
        float repetRate;//重复率
        float RN=0;//每句话的重复率总和
        float R=0;//每句话的重复率

        String[] paper=change(s).split("。");//将论文按句号拆分为字符串数组
        String[] paperX=change(sX).split("。");//将论文按句号拆分为字符串数组

        //判断论文句子的重复性，如果重复，这计算其重复字数
        for(int i=0;i<paper.length;i++){
            paper[i]=paper[i].replaceAll("[^\u4E00-\u9FA5]","");//除去句子中的所有标点符号，只保留中文。
            for(int j=0;j<paperX.length;j++){
                paperX[j]=paperX[j].replaceAll("[^\u4E00-\u9FA5]","");//除去句子中的所有标点符号，只保留中文。
                float rs=sentenceDuplication(paper[i],paperX[j]);
                if(R<rs){
                    R=rs;
                }
            }
            RN=RN+R;
        }
        repetRate=RN/paper.length;

        repetRate=(float) ((int)(repetRate*100))/100;//保留两位小数

        System.out.println("相似度："+repetRate);

        out(repetRate,sOut);
    }

    //句子查重算法
    public static float sentenceDuplication(String str, String strX){

        //重复率
        float R;
        //重复字数
        int repetNumber = 0;

        /*将字符串转化为字符数组*/
        char[] chars=str.toCharArray();
        char[] charsX=strX.toCharArray();

        /*字符比较*/
        for(int i=0;i<chars.length;i++){
            for(int j=0;j<charsX.length;j++){
                if(chars[i]==charsX[j]){
                    charsX[j]='\0';//把以匹配的字符转化为非中文字符，防止二次匹配
                    repetNumber++;
                    break;
                }
            }
        }
        R=(float)repetNumber/(float) chars.length;

        //返回一句话的重复率
        return R;
    }

    //结果以文件形式输出
    public static void out(float result,String resultadd) throws IOException {
        File f=new File(resultadd);

        //新建txt文件
        f.createNewFile();
        FileWriter fw=new FileWriter(f);
        fw.write("相似度："+result);
        fw.close();
    }
}
