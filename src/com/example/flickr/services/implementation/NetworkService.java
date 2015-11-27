/**
 * 
 */
package com.example.flickr.services.implementation;

import java.io.IOException;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import com.example.flickr.services.INetworkService;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * @author Sally
 *
 */
public class NetworkService implements INetworkService {

	private DefaultHttpClient httpClient = new DefaultHttpClient();
	private HttpEntity httpEntity = null;
	private HttpResponse httpResponse = null;

	@Override
	public boolean isInternetConnectionAvailable(Context context) {
		ConnectivityManager cm = (ConnectivityManager) context
				.getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo ni = cm.getActiveNetworkInfo();
		if (ni == null) {
			return false;
		} else
			return ni.isConnected();
	}

	@Override
	public String get(String url) throws IOException {

		String responseText = "";

		HttpGet httpGet = new HttpGet(url);
		httpResponse = httpClient.execute(httpGet);
		httpEntity = httpResponse.getEntity();
		responseText = EntityUtils.toString(httpEntity);

		return responseText;
	}
}
