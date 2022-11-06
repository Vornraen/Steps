package com.example.myapplication;

import android.content.ContentValues;
import android.content.Context;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class DBHelper extends SQLiteOpenHelper {

    private Context context;
    private static final String DBNAME = "Walkorie.db";
    private static final int VERSION = 1;

    public DBHelper(Context context) {
        super(context, DBNAME, null, VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("CREATE TABLE Users(username TEXT not null primary key, password TEXT not null, " +
                "email TEXT not null)");

        sqLiteDatabase.execSQL("CREATE TABLE UsersEat(userEatId INTEGER primary key not null, " +
                "username TEXT not null,foodName TEXT not null, dose INTEGER not null, " +
                "dateTime TEXT not null, foreign key(username) references" +
                " Users(username))");

        sqLiteDatabase.execSQL("CREATE TABLE Food(foodId INTEGER primary key not null," +
                " caloriesPerKG INTEGER not null, foodName TEXT not null)");

        sqLiteDatabase.execSQL("CREATE TABLE UsersWork(userWorkId INTEGER primary key not null," +
                " username TEXT not null, workName TEXT not null, workDuration INTEGER not null, " +
                "dateTime TEXT not null, " +
                "foreign key(usename) references Users(username))");

        sqLiteDatabase.execSQL("CREATE TABLE Work(workId INTEGER primary key not null," +
                " caloriesPerHours INTEGER not null, " +
                "workName TEXT not null)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS Users");
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS UsersEat");
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS Food");
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS UsersWork");
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS Work");

    }

    public void testInsertUser(String username, String password, String email){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put("username", username);
        cv.put("password", password);
        cv.put("email", email);
        long result = db.insert("Users", null, cv);
        if(result==-1){
            System.out.println("fail");
        }
        else{
            System.out.println("success");
        }


    }
}
