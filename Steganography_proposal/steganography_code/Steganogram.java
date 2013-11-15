/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author lchong
 */
public class Steganogram
{

    public static void main(String... args)
    {
        Steganogram s = new Steganogram();
        s.run(args);
    }

    public void run(String[] args)
    {
        String encodeOrDecode = args[0];

        if (encodeOrDecode.equals("e"))
        {
            String inputImageFileName = args[1];
            String outputImageFileName = args[2];
            String messageFileName = args[3];
            Encode e = new Encode(inputImageFileName, outputImageFileName, messageFileName);
            e.encode();
        }
        else if (encodeOrDecode.equals("d"))
        {
            String imageToDecodeFileName = args[1];
            String outputMessageFileName = args[2];
            Decode d = new Decode(imageToDecodeFileName, outputMessageFileName);
            d.decode();
        }
        else
        {
            assert false;
            return;
        }

    }
}
