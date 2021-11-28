package com.trackingservice;

import com.dto.ReportDto;
import com.mapper.Reporter;
import com.soapcommandservice.User;
import com.soapsendservice.SendService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class ReportSender {
    private final Reporter reporter;

    @Autowired
    public ReportSender(Reporter reporter) {
        this.reporter = reporter;
    }


    public void sendReport() throws IOException {
        ReportDto report = reporter.createReport();
        Message message = new Message(report);

        for (User lector : report.getLectors()) {
            try {
                SendService.sendMessage((long) lector.getId(), message.text);
            } catch (Exception e) {

            }
        }
    }

    public Reporter getReporter() {
        return reporter;
    }
}
