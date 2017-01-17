package hui.a7ifun.com.a7ifun.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;

import hui.a7ifun.com.a7ifun.fragment.MovieFragment;
import hui.a7ifun.com.a7ifun.okhttp.base.BaseFragment;

/**
 * Created by 7Yan on 2017/1/17.
 */

public class MyMovieViewPageAdapter extends FragmentPagerAdapter
{


    private final ArrayList<MovieFragment> fragments;

    public MyMovieViewPageAdapter(FragmentManager fm, ArrayList<MovieFragment> fragments) {
        super(fm);
        this.fragments = fragments;
    }

    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }

    @Override
    public int getCount() {
        return fragments.size();
    }
    //得到页面标题
    @Override
    public CharSequence getPageTitle(int position) {
        return fragments.get(position).getTitle();
    }
}
