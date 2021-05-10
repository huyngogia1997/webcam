package bhx.presentation.component;

import bhx.dto.MasterResponse;
import bhx.service.RecognizeFaceService;
import com.github.sarxos.webcam.Webcam;
import com.github.sarxos.webcam.WebcamPanel;
import com.github.sarxos.webcam.WebcamResolution;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;
import java.util.Optional;

public class VerifyComponent extends JPanel {
    JPanel cameraPa = new JPanel();
    JPanel infoP = new JPanel();
    private static Webcam webcam;
    private static WebcamPanel webcamPanel;
    private JTextField usernameTxt;
    private JLabel errorL;
    private JLabel successL;
    private JButton checkBtn;
    private JButton captureBtn;
    private JButton captureImage;
    private BufferedImage image = null;
    private static VerifyComponent registerComponent = null;

    public static VerifyComponent init(){
        if(registerComponent == null){
            registerComponent = new VerifyComponent();
        }
        return  registerComponent;
    }

    public VerifyComponent() {
        GridBagLayout layout = new GridBagLayout();
        this.setLayout(layout);
        GridBagConstraints gbc = new GridBagConstraints();

        gbc.gridx = 0;
        gbc.gridy = 0;
        cameraPa.setPreferredSize(new Dimension(300,300*3/4));
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
        captureImageP.setPreferredSize(new Dimension(235,200*3/4));
        captureImageP.setMaximumSize(new Dimension(235,200*3/4));
        captureImageP.setMinimumSize(new Dimension(235,200*3/4));
        captureImageP.setBorder(BorderFactory.createEmptyBorder(0, 35, 0, 0));


        captureImage = new JButton();
        captureImage.setBorder(BorderFactory.createLineBorder(Color.black));
        captureImageP.add(captureImage);
        captureImageContainerP.add(captureImageP);

        infoP.add(captureImageContainerP);



        JPanel usernameP = new JPanel();
        boxlayout = new BoxLayout(usernameP, BoxLayout.X_AXIS);
        usernameP.setLayout(boxlayout);
        usernameP.setPreferredSize(new Dimension(370,35));
        usernameP.setMaximumSize(new Dimension(370,35));
        usernameP.setMinimumSize(new Dimension(370,35));
        usernameP.setBorder(BorderFactory.createEmptyBorder(5, 0, 5, 0));
        JLabel usernameL = new JLabel("Username:");
        usernameL.setPreferredSize(new Dimension(70,30));
        usernameTxt = new JTextField();
        usernameP.add(usernameL);
        usernameP.add(usernameTxt);
        infoP.add(usernameP);

        JPanel checkP = new JPanel();
        boxlayout = new BoxLayout(checkP, BoxLayout.Y_AXIS);
        checkP.setLayout(boxlayout);
        checkP.setPreferredSize(new Dimension(370,50));
        checkP.setMaximumSize(new Dimension(370,50));
        checkP.setMinimumSize(new Dimension(370,50));
        checkBtn = new JButton("Verify");
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
                image = webcam.getImage();
            } catch (Exception ioException) {
            }
            ImageIcon imageIcon = new ImageIcon(image);

            Image img = imageIcon.getImage() ;
            Image newimg = img.getScaledInstance( 200, 150,  Image.SCALE_SMOOTH ) ;
            captureImage.setIcon(new ImageIcon( newimg ));
        });

        checkBtn.addActionListener((event)->{
            try {
                System.out.println("Check: "+usernameTxt.getText());

                // reset
                successL.setText("");
                errorL.setText("");

                if(isEmpty(usernameTxt.getText())){
                    errorL.setText("Username can not be empty");
                    return;
                }
                if(!usernameTxt.getText().matches("^[a-zA-Z0-9._]+$")){
                    errorL.setText("Username is invalid");
                    return;
                }

                if(Objects.isNull(image)){
                    errorL.setText("Photo is required");
                    return;
                }

                Optional<MasterResponse> optionalMasterResponse = RecognizeFaceService.verify(usernameTxt.getText(),image);
                // check existed
                RecognizeFaceService.verify(usernameTxt.getText(),image)
                    .ifPresent(masterResponse->{
                        System.out.println(masterResponse);
                                switch (masterResponse.getError_code()) {
                                    case 0:
                                        if(masterResponse.getData().getIssame()){
                                            successL.setText("Is same with "+masterResponse.getData().getProb()*100+"%" );
                                        }else{
                                            errorL.setText("Is not same with "+masterResponse.getData().getProb()*100+"%" );
                                        }
                                        break;
                                    case 2:
                                        errorL.setText("Detected face is too small");
                                        break;
                                    case 3:
                                        errorL.setText("Detected face is too skewed");
                                        break;
                                    case 4:
                                        errorL.setText("Detected face is bad face");
                                        break;
                                    case 5:
                                        errorL.setText("Can not detect face in image");
                                        break;
                                    case 6:
                                        errorL.setText("Detected multiple faces in image");
                                        break;
                                    case 400:
                                        errorL.setText("Username is required");
                                        break;
                                    case 9:
                                        errorL.setText("Username is not registered");
                                        break;
                                    default:
                                        errorL.setText("Error");
                                        break;
                                }
                    });
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
                webcam = Webcam.getWebcams(1000).stream().filter(wc -> wc.getName().contains("170")).findFirst().get();
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
