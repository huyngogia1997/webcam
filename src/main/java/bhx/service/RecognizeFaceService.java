package bhx.service;

import bhx.dto.MasterResponse;
import com.google.gson.Gson;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Optional;
import java.util.Scanner;

public class RecognizeFaceService {
    public static String REGISTER_URL = "/f4r_ai/register";
    public static String SEARCH_URL = "/f4r_ai/search";
    public static String VERIFY_URL = "/f4r_ai/verify";

    public static Optional<MasterResponse> commonRecognizeService(String url, BufferedImage image) throws IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ImageIO.write(image, "jpg", baos);
        baos.flush();
        byte[] imageInByte = baos.toByteArray();
        baos.close();

        HttpClient httpClient = HttpClients.createDefault();
        HttpPost request = new HttpPost( new Scanner(RecognizeFaceService.class.getResourceAsStream("/host.conf"), "UTF-8").useDelimiter("\\A").next() + url);
        MultipartEntityBuilder multipartEntity = MultipartEntityBuilder.create();
        multipartEntity.addBinaryBody("img_bytes", imageInByte, ContentType.create("image/jpg"), "image.jpg");
        request.setEntity(multipartEntity.build());
        HttpResponse response = httpClient.execute(request);
        HttpEntity entity = response.getEntity();
        String responseBody = "";

        if (entity != null) {

            responseBody = EntityUtils.toString(entity);

        }

        Gson gson = new Gson();
        MasterResponse res = gson.fromJson(responseBody, MasterResponse.class);

        return Optional.ofNullable(res);
    }

    public static Optional<MasterResponse> register(String uid, BufferedImage image) throws IOException {

        return commonRecognizeService(REGISTER_URL + "?uid=" + uid, image);
    }

    public static Optional<MasterResponse> search(BufferedImage image) throws IOException {

        return commonRecognizeService(SEARCH_URL, image);
    }

    public static Optional<MasterResponse> verify(String uid,BufferedImage image) throws IOException {
        return commonRecognizeService(VERIFY_URL + "?uid=" + uid, image);
    }
}
