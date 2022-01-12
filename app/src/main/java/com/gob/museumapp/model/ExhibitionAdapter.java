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

public class ExhibitionAdapter extends ArrayAdapter<Exhibition> {
    private int resourceId;

    public ExhibitionAdapter(Context context, int resource, List<Exhibition> objects) {
        super(context, resource, objects);
        resourceId = resource;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        Exhibition exhibition = getItem(position);
        View view;
        if(convertView == null) {
            view = LayoutInflater.from(getContext()).inflate(resourceId, null);
        } else{
            view = convertView;
        }
        ImageView exhibitionImage = view.findViewById(R.id.exhibition_img);
        TextView exhibitionName = view.findViewById(R.id.exhibition_name);
        LoadImage loader = new LoadImage(exhibitionImage);
        loader.setBitmap(exhibition.getImgUrl());
        exhibitionName.setText(exhibition.getExhName());
        Log.d("CollectionActivity", exhibition.getExhName() + " " + exhibition.getImgUrl());
        return view;
    }
}
