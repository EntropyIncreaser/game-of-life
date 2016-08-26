/*
 * A game inspired from Conway's Game Of Life.
 * Copyright (C) 2016 Huaji Studio.
 * <p>
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * <p>
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * <p>
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.huajistudio.gameoflife.api.util;

import org.huajistudio.gameoflife.api.GameOfLifeAPI;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.Locale;
import java.util.PropertyResourceBundle;
import java.util.ResourceBundle;

/**
 * Interface for the developer who want to have custom internalization support for this game.
 * <span style="background-color: red;">WARNING: You must have UTF-8 support! Default control is included in this class.</span>
 */
public interface II18n {
	/**
	 * Parse the string that need to be internalization.
	 * @param key the key to the string
	 * @return the localized string
     */
	String parse(String key);

	public static class UTF8Control extends ResourceBundle.Control {
		public ResourceBundle newBundle
			(String baseName, Locale locale, String format, ClassLoader loader, boolean reload)
			throws IllegalAccessException, InstantiationException, IOException
		{
			// The below is a copy of the default implementation.
			String bundleName = toBundleName(baseName, locale);
			String resourceName = toResourceName(bundleName, "properties");
			ResourceBundle bundle = null;
			InputStream stream = null;
			if (reload) {
				URL url = loader.getResource(resourceName);
				if (url != null) {
					URLConnection connection = url.openConnection();
					if (connection != null) {
						connection.setUseCaches(false);
						stream = connection.getInputStream();
					}
				}
			} else {
				stream = loader.getResourceAsStream(resourceName);
			}
			if (stream != null) {
				try {
					// Only this line is changed to make it to read properties files as UTF-8.
					bundle = new PropertyResourceBundle(new InputStreamReader(stream, "UTF-8"));
				} catch (IOException e) {
					GameOfLifeAPI.LOGGER.error(e);
				} finally {
					stream.close();
				}
			}
			return bundle;
		}
	}
}
