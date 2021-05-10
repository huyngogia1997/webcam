package bhx.facerecognization.http;

import com.google.gson.*;
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
import java.lang.reflect.Type;
import java.util.Optional;

public class HttpService {
    private String url = null;
    private String registerUrl = "/f4r_ai/register";
    private String searchUrl = "/f4r_ai/search";
    private String verifyUrl = "/f4r_ai/verify";
    private String formatName ="jpg";
    private String imageParamName = "img_bytes";
    private String idParamName = "uid";
    private ContentType contentType = ContentType.create("image/jpg");
    private String imageName = "image.jpg";

    private HttpService(){}

    public HttpService(
            String url,
            String registerUrl,
            String searchUrl,
            String verifyUrl,
            String formatName,
            String imageParamName,
            String idParamName,
            ContentType contentType,
            String imageName) {
        this.url = url;
        this.registerUrl = Optional.ofNullable(registerUrl).orElse(this.registerUrl);
        this.searchUrl = Optional.ofNullable(searchUrl).orElse(this.searchUrl);;
        this.verifyUrl = Optional.ofNullable(verifyUrl).orElse(this.verifyUrl);;
        this.formatName = Optional.ofNullable(formatName).orElse(this.formatName);;
        this.imageParamName = Optional.ofNullable(imageParamName).orElse(this.imageParamName);;
        this.idParamName = Optional.ofNullable(idParamName).orElse(this.idParamName);;
        this.contentType = Optional.ofNullable(contentType).orElse(this.contentType);;
        this.imageName = Optional.ofNullable(imageName).orElse(this.imageName);;
    }

    public static HttpBuilder builder(String url){
        return new HttpBuilder(url);
    }

    public Optional<Response> register(String id, BufferedImage bufferedImage) throws IOException {
        return requestDefault(bufferedImage,registerUrl + "?"+idParamName+"=" + id);
    }

    public Optional<Response> search(BufferedImage bufferedImage) throws IOException {
        return requestDefault(bufferedImage,searchUrl);
    }

    public Optional<Response> verify(String id, BufferedImage bufferedImage) throws IOException {
        return requestDefault(bufferedImage,verifyUrl + "?"+idParamName+"=" + id);
    }

    public Optional<Response> requestDefault(BufferedImage bufferedImage, String path) throws IOException {
//        ByteArrayOutputStream baos = new ByteArrayOutputStream();
//        ImageIO.write(bufferedImage, formatName, baos);
//        baos.flush();
//        byte[] imageInByte = baos.toByteArray();
//        baos.close();
//
//        HttpClient httpClient = HttpClients.createDefault();
//        HttpPost request = new HttpPost(url + path);
//        MultipartEntityBuilder multipartEntity = MultipartEntityBuilder.create();
//        multipartEntity.addBinaryBody(imageParamName, imageInByte, contentType, imageName);
//        request.setEntity(multipartEntity.build());
//        HttpResponse response = httpClient.execute(request);
//        HttpEntity entity = response.getEntity();
        String responseBody = "{\n" +
                "  \"error_code\": 6,\n" +
                "  \"error_message\": \"successful\",\n" +
                "  \"data\": {\n" +
                "    \"prob\": 0.99,\n" +
                "    \"uid\": \"12345\",\n" +
                "    \"issame\": true\n" +
                "  }\n" +
                "}";

//        if (entity != null) {
//
//            responseBody = EntityUtils.toString(entity);
//
//        }
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(Response.class,new ResponseStateDeserializer());
        Gson gson = gsonBuilder.create();
        Response res = gson.fromJson(responseBody, Response.class);
        System.out.println(responseBody);
        return Optional.ofNullable(res);
    }


    class ResponseStateDeserializer implements JsonDeserializer<Response> {

        @Override
        public Response deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
            JsonObject jsonObject = json.getAsJsonObject();
            Gson gson = new Gson();
            Response res = gson.fromJson(jsonObject.toString(), Response.class);
            Response response = new Response();

            ResponseState responseState = ResponseState.OTHER;
            switch (jsonObject.get("error_code").getAsInt()){
                case 0:
                    responseState = ResponseState.SUCCESS;
                    break;
                case 2:
                    responseState = ResponseState.TOO_SMALL;
                    break;
                case 3:
                    responseState = ResponseState.TOO_SKNEWED;
                    break;
                case 4:
                    responseState = ResponseState.BAD_FACE;
                    break;
                case 5:
                    responseState = ResponseState.NOT_DETECTED_FACE;
                    break;
                case 6:
                    responseState = ResponseState.MUL_FACE;
                    break;
                case 400:
                    responseState = ResponseState.REQUIRED_ID;
                    break;
                case 413:
                    responseState = ResponseState.OVER_SIZE;
                    break;
                case 9:
                    responseState = ResponseState.NOT_FOUND_ID;
                    break;
            }

            res.setError_state(responseState);
            return res;
        }
    }

}
