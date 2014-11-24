package com.innverse.elearn.json;

import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.innverse.elearn.eenum.MediaType;
import com.innverse.elearn.eenum.SubscriptionType;

public class MediaFileForm {
	
	private long ids;	
	
	private CommonsMultipartFile scormfile;
	
	private String discription;
	
	private MediaType mediaType;
	
	private SubscriptionType subtype;

	public CommonsMultipartFile getScormfile() {
		return scormfile;
	}

	public void setScormfile(CommonsMultipartFile scormfile) {
		this.scormfile = scormfile;
	}

	public String getDiscription() {
		return discription;
	}

	public void setDiscription(String discription) {
		this.discription = discription;
	}

	public MediaType getMediaType() {
		return mediaType;
	}

	public void setMediaType(MediaType mediaType) {
		this.mediaType = mediaType;
	}

	public SubscriptionType getSubtype() {
		return subtype;
	}

	public void setSubtype(SubscriptionType subtype) {
		this.subtype = subtype;
	}

	public long getIds() {
		return ids;
	}

	public void setIds(long ids) {
		this.ids = ids;
	}

	

	
	
	
}
