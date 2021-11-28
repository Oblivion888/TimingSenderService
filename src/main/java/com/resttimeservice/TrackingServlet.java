package com.resttimeservice;



import com.fasterxml.jackson.databind.ObjectMapper;
import com.model.Report;
import org.apache.commons.io.IOUtils;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.springframework.stereotype.Service;


import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.List;

@Service
public class TrackingServlet {

    public List<Report> getTimingReport() throws IOException {

        CloseableHttpClient httpclient = HttpClients.createDefault();

        HttpGet httpget = new HttpGet("http://3.14.182.52:8083/");


        HttpResponse httpresponse = httpclient.execute(httpget);

        ObjectMapper mapper = new ObjectMapper();
        String result = IOUtils.toString(httpresponse.getEntity().getContent(), StandardCharsets.UTF_8);

        List<Report> reports = Arrays.asList(mapper.readValue(result, Report[].class));

        return reports;
    }
}
