package tops.com.barberapp.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

import tops.com.barberapp.model.User;
import tops.com.barberapp.other.BarberSqliteOpenHelper;
import tops.com.barberapp.other.DB;

/**
 * Created by Admin on 4/13/2018.
 */

public class UserDao {

    SQLiteDatabase db;

    public UserDao(Context context){
        BarberSqliteOpenHelper sqliteOpenHelper=new BarberSqliteOpenHelper(context);
        db=sqliteOpenHelper.getWritableDatabase();
    }

    public long saveUser(User user){
        ContentValues values=new ContentValues();
        values.put(DB.COL_FIRST_NAME,user.getFirstName());
        values.put(DB.COL_LAST_NAME,user.getLastName());
        values.put(DB.COL_EMAIL,user.getEmail());
        values.put(DB.COL_MOBILE,user.getMobile());
        values.put(DB.COL_GENDER,user.getGender());
        values.put(DB.COL_AGE,user.getAge());
        long id=db.insert(DB.TABLE_USER,null,values);
        return id;
    }

    public int updateUser(User user){
        ContentValues values=new ContentValues();
        values.put(DB.COL_FIRST_NAME,user.getFirstName());
        values.put(DB.COL_LAST_NAME,user.getLastName());
        values.put(DB.COL_EMAIL,user.getEmail());
        values.put(DB.COL_MOBILE,user.getMobile());
        values.put(DB.COL_GENDER,user.getGender());
        values.put(DB.COL_AGE,user.getAge());
        String whereClause= DB.COL_ID+"=?";
        String[] whereArgs={String.valueOf(user.getId())};
        int count=db.update(DB.TABLE_USER,values,whereClause,whereArgs);
        return count;
    }

    public int deleteUser(int id){
        String whereClause= DB.COL_ID+"=?";
        String[] whereArgs={String.valueOf(id)};
        int count=db.delete(DB.TABLE_USER,whereClause,whereArgs);
        return count;
    }

    public ArrayList<User> getUsers(){
        String[] columns={
                DB.COL_ID,DB.COL_FIRST_NAME,DB.COL_LAST_NAME,DB.COL_EMAIL,DB.COL_MOBILE,DB.COL_GENDER,DB.COL_AGE
        };
        String selection=null;
        String[] selectionArgs=null;
        String groupBy=null;
        String having=null;
        String orderBy=null;
        Cursor cursor=db.query(DB.TABLE_USER,columns,selection,selectionArgs,groupBy,having,orderBy);
        ArrayList<User> users=new ArrayList<>();
        if(cursor.moveToFirst())
        {
            do{
                User user=new User();
                user.setId(cursor.getInt(cursor.getColumnIndex(DB.COL_ID)));
                user.setFirstName(cursor.getString(cursor.getColumnIndex(DB.COL_FIRST_NAME)));
                user.setLastName(cursor.getString(cursor.getColumnIndex(DB.COL_LAST_NAME)));
                user.setEmail(cursor.getString(cursor.getColumnIndex(DB.COL_EMAIL)));
                user.setMobile(cursor.getString(cursor.getColumnIndex(DB.COL_MOBILE)));
                user.setAge(cursor.getInt(cursor.getColumnIndex(DB.COL_AGE)));
                user.setGender(cursor.getString(cursor.getColumnIndex(DB.COL_GENDER)));
                users.add(user);
            }while (cursor.moveToNext());
        }
        return users;
    }
}

