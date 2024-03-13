package com.example.demoth1;

import java.util.List;
import java.util.ArrayList;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteDatabase;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class MyDataBaseHelper extends SQLiteOpenHelper {

    private Context context;
    private static final String DATABASE_NAME = "MyUser.db";
    private static final int DATABASE_VERSION = 1;
    private static final String TABLE_NAME = "my_user";
    private static final String COLUMN_ID = "_id";
    private static final String COLUMN_FIRST_NAME = "first_name";
    private static final String COLUMN_PASSWORD = "password";
    private static final String COLUMN_LAST_NAME = "last_name";
    private static final String COLUMN_EMAIL = "email";

    public MyDataBaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "CREATE TABLE " + TABLE_NAME +
                " (" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_FIRST_NAME + " TEXT, " +
                COLUMN_LAST_NAME + " TEXT, " +
                COLUMN_PASSWORD + " TEXT," +
                COLUMN_EMAIL + " TEXT);";
        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

//    INSERT DATABASE
    public boolean addUser (String firstName, String lastName, String password, String email, boolean isConnect) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(COLUMN_FIRST_NAME, firstName);
        cv.put(COLUMN_LAST_NAME, lastName);
        cv.put(COLUMN_PASSWORD, password);
        cv.put(COLUMN_EMAIL, email);

        long result = db.insert(TABLE_NAME,null, cv);
        if (result == -1) {
            isConnect = false;
            Toast t1 = Toast.makeText(context, "Failed", Toast.LENGTH_SHORT);
            t1.show();
        } else {
            isConnect = true;
            Toast t2 = Toast.makeText(context, "Access Successfully", Toast.LENGTH_SHORT);
            t2.show();
        }
        return isConnect;
    }

//    READ DATABASE
//    use cursor to find and return collections of my query data, a collection including many documents
    Cursor readUser() {
        String query = "SELECT * FROM " + TABLE_NAME;

//        read available databases
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = null;
        if (db != null) {
            cursor = db.rawQuery(query, null);
        }
        return cursor;
    }

//  get all users in database and gán for array list
    public List<User> getAllUsers() {

        String query = "SELECT * FROM " + TABLE_NAME;

        List<User> users = new ArrayList<>();

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(query, null);

//      move to first line
        cursor.moveToFirst();
//      if cursor == last cursor then stop
        while (!cursor.isAfterLast()) {
            int userId = cursor.getInt(0);
            String userPassword = cursor.getString(3);
            String userEmail = cursor.getString(4);

            users.add(new User(userId, userPassword, userEmail));
            cursor.moveToNext();
        }

        cursor.close();

        return users;
    }

//    public User getAllUserById(int ID) {
//
//        String query = "SELECT _id, email, password from " + TABLE_NAME + " where _id = ?";
//
//        User user = null;
//        SQLiteDatabase db = this.getReadableDatabase();
//        Cursor cursor = db.rawQuery(query, new String[]{ID + ""});
//
//        if (cursor.getCount() != 0) {
//            cursor.moveToFirst();
//            int userId = cursor.getInt(0);
//            String userEmail = cursor.getString(4);
//            String userPassword = cursor.getString(3);
//            user = new User(userId, userPassword, userEmail);
//        }
//
//        cursor.close();
//        return user;
//
//    }

//  check email and password have been existsed in database when you login
    Cursor getUserByEmail (String strLoginEmail, String strLoginPassword) {
//        String query = "SELECT _id, email, password from " + TABLE_NAME + " where email = '" + strLoginEmail + "' and password = '" + strLoginPassword + "'";
//
//        SQLiteDatabase db = this.getReadableDatabase();
//        Cursor cursor = db.rawQuery(query, null);

         SQLiteDatabase db = this.getReadableDatabase();
         String query = "SELECT * FROM " + TABLE_NAME + " WHERE email = ? AND password = ?";
         String[] selectionArgs = {strLoginEmail, strLoginPassword};

         Cursor cursor = db.rawQuery(query, selectionArgs);

        return cursor;
    }

//    check email when you register
    public boolean checkRegisterEmail(String email) {

        String query = "SELECT * FROM " + TABLE_NAME + " WHERE email = '" + email + "'";

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(query, null);

        if (cursor.getCount() != 0) {
            return false;
        }

        return true;
    }
}
