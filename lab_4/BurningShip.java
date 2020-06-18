package lab_4;
import java.awt.geom.Rectangle2D;

public class BurningShip extends FractalGenerator{
    public void getInitialRange(Rectangle2D.Double placeRange){
        placeRange.x = -2;
        placeRange.y = -2;
        placeRange.height= 4;
        placeRange.width = 4;
    }
    public static final int MAX_ITERATIONS = 2000;
    public int numIterations(double x, double y){
        double real = x;
        double imaginary = y; 
        int iteration = 0;
        while(iteration < MAX_ITERATIONS){
            iteration++;
            double secondReal = Math.abs(x * x) - Math.abs(y * y) + real;
            double secondImaginary = 2 * Math.abs(x * y) + imaginary;
            x = secondReal;
            y = secondImaginary;
            if ((x * x + y * y) > 4)
            break;
        }
        if (iteration == MAX_ITERATIONS)
        return -1;
        return iteration;
    }
    public String toString(){
        return "Burning Ship";
    }
}