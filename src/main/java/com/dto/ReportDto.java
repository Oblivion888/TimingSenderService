package com.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class ReportDto {
    private List<StudentDto> studentDtos;
    private List<LectorDto> lectorDtos;

    public ReportDto() {
        this.studentDtos = new ArrayList<>();
        this.lectorDtos = new ArrayList<>();
    }
}
