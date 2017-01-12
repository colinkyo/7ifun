package android.a7ifun.com.a7ifun.tablayout.fragment;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by 7yan on 2017/1/9.
 */
//需要 v4 包的
public class MyFragment extends Fragment {
    private final String title;
    private final String content;
    Context mContext;
    TextView textView;

    /**
     * 得到标题
     * @return
     */
    public String getTitle() {
        return title;
    }
    /**
     * 得到内容
     * @return
     */
    public String getContent() {
        return content;
    }

    /**
     * 传递参数
     * @param title
     * @param content
     */
    public MyFragment(String title,String content)
    {
//      可调可不调
        super();
        this.title = title;
        this.content = content;
    }

    /**
     * 获取上下文
     * @param savedInstanceState
     */
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = getActivity();
    }

    /**
     * 创建视图，即可以添加布局设计XML文件
     *
     * @param inflater
     * @param container
     * @param savedInstanceState
     * @return
     */
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
//        现在随便返回一个 TextView 的视图
        textView = new TextView(mContext);
        textView.setTextColor(Color.RED);
        textView.setTextSize(25);
        textView.setGravity(Gravity.CENTER);
        return textView;
    }

    /**
     * 绑定数据
     * @param savedInstanceState
     */
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState)
    {
        super.onActivityCreated(savedInstanceState);
        //设置内容,
//        复杂的话，是适配器数据
        textView.setText(content);
    }
}
