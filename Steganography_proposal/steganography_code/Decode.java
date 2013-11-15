
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author shen
 */
public class Decode
{

    String imageToDecodeFileName, outputMessageFileName;

    public Decode(String imageToDecodeFileName, String outputMessageFileName)
    {
        this.imageToDecodeFileName = imageToDecodeFileName;
        this.outputMessageFileName = outputMessageFileName;
    }

    public void decode()
    {
        ImageInformation ii = new ImageInformation(imageToDecodeFileName);
        CreateBitmap cb = new CreateBitmap(ii.getWidth(), ii.getHeight());

        List<Integer> bits = new ArrayList<>();
        List<Integer> bitDump = new ArrayList<>();
        loop:
        for (int y = 0; y < ii.getHeight(); y++)
        {
            for (int x = 0; x < ii.getWidth(); x++)
            {
                int rgb = ii.getRgb(x, y);
                int bit = rgb & 0x1;
                bitDump.add(bit);
                if (bitDump.size() == 8)
                {
                    if (bitDump.contains(1))
                    {
                        bits.addAll(bitDump);
                        bitDump.clear();
                    }
                    else
                    {
                        break loop;
                    }
                }
            }
        }
        
        FileOutputStream fos = null;
        try
        {
            fos = new FileOutputStream(outputMessageFileName);
        }
        catch (FileNotFoundException ex)
        {
            System.out.println("Error: " + ex.getMessage());
            return;
        }

        int count = 0;
        byte curr = 0;
        for (int b : bits)
        {
            count++;
            curr &= 127;
            curr |= (b << 7);

            if (count == 8)
            {
                byte[] tmp = new byte[1];
                tmp[0] = curr;
                try
                {
                    fos.write(tmp);
                }
                catch (IOException ex)
                {
                    System.out.println("Error: " + ex.getMessage());
                }
                b = 0;
                count = 0;
            }
            else
            {
                curr = (byte) (curr >>> 1);
            }

        }
    }
}
