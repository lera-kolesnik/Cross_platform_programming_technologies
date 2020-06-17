package lab_2;
import java.util.Scanner;
public class Lab1 {

    public static double area(double a, double b, double c){
        double perimeter = (a+b+c)/2;
        return Math.sqrt(perimeter * (perimeter - a) * (perimeter - b) * (perimeter - c));
    }

    public static double computeArea(Point3d point1, Point3d point2, Point3d point3){
        if (point1.compare(point2) || point1.compare(point3) || point2.compare(point3)) {
            System.out.println("Площадь равна нулю, некоторые точки совпадают");
            return 0.0;
        }
        double a, b, c;
        a = point1.distanceTo(point2);
        b = point2.distanceTo(point3);
        c = point3.distanceTo(point1);
        return area(a, b, c);

    }
    public static void main (String[] args){
        Scanner in = new Scanner(System.in);
        System.out.println("Введите точку a");
        Point3d aPoint = new Point3d(in.nextDouble(), in.nextDouble(), in.nextDouble());
        System.out.println("Введите точку b");
        Point3d bPoint = new Point3d(in.nextDouble(), in.nextDouble(), in.nextDouble());
        System.out.println("Введите точку c");
        Point3d cPoint = new Point3d(in.nextDouble(), in.nextDouble(), in.nextDouble());
        System.out.println(computeArea(aPoint, bPoint, cPoint));
    }
}