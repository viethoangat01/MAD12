package com.example.mad12.view.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import com.example.mad12.R;
import com.example.mad12.utils.Session;
import com.example.mad12.view.adapter.ViewPagerAdapter;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

import de.hdodenhof.circleimageview.CircleImageView;

public class MainActivity extends AppCompatActivity {
    private BottomNavigationView navigationView;
    private ViewPager viewPager;
    private CircleImageView imgPhoto;

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

    @Override
    protected void onStart() {
        super.onStart();
        new loadPhotoProfile().execute(Session.read(getApplicationContext(),"personPhoto",""));
    }
    //Load ảnh đại diện google
    private  class  loadPhotoProfile extends AsyncTask<String,Void, Bitmap> {

        @Override
        protected Bitmap doInBackground(String... strings) {
            Bitmap bitmap=null;
            try {
                URL url=new URL(strings[0]);
                InputStream inputStream=url.openConnection().getInputStream();
                bitmap= BitmapFactory.decodeStream(inputStream);
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return bitmap;
        }

        @Override
        protected void onPostExecute(Bitmap bitmap) {
            super.onPostExecute(bitmap);
            imgPhoto.setImageBitmap(bitmap);
        }
    }

    private void initView() {
        navigationView=(BottomNavigationView) findViewById(R.id.bottomnavigationview_main);
        viewPager=(ViewPager) findViewById(R.id.viewpager_main);
        imgPhoto=(CircleImageView) findViewById(R.id.imageview_main_profile);
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
