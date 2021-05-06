package bhx.dto;

import java.io.Serializable;

public class ResponseData implements Serializable {
    private Float prob;
    private String uid;
    private Boolean issame;

    public ResponseData() {
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
