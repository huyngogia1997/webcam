package bhx.ui;

import com.github.sarxos.webcam.Webcam;
import com.github.sarxos.webcam.WebcamPanel;

import javax.swing.*;
import java.awt.image.BufferedImage;

public interface WebcamUIHandler {
    BufferedImage takePhoto();

    BufferedImage takePhotoAndRenderUI(AbstractButton container);

    void init() throws Exception;

    void start();

    void stop();

    WebcamPanel webcamPanel();

    Webcam webcam();
}
