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
public class photoItem implements Serializable {

	private String id;
	private String owner;
	private String secret;
	private String server;
	private int farm;
	private String title;
	@SerializedName("url_s")
	private String SmallURL;
	@SerializedName("url_o")
	private String OriginalURL;
	@SerializedName("url_m")
	private String MediumURL;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getOwner() {
		return owner;
	}

	public void setOwner(String owner) {
		this.owner = owner;
	}

	public String getSecret() {
		return secret;
	}

	public void setSecret(String secret) {
		this.secret = secret;
	}

	public String getServer() {
		return server;
	}

	public void setServer(String server) {
		this.server = server;
	}

	public int getFarm() {
		return farm;
	}

	public void setFarm(int farm) {
		this.farm = farm;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getSmallURL() {
		return SmallURL;
	}

	public void setSmallURL(String smallURL) {
		SmallURL = smallURL;
	}

	public String getOriginalURL() {
		return OriginalURL;
	}

	public void setOriginalURL(String originalURL) {
		OriginalURL = originalURL;
	}

	public String getMediumURL() {
		return MediumURL;
	}

	public void setMediumURL(String mediumURL) {
		MediumURL = mediumURL;
	}

}
