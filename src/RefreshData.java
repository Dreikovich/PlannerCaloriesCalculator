import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * This class is responsible for refreshing data
 */
public class RefreshData {
    /**
     * This field is used to store the refresh count
     */
    private static int refreshCount = 0;
    /**
     * This field is used to store the available foods
     */
    private static List<Food> availableFoods;
    /**
     * This field is used to store the meals
     */
    private static List<Meal> meals;
    /**
     * This field is used to store the maximum refresh count
     */
    private static final int MAX_REFRESH_COUNT = 1000;
    /**
     * This method checks the condition
     * @return true if the condition is met, false otherwise
     */
    public static boolean condition() {
        return refreshCount >= MAX_REFRESH_COUNT;
    }
    /**
     * This method starts the data refresh
     */
    public static void startDataRefresh() {
        ScheduledExecutorService service = Executors.newSingleThreadScheduledExecutor();
        Runnable runnable = () -> {
            refreshAvailableFoods();
            refreshCount++;
            writeToFilePeriodically();
            System.out.println("Data refreshed. The size of available data now is " + availableFoods.size());
            if (condition()) {
                service.shutdown();
            }
        };
        int initialDelay = 0;
        int period = 10;
        service.scheduleAtFixedRate(runnable, initialDelay, period, TimeUnit.SECONDS);
    }
    /**
     * This method writes data to the file periodically
     */
    public static void writeToFilePeriodically() {
        String fileName = "data/meal.txt";
        System.out.println("Writing data to file function entered");

        try {
            meals = PlannerGUI.getMeals();
            if(meals == null){
               return;
            }
            for (Meal meal : meals) {
                DataManager.writeTheMealToTheFile(fileName, meal);
                System.out.println("Data written to file");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /**
     * This method refreshes the available foods
     */
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
    /**
     * This method gets the available foods
     * @return available foods
     */
    public static List<Food> getAvailableFoods() {
        return availableFoods;
    }
    /**
     * This method parses the grams
     * @param value value
     * @return parsed grams
     */
    private static double parseGrams(String value) {
        if (value.endsWith("mg")) {
            return Double.parseDouble(value.substring(0, value.length() - 2)) / 1000.0;
        } else if (value.endsWith("g")) {
            return Double.parseDouble(value.substring(0, value.length() - 1));
        } else {
            return 0.0;
        }
    }
}