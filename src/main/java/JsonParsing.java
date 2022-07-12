import java.util.ArrayList;
import java.util.List;

class JsonParsing {

    private String str = "";

    public JsonParsing() {

    }

    public JsonParsing(List<WiseSaying> list) {
        str += "[\n";
        for(WiseSaying w : list){
            str += String.format("{\"id\":%d,\n\"content\":%s,\n\"author\":%s\n}\n", w.id, w.content, w.author);
        }
        str+= "]";
    }

    public String getJson(){
        return str;
    }

    public List<WiseSaying> applyJson(String rs) {

        List<WiseSaying> list = new ArrayList<>();

        rs = rs.replace("\n","");
        rs = rs.replace("[", "");
        rs = rs.replace("]", "");
        rs = rs.replace("{", "");

        String[] bits = rs.split("}");

        for(int i = 0 ; i < bits.length ; i++){
            String[] subBits = bits[i].split(",");

            int id = -1;
            String content = "";
            String author = "";

            for(int j = 0 ; j < subBits.length ; j++){


                String[] fieldBits = subBits[j].split(":");

                String fieldName = fieldBits[0].replace("\"","");
                String fieldValue = fieldBits[1];

                if (fieldName.equals("id")) {
                    id = (Integer.parseInt(fieldValue));
                }
                if (fieldName.equals("content")) {
                    content = fieldValue;
                }
                if (fieldName.equals("author")) {
                    author = fieldValue;
                }

            }

            list.add(new WiseSaying(id,content,author));
        }

        return list;
    }
}
