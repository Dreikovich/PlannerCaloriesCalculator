import java.util.List;
/**
 * Meal class
 */
public class Meal {
    /**
     * this field is used to store the name of the meal
     */
    private String name;
    /**
     * this field is used to store the list of food items in meal
     */
    private List<Food> meal;
    /**
     * Meal
     * This constructor creates a meal with all the information
     * @param name
     * @param foods
     */
    public Meal(String name, List<Food> foods) {
        this.name = name;
        this.meal = foods;
    }
    public void addFoodToMeal(Food food) {
        meal.add(food);
    }
    /**
     * getMeal
     * This method returns the list of food items in meal
     * @return meal
     */
    public List<Food> getMeal(){
        return meal;
    }
    /**
     * getName
     * This method returns the name of the meal
     * @return name
     */
    public String getName() {
        return name;
    }



}