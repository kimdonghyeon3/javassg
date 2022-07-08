import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class App {

    public void run() throws IOException {
        System.out.println("===== 명언 SSG ====");
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        List<WiseSaying> list = new ArrayList<>();
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
                    System.out.println(num +"번 명언이 저장되었습니다.");

                    WiseSaying wiseSaying = new WiseSaying(num++, author, content);
                    list.add(wiseSaying);

                    break;

                case "목록":
                    System.out.println(" 본호  //  작가  //  명언");
                    System.out.println("------------------------");
                    for(WiseSaying t : list){
                        System.out.printf(" %s  // %s  //  %s\n",t.getId(), t.getAuthor(), t.getContent());
                    }
                    break;
                case "삭제":
                case "수정":

            }
        }
    }
}