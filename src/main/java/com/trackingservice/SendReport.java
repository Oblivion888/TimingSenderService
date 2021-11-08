package com.trackingservice;

import com.dto.LectorDto;
import com.dto.ReportDto;
import com.mapper.Reporter;
import com.soapsendservice.SendService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import java.io.IOException;

public class SendReport {
    private static Logger logger = LoggerFactory.getLogger(SendReport.class);

    public static void main(String[] args) throws IOException {
        Reporter reporter = new Reporter();
        ReportDto report = reporter.createReport();
        Message message = new Message(report);

        for (LectorDto lectorDto : report.getLectorDtos()) {
            try {
                SendService.sendMessage(lectorDto.getChatID(), message.text);
            } catch (Exception e) {
                logger.error(e.getMessage());
            }
        }
    }
}