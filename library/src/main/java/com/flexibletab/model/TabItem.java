package com.flexibletab.model;

import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;

/**
 * Model class representing a single tab item.
 * Supports text, icon, image, and combined tab types.
 */
public class TabItem {
    
    private String title;
    private Drawable icon;
    private Bitmap image;
    private int position;
    private boolean isSelected;
    private boolean isEnabled = true;
    private Object tag;
    private int badgeCount = 0;
    private boolean showBadge = false;
    
    public TabItem() {}
    
    public TabItem(String title) {
        this.title = title;
    }
    
    public TabItem(String title, Drawable icon) {
        this.title = title;
        this.icon = icon;
    }
    
    public TabItem(Drawable icon) {
        this.icon = icon;
    }
    
    public TabItem(Bitmap image) {
        this.image = image;
    }
    
    public TabItem(String title, Bitmap image) {
        this.title = title;
        this.image = image;
    }
    
    public String getTitle() {
        return title;
    }
    
    public void setTitle(String title) {
        this.title = title;
    }
    
    public Drawable getIcon() {
        return icon;
    }
    
    public void setIcon(Drawable icon) {
        this.icon = icon;
    }
    
    public Bitmap getImage() {
        return image;
    }
    
    public void setImage(Bitmap image) {
        this.image = image;
    }
    
    public int getPosition() {
        return position;
    }
    
    public void setPosition(int position) {
        this.position = position;
    }
    
    public boolean isSelected() {
        return isSelected;
    }
    
    public void setSelected(boolean selected) {
        isSelected = selected;
    }
    
    public boolean isEnabled() {
        return isEnabled;
    }
    
    public void setEnabled(boolean enabled) {
        isEnabled = enabled;
    }
    
    public Object getTag() {
        return tag;
    }
    
    public void setTag(Object tag) {
        this.tag = tag;
    }
    
    public int getBadgeCount() {
        return badgeCount;
    }
    
    public void setBadgeCount(int badgeCount) {
        this.badgeCount = badgeCount;
        this.showBadge = badgeCount > 0;
    }
    
    public boolean isShowBadge() {
        return showBadge;
    }
    
    public void setShowBadge(boolean showBadge) {
        this.showBadge = showBadge;
    }
    
    public boolean hasIcon() {
        return icon != null;
    }
    
    public boolean hasImage() {
        return image != null;
    }
    
    public boolean hasTitle() {
        return title != null && !title.isEmpty();
    }
}
