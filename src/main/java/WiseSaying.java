public class WiseSaying {
    private int id;
    private String author;
    private String content;

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
