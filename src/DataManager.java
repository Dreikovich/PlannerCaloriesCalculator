import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class DataManager {
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
    public static void ClearOldData(String fileName) {
        try (PrintWriter writer = new PrintWriter(fileName)) {
            // Clears the content of the file
            writer.print("");
        } catch (FileNotFoundException ex) {
            System.err.println("File not found: " + ex.getMessage());
        }
    }

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
}
