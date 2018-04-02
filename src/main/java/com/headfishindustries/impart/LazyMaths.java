package com.headfishindustries.impart;

import net.minecraft.util.math.Vec3d;

public class LazyMaths {
	
	static final Vec3d VEC_X = new Vec3d(1, 0, 0);
	static final Vec3d VEC_Y = new Vec3d(0, 1, 0);
	static final Vec3d VEC_Z = new Vec3d(0, 0, 1);

	public static double interpolate(double start, double end, double portion){
		return start + (end - start) * portion;
	}
	
	public static double angleWithX(Vec3d in){
		double cos = VEC_Z.dotProduct(in)/in.lengthVector();
		
		return Math.acos(cos);
	}
	
	public static double angleWithY(Vec3d in){
		double cos = VEC_X.dotProduct(in)/in.lengthVector();
		
		return Math.acos(cos);
	}

}
