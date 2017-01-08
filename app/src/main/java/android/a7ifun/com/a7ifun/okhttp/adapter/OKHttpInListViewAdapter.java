package android.a7ifun.com.a7ifun.okhttp.adapter;

import android.a7ifun.com.a7ifun.R;
import android.a7ifun.com.a7ifun.okhttp.bean.MovieInfo;
import android.content.Context;
import android.graphics.Bitmap;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.BitmapCallback;

import java.util.List;

import okhttp3.Call;

/**
 * Created by 7yan on 2017/1/8.
 */

public class OKHttpInListViewAdapter extends BaseAdapter {
    private final Context mContext;
    private final List<MovieInfo.RowsBean> mDatas;

    public OKHttpInListViewAdapter(Context context, List<MovieInfo.RowsBean> datas)
    {
        this.mContext = context;
        this.mDatas = datas;
    }

    @Override
    public int getCount() {
        return mDatas.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup){
         final ViewHolder viewHolder;
        if(view == null)
        {
            view = View.inflate(mContext, R.layout.item_okhttplistview,null);
            viewHolder = new ViewHolder();
            viewHolder.iv_icon = (ImageView) view.findViewById(R.id.iv_icon);
            viewHolder.tv_name = (TextView) view.findViewById(R.id.tv_name);
            viewHolder.tv_time = (TextView) view.findViewById(R.id.tv_time);
            view.setTag(viewHolder);
        }else{
            viewHolder = (ViewHolder) view.getTag();
        }
        //根据位置得到数据
        MovieInfo.RowsBean rowsBean = mDatas.get(position);
        viewHolder.tv_name.setText(rowsBean.getSname());
        viewHolder.tv_time.setText(rowsBean.getStime());

        final String url = "http://www.7ifun.com/"+rowsBean.getSimg();
        //OKHttp获取图片显示
        /*OkHttpUtils
                .get()//
                .url(url)//
                .tag(this)//
                .build()//
                .connTimeOut(20000)
                .readTimeOut(20000)
                .writeTimeOut(20000)
                .execute(new BitmapCallback()
                {
                    @Override
                    public void onError(Call call, Exception e, int id)
                    {
                        //tv_show.setText("onError:" + e.getMessage());
                    }

                    @Override
                    public void onResponse(Bitmap bitmap, int id)
                    {
                        Log.e("TAG", "onResponse：complete"+url);
                        viewHolder.iv_icon.setImageBitmap(bitmap);
                    }
                });*/
        //Glide获取图片显示
        Glide.with(mContext).load(url).into(viewHolder.iv_icon);
        return view;
    }
    static  class ViewHolder{
        private ImageView iv_icon;
        private TextView tv_name,tv_time;

    }
}
