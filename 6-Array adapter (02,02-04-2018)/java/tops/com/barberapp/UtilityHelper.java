package tops.com.barberapp;

import static tops.com.barberapp.Constants.ADMIN;
import static tops.com.barberapp.Constants.BARBER;
import static tops.com.barberapp.Constants.CLIENT;

/**
 * Created by Admin on 4/2/2018.
 */

public class UtilityHelper {
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
