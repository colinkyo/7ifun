package hui.a7ifun.com.a7ifun.eventbus;

import hui.a7ifun.com.a7ifun.eventbus.event.MessageEvent;
import hui.a7ifun.com.a7ifun.eventbus.event.StickyEvent;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

public class EventBusSendActivity extends Activity implements View.OnClickListener {
    private Button btn_1,btn_2;
    private TextView tv_title,tv_result;
    boolean isClick =true;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(hui.a7ifun.com.a7ifun.R.layout.activity_event_bus_send);
        initView();
        initData();
        initListener();
    }
    private void initListener() {
        btn_1.setOnClickListener(this);
        btn_2.setOnClickListener(this);
    }

    private void initData() {
        tv_title.setText("EventBus 接收数据页面");
    }

    private void initView() {
        btn_1 = (Button) findViewById(hui.a7ifun.com.a7ifun.R.id.btn_1);
        btn_2 = (Button) findViewById(hui.a7ifun.com.a7ifun.R.id.btn_2);
        tv_title = (TextView) findViewById(hui.a7ifun.com.a7ifun.R.id.tv_title);
        tv_result = (TextView) findViewById(hui.a7ifun.com.a7ifun.R.id.tv_result);
    }

    @Override
    public void onClick(View view)
    {
        switch (view.getId()){
            case hui.a7ifun.com.a7ifun.R.id.btn_1:
                //4:发送消息 与 EventBusActivity 一共五步
                EventBus.getDefault().post(new MessageEvent("主线程发送过来的数据..."));
                //startActivity(new Intent(EventBusSendActivity.this,EventBusActivity.class));
                finish();
                break;
            case hui.a7ifun.com.a7ifun.R.id.btn_2:
                if(isClick) {
                    // 2 -4 注册粘性事件
                    EventBus.getDefault().register(EventBusSendActivity.this);
                    isClick =false;
                    Toast.makeText(this, "注册一次", Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        // 2 -4 注册粘性事件
        EventBus.getDefault().removeAllStickyEvents();
        EventBus.getDefault().unregister(EventBusSendActivity.this);
    }

    // 2 -3 接收消息
    @Subscribe(threadMode = ThreadMode.MAIN,sticky = true)
    public  void  StickEventBus(StickyEvent stickyEvent){
        tv_result.setText(stickyEvent.msg);
    }

}
