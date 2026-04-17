package com.flexibletab.sample;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

/**
 * Main activity showcasing all FlexibleTabLayout examples.
 */
public class MainActivity extends AppCompatActivity {
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        // Create layout programmatically for Sketchware Pro compatibility
        ScrollView scrollView = new ScrollView(this);
        LinearLayout layout = new LinearLayout(this);
        layout.setOrientation(LinearLayout.VERTICAL);
        layout.setPadding(dpToPx(16), dpToPx(16), dpToPx(16), dpToPx(16));
        
        // Title
        TextView title = new TextView(this);
        title.setText("FlexibleTabLayout Demo");
        title.setTextSize(24);
        title.setPadding(0, 0, 0, dpToPx(16));
        layout.addView(title);
        
        // Description
        TextView desc = new TextView(this);
        desc.setText("Select a tab style to see the demo:");
        desc.setPadding(0, 0, 0, dpToPx(16));
        layout.addView(desc);
        
        // Buttons for each demo
        layout.addView(createDemoButton("Instagram Style Tabs", InstagramStyleActivity.class));
        layout.addView(createDemoButton("Google Style Tabs", GoogleStyleActivity.class));
        layout.addView(createDemoButton("TikTok Style Tabs", TikTokStyleActivity.class));
        layout.addView(createDemoButton("Pill Style Tabs", PillStyleActivity.class));
        layout.addView(createDemoButton("Material Style Tabs", MaterialStyleActivity.class));
        layout.addView(createDemoButton("Custom Theme Switching", ThemeSwitchingActivity.class));
        layout.addView(createDemoButton("All Tab Types", AllTabTypesActivity.class));
        
        scrollView.addView(layout);
        setContentView(scrollView);
    }
    
    private Button createDemoButton(String text, final Class<?> activityClass) {
        Button button = new Button(this);
        button.setText(text);
        button.setAllCaps(false);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.MATCH_PARENT,
            LinearLayout.LayoutParams.WRAP_CONTENT
        );
        params.setMargins(0, 0, 0, dpToPx(8));
        button.setLayoutParams(params);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, activityClass));
            }
        });
        return button;
    }
    
    private int dpToPx(int dp) {
        return (int) (dp * getResources().getDisplayMetrics().density);
    }
}
