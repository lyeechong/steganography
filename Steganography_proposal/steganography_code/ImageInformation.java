
/**
 *
 * @author lchong
 */
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

/**
 * 24 bit bmp
 * @author lchong
 */
public class ImageInformation
{

    BufferedImage img = null;
    int height;
    int width;

    public ImageInformation(String imageFileName)
    {

        try
        {            
            img = ImageIO.read(new File(imageFileName));

            height = img.getHeight();
            width = img.getWidth();
            
            assert height != 0;
            assert width != 0;
        }
        catch (IOException e)
        {
            System.out.println("Exception on loading image: " + e.getMessage());
        }
    }

    public int getHeight()
    {
        return height;
    }

    public int getWidth()
    {
        return width;
    }

    public int getRgb(int x, int y)
    {
        int rgb = img.getRGB(x, y);;
        return rgb;
    }

    public int getPixelRed(int x, int y)
    {
        int rgb = getRgb(x, y);
        int red = (rgb >> 16) & 0x000000FF;
        return red;
    }

    public int getPixelGreen(int x, int y)
    {
        int rgb = getRgb(x, y);
        int green  = (rgb >> 8) & 0x000000FF;
        return green;        
    }

    public int getPixelBlue(int x, int y)
    {
        int rgb = getRgb(x, y);
        int blue  = (rgb) & 0x000000FF;
        return blue;
    }
}
