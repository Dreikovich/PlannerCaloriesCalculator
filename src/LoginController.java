

public class LoginController {
    private static final String FILE_NAME = "data/userData.txt";
    public boolean authenticateUser(String login, String password) {
        String data = DataManager.readFromFile(FILE_NAME);
        if (!data.isEmpty()) {
            String[] users = data.split("\n");
            for (String user : users) {
                String[] userInfo = user.split(":");
                if (userInfo[2].equals(login) && userInfo[3].equals(password)) {
                    return true;
                }
            }
        }
        return false;
    }
    public void openPlannerGui() {
        PlannerGUI plannerGUI = new PlannerGUI();
    }
}
