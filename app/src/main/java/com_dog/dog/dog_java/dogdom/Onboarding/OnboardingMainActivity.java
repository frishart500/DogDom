package com_dog.dog.dog_java.dogdom.Onboarding;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;

import com_dog.dog.dog_java.dogdom.Auth.LoginActivity;
import com_dog.dog.dog_java.dogdom.R;

public class OnboardingMainActivity extends AppCompatActivity {

    private AdapterOnBoarding viewPagerAdapter;
    private ViewPager viewPager;
    private TabLayout tabLayout;
    private TextView logoText, skipText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_onboarding_main);

        init();

        // setting up the adapter
        viewPagerAdapter = new AdapterOnBoarding(getSupportFragmentManager());

        // add the fragments
        viewPagerAdapter.add(new Step1Fragment(), "");
        viewPagerAdapter.add(new Step2Fragment(), "");
        viewPagerAdapter.add(new ChoiseFragment(), "");

        // Set the adapter
        viewPager.setAdapter(viewPagerAdapter);

        // The Page (fragment) titles will be displayed in the
        // tabLayout hence i need to  set the page viewer
        // i use the setupWithViewPager().

        tabLayout.setupWithViewPager(viewPager);

        skipText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), LoginActivity.class));
            }
        });

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageSelected(int position) {
                if (position == 0) {
                    logoText.setVisibility(View.VISIBLE);
                    skipText.setVisibility(View.VISIBLE);
                } else if (position == 1) {
                    logoText.setVisibility(View.VISIBLE);
                    skipText.setVisibility(View.VISIBLE);
                } else {
                    skipText.setVisibility(View.GONE);
                    logoText.setVisibility(View.GONE);
                }
            }

            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });


        /*nextBtn.setOnClickListener(new View.OnClickListener() {
            int position = viewPager.getCurrentItem();
            @Override
            public void onClick(View v) {
                if (position == 0) {
                    viewPager.setCurrentItem(1);
                    nextBtn.show();
                }else if (position == 1) {
                    viewPager.setCurrentItem(2);
                    nextBtn.show();
                }
            }
        });*/


    }

    private void init() {
        //nextBtn = findViewById(R.id.next_btn);
        viewPager = findViewById(R.id.viewpager_slides);
        skipText = findViewById(R.id.text_skip);
        logoText = findViewById(R.id.text_logo);
        tabLayout = findViewById(R.id.tab_indicator);
    }

}