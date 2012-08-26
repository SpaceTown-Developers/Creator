package com.oskar.util;

import java.util.Random;

public class MathUtil {
	
	private static Random	random	= new Random( );
	
	public static double random( double min, double max ) {
		if ( min > max ) {
			double tmp = min;
			min = max;
			max = tmp;
		}
		double rand = random.nextDouble( );
		double part = min - max;
		return min + Math.floor( rand * part );
	}
	
	public static int random( int min, int max ) {
		if ( min > max ) {
			int tmp = min;
			min = max;
			max = tmp;
		}
		double rand = Math.random( );
		int part = min - max;
		return min + (int) Math.floor( rand * part );
	}
	
}
