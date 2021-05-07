package board.boardtest.util;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * 첨부 파일 업로드시 MD5 체크섬 값으로 서버에 저장되도록 구현할 예정
 * 작성일 : 2021-05-07
 * 참고 : https://kyuhyuk.kr/article/spring-boot/2020/07/22/Spring-Boot-JPA-MySQL-Board-Post-File-Upload-Download
 */
public class MD5Generator {
    
    private String result;

    public MD5Generator(String input) throws UnsupportedEncodingException, NoSuchAlgorithmException{
        MessageDigest mdMD5 = MessageDigest.getInstance("MD5");
        mdMD5.update(input.getBytes("UTF-8"));
        byte[] md5Hash = mdMD5.digest();
        StringBuilder hexMD5hash = new StringBuilder();
        for(byte b : md5Hash){
            String hexString = String.format("%02x",b);
            hexMD5hash.append(hexString);
        }
        result = hexMD5hash.toString();

    }

    public String toString(){
        return result;
    }
}
