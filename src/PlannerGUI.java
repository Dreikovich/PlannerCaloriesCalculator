import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.util.List;

/**
 * PlannerGUI class
 */
public class PlannerGUI {
    /**
     * this field is used to store the frame
     */
    private final Frame frame;
    /**
     * this field is used to store the available foods
     */
    private List<Food> availableFoods;
    /**
     * this field is used to store the selected foods
     */
    private List<Food> selectedFoods;
    /**
     * this field is used to store the meals
     */
    private static List<Meal> meals;
    /**
     * this field is used to store the food checkboxes
     */
    private final  Map<String, Checkbox> foodCheckboxes;
    /**
     * this field is used to store the selected foods text area
     */
    private final TextArea selectedFoodsTextArea;
    /**
     * this field is used to store the meals frame
     */
    private JFrame mealsFrame;
    /**
     * this field is used to store the calories field
     */

    private JTextField caloriesField;
    /**
     * this field is used to store the proteins field
     */
    private JTextField proteinsField;
    /**
     * this field is used to store the fats field
     */
    private JTextField fatsField;
    /**
     * this field is used to store the carbs field
     */
    private JTextField carbsField;

    /**
     * PlannerGUI
     * This constructor creates a frame with all the information
     */
    public PlannerGUI() {
        meals = new ArrayList<>();
        frame = new Frame("Planner Page");
        frame.setLayout(new BorderLayout());

        availableFoods = RefreshData.getAvailableFoods();

        foodCheckboxes = new HashMap<>();

        Panel checkboxPanel = new Panel(new GridLayout(0, 1));
        Panel containerPanel = new Panel();


        selectedFoodsTextArea = new TextArea(10, 30);
        selectedFoodsTextArea.setVisible(false);
        frame.add(selectedFoodsTextArea, BorderLayout.CENTER);

        Button showAvailableFoodButton = new Button("Show Available Food");
        Button addFoodToTheMealButton = new Button("Add food to the meal");
        Button addCustomFoodToTheFoodData = new Button("Add custom food");
        Button refreshButton = new Button("Refresh Data");
        refreshButton.addActionListener(e -> {
            RefreshData.refreshAvailableFoods();
            availableFoods = RefreshData.getAvailableFoods();
            System.out.println("Data refreshed. The size of available data now is " + availableFoods.size());
            showAvailableFoodCheckboxes(checkboxPanel, containerPanel);

        });

        showAvailableFoodButton.addActionListener(e -> showAvailableFoodCheckboxes(checkboxPanel, containerPanel));
        addFoodToTheMealButton.addActionListener(e -> {
            FillTheListSelectedFood();
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
            availableFoods = RefreshData.getAvailableFoods();
            System.out.println("Data refreshed. The size of available data now is " + availableFoods.size());
            showAvailableFoodCheckboxes(checkboxPanel, containerPanel);

        });

        Button viewMealsButton = new Button("View Meal");
        viewMealsButton.addActionListener(e -> {
            System.out.println("Current Meals: " + meals);
            displayMeals();
        });

        Button deleteSelectedFoodButton = new Button("Delete Selected Food");
        deleteSelectedFoodButton.addActionListener(e -> {
            FillTheListSelectedFood();
            for(Food food : selectedFoods){
                DataManager.deleteFood(food);
                RefreshData.refreshAvailableFoods();
                availableFoods = RefreshData.getAvailableFoods();
                showAvailableFoodCheckboxes(checkboxPanel, containerPanel);

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
        buttonPanel.add(viewMealsButton);
        buttonPanel.add(addCustomFoodToTheFoodData);
        buttonPanel.add(deleteSelectedFoodButton);
        buttonPanel.add(refreshButton);

        // Adding components to the frame
        frame.add(buttonPanel, BorderLayout.SOUTH);
        frame.pack();
        frame.setVisible(true);
    }
    /**
     * getMeals
     * This method returns the meals
     * @return meals
     */
    public static List<Meal> getMeals() {
        return meals;
    }
    /**
     * FillTheListSelectedFood
     * This method fills the list of selected food
     */
    private void FillTheListSelectedFood(){
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

    }
    /**
     * showAvailableFoodCheckboxes
     * This method shows the available food checkboxes
     * @param checkboxPanel checkbox panel
     * @param containerPanel container panel
     */
    private void showAvailableFoodCheckboxes(Panel checkboxPanel, Panel containerPanel) {
        // Create checkboxes for available foods
        checkboxPanel.removeAll();
        containerPanel.removeAll();
        foodCheckboxes.clear();

        int visibleCheckboxCount = 20;
        int checkboxWidth = 200;
        for (int i = 0; i < availableFoods.size() && i < visibleCheckboxCount; i++) {
            Food food = availableFoods.get(i);
            Checkbox checkbox = new Checkbox(food.getFoodItem());
            // Set constant width for the checkbox
            checkbox.setPreferredSize(new Dimension(checkboxWidth, 20));
            foodCheckboxes.put(food.getFoodItem(), checkbox);
            checkboxPanel.add(checkbox);
        }
        // create checkboxes for available foods and insert them into the map table
        /*for (Food food : availableFoods) {
            Checkbox checkbox = new Checkbox(food.getFoodItem());
            foodCheckboxes.put(food.getFoodItem(), checkbox);
        }*/
        // Sort checkboxes alphabetically
        List<Map.Entry<String, Checkbox>> entryList = new ArrayList<>(foodCheckboxes.entrySet());
        Collections.sort(entryList, Comparator.comparing(Map.Entry::getKey));
        //add checkboxes to the panel
        for (Map.Entry<String, Checkbox> entry : entryList) {
            checkboxPanel.add(entry.getValue());
        }
        Scrollbar verticalScrollbar = new Scrollbar(Scrollbar.VERTICAL, 0, visibleCheckboxCount, 0, availableFoods.size());

        containerPanel.setLayout(new BorderLayout());
        containerPanel.add(checkboxPanel, BorderLayout.CENTER);
        containerPanel.add(verticalScrollbar, BorderLayout.EAST);
        verticalScrollbar.addAdjustmentListener(new AdjustmentListener() {
            @Override
            public void adjustmentValueChanged(AdjustmentEvent e) {
                int scrollbarValue = verticalScrollbar.getValue();
                Checkbox[] selectedCheckboxes = getSelectedCheckboxes();
                checkboxPanel.removeAll();

                // Calculate the range of checkboxes to display based on the scrollbar value
                int startIndex = scrollbarValue;
                int endIndex = Math.min(startIndex + visibleCheckboxCount, availableFoods.size());
                // If the end index is equal to the size of the available foods, then the start index should be adjusted
                if(endIndex == availableFoods.size()){
                    startIndex = endIndex - visibleCheckboxCount;
                }
                // Add checkboxes within the visible range to the panel
                for(int i=startIndex;i<endIndex;i++){
                    Checkbox checkbox = new Checkbox(availableFoods.get(i).getFoodItem());
                    checkboxPanel.add(checkbox);
                    foodCheckboxes.put(availableFoods.get(i).getFoodItem(), checkbox);
                }
                reselectCheckboxes(selectedCheckboxes);
                checkboxPanel.revalidate();
                checkboxPanel.repaint();
            }
        });
        checkboxPanel.setPreferredSize(new Dimension(checkboxWidth, visibleCheckboxCount * checkboxPanel.getComponent(0).getPreferredSize().height));
        verticalScrollbar.setValue(0);
        checkboxPanel.revalidate();
        checkboxPanel.repaint();
        frame.add(containerPanel, BorderLayout.WEST);

        /*frame.add(checkboxPanel, BorderLayout.WEST);*/
        selectedFoodsTextArea.setVisible(true);
        frame.pack();
    }
    /**
     * getSelectedCheckboxes
     * This method returns the selected checkboxes
     * @return selectedCheckboxes
     */
    private Checkbox[] getSelectedCheckboxes() {
        List<Checkbox> selectedCheckboxes = new ArrayList<>();
        for (Map.Entry<String, Checkbox> entry : foodCheckboxes.entrySet()) {
            if (entry.getValue().getState()) {
                selectedCheckboxes.add(entry.getValue());
            }
        }
        return selectedCheckboxes.toArray(new Checkbox[0]);
    }
    /**
     * reselectCheckboxes
     * This method reselects the checkboxes
     * @param selectedCheckboxes selected checkboxes
     */
    private void reselectCheckboxes(Checkbox[] selectedCheckboxes) {
        for (Checkbox checkbox : selectedCheckboxes) {
            foodCheckboxes.get(checkbox.getLabel()).setState(true);
        }
    }
    /**
     * PrintSelectedFoodInTheConsole
     * This method prints the selected food in the console
     */
    private void PrintSelectedFoodInTheConsole(){
        for(Food food : selectedFoods ){
            food.toString();
        }
    }
    /**
     * updateSelectedFoodsTextArea
     * This method updates the selected foods text area
     */
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
    /**
     * initializeMealsFrame
     * This method initializes the meals frame
     */
    private void initializeMealsFrame() {
        if (mealsFrame == null) {
            mealsFrame = new JFrame("Meals");
            mealsFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            mealsFrame.setSize(1000, 600);
            mealsFrame.setLayout(new BorderLayout());
        } else {
            mealsFrame.getContentPane().removeAll();
        }
    }
    /**
     * prepareTableData
     * This method prepares the table data
     * @param maxFoodCount maximum food count
     * @return data
     */
    private String[][] prepareTableData(int maxFoodCount) {
        String[][] data = new String[meals.size()][maxFoodCount + 2];
        for (int i = 0; i < meals.size(); i++) {
            Meal meal = meals.get(i);
            String[] rowData = new String[maxFoodCount + 2];
            rowData[0] = "Meal " + (i + 1);
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
            rowData[maxFoodCount + 1] = "X";
            data[i] = rowData;
        }
        return data;
    }
    /**
     * createTableModel
     * This method creates the table model
     * @param data data
     * @param columns columns
     * @return model
     */
    private DefaultTableModel createTableModel(String[][] data, String[] columns) {
        return new DefaultTableModel(data, columns);
    }
    /**
     * createTableCellRenderer
     * This method creates the table cell renderer
     * @param finalMaxFoodCount final maximum food count
     * @return component
     */
    private DefaultTableCellRenderer createTableCellRenderer(int finalMaxFoodCount) {
        return new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                Component component = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                if (column == finalMaxFoodCount + 1) {
                    component.setBackground(Color.RED);
                    component.setForeground(Color.WHITE);
                    ((JLabel) component).setHorizontalAlignment(JLabel.CENTER);
                } else {
                    component.setBackground(table.getBackground());
                }
                return component;
            }
        };
    }
    /**
     * addRemoveMouseListener
     * This method adds the remove mouse listener
     * @param table table
     * @param model model
     * @param finalMaxFoodCount final maximum food count
     */
    private void addRemoveMouseListener(JTable table, DefaultTableModel model, int finalMaxFoodCount) {
        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int column = table.columnAtPoint(e.getPoint());
                int row = table.rowAtPoint(e.getPoint());
                if (column == finalMaxFoodCount + 1 && row >= 0) {
                    model.removeRow(row);
                    meals.remove(row);
                }
            }
        });
    }
    /**
     * calculateMaxFoodCount
     * This method calculates the maximum food count
     * @return maxFoodCount
     */
    private int calculateMaxFoodCount() {
        int maxFoodCount = 0;
        for (Meal meal : meals) {
            int foodCount = meal.getMeal().size();
            if (foodCount > maxFoodCount) {
                maxFoodCount = foodCount;
            }
        }
        return maxFoodCount;
    }
    /**
     * prepareColumns
     * This method prepares the columns
     * @param maxFoodCount maximum food count
     * @return columns
     */
    private String[] prepareColumns(int maxFoodCount) {
        String[] columns = new String[maxFoodCount + 2];
        columns[0] = "Meal Number";
        for (int i = 0; i < maxFoodCount; i++) {
            columns[i + 1] = "Food " + (i + 1);
        }
        columns[maxFoodCount + 1] = "Remove";
        return columns;
    }
    /**
     * configureAndShowMealsFrame
     * This method configures and shows the meals frame
     * @param model model
     * @param table table
     * @param finalMaxFoodCount final maximum food count
     */
    private void configureAndShowMealsFrame(DefaultTableModel model, JTable table, int finalMaxFoodCount) {
        table.setRowHeight(30);
        table.setShowVerticalLines(true);
        table.getColumnModel().getColumn(finalMaxFoodCount + 1).setCellRenderer(createTableCellRenderer(finalMaxFoodCount));
        mealsFrame.add(new JScrollPane(table));
        mealsFrame.pack();
        mealsFrame.setVisible(true);
        mealsFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        mealsFrame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                mealsFrame.dispose();
            }
        });
    }
    /**
     * displayMeals
     * This method displays the meals table
     */
    public void displayMeals() {
        initializeMealsFrame();

        int maxFoodCount = calculateMaxFoodCount();

        String[] columns = prepareColumns(maxFoodCount);

        String[][] data = prepareTableData(maxFoodCount);

        DefaultTableModel model = createTableModel(data, columns);

        JTable table = new JTable(model);

        addRemoveMouseListener(table, model, maxFoodCount);

        configureAndShowMealsFrame(model, table, maxFoodCount);

        caloriesField = new JTextField();
        proteinsField = new JTextField();
        fatsField = new JTextField();
        carbsField = new JTextField();

        Panel calculatorPanel = new Panel(new GridLayout(0, 2));
        calculatorPanel.add(new Label("Calories:"));
        calculatorPanel.add(caloriesField);
        calculatorPanel.add(new Label("Proteins (g):"));
        calculatorPanel.add(proteinsField);
        calculatorPanel.add(new Label("Fats (g):"));
        calculatorPanel.add(fatsField);
        calculatorPanel.add(new Label("Carbs (g):"));
        calculatorPanel.add(carbsField);

        Button calculateCaloriesButton = new Button("Calculate Calories");
        calculateCaloriesButton.addActionListener(e -> {
            caloriesField.setText(String.valueOf(MealCalculator.calculateCaloriesInAllMeal(meals)));
            proteinsField.setText(String.valueOf(MealCalculator.calculateProteinInAllMeal(meals)));
            fatsField.setText(String.valueOf(MealCalculator.calculateFatsInAllMeal(meals)));
            carbsField.setText(String.valueOf(MealCalculator.calculateCarbsInAllMeal(meals)));
        });

        calculatorPanel.add(calculateCaloriesButton);
        mealsFrame.add(calculatorPanel, BorderLayout.SOUTH);
    }
}
