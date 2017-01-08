package android.a7ifun.com.a7ifun.fragment;

import android.a7ifun.com.a7ifun.okhttp.base.BaseFragment;
import android.graphics.Color;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;

/**
 * Created by 7yan on 2017/1/7.
 */

public class OtherFragment extends BaseFragment {
    private static final String TAG = OtherFragment.class.getSimpleName();//"CommonframeFragment"
    private TextView textView;
    @Override
    protected View initView()
    {
        Log.e(TAG,"其他框架Fragment页面被初始化了....");
        textView = new TextView(mContent);
        textView.setTextSize(20);
        textView.setGravity(Gravity.CENTER);
        textView.setTextColor(Color.RED);
        return textView;
    }

    @Override
    protected void initData() {
        super.initData();
        Log.e(TAG,"其他框架Fragment数据被初始化了....");
        textView.setText("其他框架页面");
    }
}
