package user.blogpost;

import com.fasterxml.jackson.core.JsonProcessingException;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        start();
    }

    public static void start() {
        ReadJSONFile readJSONFile = new ReadJSONFile();

        try {
            readJSONFile.read(FilePath.JSONFILE.value);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        } catch (EmptyJsonFileExeption e) {
            e.printStackTrace();
        }

        System.out.println("------------------------------------- STARTING APPLICATION--------------------------------");
        System.out.println("Please enter post id or user id: ");
        Scanner scanner = new Scanner(System.in);

        String userInput = scanner.nextLine();
        scanner.close();
        if (readJSONFile.validateUserInput(userInput)) {
            List<UserPost> userPosts = readJSONFile.getUserPostsByID(userInput);
            try {
                userPosts.forEach(System.out::println);
            } catch (NullPointerException e) {
                e.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("No such post was found with given id");
        }
    }
}
