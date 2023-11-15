import java.io.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * This class is responsible for reading and writing data to files
 */
public class DataManager {
    /**
     * This method writes meal to the file if the meal does not already exist
     * @param fileName file name
     * @param meal food object
     */
    public static void writeTheMealToTheFile(String fileName, Meal meal){
        String data = DataHelper.formatMealData(meal);
        data = DataHelper.getCurrentTime() + ";" + data;
        if(!isDataAlreadyExists(data, fileName)){
            writeToFile(fileName, data);
        }

    }
    /**
     * This method writes food to the file if the food does not already exist
     * @param fileName file name
     * @param data food object
     */
    public static void writeToFile(String fileName, String data){
        try(FileWriter writer = new FileWriter(fileName, true)){
            //check if the existing file is empty
            File file = new File(fileName);
            if (file.length() == 0){
                writer.write(data);
            }
            else{
                writer.write("\n"+data);
            }

        }catch (IOException ex){
            System.err.println("Error writing to file: " + ex.getMessage());
        }
    }
    /**
     * This method reads data from the file
     * @param fileName file name
     * @return data from the file
     */
    public static String readFromFile(String fileName){
        StringBuilder content = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                content.append(line).append("\n");
            }
        } catch (IOException e) {
            System.err.println("Error reading from file: " + e.getMessage());
        }
        return content.toString();
    }
    /**
     * This method clears the content of the file
     * @param fileName file name
     */
    public static void ClearOldData(String fileName) {
        try (PrintWriter writer = new PrintWriter(fileName)) {
            // Clears the content of the file
            writer.print("");
        } catch (FileNotFoundException ex) {
            System.err.println("File not found: " + ex.getMessage());
        }
    }
    /**
     * This method deletes the food from the file
     * @param food food object
     */
    public static void deleteFood(Food food){
        String data = DataManager.readFromFile("data/food.txt");
        String[] foods = data.split("\n");

        List<String> updatedFoods = new ArrayList<>();

        for (String foodInfo : foods) {
            String[] foodDetails = foodInfo.split(":");
            if (!foodDetails[0].equals(food.getFoodItem())) {
                updatedFoods.add(foodInfo);
            }
        }
        String newData = String.join("\n", updatedFoods);
        ClearOldData("data/food.txt");
        DataManager.writeToFile("data/food.txt", newData);
    }
    /**
     * This method checks if the data already exists in the file
     * @param data data to be checked
     * @param fileName file name
     * @return true if the data already exists in the file, false otherwise
     */
    private static boolean isDataAlreadyExists(String data, String fileName){
        String fileData = readFromFile(fileName);
        if(fileData.equals("")){
            return false;
        }
        String[] lines = fileData.split("\n");// Exclude the timestamp
        String mealDataWithoutMealDetails = data.substring(data.indexOf(";") + 1); // Exclude the meal details
        String datePart = data.substring(0, data.indexOf(" ")); // Extract the date part of the timestamp

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
        for (String line : lines) {
            String lineDatePart = line.substring(0, line.indexOf(" "));
            if (datePart.equals(lineDatePart)) {
                String lineWithoutMealDetails = line.substring(line.indexOf(";") + 1);
                if (lineWithoutMealDetails.equals(mealDataWithoutMealDetails)) {
                    return true;
                }
            }
        }
        return false;
    }
}
