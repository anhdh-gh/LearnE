package source.service.user_service;

import com.github.alperkurtul.firebaseuserauthentication.bean.FirebaseRefreshTokenToIdTokenResponseBean;
import com.github.alperkurtul.firebaseuserauthentication.bean.FirebaseSignInSignUpResponseBean;
import com.github.alperkurtul.firebaseuserauthentication.exception.HttpBadRequestException;
import com.github.alperkurtul.firebaseuserauthentication.exception.HttpNotFoundException;
import com.github.alperkurtul.firebaseuserauthentication.exception.HttpUnauthorizedException;
import com.github.alperkurtul.firebaseuserauthentication.service.UserAuthenticationService;
import com.github.alperkurtul.firebaseuserauthentication.utility.StringUtility;
import org.json.JSONObject;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.util.ResourceUtils;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestTemplate;

@Service
public class UserAuthenticationServiceImplCustom implements UserAuthenticationService {
    private String firebaseWebApiKey;
    private StringUtility stringUtility = null;

    public UserAuthenticationServiceImplCustom() {
        this.stringUtility = new StringUtility();
        this.firebaseWebApiKey = "AIzaSyDIeSr_V2SNfeql7kdj6m3boQKNVffrXvc";
    }

    public FirebaseSignInSignUpResponseBean signInWithEmailAndPassword(String email, String password) {
        HttpEntity<String> request = this.createPostRequestBodyForEmailAndPassword(email, password);
        String url = this.stringUtility.findAndReplaceStringIntoString("https://identitytoolkit.googleapis.com/v1/accounts:signInWithPassword?key=[API_KEY]", "[API_KEY]", this.firebaseWebApiKey);
        ResponseEntity<FirebaseSignInSignUpResponseBean> responseEntity = (ResponseEntity<FirebaseSignInSignUpResponseBean>) this.doPostForEntity(url, request, FirebaseSignInSignUpResponseBean.class);
        return (FirebaseSignInSignUpResponseBean)responseEntity.getBody();
    }

    public void deleteUserAccount(String idToken) {
        HttpEntity<String> request = this.createPostRequestBodyForIdToken(idToken);
        String url = this.stringUtility.findAndReplaceStringIntoString("https://identitytoolkit.googleapis.com/v1/accounts:delete?key=[API_KEY]", "[API_KEY]", this.firebaseWebApiKey);
        this.doPostForEntity(url, request, (Class)null);
    }

    public FirebaseSignInSignUpResponseBean signUpWithEmailAndPassword(String email, String password) {
        HttpEntity<String> request = this.createPostRequestBodyForEmailAndPassword(email, password);
        String url = this.stringUtility.findAndReplaceStringIntoString("https://identitytoolkit.googleapis.com/v1/accounts:signUp?key=[API_KEY]", "[API_KEY]", this.firebaseWebApiKey);
        ResponseEntity<FirebaseSignInSignUpResponseBean> responseEntity = (ResponseEntity<FirebaseSignInSignUpResponseBean>) this.doPostForEntity(url, request, FirebaseSignInSignUpResponseBean.class);
        return (FirebaseSignInSignUpResponseBean)responseEntity.getBody();
    }

    public FirebaseRefreshTokenToIdTokenResponseBean exchangeRefreshTokenToIdToken(String refreshToken) {
        HttpEntity<MultiValueMap<String, String>> request = this.createPostRequestBodyForRefreshTokenToIdToken(refreshToken);
        String url = this.stringUtility.findAndReplaceStringIntoString("https://securetoken.googleapis.com/v1/token?key=[API_KEY]", "[API_KEY]", this.firebaseWebApiKey);
        ResponseEntity<FirebaseRefreshTokenToIdTokenResponseBean> responseEntity = (ResponseEntity<FirebaseRefreshTokenToIdTokenResponseBean>) this.doPostForEntity(url, request, FirebaseRefreshTokenToIdTokenResponseBean.class);
        return (FirebaseRefreshTokenToIdTokenResponseBean)responseEntity.getBody();
    }

    private ResponseEntity<?> doPostForEntity(String url, Object request, Class<?> responseType) {
        ResponseEntity<?> responseEntity = null;

        try {
            responseEntity = (new RestTemplate()).postForEntity(url, request, responseType, new Object[0]);
            return responseEntity;
        } catch (HttpStatusCodeException var6) {
            if (var6.getStatusCode() == HttpStatus.BAD_REQUEST) {
                throw new HttpBadRequestException(var6.getResponseBodyAsString());
            } else if (var6.getStatusCode() == HttpStatus.NOT_FOUND) {
                throw new HttpNotFoundException(var6.getResponseBodyAsString());
            } else if (var6.getStatusCode() == HttpStatus.UNAUTHORIZED) {
                throw new HttpUnauthorizedException(var6.getResponseBodyAsString());
            } else {
                throw new RuntimeException();
            }
        }
    }

    private HttpEntity<String> createPostRequestBodyForEmailAndPassword(String email, String password) {
        JSONObject requestBodyJsonObject = new JSONObject();
        requestBodyJsonObject.put("email", email);
        requestBodyJsonObject.put("password", password);
        requestBodyJsonObject.put("returnSecureToken", "true");
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> requestBody = new HttpEntity(requestBodyJsonObject.toString(), httpHeaders);
        return requestBody;
    }

    private HttpEntity<String> createPostRequestBodyForIdToken(String idToken) {
        JSONObject requestBodyJsonObject = new JSONObject();
        requestBodyJsonObject.put("idToken", idToken);
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> requestBody = new HttpEntity(requestBodyJsonObject.toString(), httpHeaders);
        return requestBody;
    }

    private HttpEntity<MultiValueMap<String, String>> createPostRequestBodyForRefreshTokenToIdToken(String refreshToken) {
        MultiValueMap<String, String> map = new LinkedMultiValueMap();
        map.add("grant_type", "refresh_token");
        map.add("refresh_token", refreshToken);
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        HttpEntity<MultiValueMap<String, String>> requestBody = new HttpEntity(map, httpHeaders);
        return requestBody;
    }
}