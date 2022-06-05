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
    String url = "http://localhost:8081/api/load/";

    @Override
    public void execute() {
    }

    @Override
    public void setGraphicApplication(GraphicApplication graphicApplication) {}
}
