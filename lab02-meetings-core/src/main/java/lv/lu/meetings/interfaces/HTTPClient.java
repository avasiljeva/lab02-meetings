package lv.lu.meetings.interfaces;

import java.io.InputStream;

/**
 * HTTP client component used to download data from the Web.
 * 
 * TODO [task] implement it to send requests to Foursquare.
 */
public interface HTTPClient {

	/**
	 * Downloads page content from specified URL and returns it as InputStream
	 * 
	 * @param url - page URL to download
	 * @return	input stream
	 */
	public InputStream download(String url);

}
