/**
 * 
 */
package com.example.flickr.services.model;

import java.io.Serializable;
import java.util.ArrayList;

import com.google.gson.annotations.SerializedName;

/**
 * @author Sally
 *
 */
@SuppressWarnings("serial")
public class PhotosList implements Serializable {

	private int page;
	private int pages;
	private int perpage;
	private String total;
	@SerializedName("photo")
	private ArrayList<photoItem> photosList;

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public int getPages() {
		return pages;
	}

	public void setPages(int pages) {
		this.pages = pages;
	}

	public int getPerpage() {
		return perpage;
	}

	public void setPerpage(int perpage) {
		this.perpage = perpage;
	}

	public String getTotal() {
		return total;
	}

	public void setTotal(String total) {
		this.total = total;
	}

	public ArrayList<photoItem> getPhotosList() {
		return photosList;
	}

	public void setPhotosList(ArrayList<photoItem> photosList) {
		this.photosList = photosList;
	}

}
