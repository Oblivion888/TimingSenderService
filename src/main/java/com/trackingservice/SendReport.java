package com.trackingservice;

import com.dto.LectorDto;
import com.dto.ReportDto;
import com.mapper.Reporter;
import com.soapsendservice.SendService;
import org.apache.log4j.Logger;
import org.apache.log4j.Priority;


import java.io.IOException;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class SendReport {
    final static Logger logger = Logger.getLogger(SendReport.class);

    public static void main(String[] args) throws IOException {
        LocalTime sendTime = LocalTime.of(22, 32, 00);

        while (true) {
            if (LocalTime.now().equals(sendTime)) {
                sendReport();
            }
        }

    }

    private static void sendReport() throws IOException {
        Reporter reporter = new Reporter();
        ReportDto report = reporter.createReport();
        Message message = new Message(report);

        for (LectorDto lectorDto : report.getLectorDtos()) {
            try {
                SendService.sendMessage(lectorDto.getChatID(), message.text);
            } catch (Exception e) {
                logger.log(Priority.ERROR, e.getMessage());
            }
        }
    }
}