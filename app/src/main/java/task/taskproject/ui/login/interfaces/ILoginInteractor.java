package task.taskproject.ui.login.interfaces;

/**
 * Created by shivappa.battur on 14/10/2018
 */
public interface ILoginInteractor {
    void doGoogleSignIn();

    interface OnLoginResultCallback {
        void onLoginSuccess();

        void onLoginFailure(String error);
    }
}
