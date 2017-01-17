package hui.a7ifun.com.a7ifun.eventbus;

import hui.a7ifun.com.a7ifun.eventbus.event.MessageEvent;
import hui.a7ifun.com.a7ifun.eventbus.event.StickyEvent;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

public class EventBusActivity extends Activity implements View.OnClickListener {
    private Button btn_1,btn_2;
    private TextView tv_title,tv_result;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(hui.a7ifun.com.a7ifun.R.layout.activity_event_bus);

        initView();
        initData();
        initListener();
    }

    private void initListener() {
        btn_1.setOnClickListener(this);
        btn_2.setOnClickListener(this);
    }

    private void initData() {
        tv_title.setText("EventBus 发送数据页面");
        //1:注册广播
        EventBus.getDefault().register(EventBusActivity.this);
    }
    @Override
    protected void onDestroy()
    {
        super.onDestroy();
        //2:关闭广播
        EventBus.getDefault().unregister(EventBusActivity.this);
    }
    private void initView() {
        btn_1 = (Button) findViewById(hui.a7ifun.com.a7ifun.R.id.btn_1);
        btn_2 = (Button) findViewById(hui.a7ifun.com.a7ifun.R.id.btn_2);
        tv_title = (TextView) findViewById(hui.a7ifun.com.a7ifun.R.id.tv_title);
        tv_result = (TextView) findViewById(hui.a7ifun.com.a7ifun.R.id.tv_result);
    }


    //5 接收消息
    @Subscribe(threadMode = ThreadMode.MAIN)
    public  void  MessageEventBus(MessageEvent messageEvent)
    {
        Toast.makeText(this, "good", Toast.LENGTH_SHORT).show();
        tv_result.setText(messageEvent.name);
    }
    @Override
    public void onClick(View view)
    {
        switch (view.getId()){
            case hui.a7ifun.com.a7ifun.R.id.btn_1:
                startActivity(new Intent(EventBusActivity.this,EventBusSendActivity.class));
                break;
            case hui.a7ifun.com.a7ifun.R.id.btn_2:
                // 2 - 2 发送粘性事件
                EventBus.getDefault().postSticky(new StickyEvent("我是粘性事件...."));
                startActivity(new Intent(EventBusActivity.this,EventBusSendActivity.class));
                break;
        }
    }
}
