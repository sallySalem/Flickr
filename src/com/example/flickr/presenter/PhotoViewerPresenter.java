/**
 * 
 */
package com.example.flickr.presenter;

import com.example.flickr.services.INetworkService;
import com.example.flickr.services.implementation.NetworkService;
import com.example.flickr.view.IPhotoViewerView;

/**
 * @author Sally
 *
 */
public class PhotoViewerPresenter {
	private INetworkService photoNetworkService;
	private IPhotoViewerView photoView;

	public PhotoViewerPresenter() {
		this.photoNetworkService = new NetworkService();
	}

	public PhotoViewerPresenter(INetworkService networkService) {
		this.photoNetworkService = networkService;
	}

	public void setView(IPhotoViewerView view) {
		this.photoView = view;
	}
}
