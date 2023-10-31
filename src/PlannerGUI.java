import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PlannerGUI {
    private Frame frame;
    private List<Food> availableFoods;
    private List<Food> selectedFoods;
    private List<Food> meal;
    private  Map<String, Checkbox> foodCheckboxes;
    public PlannerGUI() {
        frame = new Frame("Planner Page");
        frame.setLayout(new BorderLayout());
        availableFoods = readAvailableFoodsFromFile();
        foodCheckboxes = new HashMap<>();
        for (Food food : availableFoods) {
            Checkbox checkbox = new Checkbox(food.getFoodItem());
            foodCheckboxes.put(food.getFoodItem(), checkbox);
        }
        Panel checkboxPanel = new Panel(new GridLayout(foodCheckboxes.size(), 1));
        for (Checkbox checkbox : foodCheckboxes.values()) {
            checkboxPanel.add(checkbox);

        }
        frame.add(checkboxPanel, BorderLayout.NORTH);
        Button addFoodToTheMealButton = new Button("Add food to the meal");
        addFoodToTheMealButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                selectedFoods = new ArrayList<>();
                List<String> tempWithSelectedFoods = new ArrayList<>();
                for (Map.Entry<String, Checkbox> entry : foodCheckboxes.entrySet()) {
                    if (entry.getValue().getState()) {
                       tempWithSelectedFoods.add( entry.getKey());
                    }
                }
                for(Food food : availableFoods){
                    for(String filteredFoodBySelect : tempWithSelectedFoods){
                        if(filteredFoodBySelect.equals(food.getFoodItem())){
                            selectedFoods.add(food);
                        }
                    }
                }
                meal = new ArrayList<>();
                meal.addAll(selectedFoods);
                PrintSelectedFoodInTheConsole();

            }
        });

        Button viewMealButton = new Button("View Meal");
        viewMealButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.out.println("Current Meal: " + meal);
            }
        });


        // Adding components to the frame

        frame.add(addFoodToTheMealButton, BorderLayout.CENTER);
        frame.add(viewMealButton, BorderLayout.SOUTH);
        frame.pack();
        frame.setVisible(true);
    }

    private List<Food> readAvailableFoodsFromFile() {
        String data = DataManager.readFromFile("data/food.txt");
        List<Food> foods = new ArrayList<>();
        if (!data.isEmpty()) {
            String[] foodItems = data.split("\n");
            for (String foodItem : foodItems) {
                String[] parts = foodItem.split(":");
                if(parts.length==8){
                    //Calories, Fats(g):Carbs(g):Protein(g):Fiber(g):Sugar(g):Sodium
                    String foodName = parts[0].trim();
                    int calories = Integer.parseInt(parts[1].trim());
                    double fats = parseGrams(parts[2].trim());
                    double carbs = parseGrams(parts[3].trim());
                    double proteins = parseGrams(parts[4].trim());
                    double fiber = parseGrams(parts[5].trim());
                    double sugar = parseGrams(parts[6].trim());
                    double sodium = parseGrams(parts[7].trim());
                    Food food = new Food(foodName, calories, fats, carbs, proteins, fiber, sugar, sodium);
                    foods.add(food);
                } else {
                    System.out.println("Invalid data format: " + foodItem);
                }
            }
        }
        return foods;
    }

    private void PrintSelectedFoodInTheConsole(){
        for(Food food : selectedFoods ){
            food.toString();
        }
    }

    private double parseGrams(String value) {
        if (value.endsWith("mg")) {
            return Double.parseDouble(value.substring(0, value.length() - 2)) / 1000.0
        } else if (value.endsWith("g")) {
            return Double.parseDouble(value.substring(0, value.length() - 1));
        } else {
            return 0.0;
        }
    }
}
