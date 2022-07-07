package url.user.blogpost;

public class EmptyJsonExeption extends Exception{
    EmptyJsonExeption(){
        super("Empty JSON file provided");
    }
}
