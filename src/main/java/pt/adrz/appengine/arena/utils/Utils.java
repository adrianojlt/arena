package pt.adrz.appengine.arena.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream.GetField;
import java.net.URL;
import java.net.URLConnection;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import com.google.cloud.datastore.Datastore;
import com.google.cloud.datastore.DatastoreOptions;
import com.google.cloud.datastore.KeyFactory;

public class Utils {

	public static String domain = "http://arenavision.in";
	public static String USER_AGENT = "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/54.0.2840.71 Safari/537.36";
	
	public static String download(String add) throws IOException {
		
		URL url;
		String finalHTML = "";

		url = new URL(add);
		URLConnection spoof = url.openConnection();

		spoof.setRequestProperty("User-Agent", Utils.USER_AGENT);
		BufferedReader in = new BufferedReader(new InputStreamReader(spoof.getInputStream()));

		String strLine = "";

		// Loop through every line in the source
		while ((strLine = in.readLine()) != null) {
			finalHTML += strLine;
		}

		return finalHTML;
	}
	
	public static String parse(String html) {

		try {
			Document doc = Jsoup.parse(html);
			Element elem = doc.select("table").first();
			return elem.html();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}
	
	public static String table() {
		
		String html = "";

		try {
			String path = firstPage();
			html = download(domain + path);
			html = parse(html);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return html;
	}
	
	public static String getLink(String param) {
		
		String acestreamPattern = "acestream://\\w*";
		//String soapPattern = "sop://[a-z.:\\d/]*";
		//String oneOrTwoDigitsPattern = "^[0-9]{1,2}$";
		String pageContent = "";
		String link = "";
		
		try {
			pageContent = download("http://arenavision.in/" + param);
		} catch (IOException e) {
			return "";
		}

		Pattern pattern = Pattern.compile(acestreamPattern);
		Matcher matcher = pattern.matcher(pageContent);

		if ( matcher.find() ) {
			link = matcher.group();
		} 
		
		return link;
	}
	
	public static void main(String[] args) {
		//System.out.println(table());
		System.out.println(getLink("1"));
		//getAcestream();
		//datastore();
		//System.out.println(table());
		//System.out.println(firstPage());
	}

	public static String firstPage() {

		String pageContent = "";
		String schedulePath = null;

		try {
			pageContent = download(domain);
			Document doc = Jsoup.parse(pageContent);
			schedulePath = doc.getElementsByClass("leaf").get(1).select("a").attr("href");
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return schedulePath;
	}
	
	public static void datastore() {
		final Datastore ds = DatastoreOptions.getDefaultInstance().getService();
		final KeyFactory keyFactory = ds.newKeyFactory().setKind("Task");
		System.out.println("keyFactory " + keyFactory.toString());
	}
}
