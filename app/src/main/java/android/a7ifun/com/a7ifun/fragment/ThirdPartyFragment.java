package android.a7ifun.com.a7ifun.fragment;

import android.a7ifun.com.a7ifun.R;
import android.a7ifun.com.a7ifun.adapter.MyMovieAdapter;
import android.a7ifun.com.a7ifun.okhttp.base.BaseFragment;
import android.graphics.Color;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.lang.ref.SoftReference;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by 7yan on 2017/1/7.
 */

public class ThirdPartyFragment extends BaseFragment {
    private static final String TAG = ThirdPartyFragment.class.getSimpleName();//"CommonframeFragment"
    private ListView iv_movie_list;
    private TextView tv_nodata;
    private ProgressBar pb_loading;
    private ArrayList<Map<String,String>> list;
    //private ArrayList<Map> list;
    @Override
    protected View initView()
    {
        Log.e(TAG,"第三方Fragment页面被初始化了....");
        //自己定义一个电影list表
        /*textView = new TextView(mContent);
        textView.setTextSize(20);
        textView.setGravity(Gravity.CENTER);
        textView.setTextColor(Color.RED);
        return textView;*/
        //创建自己的布局
        View movieView =View.inflate(mContent, R.layout.item_movie_container,null);
        //实例化控件
        iv_movie_list = (ListView) movieView.findViewById(R.id.iv_movie_list);
        tv_nodata = (TextView) movieView.findViewById(R.id.tv_nodata);
        pb_loading = (ProgressBar) movieView.findViewById(R.id.pb_loading);
        return movieView;
    }

    @Override
    protected void initData()
    {
        super.initData();
        Log.e(TAG,"第三方Fragment数据被初始化了....");
       // textView.setText("第三方页面");
        //必须实例化list
        //list = new ArrayList<>();
        list = new ArrayList<Map<String, String>>();

        //1:准备固定的数据
        for(int i=0;i < 10;i++)
        {
            //Map item = new HashMap();
            Map item = new HashMap<String,String>();
            item.put("name","电影"+i);
            item.put("actor","主演"+i);
            item.put("time","2010-10-0"+i);
            list.add(item);

        }
        //2:网络获取数据

        //创建适配器
        MyMovieAdapter adapter = new MyMovieAdapter(mContent, list);
        //绑定适配器
        iv_movie_list.setAdapter(adapter);
    }
}
