package imdb;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.NavigableMap;
import java.util.TreeMap;
import java.util.Map.Entry;

public class HighestPagerank {

	public List<List<String>> topPagerank(List<String> movieList) throws IOException {
			Map<String,String> movieIdMap=new HashMap<String, String>();
			Map<String,Float> idPagerakMap=new HashMap<String,Float>();
			Map<Float,String> pagerankMovieMap=new TreeMap<Float, String>();
			BufferedReader br1=new BufferedReader(new FileReader("MovieIdMap.txt"));
			BufferedReader br2=new BufferedReader(new FileReader("IdPagerankMap.csv"));
			String line="";
			while((line=br1.readLine())!=null){
				movieIdMap.put(line.split("#")[0], line.split("#")[1]);
			}
			while((line=br2.readLine())!=null){
				idPagerakMap.put(line.split(",")[0], Float.parseFloat(line.split(",")[1]));
			}
			for(String movie:movieList){
                            if(!movie.contains(".com")){
                                if(movieIdMap.containsKey(movie)){
                                    if(idPagerakMap.containsKey(movieIdMap.get(movie))){
                                        float pagerank=idPagerakMap.get(movieIdMap.get(movie));
                                        pagerankMovieMap.put(pagerank, movie);
                                    }
                                }
                            }
			}
			Map<Float,String> nMap=((NavigableMap<Float, String>)pagerankMovieMap).descendingMap();
			List<String> top5=new ArrayList<String>();
                        int count=0;
			for(Entry<Float,String> entry :nMap.entrySet()){
				top5.add(entry.getValue());
                                if(count++==4)
                                    break;
			}
			List<List<String>> returnList=new ArrayList<List<String>>();
			returnList.add(top5);
			movieList.remove(top5);
			returnList.add(movieList);
			return returnList;
	}
}
