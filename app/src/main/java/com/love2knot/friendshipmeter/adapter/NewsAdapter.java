package com.love2knot.friendshipmeter.adapter;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.love2knot.friendshipmeter.R;
import com.love2knot.friendshipmeter.interfaces.LangClickListener;
import com.love2knot.friendshipmeter.model.Articles;
import com.love2knot.friendshipmeter.utils.Languages;
import com.love2knot.friendshipmeter.utils.Util;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import pl.bclogic.pulsator4droid.library.PulsatorLayout;

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.ViewHolder> {

    private final Context context;
    private LangClickListener langClickListener;
    List<Articles> articlesList=new ArrayList<>();

    public NewsAdapter(Context context, LangClickListener langClickListener) {
        this.context = context;
        this.langClickListener = langClickListener;
    }

    public NewsAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.news_item_row,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        holder.txtAuthor.setText(articlesList.get(position).getAuthorName());
        holder.txtDate.setText(getFormattedDateFromTimestamp(articlesList.get(position).getCreatedAt()));
        holder.title.setText(articlesList.get(position).getTitle());
        Util.translate(context,holder.title);
        holder.des.setText(articlesList.get(position).getDescription());
        Util.translate(context,holder.des);
        Glide.with(context).load(articlesList.get(position).getImageUrl())
                .into(holder.image);
        holder.txtTrans.setOnClickListener(v -> {
            langClickListener.clicked(holder.txtTrans,position);

        });
        holder.txtShare.start();



    }

    public void addArticle(List<Articles> articles){
        if (articlesList!=null){
            articlesList.addAll(articles);
            notifyDataSetChanged();
        }
    }

    public static String getFormattedDateFromTimestamp(long timestampInMilliSeconds)
    {
        Date date = new Date();
        date.setTime(timestampInMilliSeconds);
        String formattedDate=new SimpleDateFormat("MMM d, yyyy", Locale.getDefault()).format(date);
        return formattedDate;

    }
    @Override
    public int getItemCount() {
        return articlesList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView title,des,txtTrans,txtDate,txtAuthor;
        private final ImageView image;
        private PulsatorLayout txtShare;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            title=itemView.findViewById(R.id.title);
            des=itemView.findViewById(R.id.description);
            txtTrans=itemView.findViewById(R.id.txtTrans);
            txtDate=itemView.findViewById(R.id.txtDate);
            txtShare=itemView.findViewById(R.id.pulsator);
            txtAuthor=itemView.findViewById(R.id.txtAuthor);
            image=itemView.findViewById(R.id.imageUrl);
        }
    }
}
