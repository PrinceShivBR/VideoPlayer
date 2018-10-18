package task.taskproject.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

import task.taskproject.models.VideoModel;

/**
 * Created by shivappa.battur on 10/10/2018
 */
public class DBHelper {
    private static final int DB_VERSION = 1;
    private static final String DB_NAME = "videos.db";
    private SQLiteDatabase database;
    private static DBHelper INSTANCE;
    private DatabaseOpenHelper openHelper;

    private DBHelper(Context context) {
        this.openHelper = new DatabaseOpenHelper(context, null);
    }

    public static DBHelper getInstance(Context context) {
        if (INSTANCE == null) {
            INSTANCE = new DBHelper(context);
        }
        return INSTANCE;
    }

    public List<VideoModel> getVideoList(int videoId) {
        String selectQuery = "SELECT * FROM " + DBConstants.VIDEO.TABLE_VIDEO
                + " WHERE " + DBConstants.VIDEO.VIDEO_ID + "<>" + videoId + ";";
        Cursor cursor;
        List<VideoModel> modelList = new ArrayList<>();
        database = getReadableDatabase();
        cursor = database.rawQuery(selectQuery, null);
        if (cursor != null && cursor.moveToFirst()) {
            do {
                VideoModel model = new VideoModel();
                model.setId(cursor.getString(cursor.getColumnIndex(DBConstants.VIDEO.VIDEO_ID)));
                model.setTitle(cursor.getString(cursor.getColumnIndex(DBConstants.VIDEO.VIDEO_TITLE)));
                model.setUrl(cursor.getString(cursor.getColumnIndex(DBConstants.VIDEO.VIDEO_URL)));
                model.setThumb(cursor.getString(cursor.getColumnIndex(DBConstants.VIDEO.VIDEO_THUMB)));
                model.setDescription(cursor.getString(cursor.getColumnIndex(DBConstants.VIDEO.VIDEO_DESC)));
                modelList.add(model);
            } while (cursor.moveToNext());


        }
        if (cursor != null) {
            cursor.close();
        }
        return modelList;
    }

    static class DatabaseOpenHelper extends SQLiteOpenHelper {
        DatabaseOpenHelper(@Nullable Context context, @Nullable SQLiteDatabase.CursorFactory factory) {
            super(context, DB_NAME, factory, DB_VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            db.execSQL(DBConstants.VIDEO.CREATE_VIDEO_TABLE);
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            // Drop older table if existed
            db.execSQL("DROP TABLE IF EXISTS " + DBConstants.VIDEO.TABLE_VIDEO);
            onCreate(db);
        }
    }

    private SQLiteDatabase getReadableDatabase() {
        return openHelper.getReadableDatabase();
    }

    private SQLiteDatabase getWritableDatabase() {
        return openHelper.getWritableDatabase();
    }

    public boolean insertVideoListData(List<VideoModel> videoDataModels) {
        database = getWritableDatabase();
        for (int i = 0; i < videoDataModels.size(); i++) {
            ContentValues contentValues = new ContentValues();
            contentValues.put(DBConstants.VIDEO.VIDEO_ID, videoDataModels.get(i).getId());
            contentValues.put(DBConstants.VIDEO.VIDEO_TITLE, videoDataModels.get(i).getTitle());
            contentValues.put(DBConstants.VIDEO.VIDEO_URL, videoDataModels.get(i).getUrl());
            contentValues.put(DBConstants.VIDEO.VIDEO_THUMB, videoDataModels.get(i).getThumb());
            contentValues.put(DBConstants.VIDEO.VIDEO_DESC, videoDataModels.get(i).getDescription());
            long result = database.insertWithOnConflict(DBConstants.VIDEO.TABLE_VIDEO, null, contentValues, SQLiteDatabase.CONFLICT_REPLACE);
            if (result == -1)
                return false;

        }
        return true;
    }
}
