import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

public class WiseSayingRepository {

    public List<WiseSaying> wiseSayings;
    public int wiseSayingLastId;

    public WiseSayingRepository() {
        this.wiseSayings = load();
        wiseSayingLastId = wiseSayings.size()== 0 ? 0 : wiseSayings.get(wiseSayings.size()-1).id;
    }

    public WiseSaying findById(int paramId) {
        for (WiseSaying wiseSaying : wiseSayings) {
            if (wiseSaying.id == paramId) {
                return wiseSaying;
            }
        }

        return null;
    }

    public void build(){

        Util util = new Util(wiseSayings);

        String wiseSayingsJson = util.getJson();

        try{
            OutputStream output = new FileOutputStream("data.json");
            output.write(wiseSayingsJson.getBytes());
        }catch(Exception e){
            e.printStackTrace();
        }
        System.out.println("저장이 완료되었습니다.");

    }

    private List<WiseSaying> load(){

        List<WiseSaying> list = new ArrayList<>();

        try {
            String filePath = "data.json"; // 대상 파일
            FileInputStream fileStream = null; // 파일 스트림

            fileStream = new FileInputStream(filePath);

            byte[] readBuffer = new byte[fileStream.available()];
            while (fileStream.read(readBuffer) != -1) {
            }

            String rs = new String(readBuffer);
            Util util = new Util();
            list = util.applyJson(rs);


            fileStream.close(); // 스트림 닫기
        } catch (Exception e) {
            e.getStackTrace();
        }


        return list;

    }

    public WiseSaying write(String content, String author) {

        int id = ++wiseSayingLastId;
        WiseSaying wiseSaying = new WiseSaying(id, content, author);
        wiseSayings.add(wiseSaying);

        return wiseSaying;
    }

    public boolean edit(int id, String content, String author) {
        WiseSaying wiseSaying = findById(id);

        if (wiseSaying == null) {
            return false;
        }

        wiseSaying.content = content;
        wiseSaying.author = author;

        return true;
    }

    public boolean remove(int id) {
        WiseSaying wiseSaying = findById(id);

        if (wiseSaying == null) {
            return false;
        }

        wiseSayings.remove(wiseSaying);

        return true;
    }

    public List<WiseSaying> findAll() {
        return wiseSayings;
    }
}
