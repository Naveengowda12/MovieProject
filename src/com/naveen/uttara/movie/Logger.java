package com.naveen.uttara.movie;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Date;

public class Logger {
	private static Logger obj = null;

	private Logger() {
	
	}

	public static Logger getInstance() {
		if (obj == null)
			synchronized (Logger.class) {
				if (obj == null) {
					obj = new Logger();
				}

			}
		return obj;

	}

	public void log(String data) {
		new Thread(new Runnable() {

			public void run() {

				BufferedWriter bw = null;
				try {
					bw = new BufferedWriter(new FileWriter(Constants.LOG_FILE, true));
					Date dt = new Date();
					bw.write(dt.toString() + ":" + data);
					bw.newLine();
					if (Constants.DEVELOPEMENT_MODE)
						System.out.println(data);

				} catch (IOException e) {
					e.printStackTrace();
				} finally {
					if (bw != null) {
						try {
							bw.close();
						} catch (IOException e) {
							e.printStackTrace();
						}
					}
				}
			}
		}).start();

	}

}
