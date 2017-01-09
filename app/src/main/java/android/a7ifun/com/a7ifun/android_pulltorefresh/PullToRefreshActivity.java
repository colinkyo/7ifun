package android.a7ifun.com.a7ifun.android_pulltorefresh;

import android.a7ifun.com.a7ifun.R;
import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import static android.a7ifun.com.a7ifun.R.id.tv_title;

public class PullToRefreshActivity extends Activity implements View.OnClickListener
{
    private Button btn_1,btn_2,btn_3,btn_4,btn_5,btn_6;
    private TextView tv_title;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pull_to_refresh);
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
        btn_1 = (Button) findViewById(R.id.btn_1);
        btn_2 = (Button) findViewById(R.id.btn_2);
        btn_3 = (Button) findViewById(R.id.btn_3);
        btn_4 = (Button) findViewById(R.id.btn_4);
        btn_5 = (Button) findViewById(R.id.btn_5);
        btn_6 = (Button) findViewById(R.id.btn_6);

        tv_title =(TextView) findViewById(R.id.tv_title);

    }
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_1:

                break;
            case R.id.btn_2:

                break;
            case R.id.btn_3:

                break;
            case R.id.btn_4:

                break;
            case R.id.btn_5:

                break;
            case R.id.btn_6:

                break;

        }
    }
}
