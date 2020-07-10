package chaos.mod.util.utils;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;

import org.apache.commons.io.IOUtils;

import chaos.mod.util.Reference;

public class UtilEki {
	private static final UtilEki EKI = new UtilEki();
	
	public static UtilEki getUtil() {
		return EKI;
	}
	
	public UtilVersionChecker getVersionChecker() {
		return new UtilVersionChecker();
	}
	
	public static class UtilVersionChecker {
		public boolean isLatestVersion() {
			String latestVersion = worker();
			return latestVersion.equals(Reference.VERSION);
		}

		public String getLatestVersion() {
			return worker();
		}

		private String worker() {
			InputStream in = null;
			String latestVersion = null;
			try {
				in = new URL("http://chaos-module.wdfiles.com/local--code/eki").openStream();
			} catch (MalformedURLException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}

			try {
				latestVersion = IOUtils.readLines(in, Charset.defaultCharset()).get(0);
			} catch (IOException e) {
				e.printStackTrace();
			} finally {
				IOUtils.closeQuietly(in);
			}

			return latestVersion;
		}
	}
}
