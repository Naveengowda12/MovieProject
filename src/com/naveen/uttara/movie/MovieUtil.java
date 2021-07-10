package com.naveen.uttara.movie;

public class MovieUtil {
	public static String validateWName(String name, String fieldName, boolean isCheckMaxLength,
			boolean isCheckFirstLetter) {
		if (name == null) {
			throw new IllegalArgumentException(fieldName + " Cannot be null");
		}
		name = name.trim();
		if (name.isEmpty()) {
			throw new IllegalArgumentException("WishList Cannot be Empty");
		}

		if (isCheckFirstLetter && !Character.isLetter(name.charAt(0))) {
			throw new IllegalArgumentException("WishList must Contain First character as letter");
		} else if (name.length() < 2) {
			throw new IllegalArgumentException(fieldName + " must contain 2 letters");

		} else if (isCheckMaxLength && name.length() > 40) {
			throw new IllegalArgumentException(fieldName + " must contain maximum 40 letters");
		}
		for (int i = 1; i < name.length(); i++) {
			char c = name.charAt(i);
			if (!(Character.isDigit(c) || Character.isLetter(c) || Character.isWhitespace(c) || c == '.')) {
				throw new IllegalArgumentException(fieldName + " Contains letter Digits and White Space");
			}

		}
		return name;
	}
	
	public static int validateRating(int rating)
	{
		if(rating <= 0)
			throw new IllegalArgumentException("Rating Should not be negative");
		if(rating > 5)
			throw new IllegalArgumentException("Rating Should not be greater than 5");
			return rating;
	}

}
