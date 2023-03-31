package com.example.shsinfosystem;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class DBHelper extends SQLiteOpenHelper {
    public final String userTbl = "userTbl";
    public final String studentTbl= "studentTbl";

    public DBHelper(Context context){
        super(context, "dbName.db", null, 1);
    }
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        sqLiteDatabase.execSQL("CREATE TABLE " +studentTbl + "(id INTEGER PRIMARY KEY AUTOINCREMENT, first_name TEXT, " +
                "middle_initial TEXT, last_name TEXT, year_level INTEGER, strand TEXT, mobile_number TEXT, gender TEXT, age INTEGER, address TEXT, student_number INTEGER," +
                " section_number INTEGER, enrolled INTEGER);");
        sqLiteDatabase.execSQL("CREATE TABLE " +userTbl + "(id INTEGER PRIMARY KEY AUTOINCREMENT, student_id INTEGER, account_number INTEGER, password TEXT, user_type INTEGER" +
                ", FOREIGN KEY(student_id) REFERENCES studentTbl(id) ON DELETE CASCADE ON UPDATE CASCADE);");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS userTbl;");
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS studentTbl;");
    }

    public boolean registerStudent(String fName, String mInitial, String lName, int yearLevel, String strand, String mobileNum, String gender, int age, String address){

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("first_name", fName);
        contentValues.put("middle_initial", mInitial);
        contentValues.put("last_name", lName);
        contentValues.put("year_level", yearLevel);
        contentValues.put("strand", strand);
        contentValues.put("mobile_number", mobileNum);
        contentValues.put("gender", gender);
        contentValues.put("age", age);
        contentValues.put("address", address);
        contentValues.put("enrolled", 0);
        long result = db.insert("studentTbl", null, contentValues);

        return result > -1;
    }

    public int generateAccountNumber(int studentId){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT id FROM studentTbl WHERE id="+studentId+";", null);
        String accNum = "";
        while(cursor.moveToNext()){
            accNum = "02000" + String.valueOf(cursor.getInt(0)) ;
        }

        return Integer.parseInt(accNum);
    }
    public String generateCode(){
        String letters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        StringBuilder str = new StringBuilder(5);
        Random r = new Random();

        for(int i = 0; i < 3; i++){
            int index = r.nextInt(letters.length());
            str.append(letters.charAt(index));
        }
        int low = 10;
        int high = 99;
        int result = r.nextInt(high-low) + low;
        str.append(result);
        return str.toString();
    }
    public boolean enrollStudent(int studentId, int accnum, String password){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("student_number", accnum);
        cv.put("enrolled", 1);
        db.update("studentTbl", cv, "id=?", new String[]{String.valueOf(studentId)});

        ContentValues contentValues = new ContentValues();
        contentValues.put("student_id", studentId);
        contentValues.put("account_number", accnum);
        contentValues.put("password", password);
        contentValues.put("user_type", 3);
        long result = db.insert("userTbl", null, contentValues);

        return result > -1;
    }
    public StudentItem getStudentInfo(int studentId){
        SQLiteDatabase db = this.getWritableDatabase();

        Cursor cursor = db.rawQuery("SELECT * FROM studentTbl WHERE id='"+studentId+"';", null);
        StudentItem item = new StudentItem();
        while(cursor.moveToNext()){
            item = new StudentItem(cursor.getString(1), cursor.getString(2), cursor.getString(3), cursor.getString(5), cursor.getString(7), cursor.getString(9),
                    cursor.getInt(4), cursor.getString(6), cursor.getInt(8), cursor.getInt(10));
        }

        return item;

    }
    public boolean updateUser(int studentId, String password){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("password", password);
        long result = db.update("userTbl", contentValues, "student_id=?", new String[]{String.valueOf(studentId)});

        return result > -1;
    }

    public int getUserId(String accountNumber, String password){

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT id FROM userTbl WHERE account_number='"+accountNumber+"' AND password='"+password+"';", null);

        int userId = -1;
        while(cursor.moveToNext()){
            userId = cursor.getInt(0);
        }

        return userId;
    }

    public boolean updateStudent(int studentId, String fName, String mInitial, String lName, int yearLevel, String strand, String mobileNum, String gender, int age, String address){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put("first_name", fName);
        contentValues.put("middle_initial", mInitial);
        contentValues.put("last_name", lName);
        contentValues.put("year_level", yearLevel);
        contentValues.put("strand", strand);
        contentValues.put("mobile_number", mobileNum);
        contentValues.put("gender", gender);
        contentValues.put("age", age);
        contentValues.put("address", address);

        long result = db.update("studentTbl", contentValues, "id=?", new String[]{String.valueOf(studentId)});

        return result > -1;
    }

    public List<Item> getUnenrolledStudents(){

        SQLiteDatabase db = this.getWritableDatabase();

        List<Item> item = new ArrayList<>();
        Cursor cursor = db.rawQuery("SELECT * FROM studentTbl WHERE enrolled=0", null);
        while(cursor.moveToNext()){

            String fullname = cursor.getString(1)+ " " + cursor.getString(2) + " " + cursor.getString(3);
            item.add(new Item(fullname, cursor.getInt(4), cursor.getInt(10), R.drawable.user, cursor.getInt(0)));
        }

        return item;
    }
    public List<Item> getEnrolledStudents(){

        SQLiteDatabase db = this.getWritableDatabase();

        List<Item> item = new ArrayList<>();
        Cursor cursor = db.rawQuery("SELECT * FROM studentTbl WHERE enrolled=1", null);
        while(cursor.moveToNext()){

            String fullname = cursor.getString(1)+ " " + cursor.getString(2) + " " + cursor.getString(3);
            item.add(new Item(fullname, cursor.getInt(4), cursor.getInt(10), R.drawable.user, cursor.getInt(0)));
        }

        return item;
    }

}
