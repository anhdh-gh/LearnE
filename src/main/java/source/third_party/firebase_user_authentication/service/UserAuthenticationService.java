package source.third_party.firebase_user_authentication.service;

import source.third_party.firebase_user_authentication.bean.FirebaseRefreshTokenToIdTokenResponseBean;
import source.third_party.firebase_user_authentication.bean.FirebaseSignInSignUpResponseBean;

public interface UserAuthenticationService {

    FirebaseSignInSignUpResponseBean signUpWithEmailAndPassword(String email, String password);

    FirebaseSignInSignUpResponseBean signInWithEmailAndPassword(String email, String password);

    void deleteUserAccount(String idToken);

    FirebaseRefreshTokenToIdTokenResponseBean exchangeRefreshTokenToIdToken(String refreshToken);

}
