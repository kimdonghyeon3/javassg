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

        JsonParsing jsonParsing = new JsonParsing(wiseSayings);

        String wiseSayingsJson = jsonParsing.getJson();

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
            JsonParsing jsonParsing = new JsonParsing();
            list = jsonParsing.applyJson(rs);


            fileStream.close(); // 스트림 닫기
        } catch (Exception e) {
            e.getStackTrace();
        }


        return list;

    }
}