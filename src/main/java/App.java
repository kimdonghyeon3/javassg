import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;

public class App {

    public void run() throws IOException {
        System.out.println("===== 명언 SSG ====");
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int num = 1;

        outer : while(true){
            System.out.print(" 명령) ");
            String cmd = br.readLine().trim();

            switch( cmd ) {
                case "종료":
                    break outer;
                case "등록":
                    String content;
                    String author;
                    System.out.print(" 명언 : ");
                    content = br.readLine().trim();
                    System.out.print(" 작가 : ");
                    author = br.readLine().trim();
                    System.out.println(num++ +"번 명언이 저장되었습니다.");

                    WiseSaying wiseSaying = new WiseSaying(num, author, content);

                    break;

                case "목록":
                case "삭제":
                case "수정":

            }
        }
    }
}