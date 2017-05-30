package com.maumqmaum.androidnewarchpractice;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.util.Log;

import com.maumqmaum.androidnewarchpractice.ui.fragment.ArticleDetailFragment;
import com.maumqmaum.androidnewarchpractice.ui.fragment.MainFragment;

public class NavigationController {

    private static final String FRAGMENT_MAIN = "main";

    private final FragmentManager fragmentManager;

    public NavigationController(MainActivity mainActivity) {
        this.fragmentManager = mainActivity.getSupportFragmentManager();
    }

    void navigateToMain() {
        Log.d("NavigationController", "navigateToMain");
        MainFragment mainFragment = new MainFragment();
        fragmentManager.beginTransaction()
                .replace(R.id.container, mainFragment, FRAGMENT_MAIN)
                .commit();
    }

    public void navigateToArticleDetail(int position) {
        Log.d("NavigationController", "navigateToArticleDetail");
        ArticleDetailFragment fragment = ArticleDetailFragment.newInstance(position);
        String tag = "article" + "/" + position;
        fragmentManager.beginTransaction()
                .add(R.id.container, fragment, tag)
                .addToBackStack(tag)
                .hide(getLatestFragmentOfBackStack())
                .commit();
    }

    private Fragment getLatestFragmentOfBackStack() {
        int backStackCount = fragmentManager.getBackStackEntryCount();
        String fragmentTag;
        if (backStackCount == 0) {
            fragmentTag = FRAGMENT_MAIN;
        } else {
            fragmentTag = fragmentManager.getBackStackEntryAt(backStackCount - 1).getName();
        }
        return fragmentManager.findFragmentByTag(fragmentTag);
    }
}
