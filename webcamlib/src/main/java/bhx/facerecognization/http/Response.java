package bhx.facerecognization.http;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Response implements Serializable {
    private int error_code;
    private DataResponse data;
    private String error_message;
    @Expose
    private ResponseState error_state;

    public Response() {
    }

    public ResponseState getError_state() {
        return error_state;
    }

    public void setError_state(ResponseState error_state) {
        this.error_state = error_state;
    }

    public int getError_code() {
        return error_code;
    }

    public void setError_code(int error_code) {
        this.error_code = error_code;
    }

    public DataResponse getData() {
        return data;
    }

    public void setData(DataResponse data) {
        this.data = data;
    }

    public String getError_message() {
        return error_message;
    }

    public void setError_message(String error_message) {
        this.error_message = error_message;
    }

    @Override
    public String toString() {
        return "Response{" +
                "error_code=" + error_code +
                ", data=" + data +
                ", error_message='" + error_message + '\'' +
                ", error_state=" + error_state +
                '}';
    }
}
