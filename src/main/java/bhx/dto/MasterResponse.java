package bhx.dto;

import java.io.Serializable;

public class MasterResponse implements Serializable {
    private int error_code;
    private ResponseData data;
    private String error_message;

    public MasterResponse() {
    }

    public int getError_code() {
        return error_code;
    }

    public void setError_code(int error_code) {
        this.error_code = error_code;
    }

    public ResponseData getData() {
        return data;
    }

    public void setData(ResponseData data) {
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
        return "MasterResponse{" +
                "error_code=" + error_code +
                ", data=" + data +
                ", error_message='" + error_message + '\'' +
                '}';
    }
}
