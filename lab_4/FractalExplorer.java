package lab_4;
import java.awt.*;
import java.awt.geom.Rectangle2D;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.IOException;

public class FractalExplorer {
    private int displaySize;
    private JImageDisplay image; 
    private FractalGenerator fGenerator;
    private Rectangle2D.Double planeRange;
    private int rowsRemaning;
    JComboBox<FractalGenerator> comboBox;
    JButton resetBtn;
    JButton saveBtn;

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
        resetButton.setActionCommand("Reset");
        JButton saveButton = new JButton("Save Image");
        saveButton.setActionCommand("Save");
        comboBox = new JComboBox<FractalGenerator>();
        comboBox.addItem(new Mandelbrot());
        comboBox.addItem(new Tricorn());
        comboBox.addItem(new BurningShip());
        JPanel firstPan = new JPanel();
        JPanel secondPan = new JPanel();
        JLabel lbl = new JLabel("Fractal:");

        ActionHandler actionHandler = new ActionHandler();
        MouseHandler mouseHandler = new MouseHandler();
        resetButton.addActionListener(actionHandler);
        saveButton.addActionListener(actionHandler);
        image.addMouseListener(mouseHandler);

        firstPan.add(lbl, java.awt.BorderLayout.CENTER);
        firstPan.add(comboBox, java.awt.BorderLayout.CENTER);
        secondPan.add(resetButton, java.awt.BorderLayout.CENTER);
        secondPan.add(saveButton, java.awt.BorderLayout.CENTER);

        comboBox.addActionListener(actionHandler);

        frame.setLayout(new java.awt.BorderLayout());
        frame.add(image, java.awt.BorderLayout.CENTER);
        frame.add(firstPan, BorderLayout.NORTH);
        frame.add(secondPan, BorderLayout.SOUTH);
        
        // frame.add(resetButton, java.awt.BorderLayout.SOUTH);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        frame.pack();
        frame.setVisible(true);
        frame.setResizable(false);
    }

    public class ActionHandler implements ActionListener{
        public void actionPerformed(ActionEvent event){
            if (event.getActionCommand().equals("Reset")){
                fGenerator.getInitialRange(planeRange);
                drawFractal();
            }
            else if (event.getActionCommand().equals("Save")){
               JFileChooser chooser = new JFileChooser();
               FileFilter filter = new FileNameExtensionFilter("PNG Images", "png");
               chooser.setFileFilter(filter);
               chooser.setAcceptAllFileFilterUsed(false);
               int choose = chooser.showSaveDialog(image);
               if (choose == JFileChooser.APPROVE_OPTION){
                   try{
                       javax.imageio.ImageIO.write(image.getBufferedImage(), 
                       "png", chooser.getSelectedFile());
                    }
                    catch (NullPointerException | IOException firstExptn){
                        javax.swing.JOptionPane.showMessageDialog(
                            image, firstExptn.getMessage(),"Cannot save Image",
                            JOptionPane.ERROR_MESSAGE
                        );
                    }
                }
            } else {
                fGenerator = (FractalGenerator)comboBox.getSelectedItem();
                planeRange = new Rectangle2D.Double(0, 0, 0, 0);
                fGenerator.getInitialRange(planeRange);
                drawFractal();
            }
        }
    }
    
    public class MouseHandler extends MouseAdapter{
        @Override
        public void mouseClicked(MouseEvent e){
            if (rowsRemaning == 0){
            double xCoord = FractalGenerator.getCoord(planeRange.x, planeRange.x + 
                            planeRange.width, displaySize, e.getX());
            double yCoord = FractalGenerator.getCoord(planeRange.y, planeRange.y + 
                            planeRange.width, displaySize, e.getY());
            fGenerator.recenterAndZoomRange(planeRange, xCoord, yCoord, 0.5);
            drawFractal();
            }
        }
    }

         public void drawFractal(){
            //  enableUI(false);
            rowsRemaning = displaySize;
            for (int i = 0; i < displaySize; i++){
                FractalWorker rowDrawer = new FractalWorker(i);
                rowDrawer.execute();
            }
         }

         public static int HSBtoRGB(float hue, float saturation, float brightness) {
            int r = 0, g = 0, b = 0;
            if (saturation == 0) {
                r = g = b = (int) (brightness * 255.0f + 0.5f);
            } else {
                float h = (hue - (float)Math.floor(hue)) * 6.0f;
                float f = h - (float)java.lang.Math.floor(h);
                float p = brightness * (1.0f - saturation);
                float q = brightness * (1.0f - saturation * f);
                float t = brightness * (1.0f - (saturation * (1.0f - f)));
                switch ((int) h) {
                case 0:
                    r = (int) (brightness * 255.0f + 0.5f);
                    g = (int) (t * 255.0f + 0.5f);
                    b = (int) (p * 255.0f + 0.5f);
                    break;
                case 1:
                    r = (int) (q * 255.0f + 0.5f);
                    g = (int) (brightness * 255.0f + 0.5f);
                    b = (int) (p * 255.0f + 0.5f);
                    break;
                case 2:
                    r = (int) (p * 255.0f + 0.5f);
                    g = (int) (brightness * 255.0f + 0.5f);
                    b = (int) (t * 255.0f + 0.5f);
                    break;
                case 3:
                    r = (int) (p * 255.0f + 0.5f);
                    g = (int) (q * 255.0f + 0.5f);
                    b = (int) (brightness * 255.0f + 0.5f);
                    break;
                case 4:
                    r = (int) (t * 255.0f + 0.5f);
                    g = (int) (p * 255.0f + 0.5f);
                    b = (int) (brightness * 255.0f + 0.5f);
                    break;
                case 5:
                    r = (int) (brightness * 255.0f + 0.5f);
                    g = (int) (p * 255.0f + 0.5f);
                    b = (int) (q * 255.0f + 0.5f);
                    break;
                }
            }
            return 0xff000000 | (r << 16) | (g << 8) | (b << 0);
        }

         public class FractalWorker extends SwingWorker<Object, Object>{
             private int y;
             private int[] rgb;
             public FractalWorker(int y){
                 this.y = y;
             }
             public Object doInBackground(){
                 rgb = new int[displaySize];
                 for (int i = 0; i < displaySize; i++){
                     int nIteration = fGenerator.numIterations(
                         FractalGenerator.getCoord(
                             planeRange.x, planeRange.x + planeRange.width,displaySize, i),
                         FractalGenerator.getCoord(
                             planeRange.y, planeRange.y + planeRange.width,displaySize, y)
                    );
                    if (nIteration == -1)
                    rgb[i] = 0;
                    else{
                        double hue = 0.7f + (float)nIteration / 200f;
                        int rgbColor = HSBtoRGB((float)hue, 0.8f, 1f);
                        rgb[i] = rgbColor;
                    }
                 }
                 return 0;
             }
             public void done(){
                 for(int i = 0; i < displaySize; i++)
                 image.drawPixel(i, y, rgb[i]);
                 image.repaint(0, 0, y, displaySize, 1);
                 rowsRemaning--;
                 if(rowsRemaning == 0)
                  enableUI(true);
            }
        }
         
        public void enableUI(boolean var){
            saveBtn.setEnabled(var);
            resetBtn.setEnabled(var);
            comboBox.setEnabled(var);
        }
        public static void main(String[] args){
            FractalExplorer fractalExplorer = new FractalExplorer(600);
            fractalExplorer.createAndShowGUI();
            fractalExplorer.drawFractal();
        }
    }
