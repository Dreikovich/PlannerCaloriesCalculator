import java.util.List;

public class MealCalculator {
    public static int calculateCaloriesInOneMeal(List<Food> foods) {
       int sum = 0;
       for(Food food : foods){
           sum+= food.getCalories();
       }
       return sum;
    }

    public static int calculateCaloriesInAllMeal(List<Meal> meals) {
        int sum = 0;
        for(Meal meal : meals){
            sum+= calculateCaloriesInOneMeal(meal.getMeal());
        }
        return sum;
    }

    public static double calculateFatsInOneMeal(List<Food> foods) {
        double sum = 0;
        for(Food food : foods){
            sum+= food.getFats();
        }
        return sum;
    }

    public static double calculateFatsInAllMeal(List<Meal> meals) {
        double sum = 0;
        for(Meal meal : meals){
            sum+= calculateFatsInOneMeal(meal.getMeal());
        }
        return sum;
    }

    public static double calculateCarbsInOneMeal(List<Food> foods) {
        double sum = 0;
        for(Food food : foods){
            sum+= food.getCarbs();
        }
        return sum;
    }

    public static double calculateCarbsInAllMeal(List<Meal> meals) {
        double sum = 0;
        for(Meal meal : meals){
            sum+= calculateCarbsInOneMeal(meal.getMeal());
        }
        return sum;
    }

    public static double calculateProteinInOneMeal(List<Food> foods) {
        double sum = 0;
        for(Food food : foods){
            sum+= food.getProtein();
        }
        return sum;
    }

    public static double calculateProteinInAllMeal(List<Meal> meals) {
        double sum = 0;
        for(Meal meal : meals){
            sum+= calculateProteinInOneMeal(meal.getMeal());
        }
        return sum;
    }

    public static double calculateFiberInOneMeal(List<Food> foods) {
        double sum = 0;
        for(Food food : foods){
            sum+= food.getFiber();
        }
        return sum;
    }

    public static double calculateFiberInAllMeal(List<Meal> meals) {
        double sum = 0;
        for(Meal meal : meals){
            sum+= calculateFiberInOneMeal(meal.getMeal());
        }
        return sum;
    }

    public static double calculateSugarInOneMeal(List<Food> foods) {
        double sum = 0;
        for(Food food : foods){
            sum+= food.getSugar();
        }
        return sum;
    }

    public static double calculateSugarInAllMeal(List<Meal> meals) {
        double sum = 0;
        for(Meal meal : meals){
            sum+= calculateSugarInOneMeal(meal.getMeal());
        }
        return sum;
    }

    public static double calculateSodiumInOneMeal(List<Food> foods) {
        double sum = 0;
        for(Food food : foods){
            sum+= food.getSodium();
        }
        return sum;
    }

    public static double calculateSodiumInAllMeal(List<Meal> meals) {
        double sum = 0;
        for(Meal meal : meals){
            sum+= calculateSodiumInOneMeal(meal.getMeal());
        }
        return sum;
    }

}
