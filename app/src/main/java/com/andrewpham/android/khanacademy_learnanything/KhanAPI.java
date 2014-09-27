package com.andrewpham.android.khanacademy_learnanything;

import org.scribe.builder.api.DefaultApi10a;
import org.scribe.model.Token;

public class KhanAPI extends DefaultApi10a {

    /**
     * Returns the URL that receives the request token requests.
     *
     * @return request token URL
     */
    public String getRequestTokenEndpoint() {
        return "http://www.khanacademy.org/api/auth/request_token";
    }

    /**
     * Returns the URL that receives the access token requests.
     *
     * @return access token URL
     */
    @Override
    public String getAccessTokenEndpoint() {
        return "http://www.khanacademy.org/api/auth/access_token";
        // return null;
    }

    @Override
    public String getAuthorizationUrl(Token arg0) {
        // TODO Auto-generated method stub
        try {
            return OAuthClient.initialize();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

}