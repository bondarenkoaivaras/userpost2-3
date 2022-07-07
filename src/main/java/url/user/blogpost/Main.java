package url.user.blogpost;

import com.fasterxml.jackson.core.JsonProcessingException;

import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        start();
    }

    public static void start() {
        ReadJSON readJSON = new ReadJSON();

        System.out.println("------------------------------------- STARTING APPLICATION--------------------------------");
        System.out.println("Select option 1 for retrieving posts by user id, select 2 for geting all the : ");

        Scanner scanner = new Scanner(System.in);
        String option = scanner.nextLine();
        String userInput = null;
        String url = null;
        List<UserPost> userPosts = null;

        switch (option){
            case "1":
                System.out.println("Please enter user-id:");
                userInput = scanner.nextLine();
                if (readJSON.validateUserInput(userInput)) {
                    url = "https://gorest.co.in/public/v2/users/" + userInput + "/posts";
                }else{
                    System.out.println("Ivalid user ID was provided");
                }
                break;
            case "2":
                System.out.println("All user posts");
                url = "https://gorest.co.in/public/v2/posts";
                break;
        }

            try {
                readJSON.read(url);
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            } catch (EmptyJsonExeption e) {
                e.printStackTrace();
            }

             userPosts = option.equals("1") ?  readJSON.getUserPostsByID(userInput): readJSON.getAllPosts() ;

            try {
                userPosts.forEach(System.out::println);
            } catch (NullPointerException e) {
                e.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            }
    }
}
