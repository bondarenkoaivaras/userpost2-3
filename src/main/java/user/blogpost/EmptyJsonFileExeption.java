package user.blogpost;

public class EmptyJsonFileExeption extends Exception{
    EmptyJsonFileExeption(){
        super("Empty JSON file provided");
    }
}
