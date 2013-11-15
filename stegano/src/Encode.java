/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author shen
 */
public class Encode
{

    String inputImageFileName, outputImageFileName, messageFileName;

    public Encode(String inputImageFileName, String outputImageFileName, String messageFileName)
    {
        this.inputImageFileName = inputImageFileName;
        this.outputImageFileName = outputImageFileName;
        this.messageFileName = messageFileName;
    }

    public void encode()
    {
        ImageInformation ii = new ImageInformation(inputImageFileName);
        CreateBitmap cb = new CreateBitmap(ii.getWidth(), ii.getHeight());
        Message m = new Message(messageFileName);

        int last8bitCount = 0;
        for (int y = 0; y < ii.getHeight(); y++)
        {
            for (int x = 0; x < ii.getWidth(); x++)
            {

                int rgb = ii.getRgb(x, y);
                int newRgb;
                if (last8bitCount < 8)
                {
                    int nextBit = m.getNextBit();

                    if (m.isDone())
                    {
                        nextBit = 0;
                        last8bitCount++;
                    }

                    newRgb = ((rgb >>> 1) << 1) | nextBit;
                }
                else
                {
                    newRgb = rgb;
                }
                cb.setPixel(x, y, newRgb);
            }
        }

        cb.writeToFile(outputImageFileName);
    }
}
