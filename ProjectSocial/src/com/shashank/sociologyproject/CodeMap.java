package com.shashank.sociologyproject;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.HashMap;


public class CodeMap {
	public HashMap<Integer, Trie> Codes= new HashMap<Integer, Trie>();
	public void createCodesMap() {
		// TODO Auto-generated method stub
		try {
			//FileInputStream fstream1 = new FileInputStream(filesInDir[i]);
			FileInputStream fstream1 = new FileInputStream("C:\\Users\\Shashank\\Downloads\\EventAction.doc");

			// Get the object of DataInputStream
			DataInputStream in1 = new DataInputStream(fstream1);
			BufferedReader br1 = new BufferedReader(new InputStreamReader(in1));
	}catch (Exception e) {
		// TODO: handle exception
		e.printStackTrace();
	}

	}

}
