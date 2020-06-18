package lab_4;
import java.awt.*;
import java.awt.geom.Rectangle2D;
import javax.swing.*;

import com.apple.laf.AquaTabbedPaneCopyFromBasicUI.MouseHandler;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class FractalExplorer {
    private int displaySize;
    private JImageDisplay image; 
    private FractalGenerator fGenerator;
    private Rectangle2D.Double planeRange;

    public FractalExplorer(int displaySize){
        this.displaySize = displaySize;
        this.fGenerator = new Mandelbrot();
        this.planeRange = new Rectangle2D.Double(0, 0, 0, 0);
        fGenerator.getInitialRange(this.planeRange);
    }
    public void createAndShowGUI(){
        JFrame frame = new JFrame("Fractal Explorer");
        image = new JImageDisplay(displaySize, displaySize);
        JButton resetButton = new JButton("Reset");

        ActionHandler actionHandler = new ActionHandler();
        MouseHandler mouseHandler = new MouseHandler();
        resetButton.addActionListener(actionHandler);
        image.addMouseListener(mouseHandler);

        frame.setLayout(new java.awt.BorderLayout());
        frame.add(image, java.awt.BorderLayout.CENTER);
        frame.add(resetButton, java.awt.BorderLayout.SOUTH);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        frame.pack();
        frame.setVisible(true);
        frame.setResizable(false);
    }

    public class ActionHandler implements ActionListener{
        public void actionPerformed(ActionEvent event){
            fGenerator.getInitialRange(planeRange); 
            drawFractal();
        }
    }

    public class MouseHandler extends MouseAdapter{
        @Override
        public void mouseClicked(MouseEvent e){
            double xCoord = FractalGenerator.getCoord(planeRange.x, planeRange.x + 
                            planeRange.width, displaySize, e.getX());
            double yCoord = FractalGenerator.getCoord(planeRange.y, planeRange.y + 
                            planeRange.width, displaySize, e.getY());
            fGenerator.recenterAndZoomRange(planeRange, xCoord, yCoord, 0.5);
            drawFractal();
        }
    }

    public void drawFractal(){
        for(int x = 0; x < displaySize; x++){
            for (int y = 0; y < displaySize; y++){
                int numIt = fGenerator.numIterations(FractalGenerator.getCoord(
                    planeRange.x, planeRange.x + planeRange.width, displaySize, x),
                    FractalGenerator.getCoord(planeRange.y, planeRange.y + 
                    planeRange.width, displaySize, y));
                    if (numIt == -1)
                        image.drawPixel(x, y, 0);
                    else{
                        double hue = 0.7f + (float)numIt / 200f;
                        int rgbColor = Color.HSBtoRGB((float)hue, 0.8f, 0.8f); 
                        image.drawPixel(x, y, rgbColor);
                    }
                }
            }
            image.repaint();
        }
        public static void main(String[] args){
            FractalExplorer fractalExplorer = new FractalExplorer(600);
            fractalExplorer.createAndShowGUI();
            fractalExplorer.drawFractal();
        }
    }