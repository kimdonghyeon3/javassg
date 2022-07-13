import java.util.ArrayList;
import java.util.List;

class Util {

    private String str = "";

    public Util() {

    }

    public Util(List<WiseSaying> list) {
        str += "[\n";
        for(WiseSaying w : list){
            str += String.format("{\"id\":%d,\n\"content\":\"%s\",\n\"author\":\"%s\"\n},\n", w.id, w.content, w.author);
        }
        str = str.substring(0, str.length()-2);
        str+= "\n]";
    }

    public String getJson(){
        return str;
    }

    public List<WiseSaying> applyJson(String rs) {

        System.out.println("rs = " + rs);

        List<WiseSaying> list = new ArrayList<>();

        rs = rs.replace("\n","");
        rs = rs.replace("[", "");
        rs = rs.replace("]", "");
        rs = rs.replace("{", "");

        String[] bits = rs.split("},");

        for(int i = 0 ; i < bits.length ; i++){
            String[] subBits = bits[i].split(",");

            int id = -1;
            String content = "";
            String author = "";
            System.out.println("bits.length = " + bits.length);

            for(int j = 0 ; j < subBits.length ; j++){

                System.out.println("subBits.length = " + subBits.length);


                String[] fieldBits = subBits[j].split(":");

                String fieldName = fieldBits[0].replace("\"","");
                String fieldValue = fieldBits[1].replace("\"","");

                System.out.println("fieldName = " + fieldName);
                System.out.println("fieldValue = " + fieldValue);

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
