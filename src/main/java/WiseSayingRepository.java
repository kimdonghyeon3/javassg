import java.util.List;

public class WiseSayingRepository {

    public List<WiseSaying> wiseSayings;
    public int wiseSayingLastId;
    public Util util;

    public WiseSayingRepository() {
        util = new Util();

        String json = util.fileLoadByJson("data.json");
        this.wiseSayings = util.jsonToList(json);

        wiseSayingLastId = wiseSayings.size()== 0 ? 0 : wiseSayings.get(wiseSayings.size()-1).id;
    }

    public void build(){
        String json = util.listToJson(wiseSayings);
        util.fileSaveByJson(json, "data.json");
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

    public WiseSaying findById(int paramId) {
        for (WiseSaying wiseSaying : wiseSayings) {
            if (wiseSaying.id == paramId) {
                return wiseSaying;
            }
        }

        return null;
    }
}
