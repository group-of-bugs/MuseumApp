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


public class MuseumAdapter extends ArrayAdapter<Museum> {
    private int resourceId;

    public MuseumAdapter(Context context, int resource, List<Museum> objects) {
        super(context, resource, objects);
        resourceId = resource;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        Museum museum = getItem(position);
        View view;
        if(convertView == null) {
            view = LayoutInflater.from(getContext()).inflate(resourceId, null);
        } else{
            view = convertView;
        }
        ImageView museumImage = view.findViewById(R.id.museum_img);
        TextView museumName = view.findViewById(R.id.museum_name);
        LoadImage loader = new LoadImage(museumImage);
        loader.setBitmap(museum.getImgUrl());
        museumName.setText(museum.getMuseumName());
        return view;
    }
}
