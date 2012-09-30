package com.shashank.gfg;

public class CamelAndBananaPuzzle {
	
	/**
	 * This function returns the number of bananas remaining when camel with 'camelCapacity' covers a distance 'distance' eating one banana each kilometer.
	 * @param numberOfBananas
	 * @param camelCapacity
	 * @param distance
	 * @return
	 */
	public static int findNumberOfBananas (int numberOfBananas , int camelCapacity , int distance) {
		int bananaReachingIthPosition[] = new int [distance];
		int numberOfRemainingBananas = numberOfBananas;
		int i = 1;
		bananaReachingIthPosition[0] = numberOfBananas;
		int subtractingFactor = (2*(numberOfRemainingBananas/camelCapacity) - 1);
		while (numberOfRemainingBananas > numberOfBananas - camelCapacity) {
			// for each kilometer the camel has to travel forward-->backward-->forward-->backward-->forward. Hence he has to eat 5 bananas.
			bananaReachingIthPosition[i] = bananaReachingIthPosition[i-1]-subtractingFactor;
			numberOfRemainingBananas -= subtractingFactor;
			i++;
			
		}
		subtractingFactor = (2*(numberOfRemainingBananas/camelCapacity) - 1);
		while (numberOfRemainingBananas > numberOfBananas - 2*camelCapacity) {
			// for each kilometer the camel has to travel forward-->backward-->forward. Hence he has to eat 3 bananas.
			bananaReachingIthPosition[i] = bananaReachingIthPosition[i-1]-subtractingFactor;
			numberOfRemainingBananas -= subtractingFactor;
			i++;		
		}
		while (i < distance) {
			bananaReachingIthPosition[i] = bananaReachingIthPosition[i-1]-1;
			numberOfRemainingBananas -= 1;
			i++;
		}
		return bananaReachingIthPosition[distance-1];
	}
	/*
	 * Driver function
	 */
	public static void main(String[] args) {
		System.out.println(findNumberOfBananas(30000,1000,1000));
	}
}
