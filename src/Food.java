/**
 * Food class that holds all the information about a food item
 */

public class Food {
    /**
     * The field is used to store the food item name
     */
    private String foodItem;
    /**
     * The field is used to store the calories
     */
    private int calories;
    /**
     * The field is used to store the fats
     */
    private double fats;
    /**
     * The field is used to store the carbs
     */
    private double carbs;
    /**
     * The field is used to store the protein
     */
    private double protein;
    /**
     * The field is used to store the fiber
     */
    private double fiber;
    /**
     * The field is used to store the sugar
     */
    private double sugar;
    /**
     * The field is used to store the sodium
     */
    private double sodium;
    /**
     * Food
     * This constructor creates a food item with all the information
     * @param foodItem
     * @param calories
     * @param fats
     * @param carbs
     * @param protein
     * @param fiber
     * @param sugar
     * @param sodium
     */
    public Food(String foodItem, int calories, double fats, double carbs, double protein, double fiber, double sugar, double sodium) {
        this.foodItem = foodItem;
        this.calories = calories;
        this.fats = fats;
        this.carbs = carbs;
        this.protein = protein;
        this.fiber = fiber;
        this.sugar = sugar;
        this.sodium = sodium;
    }

    // Getters
    public String getFoodItem() {
        return foodItem;
    }

    public int getCalories() {
        return calories;
    }

    public double getFats() {
        return fats;
    }

    public double getCarbs() {
        return carbs;
    }

    public double getProtein() {
        return protein;
    }

    public double getFiber() {
        return fiber;
    }

    public double getSugar() {
        return sugar;
    }

    public double getSodium() {
        return sodium;
    }

    @Override
    public String toString() {
        return "Food Item: " + foodItem +
                "\nCalories: " + calories +
                "\nFats: " + fats + "g" +
                "\nCarbs: " + carbs + "g" +
                "\nProtein: " + protein + "g" +
                "\nFiber: " + fiber + "g" +
                "\nSugar: " + sugar + "g" +
                "\nSodium: " + sodium + "mg";
    }
}
