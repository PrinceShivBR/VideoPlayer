package task.taskproject.ui.home.interfaces;

import java.util.List;

import task.taskproject.models.VideoModel;

/**
 * Created by shivappa.battur on 10/10/2018
 */
public interface IHomeInteractor {
    void fetchVideoList();

    public interface OnVideoFetchCallback {
        void onVideoFetchSuccess(List<VideoModel> videoList);

        void onFailure(String errorMessage);
    }
}
