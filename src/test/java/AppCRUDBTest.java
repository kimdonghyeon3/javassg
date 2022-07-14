import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class AppCRUDBTest {

    @Test
    void 등록테스트() throws IOException {
        String rs = AppTestRunner.run("""
                등록
                이순신
                나의 죽음을 적에게 알리지 마라.
                종료
                """);

        assertTrue(rs.contains("명언 : "));
        assertTrue(rs.contains("작가 : "));
    }

    @Test
    void 등록문구테스트() throws IOException {
        String rs = AppTestRunner.run("""
                등록
                이순신
                나의 죽음을 적에게 알리지 마라.
                등록
                나폴레옹
                내 사전에 불가능이란 없다.
                종료
                """);

        assertTrue(rs.contains("1번 명언이 등록되었습니다."));
        assertTrue(rs.contains("2번 명언이 등록되었습니다."));
    }

    @Test
    void 삭제테스트(){
        String input = "종료";
    }

    @Test
    void 목록테스트() throws IOException {
        String rs = AppTestRunner.run("""
                등록
                나의 죽음을 적들에게 알리지 말라
                이순신
                등록
                나에게 불가능이란 없다.
                나폴레옹
                목록
                종료
                """);

        assertTrue(rs.contains("번호 / 작가 / 명언"));
        assertTrue(rs.contains("----------------------"));
        assertTrue(rs.contains("2 / 나폴레옹 / 나에게 불가능이란 없다."));
        assertTrue(rs.contains("1 / 이순신 / 나의 죽음을 적들에게 알리지 말라"));
    }

    @Test
    void 수정테스트() throws IOException {
        String rs = AppTestRunner.run("""
                등록
                나의 죽음을 적들에게 알리지 말라
                이순신
                등록
                나에게 불가능이란 없다.
                나폴레옹
                목록
                수정?id=1
                나의 죽음을 적들에게 알리지 마라!
                이순신장군
                목록
                종료
                """);

        assertTrue(rs.contains("1 / 이순신 / 나의 죽음을 적들에게 알리지 말라"));
        assertTrue(rs.contains("1 / 이순신장군 / 나의 죽음을 적들에게 알리지 마라!"));
    }

    @Test
    void 종료테스트() throws IOException {
        //여기는 사실 종료만 되기 때문에 종료를 눌렀을 때 처음 뜨는 것만
        //뜨고 무한루프에 걸리지 않으면 성공
        String rs = AppTestRunner.run("""
                종료
                """);

        assertTrue(rs.contains("== 명언 SSG =="));
        assertTrue(rs.contains("명령) "));
    }
}
