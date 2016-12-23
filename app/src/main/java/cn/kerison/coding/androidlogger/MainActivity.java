package cn.kerison.coding.androidlogger;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import cn.kerison.coding.util.KL;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        showLog();
    }

    private void showLog() {

        KL.config(BuildConfig.DEBUG).setTag("DEBUG").setTextLevel(Log.WARN).setMethodCount(1);
        KL.i();

        String name = "WORLD";
        String json = " {\"citys\": [\n" +
                "        {\n" +
                "          \"id\": \"110000\",\n" +
                "          \"full_name\": \"北京市\"\n" +
                "        },\n" +
                "        {\n" +
                "          \"id\": \"120000\",\n" +
                "          \"full_name\": \"天津市\"\n" +
                "        }\n" +
                "      ]}";

        String xml = "<manifest package=\"cn.kerison.kit.log\"\n" +
                "          xmlns:android=\"http://schemas.android.com/apk/res/android\">\n" +
                "    <application />\n" +
                "</manifest>\n";

        KL.v("hello %s ", name);
        KL.d("hello %s ", name);
        KL.i("hello %s ", name);
        KL.w("hello %s ", name);
        KL.e("hello %s ", name);
        KL.wtf("hello %s ", name);
        KL.json(json);
        KL.xml(xml);
    }
}
