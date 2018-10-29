package com.citrix.elearning.candidatemerge.utils;

import java.util.ResourceBundle;

/**
 * @author amulya.mummadi
 *
 */
public class PropertyUtil {

	static ResourceBundle bundle = ResourceBundle.getBundle("config");

	/**
	 * Method to get Property from Properties file
	 * 
	 * @param key
	 * @return property
	 */
	public static String getProperty(String key) {
		return bundle.getString(key);
	}

}
