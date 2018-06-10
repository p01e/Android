package tops.com.barberapp.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.List;

/**
 * Created by Admin on 4/9/2018.
 */

public class MyViewPagerAdapter extends FragmentStatePagerAdapter {

    private List<Fragment> fragments;
    private String[] titles;
    public MyViewPagerAdapter(FragmentManager fm, List<Fragment> fragments, String[] menus) {
        super(fm);
        this.fragments=fragments;
        this.titles=menus;
    }

    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }

    @Override
    public int getCount() {
        return fragments.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return titles[position];
    }
}
