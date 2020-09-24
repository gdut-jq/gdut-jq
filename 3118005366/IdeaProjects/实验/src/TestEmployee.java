public class TestEmployee{
    public static void main(String args[ ]){
//使用超类声明ref
        Employee ref; String out="";
//分别定义各子类
        Boss b=new Boss("Hohn","Smith",800.00);
        PieceWorker p=new PieceWorker("Bob","Lewis",2.5,200);
        HourlyWorker h=new HourlyWorker("Karen","price",13.75,40);
//使用子类分别实例化
        ref=b;
        out+=ref.toStrings()+" earned $"+ref.earnings()+"\n"+b.toStrings()+
                " earned $"+b.earnings()+"\n";
        System.out.print(out);
        ref=p;
        out+=ref.toStrings()+" earned $"+ref.earnings()+"\n"+p.toStrings()+
                " earned $"+p.earnings()+"\n";
        System.out.print(out);
        ref=h;
        out+=ref.toStrings()+" earned $"+ref.earnings()+"\n"+h.toStrings()+
                " earned $"+h.earnings()+"\n";
        System.out.print(out);
    }
}
