package hui.a7ifun.com.a7ifun.tablayout.adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TableLayout;
import android.widget.TextView;

import java.util.List;

import hui.a7ifun.com.a7ifun.R;
import hui.a7ifun.com.a7ifun.tablayout.fragment.MyFragment;

/**
 * Created by 7Yan on 2017/1/18.
 */

public class MyCTabLayoutAdapter extends FragmentPagerAdapter {
    private final Context mContext;
    private final List<MyFragment> list;
    private final String[] titles;
    private final int[] images;

    public MyCTabLayoutAdapter(FragmentManager fm, Context context, List<MyFragment> list,String[] titles,int[] images)
    {
        super(fm);
        this.mContext = context;
        this.list =list;
        this.titles =titles;
        this.images=images;
    }

    @Override
    public Fragment getItem(int position) {
        return list.get(position);
    }

    @Override
    public int getCount() {
        return list.size();
    }

    /**
     * 自定义方法，提供自定义Tab
     * @param position 位置
     * @return 返回Tab的View
     */
    public View getTabView(int position)
    {
        //View view = LayoutInflater.from(mContext).inflate(R.layout.tab_custom,null);
        View view =View.inflate(mContext, R.layout.tab_custom,null);
        TextView textView = (TextView) view.findViewById(R.id.tv_title);
        ImageView imageView = (ImageView) view.findViewById(R.id.iv_icon);
        textView.setText(titles[position]);
        imageView.setImageResource(images[position]);
        //添加一行，设置颜色
        //textView.setTextColor();
        return view;
    }
}
