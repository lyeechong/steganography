
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author lchong
 */
public class CreateBitmap
{

    BufferedImage image;

    public CreateBitmap(int width, int height)
    {
        assert width != 0;
        assert height != 0;
        image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
    }

    public void setPixel(int x, int y, int rgb)
    {
        image.setRGB(x, y, rgb);
        assert image.getRGB(x, y) == rgb;
    }

    public void writeToFile(String filename)
    {
        File outputfile = new File(filename);
        try
        {
            ImageIO.write(image, "BMP", outputfile);
        }
        catch (IOException ex)
        {
            System.out.println("Error saving bmp: " + ex.getMessage());
        }
    }
}
