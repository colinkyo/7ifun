package android.a7ifun.com.a7ifun.fragment;

import android.a7ifun.com.a7ifun.R;
import android.a7ifun.com.a7ifun.adapter.MyMovieAdapter;
import android.a7ifun.com.a7ifun.adapter.MyMovieNetAdapter;
import android.a7ifun.com.a7ifun.bean.MovieInfo;
import android.a7ifun.com.a7ifun.okhttp.activity.OKHttpInListViewActivity;
import android.a7ifun.com.a7ifun.okhttp.base.BaseFragment;
import android.graphics.Color;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.lang.ref.SoftReference;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import okhttp3.Call;

/**
 * Created by 7yan on 2017/1/7.
 */

public class ThirdPartyFragment extends BaseFragment {
    private static final String TAG = ThirdPartyFragment.class.getSimpleName();//"CommonframeFragment"
    private ListView iv_movie_list;
    private TextView tv_nodata;
    private ProgressBar pb_loading;
    private ArrayList<Map<String,String>> list;
    private String url;

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
       /* for(int i=0;i < 10;i++)
        {
            //Map item = new HashMap();
            Map item = new HashMap<String,String>();
            item.put("name","电影"+i);
            item.put("actor","主演"+i);
            item.put("time","2010-10-0"+i);
            list.add(item);

        }
       //创建适配器
        MyMovieAdapter adapter = new MyMovieAdapter(mContent, list);
        //绑定适配器
        iv_movie_list.setAdapter(adapter);*/

        //2:网络获取数据
        getDataFromNet();
    }

    private void getDataFromNet()
    {
        url = "http://www.7ifun.com/api.php";
        //url = "http://192.168.1.2/7ifun/api.php";
        //发送获取API数据
        OkHttpUtils
                .get()
                .url(url)
                .id(100)  //标记不同的请求，在响应里分别处理
                .build()
                .execute(new MyStringCallback());
    }
    //MyStringCallback 为请求响应
    class MyStringCallback extends StringCallback{
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
    //解析JSON数据
    private void parseData(String response)
    {
        //用了FastJSON
        String json = response;

        //快捷键 Alt + S 用GsonFormat 工具，创建json 数据对应的bean

        MovieInfo movieInfo = JSON.parseObject(json,MovieInfo.class);

        //准备数据源
        List<MovieInfo.RowsBean> rows = movieInfo.getRows();
        if(rows != null && rows.size() >0)
        {
            tv_nodata.setVisibility(View.GONE);
            //创建适配器
            MyMovieNetAdapter adapter = new MyMovieNetAdapter(mContent, rows);
            iv_movie_list.setAdapter(adapter);
        }else{
            tv_nodata.setVisibility(View.VISIBLE);
        }
        //进度条始终都要关闭
        pb_loading.setVisibility(View.GONE);

    }
}
