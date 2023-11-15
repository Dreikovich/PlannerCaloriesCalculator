import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * This class is used to format data for writing to file
 */
public class DataHelper {
    /**
     * This method formats the food data
     * @param food food object
     * @return formatted food data
     */
    public static String formatFoodData(Food food) {
        return food.getFoodItem() + ":" + food.getCalories() + ":" +
                food.getFats() + ":" + food.getCarbs() + ":" +
                food.getProtein() + ":" + food.getFiber() + ":" +
                food.getSugar() + ":" + food.getSodium();
    }
    /**
     * This method formats the meal data
     * @param meal meal object
     * @return formatted meal data
     */
    public static String formatMealData(Meal meal) {
        StringBuilder data = new StringBuilder();
        data.append(meal.getName()).append(":");
        for (Food food : meal.getMeal()) {
            data.append(food.getFoodItem()).append(",");
        }
        return data.toString();
    }
    /**
     * This method gets the current time
     * @return current time
     */
    public static String getCurrentTime(){
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        return dtf.format(now);
    }
}
