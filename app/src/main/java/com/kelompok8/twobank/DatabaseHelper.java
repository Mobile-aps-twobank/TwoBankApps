package com.kelompok8.twobank;

// DatabaseHelper.java
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "e_banking.db";
    private static final int DATABASE_VERSION = 1;

    // Table names and column names
    public static final String TABLE_USERS = "users";
    public static final String COLUMN_ID = "id";
    public static final String COLUMN_USERNAME = "username";
    public static final String COLUMN_NOREK = "no_rek";
    public static final String COLUMN_EMAIL = "email";
    public static final String COLUMN_PASSWORD = "password";

    private static final String CREATE_TABLE_USERS = "CREATE TABLE " + TABLE_USERS + "("
            + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + COLUMN_USERNAME + " TEXT, "
            + COLUMN_EMAIL + " TEXT, "
            + COLUMN_NOREK + " TEXT, "
            + COLUMN_PASSWORD + " TEXT);";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_USERS);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_USERS);
        onCreate(db);
    }

    // Metode untuk mengambil data pengguna dari tabel users
    public User getUser() {
        SQLiteDatabase db = this.getReadableDatabase();
        User user = null;

        String[] columns = {
                COLUMN_ID,
                COLUMN_USERNAME,
                COLUMN_EMAIL,
                COLUMN_NOREK,
                COLUMN_PASSWORD
        };

        Cursor cursor = db.query(
                TABLE_USERS,
                columns,
                null,
                null,
                null,
                null,
                null
        );

        if (cursor != null && cursor.moveToFirst()) {
            user = new User();
            user.setId(cursor.getInt(cursor.getColumnIndex(COLUMN_ID)));
            user.setUsername(cursor.getString(cursor.getColumnIndex(COLUMN_USERNAME)));
            user.setEmail(cursor.getString(cursor.getColumnIndex(COLUMN_EMAIL)));
            user.setNorek(cursor.getString(cursor.getColumnIndex(COLUMN_NOREK)));
            user.setPassword(cursor.getString(cursor.getColumnIndex(COLUMN_PASSWORD)));
        }

        if (cursor != null) {
            cursor.close();
        }

        return user;
    }

    // Metode untuk memperbarui data pengguna
    public boolean updateUser(User user) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(COLUMN_USERNAME, user.getUsername());
        values.put(COLUMN_EMAIL, user.getEmail());
        values.put(COLUMN_NOREK, user.getNorek());
        values.put(COLUMN_PASSWORD, user.getPassword());

        int rowsAffected = db.update(
                TABLE_USERS,
                values,
                COLUMN_ID + " = ?",
                new String[] { String.valueOf(user.getId()) }
        );

        return rowsAffected > 0;
    }

}