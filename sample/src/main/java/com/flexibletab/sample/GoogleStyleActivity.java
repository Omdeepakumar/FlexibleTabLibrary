package com.flexibletab.sample;

import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.viewpager.widget.ViewPager;

import com.flexibletab.FlexibleTabLayout;
import com.flexibletab.model.TabType;

/**
 * Demonstrates Google-style tabs.
 * Features: Text tabs with material underline indicator, ViewPager integration
 */
public class GoogleStyleActivity extends AppCompatActivity {
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        LinearLayout layout = new LinearLayout(this);
        layout.setOrientation(LinearLayout.VERTICAL);
        
        // Title
        TextView title = new TextView(this);
        title.setText("Google Style Tabs");
        title.setTextSize(20);
        title.setPadding(dpToPx(16), dpToPx(16), dpToPx(16), dpToPx(8));
        layout.addView(title);
        
        // Description
        TextView desc = new TextView(this);
        desc.setText("Text tabs with material indicator - typical app navigation");
        desc.setPadding(dpToPx(16), 0, dpToPx(16), dpToPx(16));
        layout.addView(desc);
        
        // Create Google-style tab layout
        FlexibleTabLayout tabLayout = new FlexibleTabLayout(this);
        tabLayout.setTabType(TabType.MATERIAL);
        tabLayout.setTheme("google");
        tabLayout.setTabMode(FlexibleTabLayout.MODE_FIXED);
        
        // Add tabs
        tabLayout.addTab("HOME");
        tabLayout.addTab("TRENDING");
        tabLayout.addTab("SUBSCRIPTIONS");
        tabLayout.addTab("LIBRARY");
        
        LinearLayout.LayoutParams tabParams = new LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.MATCH_PARENT,
            dpToPx(48)
        );
        layout.addView(tabLayout, tabParams);
        
        // ViewPager for content
        ViewPager viewPager = new ViewPager(this);
        viewPager.setAdapter(new SimplePagerAdapter(getSupportFragmentManager(), 
            new String[]{"Home", "Trending", "Subscriptions", "Library"}));
        
        // Attach tab layout to view pager
        tabLayout.attachToViewPager(viewPager);
        
        LinearLayout.LayoutParams pagerParams = new LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.MATCH_PARENT,
            0,
            1
        );
        layout.addView(viewPager, pagerParams);
        
        setContentView(layout);
    }
    
    private int dpToPx(int dp) {
        return (int) (dp * getResources().getDisplayMetrics().density);
    }
}
