package tops.com.barberapp.activities;


import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Environment;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

import tops.com.barberapp.R;
import tops.com.barberapp.UtilityHelper;
import tops.com.barberapp.fragments.AdminHomeFragment;
import tops.com.barberapp.fragments.BarberListFragment;
import tops.com.barberapp.fragments.ClientListFragment;

import static tops.com.barberapp.Constants.USER;

public class AdminActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);
        Toolbar toolbar =  findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Intent intent=getIntent();
        int user=intent.getIntExtra(USER,0);
        setTitle(UtilityHelper.getTitleFromUser(user));

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        fab.setVisibility(View.GONE);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        Fragment fragment=new AdminHomeFragment();
        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.frm_admin_layout,fragment)
                .commit();
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
       // getMenuInflater().inflate(R.menu.test,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId())
        {
            case R.id.action_test:
                Toast.makeText(this, "Test", Toast.LENGTH_SHORT).show();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        Fragment fragment=null;
        switch (id){
            case R.id.nav_barber:
                fragment=new BarberListFragment();
                break;
            case R.id.nav_client:
                fragment=new ClientListFragment();
                break;
            case R.id.nav_report:
                break;
            case R.id.nav_share:
                writeCountInternalStorage();
                break;
            case R.id.nav_logout:
                SharedPreferences sharedPrefs=getSharedPreferences(UtilityHelper.PREF_FILE,MODE_PRIVATE);
                SharedPreferences.Editor editor=sharedPrefs.edit();
                editor.remove(UtilityHelper.FIRST_LOGIN);
                editor.commit();
                finish();
                break;
        }

        if(fragment!=null)
        {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.frm_admin_layout,fragment)
                    .addToBackStack(AdminHomeFragment.class.getName())
                    .commit();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void writeCountInternalStorage() {
        /*int countInt=readInternalFile();
        String count=countInt+"";
        try {
            FileOutputStream fos=openFileOutput(UtilityHelper.INTERNAL_FILE,MODE_PRIVATE);
            fos.write(count.getBytes());
            fos.close();
            Toast.makeText(this, "Success : "+count, Toast.LENGTH_SHORT).show();
        } catch (java.io.IOException e) {
            e.printStackTrace();
        }*/
        File file=Environment.getExternalStorageDirectory();
        file=new File(file,"Barber");
        if(!file.exists())
        {
            if(file.mkdir())
                Toast.makeText(this, "Directory Created", Toast.LENGTH_SHORT).show();
            else
                Toast.makeText(this, "Directory Create Failed", Toast.LENGTH_SHORT).show();
        }
        file=new File(file,UtilityHelper.EXTERNAL_FILE);
        String count="1";
        try {
            FileOutputStream fos=new FileOutputStream(file);
            fos.write(count.getBytes());
            fos.close();
            Toast.makeText(this, "Success : "+count, Toast.LENGTH_SHORT).show();
        } catch (java.io.IOException e) {
            e.printStackTrace();
        }
    }

    private int readInternalFile() {
        try {
            FileInputStream fis=openFileInput(UtilityHelper.INTERNAL_FILE);
            byte[] bytes=new byte[fis.available()];
            fis.read(bytes);
            fis.close();
            String count=new String(bytes);
            int countInt=Integer.parseInt(count);
            countInt++;
            return countInt;
        } catch (java.io.IOException e) {
            e.printStackTrace();
        }
return 0;
    }
}
