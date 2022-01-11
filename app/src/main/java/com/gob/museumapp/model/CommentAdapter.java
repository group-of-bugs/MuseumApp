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

public class CommentAdapter extends ArrayAdapter<Comment> {
    private int resourceId;

    public CommentAdapter(Context context, int resource, List<Comment> objects) {
        super(context, resource, objects);
        resourceId = resource;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        Comment comment = getItem(position);
        View view;
        if(convertView == null) {
            view = LayoutInflater.from(getContext()).inflate(resourceId, null);
        } else{
            view = convertView;
        }
        TextView comment_user = view.findViewById(R.id.user_name);
        TextView comment_content = view.findViewById(R.id.comment_content);
        comment_user.setText(comment.getUser_name());
        comment_content.setText(comment.getContent());
        return view;
    }
}
