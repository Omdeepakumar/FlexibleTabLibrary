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
 * Demonstrates modern pill-style tabs.
 * Features: Rounded background, selected tab filled, smooth animation
 */
public class PillStyleActivity extends AppCompatActivity {
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        LinearLayout layout = new LinearLayout(this);
        layout.setOrientation(LinearLayout.VERTICAL);
        layout.setBackgroundColor(android.graphics.Color.parseColor("#F5F5F5"));
        
        // Title
        TextView title = new TextView(this);
        title.setText("Pill Style Tabs");
        title.setTextSize(20);
        title.setPadding(dpToPx(16), dpToPx(16), dpToPx(16), dpToPx(8));
        layout.addView(title);
        
        // Description
        TextView desc = new TextView(this);
        desc.setText("Modern rounded pill tabs with filled selected state");
        desc.setPadding(dpToPx(16), 0, dpToPx(16), dpToPx(16));
        layout.addView(desc);
        
        // Create pill-style tab layout
        FlexibleTabLayout tabLayout = new FlexibleTabLayout(this);
        tabLayout.setTabType(TabType.ROUNDED_PILL);
        tabLayout.setTheme("pill");
        tabLayout.setTabMode(FlexibleTabLayout.MODE_AUTO);
        tabLayout.setTabCornerRadius(20);
        tabLayout.setTabPadding(16);
        tabLayout.setTabSpacing(4);
        
        // Add tabs
        tabLayout.addTab("Day");
        tabLayout.addTab("Week");
        tabLayout.addTab("Month");
        tabLayout.addTab("Year");
        
        LinearLayout.LayoutParams tabParams = new LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.WRAP_CONTENT,
            dpToPx(40)
        );
        tabParams.setMargins(dpToPx(16), 0, dpToPx(16), 0);
        layout.addView(tabLayout, tabParams);
        
        // Content area
        final TextView content = new TextView(this);
        content.setText("Day View");
        content.setTextSize(16);
        content.setPadding(dpToPx(16), dpToPx(32), dpToPx(16), dpToPx(16));
        content.setGravity(android.view.Gravity.CENTER);
        layout.addView(content);
        
        // Update content on tab change
        tabLayout.setOnTabSelectedListener(new SimpleOnTabSelectedListener() {
            @Override
            public void onTabSelected(TabItem tab, int position) {
                String[] contents = {"Day View", "Week View", "Month View", "Year View"};
                content.setText(contents[position]);
            }
        });
        
        setContentView(layout);
    }
    
    private int dpToPx(int dp) {
        return (int) (dp * getResources().getDisplayMetrics().density);
    }
}
