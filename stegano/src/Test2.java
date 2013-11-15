
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author shen
 */
public class Test2
{

    public static void main(String[] args) throws FileNotFoundException, IOException
    {
        FileInputStream in;
        in = new FileInputStream("message.txt");

        ArrayList<Integer> al = new ArrayList<>();

        while (in.available() != 0)
        {
            byte[] b = new byte[1];
            in.read(b);
            System.out.println(b[0]);
            for (byte by : b)
            {
                for (int i = 0; i < 8; i++)
                {
                    System.out.print(by & 0x1);
                    al.add(by & 0x1);
                    by = (byte) (by >>> 1);
                }
                System.out.print(" ");
            }

        }
        System.out.println("");
        System.out.println("---------");

        FileOutputStream fos = new FileOutputStream("outputTest.txt");
        
        System.out.println(al.toString());
        
        int count = 0;
        byte curr = 0;
        for (int b : al)
        {
            count++;
            curr &= 127;
            curr |= (b << 7);
            
            
            //System.out.println("curr1: " + curr);
            
            if (count == 8)
            {
                byte[] tmp = new byte[1];
                tmp[0] = curr;
                System.out.println(curr);
                fos.write(tmp);
                b = 0;                
                count = 0;
                //return;
            }
            else
            {
                curr = (byte) (curr >>> 1);
                //System.out.println("curr2: " + curr);
            }
            
        }

        
        
    }
}
