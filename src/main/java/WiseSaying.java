public class WiseSaying {
    private int id;
    private String author;
    private String content;

    public int getId() {
        return id;
    }

    public String getAuthor() {
        return author;
    }

    public String getContent() {
        return content;
    }

    @Override
    public String toString() {
        return "WiseSaying{" +
                "id=" + id +
                ", author='" + author + '\'' +
                ", content='" + content + '\'' +
                '}';
    }

    public WiseSaying(int num, String author, String content) {
        this.id = num;
        this.author = author;
        this.content = content;
    }
}
