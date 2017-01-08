package android.a7ifun.com.a7ifun.fragment;

import android.a7ifun.com.a7ifun.R;
import android.a7ifun.com.a7ifun.json.activity.FastJsonActivity;
import android.a7ifun.com.a7ifun.json.activity.GsonActivity;
import android.a7ifun.com.a7ifun.json.activity.NativeJsonParseActivity;
import android.a7ifun.com.a7ifun.okhttp.activity.OKHttpActivity;
import android.a7ifun.com.a7ifun.okhttp.adapter.CommonFrameFragmentAdapter;
import android.a7ifun.com.a7ifun.okhttp.base.BaseFragment;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

/**
 * Created by 7yan on 2017/1/7.
 */

public class CommonframeFragment extends BaseFragment {
    private static final String TAG = CommonframeFragment.class.getSimpleName();//"CommonframeFragment"
    private ListView mlv;
    private String[] datas;
    private CommonFrameFragmentAdapter adapter;

    @Override
    protected View initView() {
        Log.e(TAG, "常用框架Fragment页面被初始化了....");
        View view = View.inflate(mContent, R.layout.fragment_common, null);
        mlv = (ListView) view.findViewById(R.id.lv);
        mlv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String data = datas[i];
                if(data.toLowerCase().equals("okhttp"))
                {
                    Intent intent = new Intent(mContent, OKHttpActivity.class);
                    startActivity(intent);
                    //mContent.startActivity(intent);
                }else  if(data.toLowerCase().equals("nativejsonparse"))
                {
                    Intent intent = new Intent(mContent, NativeJsonParseActivity.class);
                    startActivity(intent);

                }else  if(data.toLowerCase().equals("gson"))
                {
                    Intent intent = new Intent(mContent, GsonActivity.class);
                    startActivity(intent);

                }else  if(data.toLowerCase().equals("fastjson"))
                {
                    Intent intent = new Intent(mContent, FastJsonActivity.class);
                    startActivity(intent);

                }

                Toast.makeText(mContent, data, Toast.LENGTH_SHORT).show();
            }
        });
        return view;
    }

    @Override
    protected void initData() {
        super.initData();
        Log.e(TAG, "常用框架Fragment数据被初始化了....");
        //准备数据源
        datas = new String[]{"OKHttp","nativeJsonParse","Gson","FastJson", "xUtils", "Retrofit2",
                "Fresco", "Glide", "GrennDao", "Rxjava",
                "picasso", "evenBus", "jcvideoplayer", "pulltorefresh", "Expandablelistview", "UniversalviedoView"};
        //配置适配器
        adapter = new CommonFrameFragmentAdapter(mContent, datas);
        mlv.setAdapter(adapter);
    }
}
