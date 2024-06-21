package password.password.password;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Label;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.scene.input.Clipboard;
import javafx.scene.input.ClipboardContent;

import java.security.SecureRandom;
import java.util.Optional;

/**
 * This is the controller that handles the code for the FXML file
 * @author Siddharth Sircar
 * @since June 17, 2024
 */
public class PasswordController {
    /**
     * Should capital letters be allowed?
     */
    @FXML private CheckBox capital,
    /**
     * Should numbers be allowed?
     */
    numbers,
    /**
     * Should symbols be allowed?
     */
    symbol;

    /**
     * Handles copying to clipboard
     */
    @FXML private Button copy,
    /**
     * Handles resetting the system
     */
    reset;

    /**
     * Displays the password if properly generated
     */
    @FXML private Label password;

    /**
     * Obtains the length of the password
     */
    @FXML private TextField passLength;

    /**
     * Generates the password and displays it to a label
     */
    @FXML private void makePassword(){
        int length = 0;
        try{
            length = Integer.parseInt(passLength.getText());
        }catch (Exception e){
            Alert a = new Alert(AlertType.ERROR);
            a.setHeaderText("Error");
            a.setContentText("Please enter a valid integer between 8 and 15.");
            a.showAndWait();
            return;
        }
        if (length < 8 || length > 15){
            Alert a = new Alert(AlertType.ERROR);
            a.setHeaderText("Error");
            a.setContentText("Please enter a valid integer between 8 and 15.");
            a.showAndWait();
            return;
        }
        passLength.setDisable(true);
        password.setText(generatePassword(length, numbers.isSelected(), 
                                          capital.isSelected(), symbol.isSelected()));
        reset.setDisable(false);
        copy.setDisable(false);
    }

    /**
     * Copies the password to the clipboard
     */
    @FXML private void copyToClip(){
        Alert a = new Alert(AlertType.CONFIRMATION);
        a.setHeaderText("Confirm");
        a.setContentText("Are you sure you wish to copy this to the clipboard?");
        a.getButtonTypes().addAll(ButtonType.YES, ButtonType.NO);
        Optional<ButtonType> res = a.showAndWait();
        if (res.isPresent() && res.get() == ButtonType.YES){
            Clipboard cb = Clipboard.getSystemClipboard();
            ClipboardContent cc = new ClipboardContent();
            cc.putString(password.getText());
            cb.setContent(cc);
        }
    }

    /**
     * Resets the system if necessary
     */
    @FXML private void resetSystem(){
        capital.setSelected(false);
        numbers.setSelected(false);
        symbol.setSelected(false);
        passLength.setText("");
        password.setText("");
        reset.setDisable(true);
        copy.setDisable(true);
        passLength.setDisable(false);
    }

    /**
     * Generates a password given the conditions
     * @param length the length of the password
     * @param n if numbers are to be included
     * @param u if capital letters are to be included
     * @param s if symbols are to be included
     * @return a password of the given length
     */
    private String generatePassword(int length, boolean n, boolean u, boolean s) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < length; i++)
            sb.append((char) randomGenerate(n, u, s));
        return sb.toString();
    }

    /**
     * This creates a randomly generated character for the password
     * @param n if numbers are to be included
     * @param u if capital letters are to be included
     * @param s if symbols are to be included
     * @return a character randomly generated
     */
    private int randomGenerate(boolean n, boolean u, boolean s){
        SecureRandom r = new SecureRandom();
        int place = r.nextInt(94) + 33;
        if (!s)
            if (place <= 47 || place >= 58 && place <= 64 || place >= 91 && place <= 96
                    || place >= 123)
                return randomGenerate(n, u, s);
        if (!u)
            if (place >= 65 && place <= 90)
                return randomGenerate(n, u, s);
        if (!n)
            if (place >= 48 && place <= 57)
                return randomGenerate(n, u, s);
        return place;
    }
}
