
public class Main {
    public static void main(String[] args) {
        Thread dataRefreshThread = new Thread(() -> RefreshData.startDataRefresh());
        dataRefreshThread.start();
        LoginGUI loginGUI = new LoginGUI();

    }
}