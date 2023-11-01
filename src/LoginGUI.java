import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginGUI {
    private Frame frame;
    private Label usernameLabel, passwordLabel;
    private TextField usernameTextField, passwordTextField;
    private Button loginButton;

    public LoginGUI() {
        frame = new Frame("Login Page");
        usernameLabel = new Label("Username:");
        passwordLabel = new Label("Password:");
        usernameTextField = new TextField();
        passwordTextField = new TextField();
        loginButton = new Button("Login");

        // Set password field to hide input characters
        passwordTextField.setEchoChar('*');

        // Set layout manager to arrange components
        frame.setLayout(new GridLayout(3, 2));

        // Add components to the frame
        frame.add(usernameLabel);
        frame.add(usernameTextField);
        frame.add(passwordLabel);
        frame.add(passwordTextField);
        frame.add(new Label()); // Empty label for spacing
        frame.add(loginButton);

        // Set up event handling for the login button
        loginButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Handle login button click event
                String username = usernameTextField.getText();
                String password = passwordTextField.getText();
                LoginController loginController = new LoginController();
                boolean isAuthenticated = loginController.authenticateUser(username, password);
                if (isAuthenticated) {
                    System.out.println("User authenticated successfully");
                    loginController.openPlannerGui();
                    closeLoginWindowAfterSuccessfulAuthentication(frame);

                } else {
                    System.out.println("Authentication failed. Invalid username or password.");
                }
            }
        });

        // Set frame properties
        frame.setSize(300, 150);
        frame.setVisible(true);
        frame.addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent windowEvent) {
                System.exit(0);
            }
        });
    }

    public void closeLoginWindowAfterSuccessfulAuthentication(Frame loginFrame){
        loginFrame.dispose();
    }

    public static void main(String[] args) {
        new LoginGUI();
    }
}