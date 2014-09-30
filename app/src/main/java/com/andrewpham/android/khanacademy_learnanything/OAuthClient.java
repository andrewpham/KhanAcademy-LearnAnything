package com.andrewpham.android.khanacademy_learnanything;

import com.temboo.Library.KhanAcademy.OAuth.InitializeOAuth;
import com.temboo.core.TembooSession;

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

    private static final String TEMBOO_ACCOUNT_NAME = "pokedough";
    private static final String TEMBOO_APP_KEY_NAME = "KhanAcademy-LearnAnything";
    private static final String TEMBOO_APP_KEY_VALUE = "7f80e64b41284e74975efbf01f058d37";

    private static final String ACCESS_URL = "http://www.khanacademy.org/api/auth/access_token";
    private static final String CALLBACK_URL = "http%3A%2F%2Fwww.khanacademy.org%2Fapi%2Fauth%2Fdefault_callback";

    private static TembooSession mSession;
    private static Base64 mBase64 = new Base64();

    public static String initialize() throws Exception {
        mSession = new TembooSession(TEMBOO_ACCOUNT_NAME, TEMBOO_APP_KEY_NAME, TEMBOO_APP_KEY_VALUE);
        InitializeOAuth initializeOAuthChoreo = new InitializeOAuth(mSession);
        InitializeOAuth.InitializeOAuthInputSet initializeOAuthInputs = initializeOAuthChoreo.newInputSet();
        initializeOAuthInputs.set_ConsumerKey(CONSUMER_KEY);
        initializeOAuthInputs.set_ConsumerSecret(CONSUMER_SECRET);
        InitializeOAuth.InitializeOAuthResultSet initializeOAuthResults = initializeOAuthChoreo.execute(initializeOAuthInputs);

        return initializeOAuthResults.get_AuthorizationURL();
    }

    public static String authenticate(String token, String verifier) throws Exception {

        long nonce = System.currentTimeMillis();
        long timestamp = nonce / 1000L;

        String params = "oauth_callback=" + CALLBACK_URL;
        params += "&oauth_consumer_key=" + CONSUMER_KEY;
        params += "&oauth_nonce=" + Long.toString(nonce);
        params += "&oauth_signature_method=" + "HMAC-SHA1";
        params += "&oauth_timestamp=" + Long.toString(timestamp);
        params += "&oauth_token=" + token;
        params += "&oauth_verifier=" + verifier;
        params += "&oauth_version=" + "1.0";

        String url = ACCESS_URL;
        url += "?" + params + "&oauth_signature=" + getSignature("GET" + "&" + encode(ACCESS_URL) + "&" + encode(params));

        return url;

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

}
