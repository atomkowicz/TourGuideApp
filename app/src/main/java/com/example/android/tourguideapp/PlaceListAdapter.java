package com.example.android.tourguideapp;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;


public class PlaceListAdapter extends ArrayAdapter<Place> {

    public PlaceListAdapter(Activity context, ArrayList<Place> pPlaceArrayList) {
        super(context, 0, pPlaceArrayList);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View listItemView = convertView;

        if (null == listItemView) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.card_list_item, parent, false);

            Place _place = getItem(position);
            ImageView imageView = (ImageView) listItemView.findViewById(R.id.list_item_artist_image);
            imageView.setImageResource(_place.getImageId());

            if (_place.hasImage()) {
                imageView.setVisibility(imageView.VISIBLE);
            } else {
                imageView.setVisibility(imageView.GONE);
            }

            TextView singerNameTextView = (TextView) listItemView.findViewById(R.id.list_item_artist_name);
            singerNameTextView.setText(_place.getPlaceName());

            return listItemView;
        } else {
            Place _place = getItem(position);
            ImageView imageView = (ImageView) listItemView.findViewById(R.id.list_item_artist_image);
            imageView.setImageResource(_place.getImageId());

            if (_place.hasImage()) {
                imageView.setVisibility(imageView.VISIBLE);
            } else {
                imageView.setVisibility(imageView.GONE);
            }

            TextView singerNameTextView = (TextView) listItemView.findViewById(R.id.list_item_artist_name);
            singerNameTextView.setText(_place.getPlaceName());
            return listItemView;
        }
    }
}
