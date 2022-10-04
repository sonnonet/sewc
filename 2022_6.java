    public class myDBHelper extends SQLiteOpenHelper {
        public myDBHelper(Context context) {
            super(context, "groupDB", null, 1);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            db.execSQL("CREATE TABLE user1 ( gName CHAR(20), gPhoneNumber CHAR(20), gEmailAddress CHAR(40) PRIMARY KEY,gSecure INTEGER, gAddress CHAR(100));");
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            db.execSQL("DROP TABLE IF EXISTS user1");
            onCreate(db);

        }
    }
