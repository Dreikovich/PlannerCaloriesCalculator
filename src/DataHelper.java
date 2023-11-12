import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DataHelper {
    public static String formatFoodData(Food food) {
        return food.getFoodItem() + ":" + food.getCalories() + ":" +
                food.getFats() + ":" + food.getCarbs() + ":" +
                food.getProtein() + ":" + food.getFiber() + ":" +
                food.getSugar() + ":" + food.getSodium();
    }

    public static String formatMealData(Meal meal) {
        StringBuilder data = new StringBuilder();
        data.append(meal.getName()).append(":");
        for (Food food : meal.getMeal()) {
            data.append(food.getFoodItem()).append(",");
        }
        return data.toString();
    }

    public static String getCurrentTime(){
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        return dtf.format(now);
    }
}
