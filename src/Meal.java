import java.util.ArrayList;
import java.util.List;

public class Meal {
    private List<Food> meal;

    public Meal() {
        meal = new ArrayList<>();
    }

    public void addFoodToMeal(Food food) {
        meal.add(food);
    }

    public List<Food> getMeal(){
        return meal;
    }



}