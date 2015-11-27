/**
 * 
 */
package com.example.flickr.services;

import java.io.IOException;

import android.content.Context;

/**
 * @author Sally
 *
 */
public interface INetworkService {
	boolean isInternetConnectionAvailable(Context context);

	String get(String url) throws IOException;
}
