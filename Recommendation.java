/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.NavigableMap;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeMap;


import data.MovieAttributes;

/**
 *
 * @author Mandar Dalvi
 */
public class Recommendation {
    static String userMovie;
    
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new FileReader("EdgeListFinal.txt"));
        String data;
        String movie1;
        String movie2;
        
        
        MovieAttributes mov = new MovieAttributes();
        Scanner scan =  new Scanner(System.in);
        System.out.println("Please enter a movie");
        userMovie=scan.nextLine();
        System.out.println("Please wait till we recommend you some movies....!!");
        userMovie=mov.getOriginalName(userMovie);
        
        List<String> cowatched = new ArrayList<String>();
        ArrayList<String> movieAttributes = new ArrayList<String>();
        movieAttributes = mov.getMovieAttributes(userMovie);
       // System.out.println(""+movieAttributes);
        while((data=br.readLine())!=null){
            movie1=data.split("#")[0];
            movie2=data.split("#")[1];
            if(movie1.equals(userMovie))
                cowatched.add(movie2);
            if(movie2.equals(userMovie))
               cowatched.add(movie1);
            
    }
     int size;
     //size=cowatched.size();
     HighestPagerank pagerank=new HighestPagerank();
     List<List<String>> results=pagerank.topPagerank(cowatched) ;
     Set<String> top10Recommendations=new HashSet<String>();
     top10Recommendations.addAll(results.get(0));
     cowatched=results.get(1) ;
        //System.out.println(""+cowatched);
        //System.out.println(""+size);
        int i=1;
        List<String> finalList=getSimilarity(movieAttributes,cowatched);
        results=pagerank.topPagerank(finalList);
        top10Recommendations.addAll(results.get(0));
        System.out.println("Based on our algorithm we suggests you following movies:");
        Iterator itr3 = top10Recommendations.iterator();           	
                       while(itr3.hasNext()){
			String element = itr3.next().toString();
                        System.out.println(i+++":"+element);
        }
                    
        if(top10Recommendations.size()==0){
            System.out.println("\nWe have no reccomendations for you..please try some other movie" );
            System.exit(0);
        }
            
        System.out.println("\nGet some pop corn & drink and enjoy the movies..!!");
        
    }
    
    	public static List<String> getSimilarity(ArrayList<String> attributes,List<String> cowatched) throws Exception{
		Map<Float,String> similar = new TreeMap<Float,String>();
                
		BufferedReader bin = new BufferedReader(new FileReader("DatabaseFinal.txt"));
                //BufferedReader bin2 = new BufferedReader(new FileReader("top250.txt"));
		String data;
		String movieName;
		int intersection;
		int union;
		int size;
		float similarity;
		while((data=bin.readLine())!=null){
                    String movieAttributes[]=data.split("#");
			movieName=movieAttributes[0];
                        if(cowatched.contains(movieName))
                    {
			ArrayList<String> movieAttributes1 = new ArrayList<String>();
			ArrayList<String> movieAttributes2 = new ArrayList<String>();

			float constant=(float)0.0000001;
			for(int i=1;i<movieAttributes.length;i++){
				movieAttributes1.add(movieAttributes[i]);
				movieAttributes2.add(movieAttributes[i]);
			}
			size=movieAttributes1.size();
			movieAttributes1.retainAll(attributes);
			intersection= movieAttributes1.size();
			movieAttributes2.removeAll(attributes);
			union=size+movieAttributes2.size();
			similarity= (float)intersection/union;
			if(similar.containsKey(similarity)){
				while(similar.containsKey(similarity)){
					similarity = similarity + constant;
				}
			    similar.put(similarity, movieName);	
			}
			else
				similar.put(similarity, movieName);
		}
        } 
		bin.close();
		Map<Float,String> treeMap=((NavigableMap<Float,String>) similar).descendingMap();
                List<String> finalList = new ArrayList<String>();
		int size1 =similar.size();
                int counter=0;
                for (Map.Entry entry : treeMap.entrySet()) { 
                    if(counter>=10){
                        break;
                    }
                    if(!finalList.contains(entry.getValue().toString()) && !(userMovie.equals(entry.getValue().toString()))){
                        finalList.add(entry.getValue().toString());
                   // System.out.println((counter+1)+" : "+entry.getKey() + " : " + entry.getValue()); 
                    counter++;
                    }
		}
                return finalList;
	}
}

