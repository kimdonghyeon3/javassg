import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.lang.constant.Constable;
import java.util.ArrayList;
import java.util.List;

class Util {

    public String listToJson(List<WiseSaying> list){
        String json = "[\n";

        for(WiseSaying w : list) {
            json += String.format("{\"id\":%d,\n\"content\":\"%s\",\n\"author\":\"%s\"\n},\n", w.id, w.content, w.author);
        }

        json = json.substring(0, json.length()-2);
        json += "\n]";

        return json;
    }

    //초기 json 괄호 제거
    public String intializeRemoveJsonBracket(String str){

        str = str.replace("\n","")
                .replace("[", "")
                .replace("]", "")
                .replace("{", "");

        return str;
    }

    //명언에 ,이 있는 것을 고려하여 id, content, author을 나누자
    public String[] splitIdContentAuthor(String str){

        String[] idBits = str.split(",",2);
        String[] contentAndAuthorBits = idBits[1].split("\",");
        String[] subBits = new String[idBits.length + contentAndAuthorBits.length];

        System.arraycopy(idBits, 0 , subBits, 0, idBits.length);
        System.arraycopy(contentAndAuthorBits, 0 , subBits, idBits.length, contentAndAuthorBits.length);

        return subBits;
    }

    //id, content, author과 value를 나누자
    public String[] splitNameValue(String str){
        String[] fieldBits = str.split(":");
        String[] fieldNameValue = new String[2];
        fieldNameValue[0] = fieldBits[0].replace("\"","")
                .replace("}", "");
        fieldNameValue[1] = fieldBits[1].replace("\"","")
                .replace("}", "");

        return fieldNameValue;
    }

    public List<WiseSaying> jsonToList(String json){

        List<WiseSaying> list = new ArrayList<>();

        if(json.equals("error"))
            return list;

        json = intializeRemoveJsonBracket(json);
        String[] bits = json.split("},");

        for(int i = 0 ; i < bits.length ; i++){

            String[] subBits = splitIdContentAuthor(bits[i]);
            WiseSaying wiseSaying = new WiseSaying(-1, "", "");

            for(int j = 0 ; j < subBits.length ; j++){

                String[] fieldNameValue = splitNameValue(subBits[j]);

                if (fieldNameValue[0].equals("id")) {
                    wiseSaying.id = (Integer.parseInt(fieldNameValue[1]));
                }else if (fieldNameValue[0].equals("content")) {
                    wiseSaying.content = fieldNameValue[1];
                }else if (fieldNameValue[0].equals("author")) {
                    wiseSaying.author = fieldNameValue[1];
                }

            }
            list.add(wiseSaying);
        }

        return list;
    }

    public void fileSaveByJson(String json, String fileName){

        try{
            OutputStream output = new FileOutputStream(fileName);
            output.write(json.getBytes());

            System.out.println("저장이 완료되었습니다.");
        }catch(Exception e){
            e.printStackTrace();
        }


    }

    public String fileLoadByJson(String fileName){

        String fileStr = "";

        try {
            String filePath = fileName; // 대상 파일
            FileInputStream fileStream = null; // 파일 스트림

            fileStream = new FileInputStream(filePath);

            byte[] readBuffer = new byte[fileStream.available()];
            while (fileStream.read(readBuffer) != -1) {
            }

            fileStr = new String(readBuffer);

            fileStream.close(); // 스트림 닫기
        } catch (Exception e) {
            e.getStackTrace();
        }

        return fileStr == "" ? "error": fileStr;
    }

}
