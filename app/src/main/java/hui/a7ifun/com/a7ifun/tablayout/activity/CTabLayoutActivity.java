package hui.a7ifun.com.a7ifun.tablayout.activity;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

import hui.a7ifun.com.a7ifun.R;
import hui.a7ifun.com.a7ifun.tablayout.adapter.MyCTabLayoutAdapter;
import hui.a7ifun.com.a7ifun.tablayout.base.ViewPagerScroller;
import hui.a7ifun.com.a7ifun.tablayout.fragment.MyFragment;

public class CTabLayoutActivity extends AppCompatActivity {
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private List<MyFragment> list;
    private MyCTabLayoutAdapter adapter;
    private String[] titles = {"主页","群组","搜索","消息","更多"};
    //修改成选择器
    // private int images[] = {R.drawable.home_nor,R.drawable.group_nor,R.drawable.search_nor,R.drawable.msg_nor,R.drawable.more_nor};
    private int images[] = {R.drawable.home_selector,R.drawable.group_selector,R.drawable.search_selector,R.drawable.msg_selector,R.drawable.more_selector};
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ctab_layout);
        //实例化
        viewPager = (ViewPager) findViewById(R.id.vp_ctablayout);
        tabLayout = (TabLayout) findViewById(R.id.tl_ctablayout);
        //准备数据源
        list =new  ArrayList<>();
        for(int i = 1; i <= 5;i++){
            list.add(new MyFragment("标题"+i,"内容"+i));
        }
        //创建适配器
        MyCTabLayoutAdapter myCTabLayoutAdapter = new MyCTabLayoutAdapter(getSupportFragmentManager(),CTabLayoutActivity.this,list,titles,images);
        //绑定ViewPage
        viewPager.setAdapter(myCTabLayoutAdapter);
//      设置切换不需要时间
        ViewPagerScroller scroller = new ViewPagerScroller(CTabLayoutActivity.this);
        scroller.setScrollDuration(0);
        scroller.initViewPagerScroll(viewPager);  //这个是设置切换过渡时间为0毫秒
        //绑定Tab
        tabLayout.setupWithViewPager(viewPager);
        //设置自定义视图
        for(int i = 0;i < tabLayout.getTabCount();i++){
            TabLayout.Tab tab = tabLayout.getTabAt(i);
            tab.setCustomView(myCTabLayoutAdapter.getTabView(i));
        }
    }
}
