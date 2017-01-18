package hui.a7ifun.com.a7ifun.fragment;

import hui.a7ifun.com.a7ifun.R;
import hui.a7ifun.com.a7ifun.adapter.MyMovieViewPageAdapter;
import hui.a7ifun.com.a7ifun.okhttp.base.BaseFragment;
import hui.a7ifun.com.a7ifun.tablayout.base.ViewPagerScroller;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * Created by 7yan on 2017/1/7.
 */

public class ThirdPartyFragment extends BaseFragment
{
    private static final String TAG = ThirdPartyFragment.class.getSimpleName();//"CommonframeFragment"
    private ViewPager viewPager;
    private ArrayList<MovieFragment> arrayList;
    private MyMovieViewPageAdapter adapter;
    private TabLayout tabLayout;

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
        View movieView =View.inflate(mContent, R.layout.item_movie_container_tablayout,null);
        //实例化控件
        viewPager = (ViewPager) movieView.findViewById(R.id.vp_tablayout);
        //tab标题
        tabLayout = (TabLayout) movieView.findViewById(R.id.tablayout_title);

        //创建fragments,准备数据源
        arrayList = new ArrayList<>();
       /* for(int i =0; i < 7;i++)
        {
            MovieFragment movieFragment = new MovieFragment("第" + i +"个fragment","标题" + i);
            arrayList.add(movieFragment);
        }*/



/*        public final static Map<String, Fragment> NAV_ITEM_ADPTER = new HashMap<String, Fragment>() {
            {
                put("拍录传", new CameraFragment());
                put("集群对讲", new GroupTalkFragment());
                put("视通", new VideoCallFragment());
                put("位置", new PositionFragment());
                put("浏览", new BrowseFragment());
                put("消息", new MsgFragment());
                put("群组", new GroupFragment());
                put("设置", null);
                put("退出", null);
            }
        };*/

//        fragment 要加载的布局文件ID
        int resid = R.layout.item_movie_ptr_7ifun_container;
        Map<String,String> para1,para2,para3,para4;
        para1 = new HashMap<String,String>();
        para2 = new HashMap<String,String>();
        para3 = new HashMap<String,String>();
        para4 = new HashMap<String,String>();


        para1.put("keyword","");
        arrayList.add(new MovieFragment("第 1 个fragment","首页",resid,para1));

        para2.put("slang","粤语");
        arrayList.add(new MovieFragment("第 2 个fragment","粤语",resid,para2));

        para3.put("sort","lately");
        arrayList.add(new MovieFragment("第 3 个fragment","最近更新",resid,para3));

        para4.put("sort","onview");
        arrayList.add(new MovieFragment("第 4 个fragment","最近上映",resid,para4));
//        创建适配器
        adapter = new MyMovieViewPageAdapter(getActivity().getSupportFragmentManager(),arrayList);
//        绑定数据
        viewPager.setAdapter(adapter);

//      viewPager.setOffscreenPageLimit(4);表示四个界面之间来回切换都不会重新加载
        viewPager.setOffscreenPageLimit(4);
//      设置切换滑动动画时间
        /*ViewPagerScroller scroller = new ViewPagerScroller(mContent);
        scroller.setScrollDuration(4000);
        scroller.initViewPagerScroll(viewPager);  //这个是设置切换过渡时间为0毫秒*/
//       关联tab
        tabLayout.setupWithViewPager(viewPager);
//       标题可以滚动
        tabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);
        return movieView;
    }

    @Override
    protected void initData()
    {
        super.initData();
        Log.e(TAG,"第三方Fragment数据被初始化了....");
        //2:网络获取数据
        //getDataFromNet();
    }

    private void getDataFromNet()
    {
    }
}
