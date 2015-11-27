/**
 * 
 */
package com.example.flickr.adapter;

import java.util.ArrayList;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.flickr.R;
import com.example.flickr.services.model.photoItem;
import com.squareup.picasso.Picasso;

/**
 * @author Sally
 *
 */
public class PhotoFullScreenAdapter extends PagerAdapter {

	private Context context;
	private ArrayList<photoItem> photosList;
	private LayoutInflater inflater;

	@Override
	public int getCount() {
		return photosList.size();
	}

	@Override
	public boolean isViewFromObject(View view, Object object) {
		return view == ((ImageView) object);
	}

	// constructor
	public PhotoFullScreenAdapter(Context c, ArrayList<photoItem> photosList) {
		this.context = c;
		this.photosList = photosList;
	}

	// @Override
	// public boolean isViewFromObject(View view, Object object) {
	// return view == ((RelativeLayout) object);
	// }

	@Override
	public Object instantiateItem(ViewGroup viewGroup, int position) {
		ImageView imgPhoto;

		inflater = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		View view = inflater.inflate(R.layout.item_photo_fullscreen, viewGroup,
				false);

		imgPhoto = (ImageView) view.findViewById(R.id.imgPhotoFullScreen);

		String imageURL = photosList.get(position).getMediumURL();
		if (imageURL == null) {
			imageURL = photosList.get(position).getOriginalURL();
		}

		if (imageURL != null) {
			if (imageURL.trim().length() > 0) {
				Picasso.with(context).load(imageURL).into(imgPhoto);
			}
		}

		((ViewPager) viewGroup).addView(view);

		return view;
	}

	@Override
	public void destroyItem(ViewGroup viewGroup, int position, Object object) {
		((ViewPager) viewGroup).removeView((ImageView) object);

	}

}
