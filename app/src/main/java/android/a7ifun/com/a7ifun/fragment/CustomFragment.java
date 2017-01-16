package android.a7ifun.com.a7ifun.fragment;

import android.a7ifun.com.a7ifun.R;
import android.a7ifun.com.a7ifun.adapter.MyMovieAdapter;
import android.a7ifun.com.a7ifun.adapter.lv_adapter;
import android.a7ifun.com.a7ifun.okhttp.base.BaseFragment;
import android.graphics.Color;
import android.text.format.DateUtils;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.handmark.pulltorefresh.library.ILoadingLayout;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;

import java.util.ArrayList;
import java.util.List;

import static android.a7ifun.com.a7ifun.R.id.iv_movie_list;
import static android.a7ifun.com.a7ifun.R.id.pb_loading;
import static android.a7ifun.com.a7ifun.R.id.start;
import static android.a7ifun.com.a7ifun.R.id.tv_nodata;
import static android.a7ifun.com.a7ifun.R.id.tv_title;

/**
 * Created by 7yan on 2017/1/7.
 */

public class CustomFragment extends BaseFragment {
    private static final String TAG = CustomFragment.class.getSimpleName();//"CommonframeFragment"
    private TextView tv_title;
    private PullToRefreshListView mPullToRefreshListView;
    private ListView lv;
    int countA = 0;
    int countB = 0;
    private lv_adapter myAdapter;
    private List<String> datas;

    @Override
    protected View initView()
    {
        Log.e(TAG,"自定义Fragment页面被初始化了....");
       /* textView = new TextView(mContent);
        textView.setTextSize(20);
        textView.setGravity(Gravity.CENTER);
        textView.setTextColor(Color.RED);
        return textView;*/
        //创建自己的布局
        View movieView =View.inflate(mContent, R.layout.item_movie_ptr_container,null);
        //实例化控件tv_title =movieView.findViewById(R.id.tv_title);
//        实例化下拉刷新类
        mPullToRefreshListView = (PullToRefreshListView) movieView.findViewById(R.id.pull_to_refresh);
//        得到ListView对象
        lv = mPullToRefreshListView.getRefreshableView();
        return movieView;
    }

    @Override
    protected void initData()
    {
        super.initData();
        Log.e(TAG,"自定义Fragment数据被初始化了....");
//      准备数据源,进入页面首次加载数据
        datas = new ArrayList<String>();
        initUpLv();
//      创建适配器
        myAdapter = new lv_adapter(mContent,datas);
//      绑定适配器
        lv.setAdapter(myAdapter);
//      调用下拉刷新
        initRefresh();
    }
    private void initRefresh()
    {
        /*
         * Mode.BOTH：同时支持上拉下拉
         * Mode.PULL_FROM_START：只支持下拉Pulling Down
         * Mode.PULL_FROM_END：只支持上拉Pulling Up
         * Mode.DISABLED：禁用下拉刷新和上拉加载
         * Mode.MANUAL_REFRESH_ONLY：只允许手动触发
         */
        /*
         * 如果Mode设置成Mode.BOTH，需要设置刷新Listener为OnRefreshListener2，并实现onPullDownToRefresh()、onPullUpToRefresh()两个方法。
         * 如果Mode设置成Mode.PULL_FROM_START或Mode.PULL_FROM_END，需要设置刷新Listener为OnRefreshListener，同时实现onRefresh()方法。
         * 当然也可以设置为OnRefreshListener2，但是Mode.PULL_FROM_START的时候只调用onPullDownToRefresh()方法，
         * Mode.PULL_FROM_END的时候只调用onPullUpToRefresh()方法.
         * 我一般用OnRefreshListener2，因为分工比较清楚
         */
//        设置同时支持上拉和下拉
        mPullToRefreshListView.setMode(PullToRefreshBase.Mode.BOTH);
        mPullToRefreshListView.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<ListView>()
        {
//            下拉刷新
            @Override
            public void onPullDownToRefresh(PullToRefreshBase<ListView> refreshView)
            {
                //获取上次刷新时间
                String str = DateUtils.formatDateTime(mContent,System.currentTimeMillis(),DateUtils.FORMAT_SHOW_TIME | DateUtils.FORMAT_SHOW_DATE | DateUtils.FORMAT_ABBREV_ALL);
                /*
                 * getLoadingLayoutProxy(final boolean includeStart, final boolean includeEnd)
                 * 用于指定显示的标签，第一个是改变头部，第二个是改变底部
                 */

                ILoadingLayout startLoading = mPullToRefreshListView.getLoadingLayoutProxy(true,false);

                startLoading.setPullLabel("下拉刷新");//刚下拉时显示的提示
                startLoading.setRefreshingLabel("拼命刷新中...");// 刷新时显示的提示
                startLoading.setReleaseLabel("释放即可刷新");// 下拉达到一定距离时显示的提示
                startLoading.setLastUpdatedLabel("最后刷新时间" + str);

                // 设置一秒后停止刷新（本地设置假数据，时间太短，会导致数据更新后，依旧显示刷新中）
                mPullToRefreshListView.postDelayed(new Runnable() {
                    @Override
                    public void run()
                    {
//                      停止刷新
                        mPullToRefreshListView.onRefreshComplete();
//                      加载数据
                        initDownLv();
//                      刷新适配器中的数据
                        myAdapter.notifyDataSetChanged();
                    }
                },1000);

            }
//          上拉加载更多
            @Override
            public void onPullUpToRefresh(PullToRefreshBase<ListView> refreshView)
            {
                // 获取上次刷新时间
                String str = DateUtils.formatDateTime(mContent, System.currentTimeMillis(),
                        DateUtils.FORMAT_SHOW_TIME | DateUtils.FORMAT_SHOW_DATE | DateUtils.FORMAT_ABBREV_ALL);

                ILoadingLayout endLoading = mPullToRefreshListView.getLoadingLayoutProxy(false,true);
                endLoading.setPullLabel("上拉加载更多");// 刚上拉时显示的提示
                endLoading.setRefreshingLabel("拼命加载中...");// 加载时的提示
                endLoading.setReleaseLabel("释放即可加载");// 上拉达到一定距离时显示的提示
                endLoading.setLastUpdatedLabel("最后加载时间:" + str);

                // 设置一秒后停止刷新（本地设置假数据，时间太短，会导致数据更新后，依旧显示刷新中）
                mPullToRefreshListView.postDelayed(new Runnable()
                {
                    @Override
                    public void run()
                    {
                        // 停止刷新
                        mPullToRefreshListView.onRefreshComplete();
                        // 加载数据
                        initUpLv();
                        // 刷新适配器中的数据
                        myAdapter.notifyDataSetChanged();
                    }
                }, 1000);
            }
        });
    }
    // 下拉刷新的假数据
    private void initDownLv()
    {
        countB++;
        datas.add(0, "第 B " + countB + "个 : ");
    }
    // 上拉加载的假数据
    private void initUpLv()
    {
        for(int i = 0; i < 2; i++){
            countA++;
            datas.add("第 A" + countA + "个:");
        }
    }
}
