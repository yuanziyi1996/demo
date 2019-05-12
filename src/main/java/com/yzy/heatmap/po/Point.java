package com.yzy.heatmap.po;

import java.io.Serializable;

public class Point implements Serializable {
	private static final long serialVersionUID = 1L;

	private String id;

	private String lng;

	private String lat;

	private Integer count;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id == null ? null : id.trim();
	}

	public String getLng() {
		return lng;
	}

	public void setLng(String lng) {
		this.lng = lng == null ? null : lng.trim();
	}

	public String getLat() {
		return lat;
	}

	public void setLat(String lat) {
		this.lat = lat == null ? null : lat.trim();
	}

	public Integer getCount() {
		return count;
	}

	public void setCount(Integer count) {
		this.count = count;
	}

	public Point(String id, String lng, String lat, Integer count) {
		super();
		this.id = id;
		this.lng = lng;
		this.lat = lat;
		this.count = count;
	}

	@Override
	public String toString() {
		return "Point [id=" + id + ", lng=" + lng + ", lat=" + lat + ", count=" + count + "]";
	}

}