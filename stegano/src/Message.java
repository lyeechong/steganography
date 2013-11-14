
import java.io.File;
import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

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
    Scanner message;
    Queue<Integer> buffer;
    boolean done;

    public Message(String messageFileName)
    {
        try
        {
            message = new Scanner(new File(messageFileName));
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
        if(buffer.isEmpty())
        {
            reloadBuffer();
        }
        
        byte next = buffer.poll();
        
        return 0;
    }
    
    public boolean isDone()
    {
        return done;
    }
    
    public void reloadBuffer()
    {
        message.nextByte();
        
    }
}
