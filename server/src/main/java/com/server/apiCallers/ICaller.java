package com.server.apiCallers;

import com.server.graphic.GraphicApplication;

public interface ICaller {
    void execute();
    void setGraphicApplication(GraphicApplication graphicApplication);
}
