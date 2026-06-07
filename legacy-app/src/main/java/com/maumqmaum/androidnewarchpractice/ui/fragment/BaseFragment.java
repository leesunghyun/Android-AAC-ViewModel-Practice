package com.maumqmaum.androidnewarchpractice.ui.fragment;

import android.support.v4.app.Fragment;

import com.maumqmaum.androidnewarchpractice.MainActivity;
import com.maumqmaum.androidnewarchpractice.NavigationController;
import com.maumqmaum.androidnewarchpractice.di.component.FragmentComponent;

import javax.inject.Inject;

public abstract class BaseFragment extends Fragment {

    private FragmentComponent fragmentComponent;

    @Inject
    NavigationController navigationController;

    public FragmentComponent getComponent() {
        if (fragmentComponent != null) {
            return fragmentComponent;
        }
        fragmentComponent = ((MainActivity) getActivity()).getComponent().plus();
        return fragmentComponent;
    }
}
