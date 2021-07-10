package com.naveen.uttara.movie;

import java.util.Comparator;

public class MovieRatingComparator implements Comparator<MovieBean> {

	@Override
	public int compare(MovieBean o1, MovieBean o2) {
		return o2.getRatings()-o1.getRatings();
	}

}