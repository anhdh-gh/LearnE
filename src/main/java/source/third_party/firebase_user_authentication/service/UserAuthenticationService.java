package source.third_party.firebase_user_authentication.service;

import source.third_party.firebase_user_authentication.bean.FirebaseRefreshTokenToIdTokenResponseBean;
import source.third_party.firebase_user_authentication.bean.FirebaseSignInSignUpResponseBean;

public interface UserAuthenticationService {

    public FirebaseSignInSignUpResponseBean signUpWithEmailAndPassword(String email, String password);

    public FirebaseSignInSignUpResponseBean signInWithEmailAndPassword(String email, String password);

    public void deleteUserAccount(String idToken);

    public FirebaseRefreshTokenToIdTokenResponseBean exchangeRefreshTokenToIdToken(String refreshToken);

}
