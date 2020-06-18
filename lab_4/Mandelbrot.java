package lab_4;
import java.awt.geom.Rectangle2D;

public class Mandelbrot extends FractalGenerator {
    public void getInitialRange(Rectangle2D.Double initRange){
        initRange.x = -2;
        initRange.y = -1.5;
        initRange.height= 3;
        initRange.width = 3;
    }
    public static final int MAX_ITERATIONS = 2000;
    public int numIterations(double x, double y){
        double real = x;
        double imaginary = y;
        int iteration = 0;
        while (iteration < MAX_ITERATIONS){
            iteration++;
            double secondReal = x * x - y * y + real;
            double secondImaginary = 2 * x * y + imaginary;
            x = secondReal;
            y = secondImaginary;
            if ((x * x + y * y) > 4)
            break;
        }
        if (iteration == MAX_ITERATIONS)
        return -1;
        return iteration;
    }
}