package chaos.mod.util.utils;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;

import org.apache.commons.io.IOUtils;

import chaos.mod.util.Reference;

public class UtilVersionChecker {
	private static final UtilVersionChecker VC = new UtilVersionChecker();

	public static UtilVersionChecker get() {
		return VC;
	}

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
			in = new URL("https://raw.githubusercontent.com/KyleLin921021/EkiMod/master/src/main/resources/assets/eki/misc/version").openStream();
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
