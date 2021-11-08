package com.resttimeservice;



import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.model.Report;
import org.apache.commons.io.IOUtils;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;


import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class TrackingServlet {

    public Report[] getTimingReport() throws IOException {

        CloseableHttpClient httpclient = HttpClients.createDefault();

        HttpGet httpget = new HttpGet("http://localhost:8083/");

        System.out.println("Request Type: " + httpget.getMethod());

        HttpResponse httpresponse = httpclient.execute(httpget);

        ObjectMapper mapper = new ObjectMapper();
        String result = IOUtils.toString(httpresponse.getEntity().getContent(), StandardCharsets.UTF_8);

        List<Report> reports = Arrays.asList(mapper.readValue(result, Report[].class));
        System.out.println(reports);

        return null;
    }


    public static void main(String[] args) throws Exception {
        TrackingServlet trackingServlet = new TrackingServlet();
        trackingServlet.getTimingReport();
    }
}
