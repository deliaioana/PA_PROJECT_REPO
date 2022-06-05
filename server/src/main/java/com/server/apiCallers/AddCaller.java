package com.server.apiCallers;


import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import com.server.graphic.GraphicApplication;

import java.io.IOException;

public class AddCaller implements ICaller{
    private GraphicApplication graphicApplication;
    String url = "http://localhost:8081/api/add/";

    @Override
    public void setGraphicApplication(GraphicApplication graphicApplication) {
        this.graphicApplication = graphicApplication;
    }

    @Override
    public void execute() {
        String addUrl = url + graphicApplication.getInfoAsString();
        HttpPost httppost = new HttpPost(addUrl);

        try (CloseableHttpClient httpClient = HttpClients.createDefault();
             CloseableHttpResponse response = httpClient.execute(httppost)) {

            System.out.println(EntityUtils.toString(response.getEntity()));
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
}
