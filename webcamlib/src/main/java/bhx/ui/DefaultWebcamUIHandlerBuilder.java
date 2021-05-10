package bhx.ui;

import javax.swing.*;

public class DefaultWebcamUIHandlerBuilder {
    private JFrame frame;
    private String titleError = null;
    private String contentError = null;
    private String titleSelect = null;
    private String contentSelect = null;

    DefaultWebcamUIHandlerBuilder(JFrame frame){

        this.frame = frame;

    }

    public DefaultWebcamUIHandlerBuilder notFoundWebcamDialog(String title, String content){

        this.titleError = title;
        this.contentError = content;

        return this;
    }

    public DefaultWebcamUIHandlerBuilder selectWebcamDialog(String title, String content){

        this.titleSelect = title;
        this.contentSelect = content;

        return this;
    }


    public WebcamUIHandler build() {
        return new DefaultWebcamUIHandler(frame,titleError,contentError,titleSelect,contentSelect);
    }
}
