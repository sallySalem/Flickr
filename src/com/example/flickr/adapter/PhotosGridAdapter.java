/**
 * 
 */
package com.example.flickr.adapter;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ArrayAdapter;
import android.widget.ImageView;

import com.example.flickr.PhotoViewerActivity;
import com.example.flickr.R;
import com.example.flickr.services.model.photoItem;
import com.example.flickr.util.constants;
import com.squareup.picasso.Picasso;

/**
 * @author Sally
 *
 */
public class PhotosGridAdapter extends ArrayAdapter<photoItem> {

	private Context context;
	private ArrayList<photoItem> photosList;

	static class ViewHolder {
		public ImageView imgPhoto;
	}

	/**
	 * @param context
	 * @param objects
	 */
	public PhotosGridAdapter(Context context, ArrayList<photoItem> photosList) {
		super(context, R.layout.item_photo_grid, photosList);
		this.context = context;
		this.photosList = photosList;
	}

	@Override
	public photoItem getItem(int position) {
		// TODO Auto-generated method stub
		return super.getItem(position);
	}

	@Override
	public View getView(final int position, View convertView, ViewGroup parent) {
		ViewHolder viewHolder;
		if (convertView == null) {
			LayoutInflater mInflater = (LayoutInflater) context
					.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
			convertView = mInflater.inflate(R.layout.item_photo_grid, null);
			viewHolder = new ViewHolder();
			viewHolder.imgPhoto = (ImageView) convertView
					.findViewById(R.id.imgPhoto);
			convertView.setTag(viewHolder);
		} else {
			viewHolder = (ViewHolder) convertView.getTag();
		}

		String imageURL = photosList.get(position).getSmallURL();

		if (imageURL != null) {
			if (imageURL.trim().length() > 0) {
				Picasso.with(context).load(imageURL).into(viewHolder.imgPhoto);
			}
		}
		Animation anim = AnimationUtils.loadAnimation(context, R.anim.fadein);

		viewHolder.imgPhoto.setAnimation(anim);
		viewHolder.imgPhoto.setOnClickListener(new OnClickListener() {

			@SuppressWarnings("unchecked")
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(context, PhotoViewerActivity.class);
				intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
				intent.putExtra(constants.SELECTED_PHOTO_KEY, position);
				ArrayList<? extends Parcelable> photosArray = (ArrayList<? extends Parcelable>) photosList;
				intent.putParcelableArrayListExtra(constants.PHOTOS_LIST_KEY,
						photosArray);
				context.startActivity(intent);
			}
		});

		return convertView;

	}

}
