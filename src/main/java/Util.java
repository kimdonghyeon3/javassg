import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.OutputStream;
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

    public List<WiseSaying> jsonToList(String json){


        System.out.println("json = " + json);
        if(json.equals("error"))
            return new ArrayList<>();

        List<WiseSaying> list = new ArrayList<>();

        json = json.replace("\n","")
                .replace("[", "")
                .replace("]", "")
                .replace("{", "");

        String[] bits = json.split("},");

        for(int i = 0 ; i < bits.length ; i++){

            String[] idBits = bits[i].split(",",2);
            String[] contentBits = idBits[1].split("\",");
            String[] subBits = new String[idBits.length + contentBits.length];

            System.arraycopy(idBits, 0 , subBits, 0, idBits.length);
            System.arraycopy(contentBits, 0 , subBits, idBits.length, contentBits.length);
            //String[] subBits = bits[i].split(",",1);

            int id = -1;
            String content = "";
            String author = "";;

            for(int j = 0 ; j < subBits.length ; j++){

                String[] fieldBits = subBits[j].split(":");

                String fieldName = fieldBits[0].replace("\"","")
                        .replace("}", "");
                String fieldValue = fieldBits[1].replace("\"","");

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
