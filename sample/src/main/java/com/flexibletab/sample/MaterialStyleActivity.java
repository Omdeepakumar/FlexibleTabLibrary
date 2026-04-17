package com.flexibletab.sample;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import com.flexibletab.FlexibleTabLayout;
import com.flexibletab.model.TabType;

/**
 * Demonstrates Material Design tabs.
 * Features: Icon + text with material indicator, elevation
 */
public class MaterialStyleActivity extends AppCompatActivity {
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        LinearLayout layout = new LinearLayout(this);
        layout.setOrientation(LinearLayout.VERTICAL);
        
        // Title
        TextView title = new TextView(this);
        title.setText("Material Style Tabs");
        title.setTextSize(20);
        title.setPadding(dpToPx(16), dpToPx(16), dpToPx(16), dpToPx(8));
        layout.addView(title);
        
        // Description
        TextView desc = new TextView(this);
        desc.setText("Icon + text tabs with material indicator and elevation");
        desc.setPadding(dpToPx(16), 0, dpToPx(16), dpToPx(16));
        layout.addView(desc);
        
        // Create material-style tab layout
        FlexibleTabLayout tabLayout = new FlexibleTabLayout(this);
        tabLayout.setTabType(TabType.MATERIAL);
        tabLayout.setTheme("material");
        tabLayout.setTabMode(FlexibleTabLayout.MODE_FIXED);
        tabLayout.setTabElevation(4);
        
        // Add icon+text tabs
        Drawable homeIcon = ContextCompat.getDrawable(this, android.R.drawable.ic_menu_home);
        tabLayout.addTab("Home", homeIcon);
        
        Drawable searchIcon = ContextCompat.getDrawable(this, android.R.drawable.ic_menu_search);
        tabLayout.addTab("Search", searchIcon);
        
        Drawable favIcon = ContextCompat.getDrawable(this, android.R.drawable.btn_star);
        tabLayout.addTab("Favorites", favIcon);
        
        Drawable profileIcon = ContextCompat.getDrawable(this, android.R.drawable.ic_menu_myplaces);
        tabLayout.addTab("Profile", profileIcon);
        
        LinearLayout.LayoutParams tabParams = new LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.MATCH_PARENT,
            dpToPx(56)
        );
        layout.addView(tabLayout, tabParams);
        
        // Content area
        TextView content = new TextView(this);
        content.setText("Material Design Tab Content");
        content.setTextSize(16);
        content.setPadding(dpToPx(16), dpToPx(32), dpToPx(16), dpToPx(16));
        content.setGravity(android.view.Gravity.CENTER);
        layout.addView(content);
        
        setContentView(layout);
    }
    
    private int dpToPx(int dp) {
        return (int) (dp * getResources().getDisplayMetrics().density);
    }
}
