package com.yh.util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.security.KeyStore;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Signature;
import java.security.cert.Certificate;
import java.security.cert.CertificateFactory;
import java.util.Base64;
import java.util.Enumeration;

/**
 * @Author: yinhui
 * @Date: 2019/5/15 16:14
 * @Version 1.0
 */
public class RSAkeyUtil {

    private static String publicKeyFileName = "C:/demo2/ia.crt";
    private static String privateKeyFileName = "C:/demo2/ia.p12";
    private static String password = "123456";//私钥文件获取时设置的密钥

    /**
     * 签名
     * @param str
     * @return
     */
    public static String sign(String str){

        String base64Sign = "";
        InputStream inputStream = null;

        try {
            inputStream = new FileInputStream(privateKeyFileName);
            KeyStore keyStore = KeyStore.getInstance("PKCS12");

            char[] pswd = password.toCharArray();
            keyStore.load(inputStream,pswd);
            Enumeration<String> enumeration = keyStore.aliases();
            String ailes = enumeration.nextElement();
            PrivateKey privateKey = (PrivateKey) keyStore.getKey(ailes,pswd);
            //签名
            Signature signature = Signature.getInstance("SHA1withRSA");
            signature.initSign(privateKey);

            byte[] dataByte = str.getBytes();
            signature.update(dataByte);
            byte[] signByte = signature.sign();

            //base64编码
            base64Sign = Base64.getEncoder().encodeToString(signByte);

        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            if(inputStream != null){
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        return base64Sign;
    }

    /**
     * 数据验证
     * @param signStr 加密后的字符
     * @param verStr  原始字符
     * @return
     */
    public static boolean varify(String signStr , String verStr){

        boolean verfy = false;
        InputStream inputStream = null;

        try {
            inputStream = new FileInputStream(publicKeyFileName);
            CertificateFactory factory = CertificateFactory.getInstance("x509");
            Certificate certificate = factory.generateCertificate(inputStream);
            PublicKey publicKey = certificate.getPublicKey();

            //base64解码
            byte[] signDecode = Base64.getDecoder().decode(signStr);
            //签名
            Signature signature = Signature.getInstance("SHA1withRSA");
            signature.initVerify(publicKey);
            signature.update(verStr.getBytes());

            verfy = signature.verify(signDecode);
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            if(inputStream != null){
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        return verfy;
    }

    public static void main(String[] str){
        String signData = "123456我是谁2.01";
        System.out.println("待加密数据= "+signData);
        String signTest = RSAkeyUtil.sign(signData);
        System.out.println("加密完成= "+signTest);

        String varifyTest = "3Xq2J71ISVlHXHIehsdrG1aJbt3zqmuZNTz/3HfHZgU8BGQoFuqTZhyVqVxblJ/GS2PR0EFhEZ3uEz8lNPxh8ZHgBzoodOU0KUkZTaE3Epy5zjkBGJVtHuN2UKRtBGuwKQyw21bXKmtTAFB1P0ziw6KyCypoeZNwAiy3jMiGQb1vlU4uC//1DonWNrqjQ1c7w2xqJvoExHgd5CcWA5nbZJTbbwVQoaqyr9pTLCSeKKhF3rQGI+Y1dYpbIAxZ4arddN4h1dJyRE0TmOzKHH7KW/2CElS3nagBoNc08z/PYCXk4jAgGKeayJ2+u3eYyZmjWOvRcOLduyJl6NALAZQxBCF3ZvKEn+0INIZkoMWTKYS5fWcMcqyafU3MeQmQALQ5KzqTpI8/bRg07/1S9NXb02Oj5JzVO11JGxDk3l0oZS3aXYYZRD5zFqwqJR/pRbB0N+71A4IcIbCXHUTXc4oOEPAokeoImQMHnFd1r1qupjBWbas8ZK7uX5OJ+vghfUsQAvHSJYZObw4nOBCkLZVV/iux/RmLkRK2jPJbD/U1yYL5/2XPSGrNs+4YewSW5wOHOJBVtreuZVoz6ihTngPbyr2RGRjjkktltvjQXyn9r6uen1BIxY3EMLbrhk35CT2RC4XUk4ao9zWzqLXiDqcqG8xpOJibHNrrHTvtgJf7ldc=";
        boolean vdata = RSAkeyUtil.varify(varifyTest,signData);
        System.out.println("解密后数据是否相等="+vdata);
    }
}
