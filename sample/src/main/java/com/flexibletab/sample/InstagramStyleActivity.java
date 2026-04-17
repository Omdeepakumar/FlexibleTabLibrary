package com.flexibletab.sample;

import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import com.flexibletab.FlexibleTabLayout;
import com.flexibletab.listener.SimpleOnTabSelectedListener;
import com.flexibletab.model.TabItem;
import com.flexibletab.model.TabType;

/**
 * Demonstrates Instagram-style profile tabs.
 * Features: 3 icon tabs with underline indicator
 */
public class InstagramStyleActivity extends AppCompatActivity {
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        LinearLayout layout = new LinearLayout(this);
        layout.setOrientation(LinearLayout.VERTICAL);
        
        // Title
        TextView title = new TextView(this);
        title.setText("Instagram Style Tabs");
        title.setTextSize(20);
        title.setPadding(dpToPx(16), dpToPx(16), dpToPx(16), dpToPx(8));
        layout.addView(title);
        
        // Description
        TextView desc = new TextView(this);
        desc.setText("3 icon tabs with underline indicator - typical profile page style");
        desc.setPadding(dpToPx(16), 0, dpToPx(16), dpToPx(16));
        layout.addView(desc);
        
        // Create Instagram-style tab layout
        FlexibleTabLayout tabLayout = new FlexibleTabLayout(this);
        tabLayout.setTabType(TabType.INSTAGRAM);
        tabLayout.setTheme("instagram");
        tabLayout.setTabMode(FlexibleTabLayout.MODE_FIXED);
        
        // Add grid icon tab
        Drawable gridIcon = ContextCompat.getDrawable(this, android.R.drawable.ic_dialog_dialer);
        tabLayout.addTab(gridIcon);
        
        // Add list icon tab  
        Drawable listIcon = ContextCompat.getDrawable(this, android.R.drawable.ic_menu_sort_by_size);
        tabLayout.addTab(listIcon);
        
        // Add tagged icon tab
        Drawable taggedIcon = ContextCompat.getDrawable(this, android.R.drawable.ic_menu_myplaces);
        tabLayout.addTab(taggedIcon);
        
        // Set listener
        tabLayout.setOnTabSelectedListener(new SimpleOnTabSelectedListener() {
            @Override
            public void onTabSelected(TabItem tab, int position) {
                String[] labels = {"Grid View", "List View", "Tagged"};
                Toast.makeText(InstagramStyleActivity.this, 
                    "Selected: " + labels[position], Toast.LENGTH_SHORT).show();
            }
        });
        
        LinearLayout.LayoutParams tabParams = new LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.MATCH_PARENT,
            dpToPx(48)
        );
        layout.addView(tabLayout, tabParams);
        
        // Content area
        final TextView content = new TextView(this);
        content.setText("Grid View Content");
        content.setTextSize(16);
        content.setPadding(dpToPx(16), dpToPx(32), dpToPx(16), dpToPx(16));
        content.setGravity(android.view.Gravity.CENTER);
        layout.addView(content);
        
        // Update content on tab change
        tabLayout.setOnTabSelectedListener(new SimpleOnTabSelectedListener() {
            @Override
            public void onTabSelected(TabItem tab, int position) {
                String[] contents = {"Grid View Content", "List View Content", "Tagged Content"};
                content.setText(contents[position]);
            }
        });
        
        setContentView(layout);
    }
    
    private int dpToPx(int dp) {
        return (int) (dp * getResources().getDisplayMetrics().density);
    }
}
