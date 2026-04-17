package com.flexibletab.theme;

import android.content.Context;
import android.graphics.Color;
import android.util.TypedValue;

import java.util.HashMap;
import java.util.Map;

/**
 * Manages preset themes and provides theme switching functionality.
 */
public class ThemeManager {
    
    private static ThemeManager instance;
    private Map<String, TabTheme> themes = new HashMap<>();
    private TabTheme currentTheme;
    private Context context;
    
    private ThemeManager(Context context) {
        this.context = context.getApplicationContext();
        initDefaultThemes();
    }
    
    public static synchronized ThemeManager getInstance(Context context) {
        if (instance == null) {
            instance = new ThemeManager(context);
        }
        return instance;
    }
    
    private void initDefaultThemes() {
        // Light Theme
        themes.put("light", new TabTheme.Builder()
            .backgroundColor(Color.WHITE)
            .selectedTextColor(Color.BLACK)
            .unselectedTextColor(Color.GRAY)
            .selectedIconTint(Color.BLACK)
            .unselectedIconTint(Color.GRAY)
            .indicatorColor(Color.BLACK)
            .rippleColor(Color.parseColor("#1F000000"))
            .build());
        
        // Dark Theme
        themes.put("dark", new TabTheme.Builder()
            .backgroundColor(Color.parseColor("#121212"))
            .selectedTextColor(Color.WHITE)
            .unselectedTextColor(Color.parseColor("#80FFFFFF"))
            .selectedIconTint(Color.WHITE)
            .unselectedIconTint(Color.parseColor("#80FFFFFF"))
            .indicatorColor(Color.WHITE)
            .rippleColor(Color.parseColor("#1FFFFFFF"))
            .build());
        
        // Instagram Theme
        themes.put("instagram", new TabTheme.Builder()
            .backgroundColor(Color.WHITE)
            .selectedIconTint(Color.BLACK)
            .unselectedIconTint(Color.parseColor("#80000000"))
            .indicatorColor(Color.BLACK)
            .indicatorHeight(2)
            .indicatorStyle(IndicatorStyle.UNDERLINE)
            .tabPadding(16)
            .animationEnabled(true)
            .build());
        
        // Google Theme
        themes.put("google", new TabTheme.Builder()
            .backgroundColor(Color.WHITE)
            .selectedTextColor(Color.parseColor("#1A73E8"))
            .unselectedTextColor(Color.parseColor("#5F6368"))
            .selectedIconTint(Color.parseColor("#1A73E8"))
            .unselectedIconTint(Color.parseColor("#5F6368"))
            .indicatorColor(Color.parseColor("#1A73E8"))
            .indicatorHeight(3)
            .indicatorStyle(IndicatorStyle.MATERIAL)
            .tabPadding(20)
            .textSize(14)
            .animationEnabled(true)
            .rippleColor(Color.parseColor("#1F1A73E8"))
            .build());
        
        // TikTok Theme
        themes.put("tiktok", new TabTheme.Builder()
            .backgroundColor(Color.BLACK)
            .selectedTextColor(Color.WHITE)
            .unselectedTextColor(Color.parseColor("#80FFFFFF"))
            .selectedIconTint(Color.WHITE)
            .unselectedIconTint(Color.parseColor("#80FFFFFF"))
            .indicatorColor(Color.WHITE)
            .indicatorHeight(2)
            .indicatorStyle(IndicatorStyle.UNDERLINE)
            .indicatorWidth(dpToPx(20))
            .textSize(16)
            .tabPadding(12)
            .animationEnabled(true)
            .build());
        
        // Pill Theme
        themes.put("pill", new TabTheme.Builder()
            .backgroundColor(Color.parseColor("#F0F0F0"))
            .selectedBackgroundColor(Color.WHITE)
            .unselectedBackgroundColor(Color.TRANSPARENT)
            .selectedTextColor(Color.BLACK)
            .unselectedTextColor(Color.GRAY)
            .cornerRadius(dpToPx(20))
            .tabPadding(12)
            .tabSpacing(4)
            .indicatorStyle(IndicatorStyle.NONE)
            .animationEnabled(true)
            .build());
        
        // Material Theme
        themes.put("material", new TabTheme.Builder()
            .backgroundColor(Color.WHITE)
            .selectedTextColor(Color.parseColor("#6200EE"))
            .unselectedTextColor(Color.parseColor("#99000000"))
            .selectedIconTint(Color.parseColor("#6200EE"))
            .unselectedIconTint(Color.parseColor("#99000000"))
            .indicatorColor(Color.parseColor("#6200EE"))
            .indicatorHeight(2)
            .indicatorStyle(IndicatorStyle.MATERIAL)
            .tabPadding(16)
            .textSize(14)
            .animationEnabled(true)
            .rippleColor(Color.parseColor("#1F6200EE"))
            .build());
        
        // Set default theme
        currentTheme = themes.get("light").copy();
    }
    
    /**
     * Apply a preset theme by name
     */
    public void applyTheme(String themeName) {
        TabTheme preset = themes.get(themeName.toLowerCase());
        if (preset != null) {
            currentTheme = preset.copy();
        }
    }
    
    /**
     * Register a custom theme
     */
    public void registerTheme(String name, TabTheme theme) {
        themes.put(name.toLowerCase(), theme);
    }
    
    /**
     * Get current theme
     */
    public TabTheme getCurrentTheme() {
        return currentTheme;
    }
    
    /**
     * Set current theme directly
     */
    public void setCurrentTheme(TabTheme theme) {
        this.currentTheme = theme;
    }
    
    /**
     * Get a preset theme without applying it
     */
    public TabTheme getPresetTheme(String name) {
        TabTheme preset = themes.get(name.toLowerCase());
        return preset != null ? preset.copy() : null;
    }
    
    /**
     * Check if theme exists
     */
    public boolean hasTheme(String name) {
        return themes.containsKey(name.toLowerCase());
    }
    
    /**
     * Get all available theme names
     */
    public String[] getAvailableThemes() {
        return themes.keySet().toArray(new String[0]);
    }
    
    private int dpToPx(int dp) {
        return (int) TypedValue.applyDimension(
            TypedValue.COMPLEX_UNIT_DIP, 
            dp, 
            context.getResources().getDisplayMetrics()
        );
    }
    
    /**
     * Reset to default instance (useful for testing)
     */
    public static void reset() {
        instance = null;
    }
}
