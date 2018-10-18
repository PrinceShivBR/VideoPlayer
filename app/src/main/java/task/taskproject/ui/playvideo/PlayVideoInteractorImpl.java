package task.taskproject.ui.playvideo;

import java.util.List;

import task.taskproject.TaskApplication;
import task.taskproject.database.DBHelper;
import task.taskproject.models.VideoModel;
import task.taskproject.ui.playvideo.interfaces.IPlayVideoInteractor;

/**
 * Created by shivappa.battur on 10/10/2018
 */
public class PlayVideoInteractorImpl implements IPlayVideoInteractor {
    private OnVideoFetchCallback callback;

    PlayVideoInteractorImpl(OnVideoFetchCallback callback) {
        this.callback = callback;
    }

    @Override
    public void fetchVideoList(int videoId) {
        List<VideoModel> videoList = DBHelper.getInstance(TaskApplication.getAppContext()).getVideoList(videoId);
        callback.onVideoFetchSuccess(videoList);
    }
}
