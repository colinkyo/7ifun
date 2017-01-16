package android.a7ifun.com.a7ifun.adapter;

import android.a7ifun.com.a7ifun.R;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by 7Yan on 2017/1/16.
 */

public class lv_adapter extends BaseAdapter
{
    private Context mContext;
    private List<String> datas;

    public lv_adapter(Context mContext, List<String> datas)
    {
        this.mContext = mContext;
        this.datas = datas;
    }

    @Override
    public int getCount()
    {
        return datas.size();
    }

    @Override
    public Object getItem(int position)
    {
        return datas.get(position);
    }

    @Override
    public long getItemId(int position)
    {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {

        ViewHolder holder = null;
        if (convertView == null)
        {
            holder = new ViewHolder();
            convertView = LayoutInflater.from(mContext).inflate(R.layout.item_movie_list, null);

            holder.tv_movie_name = (TextView) convertView.findViewById(R.id.tv_movie_name);
            convertView.setTag(holder);
        } else
        {
            holder = (ViewHolder) convertView.getTag();
        }

        holder.tv_movie_name.setText(datas.get(position).toString());


        return convertView;
    }


    class ViewHolder
    {
        TextView tv_movie_name;
    }
}
