package com.shashank.sociologyproject;
//package com.filechoser;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

//import com.google.api.GoogleAPI;
//import com.google.api.GoogleAPIException;
import com.memetix.mst.language.Language;
import com.memetix.mst.translate.Translate;

public class CodeSheet {

	private static String key1="3273491ABDD052BE4479AE897F4FBB0954A330F4";;

	public HashMap<String,HashMap<Integer, String>> mapkeyVal = new HashMap<String,HashMap<Integer, String>>();



	/**
	 * @param args
	 */
	//public static void main(String[] args) {
		// TODO Auto-generated method stub


	public void CodeSheet(){
		
	}

		public void execute(){

			Pattern pattern=null;
			Matcher matcher=null;
			String beginHeader = "([0-9]+)\\.{1} .*";
			pattern = Pattern.compile(beginHeader);
		try {
			//FileInputStream fstream1 = new FileInputStream(filesInDir[i]);
			FileInputStream fstream1 = new FileInputStream("C:\\Users\\Shashank\\Downloads\\CodebookCodes.doc");

			// Get the object of DataInputStream
			DataInputStream in1 = new DataInputStream(fstream1);
			BufferedReader br1 = new BufferedReader(new InputStreamReader(in1));
			String strLine1;
			//Read File Line By Line
			//start of reading horsename from horse_list.txt
			String location_name1;
			//ArrayList<String> locations1 = new ArrayList<String>();

			int start=0;
			String currentKey=null;
			int counter=0;
			while ((strLine1 = br1.readLine()) != null)   {
				//write here
				try{
					if(strLine1.trim().equalsIgnoreCase("<END>")){
						break;
					}

					if(strLine1.trim().equalsIgnoreCase("1. When:"))
						start=1;
					if(start==1){

						matcher = pattern.matcher(strLine1.trim());
						if(matcher.matches()){

							currentKey=strLine1.split("\\.")[1].trim();
							//System.out.println("header " + currentKey);
							counter=1;
							continue;

						}


						if(currentKey!=null && !strLine1.equalsIgnoreCase("") && strLine1!=null){
							//System.out.println("1");
							String[] key=strLine1.trim().split(" ");
							String value="";
							for(int k=1;k<key.length;k++){
								value+=key[k]+" ";
							}
							/*if(strLine1.trim().equalsIgnoreCase("SD") || strLine1.trim().equalsIgnoreCase("No Data")){
								counter=99;
							}*/
							try{
								if(Integer.parseInt(key[0])==99){
									counter=100;
								}
							}catch (Exception e) {
								// TODO: handle exception

							}
							HashMap map=new HashMap();
							if(mapkeyVal.get(currentKey)==null){
								//System.out.println("2" + currentKey + " , " + key[0] + "- " + value +" -- " + strLine1 + "--- " + (counter));
								map= new HashMap();
								try{
									int key1 = Integer.parseInt(key[0]);

									map.put(key1,value);
									mapkeyVal.put(currentKey, map);
								}catch (Exception e) {
									// TODO: handle exception
									map.put(counter,key[0]+value);
									mapkeyVal.put(currentKey, map);
								}

							}else{
								//System.out.println("3" + currentKey + " , " + key[0] + "- " + value +" -- " + strLine1 + "--- " + (counter));
								//map = (HashMap) mapkeyVal.get(currentKey);
								//String val=(String) map.get(key[0]);

								//map.put(key[0],value);
								int key1=-1;
								try{
									key1 = Integer.parseInt(key[0]);
									mapkeyVal.get(currentKey).put(key1,value);
								}catch (Exception e) {
									// TODO: handle exception
									key1 = counter;
									mapkeyVal.get(currentKey).put(key1,key[0] + value);
								}


							}
							counter++;


						}


					}

				}catch (Exception e){//Catch exception if any
					//System.err.println("Error: " + e.getMessage());
					e.printStackTrace();
				}

			}
			
					//String key="AIzaSyDXpnheChygk_EsoakmTAbpdfBMYAP4F6I";
				   
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}



	}
	public HashMap<String, HashMap<Integer, String>> getMapkeyVal() {
		return mapkeyVal;
	}
	public void setMapkeyVal(HashMap<String, HashMap<Integer, String>> mapkeyVal) {
		this.mapkeyVal = mapkeyVal;
	}

	public void printHMap(HashMap<String,HashMap<Integer, String>> mapParam){
		Iterator entries= mapParam.keySet().iterator();
		//Iterator entries1= mapkeyVal.values().iterator();

		while (entries.hasNext()) {
			

			try{
				String keys=(String) entries.next();
			HashMap map = (HashMap) mapParam.get(keys);
		    //System.out.println("key : " + keys + map);
			Iterator entries2= map.keySet().iterator();
			//Iterator entries3= map.values().iterator();

			while (entries2.hasNext()) {
				try{
					String keysin = (String) entries2.next();
					//System.out.println( keysin+ " :  " + map.get(keysin));
				}catch (Exception e) {
					// TODO: handle exception
				}
			}
			}catch (Exception e) {
				// TODO: handle exception
			}

		}

	}

	
	public String translate(String param){

		String translatedText=null;
		Translate.setKey(key1);

		String test=" 4 horas. Comenzaron a llegar desde las 10 de la mañana, Duhalde apareció a las 13 y desconcentración luego de las 2";
	    
		try {
			translatedText = Translate.execute(test, Language.SPANISH, Language.ENGLISH);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	    //System.out.println(translatedText);
		return translatedText;

	}
}
