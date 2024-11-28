package com.ll.domain.wiseSaying.repository;

import com.ll.domain.wiseSaying.entity.WiseSaying;
import com.ll.standard.util.Util;

import java.io.IOException;
import java.nio.file.NoSuchFileException;
import java.util.List;
import java.util.Optional;

public class WiseSayingFileRepository implements WiseSayingRepository {
    public final static String DIR_PATH = "db/test/wiseSaying";
    public final static String LAST_ID_PATH = "db/test/wiseSaying/lastId.txt";

    public WiseSayingFileRepository() {
    }


    public WiseSaying save(WiseSaying wiseSaying) {
        boolean isNew = wiseSaying.isNew();

        if (isNew) {
            wiseSaying.setId(getLastId() + 1);
        }

        String jsonStr = wiseSaying.toJsonStr();

        Util.file.set(getFilePath(wiseSaying.getId()), jsonStr);

        if (isNew) {
            setLastId(wiseSaying.getId());
        }

        return wiseSaying;
    }

    public boolean deleteById(int id) {
        return Util.file.delete(getFilePath(id));
    }

    public Optional<WiseSaying> findById(int id) {
        String filePath = getFilePath(id);

        if (Util.file.notExists(filePath)) {
            return Optional.empty();
        }

        String jsonStr = Util.file.get(filePath, "");

        if (jsonStr.isEmpty()) {
            return Optional.empty();
        }

        return Optional.of(new WiseSaying(jsonStr));
    }

    public List<WiseSaying> findAll() {
        try {
            return Util.file.walkRegularFiles(
                            DIR_PATH,
                            "\\d+\\.json"
                    )
                    .map(path -> Util.file.get(path.toString(), ""))
                    .map(WiseSaying::new)
                    .toList();
        } catch (NoSuchFileException e) {
            return List.of();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public int getLastId() {
        return Util.file.getAsInt(getLastIdPath(), 0);
    }

    private void setLastId(int id) {
        Util.file.set(getLastIdPath(), id);
    }


    public static String getFilePath(int id) {
        return DIR_PATH + "/" + id + ".json";
    }

    public static String getLastIdPath() {
        return LAST_ID_PATH;
    }

    public static void dropTable() {
        Util.file.rmdir(WiseSayingFileRepository.DIR_PATH);
    }
}
