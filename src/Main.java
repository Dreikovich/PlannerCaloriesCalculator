
public class Main {
    public static void main(String[] args) {
        Thread refreshThread = new Thread(() -> {
            RefreshData.startDataRefresh();
        });
        refreshThread.start();
        LoginGUI loginGUI = new LoginGUI();
    }
}