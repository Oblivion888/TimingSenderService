package com.trackingservice;

import com.dto.ReportDto;
import com.dto.StudentDto;
import com.dto.TaskDto;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public class Message {
    String text;

    public Message(ReportDto reportDto) {
        List<StudentDto> studentDtos = reportDto.getStudentDtos();
        StringBuilder message = new StringBuilder();
        message.append("Report for " + LocalDate.now() + ":" + "\n");

        for (StudentDto studentDto : studentDtos) {
            message.append(studentDto.getFirstName() + " ");
            message.append(studentDto.getLastName() + "\n");
            if (studentDto.getTaskDtos() != null) {
                for (TaskDto taskDto : studentDto.getTaskDtos()) {
                    message.append(taskDto.getDescription() + "/" + taskDto.getTimeSpended() + "\n");
                }

            } else {
                message.append("There is no tracking");
            }
            message.append("\n" + "//------------------------------------------------------------" + "\n");
        }
        this.text = message.toString();
    }
}
