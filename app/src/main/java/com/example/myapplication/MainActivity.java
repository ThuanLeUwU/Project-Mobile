package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import me.relex.circleindicator.CircleIndicator;

public class MainActivity extends AppCompatActivity {

    private ViewPager viewPager;
    private CircleIndicator circleIndicator;
    private ImageAdapter imageAdapter;
    private List<Image> mListImage;
    private Timer mTimer;
    private TextView cart;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.fragment_carousel);

        viewPager = findViewById(R.id.viewpager);
        circleIndicator = findViewById(R.id.circle_indicator);
        mListImage = getListImage();

        imageAdapter = new ImageAdapter(this, mListImage);
        viewPager.setAdapter(imageAdapter);

        circleIndicator.setViewPager(viewPager);
        imageAdapter.registerDataSetObserver(circleIndicator.getDataSetObserver());

        autoSlideImages();

        cart = (TextView) findViewById(R.id.shoppingCart);
        cart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,Cart_ProductsList.class);
                startActivity(intent);
            }
        });
    }

    private List<Image> getListImage() {
        List<Image> list = new ArrayList<>();
        list.add(new Image(R.drawable.image_3));
        list.add(new Image(R.drawable.image_3));
        list.add(new Image(R.drawable.image_3));
//        list.add(new Image(R.drawable.image_4));

        return list;
    }

    private void autoSlideImages() {
        if (mListImage == null || mListImage.isEmpty() || viewPager == null) {
            return;
        }

        //init
        if (mTimer == null) {
            mTimer = new Timer();
        }

        mTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                new Handler(Looper.getMainLooper()).post(new Runnable() {
                    @Override
                    public void run() {
                            int currentItem = viewPager.getCurrentItem();
                            int totalItem = mListImage.size() - 1;
                            if (currentItem < totalItem)  {
                                currentItem ++;
                                viewPager.setCurrentItem(currentItem);
                            } else {
                                viewPager.setCurrentItem(0);
                            }
                    }
                });
            }
        },500,3000);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mTimer != null) {
            mTimer.cancel();
            mTimer = null;
        }
    }


}