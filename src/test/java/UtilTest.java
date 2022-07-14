import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

public class UtilTest {

    List<WiseSaying> list;

    @BeforeEach
    public void before(){
        list = new ArrayList<>();
    }

    @Test
    public void listToJsonTest(){
        list.add(new WiseSaying(1,"명언1", "작가1"));
        list.add(new WiseSaying(2,"명언2", "작가2"));

        String json = new Util().listToJson(list);

        String str = "[\n{\"id\":1,\n\"content\":\"명언1\",\n\"author\":\"작가1\"\n}," +
                "\n{\"id\":2,\n\"content\":\"명언2\",\n\"author\":\"작가2\"\n}\n]";

        assertEquals(str,json);
    }

    @Test
    public void jsonToListTest(){
        String json = "[\n{\"id\":1,\n\"content\":\"명언1\",\n\"author\":\"작가1\"\n}," +
                "\n{\"id\":2,\n\"content\":\"명언2\",\n\"author\":\"작가2\"\n}\n]";

        List<WiseSaying> list = new Util().jsonToList(json);

        assertEquals(list.get(0).id,1);
        assertEquals(list.get(0).content,"명언1");
        assertEquals(list.get(0).author,"작가1");

    }

    @Test
    public void fileSaveByJsonTest(){
        String json = "[\n{\"id\":1,\n\"content\":\"명언1\",\n\"author\":\"작가1\"\n}," +
                "\n{\"id\":2,\n\"content\":\"명언2\",\n\"author\":\"작가2\"\n}\n]";


        new Util().fileSaveByJson(json,"test.json");

        File f = new File("test.json");
        String fileName = f.getName();

        assertEquals(fileName, "test.json");
    }

    @Test
    public void fileLoadByJsonTest(){
        WiseSayingRepository wiseSayingRepository = new WiseSayingRepository(false);

        String json = "[\n{\"id\":1,\n\"content\":\"명언1\",\n\"author\":\"작가1\"\n}," +
                "\n{\"id\":2,\n\"content\":\"명언2\",\n\"author\":\"작가2\"\n}\n]";
        String str = new Util().fileLoadByJson("test.json");

        assertEquals(json, str);
    }
}
