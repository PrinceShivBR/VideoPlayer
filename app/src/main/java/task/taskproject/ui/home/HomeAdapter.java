package task.taskproject.ui.home;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.jakewharton.picasso.OkHttp3Downloader;
import com.squareup.picasso.Picasso;

import java.util.List;

import task.taskproject.R;
import task.taskproject.models.VideoModel;
import task.taskproject.ui.playvideo.PlayVideoActivity;

/**
 * Created by shivappa.battur on 10/10/2018
 */
public class HomeAdapter extends RecyclerView.Adapter<HomeAdapter.ViewHolder> {
    private final List<VideoModel> videoList;
    private Context mContext_;

    public HomeAdapter(Context context, List<VideoModel> videoList) {
        this.mContext_ = context;
        this.videoList = videoList;

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.layout_video_list, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, int position) {
        holder.tvTitle.setText(videoList.get(position).getTitle());

        Picasso.Builder builder = new Picasso.Builder(mContext_);
        builder.downloader(new OkHttp3Downloader(mContext_));
        builder.build().load(videoList.get(position).getThumb())
                .placeholder((R.drawable.ic_launcher_background))
                .error(R.drawable.ic_launcher_background)
                .into(holder.thumbnail);

        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent videoInfoIntent = new Intent(mContext_, PlayVideoActivity.class);
                videoInfoIntent.putExtra("VideoDetails", videoList.get(holder.getAdapterPosition()));
                videoInfoIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                mContext_.startActivity(videoInfoIntent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return videoList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvTitle;
        ImageView thumbnail;
        CardView cardView;

        ViewHolder(View itemView) {
            super(itemView);

            tvTitle = itemView.findViewById(R.id.video_title);
            thumbnail = itemView.findViewById(R.id.iv_thumbnail);
            cardView = itemView.findViewById(R.id.cardview);
        }
    }
}
