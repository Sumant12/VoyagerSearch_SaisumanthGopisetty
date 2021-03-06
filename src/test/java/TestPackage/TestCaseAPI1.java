package TestPackage;
import static org.junit.Assert.*;

import java.net.HttpURLConnection;
import java.net.URL;

import org.junit.Test;
import org.powermock.api.mockito.PowerMockito;
import org.voyagersearch.solrquerydemo.MyResource;

public class TestCaseAPI1 {

	@Test
	public void testAPI1() throws Exception{
		URL u = PowerMockito.mock(URL.class);
		 String url = "http://localhost:8983/solr/demo/get?id=book1";
        PowerMockito.whenNew(URL.class).withArguments(url).thenReturn(u);
        HttpURLConnection huc = PowerMockito.mock(HttpURLConnection.class);
        PowerMockito.when(huc.getResponseCode()).thenReturn(200);
        assertTrue(MyResource.isUrlAccessible(url));
	}

}
