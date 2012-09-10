package com.shashank.pos;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import edu.stanford.nlp.tagger.maxent.MaxentTagger;

public class POSTagger {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
			MaxentTagger tagger = new MaxentTagger("tagger/left3words-wsj-0-18.tagger");
			// Take the file to be tagged.
			
			TagSet.initialize();
			
			
			 File ndFile  = new File("Files/hotel_deceptive");  // File to read from.
		     File tFile = new File("Files/hotel_deceptive_tagged");  // File to write to
		     File ntFile  = new File("Files/hotel_truthful");  // File to read from
		     int isDeceptive = 1;
			String sample = "This is a sample text";
			makeTaggedFile(ndFile, tFile, tagger, isDeceptive);
			// The tagged string
			isDeceptive = 0;
			makeTaggedFile(ntFile, tFile, tagger, isDeceptive);
			 
			String tagged = tagger.tagString(sample);
			 // Create a tagged file
			
			// Calculate the values for each POS
			// Make a input file for SVM lite.
			// Run SVM lite.
			System.out.println(tagged);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	private static void makeTaggedFile(File nFile, File tFile, MaxentTagger tagger, int isDeceptive) {
		 	try {
				BufferedReader reader = new BufferedReader(new FileReader(nFile));
				BufferedWriter writer = new BufferedWriter(new FileWriter(tFile,true));
				String line = null;
				String regexp = "[\\s,;]+";
				
		        while ((line=reader.readLine()) != null) {
		        	HashMap<Integer,Integer> countPos = new HashMap<Integer,Integer>();
		        	if (isDeceptive == 1)
		        	writer.write("-1 ");
		        	else 
		        	writer.write("+1 ");
		        	String[] tokens = line.split(regexp);
		        	for (int i=0 ; i<tokens.length ;i++) {
		        		String posModelWord = tagger.tagString(tokens[i]);
		        		String pos = posModelWord.substring(posModelWord.indexOf("/")+1);
		        		Integer posNumber = TagSet.tagSet.get(pos.trim());
		        		int count = countPos.containsKey(posNumber) ? countPos.get(posNumber) : 0;
		        		countPos.put(posNumber, count + 1);
		        	}
		        	Iterator it = countPos.entrySet().iterator();
		            while (it.hasNext()) {
		                Map.Entry pairs = (Map.Entry)it.next();
		                if(pairs.getKey()!=null)
		                writer.write(pairs.getKey()+":"+ pairs.getValue() +" "); 
		                it.remove(); // avoids a ConcurrentModificationException
		            }
		        	 // Write system dependent end of line.
		            writer.newLine(); 
		        }
		        reader.close();  // Close to unlock.
		        writer.close();
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		
	}

}
