/**
 * 
 */
package com.example.flickr.services.model;

import java.io.Serializable;

import com.google.gson.annotations.SerializedName;


/**
 * @author Sally
 *
 */
@SuppressWarnings("serial")
public class PhotoSearch implements Serializable {

	private String stat;
	@SerializedName("photos")
	private PhotosList photosList;

	public String getStat() {
		return stat;
	}

	public void setStat(String stat) {
		this.stat = stat;
	}

	public PhotosList getPhotosList() {
		return photosList;
	}

	public void setPhotosList(PhotosList photosList) {
		this.photosList = photosList;
	}

}
