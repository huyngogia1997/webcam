package bhx.facerecognization.http;

public enum ResponseState {
    SUCCESS,
    TOO_SMALL,
    TOO_SKNEWED,
    BAD_FACE,
    NOT_DETECTED_FACE,
    MUL_FACE,
    NOT_FOUND_ID,
    OVER_SIZE,
    REQUIRED_ID,
    OTHER;

    public static ResponseState mapFrom (String serialized) {

        return OTHER;
    }
}
