package springmvc.login.it;

import static org.junit.Assert.*;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;

import org.apache.commons.io.IOUtils;
import org.junit.Before;
import org.junit.Test;

public class HelloIT{
	
	private static String port, name;
	
	@Before
	public void setup() {
		port = System.getProperty("servlet.port", "9090");
	}

	@Test
	public void hello() {
		
	}

}
