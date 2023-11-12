import javax.swing.JOptionPane;

public class AlertMessage {

    public AlertMessage(String message) {
        // Create a dialog box with an information message
        JOptionPane.showMessageDialog(null, message, "Information", JOptionPane.INFORMATION_MESSAGE);
    }

}