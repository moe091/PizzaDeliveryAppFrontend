package com.moe.guiseppeconcept;

public class Category {
public String name;
public String description;
public String url;
public String imgName;
int key;

	public Category(String name, String url, String description, String imageURL) {
		this.name = name;
		this.url = url;
		this.description = description;
		this.imgName = imageURL;
	}
	
	public Category(int n) {
		this.name="i-" + n + "  " + "lkajdf";
		this.description = "desctiption";
		for (int i = 0; i < n; i++) 
			this.description = this.description + " " + i + " " + "asj jw";
		this.url="http://192.168.1.9:8000";
		this.imgName="defaultImage";
	}
	
	public void printCategory() {
		System.out.println("_______CATEGORY_______");
		System.out.println("name = " + name);
		System.out.println("url = " + url);
		System.out.println("description: " + description);
		System.out.println("imageURL: " + imgName);
		System.out.println("_______CATEGORY_______");
	}
	
	@Override
	public String toString() {
		return name;
	}
}
