package bhx.facerecognization.http;

import org.apache.http.entity.ContentType;

public class HttpBuilder {
    private String url;
    private String registerUrl = "/f4r_ai/register";
    private String searchUrl = "/f4r_ai/search";
    private String verifyUrl = "/f4r_ai/verify";
    private String formatName ="jpg";
    private String imageParamName = "img_bytes";
    private String idParamName = "uid";
    private ContentType contentType = ContentType.create("image/jpg");
    private String imageName = "image.jpg";

    public HttpService build(){
        return new HttpService(
                url,
                registerUrl,
                searchUrl,
                verifyUrl,
                formatName,
                imageParamName,
                idParamName,
                contentType,
                imageName);
    }

    HttpBuilder(String url){
        this.url = url;
    }

    public HttpBuilder registerUrl(String registerUrl){

        this.registerUrl = registerUrl ;

        return this;
    }

    public HttpBuilder searchUrl(String searchUrl){

        this.searchUrl = searchUrl ;

        return this;
    }

    public HttpBuilder verifyUrl(String verifyUrl){

        this.verifyUrl = verifyUrl ;

        return this;
    }

    public HttpBuilder formatName(String formatName){

        this.formatName = formatName ;

        return this;
    }

    public HttpBuilder imageParamName(String imageParamName){

        this.imageParamName = imageParamName ;

        return this;
    }

    public HttpBuilder idParamName(String idParamName){

        this.idParamName = idParamName ;

        return this;
    }

    public HttpBuilder contentType(ContentType contentType){

        this.contentType = contentType ;

        return this;
    }

    public HttpBuilder imageName(String imageName){

        this.imageName = imageName ;

        return this;
    }
}
