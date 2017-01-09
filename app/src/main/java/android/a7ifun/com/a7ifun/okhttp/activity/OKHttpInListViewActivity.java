package android.a7ifun.com.a7ifun.okhttp.activity;

import android.a7ifun.com.a7ifun.R;
import android.a7ifun.com.a7ifun.json.bean.ShopInfo;
import android.a7ifun.com.a7ifun.okhttp.adapter.OKHttpInListViewAdapter;
import android.a7ifun.com.a7ifun.okhttp.bean.MovieInfo;
import android.a7ifun.com.a7ifun.utils.CacheUtils;
import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.List;

import okhttp3.Call;
import okhttp3.Request;

public class OKHttpInListViewActivity extends Activity {
    private ListView listview;
    private ProgressBar progressbar;
    private TextView tv_nodata;
    private String TAG = OKHttpInListViewActivity.class.getSimpleName();
    private OKHttpInListViewAdapter adapter;
    private String url;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_okhttp_in_list_view);
        initView();
        getDataFromNet();
    }

    private void getDataFromNet()
    {
        //url = "http://192.168.1.2/7ifun/api.php";
        url = "http://www.7ifun.com/api.php";
        String saveJson = CacheUtils.getString(OKHttpInListViewActivity.this, url);
        if(!TextUtils.isEmpty(saveJson)){
            parseData(saveJson);
            return;
        }
        Toast.makeText(this, "需要return", Toast.LENGTH_SHORT).show();
        //url = "http://api.m.mtime.cn/PageSubArea/TrailerList.api";
        OkHttpUtils
                .get()
                .url(url)
                .id(100)
                .build()
                .execute(new MyStringCallback());

    }
    public class MyStringCallback extends StringCallback {
        @Override
        public void onBefore(Request request, int id) {

            setTitle("loading...");
        }

        @Override
        public void onAfter(int id) {

            //setTitle("Sample-okHttp");
        }

        @Override
        public void onError(Call call, Exception e, int id)
        {
            e.printStackTrace();
            tv_nodata.setText("onError:" + e.getMessage());
            tv_nodata.setVisibility(View.VISIBLE);
        }

        @Override
        public void onResponse(String response, int id) {
            Log.e(TAG, "onResponse：complete");
            tv_nodata.setVisibility(View.GONE);
            switch (id) {
                case 100:
                    Toast.makeText(OKHttpInListViewActivity.this, "Get", Toast.LENGTH_SHORT).show();
                    break;
                case 101:
                    Toast.makeText(OKHttpInListViewActivity.this, "Post", Toast.LENGTH_SHORT).show();
                    break;
            }
            //解析JSON数据
            if(response !=null)
            {
                CacheUtils.putString(OKHttpInListViewActivity.this,url,response);
                parseData(response);
                //Toast.makeText(OKHttpInListViewActivity.this, movieInfo.getTotal(), Toast.LENGTH_SHORT).show();

            }
        }

        @Override
        public void inProgress(float progress, long total, int id) {
            Log.e(TAG, "inProgress:" + progress);
            //progressbar.setProgress((int) (100 * progress));
        }
    }

    private void parseData(String response)
    {
        //用了FastJSON
        String json = response;

        MovieInfo movieInfo = JSON.parseObject(json, MovieInfo.class);

        List<MovieInfo.RowsBean> rows = movieInfo.getRows();
        //Toast.makeText(OKHttpInListViewActivity.this, "rows.size():" + rows.size(), Toast.LENGTH_SHORT).show();
        if(rows != null && rows.size() >0)
        {
            tv_nodata.setVisibility(View.GONE);
            /*for (int i = 0; i < rows.size(); i++) {
                MovieInfo.RowsBean rowsBean = rows.get(i);
                String sname = rowsBean.getSname();
            }*/
            //创建适配器
            adapter = new OKHttpInListViewAdapter(OKHttpInListViewActivity.this,rows);
            listview.setAdapter(adapter);

        }else{
            tv_nodata.setVisibility(View.VISIBLE);
        }
        //进度条始终都要关闭
        progressbar.setVisibility(View.GONE);
    }

    private void initView() {
        tv_nodata = (TextView) findViewById(R.id.tv_nodata);
        progressbar = (ProgressBar) findViewById(R.id.progressbar);
        listview = (ListView) findViewById(R.id.listview);
    }
}
