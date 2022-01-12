package com.gob.museumapp.model;

import android.content.Context;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;


import com.gob.museumapp.R;
import com.gob.museumapp.db.DBHelper;
import com.gob.museumapp.util.LoadImage;

import java.util.List;
import java.util.Map;

public class MyCommentAdapter extends ArrayAdapter<Comment>
{
    private int resourceId;
    public MyCommentAdapter(Context context, int resource, List<Comment> objects)
    {
        super(context, resource, objects);
        resourceId = resource;
    }

    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent)
    {
        Comment comment = getItem(position);
        View view;

        if(convertView == null)
        {
            view = LayoutInflater.from(getContext()).inflate(resourceId, null);
        }
        else
        {
            view = convertView;
        }
        ImageView CollectionImage = view.findViewById(R.id.user_img);
        TextView CommentContent = view.findViewById(R.id.comment_content);
        TextView CollectionName = view.findViewById(R.id.user_name);

        class GetCollectionUrl extends Thread
        {
            private String collectionUrl = null;
            private String collectionname = null;
            @Override
            public void run()
            {
                DBHelper dbhelper=new DBHelper();
                Double Collection_ID=comment.getCol_id();
                String query="SELECT * FROM Collection WHERE col_id ="+Collection_ID;
                dbhelper.setSql(query);
                List<Map<String, Object>> result = dbhelper.executeQuery();
                collectionUrl = (String) result.get(0).get("col_picture");
                collectionname= (String) result.get(0).get("col_name");

            }

            public String getCollectionUrl()
            {
                return this.collectionUrl;
            }
            public String getCollectionName()
            {
                return this.collectionname;
            }
        }
        GetCollectionUrl getUrl = new GetCollectionUrl();
        getUrl.start();

        try {
            getUrl.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        LoadImage loader = new LoadImage(CollectionImage);
        loader.setBitmap(getUrl.getCollectionUrl());
        CommentContent.setText(comment.getContent());
        CollectionName.setText(getUrl.getCollectionName());
        CommentContent.setTextSize(15);

        return view;
    }
}
