/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author shen
 */
public class Test
{

    public static void main(String[] args)
    {
        Message m = new Message("message.txt");
        int count = 0;
        while (!m.isDone())
        {
            int bit = m.getNextBit();
            System.out.print(bit);
            count++;
            if (count == 8)
            {
                System.out.print(" ");
                count = 0;
            }
        }
    }
}
