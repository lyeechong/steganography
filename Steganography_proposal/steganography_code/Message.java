
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Queue;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author lchong
 */
public class Message
{

    Queue<Integer> buffer;
    byte[] bytes;
    boolean done;
    FileInputStream in;

    public Message(String messageFileName)
    {
        try
        {
            in = new FileInputStream(messageFileName);
        }
        catch (FileNotFoundException ex)
        {
            System.out.println("Error on loading message file: " + ex.getMessage());
        }
        done = false;
        buffer = new LinkedList<>();

    }

    /**
     *
     * @return either 1 or 0
     */
    public int getNextBit()
    {
        if (buffer.isEmpty())
        {
            reloadBuffer();
        }

        int next = buffer.poll();

        return next;
    }

    public boolean isDone()
    {
        return done;
    }

    private void reloadBuffer()
    {

        byte[] b = new byte[1];
        try
        {
            if (in.available() == 0)
            {
                done = true;
                for (int i = 0; i < 8; i++)
                {
                    buffer.add(0);
                }
            }
            in.read(b);
        }
        catch (IOException ex)
        {
            System.out.println("Error reading byte from the input message: " + ex.getMessage());
        }
        for (byte by : b)
        {
            for (int i = 0; i < 8; i++)
            {
                buffer.add(by & 0x1);
                by = (byte) (by >>> 1);
            }
        }
    }
}
