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

    private TextArea selectedFoodsTextArea;

    public PlannerGUI() {
        frame = new Frame("Planner Page");
        frame.setLayout(new BorderLayout());

        availableFoods = RefreshData.getAvailableFoods();

        foodCheckboxes = new HashMap<>();

        Panel checkboxPanel = new Panel(new GridLayout(0, 1));
        frame.add(checkboxPanel, BorderLayout.WEST);

        selectedFoodsTextArea = new TextArea(10, 30);
        selectedFoodsTextArea.setVisible(false);
        frame.add(selectedFoodsTextArea, BorderLayout.CENTER);

        Button showAvailableFoodButton = new Button("Show Available Food");
        Button addFoodToTheMealButton = new Button("Add food to the meal");
        Button addCustomFoodToTheFoodData = new Button("Add custom food");
        Button refreshButton = new Button("Refresh Data");
        refreshButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                availableFoods = RefreshData.getAvailableFoods();
                System.out.println("Data refreshed. The size of available data now is " + availableFoods.size());
                showAvailableFoodCheckboxes(checkboxPanel);

            }
        });

        showAvailableFoodButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showAvailableFoodCheckboxes(checkboxPanel);
            }
        });
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
                updateSelectedFoodsTextArea();

            }
        });

        addCustomFoodToTheFoodData.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CustomFoodDialogGUI customFoodDialogGUI = new CustomFoodDialogGUI(frame);
                frame.setVisible(false);

            }
        });

        Button viewMealButton = new Button("View Meal");
        viewMealButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.out.println("Current Meal: " + meal);
            }
        });

        frame.addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent windowEvent) {
                System.exit(0);
            }
        });

        Panel buttonPanel = new Panel(new GridLayout(0,1));
        buttonPanel.add(showAvailableFoodButton);
        buttonPanel.add(addFoodToTheMealButton);
        buttonPanel.add(viewMealButton);
        buttonPanel.add(addCustomFoodToTheFoodData);
        buttonPanel.add(refreshButton);

        // Adding components to the frame
        frame.add(buttonPanel, BorderLayout.SOUTH);
        /*frame.add(viewMealButton, BorderLayout.SOUTH);*/
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

    private void showAvailableFoodCheckboxes(Panel checkboxPanel) {
        // Create checkboxes for available foods
        checkboxPanel.removeAll();
        foodCheckboxes.clear();
        for (Food food : availableFoods) {
            Checkbox checkbox = new Checkbox(food.getFoodItem());
            foodCheckboxes.put(food.getFoodItem(), checkbox);
        }
        for (Checkbox checkbox : foodCheckboxes.values()) {
            checkboxPanel.add(checkbox);

        }
        frame.add(checkboxPanel, BorderLayout.WEST);
        selectedFoodsTextArea.setVisible(true);
        frame.pack();

    }

    private void PrintSelectedFoodInTheConsole(){
        for(Food food : selectedFoods ){
            food.toString();
        }
    }

    private double parseGrams(String value) {
        if (value.endsWith("mg")) {
            return Double.parseDouble(value.substring(0, value.length() - 2)) / 1000.0;
        } else if (value.endsWith("g")) {
            return Double.parseDouble(value.substring(0, value.length() - 1));
        } else {
            return 0.0;
        }
    }

    private void updateSelectedFoodsTextArea() {
        selectedFoodsTextArea.setText("");
        Font font = new Font("Arial", Font.PLAIN, 14); // Customize font (you can adjust the font name, style, and size)
        selectedFoodsTextArea.setFont(font);
        selectedFoodsTextArea.setBackground(Color.WHITE); // Set background color
        selectedFoodsTextArea.setEditable(false); // Make it non-editable

        for (Food food : selectedFoods) {
            selectedFoodsTextArea.append("Name: " + food.getFoodItem() + "\n");
            selectedFoodsTextArea.append("Calories: " + food.getCalories() + "\n");
            selectedFoodsTextArea.append("Fats: " + food.getFats() + "g\n");
            selectedFoodsTextArea.append("Carbs: " + food.getCarbs() + "g\n");
            selectedFoodsTextArea.append("Proteins: " + food.getProtein() + "g\n");
            selectedFoodsTextArea.append("Fiber: " + food.getFiber() + "g\n");
            selectedFoodsTextArea.append("Sugar: " + food.getSugar() + "g\n");
            selectedFoodsTextArea.append("Sodium: " + food.getSodium() + "g\n\n");
        }
    }
}
