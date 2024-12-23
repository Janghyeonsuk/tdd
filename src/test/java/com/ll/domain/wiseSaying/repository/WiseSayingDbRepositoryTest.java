package com.ll.domain.wiseSaying.repository;

import com.ll.domain.wiseSaying.entity.WiseSaying;
import com.ll.global.app.AppConfig;
import com.ll.standard.dto.Pageable;
import com.ll.standard.util.Util;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

public class WiseSayingDbRepositoryTest {
    private static WiseSayingDbRepository wiseSayingRepository;

    @BeforeAll
    public static void beforeAll() {
        AppConfig.setTestMode();
        wiseSayingRepository = new WiseSayingDbRepository();

        wiseSayingRepository.dropTable();
        wiseSayingRepository.createTable();
    }

    @BeforeEach
    public void beforeEach() {
        wiseSayingRepository.truncateTable();
    }

    @Test
    public void t0() {

    }

    @Test
    @DisplayName("명언 저장")
    public void t1() {
        WiseSaying wiseSaying = new WiseSaying(0, "꿈을 지녀라. 그러면 어려운 현실을 이길 수 있다.", "괴테");
        wiseSayingRepository.save(wiseSaying);

        Optional<WiseSaying> opWiseSaying = wiseSayingRepository.findById(wiseSaying.getId());

        assertThat(
                opWiseSaying.get()
        ).isEqualTo(wiseSaying);
    }

    @Test
    @DisplayName("명언 삭제")
    public void t2() {
        WiseSaying wiseSaying = new WiseSaying(0, "꿈을 지녀라. 그러면 어려운 현실을 이길 수 있다.", "괴테");
        wiseSayingRepository.save(wiseSaying);

        wiseSayingRepository.deleteById(wiseSaying.getId());

        Optional<WiseSaying> opWiseSaying = wiseSayingRepository.findById(wiseSaying.getId());

        assertThat(
                opWiseSaying.isEmpty()
        ).isEqualTo(true);
    }

    @Test
    @DisplayName("명언 단건조회")
    public void t3() {
        WiseSaying wiseSaying = new WiseSaying(0, "꿈을 지녀라. 그러면 어려운 현실을 이길 수 있다.", "괴테");
        wiseSayingRepository.save(wiseSaying);

        Optional<WiseSaying> opWiseSaying = wiseSayingRepository.findById(wiseSaying.getId());

        assertThat(
                opWiseSaying.get()
        ).isEqualTo(wiseSaying);
    }

    @Test
    @DisplayName("명언 다건조회")
    public void t4() {
        WiseSaying wiseSaying1 = new WiseSaying(0, "꿈을 지녀라. 그러면 어려운 현실을 이길 수 있다.", "괴테");
        wiseSayingRepository.save(wiseSaying1);

        WiseSaying wiseSaying2 = new WiseSaying(0, "나의 삶의 가치는 나의 결정에 달려있다.", "아인슈타인");
        wiseSayingRepository.save(wiseSaying2);

        assertThat(
                wiseSayingRepository.findAll()
        ).containsExactly(wiseSaying2, wiseSaying1);
    }

    @Test
    @DisplayName("명언 수정")
    public void t5() {
        WiseSaying wiseSaying = new WiseSaying(0, "꿈을 지녀라. 그러면 어려운 현실을 이길 수 있다.", "괴테");
        wiseSayingRepository.save(wiseSaying);

        int wiseSayingId = wiseSaying.getId();

        wiseSaying.setContent("나의 삶의 가치는 나의 결정에 달려있다.");
        wiseSaying.setAuthor("아인슈타인");

        wiseSayingRepository.save(wiseSaying);

        // 수정을 해도 id가 변경되지 않는다는 것을 테스트
        assertThat(
                wiseSayingId
        ).isEqualTo(wiseSaying.getId());

        Optional<WiseSaying> opWiseSaying = wiseSayingRepository.findById(wiseSaying.getId());

        // 수정된 명언을 DB에서 조회하여 확인
        assertThat(
                opWiseSaying.get()
        ).isEqualTo(wiseSaying);
    }

    @Test
    @DisplayName("빌드를 하면 data.json 파일이 생성된다.")
    public void t6() {
        WiseSaying wiseSaying1 = new WiseSaying(0, "꿈을 지녀라. 그러면 어려운 현실을 이길 수 있다.", "괴테");
        wiseSayingRepository.save(wiseSaying1);

        WiseSaying wiseSaying2 = new WiseSaying(0, "나의 삶의 가치는 나의 결정에 달려있다.", "아인슈타인");
        wiseSayingRepository.save(wiseSaying2);

        wiseSayingRepository.archive(WiseSayingFileRepository.getArchiveDirPath());

        assertThat(
                Util.file.exists(WiseSayingFileRepository.getArchiveDirPath())
        ).isTrue();
    }

    @Test
    @DisplayName("빌드 시 생성되는 data.json은 배열의 형태이다.")
    public void t7() {
        WiseSaying wiseSaying1 = new WiseSaying(0, "꿈을 지녀라. 그러면 어려운 현실을 이길 수 있다.", "괴테");
        wiseSayingRepository.save(wiseSaying1);

        WiseSaying wiseSaying2 = new WiseSaying(0, "나의 삶의 가치는 나의 결정에 달려있다.", "아인슈타인");
        wiseSayingRepository.save(wiseSaying2);

        wiseSayingRepository.archive(WiseSayingFileRepository.getArchiveDirPath());

        String jsonStr = Util.file.get(WiseSayingFileRepository.getArchiveDirPath(), "");

        assertThat(
                jsonStr
        ).isEqualTo("""
                [
                    {
                        "id": 1,
                        "content": "꿈을 지녀라. 그러면 어려운 현실을 이길 수 있다.",
                        "author": "괴테"
                    },
                    {
                        "id": 2,
                        "content": "나의 삶의 가치는 나의 결정에 달려있다.",
                        "author": "아인슈타인"
                    }
                ]
                """.stripIndent().trim());
    }

    @Test
    @DisplayName("페이징 : count")
    public void t8() {
        WiseSaying wiseSaying1 = new WiseSaying(0, "꿈을 지녀라. 그러면 어려운 현실을 이길 수 있다.", "괴테");
        wiseSayingRepository.save(wiseSaying1);

        WiseSaying wiseSaying2 = new WiseSaying(0, "나의 삶의 가치는 나의 결정에 달려있다.", "아인슈타인");
        wiseSayingRepository.save(wiseSaying2);

        assertThat(
                wiseSayingRepository.count()
        ).isEqualTo(2);
    }

    @Test
    @DisplayName("페이징 : Pageable")
    public void t9() {
        WiseSaying wiseSaying1 = new WiseSaying(0, "꿈을 지녀라. 그러면 어려운 현실을 이길 수 있다.", "괴테");
        wiseSayingRepository.save(wiseSaying1);

        WiseSaying wiseSaying2 = new WiseSaying(0, "나의 삶의 가치는 나의 결정에 달려있다.", "아인슈타인");
        wiseSayingRepository.save(wiseSaying2);

        WiseSaying wiseSaying3 = new WiseSaying(0, "삶이 있는 한 희망은 있다.", "톨스토이");
        wiseSayingRepository.save(wiseSaying3);

        int page = 1;
        int itemsPerPage = 2;
        Pageable<WiseSaying> pageable = wiseSayingRepository.pageableAll(itemsPerPage, page);

        assertThat(pageable.getTotalItems())
                .isEqualTo(3);

        assertThat(pageable.getTotalPages())
                .isEqualTo(2);

        assertThat(pageable.getItemsPerPage())
                .isEqualTo(2);

        assertThat(pageable.getPage())
                .isEqualTo(1);

        assertThat(pageable.getContent())
                .containsExactly(wiseSaying3, wiseSaying2);
    }

    @Test
    @DisplayName("페이징(with 검색) : count")
    public void t10() {
        WiseSaying wiseSaying1 = new WiseSaying(0, "꿈을 지녀라. 그러면 어려운 현실을 이길 수 있다.", "괴테");
        wiseSayingRepository.save(wiseSaying1);

        WiseSaying wiseSaying2 = new WiseSaying(0, "나의 삶의 가치는 나의 결정에 달려있다.", "아인슈타인");
        wiseSayingRepository.save(wiseSaying2);

        assertThat(
                wiseSayingRepository.count("author", "괴")
        ).isEqualTo(1);
    }

    @Test
    @DisplayName("페이징(with 검색) : Pageable")
    public void t11() {
        WiseSaying wiseSaying1 = new WiseSaying(0, "꿈을 지녀라. 그러면 어려운 현실을 이길 수 있다.", "괴테");
        wiseSayingRepository.save(wiseSaying1);

        WiseSaying wiseSaying2 = new WiseSaying(0, "나의 삶의 가치는 나의 결정에 달려있다.", "아인슈타인");
        wiseSayingRepository.save(wiseSaying2);

        WiseSaying wiseSaying3 = new WiseSaying(0, "삶이 있는 한 희망은 있다.", "톨스토이");
        wiseSayingRepository.save(wiseSaying3);

        WiseSaying wiseSaying4 = new WiseSaying(0, "너 자신인 사랑은 지금 무엇을 하려 하는가?", "닐 도널드 윌시");
        wiseSayingRepository.save(wiseSaying4);

        int page = 1;
        int itemsPerPage = 2;
        Pageable<WiseSaying> pageable = wiseSayingRepository.pageable("content", "다", itemsPerPage, page);

        assertThat(pageable.getTotalItems())
                .isEqualTo(3);

        assertThat(pageable.getTotalPages())
                .isEqualTo(2);

        assertThat(pageable.getItemsPerPage())
                .isEqualTo(2);

        assertThat(pageable.getPage())
                .isEqualTo(1);

        assertThat(pageable.getKeywordType())
                .isEqualTo("content");

        assertThat(pageable.getKeyword())
                .isEqualTo("다");

        assertThat(pageable.getContent())
                .containsExactlyInAnyOrder(wiseSaying3, wiseSaying2);
    }
}