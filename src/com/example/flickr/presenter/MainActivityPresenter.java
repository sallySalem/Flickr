/**
 * 
 */
package com.example.flickr.presenter;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.LinkedList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.message.BasicNameValuePair;

import android.os.AsyncTask;

import com.example.flickr.services.INetworkService;
import com.example.flickr.services.implementation.NetworkService;
import com.example.flickr.services.model.PhotoSearch;
import com.example.flickr.util.constants;
import com.example.flickr.view.IMainView;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

/**
 * @author Sally
 *
 */
public class MainActivityPresenter {
	private INetworkService mNetworkService;
	private IMainView mView;
	private AsyncTask<String, Void, PhotoSearch> getPhotosTask;

	public MainActivityPresenter() {
		this.mNetworkService = new NetworkService();
	}

	public MainActivityPresenter(INetworkService networkService) {
		this.mNetworkService = networkService;
	}

	public void setView(IMainView view) {
		this.mView = view;
	}

	public void searchByName(String text) {
		// get photos
		String url = constants.FLICKR_SEARCH_URL;

		List<NameValuePair> params = new LinkedList<NameValuePair>();
		params.add(new BasicNameValuePair("api_key", constants.FLICKR_API_KEY));
		params.add(new BasicNameValuePair("text", text));
		params.add(new BasicNameValuePair("extras", "url_s,url_m,url_o"));
		params.add(new BasicNameValuePair("format", "json"));
		params.add(new BasicNameValuePair("nojsoncallback", "1"));

		String paramURL = URLEncodedUtils.format(params, "utf-8");

		url += paramURL;

		getPhotosTask = new GetPhotosListTask().execute(url);
	}

	private class GetPhotosListTask extends
			AsyncTask<String, Void, PhotoSearch> {

		@Override
		protected PhotoSearch doInBackground(String... urls) {

			String response = null;
			PhotoSearch photoslist = null;
			try {

				if (mNetworkService.isInternetConnectionAvailable(mView
						.getContext())) {

					response = mNetworkService.get(urls[0]);
					photoslist = parsePhotoList(response);
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return photoslist;
		}

		@Override
		protected void onPreExecute() {
			mView.showProgress();
		}

		@Override
		protected void onPostExecute(PhotoSearch result) {
			if (null != result) {
				if (null != result.getPhotosList()) {
					mView.setPhotosGrid(result.getPhotosList().getPhotosList());
					mView.hideProgress();
				} else {
					mView.showPageError();
				}
			} else {
				mView.showPageError();
			}
		}
	}

	private PhotoSearch parsePhotoList(String response) {
		Gson gson = new Gson();
		Type listType = new TypeToken<PhotoSearch>() {
		}.getType();

		PhotoSearch playlist = (PhotoSearch) gson.fromJson(response, listType);

		return playlist;
	}

	public void cancelTask() {

		if (getPhotosTask != null
				&& getPhotosTask.getStatus() != AsyncTask.Status.FINISHED) {
			getPhotosTask.cancel(true);
		}
		if (getPhotosTask != null
				&& getPhotosTask.getStatus() != AsyncTask.Status.FINISHED) {
			getPhotosTask.cancel(true);
		}
	}
}
