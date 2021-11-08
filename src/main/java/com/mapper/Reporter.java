package com.mapper;

import com.dto.LectorDto;
import com.dto.ReportDto;
import com.dto.StudentDto;
import com.dto.TaskDto;
import com.model.Report;
import com.model.Task;
import com.resttimeservice.TrackingServlet;
import com.soapcommandservice.CommandService;
import com.soapcommandservice.User;
import lombok.Getter;
import lombok.Setter;

import java.io.IOException;
import java.util.ArrayList;
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

        List<LectorDto> lectorDtos = mapLector(allUsers);
        List<StudentDto> studentDtos = mapStundent(allUsers);

        reportDto.setStudentDtos(studentDtos);
        reportDto.setLectorDtos(lectorDtos);

        addTaskToStudent(reportDto.getStudentDtos(), timingReport);


        return reportDto;
    }

    private void addTaskToStudent(List<StudentDto> studentDtos, List<Report> timingReport) {
        Map<Long, List<Report>> collect = timingReport.stream().collect(Collectors.groupingBy(Report::getUserId, Collectors.toList()));

        for (StudentDto studentDto : studentDtos) {
            List<Report> reports = collect.get(studentDto.getChatID());
            if (reports != null) {
                Report report = reports.get(0);
                List<Task> tasks = report.getTasks();
                List<TaskDto> taskDtos = mapTaskToTaskDto(tasks);
                studentDto.setTaskDtos(taskDtos);
            }

        }
    }

    private List<TaskDto> mapTaskToTaskDto(List<Task> tasks) {
        ArrayList<TaskDto> taskDtos = new ArrayList<>();
        for (Task task : tasks) {
            TaskDto taskDto = new TaskDto();
            taskDto.setTimeSpended(task.getTimeSpended());
            taskDto.setDescription(task.getDescription());
            taskDtos.add(taskDto);
        }
        return taskDtos;
    }

    private List<StudentDto> mapStundent(List<User> allUsers) {

        return allUsers.stream().filter(v -> v.getRole().equals("user") || v.getRole().equals("lead"))
                .map(v -> {
                    StudentDto studentDto = new StudentDto();
                    studentDto.setFirstName(v.getFirstName());
                    studentDto.setLastName(v.getLastName());
                    studentDto.setChatID((long) v.getId());
                    return studentDto;
                })
                .collect(Collectors.toList());
    }

    private List<LectorDto> mapLector(List<User> allUsers) {
        return allUsers.stream().filter(v -> v.getRole().equals("admin"))
                .map(v -> {
                    LectorDto lectorDto = new LectorDto();
                    lectorDto.setChatID((long) v.getId());
                    return lectorDto;
                })
                .collect(Collectors.toList());
    }

}
