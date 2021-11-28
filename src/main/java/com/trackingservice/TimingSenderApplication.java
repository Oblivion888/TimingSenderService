package com.trackingservice;

import com.dto.ReportDto;
import com.mapper.Reporter;
import com.soapsendservice.SendService;


import java.io.IOException;
import java.time.LocalTime;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class TimingSenderApplication {


    public static void main(String[] args) throws IOException {
        AnnotationConfigApplicationContext applicationContext
                = new AnnotationConfigApplicationContext(Config.class);

        LocalTime sendTime = LocalTime.of(19, 00, 00);

        ReportSender reportSender = (ReportSender) applicationContext.getBean("reportSender");
        System.out.println(reportSender
                .getReporter());
        /*
        while (true) {
            if (LocalTime.now().equals(sendTime)) {
                sendReport();
            }
        }*/

        reportSender.sendReport();
    }
}
