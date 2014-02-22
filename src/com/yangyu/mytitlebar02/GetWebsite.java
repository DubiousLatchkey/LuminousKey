package com.yangyu.mytitlebar02;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;


public class GetWebsite {
		
	
		 public double[] GetWebsite(){
			 double rates[] = new double[40];
			 int idx = 0;
			 try{
		        URL oracle = new URL("http://www.ecb.europa.eu/stats/eurofxref/eurofxref-daily.xml");
		        URLConnection yc = oracle.openConnection();
		        BufferedReader in = new BufferedReader(new InputStreamReader(
		                                    yc.getInputStream()));
		        String inputLine;
		        while ((inputLine = in.readLine()) != null) {
		        	String ConvertRate[] = inputLine.split(" ");
		        	if (ConvertRate.length == 3 && ConvertRate[0].contains("Cube")){
		        		//System.out.println(ConvertRate[1].substring(10, 13));
		        		//System.out.println(ConvertRate[2].substring(6, ConvertRate[2].length() - 3));
		        		Double rate = Double.parseDouble(ConvertRate[2].substring(6, ConvertRate[2].length() - 3));
		        		rates[idx++] = rate;
		        	
		        	}
		        	
		        	
		        }
		        in.close();
		    }
			 catch(Exception e){}
			 for (int i = 0; i < idx ; i++){
	        		System.out.println(i + " " + rates[i]);
	        	}
			return rates;
		 }
		 
		 
	

}
