//Employee超类的定义
class Employee{
    private String firstName;
    private String lastName;
    public Employee(String first,String last){
        firstName=first;
        lastName=last;
    }
    public String getEmployeeName(){
        return firstName; }
    public String getLastName(){
        return lastName; }
    public String toStrings(){
        return firstName+' '+lastName; }
    public double earnings(){
        System.out.println("Employ's salary is 0.0");
        return 0.0;}
}
//定义Boss类，为Employee的子类
class Boss extends Employee{
    private double weeklySalary;
    public Boss(String first,String last,double s){
        super(first,last);
        setWeeklySalary(s);}
    public void setWeeklySalary(double s){
        weeklySalary=(s>0?s:0);}
    public double earnings(){
        return weeklySalary; }
    public String toStrings(){
        return "Boss: "+super.toStrings();}
}
//定义PieceWorker类，为Employee的子类
class PieceWorker extends Employee{
    private double wagePiece;
    private int quantity;
    public PieceWorker(String first,String last,double w,int q){
        super(first,last);
        setWage(w);
        setQuantity(q);}
    public void setWage(double w){
        wagePiece=(w>0?w:0); }
    public void setQuantity(int q){
        quantity=(q>0?q:0);}
    public double earnings(){
        return quantity*wagePiece; }
    public String toStrings(){
        return "PieceWoeker: "+super.toStrings();}
}
//定义HourlyWorker类，为Employee的子类
class HourlyWorker extends Employee{
    private double wage;
    private double hours;
    public HourlyWorker(String first,String last ,double w,double h) {
        super(first,last);
        setWage(w);
        setHours(h);}
    public void setWage (double w){
        wage=(w>0?w:0); }
    public void setHours(double h){
        hours=(h>=0&&h<168?h:0); }
    public double earnings(){
        return wage*hours;}
    public String toStrings(){
        return "HourlyWorker: "+super.toStrings(); }
}
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
