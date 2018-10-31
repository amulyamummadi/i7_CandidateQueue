package com.citrix.elearning.candidatemerge.utils;

import java.util.ResourceBundle;

/**
 * This class contains functionality of accessing config file.
 *
 * @author amulya.mummadi
 *
 */
public class PropertyUtil {

	static ResourceBundle bundle = ResourceBundle.getBundle("config");

	/**
	 * Method to get property from config file.
	 *
	 * @param key
	 * @return property
	 */
	public static String getProperty(String key) {
		return bundle.getString(key);
	}

}
