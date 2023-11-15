package com.example.sistemascasa.tigie.adapter;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import androidx.recyclerview.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.sistemascasa.tigie.R;
import com.example.sistemascasa.tigie.activities.HeadingActivity;
import com.example.sistemascasa.tigie.db.ConstructorData;
import com.example.sistemascasa.tigie.pojo.Chapters;

import java.util.ArrayList;

/**
 * Created by desarrolloweb on 12/07/16.
 * Pasa los elementos de la lista
 */
public class ChapterAdapter extends RecyclerView.Adapter<ChapterAdapter.ChapterViewHolder> {

    ArrayList<Chapters> chapters;
    Activity activity;
    ConstructorData constructorData;
    Context context;
    Integer valTigie;

    public ChapterAdapter(ArrayList<Chapters> chapters, Activity activity, Context context, Integer valTigie) {
        this.context = context;
        this.chapters = chapters;
        this.activity = activity;
        this.valTigie = valTigie;
        constructorData = new ConstructorData(context);
    }

    @Override
    public ChapterViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_chapters, parent, false); //Cual sera el layout que estara reciclando la lista
        return new ChapterViewHolder(v);
    }

    @SuppressLint("ResourceType")
    @Override
    public void onBindViewHolder(ChapterViewHolder chapterViewHolder, int position) {
        final Chapters chapter = chapters.get(position);

        chapterViewHolder.tvId.setText(String.valueOf(chapter.getId()));
        chapterViewHolder.tvCode.setText(chapter.getCodigo());
        chapterViewHolder.tvDescripcion.setText(chapter.getDescripcion());
        chapterViewHolder.imgIcon.setImageResource( chapter.getIcono());

        chapterViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i("chapteradaper", "" + valTigie);
                Intent intent = new Intent(activity, HeadingActivity.class);
                intent.putExtra("flag", 1);
                intent.putExtra("iconChapter", chapter.getIcono());
                intent.putExtra("idTariffChapter", String.valueOf(chapter.getId()));
                intent.putExtra("tariffChapterCode", chapter.getCodigo());
                intent.putExtra("tariffChapterDescription", chapter.getDescripcion());
                intent.putExtra("idChapter", chapter.getId());
                intent.putExtra("valTigie", valTigie);
                activity.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return chapters.size();
    }

    public static class ChapterViewHolder extends RecyclerView.ViewHolder {

        private ImageView imgIcon;
        private TextView  tvId;
        private TextView  tvCode;
        private TextView  tvDescripcion;

        public ChapterViewHolder(View itemView) {
            super(itemView);
            imgIcon         = (ImageView) itemView.findViewById(R.id.ivIcon);
            tvId            = (TextView)  itemView.findViewById(R.id.tvId);
            tvCode          = (TextView)  itemView.findViewById(R.id.tvChapter);
            tvDescripcion   = (TextView)  itemView.findViewById(R.id.tvChapterDesc);
        }
    }
}
