package task.taskproject.ui.playvideo.interfaces;

import java.util.List;

import task.taskproject.models.VideoModel;

/**
 * Created by shivappa.battur on 10/10/2018
 */
public interface IPlayVideoContract {
    interface IPlayVideoView {
        void showVideoList(List<VideoModel> videoList);

        void showError(String error);
    }

    interface IPlayVideoPresenter {
        void fetchVideoList(int videoId);
    }
}
