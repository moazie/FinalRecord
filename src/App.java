import java.io.*;
import java.util.*;

public class App {
    public static void main(String[] args) throws Exception {
        //To implement
        ArrayList<Integer> list = new ArrayList<>();

        Scanner scan = new Scanner(System.in);

        System.out.print(
            """
            Choose an option:    
            1. Write a review
            2. Search for a review
            
            Option:""");

        String optionMain = scan.nextLine();

        String filePath = "data/data.txt";

        try (FileWriter writer = new FileWriter(filePath)){
            writer.write(optionMain);
            System.out.println("Data written");
            
        } catch (IOException e) {
            System.err.println("Could not save data");
        }
    }
}
