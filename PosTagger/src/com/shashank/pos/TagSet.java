package com.shashank.pos;

import java.util.HashMap;

public class TagSet {
	static final HashMap<String, Integer> tagSet = new HashMap<String, Integer>();

	public static void initialize() {
		tagSet.put("CC", 1);
		tagSet.put("CD", 2);
		tagSet.put("DT", 3);
		tagSet.put("EX", 4);
		tagSet.put("FW", 5);
		tagSet.put("IN", 6);
		tagSet.put("JJ", 7);
		tagSet.put("JJR", 8);
		tagSet.put("JJS", 9);
		tagSet.put("LS", 10);
		tagSet.put("MD", 11);
		tagSet.put("NN", 12);
		tagSet.put("NNP", 13);
		tagSet.put("NNPS", 14);
		tagSet.put("NNS", 15);
		tagSet.put("PDT", 16);
		tagSet.put("POS", 17);
		tagSet.put("PRP", 18);
		tagSet.put("PRP$", 19);
		tagSet.put("RB", 20);
		tagSet.put("RBR", 21);
		tagSet.put("RBS", 22);
		tagSet.put("RP", 23);
		tagSet.put("SYM", 24);
		tagSet.put("TO", 25);
		tagSet.put("UH", 26);
		tagSet.put("VB", 27);
		tagSet.put("VBD", 28);
		tagSet.put("VBG", 29);
		tagSet.put("VBN", 30);
		tagSet.put("VBP", 31);
		tagSet.put("VBZ", 32);
		tagSet.put("WDT", 33);
		tagSet.put("WP", 34);
		tagSet.put("WP$", 35);
		tagSet.put("WRB", 36);
	}
}
