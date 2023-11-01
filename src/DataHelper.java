public class DataHelper {
    public static String formatFoodData(Food food) {
        return food.getFoodItem() + ":" + food.getCalories() + ":" +
                food.getFats() + ":" + food.getCarbs() + ":" +
                food.getProtein() + ":" + food.getFiber() + ":" +
                food.getSugar() + ":" + food.getSodium();
    }


}
