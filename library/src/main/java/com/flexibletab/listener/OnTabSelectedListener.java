package com.flexibletab.listener;

import com.flexibletab.model.TabItem;

/**
 * Interface for tab selection callbacks.
 */
public interface OnTabSelectedListener {
    /**
     * Called when a tab is selected
     * @param tab The selected tab
     * @param position Position of the selected tab
     */
    void onTabSelected(TabItem tab, int position);
    
    /**
     * Called when a tab is unselected
     * @param tab The unselected tab
     * @param position Position of the unselected tab
     */
    void onTabUnselected(TabItem tab, int position);
    
    /**
     * Called when a tab is reselected (clicked again)
     * @param tab The reselected tab
     * @param position Position of the reselected tab
     */
    void onTabReselected(TabItem tab, int position);
}
