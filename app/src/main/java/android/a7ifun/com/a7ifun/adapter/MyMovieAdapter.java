package android.a7ifun.com.a7ifun.adapter;

import android.a7ifun.com.a7ifun.R;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Map;

/**
 * Created by 7Yan on 2017/1/11.
 */

public class MyMovieAdapter extends BaseAdapter {

    private final Context mContext;
    private final ArrayList<Map<String,String>> mDatas;
   //private final ArrayList<Map> mDatas;

    //public MyMovieAdapter(Context context, ArrayList<Map> datas)
    public MyMovieAdapter(Context context, ArrayList<Map<String,String>> datas)
    {
        this.mContext = context;
        this.mDatas =datas;
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
    public View getView(int position, View convertView, ViewGroup parent)
    {
        ViewHodle viewHodle;
        if(convertView == null)
        {
            convertView = View.inflate(mContext, R.layout.item_movie_list,null);
            viewHodle = new ViewHodle();
            //注意和布局文件的ID 对应好，不然报错
            viewHodle.iv_movie_logo = (ImageView) convertView.findViewById(R.id.iv_movie_logo);
            viewHodle.tv_name = (TextView) convertView.findViewById(R.id.tv_movie_name);
            viewHodle.tv_time = (TextView) convertView.findViewById(R.id.tv_movie_time);

            convertView.setTag(viewHodle);
        }else{
            viewHodle = (ViewHodle) convertView.getTag();
        }
        //根据位置绑定数据

        viewHodle.tv_name.setText((CharSequence) mDatas.get(position).get("name"));
        viewHodle.tv_time.setText((CharSequence) mDatas.get(position).get("time"));
        return convertView;
    }
    static  class ViewHodle
    {
       private ImageView iv_movie_logo;
       private TextView tv_name;
       private TextView tv_time;
    }
}
