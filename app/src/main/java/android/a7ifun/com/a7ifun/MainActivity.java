package android.a7ifun.com.a7ifun;

import android.a7ifun.com.a7ifun.okhttp.base.BaseFragment;
import android.a7ifun.com.a7ifun.fragment.CommonframeFragment;
import android.a7ifun.com.a7ifun.fragment.CustomFragment;
import android.a7ifun.com.a7ifun.fragment.OtherFragment;
import android.a7ifun.com.a7ifun.fragment.ThirdPartyFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.os.Bundle;
import android.widget.RadioGroup;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends FragmentActivity
{
    private RadioGroup mRg_main;
    private List<BaseFragment> mBaseFragmnet;
    //选择fragment对应的位置
    private int position;
    //上次切换的fragment
    private Fragment mContent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //初始化view       
        initView();
        //初始化fragment
        initFragment();
        //设置RadioGroup监听
        setListener();

    }

    private void setListener()
    {
        mRg_main.setOnCheckedChangeListener(new myOnCheckedChangeListener());
        //默认选择项
        mRg_main.check(R.id.bnt2);
    }
    class myOnCheckedChangeListener implements RadioGroup.OnCheckedChangeListener{

        @Override
        public void onCheckedChanged(RadioGroup radioGroup, int i)
        {
            switch (i){
                case R.id.bnt1:
                    position = 0;
                    break;
                case R.id.bnt2:
                    position = 1;
                    break;
                case R.id.bnt3:
                    position = 2;
                    break;
                case R.id.bnt4:
                    position = 3;
                    break;
                default:
                    position = 0;
                    break;
            }
            //根据位置得到对应的fragment
            BaseFragment to = getFragment();
            //替换FrameLayout
            switchFragment(mContent,to);
        }
    }

    /**
     *
     * @param from 当前显示的fragment 马上要被隐藏
     * @param to 马上要被显示
     */
    private void switchFragment(Fragment from,Fragment to)
    {
        if(from != to)
        {
            mContent = to;
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            //判断有没有被添加
            if(!to.isAdded()){
                //没有被添加
                //from隐藏
                if(from != null){
                    ft.hide(from);
                }
                //to 添加
                if(to != null)
                {
                    ft.add(R.id.fl_content,to).commit();
                }

            }else{
                //to已经被添加
                //from隐藏
                if(from != null){
                    ft.hide(from);
                }
                //to 显示
                if(to != null)
                {
                    ft.show(to).commit();
                }
            }
        }
    }
   /* private void switchFragment(BaseFragment fragment)
    {
        //注意是v4
        FragmentManager fm = getSupportFragmentManager();
        //开始事务
        FragmentTransaction transaction = fm.beginTransaction();
        //替换
        transaction.replace(R.id.fl_content,fragment);
        //提交事务
        transaction.commit();
    }*/
    /**
     * 根据位置得到对应的fragment
     * @return
     */
    private BaseFragment getFragment()
    {
        BaseFragment fragment = mBaseFragmnet.get(position);
        return fragment;
    }

    private void initFragment()
    {
        mBaseFragmnet = new ArrayList<>();
        mBaseFragmnet.add(new CommonframeFragment());
        mBaseFragmnet.add(new ThirdPartyFragment());
        mBaseFragmnet.add(new CustomFragment());
        mBaseFragmnet.add(new OtherFragment());
    }

    private void initView() {
        setContentView(R.layout.activity_main);
        mRg_main = (RadioGroup) findViewById(R.id.rg_main);
    }
}
