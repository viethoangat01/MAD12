package com.example.mad12.view.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import com.example.mad12.R;
import com.example.mad12.view.adapter.ViewPagerAdapter;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {
    private BottomNavigationView navigationView;
    private ViewPager viewPager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Khởi tạo giao diện
        initView();
        //Khởi tạo view Pager
        setUpViewPager();
        //Bắt sụ kiện với navigationView
        navigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.item_main_home:
                        viewPager.setCurrentItem(0);
                        break;
                    case R.id.item_main_selflearning:
                        viewPager.setCurrentItem(1);
                        break;
                    case R.id.item_main_notification:
                        viewPager.setCurrentItem(2);
                        break;
                    case R.id.item_main_document:
                        viewPager.setCurrentItem(3);
                        break;
                }
                return true;
            }
        });
    }

    private void initView() {
        navigationView=(BottomNavigationView) findViewById(R.id.bottomnavigationview_main);
        viewPager=(ViewPager) findViewById(R.id.viewpager_main);
    }

    private void setUpViewPager(){
        ViewPagerAdapter viewPagerAdapter=new ViewPagerAdapter(getSupportFragmentManager(), FragmentStatePagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        viewPager.setAdapter(viewPagerAdapter);

        //Xử lý event vuốt chuyển đổi các viewpager
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                switch (position){
                    case 0:
                        navigationView.getMenu().findItem(R.id.item_main_home).setChecked(true);
                        break;
                    case 1:
                        navigationView.getMenu().findItem(R.id.item_main_selflearning).setChecked(true);
                        break;
                    case 2:
                        navigationView.getMenu().findItem(R.id.item_main_notification).setChecked(true);
                        break;
                    case 3:
                        navigationView.getMenu().findItem(R.id.item_main_document).setChecked(true);
                        break;
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }
}
