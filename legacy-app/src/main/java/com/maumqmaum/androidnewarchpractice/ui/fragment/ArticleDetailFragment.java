package com.maumqmaum.androidnewarchpractice.ui.fragment;

import android.arch.lifecycle.LifecycleRegistry;
import android.arch.lifecycle.LifecycleRegistryOwner;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.maumqmaum.androidnewarchpractice.R;
import com.maumqmaum.androidnewarchpractice.databinding.FragmentArticleDetailBinding;
import com.maumqmaum.androidnewarchpractice.model.Article;
import com.maumqmaum.androidnewarchpractice.viewmodel.ArticleListViewModel;

import java.util.List;

public class ArticleDetailFragment extends BaseFragment implements LifecycleRegistryOwner {

    private static final String KEY_POSITION = "position";

    private LifecycleRegistry lifecycleRegistry = new LifecycleRegistry(this);
    private FragmentArticleDetailBinding binding;
    private MutableLiveData<Article> liveArticle;

    public static ArticleDetailFragment newInstance(int position) {
        ArticleDetailFragment articleDetailFragment = new ArticleDetailFragment();
        Bundle bundle = new Bundle();
        bundle.putInt(KEY_POSITION, position);
        articleDetailFragment.setArguments(bundle);
        return articleDetailFragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        Bundle args = getArguments();
        if (args != null) {
            initArticle(args.getInt(KEY_POSITION));
        }
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_article_detail, container, false);
        return binding.getRoot();
    }

    private void initArticle(int position) {
        ArticleListViewModel viewModel = ViewModelProviders.of(getActivity()).get(ArticleListViewModel.class);
        List<MutableLiveData<Article>> list = viewModel.getArticleList().getValue();
        if (list != null) {
            liveArticle = list.get(position);
        }
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Button saveButton = (Button) view.findViewById(R.id.save_button);
        liveArticle.observe(this, new Observer<Article>() {
            @Override
            public void onChanged(@Nullable Article article) {
                if (article == null) {
                    return;
                }
                binding.setArticle(article);
            }
        });

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                liveArticle.postValue(binding.getArticle());
                Toast.makeText(getContext(), "Saved!", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public LifecycleRegistry getLifecycle() {
        return lifecycleRegistry;
    }
}
