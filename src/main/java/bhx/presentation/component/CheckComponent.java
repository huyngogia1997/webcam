package bhx.presentation.component;

import bhx.dto.MasterResponse;
import bhx.service.RecognizeFaceService;
import bhx.utility.MRes;
import com.github.sarxos.webcam.Webcam;
import com.github.sarxos.webcam.WebcamPanel;
import com.github.sarxos.webcam.WebcamResolution;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Optional;

public class CheckComponent extends JPanel {
    JPanel cameraPa = new JPanel();
    JPanel infoP = new JPanel();
    private static Webcam webcam;
    private static WebcamPanel webcamPanel;
    private JTextField phoneTxt;
    private JLabel errorL;
    private JLabel successL;
    private JButton checkBtn;
    private JButton captureBtn;
    private JButton captureImage;
    private static CheckComponent checkComponent;
    private BufferedImage image = null;

    public static CheckComponent init(){
        if(checkComponent == null){
            checkComponent = new CheckComponent();
        }
        return checkComponent;
    }


    public CheckComponent() {
        GridBagLayout layout = new GridBagLayout();
        this.setLayout(layout);
        GridBagConstraints gbc = new GridBagConstraints();

        gbc.gridx = 0;
        gbc.gridy = 0;
        cameraPa.setPreferredSize(new Dimension(300,300));
        cameraPa.setLayout(new CardLayout());
        cameraPa.setBorder(BorderFactory.createLineBorder(Color.black));

        initWebCam();
        if (webcam != null) {
            webcamPanel = new WebcamPanel(webcam, false);
            webcamPanel.setMirrored(true);
            cameraPa.add(webcamPanel);
            webcamPanel.start();
        }
        this.add(cameraPa,gbc);

        gbc.gridx = 1;
        gbc.gridy = 0;
        infoP.setPreferredSize(new Dimension(370,350));
        infoP.setBorder(BorderFactory.createEmptyBorder(20, 40, 0, 0));

        BoxLayout boxlayout = new BoxLayout(infoP, BoxLayout.Y_AXIS);
        infoP.setLayout(boxlayout);

        JPanel captureImageContainerP = new JPanel();
        boxlayout = new BoxLayout(captureImageContainerP, BoxLayout.X_AXIS);

        captureImageContainerP.setPreferredSize(new Dimension(390,200));
        captureImageContainerP.setMaximumSize(new Dimension(390,200));
        captureImageContainerP.setMinimumSize(new Dimension(390,200));
        captureImageContainerP.setLayout(boxlayout);

        captureBtn = new JButton(">>");
        captureBtn.setPreferredSize(new Dimension(50,30));
        captureImageContainerP.add(captureBtn);

        JPanel captureImageP = new JPanel();
        captureImageP.setLayout(new CardLayout());
        captureImageP.setPreferredSize(new Dimension(235,200));
        captureImageP.setMaximumSize(new Dimension(235,200));
        captureImageP.setMinimumSize(new Dimension(235,200));
        captureImageP.setBorder(BorderFactory.createEmptyBorder(0, 35, 0, 0));


        captureImage = new JButton();
        captureImage.setBorder(BorderFactory.createLineBorder(Color.black));
        captureImageP.add(captureImage);
        captureImageContainerP.add(captureImageP);

        infoP.add(captureImageContainerP);



        JPanel phoneP = new JPanel();
        boxlayout = new BoxLayout(phoneP, BoxLayout.X_AXIS);
        phoneP.setLayout(boxlayout);
        phoneP.setPreferredSize(new Dimension(370,35));
        phoneP.setMaximumSize(new Dimension(370,35));
        phoneP.setMinimumSize(new Dimension(370,35));
        phoneP.setBorder(BorderFactory.createEmptyBorder(5, 0, 5, 0));
        JLabel phoneL = new JLabel("Phone:");
        phoneL.setPreferredSize(new Dimension(70,30));
        phoneTxt = new JTextField();
        phoneP.add(phoneL);
        phoneP.add(phoneTxt);
        infoP.add(phoneP);

        JPanel checkP = new JPanel();
        boxlayout = new BoxLayout(checkP, BoxLayout.Y_AXIS);
        checkP.setLayout(boxlayout);
        checkP.setPreferredSize(new Dimension(370,50));
        checkP.setMaximumSize(new Dimension(370,50));
        checkP.setMinimumSize(new Dimension(370,50));
        checkBtn = new JButton("Check");
        checkP.add(checkBtn);
        infoP.add(checkP);

        errorL = new JLabel();
        errorL.setForeground(Color.red);
        infoP.add(errorL);

        successL= new JLabel();
        successL.setForeground(Color.green);
        infoP.add(successL);

        this.add(infoP,gbc);

        captureBtn.addActionListener(e->{

            try {
                image = ImageIO.read(MRes.getResourceStream("resources/images/face.jpg"));
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
            ImageIcon imageIcon = new ImageIcon(image);
            captureImage.setIcon(imageIcon);
        });

        checkBtn.addActionListener((event)->{
            // reset
            successL.setText("");
            errorL.setText("");

            if(isEmpty(phoneTxt.getText())){
                errorL.setText("Phone number can not be empty");
                return;
            }
            if(image == null){
                errorL.setText("Take photo before check");
                return;
            }
            try {
                Optional<MasterResponse> masterResponseOptional = RecognizeFaceService.verify(phoneTxt.getText(),image);
                if(!masterResponseOptional.isPresent()){
                    errorL.setText("Server is error");
                    return;
                }
                MasterResponse masterResponse = masterResponseOptional.get();
                switch (masterResponse.getError_code()){
                    case 0:
                        if(masterResponse.getData().getIssame()){
                            successL.setText("Is same with "+masterResponse.getData().getProb()*100+"%" );
                        }else{
                            successL.setText("Is not same with "+masterResponse.getData().getProb()*100+"%" );
                        }
                        break;
                    case 2:
                        errorL.setText("Detected face is too small" );
                        break;
                    case 3:
                        errorL.setText("Detected face is too skewed" );
                        break;
                    case 4:
                        errorL.setText("Detected face is bad face" );
                        break;
                    case 5:
                        errorL.setText("Can not detect face in image" );
                        break;
                    case 6:
                        errorL.setText("Detected multiple faces in image" );
                        break;
                    default:
                        errorL.setText("Error" );
                        break;
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }
    private boolean isEmpty(String str){
        return  str == null || str.isEmpty();
    }
    private void initWebCam() {
        if (webcam == null) {
            try {
                webcam = Webcam.getDefault();
                webcam.setViewSize(WebcamResolution.QVGA.getSize());
            } catch (Exception e) {
                e.getStackTrace();
            }
        }
    }
    private void onStop(){
        webcamPanel.stop();
    }
}
