import java.util.List;

public class MealCalculator {
    public static int calculateCaloriesInOneMeal(List<Food> foods) {
       int sum = 0;
       for(Food food : foods){
           sum+= food.getCalories();
       }
       return sum;
    }

    public static double calculateCaloriesInAllMeal(List<Meal> meals) {
        int sum = 0;
        for(Meal meal : meals){
            sum+= calculateCaloriesInOneMeal(meal.getMeal());
        }
        return sum;
    }
}
