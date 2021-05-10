package bhx.presentation.component;

import bhx.facerecognization.http.Response;
import bhx.facerecognization.http.HttpService;
import bhx.facerecognization.http.ResponseState;
import bhx.handler.DefaultHandler;
import bhx.ui.DefaultWebcamUIHandler;
import bhx.ui.WebcamUIHandler;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;
import java.util.Optional;

public class TestWebcamUIHandlerComponent extends JPanel {
    JPanel cameraPa = new JPanel();
    JPanel infoP = new JPanel();
    private JTextField usernameTxt;
    private JLabel errorL;
    private JLabel successL;
    private JButton checkBtn;
    private JButton captureBtn;
    private JButton captureImage;
    private static TestWebcamUIHandlerComponent registerComponent = null;
    private DefaultHandler webcamHandler;

    public static TestWebcamUIHandlerComponent init(){
        if(registerComponent == null){
            registerComponent = new TestWebcamUIHandlerComponent();
        }
        return  registerComponent;
    }

    public TestWebcamUIHandlerComponent() {

        initWebcam();

        GridBagLayout layout = new GridBagLayout();
        this.setLayout(layout);
        GridBagConstraints gbc = new GridBagConstraints();

        gbc.gridx = 0;
        gbc.gridy = 0;
        cameraPa.setPreferredSize(new Dimension(300,300*3/4));
        cameraPa.setLayout(new CardLayout());
        cameraPa.setBorder(BorderFactory.createLineBorder(Color.black));

        cameraPa.add(webcamHandler.webcamPanel());
        webcamHandler.start();

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
        checkBtn = new JButton("Register");
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
            webcamHandler.takePhotoAndRenderUI(captureImage);
        });

        checkBtn.addActionListener((event)->{
            try {
                System.out.println("Check: "+usernameTxt.getText());
                // reset
                successL.setText("");
                errorL.setText("");


//                if(isEmpty(usernameTxt.getText())){
//                    errorL.setText("username can not be empty");
//                    return;
//                }
//                if(!usernameTxt.getText().matches("^[a-zA-Z0-9._]+$")){
//                    errorL.setText("Username is invalid");
//                    return;
//                }
//                if(Objects.isNull(image)){
//                    errorL.setText("Photo is required");
//                    return;
//                }


                    Optional<Response> responseOptional = webcamHandler.verifyLastImage(usernameTxt.getText());

                    if(!responseOptional.isPresent()) {
                        errorL.setText("Request error");
                        return;
                    }

                    if(!responseOptional.get().equals(ResponseState.NOT_FOUND_ID)){
                        if(!responseOptional.get().equals(ResponseState.SUCCESS)){
                            errorL.setText(responseOptional.get().getError_message());
                        }
                        return;
                    }

                    webcamHandler.registerLastImage(usernameTxt.getText()).ifPresent(response -> {
                        successL.setText(response.getError_message());
                    });

            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    private void initWebcam() {


        try {

//            defaultWebcamUIHandler.init();
            webcamHandler = new DefaultHandler("",this);

        } catch (Exception e) {
            e.printStackTrace();
            JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(this);
            JOptionPane.showMessageDialog(frame,
                    e.getMessage(),
                    "",
                    JOptionPane.ERROR_MESSAGE);
            System.exit(-1);
        }
    }

    private boolean isEmpty(String str){
        return  str == null || str.isEmpty();
    }

}
