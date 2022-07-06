package user.blogpost;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ReadJSONFile {
    private final ObjectMapper objectMapper;
    private List<UserPost> userPosts;

    ReadJSONFile() {
        this.objectMapper = new ObjectMapper();
        this.userPosts = null;
    }

    public void read(String filePath) throws JsonProcessingException, EmptyJsonFileExeption {
        objectMapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
        // convert JSON array to list of books
        try {
            userPosts = Arrays.asList(objectMapper.readValue(new File(filePath), UserPost[].class));
            if (userPosts.isEmpty()) {
                throw new EmptyJsonFileExeption();
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
