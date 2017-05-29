package TestMD5;

import java.security.MessageDigest;


public class TestMD5 {
	public static void main(String[] args) throws Exception {
          System.out.println(md5Encode("amigoxiexiexingxing"));
	}

	public static String md5Encode(String str) throws Exception {
		MessageDigest md5 = MessageDigest.getInstance("MD5");
		byte[] byteArray = str.getBytes();
		byte[] md5Array = md5.digest(byteArray);
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < md5Array.length; i++) {
			int val = md5Array[i] & 0xff;
			if (val < 16) {
				sb.append("0");
			}
			sb.append(Integer.toHexString(val));
		}
		return sb.toString();

	}
	
}
