package android.a7ifun.com.a7ifun.tablayout.activity;

import android.a7ifun.com.a7ifun.R;
import android.a7ifun.com.a7ifun.tablayout.adapter.ViewPageAdapter;
import android.a7ifun.com.a7ifun.tablayout.fragment.MyFragment;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import java.util.ArrayList;


public class TabLayoutActivity extends AppCompatActivity {
    private ViewPager viewpage;
    private TextView tv_title;
    private ArrayList<MyFragment> fragments;
    private ViewPageAdapter adapter;
    private TabLayout tabLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tab_layout);

        viewpage = (ViewPager) findViewById(R.id.viewpage);
        tv_title = (TextView) findViewById(R.id.tv_title);
        tabLayout = (TabLayout) findViewById(R.id.tabLayout);
        tv_title.setText("TabLayout......");
        fragments = new ArrayList<>();

        //初始化数据
        for (int i = 0; i < 12; i++) {
            fragments.add(new MyFragment("标题" + i, "内容" + i));
        }
//        创建适配器
//        要继承 AppCompatActivity 才有 getSupportFragmentManager
//        传递数据
        adapter = new ViewPageAdapter(getSupportFragmentManager(), fragments);
//        绑定适配器
        viewpage.setAdapter(adapter);
//
        tabLayout.setupWithViewPager(viewpage);
        tabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);
    }
}
