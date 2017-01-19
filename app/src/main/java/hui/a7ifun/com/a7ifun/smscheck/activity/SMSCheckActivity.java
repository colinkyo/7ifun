package hui.a7ifun.com.a7ifun.smscheck.activity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.taobao.api.ApiException;
import com.taobao.api.DefaultTaobaoClient;
import com.taobao.api.TaobaoClient;
import com.taobao.api.request.AlibabaAliqinFcSmsNumSendRequest;
import com.taobao.api.response.AlibabaAliqinFcSmsNumSendResponse;

import java.io.IOException;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import hui.a7ifun.com.a7ifun.R;
import hui.a7ifun.com.a7ifun.smscheck.AlidayuMessage;
import hui.a7ifun.com.a7ifun.smscheck.bean.AlidayuBean;

public class SMSCheckActivity extends AppCompatActivity {

    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.et_tel)
    EditText etTel;
    @BindView(R.id.et_code)
    EditText etCode;
    @BindView(R.id.btn_send)
    Button btnSend;
    @BindView(R.id.btn_check)
    Button btnCheck;
    @BindView(R.id.btn_parseJson)
    Button btnParseJson;

    private int numcode;
    private int time = 20; //设置一个倒计时时间
    private Handler handler = new Handler() {
        //      响应消息
        @Override
        public void handleMessage(Message msg) {
            //super.handleMessage(msg);
            switch (msg.what) {
                case 1:
//                发送正确响应
//                {"alibaba_aliqin_fc_sms_num_send_response":{"result":{"err_code":"0","model":"105730306040^1107775087847","success":true},"request_id":"12lp4otrq5z0e"}}
//                 异常响应
//                 {"error_response":{"code":15,"msg":"Remote service error","sub_code":"isv.MOBILE_NUMBER_ILLEGAL","sub_msg":"号码格式错误","request_id":"10fi1t1dl4k8c"}}
                    String json = msg.obj.toString();
                    AlidayuBean alidayuBean=JSON.parseObject(json, AlidayuBean.class);
                    AlidayuBean.AlibabaAliqinFcSmsNumSendResponseBean numSendResponse = alidayuBean.getAlibaba_aliqin_fc_sms_num_send_response();
                    String requestId = numSendResponse.getRequest_id();
                    AlidayuBean.AlibabaAliqinFcSmsNumSendResponseBean.ResultBean result = numSendResponse.getResult();
                    boolean b = result.getSuccess();
                    if(b)
                    {
                        Toast.makeText(SMSCheckActivity.this, "发送成功的", Toast.LENGTH_SHORT).show();
                    }
                    Toast.makeText(SMSCheckActivity.this, json, Toast.LENGTH_SHORT).show();
                    break;
            }
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_smscheck);
        ButterKnife.bind(this);
        initData();
    }

    private void initData() {
        tvTitle.setText("获取验证码测试");
    }

    @OnClick(R.id.btn_send)
    public void onClickSend() {
        final String tel = etTel.getText().toString();
        if (TextUtils.isEmpty(tel)) {
            Toast.makeText(this, "请先填写手机号码...", Toast.LENGTH_SHORT).show();
            return;
        }
        btnSend.setEnabled(false);
        new Thread(new Runnable() {
            public void run() {
                while (time > 0) {
                    time--;
//                  在Thread里面需要这样开runOnUiThread才可以更新UI
                    runOnUiThread(new Runnable() {
                        public void run() {
                            btnSend.setText("再次获取验证码（" + time + "s）");
                        }
                    });
                    try {
                        Thread.sleep(1000);
                    } catch (Exception e) {
                        // TODO: handle exception
                    }
                }
                if (time <= 0) {
//                  在Thread里面需要这样开runOnUiThread才可以更新UI
                    runOnUiThread(new Runnable() {
                        public void run() {
                            time = 20;
                            btnSend.setText("获取阿里大鱼验证码");
                            btnSend.setEnabled(true);
                        }
                    });
                }

            }
        }).start();
        // Android 4.0 之后不能在主线程中请求HTTP请求
        new Thread() {
            @Override
            public void run() {
                //getAlidayuCode();
                try {
                    AlidayuMessage alidayuMessage = new AlidayuMessage();
//                    设置接收手机号码
                    alidayuMessage.setRecNum(tel);
//                    设置验证码  {"number":"7454"}
                    numcode = (int) ((Math.random() * 9 + 1) * 100000);
                    String codePara = "{\"number\":\"" + numcode + "\"}";
                    alidayuMessage.setSmsParam(codePara);
//                    设置短信模版
                    alidayuMessage.setSmsTemplateCode("SMS_42285001");

                    String str = alidayuMessage.SendMsg();
                    //Message message = new Message();
                    Message message = Message.obtain();
//                  消息标记，响应时区分处理
                    message.what = 1;
                    message.obj = str;
                    handler.sendMessage(message);
                    Log.e("7Yan", "-------------------" + str + "-----------------");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }.start();
        //Toast.makeText(this, "So Good", Toast.LENGTH_SHORT).show();
    }

    //获取阿里大鱼验证码
    private void getAlidayuCode() {
        String tel = etTel.getText().toString();
        if (TextUtils.isEmpty(tel)) {
            Toast.makeText(this, "请先填写手机号码...", Toast.LENGTH_SHORT).show();
            return;
        }
        //调用阿里大鱼
        /*正式环境	http://gw.api.taobao.com/router/rest	https://eco.taobao.com/router/rest
        沙箱环境	http://gw.api.tbsandbox.com/router/rest	https://gw.api.tbsandbox.com/router/rest*/
        String url = "http://gw.api.taobao.com/router/rest";
        String appKey = "23609009";
        String appSecret = "ac37654e1ef72f8ef5e6e492bd707972";

        TaobaoClient client = new DefaultTaobaoClient(url, appKey, appSecret);
        AlibabaAliqinFcSmsNumSendRequest req = new AlibabaAliqinFcSmsNumSendRequest();
        req.setExtend("这个我也不知道是什么来的");
//      验证码类型
        req.setSmsType("normal");
//      短信签名【需要在阿里大鱼后台注册申请】
        req.setSmsFreeSignName("去分享");
//      随机生成4-6位验证码
        req.setSmsParamString("{number:'7454'}");
//      接收验证码手机
        req.setRecNum("1363237454");
//        req.setRecNum(tel);
//      短信模板内容：【需要在阿里大鱼后台注册申请】
//      验证码${number}，您正进行短信的身份验证，确认是自己操作！
        req.setSmsTemplateCode("SMS_42285001");
        try {
            AlibabaAliqinFcSmsNumSendResponse rsp = client.execute(req);
            Toast.makeText(this, rsp.getBody(), Toast.LENGTH_SHORT).show();
        } catch (ApiException e) {
            e.printStackTrace();
        }
    }

    //验证阿里大鱼验证码
    @OnClick(R.id.btn_check)
    public void onClickCheck() {
        String et_code = etCode.getText().toString();

        if (TextUtils.isEmpty(et_code)) {
            Toast.makeText(this, "请先验证码...", Toast.LENGTH_SHORT).show();
            return;
        }
        if (et_code.equals(String.valueOf(numcode))) {
            Toast.makeText(this, "验证码一致", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "验证码不一致", Toast.LENGTH_SHORT).show();
        }
    }

    @OnClick(R.id.btn_parseJson)
    public void onClickParseJson()
    {
//      发送正确响应
//      {"alibaba_aliqin_fc_sms_num_send_response":{"result":{"err_code":"0","model":"105730306040^1107775087847","success":true},"request_id":"12lp4otrq5z0e"}}
//      异常响应
//       {"error_response":{"code":15,"msg":"Remote service error","sub_code":"isv.MOBILE_NUMBER_ILLEGAL","sub_msg":"号码格式错误","request_id":"10fi1t1dl4k8c"}}
//      用到了FastJson
//      1：用 GSON 创建该JSON格式的bean
//        2:
        String json = "{\"alibaba_aliqin_fc_sms_num_send_response\":{\"result\":{\"err_code\":\"0\",\"model\":\"105730306040^1107775087847\",\"success\":true},\"request_id\":\"12lp4otrq5z0e\"}}";
        AlidayuBean alidayuBean=JSON.parseObject(json, AlidayuBean.class);
        AlidayuBean.AlibabaAliqinFcSmsNumSendResponseBean numSendResponse = alidayuBean.getAlibaba_aliqin_fc_sms_num_send_response();
        String requestId = numSendResponse.getRequest_id();
        AlidayuBean.AlibabaAliqinFcSmsNumSendResponseBean.ResultBean result = numSendResponse.getResult();
        boolean b = result.getSuccess();

        Toast.makeText(this, requestId + "=========" + b, Toast.LENGTH_SHORT).show();
    }

    class MyAsyncTask extends AsyncTask<String, Void, String> {
        @Override
        protected String doInBackground(String... params) {
            return null;
        }
    }
}
