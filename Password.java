import java.awt.datatransfer.*;
import java.awt.event.*;
import java.awt.*;
import javax.swing.*;
import java.security.SecureRandom;

public class Password extends JFrame {
    public Password() {
        JFrame f = new JFrame("Password Generator");
        f.setSize(437, 300);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setVisible(true);
        f.setResizable(false);
        f.setLayout(null);
        JCheckBox upper = new JCheckBox("Add upper case letters?"), symbols = new JCheckBox("Add symbols?"), numbers = new JCheckBox("Add numbers?");
        JButton generate = new JButton("Generate Password");
        JLabel jLength = new JLabel("Enter length (between 8 and 15): ");
        JTextField tf = new JTextField();
        jLength.setBounds(12, 57, 254, 20);
        f.add(jLength);
        tf.setBounds(244, 57, 125, 27);
        f.add(tf);
        upper.setBounds(38, 109, 200, 24);
        f.add(upper);
        symbols.setBounds(38, 149, 200, 24);
        f.add(symbols);
        numbers.setBounds(38, 189, 200, 24);
        f.add(numbers);
        generate.setBounds(150, 229, 200, 24);
        f.add(generate);
        generate.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    int length = Integer.parseInt(tf.getText());
                    if (length >= 8 && length <= 15) {
                        StringBuilder password = new StringBuilder("");
                        boolean n = false, u = false, s = false;
                        if (numbers.isSelected())
                            n = true;
                        if (symbols.isSelected())
                            s = true;
                        if (upper.isSelected())
                            u = true;
                        char[] p = generatePassword(length, n, u, s);
                        for (int i = 0; i < p.length; i++)
                            password.append(p[i]);
                        String h = password.toString();
                        int x = JOptionPane.showConfirmDialog(f, "You password is " + h + ". Copy to clipboard?");
                        if (x == JOptionPane.YES_OPTION) {
                            StringSelection g = new StringSelection(h);
                            Clipboard c = Toolkit.getDefaultToolkit().getSystemClipboard();
                            c.setContents(g, null);
                            JOptionPane.showMessageDialog(f, "Password has been copied to clipboard");
                        }
                    } else
                        JOptionPane.showMessageDialog(f, "Password length must be between 8 and 15 characters", "Alert", JOPtionPane.WARNING_MESSAGE);
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(f, "Please enter an integer", "Alert", JOPtionPane.WARNING_MESSAGE);
                } catch (NullPointerException ex){
                    JOptionPane.showMessageDialog(f, "Please enter an integer", "Alert", JOPtionPane.WARNING_MESSAGE);
                }
            }
        });
    }

    private static char[] generatePassword(int length, boolean n, boolean u, boolean s) {
        char[] pass = new char[length];
        for (int i = 0; i < length; i++)
            pass[i] = (char) randomGenerate(n, u, s);
        return pass;
    }

    private static int randomGenerate(boolean n, boolean u, boolean s) {
        SecureRandom r = new SecureRandom();
        int place = r.nextInt(94) + 33;
        if (!s)
            if (place >= 33 && place <= 47 || place >= 58 && place <= 64 || place >= 91 && place <= 96
                    || place >= 123 && place <= 126)
                return randomGenerate(n, u, s);
        if (!u)
            if (place >= 65 && place <= 90)
                return randomGenerate(n, u, s);
        if (!n)
            if (place >= 48 && place <= 57)
                return randomGenerate(n, u, s);
        return place;
    }

    public static void main(String[] args) {
        new Password();
    }
}
