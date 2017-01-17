package hui.a7ifun.com.a7ifun.butterknife.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import hui.a7ifun.com.a7ifun.R;

public class ButterKnifeActivity extends AppCompatActivity {

    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tv_butterknife)
    TextView tvButterknife;
    @BindView(R.id.cb_butterknife)
    CheckBox cbButterknife;
    @BindView(R.id.btn_butterknife)
    Button btnButterknife;
    @BindView(R.id.activity_butter_knife)
    LinearLayout activityButterKnife;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_butter_knife);
        ButterKnife.bind(this);
        initData();
    }

    private void initData()
    {
        tvTitle.setText("Butterknife的使用");
        tvButterknife.setText("我好喜欢bbutterknife....");
        cbButterknife.setText("复选框点击");
        btnButterknife.setText("按钮点击");
    }
    @OnClick(R.id.cb_butterknife)
    void cbButterknife(View view){
        Toast.makeText(this, "So Good", Toast.LENGTH_SHORT).show();
    }

    @OnClick(R.id.btn_butterknife)
    void btButterknife(View view){
        Toast.makeText(this, "Good,Good...", Toast.LENGTH_SHORT).show();
    }
}
