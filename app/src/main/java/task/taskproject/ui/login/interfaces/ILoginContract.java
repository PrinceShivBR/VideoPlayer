package task.taskproject.ui.login.interfaces;

/**
 * Created by shivappa.battur on 14/10/2018
 */
public interface ILoginContract {
    interface ILoginView {
        void showProgressBar();

        void hideProgressBar();

        void showError(String errorMessage);

        void onLoginSuccess();
    }

    interface ILoginPresenter {
        void doGoogleSignIn();
    }
}
