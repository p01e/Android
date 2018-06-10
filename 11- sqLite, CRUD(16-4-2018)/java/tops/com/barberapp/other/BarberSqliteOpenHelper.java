package tops.com.barberapp.other;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Admin on 4/13/2018.
 */

public class BarberSqliteOpenHelper extends SQLiteOpenHelper {



    public BarberSqliteOpenHelper(Context context) {

        super(context, DB.DB_NAME, null, DB.VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(DB.CREATE_UESR);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
