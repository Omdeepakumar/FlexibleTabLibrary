package com.flexibletab.sample;

import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.flexibletab.FlexibleTabLayout;
import com.flexibletab.listener.SimpleOnTabSelectedListener;
import com.flexibletab.model.TabItem;
import com.flexibletab.model.TabType;

/**
 * Demonstrates TikTok-style tabs.
 * Features: Bold text tabs with minimal underline indicator, dark theme
 */
public class TikTokStyleActivity extends AppCompatActivity {
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        LinearLayout layout = new LinearLayout(this);
        layout.setOrientation(LinearLayout.VERTICAL);
        layout.setBackgroundColor(android.graphics.Color.BLACK);
        
        // Title
        TextView title = new TextView(this);
        title.setText("TikTok Style Tabs");
        title.setTextSize(20);
        title.setTextColor(android.graphics.Color.WHITE);
        title.setPadding(dpToPx(16), dpToPx(16), dpToPx(16), dpToPx(8));
        layout.addView(title);
        
        // Description
        TextView desc = new TextView(this);
        desc.setText("Bold text tabs with minimal underline - dark theme");
        desc.setTextColor(android.graphics.Color.LTGRAY);
        desc.setPadding(dpToPx(16), 0, dpToPx(16), dpToPx(16));
        layout.addView(desc);
        
        // Create TikTok-style tab layout
        FlexibleTabLayout tabLayout = new FlexibleTabLayout(this);
        tabLayout.setTabType(TabType.TIKTOK);
        tabLayout.setTheme("tiktok");
        tabLayout.setTabMode(FlexibleTabLayout.MODE_FIXED);
        tabLayout.setTabTextSize(18);
        
        // Add tabs
        tabLayout.addTab("Following");
        tabLayout.addTab("For You");
        
        LinearLayout.LayoutParams tabParams = new LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.MATCH_PARENT,
            dpToPx(48)
        );
        layout.addView(tabLayout, tabParams);
        
        // Content area
        final TextView content = new TextView(this);
        content.setText("Following Feed");
        content.setTextSize(16);
        content.setTextColor(android.graphics.Color.WHITE);
        content.setPadding(dpToPx(16), dpToPx(32), dpToPx(16), dpToPx(16));
        content.setGravity(android.view.Gravity.CENTER);
        layout.addView(content);
        
        // Update content on tab change
        tabLayout.setOnTabSelectedListener(new SimpleOnTabSelectedListener() {
            @Override
            public void onTabSelected(TabItem tab, int position) {
                String[] contents = {"Following Feed", "For You Feed"};
                content.setText(contents[position]);
            }
        });
        
        setContentView(layout);
    }
    
    private int dpToPx(int dp) {
        return (int) (dp * getResources().getDisplayMetrics().density);
    }
}
