import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class PlannerGUI {
    private final Frame frame;
    private List<Food> availableFoods;
    private List<Food> selectedFoods;
    private final List<Meal> meals;
    private final  Map<String, Checkbox> foodCheckboxes;

    private final TextArea selectedFoodsTextArea;
    private JFrame mealsFrame;


    public PlannerGUI() {
        meals = new ArrayList<>();
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
        refreshButton.addActionListener(e -> {
            availableFoods = RefreshData.getAvailableFoods();
            System.out.println("Data refreshed. The size of available data now is " + availableFoods.size());
            showAvailableFoodCheckboxes(checkboxPanel);

        });

        showAvailableFoodButton.addActionListener(e -> showAvailableFoodCheckboxes(checkboxPanel));
        addFoodToTheMealButton.addActionListener(e -> {
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

            PrintSelectedFoodInTheConsole();
            updateSelectedFoodsTextArea();
            System.out.println("selectedFoods size: " + selectedFoods.size());
            Meal meal = new Meal("Meal " + (meals.size() + 1), selectedFoods);
            meals.add(meal);
            System.out.println("Meal saved: " + meal.getName());

        });

        addCustomFoodToTheFoodData.addActionListener(e -> {
            CustomFoodDialogGUI customFoodDialogGUI = new CustomFoodDialogGUI(frame);
            frame.setVisible(false);

        });

        Button viewMealsButton = new Button("View Meal");
        viewMealsButton.addActionListener(e -> {
            System.out.println("Current Meals: " + meals);
            displayMeals();
        });

        frame.addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent windowEvent) {
                System.exit(0);
            }
        });

        Panel buttonPanel = new Panel(new GridLayout(0,1));
        buttonPanel.add(showAvailableFoodButton);
        buttonPanel.add(addFoodToTheMealButton);
        buttonPanel.add(viewMealsButton);
        buttonPanel.add(addCustomFoodToTheFoodData);
        buttonPanel.add(refreshButton);

        // Adding components to the frame
        frame.add(buttonPanel, BorderLayout.SOUTH);
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
        Font font = new Font("Arial", Font.PLAIN, 14); // Create a font
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

    private void displayMeals() {

        if (mealsFrame == null) {
            mealsFrame = new JFrame("Meals");
            mealsFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            mealsFrame.setSize(1000, 600);
            mealsFrame.setLayout(new BorderLayout());
        }
        else{
            mealsFrame.getContentPane().removeAll();
        }
        int maxFoodCount = 0;

        for (Meal meal : meals) {
            int foodCount = meal.getMeal().size();
            if (foodCount > maxFoodCount) {
                maxFoodCount = foodCount;
            }
        }

        String[][] data = new String[meals.size()][maxFoodCount + 2];
        String[] columns = new String[maxFoodCount + 2];

        columns[0] = "Meal Number";
        for (int i = 0; i < maxFoodCount; i++) {
            columns[i + 1] = "Food " + (i + 1);
        }
        columns[maxFoodCount + 1] = "Remove";

        for (int i = 0; i < meals.size(); i++) {
            Meal meal = meals.get(i);
            String[] rowData = new String[maxFoodCount + 2];
            rowData[0] = "Meal " + (i + 1); // Meal number
            List<Food> foods = meal.getMeal();
            for (int j = 0; j < foods.size(); j++) {
                Food food = foods.get(j);
                rowData[j + 1] = food.getFoodItem() + " (Calories: " + food.getCalories() +
                        ", Proteins: " + food.getProtein() +
                        ", Fats: " + food.getFats() +
                        ", Carbs: " + food.getCarbs() +
                        ", Sodium: " + food.getSodium() +
                        ", Sugar: " + food.getSugar() + ")";
            }
            // Remove button
            data[i] = rowData;
            rowData[maxFoodCount + 1] = "X";
        }

        // Create a table model
        DefaultTableModel model = new DefaultTableModel(data, columns);
        JTable table = new JTable(model);
        int finalMaxFoodCount = maxFoodCount;
         /*table.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent e) {
                int column = table.getColumnModel().getColumnIndex("Remove");
                int row = table.rowAtPoint(e.getPoint());
                if (column == finalMaxFoodCount + 1 && row >= 0) {

                    model.removeRow(row);
                    meals.remove(row);

                }
            }
        });*/
        DefaultTableCellRenderer renderer = new DefaultTableCellRenderer() {
        @Override
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
            Component component = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
            if (column == finalMaxFoodCount + 1) {
                component.setBackground(Color.RED);
                component.setForeground(Color.WHITE);
                ((JLabel) component).setHorizontalAlignment(JLabel.CENTER);
                // create the click listener for the cell renderer to remove the row when clicked in the meal doesnt work solved here:
                // https://stackoverflow.com/questions/7612569/how-to-add-mouse-listener-to-a-component-in-a-custom-jtable-header-cell-renderer
            }

            else {
                component.setBackground(table.getBackground());
            }
            return component;
        }
        };
        if(!meals.isEmpty()){
            MealCalculator mealCalculator = new MealCalculator();
            double totalCalories = mealCalculator.calculateCaloriesInOneMeal(meals.get(0).getMeal());
            System.out.println("Total calories in meal 1: " + totalCalories);
            System.out.println(MealCalculator.calculateCaloriesInAllMeal(meals));
        }



        table.getColumnModel().getColumn(finalMaxFoodCount + 1).setCellRenderer(renderer);
        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int column =  table.columnAtPoint(e.getPoint());
                int row = table.rowAtPoint(e.getPoint());
                if (column == finalMaxFoodCount + 1 && row >= 0) {
                    model.removeRow(row);
                    meals.remove(row);
                }
            }
        });
        table.setRowHeight(30);
        table.setShowVerticalLines(true);
        mealsFrame.add(new JScrollPane(table));
        mealsFrame.pack();
        mealsFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        mealsFrame.setVisible(true);
        mealsFrame.setSize(1000, 600);

        mealsFrame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                mealsFrame.dispose();
            }
        });
    }

}
