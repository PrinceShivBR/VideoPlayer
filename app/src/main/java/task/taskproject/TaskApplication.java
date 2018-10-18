package task.taskproject;

import android.app.Application;
import android.content.Context;

/**
 * Created by shivappa.battur on 15/10/2018
 */
public class TaskApplication extends Application {
    private static Context mContext;

    public static Context getAppContext() {
        return mContext;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mContext = getApplicationContext();
    }
}
