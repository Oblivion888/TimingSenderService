package com.dto;

import com.model.Task;
import com.soapcommandservice.User;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Getter
@Setter
public class ReportDto {
    private Map<User, List<Task>> students;
    private List<User> lectors;

    public ReportDto() {
        this.students = new HashMap<>();
        this.lectors = new ArrayList<>();
    }
}
