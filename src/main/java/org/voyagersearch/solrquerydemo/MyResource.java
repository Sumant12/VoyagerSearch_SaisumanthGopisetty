package org.voyagersearch.solrquerydemo;


import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.net.UnknownHostException;
import java.nio.charset.Charset;



@Path("myresource")
public class MyResource {
	
	public static boolean isUrlAccessible(final String urlToValidate)  {
        URL url = null;
        HttpURLConnection huc = null;
        int responseCode = -1;
        try {
            url = new URL(urlToValidate);
            huc = (HttpURLConnection) url.openConnection();
            huc.setRequestMethod("HEAD");
            huc.connect();
            responseCode = huc.getResponseCode();}
           catch (Exception e) {
			System.out.println("Some Exception. The url is not reachable");
		} 
            
            finally {
            if (huc != null) {
                huc.disconnect();
            }
        }
        return responseCode == 200;
    }
	
	@GET
	@Path("/id/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public String getBook(@PathParam("id") String id){
		String myURL = "http://localhost:8983/solr/demo/get?id="+id;
		System.out.println("Requeted URL:" + myURL);
		StringBuilder sb = new StringBuilder();
		URLConnection urlConn = null;
		InputStreamReader in = null;
		try {
			URL url = new URL(myURL);
			urlConn = url.openConnection();
			if (urlConn != null)
				urlConn.setReadTimeout(60 * 1000);
			if (urlConn != null && urlConn.getInputStream() != null) {
				in = new InputStreamReader(urlConn.getInputStream(),
						Charset.defaultCharset());
				BufferedReader bufferedReader = new BufferedReader(in);
				if (bufferedReader != null) {
					int cp;
					while ((cp = bufferedReader.read()) != -1) {
						sb.append((char) cp);
					}
					bufferedReader.close();
				}
			}
			in.close();
		} catch (Exception e) {
			throw new RuntimeException("Exception while calling URL:"+ myURL, e);
		} 

		return sb.toString();


	}


	@GET
	@Path("/query/{query}/filters/{filter1}/{filter2}")
	@Produces(MediaType.APPLICATION_JSON)
	public String getRequiredReturnTypes(@PathParam("query") String query,@PathParam("filter1") String filter1,@PathParam("filter2") String filter2){	
		String myURL="http://localhost:8983/solr/demo/query?q="+query+"&fl="+filter1+","+filter2;
		System.out.println("Requeted URL:" + myURL);
		StringBuilder sb = new StringBuilder();
		URLConnection urlConn = null;
		InputStreamReader in = null;
		try {
			URL url = new URL(myURL);
			urlConn = url.openConnection();
			if (urlConn != null)
				urlConn.setReadTimeout(60 * 1000);
			if (urlConn != null && urlConn.getInputStream() != null) {
				in = new InputStreamReader(urlConn.getInputStream(),
						Charset.defaultCharset());
				BufferedReader bufferedReader = new BufferedReader(in);
				if (bufferedReader != null) {
					int cp;
					while ((cp = bufferedReader.read()) != -1) {
						sb.append((char) cp);
					}
					bufferedReader.close();
				}
			}
			in.close();
		} catch (Exception e) {
			throw new RuntimeException("Exception while calling URL:"+ myURL, e);
		} 

		return sb.toString();


	}

	@GET
	@Path("/deepquery/publisher/{publisher}/rows/{rows}/sortparam/{sortparam}/sortorder/{sortorder}")
	@Produces(MediaType.APPLICATION_JSON)	
	public String useAdvancedFilters(@PathParam("publisher") String publisher,@PathParam("rows") String rows,@PathParam("sortparam") String sortparam
			,@PathParam("sortorder") String sortorder){	
		//String myURL="http://localhost:8983/solr/demo/query?q=*:*&fq=publisher_s:Bantam&rows=3& sort=pubyear_i desc& fl=title_t,pubyear_i";
		String myURL= "http://localhost:8983/solr/demo/query?q=*:*&fq=publisher_s:"+publisher+"&rows="+rows+"&%20sort="+sortparam+"%20"+sortorder;
		//working url2
		//		String myURL= "http://localhost:8983/solr/demo/query?q=*:*&fq=publisher_s:Bantam&rows=3&%20sort=pubyear_i%20desc";
		//working url1
		//	String myURL= "http://localhost:8983/solr/demo/query?q=*:*&fq=publisher_s:Bantam&rows=3&%20sort=pubyear_i%20desc&%20fl=title_t,pubyear_i";
		System.out.println("Requeted URL:" + myURL);
		StringBuilder sb = new StringBuilder();
		URLConnection urlConn = null;
		InputStreamReader in = null;
		try {
			URL url = new URL(myURL);
			urlConn = url.openConnection();
			if (urlConn != null)
				urlConn.setReadTimeout(60 * 1000);
			if (urlConn != null && urlConn.getInputStream() != null) {
				in = new InputStreamReader(urlConn.getInputStream(),
						Charset.defaultCharset());
				BufferedReader bufferedReader = new BufferedReader(in);
				if (bufferedReader != null) {
					int cp;
					while ((cp = bufferedReader.read()) != -1) {
						sb.append((char) cp);
					}
					bufferedReader.close();
				}
			}
			in.close();
		} catch (Exception e) {
			throw new RuntimeException("Exception while calling URL:"+ myURL, e);
		} 

		return sb.toString();
	}

}


