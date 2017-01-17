package hui.a7ifun.com.a7ifun.adapter;

import hui.a7ifun.com.a7ifun.bean.MovieInfo;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

/**
 * Created by 7yan on 2017/1/13.
 */

public class MyMovieNetAdapter extends BaseAdapter
{
    private final Context mContext;
    private final List<MovieInfo.RowsBean> mDatas    ;

    public MyMovieNetAdapter(Context context, List<MovieInfo.RowsBean> mdata)
    {
        this.mContext = context;
        this.mDatas = mdata;
    }

    @Override
    public int getCount() {
        return mDatas.size();
    }

    @Override
    public Object getItem(int position) {
        return mDatas.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup)
    {
        ViewHodle viewHodle;
        if(view == null)
        {
            view = View.inflate(mContext, hui.a7ifun.com.a7ifun.R.layout.item_movie_list,null);
            viewHodle = new ViewHodle();
            viewHodle.iv_movie_logo = (ImageView) view.findViewById(hui.a7ifun.com.a7ifun.R.id.iv_movie_logo);
            viewHodle.tv_movie_name = (TextView) view.findViewById(hui.a7ifun.com.a7ifun.R.id.tv_movie_name);
            viewHodle.tv_movie_time = (TextView) view.findViewById(hui.a7ifun.com.a7ifun.R.id.tv_movie_time);
            viewHodle.tv_movie_actor = (TextView) view.findViewById(hui.a7ifun.com.a7ifun.R.id.tv_movie_actor);
            view.setTag(viewHodle);

        }else
        {
            viewHodle = (ViewHodle) view.getTag();
        }

        //根据位置获取数据

        MovieInfo.RowsBean rowsBean = mDatas.get(position);
        viewHodle.tv_movie_name.setText(rowsBean.getSname());
        viewHodle.tv_movie_actor.setText(rowsBean.getSsname());
        viewHodle.tv_movie_time.setText(rowsBean.getStime());

        //使用Glid下载图片
        final String url = "http://www.7ifun.com/"+rowsBean.getSimg();
        //Glide获取图片显示
        Glide.with(mContext).load(url).into(viewHodle.iv_movie_logo);

        return view;
    }
    static class ViewHodle{
        private ImageView iv_movie_logo;
        private TextView tv_movie_name;
        private TextView tv_movie_time;
        private TextView tv_movie_actor;
    }
}
