import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class WiseSayingController {


    private BufferedReader br;
    WiseSayingService wiseSayingService;

    public WiseSayingController(BufferedReader br) {
        wiseSayingService = new WiseSayingService();
        this.br = br;
    }

    public void edit(Rq rq) throws IOException {

        int id = rq.getIntParam("id", 0);

        if (id == 0) {
            System.out.println("번호를 입력해주세요.");
            return;
        }

        WiseSaying wiseSaying = wiseSayingService.findById(id);

        if (wiseSaying == null) {
            System.out.printf("%d번 명언은 존재하지 않습니다.\n", id);
            return;
        }

        System.out.printf("명언(기존) : %s\n", wiseSaying.content);
        System.out.print("명언 : ");
        String content = br.readLine();

        System.out.printf("작가(기존) : %s\n", wiseSaying.author);
        System.out.print("작가 : ");
        String author = br.readLine();

        wiseSayingService.edit(id, content, author);

        System.out.printf("%d번 명언이 수정되었습니다.\n", id);
    }

    public void list(Rq rq) {
        System.out.println("번호 / 작가 / 명언");
        System.out.println("----------------------");

        List<WiseSaying> wiseSayings = wiseSayingService.findAll();

        for (int i = wiseSayings.size() - 1; i >= 0; i--) {
            WiseSaying wiseSaying = wiseSayings.get(i);

            System.out.printf("%d / %s / %s\n", wiseSaying.id, wiseSaying.author, wiseSaying.content);
        }
    }

    public void write(Rq rq) throws IOException {
        System.out.printf("명언 : ");
        String content = br.readLine().trim();
        System.out.printf("작가 : ");
        String author = br.readLine().trim();

        WiseSaying wiseSaying = wiseSayingService.write(content, author);

        System.out.printf("%d번 명언이 등록되었습니다.\n", wiseSaying.id);
    }

    public void remove(Rq rq) {
        int id = rq.getIntParam("id", 0);

        if (id == 0) {
            System.out.println("번호를 입력해주세요.");
            return;
        }

        WiseSaying wiseSaying = wiseSayingService.findById(id);

        if (wiseSaying == null) {
            System.out.printf("%d번 명언은 존재하지 않습니다.\n", id);
            return;
        }

        wiseSayingService.remove(wiseSaying.id);

        System.out.printf("%d번 명언이 삭제되었습니다.\n", id);
    }

    public void build() {
        wiseSayingService.build();
    }
}
