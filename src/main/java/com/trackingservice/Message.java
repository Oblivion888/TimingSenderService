package com.trackingservice;

import com.dto.ReportDto;
import com.model.Task;
import com.soapcommandservice.User;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

public class Message {
    String text;

    public Message(ReportDto reportDto) {
        StringBuilder message = new StringBuilder();
        for (Map.Entry<User, List<Task>> userListEntry : reportDto.getStudents().entrySet()) {
            User user = userListEntry.getKey();
            message.append("Tracking for " + LocalDate.now() + "\n");
            message.append(user.getFirstName() + " " + user.getLastName() + "\n");
            List<Task> value = userListEntry.getValue();
            if (value.size() != 0) {
                for (Task task : value) {
                    message.append("Task: " + task.getDescription() + " Spend time: " + task.getTimeSpended() + " hours" + "\n");
                }
            } else {
                message.append("There is no timing detected" + "\n");
            }
            message.append("--------------------------------------" + "\n");

        }

        this.text = message.toString();

    }
}
