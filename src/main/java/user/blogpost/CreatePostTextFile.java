package user.blogpost;

import java.io.*;
import java.util.List;

public class CreatePostTextFile {
    public static void createFile (FilePath path, List<UserPost> posts) {
        BufferedWriter writer = null;
        try {
            writer = new BufferedWriter(new FileWriter( new File(path.value)));
            for(UserPost post: posts){
                writer.write(String.format(" Title: %s \n Body: %s \n Post lenght: %d \n\n",
                        post.getTitle(),
                        post.getBody(),
                        getPostLenght(post)
                       ));
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                writer.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    //gali buti null
    public static int getPostLenght(UserPost post){
        return  post.getTitle().length() + post.getBody().length();
    }
}
