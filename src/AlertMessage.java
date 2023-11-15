import javax.swing.JOptionPane;

/**
 * AlertMessage
 * This class is used to display an alert message
 */
public class AlertMessage {
    /**
     * AlertMessage
     * This constructor creates a dialog box with an information message
     * @param message
     */
    public AlertMessage(String message) {
        // Create a dialog box with an information message
        JOptionPane.showMessageDialog(null, message, "Information", JOptionPane.INFORMATION_MESSAGE);
    }

}