/**
 * Main
 */
public class Main {
    /**
     * main
     * This method is used to start the program
     * @param args
     */
    public static void main(String[] args) {
        Thread refreshThread = new Thread(() -> {
            RefreshData.startDataRefresh();
        });
        refreshThread.start();
        PlannerGUI plannerGUI = new PlannerGUI();
    }
}