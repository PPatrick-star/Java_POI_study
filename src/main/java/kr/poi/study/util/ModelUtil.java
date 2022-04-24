package kr.poi.study.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.function.Predicate;

public class ModelUtil {

    public static final ObjectMapper objectMapper = new ObjectMapper();

    public static Predicate<Throwable> is(final HttpStatus status) {
        return thr -> {
            ResponseStatus statusAnnotation = thr.getClass().getAnnotation(ResponseStatus.class);
            if (statusAnnotation == null) return false;
            return status.equals(statusAnnotation.value());
        };
    }

    public static <T> List<T> readModelList(Class<?> elementClass, String path) {

        File givenModelFile = new File(path);
        if (givenModelFile.exists()) {
            try {
                return objectMapper.readValue(givenModelFile,
                        objectMapper.getTypeFactory().constructCollectionType(List.class, elementClass));
            } catch (IOException e) {
                //log.error("can't convert json", e);
                throw new IllegalStateException(e);
            }
        } else {
            throw new IllegalArgumentException();
        }
    }

    public static <T> T readModelVo(Class<?> elementClass, String path) {

        File givenModelFile = new File(path);
        if (givenModelFile.exists()) {
            try {
                return objectMapper.readValue(givenModelFile,
                        objectMapper.getTypeFactory().constructType(elementClass));
            } catch (IOException e) {
                //log.error("can't convert json", e);
                throw new IllegalStateException(e);
            }
        } else {
            throw new IllegalArgumentException();
        }
    }
}
