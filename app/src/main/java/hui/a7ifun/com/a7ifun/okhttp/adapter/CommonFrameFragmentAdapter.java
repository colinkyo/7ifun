package hui.a7ifun.com.a7ifun.okhttp.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

/**
 * Created by 7yan on 2017/1/7.
 */

public class CommonFrameFragmentAdapter extends BaseAdapter {
    private Context mContext;
    private String[] mData;
    public CommonFrameFragmentAdapter(Context context,String[] datas)
    {
        this.mContext = context;
        this.mData = datas;
    }

    @Override
    public int getCount() {
        return mData.length;
    }

    @Override
    public Object getItem(int i) {
        return mData[i];
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup)
    {
        TextView textView = new TextView(mContext);
        textView.setPadding(10,10,0,10);
        textView.setTextColor(Color.BLACK);
        textView.setTextSize(20);
        textView.setText(mData[i]);
        return textView;
    }
}
