package password.password.password;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.layout.AnchorPane;

/**
 * This is the main class for running the GUI
 * @author Siddharth Sircar
 * @since June 17, 2024
 */
public class PasswordGenerator extends Application{

    /**
     * Creates a GUI for the password generator
     * @param s the main stage
     * @throws Exception if anything happens
     */
    @Override
    public void start(Stage s) throws Exception{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("PasswordController.fxml"));
        Scene sc = new Scene((AnchorPane) loader.load(), 447, 415);
        s.setScene(sc);
        s.setTitle("Password Generator");
        s.setResizable(false);
        s.show();
    }

    /**
     * The main method for running the GUI
     * @param args the command-line arguments
     */
    public static void main(String[] args){launch(args);}
}
