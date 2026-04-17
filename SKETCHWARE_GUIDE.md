# Sketchware Pro Integration Guide

This guide shows how to use FlexibleTabLayout in Sketchware Pro projects.

## Step 1: Add Library Files

1. Open your Sketchware Pro project
2. Go to **Local Libraries** or **More Block** section
3. Add the FlexibleTabLayout Java files to your project

### Required Files:
- `FlexibleTabLayout.java` - Main custom view
- `TabItem.java` - Tab data model
- `TabType.java` - Tab type enum
- `TabTheme.java` - Theme configuration
- `IndicatorStyle.java` - Indicator style annotation
- `ThemeManager.java` - Theme manager
- `TabAnimator.java` - Animation helper
- `TabIndicator.java` - Indicator view
- `OnTabSelectedListener.java` - Listener interface
- `SimpleOnTabSelectedListener.java` - Simple adapter

## Step 2: Create a More Block

Create a More Block called `initializeTabLayout`:

```java
// Input: LinearLayout container
// Output: FlexibleTabLayout tabLayout

FlexibleTabLayout _tabLayout = new FlexibleTabLayout(MainActivity.this);
_tabLayout.setLayoutParams(new LinearLayout.LayoutParams(
    LinearLayout.LayoutParams.MATCH_PARENT,
    (int) (48 * getResources().getDisplayMetrics().density)
));

// Add tabs
_tabLayout.addTab("Home");
_tabLayout.addTab("Profile");
_tabLayout.addTab("Settings");

// Set theme
_tabLayout.setTheme("material");

// Add to container
container.addView(_tabLayout);

// Return the tab layout for later use
_tabLayout;
```

## Step 3: Handle Tab Selection

Create another More Block called `onTabSelected`:

```java
// Input: FlexibleTabLayout tabLayout, int position

_tabLayout.setOnTabSelectedListener(new SimpleOnTabSelectedListener() {
    @Override
    public void onTabSelected(TabItem tab, int position) {
        // Use Sketchware block to show message
        showMessage("Selected: " + position);
        
        // Switch views based on position
        switch(position) {
            case 0:
                // Show home layout
                homeLayout.setVisibility(View.VISIBLE);
                profileLayout.setVisibility(View.GONE);
                break;
            case 1:
                // Show profile layout
                homeLayout.setVisibility(View.GONE);
                profileLayout.setVisibility(View.VISIBLE);
                break;
        }
    }
});
```

## Common Examples

### Instagram-Style Profile Tabs

```java
// More Block: setupInstagramTabs
FlexibleTabLayout _tabs = new FlexibleTabLayout(MainActivity.this);
_tabs.setTabType(TabType.INSTAGRAM);
_tabs.setTheme("instagram");
_tabs.setTabMode(FlexibleTabLayout.MODE_FIXED);

// Use Sketchware image resources
_tabs.addTab(getDrawable("ic_grid"));
_tabs.addTab(getDrawable("ic_list"));
_tabs.addTab(getDrawable("ic_tagged"));

_tabs.setOnTabSelectedListener(new SimpleOnTabSelectedListener() {
    @Override
    public void onTabSelected(TabItem tab, int position) {
        // Switch between grid/list/tagged views
        if (position == 0) {
            gridView.setVisibility(View.VISIBLE);
            listView.setVisibility(View.GONE);
            taggedView.setVisibility(View.GONE);
        } else if (position == 1) {
            gridView.setVisibility(View.GONE);
            listView.setVisibility(View.VISIBLE);
            taggedView.setVisibility(View.GONE);
        } else {
            gridView.setVisibility(View.GONE);
            listView.setVisibility(View.GONE);
            taggedView.setVisibility(View.VISIBLE);
        }
    }
});

container.addView(_tabs);
```

### Google-Style Navigation Tabs

```java
// More Block: setupGoogleTabs
FlexibleTabLayout _tabs = new FlexibleTabLayout(MainActivity.this);
_tabs.setTabType(TabType.MATERIAL);
_tabs.setTheme("google");

_tabs.addTab("HOME");
_tabs.addTab("TRENDING");
_tabs.addTab("SUBSCRIPTIONS");

// Optional: Add icons
// _tabs.addTab("HOME", getDrawable("ic_home"));

container.addView(_tabs);
```

### TikTok-Style Tabs

```java
// More Block: setupTikTokTabs
FlexibleTabLayout _tabs = new FlexibleTabLayout(MainActivity.this);
_tabs.setTabType(TabType.TIKTOK);
_tabs.setTheme("tiktok");
_tabs.setTabTextSize(18);

_tabs.addTab("Following");
_tabs.addTab("For You");

container.addView(_tabs);
```

### Pill-Style Segmented Control

```java
// More Block: setupPillTabs
FlexibleTabLayout _tabs = new FlexibleTabLayout(MainActivity.this);
_tabs.setTabType(TabType.ROUNDED_PILL);
_tabs.setTheme("pill");
_tabs.setTabCornerRadius(20);
_tabs.setTabPadding(16);

_tabs.addTab("Day");
_tabs.addTab("Week");
_tabs.addTab("Month");

container.addView(_tabs);
```

## Tips for Sketchware Pro

### 1. Use getDrawable() for Icons
```java
// In Sketchware, use your project's image resources
Drawable icon = getDrawable("ic_home");
tabLayout.addTab("Home", icon);
```

### 2. Dynamic Tab Content
```java
// Switch between Sketchware layouts based on tab
@Override
public void onTabSelected(TabItem tab, int position) {
    // Hide all layouts first
    layout1.setVisibility(View.GONE);
    layout2.setVisibility(View.GONE);
    layout3.setVisibility(View.GONE);
    
    // Show selected layout
    if (position == 0) layout1.setVisibility(View.VISIBLE);
    else if (position == 1) layout2.setVisibility(View.VISIBLE);
    else if (position == 2) layout3.setVisibility(View.VISIBLE);
}
```

### 3. Runtime Theme Changes
```java
// Switch theme on button click
buttonDark.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        tabLayout.setTheme("dark");
    }
});

buttonLight.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        tabLayout.setTheme("light");
    }
});
```

### 4. Tab with Badge
```java
// Show notification badge on tab
tabLayout.setTabBadge(2, 5); // Position 2, 5 notifications
```

### 5. Enable/Disable Tabs
```java
// Disable specific tab
tabLayout.setTabEnabled(1, false); // Disable second tab
```

## Troubleshooting

### ClassNotFoundException
- Make sure all Java files are added to your project
- Check package names match your project structure

### Resources Not Found
- Add `attrs.xml` to your project's `res/values` folder
- Verify drawable resources exist

### Theme Not Applied
- Call `setTheme()` after adding tabs
- Use `refreshTabs()` if needed

## Full Example Project Structure

```
MySketchwareProject/
├── java/
│   ├── com/flexibletab/
│   │   ├── FlexibleTabLayout.java
│   │   ├── model/
│   │   │   ├── TabItem.java
│   │   │   └── TabType.java
│   │   ├── theme/
│   │   │   ├── TabTheme.java
│   │   │   ├── IndicatorStyle.java
│   │   │   └── ThemeManager.java
│   │   ├── animation/
│   │   │   └── TabAnimator.java
│   │   ├── indicator/
│   │   │   └── TabIndicator.java
│   │   └── listener/
│   │       ├── OnTabSelectedListener.java
│   │       └── SimpleOnTabSelectedListener.java
│   └── com/myapp/
│       └── MainActivity.java
└── res/
    ├── values/
    │   └── attrs.xml
    └── drawable/
        ├── ic_home.png
        ├── ic_search.png
        └── ic_profile.png
```

## Quick Reference Card

### Create Tab Layout
```java
FlexibleTabLayout tabs = new FlexibleTabLayout(this);
```

### Add Tabs
```java
tabs.addTab("Title");
tabs.addTab(iconDrawable);
tabs.addTab("Title", iconDrawable);
```

### Set Theme
```java
tabs.setTheme("instagram");
tabs.setTheme("google");
tabs.setTheme("tiktok");
tabs.setTheme("pill");
tabs.setTheme("material");
```

### Handle Clicks
```java
tabs.setOnTabSelectedListener(new SimpleOnTabSelectedListener() {
    @Override
    public void onTabSelected(TabItem tab, int position) {
        // Your code here
    }
});
```

### Add to Layout
```java
myLayout.addView(tabs);
```

That's it! You now have a fully functional tab layout in your Sketchware Pro project.
