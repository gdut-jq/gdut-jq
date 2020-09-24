public class Shape {
    private static String name="矩形";
    public static void GetArea(String Name,double r,double length,double width){
        if(Name=="圆形"){
            double t=Math.PI*r*r;
            System.out.println(t);
        }
        else if(Name=="矩形")System.out.println(length*width);
    }
    public static void GetLength(String Name,double r,double length,double width){
        if(Name=="圆形"){
            double t=Math.PI*r*2;
            System.out.println(t);
        }
        else if(Name=="矩形"){
            System.out.println(2*length+2*width);
        }
    }
    public static void main(String[] args) {
        GetArea(name,Circle.radius,Rectangle.length,Rectangle.width);
        GetLength(name,Circle.radius,Rectangle.length,Rectangle.width);
    }
}
class Circle{
    public static double radius=1;
}
class Rectangle{
    public static double length=2;
    public static double width=1;
}