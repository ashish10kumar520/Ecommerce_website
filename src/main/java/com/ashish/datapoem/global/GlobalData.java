package com.ashish.datapoem.global;

import java.util.ArrayList;
import java.util.List;

import com.ashish.datapoem.Entity.Product;

public class GlobalData {
	public static List<Product> cart;
	static {
		cart=new ArrayList<Product>();
	}
}
