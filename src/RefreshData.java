import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class RefreshData {
    private static int refreshCount = 0;
    private static List<Food> availableFoods;
    private static boolean dataLoaded = false;
    private static final int MAX_REFRESH_COUNT = 1000;
    public static boolean condition() {
        return refreshCount >= MAX_REFRESH_COUNT;
    }
    public static void startDataRefresh() {
        ScheduledExecutorService service = Executors.newSingleThreadScheduledExecutor();
        Runnable runnable = () -> {
            refreshAvailableFoods();
            refreshCount++;
            System.out.println("Data refreshed. The size of available data now is " + availableFoods.size());
            if (condition()) {
                service.shutdown();
            }
        };
        int initialDelay = 0;
        int period = 10;
        service.scheduleAtFixedRate(runnable, initialDelay, period, TimeUnit.SECONDS);
    }

    public static synchronized void refreshAvailableFoods() {
        try {
            List<String> lines = Files.readAllLines(Paths.get("data/food.txt"));
            Collections.sort(lines);
            availableFoods = lines.stream()
                    .map(line -> {
                        String[] parts = line.split(":");
                        return new Food(parts[0],
                                Integer.parseInt(parts[1]),
                                parseGrams(parts[2].trim()),
                                parseGrams(parts[3].trim()),
                                parseGrams(parts[4].trim()),
                                parseGrams(parts[5].trim()),
                                parseGrams(parts[6].trim()),
                                parseGrams(parts[7].trim()));
                    })
                    .collect(Collectors.toList());
        } catch (IOException e) {
            System.out.println("Error reading food data: " + e.getMessage());
        }
    }

    public static List<Food> getAvailableFoods() {
        return availableFoods;
    }

    private static double parseGrams(String value) {
        if (value.endsWith("mg")) {
            return Double.parseDouble(value.substring(0, value.length() - 2)) / 1000.0;
        } else if (value.endsWith("g")) {
            return Double.parseDouble(value.substring(0, value.length() - 1));
        } else {
            return 0.0;
        }
    }

    public static boolean isDataLoaded() {
        return dataLoaded;
    }


}