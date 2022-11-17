package source.third_party.firebase_user_authentication.service;

import org.json.simple.JSONObject;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestTemplate;
import source.third_party.firebase_user_authentication.bean.FirebaseRefreshTokenToIdTokenResponseBean;
import source.third_party.firebase_user_authentication.bean.FirebaseSignInSignUpResponseBean;
import source.third_party.firebase_user_authentication.constants.ApiUrlConstants;
import source.third_party.firebase_user_authentication.exception.HttpBadRequestException;
import source.third_party.firebase_user_authentication.exception.HttpNotFoundException;
import source.third_party.firebase_user_authentication.exception.HttpUnauthorizedException;
import source.third_party.firebase_user_authentication.utility.StringUtility;

@Service
public class UserAuthenticationServiceImpl implements UserAuthenticationService {

    private final String firebaseWebApiKey;
    private StringUtility stringUtility = null;

    public UserAuthenticationServiceImpl() {
        stringUtility = new StringUtility();
        this.firebaseWebApiKey = "AIzaSyBFflIeftc5O-m9TotdYgyVcLEFbXGskqQ";

    }

    @Override
    public FirebaseSignInSignUpResponseBean signInWithEmailAndPassword(String email, String password) {

        HttpEntity<String> request = createPostRequestBodyForEmailAndPassword(email, password);

        // This is the generated URI
        String url = stringUtility.findAndReplaceStringIntoString(ApiUrlConstants.FIREBASE_SIGNIN_EMAIL_AND_PASSWORD,
                ApiUrlConstants.FIREBASE_SIGNIN_EMAIL_AND_PASSWORD_TO_BE_CHANGED_PART,
                this.firebaseWebApiKey);

        ResponseEntity<FirebaseSignInSignUpResponseBean> responseEntity = (ResponseEntity<FirebaseSignInSignUpResponseBean>) doPostForEntity(url, request, FirebaseSignInSignUpResponseBean.class);

        return responseEntity.getBody();
    }

    @Override
    public void deleteUserAccount(String idToken) {

        HttpEntity<String> request = createPostRequestBodyForIdToken(idToken);

        // This is the generated URI
        String url = stringUtility.findAndReplaceStringIntoString(ApiUrlConstants.FIREBASE_DELETE_USER_ACCOUNT,
                ApiUrlConstants.FIREBASE_DELETE_USER_ACCOUNT_TO_BE_CHANGED_PART,
                this.firebaseWebApiKey);

        doPostForEntity(url, request, null);

    }

    @Override
    public FirebaseSignInSignUpResponseBean signUpWithEmailAndPassword(String email, String password) {

        HttpEntity<String> request = createPostRequestBodyForEmailAndPassword(email, password);

        // This is the generated URI
        String url = stringUtility.findAndReplaceStringIntoString(ApiUrlConstants.FIREBASE_SIGNUP_EMAIL_AND_PASSWORD,
                ApiUrlConstants.FIREBASE_SIGNUP_EMAIL_AND_PASSWORD_TO_BE_CHANGED_PART,
                this.firebaseWebApiKey);

        ResponseEntity<FirebaseSignInSignUpResponseBean> responseEntity = (ResponseEntity<FirebaseSignInSignUpResponseBean>) doPostForEntity(url, request, FirebaseSignInSignUpResponseBean.class);

        return responseEntity.getBody();
    }

    @Override
    public FirebaseRefreshTokenToIdTokenResponseBean exchangeRefreshTokenToIdToken(String refreshToken) {

        HttpEntity<MultiValueMap<String, String>> request = createPostRequestBodyForRefreshTokenToIdToken(refreshToken);

        // This is the generated URI
        String url = stringUtility.findAndReplaceStringIntoString(ApiUrlConstants.FIREBASE_EXCHANGE_REFRESH_TOKEN_TO_ID_TOKEN,
                ApiUrlConstants.FIREBASE_EXCHANGE_REFRESH_TOKEN_TO_ID_TOKEN_TO_BE_CHANGED_PART,
                this.firebaseWebApiKey);

        ResponseEntity<FirebaseRefreshTokenToIdTokenResponseBean> responseEntity = (ResponseEntity<FirebaseRefreshTokenToIdTokenResponseBean>) doPostForEntity(url, request, FirebaseRefreshTokenToIdTokenResponseBean.class);

        return responseEntity.getBody();
    }

    private ResponseEntity<?> doPostForEntity(String url, Object request, Class<?> responseType) {

        ResponseEntity<?> responseEntity = null;
        try {
            responseEntity = new RestTemplate().postForEntity(url, request, responseType);
        } catch (HttpStatusCodeException e) {
            if (e.getStatusCode() == HttpStatus.BAD_REQUEST) {
                throw new HttpBadRequestException(e.getResponseBodyAsString());
            } else if (e.getStatusCode() == HttpStatus.NOT_FOUND) {
                throw new HttpNotFoundException(e.getResponseBodyAsString());
            } else if (e.getStatusCode() == HttpStatus.UNAUTHORIZED) {
                throw new HttpUnauthorizedException(e.getResponseBodyAsString());
            } else {
                throw new RuntimeException();
            }
        }

        return responseEntity;

    }

    private HttpEntity<String> createPostRequestBodyForEmailAndPassword(String email, String password) {

        JSONObject requestBodyJsonObject = new JSONObject();
        requestBodyJsonObject.put("email", email);
        requestBodyJsonObject.put("password", password);
        requestBodyJsonObject.put("returnSecureToken", "true");

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<String> requestBody = new HttpEntity<String>(requestBodyJsonObject.toString(), httpHeaders);

        return requestBody;

    }

    private HttpEntity<String> createPostRequestBodyForIdToken(String idToken) {

        JSONObject requestBodyJsonObject = new JSONObject();
        requestBodyJsonObject.put("idToken", idToken);

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<String> requestBody = new HttpEntity<String>(requestBodyJsonObject.toString(), httpHeaders);

        return requestBody;

    }

    private HttpEntity<MultiValueMap<String, String>> createPostRequestBodyForRefreshTokenToIdToken(String refreshToken) {

        MultiValueMap<String, String> map = new LinkedMultiValueMap<String, String>();
        map.add("grant_type", "refresh_token");
        map.add("refresh_token", refreshToken);

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        HttpEntity<MultiValueMap<String, String>> requestBody = new HttpEntity<MultiValueMap<String, String>>(map, httpHeaders);

        return requestBody;

    }
}