/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author lchong
 */
public class Stegano
{

    public static void main(String... args)
    {
        Stegano s = new Stegano();
        s.run(args);
    }

    public void run(String[] args)
    {
        String encodeOrDecode = args[0];
        String inputImageFileName = args[1];
        String outputImageFileName = args[2];
        String messageFileName = args[3];

        ImageInformation ii = new ImageInformation(inputImageFileName);
        CreateBitmap cb = new CreateBitmap(ii.getWidth(), ii.getHeight());
        Message m = new Message(messageFileName);

        System.out.println("Height " + ii.getHeight());
        System.out.println("Width " + ii.getWidth());

        for (int y = 1; y < ii.getHeight(); y++)
        {
            for (int x = 1; x < ii.getWidth(); x++)
            {
                int rgb = ii.getRgb(x, y);

                int nextBit = m.getNextBit();
                int newRgb = ((rgb >>> 1) << 1) | nextBit;
                
                cb.setPixel(x, y, newRgb);
            }
        }

        cb.writeToFile(outputImageFileName);
        System.out.println("Done");
    }
}
