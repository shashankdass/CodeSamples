package com.shashank.pos;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;

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

			File ndFile = new File("Files/test"); // File to read
																// from.
			File tFile = new File("Files/test_tagged"); // File to
																	// write to
			//File ntFile = new File("Files/hotel_truthful"); // File to read from
			int isDeceptive = 1;
			String sample = "This is a sample text";
			HashMap<Integer, Integer> totCountForDeceptive = new HashMap<Integer, Integer>();
			HashMap<Integer, Integer> totCountForTruthful = new HashMap<Integer, Integer>();
			findTotalPos(ndFile, tagger, totCountForDeceptive);
			//findTotalPos(ntFile, tagger, totCountForTruthful);
			makeTaggedFile(ndFile, tFile, tagger, isDeceptive, totCountForDeceptive);
			// The tagged string
			isDeceptive = 0;
			//makeTaggedFile(ntFile, tFile, tagger, isDeceptive, totCountForTruthful);

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

	private static void findTotalPos(File ndFile, MaxentTagger tagger, HashMap<Integer, Integer> totCount) {
		BufferedReader reader;
		try {
			reader = new BufferedReader(new FileReader(ndFile));

			String line = null;
			String regexp = "[\\s,;]+";

			while ((line = reader.readLine()) != null) {
				HashMap<Integer, Integer> countPos = new HashMap<Integer, Integer>();

				String[] tokens = line.split(regexp);
				for (int i = 0; i < tokens.length; i++) {
					String posModelWord = tagger.tagString(tokens[i]);
					String pos = posModelWord.substring(posModelWord.indexOf("/") + 1);
					Integer posNumber = TagSet.tagSet.get(pos.trim());
					int count = countPos.containsKey(posNumber) ? countPos.get(posNumber) : 0;
					countPos.put(posNumber, count + 1);
				}
				Iterator it = countPos.entrySet().iterator();
				while (it.hasNext()) {
					Map.Entry pairs = (Map.Entry) it.next();
					if (pairs.getKey() != null) {
						int count = totCount.containsKey(pairs.getKey()) ? totCount.get(pairs.getKey()) : 0;
						totCount.put((Integer) pairs.getKey(), count + (Integer) pairs.getValue());
					}
				}
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private static void makeTaggedFile(File nFile, File tFile, MaxentTagger tagger, int isDeceptive, HashMap<Integer, Integer> totCount2) {
		try {
			BufferedReader reader = new BufferedReader(new FileReader(nFile));
			BufferedWriter writer = new BufferedWriter(new FileWriter(tFile, true));
			
			String line = null;
			String regexp = "[\\s,;]+";
			while ((line = reader.readLine()) != null) {
				TreeMap<Integer, Integer> countPos = new TreeMap<Integer, Integer>();
				if (isDeceptive == 1)
					writer.write("0 ");
				else
					writer.write("0 ");
				String[] tokens = line.split(regexp);
				for (int i = 0; i < tokens.length; i++) {
					
					String posModelWord = tagger.tagString(tokens[i]);
					String pos = posModelWord.substring(posModelWord.indexOf("/") + 1);
					Integer posNumber = TagSet.tagSet.get(pos.trim());
					if(posNumber != null) {
					int count = countPos.containsKey(posNumber) ? countPos.get(posNumber) : 0;
					countPos.put(posNumber, count + 1);
					}
				}
				
				Iterator it = countPos.entrySet().iterator();
				while (it.hasNext()) {
					Map.Entry pairs = (Map.Entry) it.next();
					if (pairs.getKey() != null) {
						if (totCount2.get(pairs.getKey()) != null) {
							int numOfPosInTotal = totCount2.get(pairs.getKey());
							writer.write(pairs.getKey() + ":" + ((Integer) pairs.getValue()).floatValue() / numOfPosInTotal + " ");
						}
					}

					it.remove(); // avoids a ConcurrentModificationException
				}
				// Write system dependent end of line.
				writer.newLine();
			}
			reader.close(); // Close to unlock.
			writer.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
