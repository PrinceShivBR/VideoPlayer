package task.taskproject.ui.login;

import task.taskproject.ui.login.interfaces.ILoginContract;
import task.taskproject.ui.login.interfaces.ILoginInteractor;

/**
 * Created by shivappa.battur on 14/10/2018
 */
public class LoginPresenterImpl implements ILoginContract.ILoginPresenter, ILoginInteractor.OnLoginResultCallback {
    private ILoginContract.ILoginView mView_;
    private ILoginInteractor interactor;

    LoginPresenterImpl(ILoginContract.ILoginView view) {
        this.mView_ = view;
    }

    @Override
    public void doGoogleSignIn() {
        mView_.showProgressBar();
        interactor.doGoogleSignIn();
    }

    @Override
    public void onLoginSuccess() {
        mView_.hideProgressBar();
        mView_.onLoginSuccess();
    }

    @Override
    public void onLoginFailure(String error) {
        mView_.hideProgressBar();
        mView_.showError(error);
    }
}
