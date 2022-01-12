package com.gob.museumapp.model;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.gob.museumapp.R;
import com.gob.museumapp.util.LoadImage;

import java.util.List;

public class RankCollectionAdapter extends ArrayAdapter<Collection> {
    private int resourceId;

    public RankCollectionAdapter(Context context, int resource, List<Collection> objects) {
        super(context, resource, objects);
        resourceId = resource;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        Collection collection = getItem(position);
        View view;
        if(convertView == null) {
            view = LayoutInflater.from(getContext()).inflate(resourceId, null);
        } else{
            view = convertView;
        }
        ImageView collectionImage = view.findViewById(R.id.collection_img);
        TextView collectionName = view.findViewById(R.id.collection_name);
        TextView collectionScore = view.findViewById(R.id.collection_score);
        LoadImage loader = new LoadImage(collectionImage);
        loader.setBitmap(collection.getImgUrl());
        collectionName.setText(collection.getColName());
        collectionScore.setText("平均得分: " + String.valueOf(collection.getColScore()));
        Log.d("RankCollectionAdapter", collection.getColName() + " " + collection.getImgUrl());
        return view;

    }
}
