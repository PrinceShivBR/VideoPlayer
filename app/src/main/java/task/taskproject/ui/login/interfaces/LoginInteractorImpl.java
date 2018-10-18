package task.taskproject.ui.login.interfaces;

import android.content.Context;

import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.firebase.auth.FirebaseAuth;

import task.taskproject.R;

/**
 * Created by shivappa.battur on 14/10/2018
 */
public class LoginInteractorImpl implements ILoginInteractor {
    private OnLoginResultCallback callback;
    private GoogleApiClient mGoogleApiClient_;
    private FirebaseAuth mAuth_;
    private FirebaseAuth.AuthStateListener mAuthStateListener_;
    private Context mContext_;

    LoginInteractorImpl(OnLoginResultCallback callback) {
        this.callback = callback;
        mAuth_ = FirebaseAuth.getInstance();
    }

    @Override
    public void doGoogleSignIn() {
        callback.onLoginSuccess();
    }

    // This method configures Google SignIn
    public void configureSignIn() {
// Configure sign-in to request the user's basic profile like name and email
        GoogleSignInOptions options = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(mContext_.getResources().getString(R.string.web_client_id))
                .requestEmail()
                .build();

        // Build a GoogleApiClient with access to GoogleSignIn.API and the options above.
        mGoogleApiClient_ = new GoogleApiClient.Builder(mContext_)
//                .enableAutoManage(mContext_,mContext_)
                .addApi(Auth.GOOGLE_SIGN_IN_API, options)
                .build();
        mGoogleApiClient_.connect();
    }
}
