package com.coolweather.app.activity;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.coolweather.app.R;

public class MainActivity extends Activity {
    private Fragment1 fragment1;
    private Fragment2 fragment2;
    private Fragment3 fragment3;
    private RadioGroup radioGroup;
    private RadioButton radioButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //全屏显示，显示时间和电量
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LOW_PROFILE);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //开启事务管理，主要处理Fragment
        //manager = getFragmentManager();
        // transaction = manager.beginTransaction();

        //设置切换Fragment
        radioGroup = (RadioGroup)findViewById(R.id.radiogroup);
        RadioGroupList radigGroupList = new RadioGroupList();
        radioGroup.setOnCheckedChangeListener(radigGroupList);

        //设置默认按钮为选中状态
        radioButton =(RadioButton) findViewById(R.id.btn_0);
        radioButton.setChecked(true);


        //开始处理Fragment
        fragment1 = new Fragment1();
        fragment2 = new Fragment2();
        fragment3 = new Fragment3();

        findViewById(R.id.fragment_1).setVisibility(View.VISIBLE);
        findViewById(R.id.fragment_2).setVisibility(View.INVISIBLE);
        findViewById(R.id.fragment_3).setVisibility(View.INVISIBLE);
    }
//    @Override
//    public boolean onCreateOptionsMenu(Menu menu)
//    {
//        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.main, menu);
//        return true;
//    }
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item)
//    {
//        // Handle action bar item clicks here. The action bar will
//        // automatically handle clicks on the Home/Up button, so long
//        // as you specify a parent activity in AndroidManifest.xml.
//        int id = item.getItemId();
//        if (id == R.id.action_settings)
//        {
//            return true;
//        }
//        return super.onOptionsItemSelected(item);
//    }
    public class RadioGroupList implements RadioGroup.OnCheckedChangeListener
    {

        @Override
        public void onCheckedChanged(RadioGroup group, int checkedId)
        {
            if(group.getId() == R.id.radiogroup)
            {
                switch (checkedId)
                {
                    case R.id.btn_0:
                        findViewById(R.id.fragment_1).setVisibility(View.VISIBLE);
                        findViewById(R.id.fragment_2).setVisibility(View.INVISIBLE);
                        findViewById(R.id.fragment_3).setVisibility(View.INVISIBLE);
                        Log.d("首页", "提示");
                        break;
                    case R.id.btn_1:
                        findViewById(R.id.fragment_1).setVisibility(View.INVISIBLE);
                        findViewById(R.id.fragment_2).setVisibility(View.VISIBLE );
                        findViewById(R.id.fragment_3).setVisibility(View.INVISIBLE);
                        Log.d("咨询", "提示");
                        break;
                    case R.id.btn_2:
                        findViewById(R.id.fragment_1).setVisibility(View.INVISIBLE);
                        findViewById(R.id.fragment_2).setVisibility(View.INVISIBLE);
                        findViewById(R.id.fragment_3).setVisibility(View.VISIBLE);
                        Log.d("我的", "提示");
                        break;
                    default :
                        break;
                }
            }
        }
    }
}
