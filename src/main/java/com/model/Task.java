package com.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class Task {
    int id;
    float timeSpended;
    String description;
    int reportId;


    @Override
    public String toString() {
        return "Task{" +
                "id=" + id +
                ", timeSpended=" + timeSpended +
                ", description='" + description + '\'' +
                ", reportId=" + reportId +
                '}';
    }
}
