package hui.a7ifun.com.a7ifun.tablayout.adapter;

import hui.a7ifun.com.a7ifun.tablayout.fragment.MyFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;

/**
 * Created by 7yan on 2017/1/10.
 */

public class ViewPageAdapter extends FragmentPagerAdapter {


    private final ArrayList<MyFragment> fragments;

    public ViewPageAdapter(FragmentManager fm, ArrayList<MyFragment> fragments)
    {
        super(fm);
        this.fragments = fragments;
    }

    /**
     * 根据位置返回 fragment
     * @param position
     * @return
     */
    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }

    @Override
    public int getCount() {
        return fragments.size();
    }

    /**
     * 得到页面的标题
     * @param position
     * @return
     */
    @Override
    public CharSequence getPageTitle(int position) {
        return fragments.get(position).getTitle();
    }
}
