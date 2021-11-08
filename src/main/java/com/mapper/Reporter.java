package com.mapper;

import com.dto.LectorDto;
import com.dto.ReportDto;
import com.dto.StudentDto;
import com.dto.TaskDto;
import com.model.Report;
import com.model.Task;
import com.resttimeservice.TrackingServlet;
import com.soapcommandservice.CommandService;
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

    public static void main(String[] args) throws IOException {
        Reporter reporter = new Reporter();
        ReportDto report = reporter.createReport();

        for (StudentDto studentDto : report.getStudentDtos()) {
            System.out.println(studentDto);
        }

        for (LectorDto lectorDto : report.getLectorDtos()) {
            System.out.println(lectorDto);
        }
    }

    public ReportDto createReport() throws IOException {
        reportDto = new ReportDto();

        List<String> allUsers = CommandService.getAllUsers();
        List<Report> timingReport = trackingServlet.getTimingReport();

        mapLector(allUsers);
        mapStundent(allUsers);

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

    private void mapStundent(List<String> allUsers) {

        List<StudentDto> collect = allUsers.stream().filter(v -> v.split("\\|")[4].equals("user") || v.split("\\|")[4].equals("lead"))
                .map(v -> {
                    String[] split = v.split("\\|");
                    StudentDto studentDto = new StudentDto();
                    studentDto.setChatID(Long.parseLong(split[0]));
                    studentDto.setFirstName(split[2]);
                    studentDto.setLastName(split[3]);
                    return studentDto;
                })
                .collect(Collectors.toList());
        reportDto.setStudentDtos(collect);
    }

    private void mapLector(List<String> allUsers) {
        List<LectorDto> admin = allUsers.stream().filter(v -> v.split("\\|")[4].equals("admin"))
                .map(v -> {
                    String[] split = v.split("\\|");
                    LectorDto lectorDto = new LectorDto();
                    lectorDto.setChatID(Long.parseLong(split[0]));
                    return lectorDto;
                })
                .collect(Collectors.toList());
        reportDto.setLectorDtos(admin);

    }

}
