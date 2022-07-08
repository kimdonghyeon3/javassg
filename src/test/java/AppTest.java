import org.junit.jupiter.api.Test;

import java.io.*;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AppTest {

    @Test
    public void 테스트_실험(){
        int rs = 10 + 20;
        assertEquals(30,rs);
    }

    //사용자 입력을 테스트하는 법!
    @Test
    public void 테스트_스캐너() throws IOException {
        String input = """
                등록
                명언1
                작가1
                """.stripIndent();
        InputStream in = new ByteArrayInputStream(input.getBytes());

        BufferedReader br = new BufferedReader(new InputStreamReader(in));

        Scanner sc = new Scanner(in);

        String cmd = sc.nextLine().trim();
        String content = sc.nextLine().trim();
        String author = sc.nextLine().trim();

        assertEquals("등록",cmd);
        assertEquals("명언1",content);
        assertEquals("작가1",author);


//        String cmd1 = br.readLine();
//        String content1 = br.readLine();
//        String author1 = br.readLine();
//
//        assertEquals("등록",cmd1);
//        assertEquals("명언1",content1);
//        assertEquals("작가1",author1);

    }

    @Test
    public void 표준출력을_리다이렉션하여_결과를_문자열로_받기() throws IOException {
        // 표준출력을 리다이렉션
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        System.setOut(new PrintStream(output));

        System.out.println("안녕");

        String rs = output.toString().trim();

        // 표준출력을 원상복구
        System.setOut(new PrintStream(new FileOutputStream(FileDescriptor.out)));
        output.close();

        assertEquals("안녕", rs);
    }

}
