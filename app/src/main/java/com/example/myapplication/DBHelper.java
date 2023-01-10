package com.example.myapplication;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

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

            sqLiteDatabase.execSQL("CREATE TABLE UsersEat(userEatId INTEGER primary key AUTOINCREMENT not null, " +
                    "username TEXT not null,foodName TEXT not null, dose INTEGER not null, calories INTEGER not null, " +
                    "dateTime TEXT not null, foreign key(username) references" +
                    " Users(username))");

        sqLiteDatabase.execSQL("SELECT * FROM UsersEat");

        sqLiteDatabase.execSQL("CREATE TABLE Food(foodId INTEGER primary key AUTOINCREMENT not null," +
                " caloriesPerKG INTEGER not null, foodName TEXT not null)");

        sqLiteDatabase.execSQL("INSERT INTO Food (foodId, caloriesPerKG, foodName) VALUES \n" +
                "(1, 52, 'Apple'), \n" +
                "(2, 89, 'Banana'), \n" +
                "(3, 41, 'Carrots'), \n" +
                "(4, 342, 'Chicken'), \n" +
                "(5, 250, 'Beef'), \n" +
                "(6, 210, 'Salmon'), \n" +
                "(7, 365, 'Brown rice'), \n" +
                "(8, 368, 'Quinoa'), \n" +
                "(9, 77, 'Potatoes'), \n" +
                "(10, 18, 'Tomatoes'), \n" +
                "(11, 34, 'Broccoli'), \n" +
                "(12, 23, 'Spinach'), \n" +
                "(13, 49, 'Kale'), \n" +
                "(14, 59, 'Greek yogurt'), \n" +
                "(15, 98, 'Cottage cheese'), \n" +
                "(16, 63, 'Milk'), \n" +
                "(17, 265, 'Bread'), \n" +
                "(18, 131, 'Pasta'), \n" +
                "(19, 588, 'Peanut butter'), \n" +
                "(20, 546, 'Dark chocolate');");

        sqLiteDatabase.execSQL("CREATE TABLE UsersWork(userWorkId INTEGER primary key AUTOINCREMENT not null," +
                " username TEXT not null, workName TEXT not null, workDuration INTEGER not null, " +
                " dateTime TEXT not null, calories INTEGER not null, " +
                " foreign key(username) references Users(username))");

        sqLiteDatabase.execSQL("CREATE TABLE Work(workId INTEGER primary key AUTOINCREMENT not null," +
                " caloriesPerHours INTEGER not null, " +
                " workName TEXT not null)");

        sqLiteDatabase.execSQL("INSERT INTO Work (workId, caloriesPerHours, workName) VALUES \n" +
                "(1, 60, 'Jogging'), \n" +
                "(2, 400, 'Boxing'), \n" +
                "(3, 240, 'Swimming'), \n" +
                "(4, 300, 'Bicycling'), \n" +
                "(5, 350, 'Tennis'), \n" +
                "(6, 200, 'Yoga'), \n" +
                "(7, 280, 'Hiking'), \n" +
                "(8, 330, 'Dancing'), \n" +
                "(9, 400, 'Jump rope'), \n" +
                "(10, 240, 'Kickboxing'), \n" +
                "(11, 300, 'Rowing'), \n" +
                "(12, 350, 'Pilates'), \n" +
                "(13, 200, 'Elliptical'), \n" +
                "(14, 280, 'Weight lifting'), \n" +
                "(15, 330, 'Skiing'), \n" +
                "(16, 400, 'Rock climbing'), \n" +
                "(17, 240, 'MMA training'), \n" +
                "(18, 300, 'Stair climbing'), \n" +
                "(19, 350, 'Surfing'), \n" +
                "(20, 200, 'Snowboarding');");

        sqLiteDatabase.execSQL("INSERT INTO UsersEat (userEatId, username, foodName, dose, calories, dateTime)\n" +
                "VALUES (1, 'john.doe', 'Apple', 1000, 52, '2022-01-01 12:00:00'),\n" +
                "       (2, 'john.doe', 'Banana', 101, 89, '2022-01-01 12:30:00'),\n" +
                "       (3, 'john.doe', 'Milk', 250, 63, '2022-01-01 13:00:00'),\n" +
                "       (4, 'john.doe', 'Tomatoes', 14, 18, '2022-01-01 14:00:00'),\n" +
                "       (5, 'john.doe', 'Pasta', 1000, 131, '2022-01-01 15:00:00');");

        sqLiteDatabase.execSQL("INSERT INTO UsersWork(userWorkId, username, workName, workDuration," +
                "dateTime, calories) VALUES (1, 'john.doe', 'Running', 30, '2022-06-22 12:00:00', 500),\n" +
                "(2, 'john.doe', 'Weight lifting', 45, '2022-06-22 13:00:00', 400),\n" +
                "(3, 'john.doe', 'Yoga', 60, '2022-06-22 14:00:00', 300),\n" +
                "(4, 'john.doe', 'Swimming', 30, '2022-06-22 15:00:00', 350),\n" +
                "(5, 'john.doe', 'Biking', 45, '2022-06-23 10:00:00', 450)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS Users");
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS UsersEat");
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS Food");
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS UsersWork");
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS Work");

    }

    public boolean insertUser(String username, String password, String email){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put("username", username);
        cv.put("password", password);
        cv.put("email", email);
        long result = db.insert("Users", null, cv);
        if(result==-1){
            return false;
        }
        else{
            return true;
        }


    }

    public boolean usernameExists(String username){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM Users where username = ?",
                new String[]{username});
        if(cursor.getCount()>0)
            return true;
        return false;
    }

    public boolean checkLogin(String username, String password){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM Users WHERE " +
                "username=? AND password=?", new String[]{username, password});

        if(cursor.getCount()==1)
            return true;
        return false;
    }

    public int calorieCounter(String username){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT SUM(calories) FROM UsersEat WHERE username=?", new String[]{username});

        cursor.moveToFirst();
        int foodCalories = cursor.getInt(0);
        cursor = db.rawQuery("SELECT SUM(calories) FROM UsersWork WHERE username=?", new String[]{username});

        cursor.moveToFirst();
        int workCalories = cursor.getInt(0);

       return foodCalories- workCalories;
    }

    public List<String> foodList(String username) {
        List<String> foodList = new ArrayList<>();

        String query = "SELECT foodName, dose, ' + ' || calories || ' calories' AS calories " +
                "FROM UsersEat " +
                "WHERE username = ?";

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, new String[]{username});

        if (cursor.moveToFirst()) {
            do {
                String foodName = cursor.getString(0);
                int dose = cursor.getInt(1);
                String calories = cursor.getString(2);

                String food = foodName + " - " + dose + "" + calories;
                foodList.add(food);
            } while (cursor.moveToNext());
        }
        cursor.close();

        return foodList;
    }

    public List<String> activityList(String username) {
        List<String> activityList = new ArrayList<>();

        String query = "SELECT workName, workDuration, ' + ' || calories || ' calories' AS calories " +
                "FROM UsersWork " +
                "WHERE username = ?";

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, new String[]{username});

        if (cursor.moveToFirst()) {
            do {
                String workName = cursor.getString(0);
                int workDuration = cursor.getInt(1);
                String calories = cursor.getString(2);

                String work = workName + " - " + workDuration + "" + calories;
                activityList.add(work);
            } while (cursor.moveToNext());
        }
        cursor.close();

        return activityList;
    }

    public ArrayList<String> getFoodList(){

        ArrayList<String> foodl = new ArrayList<>();
        String query = "SELECT foodName FROM Food";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, new String[]{});
        if (cursor.moveToFirst()){
            do {
                String food = cursor.getString(0);
                foodl.add(food);
            } while (cursor.moveToNext());
        }
        cursor.close();

        return foodl;
    }

    public void addFood(String username, String foodName, String dose) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        // First, look up the calories per kilogram for the food
        String query = "SELECT caloriesPerKG FROM Food WHERE foodName = ?";
        Cursor cursor = sqLiteDatabase.rawQuery(query, new String[] { foodName });
        int caloriesPerKG = 0;
        if (cursor.moveToFirst()) {
            caloriesPerKG = cursor.getInt(0);
        }
        cursor.close();

        // Calculate the number of calories based on the dose
        int calories = (int) (caloriesPerKG * Double.parseDouble(dose)/1000);

        // Insert the new row into the UsersEat table
        ContentValues values = new ContentValues();
        values.put("username", username);
        values.put("foodName", foodName);
        values.put("dose", dose);
        values.put("calories", calories);
        values.put("dateTime", getCurrentDateTime());
        sqLiteDatabase.insert("UsersEat", null, values);
    }

    private String getCurrentDateTime() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault());
        return sdf.format(new Date());
    }

    public ArrayList<String> getWorkList(){

        ArrayList<String> workL = new ArrayList<>();
        String query = "SELECT workName FROM Work";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, new String[]{});
        if (cursor.moveToFirst()){
            do {
                String work = cursor.getString(0);
                workL.add(work);
            } while (cursor.moveToNext());
        }
        cursor.close();

        return workL;
    }


    public void addWork(String username, String workName, String timeSpentDoingWork) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        // First, look up the calories burned per hour for the work
        String query = "SELECT caloriesPerHours FROM Work WHERE workName = ?";
        Cursor cursor = sqLiteDatabase.rawQuery(query, new String[] { workName });
        int caloriesBurnedPerHour = 0;
        if (cursor.moveToFirst()) {
            caloriesBurnedPerHour = cursor.getInt(0);
        }
        cursor.close();

        // Calculate the number of calories burned based on the time spent doing the work
        int calories = (int) (caloriesBurnedPerHour * Double.parseDouble(timeSpentDoingWork))/60;

        // Insert the new row into the UsersWork table
        ContentValues values = new ContentValues();
        values.put("username", username);
        values.put("workName", workName);
        values.put("workDuration", timeSpentDoingWork);
        values.put("calories", calories);
        values.put("dateTime", getCurrentDateTime());
        sqLiteDatabase.insert("UsersWork", null, values);
    }



}
