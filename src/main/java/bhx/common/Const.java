package bhx.common;

public class Const {

	public static int MINIMUM_QUALITY = 50; // User defined
	public static int MINIMUM_NUM_MINUTIAE = 20; // User defined
	public static int MAXIMUM_NFIQ = 2; // User defined
	public static final int TIME_OUT = 2;
	public static final int IMAGE_QUALITY = 80;
	
	public class Message {
		public static final String GET_FINGERPRINT_FAIL = "Fail to get Fingerpritn. Try again!";
		public static final String GET_FINGERPRINT_SUCCESS = "Success";
	}
	
	public class Http {
		public static final String DETECT_IDENTIFY_CARD_URL = "http://10.1.12.58:50520/get_info_base64";
		public static final String URL_CREATE_DRIVER = "/api/DriverManagement/CreateDriverManagerment";
		public static final String URL_CHECKIN_DRIVER = "/api/DriverManagement/DriverManagermentCheckIn";
		public static final String URL_LOGIN = "/api/Login/CheckLogin";
		public static final String URL_CHECK_TOKEN = "/api/Login/CheckUserToken";
	}
	
	public class Prefix {
		public static final String HTTPS = "https://";
	}
	
	public static final int SUCCESS_CODE = 200;
	public static final String NONE = "---";
}
