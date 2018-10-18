package task.taskproject.ui.playvideo.interfaces;

import java.util.List;

import task.taskproject.models.VideoModel;

/**
 * Created by shivappa.battur on 10/10/2018
 */
public interface IPlayVideoInteractor {
    void fetchVideoList(int videoId);

    interface OnVideoFetchCallback {
        void onVideoFetchSuccess(List<VideoModel> videoList);

        void onFetchFailure(String errorMessage);
    }
}
