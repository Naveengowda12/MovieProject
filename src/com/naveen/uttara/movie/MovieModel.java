package com.naveen.uttara.movie;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class MovieModel {

	public String addWishList(String wname) {
		String msg = null;
		BufferedWriter bw = null;
		try {
			bw = new BufferedWriter(new FileWriter(Constants.WISHLIST_PATH, true));
			bw.write(wname);
			bw.newLine();
			bw.flush();
			File f = new File(wname + Constants.FILE_EXTENSION);
			if (!f.exists()) {
				f.createNewFile();
			}
			msg = Constants.SUCCESS;
		} catch (IOException e) {
			Logger.getInstance().log(e.toString());
			Logger.getInstance().log(Arrays.toString(e.getStackTrace()));
			msg = e.getLocalizedMessage();

		} finally {
			if (bw != null)
				try {
					bw.close();
				} catch (IOException e) {
					Logger.getInstance().log(e.toString());
					Logger.getInstance().log(Arrays.toString(e.getStackTrace()));
					msg = e.getLocalizedMessage();

				}
		}
		return msg;

	}

	public List<MovieBean> getMovies(String wname) {
		List<MovieBean> ls = new ArrayList<MovieBean>();

		BufferedReader br = null;
		try {
			String line;
			br = new BufferedReader(new FileReader(wname + Constants.FILE_EXTENSION));
			while ((line = br.readLine()) != null) {
				MovieBean mb2 = new MovieBean();
				String[] arr = line.split(":");
				mb2.setMoviename(arr[0]);
				mb2.setDiretorname(arr[1]);
				mb2.setProduername(arr[2]);
				mb2.setReviews(arr[3]);
				mb2.setRatings(Integer.parseInt(arr[4]));
				ls.add(mb2);
			}

		} catch (IOException e) {
			Logger.getInstance().log(e.toString());
			Logger.getInstance().log(Arrays.toString(e.getStackTrace()));
			return null;
		} finally {
			if (br != null)
				try {
					br.close();
				} catch (IOException e) {
					Logger.getInstance().log(e.toString());
					Logger.getInstance().log(Arrays.toString(e.getStackTrace()));

				}
		}
		return ls;

	}

	public String addMovie(MovieBean mb, String wname) {
		String msg = null;
		BufferedWriter bw = null;
		try {
			System.out.println(mb.getMoviename());
			bw = new BufferedWriter(new FileWriter(wname + Constants.FILE_EXTENSION, true));
			bw.write(mb.getMoviename() + ":" + mb.getDiretorname() + ":" + mb.getProduername() + ":" + mb.getReviews()
					+ ":" + mb.getRatings());
			bw.newLine();
			msg = Constants.SUCCESS;

		} catch (IOException e) {
			Logger.getInstance().log(e.toString());
			Logger.getInstance().log(Arrays.toString(e.getStackTrace()));
			msg = e.getLocalizedMessage();

		} finally {
			if (bw != null) {
				try {
					bw.close();
				} catch (IOException e) {
					Logger.getInstance().log(e.toString());
					Logger.getInstance().log(Arrays.toString(e.getStackTrace()));
					msg = e.getLocalizedMessage();

				}
			}
		}
		return msg;
	}

	public String removeMovie(String mname, String wname) {
		String msg = null;
		List<MovieBean> movielist = getMovies(wname);
		MovieBean movie = getMovie(mname, wname);
		if (movie == null && movielist == null) {
			msg = "Movie or movie list doesnot exits";
		} else if (movielist.isEmpty()) {
			msg = "Specified wishlist doesnot contain any movies";
		} else {
			movielist.remove(movie);
			msg = uploadMovies(wname, movielist);
		}
		return msg;
	}

	public String updateMovie(MovieBean newMovie, String wname) {
		String msg = null;
		List<MovieBean> movieUpdate = getMovies(wname);
		MovieBean oldMovie = getMovie(newMovie.getMoviename(), wname);
		movieUpdate.remove(oldMovie);
		movieUpdate.add(newMovie);
		uploadMovies(wname, movieUpdate);
		msg = Constants.SUCCESS;

		return msg;
	}

	public boolean isMovieExists(String mname, String wname) {
		if (getMovie(mname, wname) != null) {
			return true;
		}
		return false;
	}

	public MovieBean getMovie(String mname, String wname) {
		BufferedReader br = null;
		try {
			String line;
			br = new BufferedReader(new FileReader(wname + Constants.FILE_EXTENSION));
			while ((line = br.readLine()) != null) {
				String[] arr = line.split(":");
				if (arr[0].equals(mname)) {
					MovieBean mb2 = new MovieBean();
					mb2.setMoviename(arr[0]);
					mb2.setDiretorname(arr[1]);
					mb2.setProduername(arr[2]);
					mb2.setReviews(arr[3]);
					mb2.setRatings(Integer.parseInt(arr[4]));
					return mb2;
				}
			}

		} catch (IOException e) {
			Logger.getInstance().log(e.toString());
			Logger.getInstance().log(Arrays.toString(e.getStackTrace()));
			return null;
		} finally {
			if (br != null)
				try {
					br.close();
				} catch (IOException e) {
					Logger.getInstance().log(e.toString());
					Logger.getInstance().log(Arrays.toString(e.getStackTrace()));

				}
		}
		return null;
	}

	public boolean isWishListExists(String wname) {
		return new File(wname + Constants.FILE_EXTENSION).exists();
	}

	public List<String> getWishList() {
		List<String> wishlist = new LinkedList<String>();
		BufferedReader br = null;
		try {
			String line;
			br = new BufferedReader(new FileReader(Constants.WISHLIST_PATH));
			while ((line = br.readLine()) != null) {
				wishlist.add(line);

			}
		} catch (IOException e) {
			Logger.getInstance().log(e.toString());
			Logger.getInstance().log(Arrays.toString(e.getStackTrace()));
			return null;
		} finally {
			if (br != null)
				try {
					br.close();
				} catch (IOException e) {
					Logger.getInstance().log(e.toString());
					Logger.getInstance().log(Arrays.toString(e.getStackTrace()));
				}
		}
		return wishlist;

	}

	private String uploadMovies(String wname, List<MovieBean> movielist) {
		String msg = null;
		BufferedWriter bw = null;
		try {
			File f = new File(wname + Constants.FILE_EXTENSION);

			bw = new BufferedWriter(new FileWriter(f));
			for (MovieBean movie : movielist) {
				bw.write(movie.getMoviename() + ":" + movie.getDiretorname() + ":" + movie.getProduername() + ":"
						+ movie.getReviews() + ":" + movie.getRatings());
				bw.newLine();
				msg = Constants.SUCCESS;
			}

		} catch (Exception e) {
			Logger.getInstance().log(e.toString());
			Logger.getInstance().log(Arrays.toString(e.getStackTrace()));
			msg = e.getLocalizedMessage();
		} finally {
			if (bw != null) {
				try {
					bw.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					msg = e.getLocalizedMessage();
				}
			}
		}
		return msg;
	}

	public Map<String, List<MovieBean>> searchMovieInWishList(String key, String wname) {

		Map<String, List<MovieBean>> searchMoviewMap = new HashMap<String, List<MovieBean>>();
		List<MovieBean> movieList = getMovies(wname);
		searchMoviewMap.put("MovieName", searchSpecificMovieField(movieList, "MovieName", key));
		searchMoviewMap.put("DirectorName", searchSpecificMovieField(movieList, "DirectorName", key));
		searchMoviewMap.put("ProducerName", searchSpecificMovieField(movieList, "ProducerName", key));
		searchMoviewMap.put("Ratings", searchSpecificMovieField(movieList, "Ratings", key));
		searchMoviewMap.put("Review", searchSpecificMovieField(movieList, "Review", key));
		return searchMoviewMap;
	}

	private List<MovieBean> searchSpecificMovieField(List<MovieBean> movieList, String fieldName, String key) {
		List<MovieBean> resList = new LinkedList<MovieBean>();
		for (MovieBean movie : movieList) {
			if (fieldName.equals("MovieName") && movie.getMoviename().contains(key)) {
				resList.add(movie);
			} else if (fieldName.equals("DirectorName") && movie.getDiretorname().contains(key)) {
				resList.add(movie);
			} else if (fieldName.equals("ProducerName") && movie.getProduername().contains(key)) {
				resList.add(movie);
			} else if (fieldName.equals("Review") && movie.getReviews().contains(key)) {
				resList.add(movie);
			} else if (fieldName.equals("Ratings") && String.valueOf(movie.getRatings()).contains(key)) {
				resList.add(movie);
			}

		}
		return resList;

	}

}

