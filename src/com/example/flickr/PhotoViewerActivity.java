/**
 * 
 */
package com.example.flickr;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.view.ViewPager;

import com.example.flickr.adapter.PhotoFullScreenAdapter;
import com.example.flickr.presenter.PhotoViewerPresenter;
import com.example.flickr.services.model.photoItem;
import com.example.flickr.util.DepthPageTransformer;
import com.example.flickr.util.constants;
import com.example.flickr.view.IPhotoViewerView;

/**
 * @author Sally
 *
 */
public class PhotoViewerActivity extends Activity implements IPhotoViewerView {

	private PhotoViewerPresenter photoViewerPresenter = new PhotoViewerPresenter();
	private ArrayList<photoItem> PhotosList;
	private int selectedID;

	@SuppressWarnings("unchecked")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_photoviewer);

		overridePendingTransition(R.anim.fadein, R.anim.fadeout);

		photoViewerPresenter.setView(this);

		selectedID = getIntent().getIntExtra(constants.SELECTED_PHOTO_KEY, -1);
		PhotosList = (ArrayList<photoItem>) getIntent().getSerializableExtra(
				constants.PHOTOS_LIST_KEY);

		if (PhotosList != null) {
			ViewPager viewPager = (ViewPager) findViewById(R.id.vpPhotoViewer);
			PhotoFullScreenAdapter adapter = new PhotoFullScreenAdapter(
					getApplicationContext(), PhotosList);
			viewPager.setAdapter(adapter);
			viewPager.setCurrentItem(selectedID);
			viewPager.setPageTransformer(true, new DepthPageTransformer());
		}
	}

	@Override
	public Context getContext() {
		// TODO Auto-generated method stub
		return getApplicationContext();
	}
}
