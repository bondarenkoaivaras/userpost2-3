package url.user.blogpost;

public enum FilePath {
    TEXTFILE("src/main/java/url/user/blogpost/userpost.txt");

    public final String value;

    private FilePath(String value) {
        this.value = value;
    }
}
