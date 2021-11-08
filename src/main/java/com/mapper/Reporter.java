package com.mapper;

import com.dto.ReportDto;
import com.model.Report;
import com.model.Task;
import com.resttimeservice.TrackingServlet;
import com.soapcommandservice.CommandService;
import com.soapcommandservice.User;
import lombok.Getter;
import lombok.Setter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Getter
@Setter
public class Reporter {
    private TrackingServlet trackingServlet;
    private ReportDto reportDto;

    public Reporter() {
        this.trackingServlet = new TrackingServlet();
    }


    public ReportDto createReport() throws IOException {
        reportDto = new ReportDto();


        List<User> allUsers = CommandService.getAllUsers();
        TrackingServlet trackingServlet = new TrackingServlet();
        List<Report> timingReport = trackingServlet.getTimingReport();

        reportDto.setLectors(mapLector(allUsers));

        HashMap<User, List<Task>> userListHashMap = mapStundent(allUsers);


        addTaskToStudent(userListHashMap, timingReport);
        reportDto.setStudents(userListHashMap);

        for (Map.Entry<User, List<Task>> userListEntry : reportDto.getStudents().entrySet()) {
            System.out.println(userListEntry.getKey());
            System.out.println(userListEntry.getValue());
        }

        return reportDto;
    }

    private void addTaskToStudent(HashMap<User, List<Task>> students, List<Report> timingReport) {
        Map<Long, List<Report>> collect = timingReport.stream().collect(Collectors.groupingBy(Report::getUserId, Collectors.toList()));
       /* for (Map.Entry<Long, List<Report>> longListEntry : collect.entrySet()) {
            System.out.println(longListEntry.getKey());
            System.out.println(longListEntry.getValue());
        }*/

        for (Map.Entry<User, List<Task>> userTaskEntry : students.entrySet()) {

            int id = userTaskEntry.getKey().getId();
//
            List<Report> reports = collect.get((long) id);

//            System.out.println(reports);
            if (reports != null) {
                Report report = reports.get(0);
                List<Task> tasks = report.getTasks();
                userTaskEntry.setValue(tasks);
            }
        }


       /* for (StudentDto studentDto : studentDtos) {
            List<Report> reports = collect.get(studentDto.getChatID());
            if (reports != null) {
                Report report = reports.get(0);
                List<Task> tasks = report.getTasks();
                List<TaskDto> taskDtos = mapTaskToTaskDto(tasks);
                studentDto.setTaskDtos(taskDtos);
            }

        }*/
    }


    private HashMap<User, List<Task>> mapStundent(List<User> allUsers) {

        List<User> students = allUsers.stream()
                .filter(v -> v.getRole().equals("user") || v.getRole().equals("lead"))
                .collect(Collectors.toList());
        HashMap<User, List<Task>> userMap = new HashMap<>();
        for (User student : students) {
            userMap.put(student, new ArrayList<Task>());
        }

        return userMap;
    }

    private List<User> mapLector(List<User> allUsers) {
        return allUsers.stream().filter(v -> v.getRole().equals("admin"))
                .collect(Collectors.toList());


    }

}
