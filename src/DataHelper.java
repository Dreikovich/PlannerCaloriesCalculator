public class DataHelper {
    public static String formatMealData(Food food) {
        return food.getFoodItem() + ":" + food.getCalories() + ":" +
                food.getFats() + ":" + food.getCarbs() + ":" +
                food.getProtein() + ":" + food.getFiber() + ":" +
                food.getSugar() + ":" + food.getSodium();
    }
}
