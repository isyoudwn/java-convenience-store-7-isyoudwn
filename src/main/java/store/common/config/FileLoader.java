package store.common.config;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.lang.reflect.Constructor;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class FileLoader<T> {
    private final String filePath;
    private final Class<T> clazz;

    public FileLoader(String filePath, Class<T> clazz) {
        this.filePath = filePath;
        this.clazz = clazz;
    }

    public List<T> loadFileData(DateTimeFormatter dateFormatter) {
        List<T> resultList = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(
                new InputStreamReader(getClass().getClassLoader().getResourceAsStream(filePath)))) {
            br.lines().skip(1).forEach(line -> resultList.add(createObject(line.split(","), dateFormatter)));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return resultList;
    }

    private T createObject(String[] values, DateTimeFormatter dateFormatter) {
        try {
            Constructor<T> constructor = clazz.getConstructor(String.class, Integer.class, Integer.class, LocalDate.class, LocalDate.class);
            return constructor.newInstance(values[0], Integer.parseInt(values[1]), Integer.parseInt(values[2]),
                    LocalDate.parse(values[3], dateFormatter), LocalDate.parse(values[4], dateFormatter));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
