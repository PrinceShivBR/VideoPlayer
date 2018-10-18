package task.taskproject.ui.playvideo;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.devbrackets.android.exomedia.listener.OnPreparedListener;
import com.devbrackets.android.exomedia.listener.OnVideoSizeChangedListener;
import com.devbrackets.android.exomedia.ui.widget.VideoView;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import task.taskproject.R;
import task.taskproject.models.VideoModel;
import task.taskproject.ui.home.HomeAdapter;
import task.taskproject.ui.playvideo.interfaces.IPlayVideoContract;

/**
 * Created by shivappa.battur on 10/10/2018
 */
public class PlayVideoActivity extends AppCompatActivity implements IPlayVideoContract.IPlayVideoView, OnPreparedListener {

    private VideoView mVideoView_;
    private ImageView ivPlay;
    private Queue<List<VideoModel>> mQueue_;
    private ProgressBar mProgressBar_;
    private VideoModel videoModel;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_description);

        Intent intent = getIntent();
        videoModel = intent.getParcelableExtra("VideoDetails");
        setUpViews();
        initListeners();

        mQueue_ = new LinkedList<>();

        IPlayVideoContract.IPlayVideoPresenter presenter = new PlayVideoPresenterImpl(this);
        presenter.fetchVideoList(Integer.parseInt(videoModel.getId()));
    }

    private void initListeners() {
        mVideoView_.setOnPreparedListener(this);
        ivPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ivPlay.setVisibility(View.GONE);
                mProgressBar_.setVisibility(View.VISIBLE);
                mVideoView_.setVideoURI(Uri.parse(videoModel.getUrl()));
            }
        });
    }

    private void setUpViews() {
        TextView tvTile = findViewById(R.id.tv_title);
        tvTile.setText(videoModel.getTitle());
        TextView tvDesc = findViewById(R.id.tv_desc);
        tvDesc.setText(videoModel.getDescription());
        mProgressBar_ = findViewById(R.id.progress_bar);
        mVideoView_ = findViewById(R.id.video_view);
        ivPlay = findViewById(R.id.iv_play);
    }

    @Override
    public void showVideoList(List<VideoModel> videoList) {
        mQueue_.add(videoList);
        HomeAdapter adapter = new HomeAdapter(this, videoList);
        RecyclerView recyclerView = findViewById(R.id.recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void showError(String error) {
        Toast.makeText(this, error, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onPrepared() {
        mVideoView_.start();
        mProgressBar_.setVisibility(View.GONE);
        mVideoView_.setOnVideoSizedChangedListener(new OnVideoSizeChangedListener() {
            @Override
            public void onVideoSizeChanged(int intrinsicWidth, int intrinsicHeight) {
                mProgressBar_.setVisibility(View.GONE);
                mVideoView_.start();
            }
        });
    }
}
