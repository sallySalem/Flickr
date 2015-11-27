package com.example.flickr;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ProgressBar;

import com.example.flickr.adapter.PhotosGridAdapter;
import com.example.flickr.presenter.MainActivityPresenter;
import com.example.flickr.services.model.photoItem;
import com.example.flickr.view.IMainView;

public class MainActivity extends Activity implements IMainView, TextWatcher {

	private MainActivityPresenter mPresenter = new MainActivityPresenter();
	private EditText etSearch;
	private ProgressBar pbLoading;
	private GridView gvPhotos;
	private ArrayList<photoItem> photosList;
	private PhotosGridAdapter photosGridAdapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		mPresenter.setView(this);

		etSearch = (EditText) findViewById(R.id.etSearch);
		etSearch.addTextChangedListener(this);

		gvPhotos = (GridView) findViewById(R.id.gvPhotos);
		photosList = new ArrayList<photoItem>();
		photosGridAdapter = new PhotosGridAdapter(getApplicationContext(), photosList);
		photosGridAdapter.notifyDataSetChanged();
		gvPhotos.setAdapter(photosGridAdapter);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	@Override
	public Context getContext() {
		return getApplicationContext();
	}

	@Override
	public void showProgress() {
		if (pbLoading == null) {
			pbLoading = (ProgressBar) findViewById(R.id.pbloading);
			pbLoading.getIndeterminateDrawable().setColorFilter(getResources().getColor(R.color.ProgressBar_bg),
					android.graphics.PorterDuff.Mode.MULTIPLY);
		}
		pbLoading.setVisibility(View.VISIBLE);
	}

	@Override
	public void hideProgress() {
		pbLoading.setVisibility(View.INVISIBLE);
	}

	@Override
	public void onDestroy() {
		super.onDestroy();
		mPresenter.cancelTask();
	}

	@Override
	public void showPageError() {
		hideProgress();
		gvPhotos.setVisibility(View.INVISIBLE);
		mPresenter.cancelTask();
	}

	@Override
	public void afterTextChanged(Editable arg0) {
		String text = etSearch.getText().toString();

		mPresenter.searchByName(text);
	}

	@Override
	public void beforeTextChanged(CharSequence arg0, int arg1, int arg2,
			int arg3) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onTextChanged(CharSequence arg0, int arg1, int arg2, int arg3) {
		// TODO Auto-generated method stub

	}

	@Override
	public void setPhotosGrid(ArrayList<photoItem> photosList) {
		if (photosList == null) {
			gvPhotos.setVisibility(View.INVISIBLE);
		} else {
			gvPhotos.setVisibility(View.VISIBLE);
			this.photosList.clear();
			this.photosList.addAll(photosList);
			photosGridAdapter.notifyDataSetChanged();
		}
	}
}
