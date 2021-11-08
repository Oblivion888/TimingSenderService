package com.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Date;
import java.util.List;

/**
 * Student's table entity.
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Report {

    int id;
    Date date;
    long userId;
    List<Task> tasks;


    @Override
    public String toString() {
        return "Report{" +
                "id=" + id +
                ", date=" + date +
                ", userId='" + userId + '\'' +
                ", tasks=" + tasks +
                '}';
    }
}
