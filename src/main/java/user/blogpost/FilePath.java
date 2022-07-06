package user.blogpost;

public enum FilePath {
    JSONFILE("src/main/java/user/blogpost/userblogPosts.json"),
    TEXTFILE("src/main/java/user/blogpost/userpost.txt");

    public final String value;

    private FilePath(String value) {
        this.value = value;
    }
}
