package com.shashank.sociologyproject;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

import org.docx4j.model.listnumbering.Emulator;
import org.docx4j.model.listnumbering.Emulator.ResultTriple;
import org.docx4j.openpackaging.exceptions.Docx4JException;
import org.docx4j.openpackaging.packages.WordprocessingMLPackage;
import org.docx4j.openpackaging.parts.WordprocessingML.NumberingDefinitionsPart;
import org.docx4j.wml.P;



public class TrieMap {

	public HashMap<Integer, ArrayList<String>> createTrie(String string) {
		// TODO Auto-generated method stub
		String inputfilepath = string;
		Trie t = null;
		HashMap<Integer, ArrayList<String>> hmap = new HashMap<Integer, ArrayList<String>>();
		
		// String inputfilepath = "C:\\Users\\Shashank\\Desktop\\test.docx";
		WordprocessingMLPackage wordMLPackage = null;
		ArrayList NumberString = new ArrayList();
		try {
			wordMLPackage = WordprocessingMLPackage.load(new java.io.File(
					inputfilepath));
		} catch (Docx4JException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		NumberingDefinitionsPart ndp = wordMLPackage.getMainDocumentPart()
				.getNumberingDefinitionsPart();
		// Force initialisation of maps
		ndp.getEmulator();
		int i = 0;
		for (Object o : wordMLPackage.getMainDocumentPart().getContent()) {

			if (o instanceof P) {

				P p = (P) o;

				if (p.getPPr() != null) {

					// Set up values required for call to Emulator's getNumber
					String pStyleVal = null;
					if (p.getPPr().getPStyle() != null) {
						pStyleVal = p.getPPr().getPStyle().getVal();
					}

					String numId = null;
					String levelId = null;
					if (p.getPPr().getNumPr() != null) {
						if (p.getPPr().getNumPr().getNumId() != null) {
							numId = p.getPPr().getNumPr().getNumId().getVal()
									.toString();
						}
						if (p.getPPr().getNumPr().getIlvl() != null) {
							levelId = p.getPPr().getNumPr().getIlvl().getVal()
									.toString();
						}
					}

					ResultTriple rt = Emulator.getNumber(wordMLPackage,
							pStyleVal, numId, levelId);
					if (rt != null) {
						i++;
						MapWordNumber mwn = new MapWordNumber(wordMLPackage
								.getMainDocumentPart().getContent().get(i)
								.toString(), Integer.parseInt(rt.getNumString()
								.substring(0, rt.getNumString().indexOf('.'))));
						NumberString.add(mwn);

						//System.out.println(rt.getBullet() + "");
					}
					// Text text = (Text)XmlUtils.unwrap(
					// ((R)p.getContent().get(0)).getContent().get(0));
					// String content = text.getValue();
					// //System.out.println(content);
				}
				
			}

			// org.docx4j.wml.PPr ppr =
			// ((org.docx4j.wml.P)wordMLPackage.getMainDocumentPart().getContent()).getPPr();
			// // get paragraph propertires
			// org.docx4j.wml.PPrBase.NumPr numPr = ppr.getNumPr();
			// if( numPr != null) { // This paragraph is numbered
			// //System.out.println("This paragraph has numbering level: " +
			// numPr.getIlvl().getVal());

		}
		
		for(Object mwn : NumberString)
		{
			String[] values = ((MapWordNumber)mwn).word.split("[, ]");
			ArrayList<String> tmplist = new ArrayList<String>();
			for (int j = 0; j < values.length; j++) {
				tmplist.add(values[j]);
			}
			hmap.put(((MapWordNumber)mwn).number, tmplist);
		}
//			
//		t = new Trie();
//		for(Object mwn : NumberString)
//		{
//			String[] tmp = ((MapWordNumber)mwn).word.split(",");
//			for (int j = 0; j < tmp.length; j++) {
//				t.insert(tmp[j],((MapWordNumber)mwn).number);//insertInTrie(NumberString);
//				t.setAttribute(tmp[j],((MapWordNumber)mwn).number );
//				//System.out.println("sdebug " +tmp[j] + " " + ((MapWordNumber)mwn).number );
//			}
//			
//		
//		}
		return hmap;

	}
	
}
