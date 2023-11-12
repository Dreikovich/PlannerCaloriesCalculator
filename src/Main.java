
public class Main {
    public static void main(String[] args) {
        Thread refreshThread = new Thread(() -> {
            RefreshData.startDataRefresh();
        });
        refreshThread.start();
        LoginGUI loginGUI = new LoginGUI();
        //Todo пофиксить баг с добавлением приема пищи когда он поторяется несколько раз в текстовом файле
        //Todo fix the issue when add the empty foods to the meal
    }
}