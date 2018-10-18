package task.taskproject.database;

/**
 * Created by shivappa.battur on 10/10/2018
 */
final class DBConstants {

    static class VIDEO {
        static final String TABLE_VIDEO = "video";
        static final String VIDEO_ID = "v_id";
        static final String VIDEO_TITLE = "v_title";
        static final String VIDEO_URL = "v_url";
        static final String VIDEO_THUMB = "v_thumb";
        static final String VIDEO_DESC = "v_desc";
        static final String CREATE_VIDEO_TABLE = "CREATE TABLE " + TABLE_VIDEO + "("
                + VIDEO_ID + " INTEGER PRIMARY KEY, "
                + VIDEO_TITLE + " TEXT, "
                + VIDEO_URL + " TEXT, "
                + VIDEO_THUMB + " BLOB NOT NULL, "
                + VIDEO_DESC + " TEXT "
                + ")";

        private VIDEO() {

        }
    }
}
