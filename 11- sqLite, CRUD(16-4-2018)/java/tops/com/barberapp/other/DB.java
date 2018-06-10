package tops.com.barberapp.other;

/**
 * Created by Admin on 4/13/2018.
 */

public interface DB {
    String DB_NAME="BARBERAPP";
    int VERSION=1;

    String TABLE_USER="User";
    String COL_ID="id";
    String COL_FIRST_NAME="first_name";
    String COL_LAST_NAME="last_name";
    String COL_EMAIL="email";
    String COL_MOBILE="mobile";
    String COL_AGE="age";
    String COL_GENDER="gender";

    String CREATE_UESR="create table "+TABLE_USER+" ("+COL_ID+" integer primary key autoincrement, "
            + COL_FIRST_NAME+" text, "+COL_LAST_NAME+" text, "+COL_EMAIL+" text, "
            + COL_MOBILE+" text, "+COL_AGE+" integer, "+COL_GENDER+" text)";
}
