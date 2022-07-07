package url.user.blogpost;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ReadJSON {
    private final ObjectMapper objectMapper;
    private List<UserPost> userPosts;

    ReadJSON() {
        this.objectMapper = new ObjectMapper();
        this.userPosts = null;
    }

    public void read(String url) throws JsonProcessingException, EmptyJsonExeption {
        objectMapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
        // convert JSON array to list of books
        // https://gorest.co.in/public/v2/users/3251/posts single post
        // https://gorest.co.in/public/v2/posts all posts

        try {
            userPosts = Arrays.asList(objectMapper.readValue(new URL(url), UserPost[].class));
            if (userPosts.isEmpty()) {
                throw new EmptyJsonExeption();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List<UserPost> getUserPostsByID(String id) {

        List<UserPost> posts = new ArrayList<>();

        for (UserPost userPost : userPosts) {
            if (validateFoundID(userPost, id)) {
                posts.add(userPost);
            }
        }

        // create file with post/posts
        CreatePostTextFile.createFile(FilePath.TEXTFILE, posts);

        return posts;
    }

    public List<UserPost> getAllPosts(){

        List<UserPost> posts = new ArrayList<>();

        for (UserPost userPost : userPosts) {
                posts.add(userPost);
        }

        // create file with post/posts
        CreatePostTextFile.createFile(FilePath.TEXTFILE, posts);

        return posts;
    }

    public boolean validateFoundID(UserPost post, String userInput) {
        return post.getUserId().equals(userInput) || post.getId().equals(userInput);
    }

    public boolean validateUserInput(String userInput) {
        String regex = "[0-9]+";
        Pattern patter = Pattern.compile(regex);

        if (userInput == null) {
            return false;
        }

        Matcher matcher = patter.matcher(userInput);
        return matcher.matches();
    }
}
