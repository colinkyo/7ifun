package hui.a7ifun.com.a7ifun.okhttp.base;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by 7yan on 2017/1/7.
 */
//继承android.support.v4.app.Fragment的
public abstract class BaseFragment extends Fragment
{
    //注意定义为   protected
    protected Context mContent;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //得到上下文
        mContent=getActivity();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return initView();
    }

    /**
     * 如果类中有方法是抽象的，那该类也一定是抽象的
     * 强制子类重写，实现子类独有UI
     * @return
     */
    protected abstract View initView();

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initData();
    }

    /**
     * 当子类需要初始化数据，或者联网请求绑定数据，展示数据时，等等可以重写该方法
     */
    protected void initData() {
    }
}
