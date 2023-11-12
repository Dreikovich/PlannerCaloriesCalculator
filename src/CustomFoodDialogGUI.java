import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

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

                if(checkIfAllFieldsAreFilled()){
                    System.out.println("All fields are filled");
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
                else{
                    System.out.println("All fields are not filled");
                    AlertMessage alertMessage = new AlertMessage("Please fill all the fields");
                }
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
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                parentFrame.setVisible(true);
                dispose();
            }
        });
        pack();
        setLocationRelativeTo(parentFrame);
        setVisible(true);

    }
    private boolean checkIfAllFieldsAreFilled() {
        return !foodNameField.getText().isEmpty() &&
                !caloriesField.getText().isEmpty() &&
                !fatsField.getText().isEmpty() &&
                !carbsField.getText().isEmpty() &&
                !proteinsField.getText().isEmpty() &&
                !fiberField.getText().isEmpty() &&
                !sugarField.getText().isEmpty() &&
                !sodiumField.getText().isEmpty();
    }


}
