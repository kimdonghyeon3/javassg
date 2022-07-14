import org.junit.jupiter.api.Test;

import java.io.*;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class AppTest {

    @Test
    public void bufferedReaderTest() throws IOException {
        String input = """
                등록
                명언1
                작가1
                """.stripIndent();

        InputStream in = new ByteArrayInputStream(input.getBytes());
        BufferedReader br = new BufferedReader(new InputStreamReader(in));

        String cmd = br.readLine();
        String content = br.readLine();
        String author = br.readLine();

        assertEquals("등록", cmd);
        assertEquals("명언1", content);
        assertEquals("작가1", author);
    }

    @Test
    public void getPrintOut() throws IOException{
        //표준 출력 리다이렉션
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        System.setOut(new PrintStream(output));

        System.out.println("안녕");

        String rs = output.toString().trim();

        //표준 출력 원상복구
        System.setOut(new PrintStream(new FileOutputStream(FileDescriptor.out)));
        output.close();

        assertEquals("안녕", rs);
    }

    @Test
    void startPrintOut() throws IOException{

        String rs = AppTestRunner.run("""
                종료
                """);;

        assertTrue(rs.contains("== 명언 SSG =="));
        assertTrue(rs.contains("명령) "));
    }

}
