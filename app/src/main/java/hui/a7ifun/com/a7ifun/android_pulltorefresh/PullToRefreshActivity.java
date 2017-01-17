package hui.a7ifun.com.a7ifun.android_pulltorefresh;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class PullToRefreshActivity extends Activity implements View.OnClickListener
{
    private Button btn_1,btn_2,btn_3,btn_4,btn_5,btn_6;
    private TextView tv_title;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(hui.a7ifun.com.a7ifun.R.layout.activity_pull_to_refresh);
        initView();
        initData();
        initListener();
    }
    private void initListener() {
        btn_1.setOnClickListener(this);
        btn_2.setOnClickListener(this);
        btn_3.setOnClickListener(this);
        btn_4.setOnClickListener(this);
        btn_5.setOnClickListener(this);
        btn_6.setOnClickListener(this);
    }
    private void initData() {

        tv_title.setText("FastJson解析");
    }

    private void initView() {
        btn_1 = (Button) findViewById(hui.a7ifun.com.a7ifun.R.id.btn_1);
        btn_2 = (Button) findViewById(hui.a7ifun.com.a7ifun.R.id.btn_2);
        btn_3 = (Button) findViewById(hui.a7ifun.com.a7ifun.R.id.btn_3);
        btn_4 = (Button) findViewById(hui.a7ifun.com.a7ifun.R.id.btn_4);
        btn_5 = (Button) findViewById(hui.a7ifun.com.a7ifun.R.id.btn_5);
        btn_6 = (Button) findViewById(hui.a7ifun.com.a7ifun.R.id.btn_6);

        tv_title =(TextView) findViewById(hui.a7ifun.com.a7ifun.R.id.tv_title);

    }
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case hui.a7ifun.com.a7ifun.R.id.btn_1:

                break;
            case hui.a7ifun.com.a7ifun.R.id.btn_2:

                break;
            case hui.a7ifun.com.a7ifun.R.id.btn_3:

                break;
            case hui.a7ifun.com.a7ifun.R.id.btn_4:

                break;
            case hui.a7ifun.com.a7ifun.R.id.btn_5:

                break;
            case hui.a7ifun.com.a7ifun.R.id.btn_6:

                break;

        }
    }
}
