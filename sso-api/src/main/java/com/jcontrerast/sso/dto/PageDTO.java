package com.jcontrerast.sso.dto;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.domain.Sort.Direction;

@Getter
@Setter
public class PageDTO {
    private Integer page;
    private Integer size;
    private String sort;
    private Direction order;

    @JsonProperty
    public void setOrder(String order) {
        this.order = Direction.fromString(order);
    }
}
