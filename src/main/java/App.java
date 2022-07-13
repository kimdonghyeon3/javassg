import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class App {
    private final BufferedReader br;
    private WiseSayingController wiseSayingController;


    public App() {
        br = new BufferedReader(new InputStreamReader(System.in));
        wiseSayingController = new WiseSayingController(br);
    }

    public void run() throws IOException {
        System.out.println("== 명언 SSG ==");

        outer:
        while (true) {
            System.out.printf("명령) ");
            String cmd = br.readLine().trim();

            Rq rq = new Rq(cmd);

            switch (rq.getPath()) {
                case "등록":
                    wiseSayingController.write(rq);
                    break;
                case "삭제":
                    wiseSayingController.remove(rq);
                    break;
                case "목록":
                    wiseSayingController.list(rq);
                    break;
                case "수정":
                    wiseSayingController.edit(rq);
                    break;
                case "종료":
                    break outer;
                case "빌드":
                    wiseSayingController.build();
                    break;
            }
        }

        br.close();
    }
}