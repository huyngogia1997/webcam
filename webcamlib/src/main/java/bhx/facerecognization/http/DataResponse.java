package bhx.facerecognization.http;

import java.io.Serializable;

public class DataResponse implements Serializable {
    private Float prob;
    private String uid;
    private Boolean issame;

    public DataResponse() {
    }

    public float getProb() {
        return prob;
    }

    public void setProb(float prob) {
        this.prob = prob;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public void setProb(Float prob) {
        this.prob = prob;
    }

    public Boolean getIssame() {
        return issame;
    }

    public void setIssame(Boolean issame) {
        this.issame = issame;
    }
}
