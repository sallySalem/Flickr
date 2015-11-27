/**
 * 
 */
package com.example.flickr.view;

import java.util.ArrayList;

import com.example.flickr.services.model.photoItem;

import android.content.Context;

/**
 * @author Sally
 *
 */
public interface IMainView {
	Context getContext();

	void showProgress();

	void hideProgress();

	void showPageError();

	/**
	 * @param photosList
	 */
	void setPhotosGrid(ArrayList<photoItem> photosList);
}
