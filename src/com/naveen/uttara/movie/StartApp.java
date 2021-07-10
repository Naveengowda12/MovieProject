package com.naveen.uttara.movie;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class StartApp {

	public static void main(String[] args) {
		Scanner sc2 = new Scanner(System.in);
		Scanner sc1 = new Scanner(System.in);
		try {

			int ch1 = 0;
			String wname, mname, dname, pname, review;
			int rating;
			MovieModel mm = new MovieModel();

			while (ch1 != 4) {
				System.out.println("1 Creating a MovieWishList");
				System.out.println("2 Load MovieWishList");
				System.out.println("3 List");
				System.out.println("4 Exit");
				System.out.println("Enter your choice");
				ch1 = sc1.nextInt();
				switch (ch1) {
				case 1:
					System.out.println("------------Creating a MovieWishList-------------");

					wname = null;
					while (wname == null) {
						try {
							System.out.println("Enter your wishList");
							wname = MovieUtil.validateWName(sc2.nextLine(), wname, true, true);
						} catch (IllegalArgumentException e) {
							System.err.println(e.getMessage());
						}
					}
					if (mm.isWishListExists(wname)) {
						System.err.println("You Entered WishList Already Exits");
					} else {
						String res = mm.addWishList(wname);
						if (res.equals(Constants.SUCCESS)) {
							System.out.println("WishList Created Sucessfully");
							showMenu(wname);
						} else {
							System.err.println("Unable to create wishlist beacuse " + res);
						}
					}
					break;
				case 2:
					System.out.println("-------Loading WishList------------");
					wname = null;
					while (wname == null) {
						try {
							System.out.println("Enter your wishList");
							wname = MovieUtil.validateWName(sc2.nextLine(), wname, true, true);
						} catch (IllegalArgumentException e) {
							System.err.println(e.getMessage());

						}
					}
					if (mm.isWishListExists(wname)) {
						showMenu(wname);
					} else {
						System.err.println("WishList Not Found");
					}
					break;
				case 3:
					System.out.println("--------Showing WishList---------");
					List<String> WishList = mm.getWishList();
					if (WishList == null) {
						System.out.println("Something Bad Happened Please Check in log file at path "
								+ Constants.LOG_FILE.getAbsolutePath());

					} else if (WishList.isEmpty()) {
						System.out.println("WishList is Empty");
					} else {
						for (String ls : WishList) {
							System.out.println(ls);
						}
					}
					break;

				case 4:
					System.out.println("Tata-Bye-Bye");
					break;

				default:
					System.out.println("Option Not Supportted yet");
					break;

				}

			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void showMenu(String wname) {
		Scanner sc2 = new Scanner(System.in);
		Scanner sc1 = new Scanner(System.in);
		int choice = 0, choice1 = 0;
		String mname, dname, pname, review;
		int rating;
		MovieModel mm = new MovieModel();

		while (choice != 7) {
			System.out.println("1 Add a movie");
			System.out.println("2 Edit a movie");
			System.out.println("3 Remove a movie");
			System.out.println("4 List the movies");
			System.out.println("5 Search a movie");
			System.out.println("6 Sort a movie");
			System.out.println("7 Go Back");
			System.out.println("Enter your choice");
			choice = sc1.nextInt();
			switch (choice) {
			case 1:
				System.out.println("------------Creating a movie------------");
				mname = null;
				while (mname == null) {
					try {
						System.out.println("Enter your Moviename");
						mname = MovieUtil.validateWName(sc2.nextLine(), "Movie name", true, false);
					} catch (IllegalArgumentException e) {
						System.err.println(e.getMessage());
					}
				}
				if (mm.isMovieExists(mname, wname)) {
					System.err.println("Movie is already exists");
					break;
				}

				dname = null;
				while (dname == null) {
					try {
						System.out.println("Enter Director name");
						dname = MovieUtil.validateWName(sc2.nextLine(), "Director name", true, true);
					} catch (IllegalArgumentException e) {
						System.err.println(e.getMessage());
					}
				}

				pname = null;
				while (pname == null) {
					try {
						System.out.println("Enter Producer Name");
						pname = MovieUtil.validateWName(sc2.nextLine(), "Producer name", true, true);
					} catch (IllegalArgumentException e) {
						System.err.println(e.getMessage());
					}
				}

				review = null;
				while (review == null) {
					try {
						System.out.println("Enter your Review");
						review = MovieUtil.validateWName(sc2.nextLine(), "Review", true, false);
					} catch (IllegalArgumentException e) {
						System.err.println(e.getMessage());
					}
				}

				rating = 0;
				while (rating == 0) {
					try {
						System.out.println("Enter your Rating (1-Low and 5-high)");
						rating = MovieUtil.validateRating(sc1.nextInt());
					} catch (IllegalArgumentException e) {
						System.err.println(e.getMessage());
					}
				}
				MovieBean mb = new MovieBean(mname, dname, pname, rating, review);
				String res = mm.addMovie(mb, wname);
				if (res.equals(Constants.SUCCESS)) {
					System.out.println("Movie Added Sucessfully");
				} else {
					System.out.println("Unable to add Movie beacuse" + res);
				}

				break;
			case 2:
				System.out.println("------------Editing a Movie------------");
				mname = null;
				while (mname == null) {
					try {
						System.out.println("Enter your Moviename");
						mname = MovieUtil.validateWName(sc2.nextLine(), "Movie name", true, false);
					} catch (IllegalArgumentException e) {
						System.err.println(e.getMessage());
					}
				}
				if (mm.isMovieExists(mname, wname)) {
					MovieBean mv = mm.getMovie(mname, wname);
					while (choice1 != 5) {
						System.out.println("Director Name: " + mv.getDiretorname());
						System.out.println("Producer Name: " + mv.getProduername());
						System.out.println("Review: " + mv.getReviews());
						System.out.println("Ratings: " + mv.getRatings());

						System.out.println();
						System.out.println("1 Edit director name");
						System.out.println("2 Edit producer name");
						System.out.println("3 Edit your review");
						System.out.println("4 Edit your rating");
						System.out.println("5 Save new details");
						System.out.println("6 go cancel");

						System.out.println("Enter your choice");
						choice1 = sc1.nextInt();
						switch (choice1) {
						case 1:
							System.out.println("Enter director name to edit");

							String newDName = null;
							while (newDName == null) {
								try {
									newDName = MovieUtil.validateWName(sc2.nextLine(), "Director Name", true, true);
								} catch (IllegalArgumentException e) {
									System.out.println(e.getMessage());
								}
							}

							mv.setDiretorname(newDName);

							break;

						case 2:
							System.out.println("Enter producer name to edit");

							String newPName = null;
							while (newPName == null) {
								try {
									newPName = MovieUtil.validateWName(sc2.nextLine(), "Producer Name", true, true);
								} catch (IllegalArgumentException e) {
									System.out.println(e.getMessage());
								}
							}
							mv.setProduername(newPName);
							break;

						case 3:
							System.out.println("Enter new review");

							String newReview = null;
							while (newReview == null) {
								try {
									newReview = MovieUtil.validateWName(sc2.nextLine(), "Review", true, false);
								} catch (IllegalArgumentException e) {
									System.out.println(e.getMessage());
								}
							}
							mv.setReviews(newReview);
							break;

						case 4:
							System.out.println("Enter new ratings");

							int newRatings = 0;
							while (newRatings == 0) {
								try {
									newRatings = MovieUtil.validateRating(newRatings);
								} catch (IllegalArgumentException e) {
									System.out.println(e.getMessage());
								}
							}
							mv.setRatings(newRatings);
							break;

						case 5:

							if (mm.updateMovie(mv, wname).equals(Constants.SUCCESS)) {
								System.out.println("movie updated succesfully");
							} else {
								System.out.println("Movie updation failed");
							}
							break;

						default:
							System.out.println("Invalid choice");
							break;
						}
					}
				} else {
					System.out.println("Movie not found");
				}

				break;
			case 3:
				System.out.println("------------Delete a Movie--------------");
				mname = null;
				while (mname == null) {
					try {
						System.out.println("Enter your Moviename");
						mname = MovieUtil.validateWName(sc2.nextLine(), "Movie name", true, false);
					} catch (IllegalArgumentException e) {
						System.err.println(e.getMessage());
					}
				}
				if (!mm.isMovieExists(mname, wname)) {
					System.err.println("Movie does not exist in this Wishlist to remove");
					break;
				}

				String removeRes = mm.removeMovie(mname, wname);
				if (removeRes == null) {
					System.err.println("Movielist not be null");

				} else if (removeRes.equals(Constants.SUCCESS)) {
					System.out.println("Deleted Sucessfully");
				} else {
					System.out.println("Movie Not Deleted Beacuse " + removeRes);
				}
				break;
			case 4:
				System.out.println("----------List the Movies----------------");
				List<MovieBean> movielist = mm.getMovies(wname);
				if (movielist == null) {
					System.out.println("Something Bad Happened Please Check in log file at path "
							+ Constants.LOG_FILE.getAbsolutePath());

				} else if (movielist.isEmpty()) {
					System.out.println("MovieList is Empty");
				} else {
					for (MovieBean ls : movielist) {
						System.out.println("Movie Name: " + ls.getMoviename() + "Director Name:" + ls.getDiretorname()
								+ "Producer Name: " + ls.getProduername() + "Review: " + ls.getReviews() + "Ratings: "
								+ ls.getRatings());
					}
				}

				break;
			case 5:
				System.out.println("Search Movies Based on MovieName,DirectorName,ProducerName,Reviews");
				String key = null;
				while (key == null) {
					try {
						key = MovieUtil.validateWName(sc2.nextLine(), "Name", true, true);
					} catch (IllegalArgumentException e) {
						System.out.println(e.getLocalizedMessage());
					}

				}
				Map<String, List<MovieBean>> mv = mm.searchMovieInWishList(key, wname);
				System.out.println("Total number of occurances in Movie Name: " + mv.get("movieName").size());
				for (MovieBean movie : mv.get("movieName")) {
					System.out.println("Movie Name: " + movie.getMoviename() + " Director: " + movie.getDiretorname()
							+ " Producer: " + movie.getProduername() + " Review: " + movie.getReviews() + "  Ratings: "
							+ movie.getRatings());
				}
				System.out.println();

				System.out.println("Total number of occurances in Director Name: " + mv.get("directorName").size());
				for (MovieBean movie : mv.get("directorName")) {
					System.out.println("Movie Name: " + movie.getMoviename() + " Director: " + movie.getDiretorname()
							+ " Producer: " + movie.getProduername() + " Review: " + movie.getReviews() + "  Ratings: "
							+ movie.getRatings());
				}

				System.out.println();

				System.out.println("Total number of occurances in producerName : " + mv.get("producerName").size());
				for (MovieBean movie : mv.get("producerName")) {
					System.out.println("Movie Name: " + movie.getMoviename() + " Director: " + movie.getDiretorname()
							+ " Producer: " + movie.getProduername() + " Review: " + movie.getReviews() + "  Ratings: "
							+ movie.getRatings());
				}

				System.out.println();

				System.out.println("Total number of occurances in ratings: " + mv.get("ratings").size());
				for (MovieBean movie : mv.get("ratings")) {
					System.out.println("Movie Name: " + movie.getMoviename() + " Director: " + movie.getDiretorname()
							+ " Producer: " + movie.getProduername() + " Review: " + movie.getReviews() + "  Ratings: "
							+ movie.getRatings());
				}

				System.out.println();

				System.out.println("Total number of occurances in review: " + mv.get("review").size());
				for (MovieBean movie : mv.get("review")) {
					System.out.println("Movie Name: " + movie.getMoviename() + " Director: " + movie.getDiretorname()
							+ " Producer: " + movie.getProduername() + " Review: " + movie.getReviews() + "  Ratings: "
							+ movie.getRatings());
				}

				break;

			case 6:
				System.out.println("------------Sort Movies---------------");
				mname = null;
				int ch=0;
				if(!mm.isMovieExists(mname, wname))
				{
					while(ch!=5)
					{
						System.out.println("1 Sort Based on Movie Name");
						System.out.println("2 Sort Based on Director Name");
						System.out.println("3 Sort Based on Producer Name");
						System.out.println("4 Sort Based on Ratings");
						System.out.println("5 Go Back");
						System.out.println("Enter your choice");
						ch=sc1.nextInt();
						switch(ch)
						{
						case 1:
							System.out.println("Sorting The Movie Names");
							List<MovieBean>ls3 = mm.getMovies(wname);
							MovieNameComparator mnc = new MovieNameComparator();
							Collections.sort(ls3, mnc);
							for(MovieBean mb5:ls3)
							{
								System.out.println(mb5);
							}
							break;
						case 2:
							System.out.println("Sorting The Director Names");
							List<MovieBean>ls=mm.getMovies(wname);
							DirectorNameCompartor dnc = new DirectorNameCompartor();
							Collections.sort(ls, dnc);
							for(MovieBean mb2:ls)
							{
								System.out.println(mb2);
							}
							
							break;
						case 3:
							System.out.println("Sorting the Producer Name");
							List<MovieBean>ls1 = mm.getMovies(wname);
							ProducerNameComparator pnc = new ProducerNameComparator();
							Collections.sort(ls1, pnc);
							for(MovieBean mb3:ls1)
							{
								System.out.println(mb3);
							}
							break;
						case 4:
							System.out.println("Sorting the Ratings");
							List<MovieBean>ls4 = mm.getMovies(wname);
							MovieRatingComparator mrc = new MovieRatingComparator();
							Collections.sort(ls4, mrc);
							for(MovieBean mb3:ls4)
							{

								System.out.println(mb3);
							}
							break;
							
						case 5:
							System.out.println("Going Back");
							break;
						}
					}
				}
				
			case 7:
				System.out.println("Going Back");
				break;

			}
		}
	}
}

