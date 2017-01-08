package android.a7ifun.com.a7ifun;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class LauncherActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_launcher);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                //在主线程中执行
                startMainActivity();
            }
        }, 1000);
    }

    private void startMainActivity()
    {
        Intent intent =new Intent(LauncherActivity.this,MainActivity.class);
        startActivity(intent);
        //关闭自己
        finish();
    }
}
