package com.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class TaskDto {
    private float timeSpended;
    private String description;
}
