package com.naveen.uttara.movie;

import java.util.Comparator;

public class MovieNameComparator implements Comparator<MovieBean> {

	@Override
	public int compare(MovieBean o1, MovieBean o2) {
		// TODO Auto-generated method stub
		return o1.getMoviename().compareTo(o2.getMoviename());
	}
	

}