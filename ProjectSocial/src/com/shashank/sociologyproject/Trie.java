package com.shashank.sociologyproject;
import java.util.ArrayList;
import java.util.StringTokenizer;

/**
 * Trie.java (Philip Dorin 2012-03-14)
 * 
 * An object of this class represents a trie (reTRIEval tree).
 * 
 * A trie is an efficient data structure for capturing a multiset of words. In
 * addition, an optional (int) attribute may be associated with each word in the
 * trie; see methods setAttribute() and getAttribute(). The use of this
 * attribute is strictly at the discretion of the client; no representations are
 * made about initial values, etc. Any attempt to store a string with an illegal
 * character will result in an exception; method legalChars() returns the
 * complete set of legal characters in an array, while inSetOfLegalChars() can
 * be used to determine if a given character is legal.
 */

public class Trie {

	/************************/
	/* Private constants */
	/************************/

	private static final char[] LEGAL_CHARS = new char[] { ' ', '\t', '_', 'A',
			'a', 'á', 'B', 'b', 'C', 'c', 'D', 'd', 'E', 'e', 'é', 'F', 'f',
			'G', 'g', 'H', 'h', 'I', 'i', 'J', 'j', 'K', 'k', 'L', 'l', 'M',
			'm', 'N', 'n', 'O', 'o', 'P', 'p', 'Q', 'q', 'R', 'r', 'S', 's',
			'T', 't', 'U', 'u', 'V', 'v', 'W', 'w', 'X', 'x', 'Y', 'y', 'Z',
			'z', 'í', 'ó', 'ú', 'ñ', '0', '1', '2', '3', '4', '5', '6', '7',
			'8', '9', '~', '?', '!', '+', '-', '*', '/', '=', '(', ')', '{',
			'}', '[', ']', '<', '>', '@', '#', '$', '%', '^', '&', ',', '.',
			':', ';', '|', '\\', '`', '\'', '\"' };

	private static final int NUMBER_OF_LEGAL_CHARS = LEGAL_CHARS.length;

	/****************************/
	/* Private classwide data */
	/****************************/

	private static String s = ""; // global variable used ONLY by method
									// output()

	/****************************/
	/* Private instance data */
	/****************************/

	private int count; // frequency associated with word terminating on this
						// node
	private Trie[] edges; // array of edges, one for each legal character
	private int attribute; // optional settable/gettable integer associated with
							// the
							// word terminating on this node

	/********************************/
	/* Private classwide methods */
	/********************************/

	/*
	 * Within each node of the Trie, each legal character, ch, corresponds to
	 * one of the edges[i]. This method returns the index, i, of the edge that
	 * corresponds to ch.
	 */
	private static int toIndex(char ch) {
		for (int i = 0; i < NUMBER_OF_LEGAL_CHARS; i++)
			if (LEGAL_CHARS[i] == ch)
				return i;
		//System.out.println("ch----" + ch);
		throw new IllegalArgumentException();
	}

	/*
	 * This method returns the character, ch, that corresponds to the index, i,
	 * in the corresponding edges[i] within each node in the Trie.
	 */
	private static char toChar(int i) {
		if ((i >= 0) && (i < NUMBER_OF_LEGAL_CHARS))
			return LEGAL_CHARS[i];
		else
			throw new IllegalArgumentException();
	}

	/********************************/
	/* Public constructor */
	/********************************/

	/**
	 * Constructs an initially empty trie.
	 */
	public Trie() {
		// Note that the attribute is not set in the constructor; it is
		// only affected by the getAttribute() and setAttribute() methods.
		count = 0;
		edges = new Trie[NUMBER_OF_LEGAL_CHARS];
		for (int i = 0; i < NUMBER_OF_LEGAL_CHARS; i++) {
			edges[i] = null;
		}
	}

	/********************************/
	/* Public instance methods */
	/********************************/

	/**
	 * This method stores a word in the trie, or, if it is already present,
	 * increases its frequency by one. Null words are disallowed.
	 */
	// public void insert ( MapWordNumber mwn ) {
	// String s = mwn.word;
	public void insert(String s, int number) {
		// String s = mwn.word;
		switch (s.length()) {

		case 0:
			throw new IllegalArgumentException();

		case 1: {

			int i = toIndex(s.charAt(0));
			if (edges[i] == null) {
				Trie t = new Trie();
				t.count = 1;
				edges[i] = t;
			} else {
				edges[i].count++;
			}
		}
			break;

		default: {
			int i = toIndex(s.charAt(0));
			if (edges[i] == null) {
				Trie t = new Trie();
				edges[i] = t;
				t.insert(s.substring(1), number);
			} else {
				edges[i].insert(s.substring(1), number);
			}
		}
			break;
		}
	}

	/**
	 * This method gets a word's optional attribute. If the word is not already
	 * in the trie, an exception is thrown.
	 */
	public int getAttribute(String s) {
		if (frequency(s) == 0)
			//throw new IllegalArgumentException();
			return 0;
		else if (s.length() == 0)
			//throw new IllegalArgumentException();
			return 0;
		else if (s.length() == 1) {
			int i = toIndex(s.charAt(0));
			if (edges[i] == null)
				return 0;
			else
				return edges[i].attribute;
		}

		else {
			int i = toIndex(s.charAt(0));
			if (edges[i] == null)
				return 0;
			else
				return edges[i].getAttribute(s.substring(1));
		}
	}

	/**
	 * This method sets a word's optional attribute. If the word is not already
	 * in the trie, an exception is thrown.
	 */
	public void setAttribute(String s, int attr) {
		if (frequency(s) == 0)
			throw new IllegalArgumentException();
		else if (s.length() == 0)
			throw new IllegalArgumentException();
		else if (s.length() == 1) {
			int i = toIndex(s.charAt(0));
			if (edges[i] == null)
				return;
			else
				edges[i].attribute = attr;
		}

		else {
			int i = toIndex(s.charAt(0));
			if (edges[i] == null)
				return;
			else
				edges[i].setAttribute(s.substring(1), attr);
		}
	}

	/**
	 * Returns the frequency associated with a given word in the trie.
	 */
	public int frequency(String s) {

		switch (s.length()) {

		case 0:
			throw new IllegalArgumentException();

		case 1: {
			int i = toIndex(s.charAt(0));
			if (edges[i] == null)
				return 0;
			else
				return edges[i].count;
		}

		default: {
			int i = toIndex(s.charAt(0));
			if (edges[i] == null)
				return 0;
			else
				return edges[i].frequency(s.substring(1));
		}
		}
	}

	/**
	 * Sends to standard output a sorted list of the words contained in the
	 * trie, along with their attributes. It accomplishes this via a recursive,
	 * depth-first traversal of the trie. The ordering is prescribed by the
	 * order in which characters appear in the set returned by the static method
	 * legalChars().
	 */
	public void toStandardOutput() {

		for (int i = 0; i < NUMBER_OF_LEGAL_CHARS; i++) {
			if (edges[i] != null) { // for definition of s, see private
									// classwide data
				s = s + String.valueOf(toChar(i));

				if (edges[i].count > 0) {
					//System.out.println(s + " \t " + edges[i].attribute);
				}

				edges[i].toStandardOutput();

				if (s.length() > 1) {
					s = s.substring(0, s.length() - 1);
				} else {
					s = "";
				}
			}
		}
	}

	/********************************/
	/* Public classwide methods */
	/********************************/

	/**
	 * Returns an array consisting of the characters that are allowed to appear
	 * in words in a trie.
	 */
	public static char[] legalChars() {
		return LEGAL_CHARS;
	}

	/**
	 * Returns true iff the given character is among the legal ones, i.e., those
	 * that are allowed to appear in strings in the trie.
	 */
	public static boolean inSetOfLegalChars(char ch) {
		for (int i = 0; i < NUMBER_OF_LEGAL_CHARS; i++) {
			if (LEGAL_CHARS[i] == ch)
				return true;
		}
		return false;
	}

	public int getNumber(String eventDescription) {
		// TODO Auto-generated method stub
		StringTokenizer st2 = new StringTokenizer(eventDescription, ",");
		ArrayList<String> substringArray = new ArrayList<String>();

		int number = 0;
		while (st2.hasMoreElements()) {
			try {
				String val = st2.nextElement().toString();
				substringArray = findSubstring(val);
				
				for (int i=0 ; i<substringArray.size() ;i++) {
					if (number == 0) {
						number = getAttribute(substringArray.get(i));
					}
				}
			} catch (IllegalArgumentException ill) {
				continue;
			}
		}
		//System.out.println("Final number====" + number);
		return number;
	}

	private ArrayList<String> findSubstring(String val) {
		// TODO Auto-generated method stub
		ArrayList<String> substringArray = new ArrayList<String>();
		substringArray.add(val);
		String newVal = val;
		String newVal1 = newVal;
		int count = 0;
		int limit = val.length();
		for (int i = 0; i < limit; ++i) {
			if (Character.isWhitespace(val.charAt(i))) {
				++count;
			}
		}
		for (int i = 0; i < count; i++) {
			newVal = newVal.substring(0,newVal.lastIndexOf(" "));
			newVal1 = newVal1.substring(newVal.lastIndexOf(" "));
			substringArray.add(newVal);
			substringArray.add(newVal1);
		}

		return substringArray;
	}

}
