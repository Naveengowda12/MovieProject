package com.naveen.uttara.movie;

import java.util.Comparator;

public class DirectorNameCompartor implements Comparator<MovieBean> {

	@Override
	public int compare(MovieBean o1, MovieBean o2) {
		return o1.getDiretorname().compareTo(o2.getDiretorname());
	}

}
