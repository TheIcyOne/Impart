package com.headfishindustries.impart;

public class LazyMaths {

	public static double interpolate(double start, double end, double portion){
		return start + (end - start) * portion;
	}

}
