package com.server.apiCallers;

import com.server.graph.BipartiteGraph;
import com.server.graphic.GraphicApplication;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;

public class LoadCaller implements ICaller{
    private GraphicApplication graphicApplication;
    String url = "http://localhost:8081/api/load/";

    @Override
    public void execute() {
        int id = graphicApplication.upperPanel.getLoadSpinnerValue();
        String loadUrl = url + id;
        HttpPost httppost = new HttpPost(loadUrl);

        try (CloseableHttpClient httpClient = HttpClients.createDefault();
             CloseableHttpResponse response = httpClient.execute(httppost)) {

            graphicApplication.getGraph().loadGraphFromString(EntityUtils.toString(response.getEntity()));
            graphicApplication.canvas.init(graphicApplication.getGraph().getNumberOfPairs());

        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void setGraphicApplication(GraphicApplication graphicApplication) {
        this.graphicApplication = graphicApplication;
    }
}
