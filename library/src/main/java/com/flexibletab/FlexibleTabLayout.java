package com.flexibletab;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.RippleDrawable;
import android.graphics.drawable.StateListDrawable;
import android.os.Build;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.viewpager.widget.ViewPager;
import androidx.viewpager2.widget.ViewPager2;

import com.flexibletab.animation.TabAnimator;
import com.flexibletab.indicator.TabIndicator;
import com.flexibletab.listener.OnTabSelectedListener;
import com.flexibletab.listener.SimpleOnTabSelectedListener;
import com.flexibletab.model.TabItem;
import com.flexibletab.model.TabType;
import com.flexibletab.theme.IndicatorStyle;
import com.flexibletab.theme.TabTheme;
import com.flexibletab.theme.ThemeManager;

import java.util.ArrayList;
import java.util.List;

/**
 * A flexible, customizable tab layout for Android.
 * Supports multiple tab types, indicators, themes, and animations.
 */
public class FlexibleTabLayout extends FrameLayout {
    
    private LinearLayout tabContainer;
    private TabIndicator indicator;
    private List<TabItem> tabs = new ArrayList<>();
    private List<View> tabViews = new ArrayList<>();
    private TabTheme theme;
    private TabAnimator animator;
    private OnTabSelectedListener listener;
    
    private int selectedPosition = -1;
    private TabType tabType = TabType.TEXT_ONLY;
    private int tabGravity = Gravity.CENTER;
    private int tabMode = MODE_FIXED;
    
    private ViewPager viewPager;
    private ViewPager2 viewPager2;
    
    public static final int MODE_FIXED = 0;
    public static final int MODE_SCROLLABLE = 1;
    public static final int MODE_AUTO = 2;
    
    // Attribute constants
    private static final int ATTR_TEXT_ONLY = 0;
    private static final int ATTR_ICON_ONLY = 1;
    private static final int ATTR_IMAGE_ONLY = 2;
    private static final int ATTR_ICON_TEXT = 3;
    private static final int ATTR_IMAGE_TEXT = 4;
    private static final int ATTR_INDICATOR_ONLY = 5;
    private static final int ATTR_ROUNDED_PILL = 6;
    private static final int ATTR_UNDERLINE = 7;
    private static final int ATTR_MATERIAL = 8;
    private static final int ATTR_INSTAGRAM = 9;
    private static final int ATTR_TIKTOK = 10;
    
    public FlexibleTabLayout(Context context) {
        super(context);
        init(context, null);
    }
    
    public FlexibleTabLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }
    
    public FlexibleTabLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }
    
    private void init(Context context, AttributeSet attrs) {
        animator = new TabAnimator();
        theme = ThemeManager.getInstance(context).getCurrentTheme();
        
        // Create container
        tabContainer = new LinearLayout(context);
        tabContainer.setOrientation(LinearLayout.HORIZONTAL);
        tabContainer.setGravity(Gravity.CENTER_VERTICAL);
        LayoutParams containerParams = new LayoutParams(
            LayoutParams.MATCH_PARENT, 
            LayoutParams.MATCH_PARENT
        );
        addView(tabContainer, containerParams);
        
        // Create indicator
        indicator = new TabIndicator(context);
        LayoutParams indicatorParams = new LayoutParams(
            LayoutParams.MATCH_PARENT, 
            LayoutParams.MATCH_PARENT
        );
        indicator.setTheme(theme);
        addView(indicator, indicatorParams);
        
        // Parse attributes
        if (attrs != null) {
            parseAttributes(context, attrs);
        }
        
        // Set background
        setBackgroundColor(theme.getBackgroundColor());
    }
    
    private void parseAttributes(Context context, AttributeSet attrs) {
        // Note: In production, use R.styleable.FlexibleTabLayout
        // For now, we'll use default values
    }
    
    /**
     * Add a tab with title
     */
    public void addTab(String title) {
        addTab(new TabItem(title));
    }
    
    /**
     * Add a tab with icon
     */
    public void addTab(Drawable icon) {
        addTab(new TabItem(icon));
    }
    
    /**
     * Add a tab with title and icon
     */
    public void addTab(String title, Drawable icon) {
        addTab(new TabItem(title, icon));
    }
    
    /**
     * Add a tab with image
     */
    public void addTab(Bitmap image) {
        addTab(new TabItem(image));
    }
    
    /**
     * Add a tab with title and image
     */
    public void addTab(String title, Bitmap image) {
        addTab(new TabItem(title, image));
    }
    
    /**
     * Add a tab from TabItem model
     */
    public void addTab(TabItem tab) {
        tab.setPosition(tabs.size());
        tabs.add(tab);
        createTabView(tab);
        requestLayout();
    }
    
    /**
     * Set multiple tabs at once
     */
    public void setTabs(List<TabItem> tabs) {
        removeAllTabs();
        for (TabItem tab : tabs) {
            addTab(tab);
        }
    }
    
    /**
     * Set tabs from string array
     */
    public void setTabs(String[] titles) {
        removeAllTabs();
        for (String title : titles) {
            addTab(title);
        }
    }
    
    /**
     * Remove all tabs
     */
    public void removeAllTabs() {
        tabs.clear();
        tabViews.clear();
        tabContainer.removeAllViews();
        selectedPosition = -1;
    }
    
    /**
     * Create the visual view for a tab
     */
    private void createTabView(final TabItem tab) {
        final Context context = getContext();
        final FrameLayout tabView = new FrameLayout(context);
        
        // Setup layout params
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
            ViewGroup.LayoutParams.WRAP_CONTENT,
            ViewGroup.LayoutParams.MATCH_PARENT
        );
        params.weight = tabMode == MODE_FIXED ? 1 : 0;
        tabView.setLayoutParams(params);
        
        // Set padding
        int padding = theme.getTabPadding();
        tabView.setPadding(padding, 0, padding, 0);
        
        // Create content based on tab type
        switch (tabType) {
            case TEXT_ONLY:
                createTextTab(tabView, tab, false);
                break;
            case ICON_ONLY:
                createIconTab(tabView, tab, false);
                break;
            case IMAGE_ONLY:
                createImageTab(tabView, tab, false);
                break;
            case ICON_TEXT:
                createIconTextTab(tabView, tab);
                break;
            case IMAGE_TEXT:
                createImageTextTab(tabView, tab);
                break;
            case INDICATOR_ONLY:
                createTextTab(tabView, tab, true);
                break;
            case ROUNDED_PILL:
                createPillTab(tabView, tab);
                break;
            case UNDERLINE:
                createTextTab(tabView, tab, false);
                break;
            case MATERIAL:
                createIconTextTab(tabView, tab);
                break;
            case INSTAGRAM:
                createIconTab(tabView, tab, false);
                break;
            case TIKTOK:
                createTextTab(tabView, tab, false);
                break;
        }
        
        // Set click listener
        tabView.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (tab.isEnabled()) {
                    selectTab(tab.getPosition());
                }
            }
        });
        
        // Set ripple effect
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            tabView.setForeground(createRippleDrawable());
        }
        
        tabViews.add(tabView);
        tabContainer.addView(tabView);
    }
    
    private void createTextTab(FrameLayout container, TabItem tab, boolean indicatorOnly) {
        TextView textView = new TextView(getContext());
        textView.setText(tab.getTitle());
        textView.setTextSize(TypedValue.COMPLEX_UNIT_SP, theme.getTextSize());
        textView.setGravity(Gravity.CENTER);
        textView.setTextColor(tab.isSelected() ? theme.getSelectedTextColor() : theme.getUnselectedTextColor());
        
        FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(
            FrameLayout.LayoutParams.WRAP_CONTENT,
            FrameLayout.LayoutParams.WRAP_CONTENT
        );
        params.gravity = Gravity.CENTER;
        container.addView(textView, params);
        
        // Store text view reference for updates
        container.setTag(textView);
    }
    
    private void createIconTab(FrameLayout container, TabItem tab, boolean showText) {
        ImageView imageView = new ImageView(getContext());
        imageView.setImageDrawable(tab.getIcon());
        imageView.setColorFilter(tab.isSelected() ? theme.getSelectedIconTint() : theme.getUnselectedIconTint());
        
        int iconSize = theme.getIconSize();
        FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(iconSize, iconSize);
        params.gravity = Gravity.CENTER;
        container.addView(imageView, params);
        
        container.setTag(imageView);
    }
    
    private void createImageTab(FrameLayout container, TabItem tab, boolean showText) {
        ImageView imageView = new ImageView(getContext());
        imageView.setImageBitmap(tab.getImage());
        
        int iconSize = theme.getIconSize();
        FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(iconSize, iconSize);
        params.gravity = Gravity.CENTER;
        container.addView(imageView, params);
        
        container.setTag(imageView);
    }
    
    private void createIconTextTab(FrameLayout container, TabItem tab) {
        LinearLayout layout = new LinearLayout(getContext());
        layout.setOrientation(LinearLayout.HORIZONTAL);
        layout.setGravity(Gravity.CENTER);
        
        // Icon
        ImageView imageView = new ImageView(getContext());
        imageView.setImageDrawable(tab.getIcon());
        imageView.setColorFilter(tab.isSelected() ? theme.getSelectedIconTint() : theme.getUnselectedIconTint());
        int iconSize = dpToPx(20);
        LinearLayout.LayoutParams iconParams = new LinearLayout.LayoutParams(iconSize, iconSize);
        iconParams.rightMargin = dpToPx(8);
        layout.addView(imageView, iconParams);
        
        // Text
        TextView textView = new TextView(getContext());
        textView.setText(tab.getTitle());
        textView.setTextSize(TypedValue.COMPLEX_UNIT_SP, theme.getTextSize());
        textView.setTextColor(tab.isSelected() ? theme.getSelectedTextColor() : theme.getUnselectedTextColor());
        layout.addView(textView);
        
        FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(
            FrameLayout.LayoutParams.WRAP_CONTENT,
            FrameLayout.LayoutParams.WRAP_CONTENT
        );
        params.gravity = Gravity.CENTER;
        container.addView(layout, params);
        
        container.setTag(new View[]{imageView, textView});
    }
    
    private void createImageTextTab(FrameLayout container, TabItem tab) {
        LinearLayout layout = new LinearLayout(getContext());
        layout.setOrientation(LinearLayout.HORIZONTAL);
        layout.setGravity(Gravity.CENTER);
        
        // Image
        ImageView imageView = new ImageView(getContext());
        imageView.setImageBitmap(tab.getImage());
        int iconSize = dpToPx(20);
        LinearLayout.LayoutParams imageParams = new LinearLayout.LayoutParams(iconSize, iconSize);
        imageParams.rightMargin = dpToPx(8);
        layout.addView(imageView, imageParams);
        
        // Text
        TextView textView = new TextView(getContext());
        textView.setText(tab.getTitle());
        textView.setTextSize(TypedValue.COMPLEX_UNIT_SP, theme.getTextSize());
        textView.setTextColor(tab.isSelected() ? theme.getSelectedTextColor() : theme.getUnselectedTextColor());
        layout.addView(textView);
        
        FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(
            FrameLayout.LayoutParams.WRAP_CONTENT,
            FrameLayout.LayoutParams.WRAP_CONTENT
        );
        params.gravity = Gravity.CENTER;
        container.addView(layout, params);
        
        container.setTag(new View[]{imageView, textView});
    }
    
    private void createPillTab(FrameLayout container, TabItem tab) {
        TextView textView = new TextView(getContext());
        textView.setText(tab.getTitle());
        textView.setTextSize(TypedValue.COMPLEX_UNIT_SP, theme.getTextSize());
        textView.setGravity(Gravity.CENTER);
        textView.setPadding(dpToPx(16), dpToPx(8), dpToPx(16), dpToPx(8));
        
        // Set background
        updatePillBackground(textView, tab.isSelected());
        
        FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(
            FrameLayout.LayoutParams.WRAP_CONTENT,
            FrameLayout.LayoutParams.WRAP_CONTENT
        );
        params.gravity = Gravity.CENTER;
        container.addView(textView, params);
        
        container.setTag(textView);
    }
    
    private void updatePillBackground(TextView textView, boolean selected) {
        GradientDrawable drawable = new GradientDrawable();
        drawable.setCornerRadius(theme.getCornerRadius());
        if (selected) {
            drawable.setColor(theme.getSelectedBackgroundColor());
            textView.setTextColor(theme.getSelectedTextColor());
        } else {
            drawable.setColor(theme.getUnselectedBackgroundColor());
            textView.setTextColor(theme.getUnselectedTextColor());
        }
        textView.setBackground(drawable);
    }
    
    private Drawable createRippleDrawable() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            return new RippleDrawable(
                android.content.res.ColorStateList.valueOf(theme.getRippleColor()),
                null,
                null
            );
        }
        return null;
    }
    
    /**
     * Select a tab by position
     */
    public void selectTab(int position) {
        if (position < 0 || position >= tabs.size()) return;
        if (position == selectedPosition) {
            // Reselected
            TabItem tab = tabs.get(position);
            if (listener != null) {
                listener.onTabReselected(tab, position);
            }
            return;
        }
        
        // Unselect previous
        int previousPosition = selectedPosition;
        if (previousPosition >= 0 && previousPosition < tabs.size()) {
            TabItem previousTab = tabs.get(previousPosition);
            previousTab.setSelected(false);
            updateTabView(previousPosition, false);
            if (listener != null) {
                listener.onTabUnselected(previousTab, previousPosition);
            }
        }
        
        // Select new
        selectedPosition = position;
        TabItem selectedTab = tabs.get(position);
        selectedTab.setSelected(true);
        updateTabView(position, true);
        
        // Animate indicator
        animateIndicatorToTab(position);
        
        if (listener != null) {
            listener.onTabSelected(selectedTab, position);
        }
        
        // Update ViewPager if attached
        if (viewPager != null) {
            viewPager.setCurrentItem(position, true);
        }
        if (viewPager2 != null) {
            viewPager2.setCurrentItem(position, true);
        }
    }
    
    /**
     * Update tab view appearance based on selection state
     */
    private void updateTabView(int position, boolean selected) {
        if (position < 0 || position >= tabViews.size()) return;
        
        View tabView = tabViews.get(position);
        Object tag = tabView.getTag();
        
        if (tag instanceof TextView) {
            TextView textView = (TextView) tag;
            if (theme.isAnimationEnabled()) {
                animator.animateTextColor(textView, 
                    selected ? theme.getUnselectedTextColor() : theme.getSelectedTextColor(),
                    selected ? theme.getSelectedTextColor() : theme.getUnselectedTextColor(),
                    theme);
            } else {
                textView.setTextColor(selected ? theme.getSelectedTextColor() : theme.getUnselectedTextColor());
            }
            
            if (tabType == TabType.ROUNDED_PILL) {
                updatePillBackground(textView, selected);
            }
        } else if (tag instanceof ImageView) {
            ImageView imageView = (ImageView) tag;
            imageView.setColorFilter(selected ? theme.getSelectedIconTint() : theme.getUnselectedIconTint());
        } else if (tag instanceof View[]) {
            View[] views = (View[]) tag;
            for (View view : views) {
                if (view instanceof TextView) {
                    TextView tv = (TextView) view;
                    if (theme.isAnimationEnabled()) {
                        animator.animateTextColor(tv,
                            selected ? theme.getUnselectedTextColor() : theme.getSelectedTextColor(),
                            selected ? theme.getSelectedTextColor() : theme.getUnselectedTextColor(),
                            theme);
                    } else {
                        tv.setTextColor(selected ? theme.getSelectedTextColor() : theme.getUnselectedTextColor());
                    }
                } else if (view instanceof ImageView) {
                    ImageView iv = (ImageView) view;
                    iv.setColorFilter(selected ? theme.getSelectedIconTint() : theme.getUnselectedIconTint());
                }
            }
        }
        
        // Scale animation
        if (theme.isAnimationEnabled()) {
            animator.animateScale(tabView, selected, theme);
        }
    }
    
    /**
     * Animate indicator to selected tab position
     */
    private void animateIndicatorToTab(int position) {
        if (position < 0 || position >= tabViews.size()) return;
        
        View tabView = tabViews.get(position);
        float targetX = tabView.getX();
        float targetWidth = tabView.getWidth();
        
        // Adjust for padding
        int padding = theme.getTabPadding();
        targetX += padding;
        targetWidth -= padding * 2;
        
        // If indicator width is specified, center it
        if (theme.getIndicatorWidth() > 0) {
            targetX = tabView.getX() + (tabView.getWidth() - theme.getIndicatorWidth()) / 2;
            targetWidth = theme.getIndicatorWidth();
        }
        
        float currentX = indicator.getX();
        float currentWidth = indicator.getWidth();
        
        animator.animateIndicator(indicator, currentX, targetX, currentWidth, targetWidth, theme);
    }
    
    /**
     * Set tab type
     */
    public void setTabType(TabType type) {
        this.tabType = type;
        refreshTabs();
    }
    
    /**
     * Set indicator style
     */
    public void setIndicatorStyle(@IndicatorStyle int style) {
        theme.setIndicatorStyle(style);
        indicator.setIndicatorStyle(style);
        invalidate();
    }
    
    /**
     * Set indicator color
     */
    public void setIndicatorColor(int color) {
        theme.setIndicatorColor(color);
        indicator.setIndicatorColor(color);
    }
    
    /**
     * Set indicator height
     */
    public void setIndicatorHeight(int height) {
        theme.setIndicatorHeight(height);
    }
    
    /**
     * Set selected text color
     */
    public void setSelectedTabTextColor(int color) {
        theme.setSelectedTextColor(color);
        refreshTabs();
    }
    
    /**
     * Set unselected text color
     */
    public void setUnselectedTabTextColor(int color) {
        theme.setUnselectedTextColor(color);
        refreshTabs();
    }
    
    /**
     * Set selected icon tint
     */
    public void setSelectedTabIconTint(int color) {
        theme.setSelectedIconTint(color);
        refreshTabs();
    }
    
    /**
     * Set unselected icon tint
     */
    public void setUnselectedTabIconTint(int color) {
        theme.setUnselectedIconTint(color);
        refreshTabs();
    }
    
    /**
     * Set theme
     */
    public void setTheme(TabTheme theme) {
        this.theme = theme;
        indicator.setTheme(theme);
        setBackgroundColor(theme.getBackgroundColor());
        refreshTabs();
    }
    
    /**
     * Apply preset theme
     */
    public void setTheme(String themeName) {
        ThemeManager.getInstance(getContext()).applyTheme(themeName);
        setTheme(ThemeManager.getInstance(getContext()).getCurrentTheme());
    }
    
    /**
     * Set animation enabled
     */
    public void setAnimationEnabled(boolean enabled) {
        theme.setAnimationEnabled(enabled);
    }
    
    /**
     * Set animation duration
     */
    public void setAnimationDuration(int duration) {
        theme.setAnimationDuration(duration);
    }
    
    /**
     * Set tab mode
     */
    public void setTabMode(int mode) {
        this.tabMode = mode;
        refreshTabs();
    }
    
    /**
     * Set tab gravity
     */
    public void setTabGravity(int gravity) {
        this.tabGravity = gravity;
        tabContainer.setGravity(gravity);
    }
    
    /**
     * Set text size
     */
    public void setTabTextSize(int sizeSp) {
        theme.setTextSize(sizeSp);
        refreshTabs();
    }
    
    /**
     * Set icon size
     */
    public void setTabIconSize(int sizeDp) {
        theme.setIconSize(dpToPx(sizeDp));
        refreshTabs();
    }
    
    /**
     * Set tab padding
     */
    public void setTabPadding(int paddingDp) {
        theme.setTabPadding(dpToPx(paddingDp));
        refreshTabs();
    }
    
    /**
     * Set tab spacing
     */
    public void setTabSpacing(int spacingDp) {
        theme.setTabSpacing(dpToPx(spacingDp));
        refreshTabs();
    }
    
    /**
     * Set corner radius for pill tabs
     */
    public void setTabCornerRadius(int radiusDp) {
        theme.setCornerRadius(dpToPx(radiusDp));
        refreshTabs();
    }
    
    /**
     * Set ripple color
     */
    public void setTabRippleColor(int color) {
        theme.setRippleColor(color);
    }
    
    /**
     * Set background color
     */
    public void setTabBackgroundColor(int color) {
        theme.setBackgroundColor(color);
        setBackgroundColor(color);
    }
    
    /**
     * Set selected background color
     */
    public void setSelectedTabBackgroundColor(int color) {
        theme.setSelectedBackgroundColor(color);
        refreshTabs();
    }
    
    /**
     * Set unselected background color
     */
    public void setUnselectedTabBackgroundColor(int color) {
        theme.setUnselectedBackgroundColor(color);
        refreshTabs();
    }
    
    /**
     * Set elevation
     */
    public void setTabElevation(int elevationDp) {
        theme.setElevation(dpToPx(elevationDp));
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            setElevation(dpToPx(elevationDp));
        }
    }
    
    /**
     * Set on tab selected listener
     */
    public void setOnTabSelectedListener(OnTabSelectedListener listener) {
        this.listener = listener;
    }
    
    /**
     * Attach to ViewPager
     */
    public void attachToViewPager(ViewPager viewPager) {
        this.viewPager = viewPager;
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {}
            
            @Override
            public void onPageSelected(int position) {
                selectTab(position);
            }
            
            @Override
            public void onPageScrollStateChanged(int state) {}
        });
    }
    
    /**
     * Attach to ViewPager2
     */
    public void attachToViewPager2(ViewPager2 viewPager2) {
        this.viewPager2 = viewPager2;
        viewPager2.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                selectTab(position);
            }
        });
    }
    
    /**
     * Get selected tab position
     */
    public int getSelectedTabPosition() {
        return selectedPosition;
    }
    
    /**
     * Get tab count
     */
    public int getTabCount() {
        return tabs.size();
    }
    
    /**
     * Get tab at position
     */
    public TabItem getTabAt(int position) {
        if (position >= 0 && position < tabs.size()) {
            return tabs.get(position);
        }
        return null;
    }
    
    /**
     * Enable/disable a tab
     */
    public void setTabEnabled(int position, boolean enabled) {
        if (position >= 0 && position < tabs.size()) {
            tabs.get(position).setEnabled(enabled);
            if (position < tabViews.size()) {
                tabViews.get(position).setEnabled(enabled);
                tabViews.get(position).setAlpha(enabled ? 1.0f : 0.5f);
            }
        }
    }
    
    /**
     * Set badge count for a tab
     */
    public void setTabBadge(int position, int count) {
        if (position >= 0 && position < tabs.size()) {
            tabs.get(position).setBadgeCount(count);
            // TODO: Update badge view
        }
    }
    
    /**
     * Refresh all tabs
     */
    private void refreshTabs() {
        List<TabItem> currentTabs = new ArrayList<>(tabs);
        removeAllTabs();
        for (TabItem tab : currentTabs) {
            addTab(tab);
        }
        if (selectedPosition >= 0 && selectedPosition < tabs.size()) {
            selectTab(selectedPosition);
        }
    }
    
    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        
        // Position indicator
        if (selectedPosition >= 0 && selectedPosition < tabViews.size()) {
            View tabView = tabViews.get(selectedPosition);
            float targetX = tabView.getX();
            float targetWidth = tabView.getWidth();
            
            int padding = theme.getTabPadding();
            targetX += padding;
            targetWidth -= padding * 2;
            
            if (theme.getIndicatorWidth() > 0) {
                targetX = tabView.getX() + (tabView.getWidth() - theme.getIndicatorWidth()) / 2;
                targetWidth = theme.getIndicatorWidth();
            }
            
            indicator.setX(targetX);
            indicator.getLayoutParams().width = (int) targetWidth;
            indicator.getLayoutParams().height = getHeight();
            indicator.requestLayout();
        }
    }
    
    private int dpToPx(int dp) {
        return (int) TypedValue.applyDimension(
            TypedValue.COMPLEX_UNIT_DIP,
            dp,
            getResources().getDisplayMetrics()
        );
    }
}
