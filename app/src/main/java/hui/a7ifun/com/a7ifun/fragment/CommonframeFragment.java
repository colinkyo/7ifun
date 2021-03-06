package hui.a7ifun.com.a7ifun.fragment;

import hui.a7ifun.com.a7ifun.android_pulltorefresh.PullToRefreshActivity;
import hui.a7ifun.com.a7ifun.butterknife.activity.ButterKnifeActivity;
import hui.a7ifun.com.a7ifun.eventbus.EventBusActivity;
import hui.a7ifun.com.a7ifun.json.activity.FastJsonActivity;
import hui.a7ifun.com.a7ifun.json.activity.GsonActivity;
import hui.a7ifun.com.a7ifun.json.activity.NativeJsonParseActivity;
import hui.a7ifun.com.a7ifun.okhttp.activity.OKHttpActivity;
import hui.a7ifun.com.a7ifun.okhttp.adapter.CommonFrameFragmentAdapter;
import hui.a7ifun.com.a7ifun.okhttp.base.BaseFragment;
import hui.a7ifun.com.a7ifun.smscheck.activity.SMSCheckActivity;
import hui.a7ifun.com.a7ifun.tablayout.activity.TabLayoutActivity;
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
        View view = View.inflate(mContent, hui.a7ifun.com.a7ifun.R.layout.fragment_common, null);
        mlv = (ListView) view.findViewById(hui.a7ifun.com.a7ifun.R.id.lv);
        mlv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String data = datas[i];
                if(data.toLowerCase().equals("okhttp"))
                {
                    Intent intent = new Intent(mContent, OKHttpActivity.class);
                    startActivity(intent);
                    //mContent.startActivity(intent);
                }else if(data.toLowerCase().equals("nativejsonparse"))
                {
                    Intent intent = new Intent(mContent, NativeJsonParseActivity.class);
                    startActivity(intent);

                }else if(data.toLowerCase().equals("gson"))
                {
                    Intent intent = new Intent(mContent, GsonActivity.class);
                    startActivity(intent);

                }else if(data.toLowerCase().equals("fastjson"))
                {
                    Intent intent = new Intent(mContent, FastJsonActivity.class);
                    startActivity(intent);

                }else if(data.toLowerCase().equals("pulltorefresh"))
                {
                    Intent intent = new Intent(mContent, PullToRefreshActivity.class);
                    startActivity(intent);

                }else if(data.toLowerCase().equals("eventbus"))
                {
                    Intent intent = new Intent(mContent, EventBusActivity.class);
                    startActivity(intent);

                }else if(data.toLowerCase().equals("tablayout"))
                {
                    Intent intent = new Intent(mContent, TabLayoutActivity.class);
                    startActivity(intent);

                }else if(data.toLowerCase().equals("butterknife"))
                {
                    Intent intent = new Intent(mContent, ButterKnifeActivity.class);
                    startActivity(intent);

                }else if(data.toLowerCase().equals("smscheck"))
                {
                    Intent intent = new Intent(mContent, SMSCheckActivity.class);
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
        datas = new String[]{"OKHttp","nativeJsonParse","Gson","FastJson","ButterKnife","TabLayout","SMSCheck", "xUtils", "Retrofit2",
                "Fresco", "Glide", "GrennDao", "Rxjava",
                "picasso", "eventbus", "jcvideoplayer", "pulltorefresh", "Expandablelistview", "UniversalviedoView"};
        //配置适配器
        adapter = new CommonFrameFragmentAdapter(mContent, datas);
        mlv.setAdapter(adapter);
    }
}
