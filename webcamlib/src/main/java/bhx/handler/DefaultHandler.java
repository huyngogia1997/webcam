package bhx.handler;

import bhx.facerecognization.http.HttpService;
import bhx.facerecognization.http.Response;
import bhx.ui.DefaultWebcamUIHandler;
import bhx.ui.WebcamUIHandler;
import com.github.sarxos.webcam.Webcam;
import com.github.sarxos.webcam.WebcamPanel;

import javax.swing.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Optional;

public class DefaultHandler  {
    private WebcamUIHandler webcamUIHandler;
    private HttpService httpService;
    private BufferedImage bufferedImage;

    private DefaultHandler(){
    }
    public DefaultHandler(WebcamUIHandler webcamUIHandler,HttpService httpService) throws Exception{
        this.webcamUIHandler = webcamUIHandler;
        this.httpService = httpService;
    };

    public  DefaultHandler(String url, JFrame frame) throws Exception{
        this.webcamUIHandler = DefaultWebcamUIHandler.builder(frame).build();
        this.httpService = this.httpService.builder(url).build();
    };

    public  DefaultHandler(String url, JComponent component) throws Exception{
        JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(component);
        this.webcamUIHandler = DefaultWebcamUIHandler.builder(frame).build();
        this.httpService = this.httpService.builder(url).build();
    };


    public Optional<Response> searchByLastImage() throws IOException {
        return httpService.search(bufferedImage);
    }

    public Optional<Response> verifyLastImage(String id) throws IOException {
        return httpService.verify(id,bufferedImage);
    }

    public Optional<Response> registerLastImage(String id) throws IOException {
        return httpService.register(id, bufferedImage);
    }

    public BufferedImage takePhoto(){
        bufferedImage = webcamUIHandler.takePhoto();
        return bufferedImage;
    }

    public BufferedImage takePhotoAndRenderUI(AbstractButton container){
        bufferedImage = webcamUIHandler.takePhotoAndRenderUI(container);
        return bufferedImage;
    }

    public void start(){
        webcamUIHandler.start();
    };

    public void stop(){
        webcamUIHandler.stop();
    };

    public WebcamPanel webcamPanel(){
        return webcamUIHandler.webcamPanel();
    };

    public Webcam webcam(){
        return webcamUIHandler.webcam();
    };

    public WebcamUIHandler getWebcamUIHandler() {
        return webcamUIHandler;
    }

    public HttpService getHttpService() {
        return httpService;
    }

    public BufferedImage getBufferedImage() {
        return bufferedImage;
    }
}
