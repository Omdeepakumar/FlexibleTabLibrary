package com.flexibletab.sample;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.flexibletab.FlexibleTabLayout;
import com.flexibletab.model.TabType;
import com.flexibletab.theme.TabTheme;
import com.flexibletab.theme.ThemeManager;

/**
 * Demonstrates runtime theme switching.
 * Features: Switch between multiple themes at runtime
 */
public class ThemeSwitchingActivity extends AppCompatActivity {
    
    private FlexibleTabLayout tabLayout;
    private LinearLayout rootLayout;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        rootLayout = new LinearLayout(this);
        rootLayout.setOrientation(LinearLayout.VERTICAL);
        
        // Title
        TextView title = new TextView(this);
        title.setText("Runtime Theme Switching");
        title.setTextSize(20);
        title.setPadding(dpToPx(16), dpToPx(16), dpToPx(16), dpToPx(8));
        rootLayout.addView(title);
        
        // Description
        TextView desc = new TextView(this);
        desc.setText("Tap buttons to switch themes dynamically");
        desc.setPadding(dpToPx(16), 0, dpToPx(16), dpToPx(16));
        rootLayout.addView(desc);
        
        // Theme switch buttons
        LinearLayout buttonRow = new LinearLayout(this);
        buttonRow.setOrientation(LinearLayout.HORIZONTAL);
        buttonRow.setPadding(dpToPx(16), 0, dpToPx(16), dpToPx(16));
        
        buttonRow.addView(createThemeButton("Light", "light"));
        buttonRow.addView(createThemeButton("Dark", "dark"));
        buttonRow.addView(createThemeButton("Google", "google"));
        buttonRow.addView(createThemeButton("Custom", null));
        
        rootLayout.addView(buttonRow);
        
        // Create tab layout
        tabLayout = new FlexibleTabLayout(this);
        tabLayout.setTabType(TabType.TEXT_ONLY);
        tabLayout.setTabMode(FlexibleTabLayout.MODE_FIXED);
        
        // Add tabs
        tabLayout.addTab("Tab 1");
        tabLayout.addTab("Tab 2");
        tabLayout.addTab("Tab 3");
        
        LinearLayout.LayoutParams tabParams = new LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.MATCH_PARENT,
            dpToPx(48)
        );
        rootLayout.addView(tabLayout, tabParams);
        
        // Content area
        TextView content = new TextView(this);
        content.setText("Current Theme: Light");
        content.setTextSize(16);
        content.setPadding(dpToPx(16), dpToPx(32), dpToPx(16), dpToPx(16));
        content.setGravity(android.view.Gravity.CENTER);
        rootLayout.addView(content);
        
        setContentView(rootLayout);
    }
    
    private Button createThemeButton(final String label, final String themeName) {
        Button button = new Button(this);
        button.setText(label);
        button.setAllCaps(false);
        button.setTextSize(12);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
            0,
            LinearLayout.LayoutParams.WRAP_CONTENT,
            1
        );
        params.setMargins(dpToPx(4), 0, dpToPx(4), 0);
        button.setLayoutParams(params);
        
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (themeName != null) {
                    // Apply preset theme
                    tabLayout.setTheme(themeName);
                } else {
                    // Apply custom theme
                    TabTheme customTheme = new TabTheme.Builder()
                        .backgroundColor(Color.parseColor("#E8F5E9"))
                        .selectedTextColor(Color.parseColor("#2E7D32"))
                        .unselectedTextColor(Color.parseColor("#81C784"))
                        .indicatorColor(Color.parseColor("#2E7D32"))
                        .indicatorHeight(4)
                        .indicatorStyle(com.flexibletab.theme.IndicatorStyle.UNDERLINE)
                        .animationEnabled(true)
                        .animationDuration(400)
                        .build();
                    tabLayout.setTheme(customTheme);
                }
                
                // Update background
                updateBackground(themeName);
            }
        });
        
        return button;
    }
    
    private void updateBackground(String themeName) {
        if ("dark".equals(themeName)) {
            rootLayout.setBackgroundColor(Color.parseColor("#121212"));
        } else if ("google".equals(themeName)) {
            rootLayout.setBackgroundColor(Color.WHITE);
        } else if (themeName == null) {
            rootLayout.setBackgroundColor(Color.parseColor("#E8F5E9"));
        } else {
            rootLayout.setBackgroundColor(Color.WHITE);
        }
    }
    
    private int dpToPx(int dp) {
        return (int) (dp * getResources().getDisplayMetrics().density);
    }
}
