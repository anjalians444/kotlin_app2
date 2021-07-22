 package com.example.task_app2

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper 

class SQliteHelper(context : Context) :
    SQLiteOpenHelper(context ,DATABASE_NAME,null, DATABASE_VERSION) {

    companion object{
      private  const val TABLE_USERS = "users"
      private  const val COLUMN_ID = "id"
      private  const val COLUMN_NAME = "name"
       private const val COLUMN_MOBILENO = "mobile_no"


    private const val DATABASE_NAME = "users.db"
       private const val DATABASE_VERSION = 1
    }

    override fun onCreate(db: SQLiteDatabase?) {

        // Database creation sql statement
        val DATABASE_CREATE = ("CREATE TABLE " +
               TABLE_USERS + "(" + COLUMN_ID + " INTEGER PRIMARY KEY, " +
                COLUMN_NAME + " Text," + COLUMN_MOBILENO +
                " Text" + ")")
        db?.execSQL(DATABASE_CREATE)


    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {

        //if (oldVersion > 1) {
        db!!.execSQL(" DROP TABLE IF EXISTS $TABLE_USERS")
        onCreate(db)
    }


    fun insertdata(userModel: UserModel) :Long{
        val db = this.writableDatabase

        val cv = ContentValues()
        cv.put(COLUMN_ID,userModel.id)
        cv.put(COLUMN_NAME,userModel.name)
        cv.put(COLUMN_MOBILENO,userModel.mobile)
         val success = db.insert(TABLE_USERS,null,cv)
        db.close()
        return success
    }

    fun getAllusers() : ArrayList<UserModel> {
        val userlist : ArrayList<UserModel> = ArrayList()

        val selectquery = "SELECT * FROM $TABLE_USERS"
        val db = this.readableDatabase

        val cursor : Cursor?
        try {
            cursor = db.rawQuery(selectquery,null)
        }catch (e: Exception){
            e.printStackTrace()
            db.execSQL(selectquery)
            return ArrayList()
        }

       var  id : Int
       var username : String
       var mobileno : String

     if (cursor.moveToFirst()){
    do{
        id = cursor.getInt(cursor.getColumnIndex("id"))
        username = cursor.getString(cursor.getColumnIndex("name"))
        mobileno = cursor.getString(cursor.getColumnIndex("mobile_no"))

        val user = UserModel(id = id, name = username , mobile = mobileno)
        userlist.add(user)
    }while (cursor.moveToNext())
}
        return  userlist
    }
}