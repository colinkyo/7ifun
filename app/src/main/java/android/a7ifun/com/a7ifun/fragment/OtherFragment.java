package android.a7ifun.com.a7ifun.fragment;

import android.a7ifun.com.a7ifun.R;
import android.a7ifun.com.a7ifun.adapter.MyMovieNetAdapter;
import android.a7ifun.com.a7ifun.bean.MovieInfo;
import android.a7ifun.com.a7ifun.okhttp.base.BaseFragment;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.text.format.DateUtils;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.handmark.pulltorefresh.library.ILoadingLayout;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;

/**
 * Created by 7yan on 2017/1/7.
 */

public class OtherFragment extends BaseFragment {
    private static final String TAG = OtherFragment.class.getSimpleName();//"CommonframeFragment"
    private TextView tv_nodata;
    private PullToRefreshListView mPullToRefreshListView;
    private ListView lv_7ifun;
    private String url;
    private MyMovieNetAdapter adapter;
    private List<MovieInfo.RowsBean> rows;
//    allpage 为总页数，page为当前页数
    private int allpage,page;

    @Override
    protected View initView()
    {
        Log.e(TAG,"其他框架Fragment页面被初始化了....");
       /* textView = new TextView(mContent);
        textView.setTextSize(20);
        textView.setGravity(Gravity.CENTER);
        textView.setTextColor(Color.RED);
        return textView;*/
//      加载包含PullToRefreshListView控件的布局进来
        View ptrview = View.inflate(mContent, R.layout.item_movie_ptr_7ifun_container,null);
        tv_nodata = (TextView) ptrview.findViewById(R.id.tv_nodata);
        mPullToRefreshListView = (PullToRefreshListView) ptrview.findViewById(R.id.pull_to_refresh_7ifun);
        lv_7ifun = mPullToRefreshListView.getRefreshableView();
        return ptrview;
    }

    @Override
    protected void initData() {
        super.initData();
        Log.e(TAG,"其他框架Fragment数据被初始化了....");
        //textView.setText("其他框架页面");

//        要先实例化List对象
        rows = new ArrayList<>();

//        创建数据源,从网络中获取
        getDataFromNet(1);
        //创建适配器
        adapter = new MyMovieNetAdapter(mContent, rows);
        lv_7ifun.setAdapter(adapter);
//        调用下拉刷新
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
        mPullToRefreshListView.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<ListView>() {
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

                page = 1;
                Toast.makeText(mContent, "page:" + page, Toast.LENGTH_SHORT).show();
//                设置一秒后停止刷新（本地设置假数据，时间太短，会导致数据更新后，依旧显示刷新中）

                mPullToRefreshListView.postDelayed(new Runnable() {
                    @Override
                    public void run()
                    {
                        // 加载数据
                        // initDownLv();
                        // 清除所有数据，重新加载第一页
                        rows.clear();
                        getDataFromNet(page);
                        //刷新适配器中的数据
                        adapter.notifyDataSetChanged();
                       /* int  position = lv_7ifun.getFirstVisiblePosition();
                        View v = lv_7ifun.getChildAt(0);
                        int top = (v == null) ? 0 : v.getTop();
                        lv_7ifun.setSelectionFromTop(position,top);*/
                    }
                },500);
            }

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

                if((page + 1) >= allpage)
                {
                    page = allpage;
                }else {
                    page = page + 1;
                }
//                Toast.makeText(mContent, "allpage:" + page, Toast.LENGTH_SHORT).show();
//                设置一秒后停止刷新（本地设置假数据，时间太短，会导致数据更新后，依旧显示刷新中）
                mPullToRefreshListView.postDelayed(new Runnable() {
                    @Override
                    public void run()
                    {
//                      加载数据

                        int idx = lv_7ifun.getFirstVisiblePosition();
                        getDataFromNet(page);
                        //刷新适配器中的数据
                        adapter.notifyDataSetChanged();
                        lv_7ifun.setSelection(idx);
                      /*  int  position = lv_7ifun.getFirstVisiblePosition();
                        View v = lv_7ifun.getChildAt(0);
                        int top = (v == null) ? 0 : v.getTop();
                        lv_7ifun.setSelectionFromTop(position,top);*/
                    }
                },500);
            }
        });
    }

    /**
     *
     * @param page 要获取的页码
     */
    private void getDataFromNet(int page)
    {
        url = "http://www.7ifun.com/api.php";
        //url = "http://192.168.1.2/7ifun/api.php";
        //发送获取API数据
        OkHttpUtils
//                .get()
                .post()
                .addParams("page",page+"")
                .url(url)
                .id(100)  //标记不同的请求，在响应里分别处理
                .build()
                .execute(new MyStringCallback());
    }
    class MyStringCallback extends StringCallback
    {
        //响应出错
        @Override
        public void onError(Call call, Exception e, int id)
        {
            e.printStackTrace();
            Log.e("Yan","加载出错: " + e.getMessage());
            tv_nodata.setText("加载出错: " + e.getMessage());
            tv_nodata.setVisibility(View.VISIBLE);
        }
        //响应成功
        @Override
        public void onResponse(String response, int id)
        {
            tv_nodata.setVisibility(View.GONE);
            switch (id) {
                case 100:
                    Toast.makeText(mContent, "标记为100的响应", Toast.LENGTH_SHORT).show();
                    break;
                case 101:
                    Toast.makeText(mContent, "标记为101的响应", Toast.LENGTH_SHORT).show();
                    break;
            }
            //如果返回有结果的话,解析JSON数据
            if(response != null)
            {
                parseData(response);
            }

        }
    }
//  解析JSON数据
    private void parseData(String response)
    {
        //用了FastJSON
        String json = response;

        //快捷键 Alt + S 用GsonFormat 工具，创建json 数据对应的bean
        MovieInfo movieInfo = JSON.parseObject(json,MovieInfo.class);
        allpage = movieInfo.getAllpage();
        page = movieInfo.getPage();
        //准备数据源
        //List<MovieInfo.RowsBean> rows = movieInfo.getRows();

        for(int j=0; j < movieInfo.getRows().size();j++)
        {
            rows.add(movieInfo.getRows().get(j));
        }
        if(rows != null && rows.size() >0)
        {
            tv_nodata.setVisibility(View.GONE);
           /* //创建适配器
            adapter = new MyMovieNetAdapter(mContent, rows);
            lv_7ifun.setAdapter(adapter);*/
        }else{
            tv_nodata.setVisibility(View.VISIBLE);
        }
        //进度条始终都要关闭
        //pb_loading.setVisibility(View.GONE);
        //停止刷新
        mPullToRefreshListView.onRefreshComplete();
    }
}
