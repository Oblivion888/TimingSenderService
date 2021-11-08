package com.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class TaskDto {
    private int timeSpended;
    private String description;
}
