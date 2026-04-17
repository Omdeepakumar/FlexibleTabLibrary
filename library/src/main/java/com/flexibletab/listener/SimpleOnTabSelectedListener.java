package com.flexibletab.listener;

import com.flexibletab.model.TabItem;

/**
 * Adapter class for OnTabSelectedListener.
 * Extend this to only override the methods you need.
 */
public abstract class SimpleOnTabSelectedListener implements OnTabSelectedListener {
    
    @Override
    public void onTabSelected(TabItem tab, int position) {
        // Override if needed
    }
    
    @Override
    public void onTabUnselected(TabItem tab, int position) {
        // Override if needed
    }
    
    @Override
    public void onTabReselected(TabItem tab, int position) {
        // Override if needed
    }
}
