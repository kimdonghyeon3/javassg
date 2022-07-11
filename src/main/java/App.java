import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class App {
    private final BufferedReader br;
    private final List<WiseSaying> wiseSayings;
    private int wiseSayingLastId;

    public App() {
        br = new BufferedReader(new InputStreamReader(System.in));
        wiseSayings = new ArrayList<>();
        wiseSayingLastId = 0;
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
                    write(rq);
                    break;
                case "삭제":
                    remove(rq);
                    break;
                case "목록":
                    list(rq);
                    break;
                case "수정":
                    edit(rq);
                    break;
                case "종료":
                    break outer;
            }
        }

        br.close();
    }

    private void edit(Rq rq) throws IOException {

        int paramId = rq.getIntParam("id", 0);

        // URL에 입력된 id가 없다면 작업중지
        if (paramId == 0) {
            System.out.println("id를 입력해주세요.");
            return;
        }

        // URL에 입력된 id에 해당하는 명언객체 찾기
        WiseSaying foundWiseSaying = findById(paramId);

        // 찾지 못했다면 중지
        if (foundWiseSaying == null) {
            System.out.printf("%d번 명언은 존재하지 않습니다.\n", paramId);
            return;
        }

        System.out.println("기존 명언 : " + foundWiseSaying.content);
        System.out.print("새 명언 : ");
        String newContent = br.readLine().trim();

        // 입력된 id에 해당하는 명언객체를 리스트에서 수정
        foundWiseSaying.content = newContent;
        int index = indexFindById(paramId);

        //찾으려는 명언이 없다면 사실 이 상화은 말이 안되긴함.. 찾았을 때부터 오류가 났어야되는데
        //혹시 몰라서 넣어둠
        if(index == -1) {
            System.out.println("변경하려는 명언이 없습니다.");
            return;
        }

        wiseSayings.set(index,foundWiseSaying);

        System.out.printf("%d번 명언이 수정되었습니다.\n", paramId);
    }

    private void list(Rq rq) {
        System.out.println("번호 / 작가 / 명언");
        System.out.println("-------------------");
        for (int i = wiseSayings.size() - 1; i >= 0; i--) {
            WiseSaying wiseSaying_ = wiseSayings.get(i);
            System.out.printf("%d / %s / %s\n", wiseSaying_.id, wiseSaying_.content, wiseSaying_.author);
        }
    }

    private void write(Rq rq) throws IOException {
        System.out.printf("명언 : ");
        String content = br.readLine().trim();
        System.out.printf("작가 : ");
        String author = br.readLine().trim();
        int id = ++wiseSayingLastId; // 명언 글 번호 증가

        WiseSaying wiseSaying = new WiseSaying(id, content, author);
        wiseSayings.add(wiseSaying);

        System.out.printf("%d번 명언이 등록되었습니다.\n", id);
    }

    private void remove(Rq rq) {
        // URL에 입력된 id 얻기
        int paramId = rq.getIntParam("id", 0);

        // URL에 입력된 id가 없다면 작업중지
        if (paramId == 0) {
            System.out.println("id를 입력해주세요.");
            return;
        }

        // URL에 입력된 id에 해당하는 명언객체 찾기
        WiseSaying foundWiseSaying = findById(paramId);

        // 찾지 못했다면 중지
        if (foundWiseSaying == null) {
            System.out.printf("%d번 명언은 존재하지 않습니다.\n", paramId);
            return;
        }

        // 입력된 id에 해당하는 명언객체를 리스트에서 삭제
        wiseSayings.remove(foundWiseSaying);

        System.out.printf("%d번 명언이 삭제되었습니다.\n", paramId);
    }

    private WiseSaying findById(int paramId) {
        for (WiseSaying wiseSaying : wiseSayings) {
            if (wiseSaying.id == paramId) {
                return wiseSaying;
            }
        }

        return null;
    }

    private int indexFindById(int paramId) {

        for(int i = 0 ; i < wiseSayings.size() ; i++){
            if(wiseSayings.get(i).id == paramId)
                return i;
        }

        return -1;
    }
}