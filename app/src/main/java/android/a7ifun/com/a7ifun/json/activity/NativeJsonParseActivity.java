package android.a7ifun.com.a7ifun.json.activity;

import android.a7ifun.com.a7ifun.R;
import android.a7ifun.com.a7ifun.json.bean.FilmInfo;
import android.a7ifun.com.a7ifun.json.bean.MovieInfo;
import android.a7ifun.com.a7ifun.json.bean.ShopInfo;
import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import static android.a7ifun.com.a7ifun.json.bean.FilmInfo.*;

public class NativeJsonParseActivity extends Activity implements View.OnClickListener {
    private Button btn_jsonObject2JavaObject, btn_jsonList2JavaMap, btn_tjsonparse, btn_sjsonparse;
    private TextView tv_before, tv_after, tv_title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_native_json_parse);

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
        tv_title.setText("JSON数据的解析");
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
                jsonToJavaObjectByNative();
                break;
            case R.id.btn_jsonList2JavaMap:
                jsonToJavaListByNative();
                break;
            case R.id.btn_tjsonparse:
                jsonToJavaOfComplex();
                break;
            case R.id.btn_sjsonparse:
                jsonToJavaOfSpecial();
                break;
        }
    }

    private void jsonToJavaOfSpecial()
    {
//       获取JSON数据
        String json = "{\n" +
                "    \"code\": 0,\n" +
                "    \"list\": {\n" +
                "        \"0\": {\n" +
                "            \"aid\": \"12121\",\n" +
                "            \"author\": \"dsfdsfdsa\",\n" +
                "            \"coins\": 170,\n" +
                "            \"copyright\": \"Copy\",\n" +
                "            \"create\": \"2017-01-08\"\n" +
                "        },\n" +
                "        \"1\": {\n" +
                "            \"aid\": \"7657\",\n" +
                "            \"author\": \"yoiouoere\",\n" +
                "            \"coins\": 404,\n" +
                "            \"copyright\": \"Copy\",\n" +
                "            \"create\": \"2017-02-08\"\n" +
                "        }\n" +
                "    }\n" +
                "}";
        FilmInfo filmInfo = new FilmInfo();
//       解析JSON数据
        try {
            JSONObject jsonObject = new JSONObject(json);
//          解析第一层
            int code = jsonObject.optInt("code");
            JSONObject list = jsonObject.getJSONObject("list");
//          第一层封装
            filmInfo.setCode(code);
            List<FilmInfo.FilmBean> lists = new ArrayList<>();
            filmInfo.setList(lists);
//          解析第二层
            for(int i=0;i<list.length();i++)
            {
                JSONObject jsonObject1 = list.optJSONObject(i + "");
                if(jsonObject1 != null)
                {
                    String aid = jsonObject1.optString("aid");
                    String author = jsonObject1.optString("author");
                    int coins = jsonObject1.optInt("coins");
                    String copyright = jsonObject1.optString("copyright");
                    String create = jsonObject1.optString("create");
//                    第二层数据封装
                    FilmInfo.FilmBean filmBean = new FilmInfo.FilmBean();
                    filmBean.setAid(aid);
                    filmBean.setAuthor(author);
                    filmBean.setCoins(coins);
                    filmBean.setCopyright(copyright);
                    filmBean.setCreate(create);

                    lists.add(filmBean);

                }
            }
            tv_before.setText(json);
            tv_after.setText(filmInfo.toString());

        } catch (JSONException e) {
            e.printStackTrace();
        }
//       显示JSON数据
    }

    private void jsonToJavaOfComplex() {
//        https://api.douban.com/v2/book/1220562
//        获取JSON数据
        String json = "{\n" +
                "    \"id\":\"1220562\",\n" +
                "    \"alt\":\"https:\\/\\/book.douban.com\\/book\\/1220562\",\n" +
                "    \"rating\":{\"max\":10, \"average\":\"7.0\", \"numRaters\":282, \"min\":0},\n" +
                "    \"author\":[{\"name\":\"片山恭一\"}, {\"name\":\"豫人\"}],\n" +
                "    \"alt_title\":\"\",\n" +
                "    \"image\":\"https://img3.doubanio.com\\/spic\\/s1747553.jpg\",\n" +
                "    \"title\":\"满月之夜白鲸现\",\n" +
                "    \"mobile_link\":\"https:\\/\\/m.douban.com\\/book\\/subject\\/1220562\\/\",\n" +
                "    \"summary\":\"那一年，是听莫扎特、钓鲈鱼和家庭破裂的一年。说到家庭破裂，母亲怪自己当初没有找到好男人，父亲则认为当时是被狐狸精迷住了眼，失常的是母亲，但出问题的是父亲……。\",\n" +
                "    \"attrs\":{ \n" +
                "       \"publisher\":[\"青岛出版社\"],\n" +
                "        \"pubdate\":[\"2005-01-01\"],\n" +
                "        \"author\":[\"片山恭一\", \"豫人\"],\n" +
                "        \"price\":[\"18.00元\"],\n" +
                "        \"title\":[\"满月之夜白鲸现\"],\n" +
                "        \"binding\":[\"平装(无盘)\"],\n" +
                "        \"translator\":[\"豫人\"],\n" +
                "        \"pages\":[\"180\"]\n" +
                "    },\n" +
                "    \"tags\":[\n" +
                "        {\"count\":106, \"name\":\"片山恭一\"},\n" +
                "        {\"count\":50, \"name\":\"日本\"},\n" +
                "        {\"count\":42, \"name\":\"日本文学\"},\n" +
                "        {\"count\":30, \"name\":\"满月之夜白鲸现\"},\n" +
                "        {\"count\":28, \"name\":\"小说\"},\n" +
                "        {\"count\":10, \"name\":\"爱情\"},\n" +
                "        {\"count\":7, \"name\":\"純愛\"},\n" +
                "        {\"count\":6, \"name\":\"外国文学\"}\n" +
                "    ]\n" +
                "}";
//        解析JSON数据
        MovieInfo movieInfo = new MovieInfo();
        try {
            JSONObject jsonObject = new JSONObject(json);
//            第一层
            JSONObject rating = jsonObject.optJSONObject("rating");
            JSONObject attrs = jsonObject.optJSONObject("attrs");
//            电影名称
            String title = jsonObject.optString("title");
//            电影封面
            String image = jsonObject.optString("image");
//            第一层封装
//            把数据添加到bean中去
            movieInfo.setTitle(title);
            movieInfo.setImage(image);
            MovieInfo.RatingBean ratingbean = new MovieInfo.RatingBean();
            movieInfo.setRating(ratingbean);
            MovieInfo.AttrsBean attrsbean = new MovieInfo.AttrsBean();
            movieInfo.setAttrs(attrsbean);
//            第二层
            JSONArray author = attrs.getJSONArray("author");
//            第二层数据封装
            List<String> authorlist = new ArrayList<>();
            attrsbean.setAuthor(authorlist);
//            把数据添加到bean中去
            ratingbean.setAverage(rating.optString("average"));
            ratingbean.setMax(rating.optInt("max"));
            ratingbean.setMin(rating.optInt("mix"));
            ratingbean.setNumRaters(rating.optInt("numRaters"));
//            第三层
            for (int i = 0; i < author.length(); i++) {

//              author.getJSONObject()
//              authorlist.get(i);
                //authorlist.set(i,author.getString(1));
//              把数据添加到bean中去
                authorlist.add(author.getString(i));
                Toast.makeText(this, author.getString(i), Toast.LENGTH_SHORT).show();
            }
            tv_before.setText(json);
            tv_after.setText(movieInfo.toString());
        } catch (JSONException e) {
            e.printStackTrace();
        }
//        显示JSOn数据

    }

    private void jsonToJavaListByNative() {
        //获取JSON数据
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
        List<ShopInfo> shops = new ArrayList<>();
        //解析JSON数据
        try {
            JSONArray jsonArray = new JSONArray(json);
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                if (jsonObject != null) {
                    int id = jsonObject.optInt("id");
                    String name = jsonObject.optString("name");
                    double price = jsonObject.optDouble("price");
                    String imagePath = jsonObject.optString("imagePath");

                    ShopInfo shopInfo = new ShopInfo(id, price, name, imagePath);
                    shops.add(shopInfo);
                }
            }
            tv_before.setText(json);
            tv_after.setText(shops.toString());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        //显示JSON数据
    }

    private void jsonToJavaObjectByNative() {
        String json = "{\n" +
                "    \"id\": 2,\n" +
                "    \"name\": \"7Yan\",\n" +
                "    \"price\": 12.3,\n" +
                "    \"imagePath\": \"http: //www.7ifun.com/images/1.png\"\n" +
                "}";
        try {
            JSONObject jsonObject = new JSONObject(json);
            int id = jsonObject.getInt("id");//会报空指针异常
            int id1 = jsonObject.optInt("id");//常用这个取值
            String name = jsonObject.optString("name");
            double price = jsonObject.optDouble("price");
            String imagePath = jsonObject.optString("imagePath");
            tv_before.setText(json);
            ShopInfo shopInfo = new ShopInfo(id, price, name, imagePath);
            //Toast.makeText(this, shopInfo.toString(), Toast.LENGTH_SHORT).show();
            tv_after.setText(shopInfo.toString());
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
