package com.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Setter
@Getter
@ToString
public class StudentDto {
    String firstName;
    String lastName;
    Long chatID;
    List<TaskDto> taskDtos;

}
