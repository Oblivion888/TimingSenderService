package com.trackingservice;

import com.dto.LectorDto;
import com.dto.ReportDto;
import com.mapper.Reporter;
import com.soapsendservice.SendService;



import java.io.IOException;

public class TimingSenderApplication {

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
              
            }
        }
    }
}