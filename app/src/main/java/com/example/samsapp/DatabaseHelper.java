package com.example.samsapp;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteDatabase;
import android.content.Context;
import android.util.Log;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;

public class DatabaseHelper {

    private static final String DATABASE_NAME = "students.db";
    private static final int DATABASE_VERSION = 1;
    private static final String TABLE_NAME = "students";

    private static final String COLUMN_ID = "id";
    private static final String COLUMN_USERNAME = "username";
    private static final String COLUMN_NAME = "name";
    private static final String COLUMN_ROLE = "role";
    private static final String COLUMN_GROUP = "groups";
    private static final String COLUMN_EMAIL = "email";
    private static final String COLUMN_HASHED_PASSWORD = "hashed_password";

    private static final int NUM_COLUMN_ID = 0;
    private static final int NUM_COLUMN_USERNAME = 1;
    private static final int NUM_COLUMN_NAME = 2;
    private static final int NUM_COLUMN_ROLE = 3;
    private static final int NUM_COLUMN_GROUPS = 4;
    private static final int NUM_COLUMN_EMAIL = 5;
    private static final int NUM_COLUMN_HASHED_PASSWORD = 6;

    private SQLiteDatabase mDataBase;

    public DatabaseHelper(Context context) {
        OpenHelper mOpenHelper = new OpenHelper(context);
        mDataBase = mOpenHelper.getWritableDatabase();
    }

    public long insert(String username, String name, String role, String group, String email, String hashed_password) {
        ContentValues cv = new ContentValues();
        cv.put(COLUMN_USERNAME, username);
        cv.put(COLUMN_NAME, name);
        cv.put(COLUMN_ROLE, role);
        cv.put(COLUMN_GROUP, group);
        cv.put(COLUMN_EMAIL, email);
        cv.put(COLUMN_HASHED_PASSWORD, hashed_password);
        return mDataBase.insert(TABLE_NAME, null, cv);
    }

    public int update(Students st) {
        ContentValues cv=new ContentValues();
        cv.put(COLUMN_USERNAME, st.getUsername());
        cv.put(COLUMN_NAME, st.getName());
        cv.put(COLUMN_ROLE, st.getRole());
        cv.put(COLUMN_GROUP, st.getGroups());
        cv.put(COLUMN_EMAIL, st.getEmail());
        cv.put(COLUMN_HASHED_PASSWORD, st.getHashed_password());
        return mDataBase.update(TABLE_NAME, cv, COLUMN_ID + " = ?",new String[] { String.valueOf(st.getId())});
    }

    public void deleteAll() {
        mDataBase.delete(TABLE_NAME, null, null);
    }

    public void delete(long id) {
        mDataBase.delete(TABLE_NAME, COLUMN_ID + " = ?", new String[] { String.valueOf(id) });
    }

    public boolean exist(String username){
        Cursor mCursor = mDataBase.query(TABLE_NAME, null, COLUMN_USERNAME + " = ?", new String[]{String.valueOf(username)}, null, null, null);
        if(mCursor.getCount() > 0 && mCursor.moveToFirst())
            return true;
        else
            return false;
    }

    public Students select(String username) {
        Cursor mCursor = mDataBase.query(TABLE_NAME, null, COLUMN_USERNAME + " = ?", new String[]{String.valueOf(username)}, null, null, null);
        mCursor.moveToFirst();
        long Id = mCursor.getLong(NUM_COLUMN_ID);
        String Username = mCursor.getString(NUM_COLUMN_USERNAME);
        String Name = mCursor.getString(NUM_COLUMN_NAME);
        String Role = mCursor.getString(NUM_COLUMN_ROLE);
        String Group = mCursor.getString(NUM_COLUMN_GROUPS);
        String Email = mCursor.getString(NUM_COLUMN_EMAIL);
        String Password = mCursor.getString(NUM_COLUMN_HASHED_PASSWORD);
        return new Students(Id, Username, Name, Role, Group, Email, Password);
    }

    public ArrayList<Students> selectGr(String group) {
        Cursor mCursor = mDataBase.query(TABLE_NAME, null, COLUMN_GROUP + " = ?", new String[]{String.valueOf(group)}, null, null, null);
        ArrayList<Students> arr = new ArrayList<Students>();
        mCursor.moveToFirst();
        if (!mCursor.isAfterLast()) {
            do {
                long id = mCursor.getLong(NUM_COLUMN_ID);
                String Username = mCursor.getString(NUM_COLUMN_USERNAME);
                String Name = mCursor.getString(NUM_COLUMN_NAME);
                String Role = mCursor.getString(NUM_COLUMN_ROLE);
                String Group = mCursor.getString(NUM_COLUMN_GROUPS);
                String Email = mCursor.getString(NUM_COLUMN_EMAIL);
                String Password = mCursor.getString(NUM_COLUMN_HASHED_PASSWORD);
                arr.add(new Students(id, Username, Name, Role, Group, Email, Password));
            } while (mCursor.moveToNext());
        }
        return arr;
    }

    public ArrayList<Students> selectAll() {
        Cursor mCursor = mDataBase.query(TABLE_NAME, null, null, null, null, null, null);

        ArrayList<Students> arr = new ArrayList<Students>();
        mCursor.moveToFirst();
        if (!mCursor.isAfterLast()) {
            do {
                long id = mCursor.getLong(NUM_COLUMN_ID);
                String Username = mCursor.getString(NUM_COLUMN_USERNAME);
                String Name = mCursor.getString(NUM_COLUMN_NAME);
                String Role = mCursor.getString(NUM_COLUMN_ROLE);
                String Group = mCursor.getString(NUM_COLUMN_GROUPS);
                String Email = mCursor.getString(NUM_COLUMN_EMAIL);
                String Password = mCursor.getString(NUM_COLUMN_HASHED_PASSWORD);
                arr.add(new Students(id, Username, Name, Role, Group, Email, Password));
            } while (mCursor.moveToNext());
        }
        return arr;
    }

    private class OpenHelper extends SQLiteOpenHelper {

        OpenHelper(Context context) {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
        }
        @Override
        public void onCreate(SQLiteDatabase db) {
            String query = "CREATE TABLE " + TABLE_NAME + " (" +
                    COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    COLUMN_USERNAME + " TEXT, " +
                    COLUMN_NAME + " TEXT, " +
                    COLUMN_ROLE + " TEXT,"+
                    COLUMN_GROUP +" TEXT," +
                    COLUMN_EMAIL +" TEXT," +
                    COLUMN_HASHED_PASSWORD +" TEXT);";
            db.execSQL(query);
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            //db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
            //onCreate(db);
        }
    }
}