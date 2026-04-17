package com.flexibletab.theme;

import android.content.res.ColorStateList;
import android.graphics.Color;

/**
 * Theme configuration for FlexibleTabLayout.
 * Defines colors, sizes, and styling for all tab states.
 */
public class TabTheme {
    
    // Background colors
    private int backgroundColor = Color.TRANSPARENT;
    private int selectedBackgroundColor = Color.TRANSPARENT;
    private int unselectedBackgroundColor = Color.TRANSPARENT;
    
    // Text colors
    private int selectedTextColor = Color.BLACK;
    private int unselectedTextColor = Color.GRAY;
    
    // Icon/Image tints
    private int selectedIconTint = Color.BLACK;
    private int unselectedIconTint = Color.GRAY;
    private int selectedImageTint = Color.TRANSPARENT;
    private int unselectedImageTint = Color.TRANSPARENT;
    
    // Indicator
    private int indicatorColor = Color.BLACK;
    private int indicatorHeight = 4;
    private int indicatorWidth = 0;
    private int indicatorRadius = 0;
    private int indicatorStyle = IndicatorStyle.UNDERLINE;
    
    // Tab appearance
    private int cornerRadius = 0;
    private int tabPadding = 16;
    private int tabSpacing = 0;
    private int textSize = 14;
    private int iconSize = 48;
    private int elevation = 0;
    
    // Effects
    private int rippleColor = Color.parseColor("#1F000000");
    private int borderColor = Color.TRANSPARENT;
    private int dividerColor = Color.TRANSPARENT;
    private int animationDuration = 300;
    private boolean animationEnabled = true;
    
    // Font
    private String fontFamily = null;
    private int fontStyle = 0;
    
    // Badge
    private int badgeBackgroundColor = Color.RED;
    private int badgeTextColor = Color.WHITE;
    
    public TabTheme() {}
    
    // Builder pattern for easy theme creation
    public static class Builder {
        private TabTheme theme = new TabTheme();
        
        public Builder backgroundColor(int color) {
            theme.backgroundColor = color;
            return this;
        }
        
        public Builder selectedBackgroundColor(int color) {
            theme.selectedBackgroundColor = color;
            return this;
        }
        
        public Builder unselectedBackgroundColor(int color) {
            theme.unselectedBackgroundColor = color;
            return this;
        }
        
        public Builder selectedTextColor(int color) {
            theme.selectedTextColor = color;
            return this;
        }
        
        public Builder unselectedTextColor(int color) {
            theme.unselectedTextColor = color;
            return this;
        }
        
        public Builder selectedIconTint(int color) {
            theme.selectedIconTint = color;
            return this;
        }
        
        public Builder unselectedIconTint(int color) {
            theme.unselectedIconTint = color;
            return this;
        }
        
        public Builder indicatorColor(int color) {
            theme.indicatorColor = color;
            return this;
        }
        
        public Builder indicatorHeight(int height) {
            theme.indicatorHeight = height;
            return this;
        }
        
        public Builder indicatorWidth(int width) {
            theme.indicatorWidth = width;
            return this;
        }
        
        public Builder indicatorRadius(int radius) {
            theme.indicatorRadius = radius;
            return this;
        }
        
        public Builder indicatorStyle(@IndicatorStyle int style) {
            theme.indicatorStyle = style;
            return this;
        }
        
        public Builder cornerRadius(int radius) {
            theme.cornerRadius = radius;
            return this;
        }
        
        public Builder tabPadding(int padding) {
            theme.tabPadding = padding;
            return this;
        }
        
        public Builder tabSpacing(int spacing) {
            theme.tabSpacing = spacing;
            return this;
        }
        
        public Builder textSize(int size) {
            theme.textSize = size;
            return this;
        }
        
        public Builder iconSize(int size) {
            theme.iconSize = size;
            return this;
        }
        
        public Builder elevation(int elevation) {
            theme.elevation = elevation;
            return this;
        }
        
        public Builder rippleColor(int color) {
            theme.rippleColor = color;
            return this;
        }
        
        public Builder borderColor(int color) {
            theme.borderColor = color;
            return this;
        }
        
        public Builder dividerColor(int color) {
            theme.dividerColor = color;
            return this;
        }
        
        public Builder animationDuration(int duration) {
            theme.animationDuration = duration;
            return this;
        }
        
        public Builder animationEnabled(boolean enabled) {
            theme.animationEnabled = enabled;
            return this;
        }
        
        public Builder fontFamily(String family) {
            theme.fontFamily = family;
            return this;
        }
        
        public Builder badgeBackgroundColor(int color) {
            theme.badgeBackgroundColor = color;
            return this;
        }
        
        public Builder badgeTextColor(int color) {
            theme.badgeTextColor = color;
            return this;
        }
        
        public TabTheme build() {
            return theme;
        }
    }
    
    // Getters and Setters
    public int getBackgroundColor() { return backgroundColor; }
    public void setBackgroundColor(int color) { this.backgroundColor = color; }
    
    public int getSelectedBackgroundColor() { return selectedBackgroundColor; }
    public void setSelectedBackgroundColor(int color) { this.selectedBackgroundColor = color; }
    
    public int getUnselectedBackgroundColor() { return unselectedBackgroundColor; }
    public void setUnselectedBackgroundColor(int color) { this.unselectedBackgroundColor = color; }
    
    public int getSelectedTextColor() { return selectedTextColor; }
    public void setSelectedTextColor(int color) { this.selectedTextColor = color; }
    
    public int getUnselectedTextColor() { return unselectedTextColor; }
    public void setUnselectedTextColor(int color) { this.unselectedTextColor = color; }
    
    public int getSelectedIconTint() { return selectedIconTint; }
    public void setSelectedIconTint(int color) { this.selectedIconTint = color; }
    
    public int getUnselectedIconTint() { return unselectedIconTint; }
    public void setUnselectedIconTint(int color) { this.unselectedIconTint = color; }
    
    public int getSelectedImageTint() { return selectedImageTint; }
    public void setSelectedImageTint(int color) { this.selectedImageTint = color; }
    
    public int getUnselectedImageTint() { return unselectedImageTint; }
    public void setUnselectedImageTint(int color) { this.unselectedImageTint = color; }
    
    public int getIndicatorColor() { return indicatorColor; }
    public void setIndicatorColor(int color) { this.indicatorColor = color; }
    
    public int getIndicatorHeight() { return indicatorHeight; }
    public void setIndicatorHeight(int height) { this.indicatorHeight = height; }
    
    public int getIndicatorWidth() { return indicatorWidth; }
    public void setIndicatorWidth(int width) { this.indicatorWidth = width; }
    
    public int getIndicatorRadius() { return indicatorRadius; }
    public void setIndicatorRadius(int radius) { this.indicatorRadius = radius; }
    
    @IndicatorStyle
    public int getIndicatorStyle() { return indicatorStyle; }
    public void setIndicatorStyle(@IndicatorStyle int style) { this.indicatorStyle = style; }
    
    public int getCornerRadius() { return cornerRadius; }
    public void setCornerRadius(int radius) { this.cornerRadius = radius; }
    
    public int getTabPadding() { return tabPadding; }
    public void setTabPadding(int padding) { this.tabPadding = padding; }
    
    public int getTabSpacing() { return tabSpacing; }
    public void setTabSpacing(int spacing) { this.tabSpacing = spacing; }
    
    public int getTextSize() { return textSize; }
    public void setTextSize(int size) { this.textSize = size; }
    
    public int getIconSize() { return iconSize; }
    public void setIconSize(int size) { this.iconSize = size; }
    
    public int getElevation() { return elevation; }
    public void setElevation(int elevation) { this.elevation = elevation; }
    
    public int getRippleColor() { return rippleColor; }
    public void setRippleColor(int color) { this.rippleColor = color; }
    
    public int getBorderColor() { return borderColor; }
    public void setBorderColor(int color) { this.borderColor = color; }
    
    public int getDividerColor() { return dividerColor; }
    public void setDividerColor(int color) { this.dividerColor = color; }
    
    public int getAnimationDuration() { return animationDuration; }
    public void setAnimationDuration(int duration) { this.animationDuration = duration; }
    
    public boolean isAnimationEnabled() { return animationEnabled; }
    public void setAnimationEnabled(boolean enabled) { this.animationEnabled = enabled; }
    
    public String getFontFamily() { return fontFamily; }
    public void setFontFamily(String family) { this.fontFamily = family; }
    
    public int getFontStyle() { return fontStyle; }
    public void setFontStyle(int style) { this.fontStyle = style; }
    
    public int getBadgeBackgroundColor() { return badgeBackgroundColor; }
    public void setBadgeBackgroundColor(int color) { this.badgeBackgroundColor = color; }
    
    public int getBadgeTextColor() { return badgeTextColor; }
    public void setBadgeTextColor(int color) { this.badgeTextColor = color; }
    
    /**
     * Create a copy of this theme
     */
    public TabTheme copy() {
        TabTheme copy = new TabTheme();
        copy.backgroundColor = this.backgroundColor;
        copy.selectedBackgroundColor = this.selectedBackgroundColor;
        copy.unselectedBackgroundColor = this.unselectedBackgroundColor;
        copy.selectedTextColor = this.selectedTextColor;
        copy.unselectedTextColor = this.unselectedTextColor;
        copy.selectedIconTint = this.selectedIconTint;
        copy.unselectedIconTint = this.unselectedIconTint;
        copy.selectedImageTint = this.selectedImageTint;
        copy.unselectedImageTint = this.unselectedImageTint;
        copy.indicatorColor = this.indicatorColor;
        copy.indicatorHeight = this.indicatorHeight;
        copy.indicatorWidth = this.indicatorWidth;
        copy.indicatorRadius = this.indicatorRadius;
        copy.indicatorStyle = this.indicatorStyle;
        copy.cornerRadius = this.cornerRadius;
        copy.tabPadding = this.tabPadding;
        copy.tabSpacing = this.tabSpacing;
        copy.textSize = this.textSize;
        copy.iconSize = this.iconSize;
        copy.elevation = this.elevation;
        copy.rippleColor = this.rippleColor;
        copy.borderColor = this.borderColor;
        copy.dividerColor = this.dividerColor;
        copy.animationDuration = this.animationDuration;
        copy.animationEnabled = this.animationEnabled;
        copy.fontFamily = this.fontFamily;
        copy.fontStyle = this.fontStyle;
        copy.badgeBackgroundColor = this.badgeBackgroundColor;
        copy.badgeTextColor = this.badgeTextColor;
        return copy;
    }
}
