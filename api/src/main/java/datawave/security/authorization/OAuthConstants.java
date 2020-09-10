package datawave.security.authorization;

public class OAuthConstants {
    
    /*
     * Use this grant type in the token API call after receiving an authorization code from the authorize API call
     */
    public static final String GRANT_AUTHORIZATION_CODE = "authorization_code";
    
    /*
     * Use this grant type in the token API call to get a new token using the (longer lived) refresh token which is passed as a parameter
     */
    public static final String GRANT_REFRESH_TOKEN = "refresh_token";
}
