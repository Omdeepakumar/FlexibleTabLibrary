# FlexibleTabLayout

[![](https://jitpack.io/v/Omdeepakumar/FlexibleTabLibrary.svg)](https://jitpack.io/#Omdeepakumar/FlexibleTabLibrary)

A flexible, customizable Android tab layout library that supports multiple tab types, indicator styles, themes, and smooth animations.

## Features

- **11 Tab Types**: Text-only, icon-only, image-only, icon+text, image+text, indicator-only, rounded pill, underline, material, Instagram, TikTok
- **6 Indicator Styles**: Underline, pill, dot, none, material, capsule
- **7 Preset Themes**: Light, dark, Instagram, Google, TikTok, pill, material
- **Custom Themes**: Build your own with the theme builder
- **Smooth Animations**: Color transitions, indicator movement, scale effects
- **ViewPager Support**: Works with ViewPager and ViewPager2
- **Runtime Theme Switching**: Change themes dynamically
- **Sketchware Pro Compatible**: Easy to integrate with Sketchware Pro

## Installation (JitPack)

### Step 1: Add JitPack repository

Add the JitPack repository to your root `settings.gradle` or `build.gradle`:

**settings.gradle (Recommended):**
```gradle
dependencyResolutionManagement {
    repositories {
        google()
        mavenCentral()
        maven { url 'https://jitpack.io' }
    }
}
```

**OR root build.gradle:**
```gradle
allprojects {
    repositories {
        google()
        mavenCentral()
        maven { url 'https://jitpack.io' }
    }
}
```

### Step 2: Add the dependency

Add the dependency in your app-level `build.gradle`:

```gradle
dependencies {
    implementation 'com.github.Omdeepakumar:FlexibleTabLibrary:1.0.0'
}
```

## Quick Start

### Basic Usage (Java)

```java
// Create tab layout
FlexibleTabLayout tabLayout = new FlexibleTabLayout(this);

// Add tabs
tabLayout.addTab("Home");
tabLayout.addTab("Profile");
tabLayout.addTab("Settings");

// Set listener
tabLayout.setOnTabSelectedListener(new SimpleOnTabSelectedListener() {
    @Override
    public void onTabSelected(TabItem tab, int position) {
        // Handle tab selection
    }
});

// Add to layout
layout.addView(tabLayout);
```

### Instagram Style Tabs

```java
FlexibleTabLayout tabLayout = new FlexibleTabLayout(this);
tabLayout.setTabType(TabType.INSTAGRAM);
tabLayout.setTheme("instagram");

// Add icon-only tabs
tabLayout.addTab(gridIcon);
tabLayout.addTab(listIcon);
tabLayout.addTab(taggedIcon);
```

### Google Style Tabs

```java
FlexibleTabLayout tabLayout = new FlexibleTabLayout(this);
tabLayout.setTabType(TabType.MATERIAL);
tabLayout.setTheme("google");
tabLayout.setTabMode(FlexibleTabLayout.MODE_FIXED);

tabLayout.addTab("HOME");
tabLayout.addTab("TRENDING");
tabLayout.addTab("SUBSCRIPTIONS");
```

### TikTok Style Tabs

```java
FlexibleTabLayout tabLayout = new FlexibleTabLayout(this);
tabLayout.setTabType(TabType.TIKTOK);
tabLayout.setTheme("tiktok");

tabLayout.addTab("Following");
tabLayout.addTab("For You");
```

### Pill Style Tabs

```java
FlexibleTabLayout tabLayout = new FlexibleTabLayout(this);
tabLayout.setTabType(TabType.ROUNDED_PILL);
tabLayout.setTheme("pill");
tabLayout.setTabCornerRadius(20);
tabLayout.setTabPadding(16);

tabLayout.addTab("Day");
tabLayout.addTab("Week");
tabLayout.addTab("Month");
```

## Tab Types

| Type | Description |
|------|-------------|
| `TEXT_ONLY` | Text-only tabs |
| `ICON_ONLY` | Icon-only tabs |
| `IMAGE_ONLY` | Image-only tabs |
| `ICON_TEXT` | Icon with text |
| `IMAGE_TEXT` | Image with text |
| `INDICATOR_ONLY` | Text with indicator only |
| `ROUNDED_PILL` | Rounded pill-style tabs |
| `UNDERLINE` | Text with underline indicator |
| `MATERIAL` | Material Design style |
| `INSTAGRAM` | Instagram profile style |
| `TIKTOK` | TikTok minimal style |

## Indicator Styles

| Style | Description |
|-------|-------------|
| `UNDERLINE` | Bottom underline |
| `PILL` | Pill-shaped background |
| `DOT` | Small dot indicator |
| `NONE` | No indicator |
| `MATERIAL` | Material Design underline |
| `CAPSULE` | Capsule-shaped indicator |

## Preset Themes

Apply preset themes using:

```java
tabLayout.setTheme("instagram");  // Instagram style
tabLayout.setTheme("google");     // Google style
tabLayout.setTheme("tiktok");     // TikTok style
tabLayout.setTheme("pill");       // Pill style
tabLayout.setTheme("material");   // Material style
tabLayout.setTheme("light");      // Light theme
tabLayout.setTheme("dark");       // Dark theme
```

## Custom Themes

Build custom themes with the Theme Builder:

```java
TabTheme customTheme = new TabTheme.Builder()
    .backgroundColor(Color.WHITE)
    .selectedTextColor(Color.BLACK)
    .unselectedTextColor(Color.GRAY)
    .indicatorColor(Color.BLUE)
    .indicatorHeight(4)
    .indicatorStyle(IndicatorStyle.UNDERLINE)
    .cornerRadius(20)
    .animationEnabled(true)
    .animationDuration(300)
    .build();

tabLayout.setTheme(customTheme);
```

## ViewPager Integration

### ViewPager

```java
ViewPager viewPager = findViewById(R.id.viewPager);
viewPager.setAdapter(adapter);
tabLayout.attachToViewPager(viewPager);
```

### ViewPager2

```java
ViewPager2 viewPager2 = findViewById(R.id.viewPager2);
viewPager2.setAdapter(adapter);
tabLayout.attachToViewPager2(viewPager2);
```

## Runtime Theme Switching

```java
// Switch to dark theme
tabLayout.setTheme("dark");

// Or apply custom theme at runtime
TabTheme newTheme = new TabTheme.Builder()
    .backgroundColor(Color.BLACK)
    .selectedTextColor(Color.WHITE)
    .build();
tabLayout.setTheme(newTheme);
```

## Customization Options

### Colors
- `setSelectedTabTextColor(int color)`
- `setUnselectedTabTextColor(int color)`
- `setSelectedTabIconTint(int color)`
- `setUnselectedTabIconTint(int color)`
- `setIndicatorColor(int color)`
- `setTabBackgroundColor(int color)`
- `setTabRippleColor(int color)`

### Sizes
- `setTabTextSize(int sizeSp)`
- `setTabIconSize(int sizeDp)`
- `setTabPadding(int paddingDp)`
- `setTabSpacing(int spacingDp)`
- `setIndicatorHeight(int height)`
- `setIndicatorWidth(int width)`
- `setTabCornerRadius(int radiusDp)`

### Behavior
- `setAnimationEnabled(boolean enabled)`
- `setAnimationDuration(int durationMs)`
- `setTabMode(int mode)` - `MODE_FIXED`, `MODE_SCROLLABLE`, `MODE_AUTO`
- `setTabGravity(int gravity)`

## Tab Events

```java
tabLayout.setOnTabSelectedListener(new OnTabSelectedListener() {
    @Override
    public void onTabSelected(TabItem tab, int position) {
        // Tab selected
    }
    
    @Override
    public void onTabUnselected(TabItem tab, int position) {
        // Tab unselected
    }
    
    @Override
    public void onTabReselected(TabItem tab, int position) {
        // Tab reselected (clicked again)
    }
});
```

Or use the simplified adapter:

```java
tabLayout.setOnTabSelectedListener(new SimpleOnTabSelectedListener() {
    @Override
    public void onTabSelected(TabItem tab, int position) {
        // Only override what you need
    }
});
```

## Sketchware Pro Integration

### Adding to Sketchware Pro

1. **Add Library Files**:
   - Copy all Java files from `com/flexibletab` package to your Sketchware project
   - Copy `attrs.xml` to your resources

2. **Create in More Block**:
   ```java
   // Initialize FlexibleTabLayout
   FlexibleTabLayout tabLayout = new FlexibleTabLayout(this);
   ```

3. **Add Tabs**:
   ```java
   tabLayout.addTab("Tab 1");
   tabLayout.addTab("Tab 2");
   tabLayout.addTab("Tab 3");
   ```

4. **Set Theme**:
   ```java
   tabLayout.setTheme("instagram");
   ```

5. **Add to Layout**:
   ```java
   linearLayout.addView(tabLayout);
   ```

### Sketchware Example

```java
// More Block: initializeTabs
FlexibleTabLayout _tabLayout = new FlexibleTabLayout(MainActivity.this);
_tabLayout.setTabType(TabType.ICON_TEXT);
_tabLayout.setTheme("material");
_tabLayout.setTabMode(FlexibleTabLayout.MODE_FIXED);

_tabLayout.addTab("Home", getDrawable("ic_home"));
_tabLayout.addTab("Search", getDrawable("ic_search"));
_tabLayout.addTab("Profile", getDrawable("ic_profile"));

_tabLayout.setOnTabSelectedListener(new SimpleOnTabSelectedListener() {
    @Override
    public void onTabSelected(TabItem tab, int position) {
        // Switch content based on position
        switch(position) {
            case 0: // Home
                break;
            case 1: // Search
                break;
            case 2: // Profile
                break;
        }
    }
});

linearLayout.addView(_tabLayout);
```

## API Reference

### FlexibleTabLayout Methods

| Method | Description |
|--------|-------------|
| `addTab(String title)` | Add text tab |
| `addTab(Drawable icon)` | Add icon tab |
| `addTab(String title, Drawable icon)` | Add icon+text tab |
| `setTabs(List<TabItem> tabs)` | Set multiple tabs |
| `setTabs(String[] titles)` | Set tabs from array |
| `removeAllTabs()` | Remove all tabs |
| `selectTab(int position)` | Select tab by position |
| `getSelectedTabPosition()` | Get selected position |
| `getTabCount()` | Get number of tabs |
| `setTabEnabled(int position, boolean enabled)` | Enable/disable tab |
| `setTabBadge(int position, int count)` | Set badge count |

### Theme Methods

| Method | Description |
|--------|-------------|
| `setTheme(String themeName)` | Apply preset theme |
| `setTheme(TabTheme theme)` | Apply custom theme |
| `setTabType(TabType type)` | Set tab type |
| `setIndicatorStyle(int style)` | Set indicator style |
| `setIndicatorColor(int color)` | Set indicator color |
| `setIndicatorHeight(int height)` | Set indicator height |

## Requirements

- Android API 16+
- AndroidX AppCompat
- Java 8+

## License

MIT License - Free for personal and commercial use.

## Credits

Created for easy integration with Sketchware Pro and standard Android development.
