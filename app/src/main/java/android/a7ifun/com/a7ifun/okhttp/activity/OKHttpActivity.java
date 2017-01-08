package android.a7ifun.com.a7ifun.okhttp.activity;

import android.a7ifun.com.a7ifun.R;
import android.app.Activity;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.BitmapCallback;
import com.zhy.http.okhttp.callback.FileCallBack;
import com.zhy.http.okhttp.callback.StringCallback;

import java.io.File;
import java.io.IOException;

import okhttp3.Call;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class OKHttpActivity extends Activity implements View.OnClickListener {
    private static final int GET = 1;
    private static final int POST = 2;
    private static final int GET1 = 3;
    private static final int POST1 = 4;
    private ImageView iv;
    private static final String TAG = OKHttpActivity.class.getSimpleName();
    private Button btn_get, btn_post, btn_get1, btn_post1, btn_download,btn_downloadimg;
    private TextView tv_show, tv_title;
    private ProgressBar pb;
    //OKHttp
    public static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");
    private OkHttpClient client = new OkHttpClient();
    //OKHttp
    private Handler handle = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case GET:
                    //获取数据
                    tv_show.setText((String) msg.obj);
                    break;
                case POST:
                    //获取数据
                    tv_show.setText((String) msg.obj);
                    break;
                case GET1:
                    break;
                case POST1:
                    break;
            }
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_okhttp);
        //初始化
        initView();
    }

    private void initView() {
        btn_get = (Button) findViewById(R.id.btn_get);
        btn_post = (Button) findViewById(R.id.btn_post);
        btn_get1 = (Button) findViewById(R.id.btn_get1);
        btn_post1 = (Button) findViewById(R.id.btn_post1);
        btn_download = (Button) findViewById(R.id.btn_download);
        btn_downloadimg = (Button) findViewById(R.id.btn_downloadimg);

        tv_show = (TextView) findViewById(R.id.tv_show);
        tv_title = (TextView) findViewById(R.id.tv_title);
        tv_title.setText("OKHttp的简单使用方法");

        iv = (ImageView) findViewById(R.id.iv);
        pb = (ProgressBar) findViewById(R.id.pb);
        //设置按钮单击监听事件
        btn_get.setOnClickListener(this);
        btn_get1.setOnClickListener(this);
        btn_post.setOnClickListener(this);
        btn_post1.setOnClickListener(this);
        btn_download.setOnClickListener(this);
        btn_downloadimg.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        tv_show.setText("");
        switch (view.getId()) {
            case R.id.btn_get:
                getDataFromOKHttpGet();
                break;
            case R.id.btn_post:
                getDataFromOKHttpPost();
                break;
            case R.id.btn_get1:
                getDataFromOKHttpUtilsGet();
                break;
            case R.id.btn_post1:
                getDataFromOKHttpUtilsPost();
                break;
            case R.id.btn_download:
                downloadFile();
                break;
            case R.id.btn_downloadimg:
                downloadImg();
                break;
        }
    }
    public void downloadImg()
    {
        String url = "http://images.csdn.net/20150817/1.jpg";
        OkHttpUtils
                .get()//
                .url(url)//
                .tag(this)//
                .build()//
                .connTimeOut(20000)
                .readTimeOut(20000)
                .writeTimeOut(20000)
                .execute(new BitmapCallback()
                {
                    @Override
                    public void onError(Call call, Exception e, int id)
                    {
                        tv_show.setText("onError:" + e.getMessage());
                    }

                    @Override
                    public void onResponse(Bitmap bitmap, int id)
                    {
                        Log.e("TAG", "onResponse：complete");
                        iv.setImageBitmap(bitmap);
                    }
                });
    }
    private void downloadFile() {
        String url = "http://vf1.mtime.cn/Video/2017/01/07/flv/170107103740836412.flv";
        OkHttpUtils//
                .get()//
                .url(url)//
                .build()//
                .execute(new FileCallBack(Environment.getExternalStorageDirectory().getAbsolutePath(), "db.flv")//
                {

                    @Override
                    public void onBefore(Request request, int id) {
                    }

                    @Override
                    public void inProgress(float progress, long total, int id) {
                        pb.setProgress((int) (100 * progress));
                        Log.e(TAG, "inProgress :" + (int) (100 * progress));
                    }

                    @Override
                    public void onError(Call call, Exception e, int id) {
                        Log.e(TAG, "onError :" + e.getMessage());
                    }

                    @Override
                    public void onResponse(File file, int id) {
                        Log.e(TAG, "onResponse :" + file.getAbsolutePath());
                    }
                });
    }

    //原生OKHttp Get请求
    public String okhttpGet(String url) throws IOException {
        Request request = new Request.Builder()
                .url(url)
                .build();
        Response response = client.newCall(request).execute();
        return response.body().string();
    }

    //    原生OKHttp Post请求
    private String okhttpPost(String url, String json) throws IOException {
        RequestBody body = RequestBody.create(JSON, json);
        Request request = new Request.Builder()
                .url(url)
                .post(body)
                .build();
        Response response = client.newCall(request).execute();
        return response.body().string();
    }

    private void getDataFromOKHttpPost() {
        new Thread() {
            @Override
            public void run() {
                super.run();
                try {
                    String result = okhttpPost("http://api.m.mtime.cn/PageSubArea/TrailerList.api", "");
                    Message msg = Message.obtain();
                    msg.what = POST;
                    msg.obj = result;
                    handle.sendMessage(msg);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }.start();
    }

    private void getDataFromOKHttpGet() {
        new Thread() {
            @Override
            public void run() {
                super.run();
                try {
                    String result = okhttpGet("http://api.m.mtime.cn/PageSubArea/TrailerList.api");
                    Message msg = Message.obtain();
                    //设置为原生GET方法
                    msg.what = GET;
                    msg.obj = result;
                    handle.sendMessage(msg);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }.start();
    }

    public void getDataFromOKHttpUtilsGet() {
        String url = "http://www.zhiyun-tech.com/App/Rider-M/changelog-zh.txt";
        //url="http://www.391k.com/api/xapi.ashx/info.json?key=bd_hyrzjjfb4modhj&size=10&page=1";
        url = "http://api.m.mtime.cn/PageSubArea/TrailerList.api";
        OkHttpUtils
                .get()
                .url(url)
                .id(100)
                .build()
                .execute(new MyStringCallback());
    }

    public void getDataFromOKHttpUtilsPost() {

        String url = "http://api.m.mtime.cn/PageSubArea/TrailerList.api";
        /*OkHttpUtils
                .postString()
                .url(url)
                .mediaType(MediaType.parse("application/json; charset=utf-8"))
                .content(new Gson().toJson(new User("zhy", "123")))
                .build()
                .execute(new MyStringCallback());*/
        OkHttpUtils
                .post()
                .url(url)
                .id(101)
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
            setTitle("Sample-okHttp");
        }

        @Override
        public void onError(Call call, Exception e, int id) {
            e.printStackTrace();
            tv_show.setText("onError:" + e.getMessage());
        }

        @Override
        public void onResponse(String response, int id) {
            Log.e(TAG, "onResponse：complete");
            tv_show.setText("onResponse:" + response);

            switch (id) {
                case 100:
                    Toast.makeText(OKHttpActivity.this, "Get", Toast.LENGTH_SHORT).show();
                    break;
                case 101:
                    Toast.makeText(OKHttpActivity.this, "Post", Toast.LENGTH_SHORT).show();
                    break;
            }
        }

        @Override
        public void inProgress(float progress, long total, int id) {
            Log.e(TAG, "inProgress:" + progress);
            //mProgressBar.setProgress((int) (100 * progress));
        }
    }
}
