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

public class CollectionAdapter extends ArrayAdapter<Collection> {
    private int resourceId;

    public CollectionAdapter(Context context, int resource, List<Collection> objects) {
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
        LoadImage loader = new LoadImage(collectionImage);
        loader.setBitmap(collection.getImgUrl());
        collectionName.setText(collection.getColName());
        Log.d("CollectionActivity", collection.getColName() + " " + collection.getImgUrl());
        return view;
    }
}
