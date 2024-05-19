package com.jcontrerast.sso.utils;

import com.jcontrerast.sso.dto.PageDTO;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.lang.reflect.Field;
import java.util.Optional;

@Log4j2
public class Utils {
    public static <T> void copyNonNull(T source, T target) {
        try {
            Class<?> clazz = source.getClass();
            Field[] fields = clazz.getDeclaredFields();

            for (Field field : fields) {
                field.setAccessible(true);
                Object value = field.get(source);

                if (value != null) {
                    field.set(target, value);
                }
            }
        } catch (Exception e) {
            log.error("ERROR: {}", e.getLocalizedMessage());
            log.error("An error occurred while copying the object");
            throw new RuntimeException(e);
        }
    }

    public static Pageable getPageable(PageDTO pageDTO) {
        if (pageDTO == null) {
            return PageRequest.of(Constants.DEFAULT_PAGE_NUMBER, Constants.DEFAULT_PAGE_SIZE);
        }

        int page = Optional.ofNullable(pageDTO.getPage()).orElse(Constants.DEFAULT_PAGE_NUMBER);
        int size = Optional.ofNullable(pageDTO.getSize()).orElse(Constants.DEFAULT_PAGE_SIZE);

        if (pageDTO.getSort() != null && !pageDTO.getSort().isBlank()) {
            if (Sort.Direction.ASC.equals(pageDTO.getOrder())) {
                return PageRequest.of(page, size, Sort.Direction.ASC, pageDTO.getSort());
            } else {
                return PageRequest.of(page, size, Sort.Direction.DESC, pageDTO.getSort());
            }
        }
        return PageRequest.of(page, size);
    }
}
