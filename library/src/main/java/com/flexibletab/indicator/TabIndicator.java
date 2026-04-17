package com.flexibletab.indicator;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.TypedValue;
import android.view.View;

import com.flexibletab.theme.IndicatorStyle;
import com.flexibletab.theme.TabTheme;

/**
 * Custom view for drawing tab indicators in various styles.
 */
public class TabIndicator extends View {
    
    private Paint paint;
    private RectF rectF;
    private TabTheme theme;
    private float indicatorX = 0;
    private float indicatorWidth = 0;
    private float indicatorHeight = 0;
    private @IndicatorStyle int style = IndicatorStyle.UNDERLINE;
    
    public TabIndicator(Context context) {
        super(context);
        init();
    }
    
    private void init() {
        paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        rectF = new RectF();
        setLayerType(LAYER_TYPE_SOFTWARE, null);
    }
    
    public void setTheme(TabTheme theme) {
        this.theme = theme;
        this.style = theme.getIndicatorStyle();
        paint.setColor(theme.getIndicatorColor());
        invalidate();
    }
    
    public void setIndicatorStyle(@IndicatorStyle int style) {
        this.style = style;
        invalidate();
    }
    
    public void setIndicatorPosition(float x, float width, float height) {
        this.indicatorX = x;
        this.indicatorWidth = width;
        this.indicatorHeight = height;
        invalidate();
    }
    
    public void setIndicatorColor(int color) {
        paint.setColor(color);
        invalidate();
    }
    
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        
        if (style == IndicatorStyle.NONE) return;
        
        float left = indicatorX;
        float right = indicatorX + indicatorWidth;
        float top = getHeight() - indicatorHeight;
        float bottom = getHeight();
        
        switch (style) {
            case IndicatorStyle.UNDERLINE:
                drawUnderline(canvas, left, top, right, bottom);
                break;
            case IndicatorStyle.MATERIAL:
                drawMaterial(canvas, left, top, right, bottom);
                break;
            case IndicatorStyle.PILL:
                drawPill(canvas, left, top, right, bottom);
                break;
            case IndicatorStyle.DOT:
                drawDot(canvas, left, right, bottom);
                break;
            case IndicatorStyle.CAPSULE:
                drawCapsule(canvas, left, top, right, bottom);
                break;
        }
    }
    
    private void drawUnderline(Canvas canvas, float left, float top, float right, float bottom) {
        rectF.set(left, top, right, bottom);
        float radius = theme != null ? theme.getIndicatorRadius() : 0;
        canvas.drawRoundRect(rectF, radius, radius, paint);
    }
    
    private void drawMaterial(Canvas canvas, float left, float top, float right, float bottom) {
        // Material style has full width indicator with slight height
        rectF.set(left, top, right, bottom);
        float radius = theme != null ? theme.getIndicatorRadius() : dpToPx(2);
        canvas.drawRoundRect(rectF, radius, radius, paint);
    }
    
    private void drawPill(Canvas canvas, float left, float top, float right, float bottom) {
        // Pill style draws a rounded rectangle around the tab
        float padding = dpToPx(8);
        rectF.set(left - padding, top - dpToPx(4), right + padding, bottom);
        float radius = theme != null ? theme.getCornerRadius() : dpToPx(20);
        canvas.drawRoundRect(rectF, radius, radius, paint);
    }
    
    private void drawDot(Canvas canvas, float left, float right, float bottom) {
        // Dot style draws a small circle in the center
        float centerX = (left + right) / 2;
        float radius = theme != null ? theme.getIndicatorHeight() : dpToPx(3);
        canvas.drawCircle(centerX, bottom - radius - dpToPx(2), radius, paint);
    }
    
    private void drawCapsule(Canvas canvas, float left, float top, float right, float bottom) {
        // Capsule style - full rounded background
        float padding = dpToPx(4);
        rectF.set(left + padding, padding, right - padding, getHeight() - padding);
        float radius = (getHeight() - 2 * padding) / 2f;
        canvas.drawRoundRect(rectF, radius, radius, paint);
    }
    
    private int dpToPx(int dp) {
        return (int) TypedValue.applyDimension(
            TypedValue.COMPLEX_UNIT_DIP, 
            dp, 
            getResources().getDisplayMetrics()
        );
    }
}
