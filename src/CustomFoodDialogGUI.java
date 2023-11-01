import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
public class CustomFoodDialogGUI extends Frame
{
    private TextField foodNameField;
    private TextField caloriesField;
    private TextField fatsField;
    private TextField carbsField;
    private TextField proteinsField;
    private TextField fiberField;
    private TextField sugarField;
    private  TextField sodiumField;

    public  CustomFoodDialogGUI(Frame parentFrame) {

        super("Add Custom Food");

        setLayout(new GridLayout(9, 2));

        foodNameField = new TextField();
        caloriesField = new TextField();
        fatsField = new TextField();
        carbsField = new TextField();
        proteinsField = new TextField();
        fiberField = new TextField();
        sugarField = new TextField();
        sodiumField = new TextField();

        Button addButton = new Button("Add");
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String foodName = foodNameField.getText();
                int calories = Integer.parseInt(caloriesField.getText());
                double fats = Double.parseDouble(fatsField.getText());
                double carbs = Double.parseDouble(carbsField.getText());
                double proteins = Double.parseDouble(proteinsField.getText());
                double fiber = Double.parseDouble(fiberField.getText());
                double sugar = Double.parseDouble(sugarField.getText());
                double sodium = Double.parseDouble(sodiumField.getText());

                Food customFood = new Food(foodName, calories, fats, carbs, proteins, fiber, sugar, sodium);
                DataManager.writeToFile("data/food.txt", DataHelper.formatFoodData(customFood));
                parentFrame.setVisible(true); // Show the parent frame after adding the food
                dispose(); // Close the dialog after adding the food
            }
        });

        add(new Label("Food Name:"));
        add(foodNameField);
        add(new Label("Calories:"));
        add(caloriesField);
        add(new Label("Fats (g):"));
        add(fatsField);
        add(new Label("Carbs (g):"));
        add(carbsField);
        add(new Label("Proteins (g):"));
        add(proteinsField);
        add(new Label("Fiber (g):"));
        add(fiberField);
        add(new Label("Sugar (g):"));
        add(sugarField);
        add(new Label("Sodium (g):"));
        add(sodiumField);
        add(addButton);

        pack();
        setLocationRelativeTo(parentFrame);
        setVisible(true);

    }
}
