package lv.lu.meetings.impl;

import java.io.InputStream;
import java.util.Map;

import lv.lu.meetings.interfaces.HTTPClient;

import org.springframework.stereotype.Component;

/**
 * TODO [task] Your task is to implement this HTTP client
 */
@Component
public class ApacheHTTPClient implements HTTPClient {

	/* Optional proxy configuration settings.
	 * Use it only if you need.
	 */
	private Map<String, String> configurationSettings;
	
	@Override
	public InputStream download(String url){
		throw new RuntimeException("Implement me");
	}

	public Map<String, String> getConfigurationSettings() {
		return configurationSettings;
	}

	public void setConfigurationSettings(Map<String, String> configurationSettings) {
		this.configurationSettings = configurationSettings;
	}
}
