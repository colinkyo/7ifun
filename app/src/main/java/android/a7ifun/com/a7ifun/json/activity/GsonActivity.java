package android.a7ifun.com.a7ifun.json.activity;

import android.a7ifun.com.a7ifun.R;
import android.a7ifun.com.a7ifun.json.bean.ShopInfo;
import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.List;

public class GsonActivity extends Activity implements View.OnClickListener {
    private Button btn_jsonObject2JavaObject, btn_jsonList2JavaMap, btn_tjsonparse, btn_sjsonparse;
    private TextView tv_before, tv_after, tv_title;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gson);
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
        tv_title.setText("Gson解析");
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
                jsonToJavaObjectByGson();
                break;
            case R.id.btn_jsonList2JavaMap:
                jsonToJavaListByGson();
                break;
            case R.id.btn_tjsonparse:
                javaToJsonObjectByGson();
                break;
            case R.id.btn_sjsonparse:
                javaToJsonArrayByGson();
                break;
        }
    }

    private void javaToJsonArrayByGson()
    {
//        获取或者创建JAVA对象
        List<ShopInfo> shops = new ArrayList<>();
        ShopInfo shop1= new ShopInfo(1,12f,"good","http://www.baidu.com/1.jpg");
        ShopInfo shop2= new ShopInfo(2,20f,"bad","http://www.baidu.com/1.jpg");
        shops.add(shop1);
        shops.add(shop2);
//        生成JSON数据
        Gson gson = new Gson();
        String json = gson.toJson(shops);
//        显示JSON数据
        tv_before.setText(shops.toString());
        tv_after.setText(json);
    }

    private void javaToJsonObjectByGson()
    {
//        获取或者创建JAVA对象
        ShopInfo shopInfo = new ShopInfo(1, 30f, "Good", "http://www.baidu.com/1.jpg");
//        生成JSON数据
        Gson gson = new Gson();
        String json = gson.toJson(shopInfo);
//        显示JSON数据
        tv_before.setText(shopInfo.toString());
        tv_after.setText(json);
    }

    private void jsonToJavaListByGson()
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
        Gson gson = new Gson();
        List<ShopInfo> shops= gson.fromJson(json, new TypeToken<List<ShopInfo>>(){}.getType());
//        显示JSON数据
        tv_before.setText(json);
        tv_after.setText(shops.toString());
    }

    private void jsonToJavaObjectByGson()
    {
//        获取JSON数据
        String json = "{\n" +
                "    \"id\": 2,\n" +
                "    \"name\": \"7Yan\",\n" +
                "    \"price\": 12.3,\n" +
                "    \"imagePath\": \"http: //www.7ifun.com/images/1.png\"\n" +
                "}";
//        解析JSON数据
        Gson gson = new Gson();
        ShopInfo shopInfo = gson.fromJson(json, ShopInfo.class);
//        显示JSON数据
        tv_before.setText(json);
        tv_after.setText(shopInfo.toString());
    }
}
