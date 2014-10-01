package com.andrewpham.android.khanacademy_learnanything.oauth;

import org.apache.commons.codec.binary.Base64;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import javax.crypto.Mac;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

/**
 * Created by andrewpham on 9/26/14.
 */
public class OAuthClient {

    private static final String CONSUMER_KEY = "MSEDRueRnLjvzZe7";
    private static final String CONSUMER_SECRET = "gpkQW95dCDz94ebs";

    private static final String REQUEST_URL = "http://www.khanacademy.org/api/auth/request_token";
    private static final String ACCESS_URL = "http://www.khanacademy.org/api/auth/access_token";

    private static Base64 mBase64 = new Base64();

    public static String initialize() throws Exception {
        return getUrl(REQUEST_URL, null);
    }

    public static String authenticate(String token) throws Exception {
        return getUrl(ACCESS_URL, token);
    }

    private static String encode(String value) {
        String encoded = "";
        try {
            encoded = URLEncoder.encode(value, "UTF-8");
        } catch (Exception e) {
            e.printStackTrace();
        }
        StringBuilder sb = new StringBuilder(encoded.length());
        char focus;
        for (int i = 0; i < encoded.length(); i++) {
            focus = encoded.charAt(i);
            if (focus == '*') {
                sb.append("%2A");
            } else if (focus == '+') {
                sb.append("%20");
            } else if (focus == '%' && i + 1 < encoded.length()
                    && encoded.charAt(i + 1) == '7' && encoded.charAt(i + 2) == 'E') {
                sb.append('~');
                i += 2;
            } else {
                sb.append(focus);
            }
        }
        return sb.toString();
    }

    private static String getSignature(String base)
            throws UnsupportedEncodingException, NoSuchAlgorithmException,
            InvalidKeyException {
        byte[] keyBytes = (CONSUMER_SECRET + "&").getBytes("UTF-8");

        SecretKey key = new SecretKeySpec(keyBytes, "HmacSHA1");

        Mac mac = Mac.getInstance("HmacSHA1");
        mac.init(key);

        // encode it, mBase64 it, change it to string and return.
        return new String(mBase64.encode(mac.doFinal(base.toString().getBytes(
                "UTF-8"))), "UTF-8").trim();
    }

    private static String getParams(String token) {
        long nonce = System.currentTimeMillis();
        long timestamp = nonce / 1000L;
        String params = "oauth_consumer_key=" + CONSUMER_KEY +
                "&oauth_nonce=" + Long.toString(nonce) +
                "&oauth_signature_method=" + "HMAC-SHA1" +
                "&oauth_timestamp=" + Long.toString(timestamp);
        if (token != null && !token.isEmpty()) {
            params += "&oauth_token=" + token;
        }
        params += "&oauth_version=" + "1.0";
        return params;
    }

    private static String getUrl(String urlSpec, String token)
            throws UnsupportedEncodingException, NoSuchAlgorithmException, InvalidKeyException {
        return urlSpec + "?" + getParams(token) + "&oauth_signature=" +
                getSignature("GET" + "&" + encode(urlSpec) + "&" + encode(getParams(token)));
    }

}
