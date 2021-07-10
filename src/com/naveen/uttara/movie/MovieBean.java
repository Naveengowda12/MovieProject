package com.naveen.uttara.movie;

import java.io.Serializable;

public class MovieBean implements Serializable,Comparable<MovieBean> {
	private String moviename;
	private String diretorname;
	private String produername;
	private int ratings;
	private String reviews;
	
	
	

	public MovieBean() {
		super();
		// TODO Auto-generated constructor stub
	}




	public MovieBean(String moviename, String diretorname, String produername, int ratings, String reviews) {
		super();
		this.moviename = moviename;
		this.diretorname = diretorname;
		this.produername = produername;
		this.ratings = ratings;
		this.reviews = reviews;
	}
	
	




	public String getMoviename() {
		return moviename;
	}




	public void setMoviename(String moviename) {
		this.moviename = moviename;
	}




	public String getDiretorname() {
		return diretorname;
	}




	public void setDiretorname(String diretorname) {
		this.diretorname = diretorname;
	}




	public String getProduername() {
		return produername;
	}




	public void setProduername(String produername) {
		this.produername = produername;
	}




	public int getRatings() {
		return ratings;
	}




	public void setRatings(int ratings) {
		this.ratings = ratings;
	}




	public String getReviews() {
		return reviews;
	}




	public void setReviews(String reviews) {
		this.reviews = reviews;
	}

	



	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((diretorname == null) ? 0 : diretorname.hashCode());
		result = prime * result + ((moviename == null) ? 0 : moviename.hashCode());
		result = prime * result + ((produername == null) ? 0 : produername.hashCode());
		result = prime * result + ratings;
		result = prime * result + ((reviews == null) ? 0 : reviews.hashCode());
		return result;
	}




	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		MovieBean other = (MovieBean) obj;
		if (diretorname == null) {
			if (other.diretorname != null)
				return false;
		} else if (!diretorname.equals(other.diretorname))
			return false;
		if (moviename == null) {
			if (other.moviename != null)
				return false;
		} else if (!moviename.equals(other.moviename))
			return false;
		if (produername == null) {
			if (other.produername != null)
				return false;
		} else if (!produername.equals(other.produername))
			return false;
		if (ratings != other.ratings)
			return false;
		if (reviews == null) {
			if (other.reviews != null)
				return false;
		} else if (!reviews.equals(other.reviews))
			return false;
		return true;
	}
	
	




	@Override
	public String toString() {
		return "MovieBean [moviename=" + moviename + ", diretorname=" + diretorname + ", produername=" + produername
				+ ", ratings=" + ratings + ", reviews=" + reviews + "]";
	}




	@Override
	public int compareTo(MovieBean m) {
		// TODO Auto-generated method stub
		return moviename.compareTo(m.moviename);
	}

}