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
import java.awt.BorderLayout;
import javax.swing.JFrame;
import static javax.swing.JFrame.EXIT_ON_CLOSE;

/**
 *
 * @author Joseph
 */
public class MessageSender {
    
    public static void main(String args[]){
        //Create the Model
        MessageModel model = new MessageModel();
        //Create a view and controller
        MessageView view = new MessageView();
        MessageController controller = new MessageController(model, view);
        //add controller to model and view
        model.addController(controller);
        view.setController(controller);
        //Set up jframe with Jpanel 
        JFrame app = new JFrame();
        app.setLayout(new BorderLayout());
        app.add(view, BorderLayout.CENTER);
        app.setVisible(true);
        app.setDefaultCloseOperation(EXIT_ON_CLOSE);
        app.pack();
    }
}
