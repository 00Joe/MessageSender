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

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Joseph
 */
public class MessageModel {
    //Initialize fields
    private MessageController cont;
    private InetAddress address;
    private int port;
    private ArrayList<String> sent = new ArrayList<>();
    private ArrayList<String> received = new ArrayList<>();
    
    /**
     * Constructor that sets address to null and port to 0
     */
    public MessageModel()
    {
        address = null;
        port = 0;
    }
    
    /**
     * Constructor that initializes address and port
     * @param IPAddress
     * @param p 
     */
    public MessageModel(String IPAddress, int p)
    {
        try {
            address = InetAddress.getByName(IPAddress);
        } catch (UnknownHostException ex) {
            Logger.getLogger(MessageModel.class.getName()).log(Level.SEVERE, null, ex);
        }
        port = p;
    }
    
    /**
     * Adds a controller to MessageModel
     * @param c 
     */
    public void addController(MessageController c)
    {
        cont = c;
    }
    
    /**
     * Sets the address and port in MessageModel
     * @param IPAddress
     * @param p 
     */
    public void setAddress(String IPAddress, int p)
    {
        try {
            address = InetAddress.getByName(IPAddress);
        } catch (UnknownHostException ex) {
            Logger.getLogger(MessageModel.class.getName()).log(Level.SEVERE, null, ex);
        }
        port = p;
    }
    
    /**
     * Sets address
     * @param IPAddress 
     */
    public void setIP(InetAddress IPAddress)
    {
        address = IPAddress;
    }
    
    /**
     * Returns address
     * @return 
     */
    public InetAddress getIP()
    {
        return address;
    }
    
    /**
     * Sets port in MessageModel
     * @param p 
     */
    public void setPort(int p)
    {
        port = p;
    }
    
    /**
     * Returns port
     * @return 
     */
    public int getPort()
    {
        return port;
    }
    
    /**
     * Adds a String to the sent list
     * @param message 
     */
    public void messageSent(String message)
    {
        sent.add(message);
    }
    
    /**
     * Returns the last message sent as a String
     * @return 
     */
    public String getLastSentMessage()
    {
        return sent.get(sent.size()-1);
    }
    
    /**
     * Adds a String to the received list
     * @param message 
     */
    public void messageReceived(String message)
    {
        received.add(message);
    }
    
    /**
     * Returns the last message received as a String
     * @return 
     */
    public String getLastReceivedMessage()
    {
        return received.get(received.size()-1);
    }
}
