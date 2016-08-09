package com.demo.wpq.mydemo.bean;

public class DishType {
	private int id;
	private String typeName;

	public DishType(String typeName) {
		this(-1, typeName);
	}

	public DishType(int id, String typeName) {
		this.id = id;
		this.typeName = typeName;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTypeName() {
		return typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}

	@Override
	public String toString() {
		return "DishType{" +
				"id=" + id +
				", typeName='" + typeName + '\'' +
				'}';
	}
}
