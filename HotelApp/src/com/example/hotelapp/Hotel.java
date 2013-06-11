package com.example.hotelapp;

public class Hotel {
	
	public String name;
	public String street_address;
	public String thumbnail_url;
	public String nightly_rate;
	
	public Hotel() {
		// TODO Auto-generated constructor stub
	}

	public Hotel(String name, String street_address, String thumbnail_url,
			String nightly_rate) {
		this.name = name;
		this.street_address = street_address;
		this.thumbnail_url = thumbnail_url;
		this.nightly_rate = nightly_rate;
		
	}

	@Override
	public String toString() {
		return this.name;
	}

}
