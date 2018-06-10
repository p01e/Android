package tops.com.barberapp;

import android.app.Application;

import io.realm.Realm;

/**
 * Created by Admin on 4/18/2018.
 */

public class BarberApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Realm.init(this);
    }
}
