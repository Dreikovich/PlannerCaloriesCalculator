import java.util.List;
/**
 * MealCalculator class
 */
public class MealCalculator {
    /**
     * calculateCaloriesInOneMeal
     * This method calculates the total calories in one meal
     * @param foods
     * @return sum
     */
    public static int calculateCaloriesInOneMeal(List<Food> foods) {
       int sum = 0;
       for(Food food : foods){
           sum+= food.getCalories();
       }
       return sum;
    }
    /**
     * calculateCaloriesInAllMeal
     * This method calculates the total calories in all meals
     * @param meals
     * @return sum
     */
    public static int calculateCaloriesInAllMeal(List<Meal> meals) {
        int sum = 0;
        for(Meal meal : meals){
            sum+= calculateCaloriesInOneMeal(meal.getMeal());
        }
        return sum;
    }
    /**
     * calculateFatsInOneMeal
     * This method calculates the total fats in one meal
     * @param foods
     * @return sum
     */
    public static double calculateFatsInOneMeal(List<Food> foods) {
        double sum = 0;
        for(Food food : foods){
            sum+= food.getFats();
        }
        return sum;
    }
    /**
     * calculateFatsInAllMeal
     * This method calculates the total fats in all meals
     * @param meals
     * @return sum
     */
    public static double calculateFatsInAllMeal(List<Meal> meals) {
        double sum = 0;
        for(Meal meal : meals){
            sum+= calculateFatsInOneMeal(meal.getMeal());
        }
        return sum;
    }
    /**
     * calculateCarbsInOneMeal
     * This method calculates the total carbs in one meal
     * @param foods
     * @return sum
     */
    public static double calculateCarbsInOneMeal(List<Food> foods) {
        double sum = 0;
        for(Food food : foods){
            sum+= food.getCarbs();
        }
        return sum;
    }
    /**
     * calculateCarbsInAllMeal
     * This method calculates the total carbs in all meals
     * @param meals
     * @return sum
     */
    public static double calculateCarbsInAllMeal(List<Meal> meals) {
        double sum = 0;
        for(Meal meal : meals){
            sum+= calculateCarbsInOneMeal(meal.getMeal());
        }
        return sum;
    }
    /**
     * calculateProteinInOneMeal
     * This method calculates the total protein in one meal
     * @param foods
     * @return sum
     */
    public static double calculateProteinInOneMeal(List<Food> foods) {
        double sum = 0;
        for(Food food : foods){
            sum+= food.getProtein();
        }
        return sum;
    }
    /**
     * calculateProteinInAllMeal
     * This method calculates the total protein in all meals
     * @param meals
     * @return sum
     */
    public static double calculateProteinInAllMeal(List<Meal> meals) {
        double sum = 0;
        for(Meal meal : meals){
            sum+= calculateProteinInOneMeal(meal.getMeal());
        }
        return sum;
    }
    /**
     * calculateFiberInOneMeal
     * This method calculates the total fiber in one meal
     * @param foods
     * @return sum
     */
    public static double calculateFiberInOneMeal(List<Food> foods) {
        double sum = 0;
        for(Food food : foods){
            sum+= food.getFiber();
        }
        return sum;
    }
    /**
     * calculateFiberInAllMeal
     * This method calculates the total fiber in all meals
     * @param meals
     * @return sum
     */
    public static double calculateFiberInAllMeal(List<Meal> meals) {
        double sum = 0;
        for(Meal meal : meals){
            sum+= calculateFiberInOneMeal(meal.getMeal());
        }
        return sum;
    }
    /**
     * calculateSugarInOneMeal
     * This method calculates the total sugar in one meal
     * @param foods
     * @return sum
     */
    public static double calculateSugarInOneMeal(List<Food> foods) {
        double sum = 0;
        for(Food food : foods){
            sum+= food.getSugar();
        }
        return sum;
    }
    /**
     * calculateSugarInAllMeal
     * This method calculates the total sugar in all meals
     * @param meals
     * @return sum
     */
    public static double calculateSugarInAllMeal(List<Meal> meals) {
        double sum = 0;
        for(Meal meal : meals){
            sum+= calculateSugarInOneMeal(meal.getMeal());
        }
        return sum;
    }
    /**
     * calculateSodiumInOneMeal
     * This method calculates the total sodium in one meal
     * @param foods
     * @return sum
     */
    public static double calculateSodiumInOneMeal(List<Food> foods) {
        double sum = 0;
        for(Food food : foods){
            sum+= food.getSodium();
        }
        return sum;
    }
    /**
     * calculateSodiumInAllMeal
     * This method calculates the total sodium in all meals
     * @param meals
     * @return sum
     */
    public static double calculateSodiumInAllMeal(List<Meal> meals) {
        double sum = 0;
        for(Meal meal : meals){
            sum+= calculateSodiumInOneMeal(meal.getMeal());
        }
        return sum;
    }
}
