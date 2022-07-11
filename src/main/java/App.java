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

            Rq rq = new Rq(cmd);

            switch( rq.getPath() ) {
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
                    // URL에 입력된 id 얻기
                    int paramId = rq.getIntParam("id", 0);

                    // URL에 입력된 id가 없다면 작업중지
                    if (paramId == 0) {
                        System.out.println("id를 입력해주세요.");
                        continue;
                    }

                    // URL에 입력된 id에 해당하는 명언객체 찾기
                    WiseSaying wiseSaying__ = null;

                    for (WiseSaying wiseSaying___ : list) {
                        if (wiseSaying___.getId() == paramId) {
                            wiseSaying__ = wiseSaying___;
                        }
                    }

                    // 찾지 못했다면 중지
                    if (wiseSaying__ == null) {
                        System.out.printf("%d번 명언은 존재하지 않습니다..\n", paramId);
                        continue;
                    }

                    // 입력된 id에 해당하는 명언객체를 리스트에서 삭제
                    list.remove(wiseSaying__);

                    System.out.printf("%d번 명언이 삭제되었습니다.\n", paramId);
                    break;

                case "수정":

            }
        }
    }
}