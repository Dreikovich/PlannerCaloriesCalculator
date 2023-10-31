import java.io.*;
public class DataManager {
    public static void writeToFile(String fileName, String data){
        try(FileWriter writer = new FileWriter(fileName, true)){
            writer.write("\n"+data);
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
}
