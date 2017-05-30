package com.maumqmaum.androidnewarchpractice.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.maumqmaum.androidnewarchpractice.R;

public class MainFragment extends Fragment {

    private enum Tab {
        ARTICLE_LIST("Article"),
        ARTICLE_LIST2("Article2"),
        ARTICLE_LIST3("Article3");

        String title;

        Tab(String title) {
            this.title = title;
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_main, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        TabLayout tabLayout = (TabLayout) view.findViewById(R.id.tab_layout);
        for (Tab tab : Tab.values()) {
            tabLayout.addTab(tabLayout.newTab().setText(tab.title));
        }
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

        final ViewPager viewPager = (ViewPager) view.findViewById(R.id.view_pager);
        final PagerAdapter adapter = new PagerAdapter(getFragmentManager());
        viewPager.setAdapter(adapter);
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());

            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }

    private class PagerAdapter extends FragmentStatePagerAdapter {

        private PagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {

            switch (Tab.values()[position]) {
                case ARTICLE_LIST:
                case ARTICLE_LIST2:
                case ARTICLE_LIST3:
                    return new ArticleListFragment();
                default:
                    return null;
            }
        }

        @Override
        public int getCount() {
            return Tab.values().length;
        }
    }
}
