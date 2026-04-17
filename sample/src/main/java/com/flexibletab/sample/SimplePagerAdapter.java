package com.flexibletab.sample;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

/**
 * Simple pager adapter for demo ViewPager integration.
 */
public class SimplePagerAdapter extends FragmentPagerAdapter {
    
    private String[] titles;
    
    public SimplePagerAdapter(FragmentManager fm, String[] titles) {
        super(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        this.titles = titles;
    }
    
    @NonNull
    @Override
    public Fragment getItem(int position) {
        return ContentFragment.newInstance(titles[position]);
    }
    
    @Override
    public int getCount() {
        return titles.length;
    }
    
    @Override
    public CharSequence getPageTitle(int position) {
        return titles[position];
    }
    
    /**
     * Simple content fragment
     */
    public static class ContentFragment extends Fragment {
        
        private static final String ARG_TITLE = "title";
        
        public static ContentFragment newInstance(String title) {
            ContentFragment fragment = new ContentFragment();
            Bundle args = new Bundle();
            args.putString(ARG_TITLE, title);
            fragment.setArguments(args);
            return fragment;
        }
        
        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
            TextView textView = new TextView(getContext());
            textView.setText(getArguments().getString(ARG_TITLE) + " Content");
            textView.setTextSize(18);
            textView.setGravity(android.view.Gravity.CENTER);
            textView.setPadding(32, 32, 32, 32);
            return textView;
        }
    }
}
