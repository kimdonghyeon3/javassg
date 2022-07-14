import java.util.List;

public class WiseSayingService {

    private WiseSayingRepository wiseSayingRepository;

    public WiseSayingService(boolean flag) {

        wiseSayingRepository = new WiseSayingRepository(flag);
    }

    public WiseSaying write(String content, String author) {
        return wiseSayingRepository.write(content, author);
    }

    public boolean edit(int id, String content, String author) {
        return wiseSayingRepository.edit(id, content, author);
    }

    public boolean remove(int id) {
        return wiseSayingRepository.remove(id);
    }

    public WiseSaying findById(int id) {
        return wiseSayingRepository.findById(id);
    }

    public List<WiseSaying> findAll() {
        return wiseSayingRepository.findAll();
    }

    public void build() {
        wiseSayingRepository.build();
    }
}
