package task.taskproject.ui.home;

import java.util.List;

import task.taskproject.models.VideoModel;
import task.taskproject.ui.home.interfaces.IHomeContract;
import task.taskproject.ui.home.interfaces.IHomeInteractor;

/**
 * Created by shivappa.battur on 10/10/2018
 */
public class HomePresenterImpl implements IHomeContract.IHomePresenter, IHomeInteractor.OnVideoFetchCallback {
    private IHomeContract.IHomeView mView_;
    private IHomeInteractor interactor;

    HomePresenterImpl(IHomeContract.IHomeView view) {
        this.mView_ = view;
        this.interactor = new HomeInteractorImpl(this);
    }

    @Override
    public void fetchVideoList() {
        interactor.fetchVideoList();
    }

    @Override
    public void onVideoFetchSuccess(List<VideoModel> videoList) {
        mView_.hideSwipeRefresh();
        mView_.onFetchSuccess(videoList);
    }

    @Override
    public void onFailure(String errorMessage) {
        mView_.hideSwipeRefresh();
        mView_.showError(errorMessage);
    }
}
