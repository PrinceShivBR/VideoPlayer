package task.taskproject.ui.home.interfaces;

import java.util.List;

import task.taskproject.models.VideoModel;

/**
 * Created by shivappa.battur on 10/10/2018
 */
public interface IHomeContract {
    interface IHomeView {
        void showSwipeRefresh();

        void hideSwipeRefresh();

        void onFetchSuccess(List<VideoModel> videoList);

        void showError(String errorMessage);
    }

    interface IHomePresenter {
        void fetchVideoList();
    }

}
