package data;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class MovieAttributes {

	public  ArrayList<String> getMovieAttributes(String MovieName) throws Exception{
		 ArrayList<String> MovieAttributes = new ArrayList<String>();
	
		    //Find Movie Link:
			String page;
	        String link=null;
	        String regex="+";
	        MovieName=MovieName.replaceAll(" ", regex);
	        URL website = new URL("http://www.bing.com/search?q=imdb+"+MovieName);
	        URLConnection urlcon = website.openConnection();
	        BufferedReader in = new BufferedReader(new InputStreamReader(urlcon.getInputStream()));
	        String word;
	        StringBuffer line = new StringBuffer();
	        while((word=in.readLine())!=null){
	         line.append(word);   
	        }
	        page=line.toString();
	        Pattern p1 = Pattern.compile("(<h3><a href=\")(.*?)(\" h=\")(.*?)(\">)");
	        Matcher m1 = p1.matcher(page);
	        int i=0;
	        while(m1.find()){
	        	link=m1.group(2);
	        	i++;
	        	if(i==1) break;
	        }
	        if(MovieName.equals("m")|| MovieName.equals("M"))
           	link="http://www.imdb.com/title/tt0022100";
       
	      
	        
	        //Find Genre:
	        URL website2= new URL(link);
	        URLConnection url2 = website2.openConnection();
	        BufferedReader in2 = new BufferedReader(new InputStreamReader(url2.getInputStream()));
	        String data;
	        StringBuffer sb = new StringBuffer();
	        while((data=in2.readLine())!=null){
	        	sb.append(data);
	        }		
	        data=sb.toString();
	        Pattern p2 = Pattern.compile("(<span class=\"itemprop\" itemprop=\"genre\">)(.*?)(</span>)");
	        Matcher m2 = p2.matcher(data);
	        while(m2.find()){
                      
	        	MovieAttributes.add(m2.group(2));
               
              }
	                
	    //Find Director:
		  int i3=0;
          Pattern p3 = Pattern.compile("(<a href=\")(.*?)(\")( itemprop='url'><span class=\"itemprop\" itemprop=\"name\">)(.*?)(</span></a>)");
          Matcher m3 = p3.matcher(data);
          while(m3.find()){
   	      MovieAttributes.add(m3.group(5));
          i3++;
          if(i3>=1) break;
          }	
		
       // Find Actors:
          	int i4=0;
          	Pattern p4 = Pattern.compile("(<img height=\"44\" width=\"32\" alt=\")(.*?)(\" title=)");
          	Matcher m4 = p4.matcher(data);
          	while(m4.find()){
            i4++;
            MovieAttributes.add(m4.group(2));
             if(i4>=3) break;
         }
		
		return MovieAttributes;
}

	public  String getOriginalName(String MovieName) throws Exception{           
	    String page;
        String link=null;
        String regex="+";
        MovieName=MovieName.replaceAll(" ", regex);
        URL website = new URL("http://www.bing.com/search?q=imdb+"+MovieName);
        URLConnection urlcon = website.openConnection();
        BufferedReader in = new BufferedReader(new InputStreamReader(urlcon.getInputStream()));
        String word;
        StringBuffer line = new StringBuffer();
        while((word=in.readLine())!=null){
         line.append(word);   
        }
        page=line.toString();
        Pattern p1 = Pattern.compile("(<h3><a href=\")(.*?)(\" h=\")(.*?)(\">)");
        Matcher m1 = p1.matcher(page);
        int i=0;
        while(m1.find()){
          link=m1.group(2);
          i++;
          if(i==1) break;
        }
	
	String OriginalName=null;
    URL website1= new URL(link);
    URLConnection url1 = website1.openConnection();
    BufferedReader in1 = new BufferedReader(new InputStreamReader(url1.getInputStream()));
    String data;
    StringBuffer sb = new StringBuffer();
    while((data=in1.readLine())!=null){
            sb.append(data);
    }		
    data=sb.toString();
    Pattern p2 = Pattern.compile(" (<title>)(.*?)( \\()");
    Matcher m2 = p2.matcher(data);
    while(m2.find()){
        OriginalName=m2.group(2);
      }
return OriginalName;
}
	
}
