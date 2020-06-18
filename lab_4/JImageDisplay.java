package lab_4;
import javax.swing.JComponent;
import java.awt.*;
import java.awt.image.BufferedImage;

public class JImageDisplay extends JComponent{
    private BufferedImage image;
    public JImageDisplay(int width, int heigth){
        image = new BufferedImage(width, heigth,BufferedImage.TYPE_INT_RGB);
        Dimension dimension = new Dimension(width, heigth);
        super.setPreferredSize(dimension);
    }
    @Override
    public void paintComponent(Graphics g){
        g.drawImage(image, 0, 0,  image.getWidth(), image.getHeight(), null);
    }
    public void clearImage(){
        for(int i = 0; i < image.getHeight(); i++){
            for(int j = 0; i < image.getWidth(); i++){
                image.setRGB(j,i,0);
        }
    }
}
    public void drawPixel(int x, int y, int rgbColor){
        image.setRGB(x, y, rgbColor);
    }
}

    