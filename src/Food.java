public class Food {
    private String foodItem;
    private int calories;
    private double fats;
    private double carbs;
    private double protein;
    private double fiber;
    private double sugar;
    private double sodium;
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

    // Getters and Setters
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

    public void setFoodItem(String foodItem) {
        this.foodItem = foodItem;
    }

    public void setCalories(int calories) {
        this.calories = calories;
    }

    public void setFats(double fats) {
        this.fats = fats;
    }

    public void setCarbs(double carbs) {
        this.carbs = carbs;
    }

    public void setProtein(double protein) {
        this.protein = protein;
    }

    public void setFiber(double fiber) {
        this.fiber = fiber;
    }

    public void setSugar(double sugar) {
        this.sugar = sugar;
    }

    public void setSodium(double sodium) {
        this.sodium = sodium;
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
