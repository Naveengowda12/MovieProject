package com.naveen.uttara.movie;

import java.io.File;

public interface Constants {

	String SUCCESS = "success";
	String FILE_EXTENSION = ".wl";
	String WISHLIST_PATH = "WishList" + FILE_EXTENSION;
	File LOG_FILE = new File("log.txt");
	boolean DEVELOPEMENT_MODE = true;

}