/**
* Joseph Bowley
* Echo Client
* MessageSender.java
* CSCE 320
* 3/1/2015
* Language: Java
* Compiler: JDK
* Sources Consulted: Professor Hauser
* 
*/
package messagesender;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Joseph
 */
public class MessageController implements Runnable {
    //Initialize fields
    private MessageModel model;
    private MessageView view;
    
    private Socket socket;
    private InputStream in;
    private OutputStream out;
    private PrintWriter pr;
    
    private Thread worker;
    private byte[] b;
    
    /**
     * Initializes MessageController with a byte array, model, and view
     * @param m
     * @param v 
     */
    public MessageController(MessageModel m, MessageView v)
    {
        b = new byte[1024];
        model = m;
        view = v;
    }
    
    /**
     * Puts the IP address and port from view into the model
     * Creates a socket using the IP address and port from model
     * Creates input and output streams using the socket
     */
    public void setSocket()
    {
        model.setAddress(view.getIP(), view.getPort());
        try {
            socket = new Socket(model.getIP(), model.getPort() );
            in = socket.getInputStream();
            out = socket.getOutputStream();
        } catch (IOException ex) {
            Logger.getLogger(MessageController.class.getName()).log(Level.SEVERE, null, ex);
        }       
    }
    
    /**
     * Converts a string message to bytes
     * Sends the converted message to a server through socket
     * @param message 
     */
    public void sendMessage(String message)
    {
        byte[] msg;
        msg = message.getBytes();
        
        try {
            out.write(msg);
            //System.out.println(model.getLastSentMessage());
        } catch (IOException ex) {
            Logger.getLogger(MessageController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    /**
     * Overrides run method for Runnable class
     * While connected to a server reads in data as bytes and posts them to view
     */
    @Override
    public void run() 
    {       
        boolean connected = true;
        while(connected)
        {
            int len;
            try {
                //sees how long the message is
                len = in.read(b);
                //len will be -1 if disconnected from the server
                if(len > 0)
                {
                    //converts bytes to string then posts the message
                    String msg = new String(b, 0, len);
                    view.messageReceived(msg);
                    //System.out.println("message received");
                }
                else
                {
                    //tells the user that the connection to the server has been lost and closes the socket
                    view.messageReceived("Lost Connection...");
                    socket.close();
                    connected = false;
                }
            } catch (IOException ex) {
                Logger.getLogger(MessageController.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        }
    }
    
    /**
     * Creates a new Thread that will receive messages sent from the server
     */
    public void getMessages()
    {
        worker = new Thread(this);
        worker.start();
    }
    
}
