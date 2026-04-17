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
 * Demonstrates all supported tab types in one activity.
 */
public class AllTabTypesActivity extends AppCompatActivity {
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        LinearLayout layout = new LinearLayout(this);
        layout.setOrientation(LinearLayout.VERTICAL);
        
        // Title
        TextView title = new TextView(this);
        title.setText("All Tab Types");
        title.setTextSize(20);
        title.setPadding(dpToPx(16), dpToPx(16), dpToPx(16), dpToPx(8));
        layout.addView(title);
        
        // 1. Text Only Tabs
        addSection(layout, "Text Only");
        FlexibleTabLayout textTabs = new FlexibleTabLayout(this);
        textTabs.setTabType(TabType.TEXT_ONLY);
        textTabs.setTabMode(FlexibleTabLayout.MODE_FIXED);
        textTabs.addTab("Home");
        textTabs.addTab("Profile");
        textTabs.addTab("Settings");
        layout.addView(createTabContainer(textTabs));
        
        // 2. Icon Only Tabs
        addSection(layout, "Icon Only");
        FlexibleTabLayout iconTabs = new FlexibleTabLayout(this);
        iconTabs.setTabType(TabType.ICON_ONLY);
        iconTabs.setTabMode(FlexibleTabLayout.MODE_FIXED);
        iconTabs.addTab(ContextCompat.getDrawable(this, android.R.drawable.ic_menu_home));
        iconTabs.addTab(ContextCompat.getDrawable(this, android.R.drawable.ic_menu_search));
        iconTabs.addTab(ContextCompat.getDrawable(this, android.R.drawable.ic_menu_preferences));
        layout.addView(createTabContainer(iconTabs));
        
        // 3. Icon + Text Tabs
        addSection(layout, "Icon + Text");
        FlexibleTabLayout iconTextTabs = new FlexibleTabLayout(this);
        iconTextTabs.setTabType(TabType.ICON_TEXT);
        iconTextTabs.setTabMode(FlexibleTabLayout.MODE_FIXED);
        iconTextTabs.addTab("Home", ContextCompat.getDrawable(this, android.R.drawable.ic_menu_home));
        iconTextTabs.addTab("Search", ContextCompat.getDrawable(this, android.R.drawable.ic_menu_search));
        iconTextTabs.addTab("Profile", ContextCompat.getDrawable(this, android.R.drawable.ic_menu_myplaces));
        layout.addView(createTabContainer(iconTextTabs));
        
        // 4. Underline Indicator Tabs
        addSection(layout, "Underline Indicator");
        FlexibleTabLayout underlineTabs = new FlexibleTabLayout(this);
        underlineTabs.setTabType(TabType.UNDERLINE);
        underlineTabs.setTabMode(FlexibleTabLayout.MODE_FIXED);
        underlineTabs.addTab("Tab 1");
        underlineTabs.addTab("Tab 2");
        underlineTabs.addTab("Tab 3");
        layout.addView(createTabContainer(underlineTabs));
        
        // 5. Rounded Pill Tabs
        addSection(layout, "Rounded Pill");
        FlexibleTabLayout pillTabs = new FlexibleTabLayout(this);
        pillTabs.setTabType(TabType.ROUNDED_PILL);
        pillTabs.setTabMode(FlexibleTabLayout.MODE_AUTO);
        pillTabs.setTabCornerRadius(20);
        pillTabs.addTab("Option A");
        pillTabs.addTab("Option B");
        pillTabs.addTab("Option C");
        layout.addView(createTabContainer(pillTabs));
        
        // 6. Material Tabs
        addSection(layout, "Material Design");
        FlexibleTabLayout materialTabs = new FlexibleTabLayout(this);
        materialTabs.setTabType(TabType.MATERIAL);
        materialTabs.setTabMode(FlexibleTabLayout.MODE_FIXED);
        materialTabs.setTheme("material");
        materialTabs.addTab("Item 1");
        materialTabs.addTab("Item 2");
        materialTabs.addTab("Item 3");
        layout.addView(createTabContainer(materialTabs));
        
        // 7. Instagram Tabs
        addSection(layout, "Instagram Style");
        FlexibleTabLayout instaTabs = new FlexibleTabLayout(this);
        instaTabs.setTabType(TabType.INSTAGRAM);
        instaTabs.setTabMode(FlexibleTabLayout.MODE_FIXED);
        instaTabs.setTheme("instagram");
        instaTabs.addTab(ContextCompat.getDrawable(this, android.R.drawable.ic_dialog_dialer));
        instaTabs.addTab(ContextCompat.getDrawable(this, android.R.drawable.ic_menu_sort_by_size));
        instaTabs.addTab(ContextCompat.getDrawable(this, android.R.drawable.ic_menu_myplaces));
        layout.addView(createTabContainer(instaTabs));
        
        // 8. TikTok Tabs
        addSection(layout, "TikTok Style");
        FlexibleTabLayout tiktokTabs = new FlexibleTabLayout(this);
        tiktokTabs.setTabType(TabType.TIKTOK);
        tiktokTabs.setTabMode(FlexibleTabLayout.MODE_FIXED);
        tiktokTabs.setTheme("tiktok");
        tiktokTabs.addTab("Following");
        tiktokTabs.addTab("For You");
        layout.addView(createTabContainer(tiktokTabs));
        
        // Wrap in scroll view
        android.widget.ScrollView scrollView = new android.widget.ScrollView(this);
        scrollView.addView(layout);
        setContentView(scrollView);
    }
    
    private void addSection(LinearLayout parent, String title) {
        TextView textView = new TextView(this);
        textView.setText(title);
        textView.setTextSize(16);
        textView.setPadding(dpToPx(16), dpToPx(16), dpToPx(16), dpToPx(8));
        parent.addView(textView);
    }
    
    private LinearLayout createTabContainer(FlexibleTabLayout tabLayout) {
        LinearLayout container = new LinearLayout(this);
        container.setOrientation(LinearLayout.VERTICAL);
        container.setPadding(dpToPx(16), 0, dpToPx(16), dpToPx(8));
        
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.MATCH_PARENT,
            dpToPx(48)
        );
        container.addView(tabLayout, params);
        
        return container;
    }
    
    private int dpToPx(int dp) {
        return (int) (dp * getResources().getDisplayMetrics().density);
    }
}
