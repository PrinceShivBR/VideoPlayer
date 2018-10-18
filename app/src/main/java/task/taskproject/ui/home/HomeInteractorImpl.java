package task.taskproject.ui.home;

import android.content.Context;
import android.support.annotation.NonNull;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.http.GET;
import retrofit2.http.Query;
import task.taskproject.TaskApplication;
import task.taskproject.database.DBHelper;
import task.taskproject.models.VideoModel;
import task.taskproject.network.ConnectivityHelper;
import task.taskproject.ui.home.interfaces.IHomeInteractor;

/**
 * Created by shivappa.battur on 10/10/2018
 */
public class HomeInteractorImpl implements IHomeInteractor {
    private OnVideoFetchCallback callback;
    private Context mContext_;

    HomeInteractorImpl(OnVideoFetchCallback callback) {
        this.callback = callback;
//        this.mContext_ = context;
    }

    @Override
    public void fetchVideoList() {
        ConnectivityHelper helper = new ConnectivityHelper(TaskApplication.getAppContext());
        boolean isNetworkAvailable = helper.isNetworkAvailable();
        if (isNetworkAvailable) {
            IFetchApi retrofit = ConnectivityHelper.getRetrofitInstance().create(IFetchApi.class);
            Call<List<VideoModel>> call = retrofit.getVideoList("pretty");
            call.enqueue(new Callback<List<VideoModel>>() {
                @Override
                public void onResponse(@NonNull Call<List<VideoModel>> call, @NonNull Response<List<VideoModel>> response) {
                    if (response.isSuccessful()) {
                        if (response.body() != null) {
                            if (insertVideoDataIntoDb(response.body())) {
                                callback.onVideoFetchSuccess(response.body());
                            }
                        } else {
                            callback.onFailure("fetching video list failed");
                        }
                    } else {
                        callback.onFailure("Something went wrong");
                    }
                }

                @Override
                public void onFailure(@NonNull Call<List<VideoModel>> call, @NonNull Throwable t) {
                    callback.onFailure(t.getMessage());
                }
            });
        } else {
            callback.onFailure("No internet connection");
        }
    }

    private boolean insertVideoDataIntoDb(List<VideoModel> videoModelList) {
        return DBHelper.getInstance(TaskApplication.getAppContext()).insertVideoListData(videoModelList);
    }

    interface IFetchApi {
        @GET("media.json")
        Call<List<VideoModel>> getVideoList(@Query("print") String param);
    }
}
