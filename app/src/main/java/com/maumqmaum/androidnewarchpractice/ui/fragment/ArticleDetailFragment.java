package com.maumqmaum.androidnewarchpractice.ui.fragment;

import android.arch.lifecycle.LifecycleRegistry;
import android.arch.lifecycle.LifecycleRegistryOwner;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.maumqmaum.androidnewarchpractice.R;
import com.maumqmaum.androidnewarchpractice.model.Article;
import com.maumqmaum.androidnewarchpractice.viewmodel.ArticleListViewModel;

import java.util.List;

public class ArticleDetailFragment extends Fragment implements LifecycleRegistryOwner {

    private static final String KEY_POSITION = "position";

    private LifecycleRegistry lifecycleRegistry = new LifecycleRegistry(this);

    private EditText titleView;
    private EditText descriptionView;
    private EditText authorView;
    private CheckBox likeCheckBox;

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
        return inflater.inflate(R.layout.fragment_article_detail, container, false);
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
        titleView = (EditText) view.findViewById(R.id.title);
        descriptionView = (EditText) view.findViewById(R.id.description);
        authorView = (EditText) view.findViewById(R.id.author);
        likeCheckBox = (CheckBox) view.findViewById(R.id.like_check_box);
        Button saveButton = (Button) view.findViewById(R.id.save_button);
        liveArticle.observe(this, new Observer<Article>() {
            @Override
            public void onChanged(@Nullable Article article) {
                if (article == null) {
                    return;
                }
                titleView.setText(article.title);
                descriptionView.setText(article.description);
                authorView.setText(article.author);
                likeCheckBox.setChecked(article.isLiked);
            }
        });

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Article article = liveArticle.getValue();
                if (article == null) {
                    return;
                }
                article.title = titleView.getText().toString();
                article.description = descriptionView.getText().toString();
                article.author = authorView.getText().toString();
                article.isLiked = likeCheckBox.isChecked();
                liveArticle.postValue(article);
                Toast.makeText(getContext(), "Saved!", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public LifecycleRegistry getLifecycle() {
        return lifecycleRegistry;
    }
}
