package tops.com.barberapp;

import static tops.com.barberapp.Constants.ADMIN;
import static tops.com.barberapp.Constants.BARBER;
import static tops.com.barberapp.Constants.CLIENT;

/**
 * Created by Admin on 4/2/2018.
 */

public class UtilityHelper {
    public static final String PREF_FILE = "barber_preference";
    public static final String FIRST_LOGIN = "first_login";
    public static final String INTERNAL_FILE = "internal_file";
    public static final String EXTERNAL_FILE = "external_file";

    public static String getTitleFromUser(int user)
    {
        switch (user)
        {
            case ADMIN:
                return "Welcome, Admin";
            case BARBER:
                return "Welcome, Barber";
            case CLIENT:
                return "Welcome, Client";
        }
        return "";
    }
}
