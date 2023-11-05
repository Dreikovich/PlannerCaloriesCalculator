import java.util.ArrayList;
import java.util.List;

public class Meal {
    private String name;
    private List<Food> meal;
    public Meal(String name, List<Food> foods) {
        this.name = name;
        this.meal = foods;
    }
    public void addFoodToMeal(Food food) {
        meal.add(food);
    }
    public List<Food> getMeal(){
        return meal;
    }
    public String getName() {
        return name;
    }



}