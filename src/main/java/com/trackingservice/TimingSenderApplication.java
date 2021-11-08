package com.trackingservice;

import com.dto.LectorDto;
import com.dto.ReportDto;
import com.mapper.Reporter;
import com.soapsendservice.SendService;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;


import java.io.IOException;
import java.time.LocalTime;

public class TimingSenderApplication {
    private static Logger logger = Logger.getLogger(TimingSenderApplication.class);

    public static void main(String[] args) throws IOException {
      /*  LocalTime sendTime = LocalTime.of(22, 32, 00);

        while (true) {
            if (LocalTime.now().equals(sendTime)) {
                sendReport();
            }
        }*/
        sendReport();

    }

    private static void sendReport() throws IOException {
        Reporter reporter = new Reporter();
        ReportDto report = reporter.createReport();
        Message message = new Message(report);

        for (LectorDto lectorDto : report.getLectorDtos()) {
            try {
                SendService.sendMessage(lectorDto.getChatID(), message.text);
            } catch (Exception e) {
                logger.log(Level.ERROR, e.getMessage());
            }
        }
    }
}