package bhx.ui;

import com.github.sarxos.webcam.Webcam;
import com.github.sarxos.webcam.WebcamPanel;
import com.github.sarxos.webcam.WebcamResolution;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class DefaultWebcamUIHandler implements WebcamUIHandler {
    private JFrame frame;
    private Webcam webcam;
    private WebcamPanel webcamPanel;
    private String titleError = "Không tìm thấy camera nào";
    private String contentError = "Vui lòng kiểm tra lại";
    private String titleSelect = "Chọn webcam mà bạn muốn sử dụng";
    private String contentSelect = "";

    DefaultWebcamUIHandler(JFrame frame, String titleError, String contentError, String titleSelect, String contentSelect) {
        this.frame = frame;
        this.titleError = Optional.ofNullable(titleError).orElse(this.titleError);
        this.contentError = Optional.ofNullable(contentError).orElse(this.contentError);;
        this.titleSelect = Optional.ofNullable(titleSelect).orElse(this.titleSelect);;
        this.contentSelect = Optional.ofNullable(contentSelect).orElse(this.contentSelect);;
    }

    public static DefaultWebcamUIHandlerBuilder builder(JFrame frame){

        return new DefaultWebcamUIHandlerBuilder(frame);
    }

    public static DefaultWebcamUIHandlerBuilder builder(JComponent component){

        JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(component);

        return new DefaultWebcamUIHandlerBuilder(frame);
    }

    @Override
    public BufferedImage takePhoto() {

        return webcam.getImage();
    }

    @Override
    public BufferedImage takePhotoAndRenderUI(AbstractButton container) {

        BufferedImage image = null;

        image = webcam.getImage();

        ImageIcon iconTmp = new ImageIcon(image);
        Image img = iconTmp.getImage() ;
        Image newimg = img.getScaledInstance( container.getWidth(), container.getHeight(),  java.awt.Image.SCALE_SMOOTH ) ;

        container.setIcon(new ImageIcon( newimg ));

        return image;
    }

    @Override
    public void init() throws Exception {
        if (Objects.isNull(webcam)) {

            List<Webcam> webcams = Webcam.getWebcams(10000);

            if(webcams.isEmpty()){

                JOptionPane.showMessageDialog(frame,
                        titleError,
                        contentError,
                        JOptionPane.ERROR_MESSAGE);

                throw new Exception("Can not init webcam because not found any webcam");
            }

            if(webcams.size() > 1){

                Object[] webcamNames = webcams.stream().map(Webcam::getName).toArray();
                String selectedWebcamName = (String)JOptionPane.showInputDialog(
                        frame ,
                        titleSelect,
                        contentSelect,
                        JOptionPane.PLAIN_MESSAGE,
                        new ImageIcon(this.getClass().getClassLoader().getResource("webcam.png")),
                        webcamNames,
                        webcamNames[0]);;
                webcam = webcams.stream().filter(wc -> wc.getName().equals(selectedWebcamName)).findFirst().get();

            }

            if(webcams.size() == 1){

                webcam = webcams.get(0);

            }

            webcam.setViewSize(WebcamResolution.QVGA.getSize());

            webcamPanel = new WebcamPanel(webcam, false);
            webcamPanel.setMirrored(true);

        }
        postInit(webcam,webcamPanel);
    }

    public void postInit(Webcam webcam, WebcamPanel webcamPanel){
    };

    @Override
    public void start() {
        webcamPanel.start();
    }

    @Override
    public void stop() {
        webcamPanel.stop();
    }

    @Override
    public WebcamPanel webcamPanel(){
        return webcamPanel;
    }

    @Override
    public Webcam webcam() {
        return webcam;
    }
}
