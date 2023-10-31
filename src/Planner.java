import java.util.ArrayList;
import java.util.List;
public class Planner {
    private List<Meal> plannedMeals;

    public Planner() {
        plannedMeals = new ArrayList<>();
    }

    public void addMeal(Meal meal) {
        plannedMeals.add(meal);
    }

    public void displayPlannedMeals( ) {
        System.out.println("Planned Meals:");
        System.out.println("Meal:");
        for (Meal meal : plannedMeals) {
            for(Food food : meal.getMeal()) {
                System.out.println("  " + food.getFoodItem() + ": " + food.getCalories() + " calories");
            }
        }
        System.out.println("------------");
    }

}
