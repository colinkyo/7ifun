package android.a7ifun.com.a7ifun.json.activity;

import android.a7ifun.com.a7ifun.R;
import android.a7ifun.com.a7ifun.json.bean.ShopInfo;
import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;

import java.util.ArrayList;
import java.util.List;

public class FastJsonActivity extends Activity implements View.OnClickListener{
    private Button btn_jsonObject2JavaObject, btn_jsonList2JavaMap, btn_tjsonparse, btn_sjsonparse;
    private TextView tv_before, tv_after, tv_title;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fast_json);
        initView();
        initData();
        initListener();
    }

    private void initListener() {
        btn_sjsonparse.setOnClickListener(this);
        btn_tjsonparse.setOnClickListener(this);
        btn_jsonList2JavaMap.setOnClickListener(this);
        btn_jsonObject2JavaObject.setOnClickListener(this);
    }

    private void initData() {
        tv_title.setText("FastJson解析");
    }

    private void initView() {
        btn_jsonObject2JavaObject = (Button) findViewById(R.id.btn_jsonObject2JavaObject);
        btn_jsonList2JavaMap = (Button) findViewById(R.id.btn_jsonList2JavaMap);
        btn_tjsonparse = (Button) findViewById(R.id.btn_tjsonparse);
        btn_sjsonparse = (Button) findViewById(R.id.btn_sjsonparse);

        tv_before = (TextView) findViewById(R.id.tv_json_before);
        tv_after = (TextView) findViewById(R.id.tv_json_after);
        tv_title = (TextView) findViewById(R.id.tv_title);
    }

    @Override
    public void onClick(View view) {
        tv_before.setText("");
        tv_after.setText("");
        switch (view.getId()) {
            case R.id.btn_jsonObject2JavaObject:
                jsonToJavaObjectByFastJson();
                break;
            case R.id.btn_jsonList2JavaMap:
                jsonToJavaListByFastJson();
                break;
            case R.id.btn_tjsonparse:
                javaToJsonObjectByFastJson();
                break;
            case R.id.btn_sjsonparse:
                javaToJsonArrayByFastJson();
                break;
        }
    }

    private void javaToJsonArrayByFastJson() {
        List<ShopInfo> shops = new ArrayList<>();
        ShopInfo shopInfo1 = new ShopInfo(1, 1.2, "7yan", "1.jpg");
        ShopInfo shopInfo2 = new ShopInfo(2, 9.9, "So Good", "2.jpg");
        shops.add(shopInfo1);
        shops.add(shopInfo2);

        String jsonString = JSON.toJSONString(shops);

        tv_before.setText(shops.toString());
        tv_after.setText(jsonString);
    }

    private void javaToJsonObjectByFastJson()
    {
        ShopInfo shopInfo = new ShopInfo(1, 1.2, "7yan", "1.jpg");
        String jsonString = JSON.toJSONString(shopInfo);

        tv_before.setText(shopInfo.toString());
        tv_after.setText(jsonString);
    }

    private void jsonToJavaListByFastJson()
    {
//        获取JSON数据
        String json = "[\n" +
                "    {\n" +
                "        \"id\": 1,\n" +
                "        \"name\": \"7Yan\",\n" +
                "        \"price\": 12.3,\n" +
                "        \"imagePath\": \"http: //www.7ifun.com/images/1.png\"\n" +
                "    },\n" +
                "    {\n" +
                "        \"id\": 2,\n" +
                "        \"name\": \"7Yan\",\n" +
                "        \"price\": 52.3,\n" +
                "        \"imagePath\": \"http://www.7ifun.com/uploads/2016-11-10/2016111003111615853.jpg\"\n" +
                "    }\n" +
                "]";
//        解析JSON数据
        List<ShopInfo> list = JSON.parseArray(json, ShopInfo.class);
//        显示JSON数据
        tv_before.setText(json);
        tv_after.setText(list.toString());

    }

    private void jsonToJavaObjectByFastJson()
    {
//        获取JSON数据
        String json = "{\n" +
                "    \"id\": 2,\n" +
                "    \"name\": \"7Yan\",\n" +
                "    \"price\": 12.3,\n" +
                "    \"imagePath\": \"http: //www.7ifun.com/images/1.png\"\n" +
                "}";
//        解析JSON数据
        ShopInfo shopInfo = JSON.parseObject(json, ShopInfo.class);
//        显示JSON数据
        tv_before.setText(json);
        tv_after.setText(shopInfo.toString());
    }
}
