package com.maumqmaum.androidnewarchpractice.ui.adapter;

import android.arch.lifecycle.LifecycleRegistryOwner;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.Observer;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import com.maumqmaum.androidnewarchpractice.NavigationController;
import com.maumqmaum.androidnewarchpractice.R;
import com.maumqmaum.androidnewarchpractice.model.Article;

import java.util.List;

public class ArticleListAdapter extends RecyclerView.Adapter<ArticleListAdapter.ArticleListViewHolder> {

    private List<MutableLiveData<Article>> articleList;

    private LifecycleRegistryOwner lifecycleOwner;

    private NavigationController navigationController;

    public ArticleListAdapter(LifecycleRegistryOwner lifecycleOwner, NavigationController navigationController) {
        this.lifecycleOwner = lifecycleOwner;
        this.navigationController = navigationController;
    }

    @Override
    public ArticleListViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_article, parent, false);
        return new ArticleListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ArticleListViewHolder holder, int position) {
        final MutableLiveData<Article> liveArticle = articleList.get(position);
        if (liveArticle.hasObservers()) {
            liveArticle.removeObservers(lifecycleOwner);
        }
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navigationController.navigateToArticleDetail(articleList.indexOf(liveArticle));
            }
        });

        holder.likeCheckBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CheckBox checkBox = (CheckBox) v;
                Article article = liveArticle.getValue();
                if (article != null) {
                    article.isLiked = checkBox.isChecked();
                }
                liveArticle.postValue(article);
            }
        });

        liveArticle.observe(lifecycleOwner, new Observer<Article>() {
            @Override
            public void onChanged(@Nullable Article article) {
                if (article == null) {
                    return;
                }
                holder.title.setText(article.title);
                holder.description.setText(article.description);
                holder.author.setText(article.author);
                holder.likeCheckBox.setChecked(article.isLiked);
            }
        });
    }

    @Override
    public int getItemCount() {
        return articleList.size();
    }

    public void setArticleList(List<MutableLiveData<Article>> articleList) {
        this.articleList = articleList;
        notifyDataSetChanged();
    }

    public List<MutableLiveData<Article>> getArticleList() {
        return articleList;
    }

    class ArticleListViewHolder extends RecyclerView.ViewHolder {

        TextView title;
        TextView description;
        TextView author;
        CheckBox likeCheckBox;

        ArticleListViewHolder(View itemView) {
            super(itemView);
            title = (TextView) itemView.findViewById(R.id.title);
            description = (TextView) itemView.findViewById(R.id.description);
            author = (TextView) itemView.findViewById(R.id.author);
            likeCheckBox = (CheckBox) itemView.findViewById(R.id.like_check_box);
        }
    }
}
