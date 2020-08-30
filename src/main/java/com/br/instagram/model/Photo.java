package com.br.instagram.model;

import javax.persistence.Embeddable;

@Embeddable
public class Photo {

	private String url;

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

}
