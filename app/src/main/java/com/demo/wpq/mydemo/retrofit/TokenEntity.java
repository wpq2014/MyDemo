package com.demo.wpq.mydemo.retrofit;

import java.io.Serializable;

public class TokenEntity implements Serializable {

//	{"id":30344,"token":"token"}

	public int id;
	public String token;
	public String phone;

	@Override
	public String toString() {
		return "TokenEntity{" +
				"id=" + id +
				", token='" + token + '\'' +
				", phone='" + phone + '\'' +
				'}';
	}
}
