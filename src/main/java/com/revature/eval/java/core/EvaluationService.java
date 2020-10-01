package com.revature.eval.java.core;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.Temporal;
import java.time.temporal.TemporalAccessor;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class EvaluationService {

	/**
	 * 1. Without using the StringBuilder or StringBuffer class, write a method that
	 * reverses a String. Example: reverse("example"); -> "elpmaxe"
	 * 
	 * @param string
	 * @return
	 */
	public String reverse(String string) {
		char[] reversed = new char[string.length()];
		for (int i = reversed.length - 1, j=0; i >= 0; i--, j++) {
			reversed[j] = string.charAt(i);
		}
		return new String(reversed);
	}

	/**
	 * 2. Convert a phrase to its acronym. Techies love their TLA (Three Letter
	 * Acronyms)! Help generate some jargon by writing a program that converts a
	 * long name like Portable Network Graphics to its acronym (PNG).
	 * 
	 * @param phrase
	 * @return
	 */
	public String acronym(String phrase) {
		phrase = " " + phrase;
		ArrayList<Character> acronym = new ArrayList<Character>();
		char[] chars = phrase.toCharArray();
		for (int i = 0; i < chars.length; i++) {
			if ((chars[i] >= 'a' && chars[i] <= 'z') || (chars[i] >= 'A' && chars[i] <= 'Z')) {
				if (!((chars[i - 1] >= 'a' && chars[i - 1] <= 'z') || (chars[i - 1] >= 'A' && chars[i - 1] <= 'Z'))) {
					acronym.add(chars[i]);
				}
			}
		}
		String answer = "";
		for (char ch : acronym) {
			answer += Character.toUpperCase(ch);
		}
		return answer;
	}

	/**
	 * 3. Determine if a triangle is equilateral, isosceles, or scalene. An
	 * equilateral triangle has all three sides the same length. An isosceles
	 * triangle has at least two sides the same length. (It is sometimes specified
	 * as having exactly two sides the same length, but for the purposes of this
	 * exercise we'll say at least two.) A scalene triangle has all sides of
	 * different lengths.
	 *
	 */
	static class Triangle {
		private double sideOne;
		private double sideTwo;
		private double sideThree;

		public Triangle() {
			super();
		}

		public Triangle(double sideOne, double sideTwo, double sideThree) {
			this();
			this.sideOne = sideOne;
			this.sideTwo = sideTwo;
			this.sideThree = sideThree;
		}

		public double getSideOne() {
			return sideOne;
		}

		public void setSideOne(double sideOne) {
			this.sideOne = sideOne;
		}

		public double getSideTwo() {
			return sideTwo;
		}

		public void setSideTwo(double sideTwo) {
			this.sideTwo = sideTwo;
		}

		public double getSideThree() {
			return sideThree;
		}

		public void setSideThree(double sideThree) {
			this.sideThree = sideThree;
		}

		public boolean isEquilateral() {
			if (this.sideOne == this.sideTwo) {
				if (this.sideTwo == this.sideThree) {
					return true;
				}
			}
			return false;
		}

		public boolean isIsosceles() {
			if (this.sideOne == this.sideTwo) {
				return true;
			}
			if (this.sideTwo == this.sideThree) {
				return true;
			}
			if (this.sideThree == this.sideOne) {
				return true;
			}
			return false;
		}

		public boolean isScalene() {
			if (this.sideOne != this.sideTwo) {
				if (this.sideTwo != this.sideThree) {
					if (this.sideOne != this.sideThree) {
						return true;
					}
				}
			}
			return false;
		}

	}

	/**
	 * 4. Given a word, compute the scrabble score for that word.
	 * 
	 * --Letter Values-- Letter Value A, E, I, O, U, L, N, R, S, T = 1; D, G = 2; B,
	 * C, M, P = 3; F, H, V, W, Y = 4; K = 5; J, X = 8; Q, Z = 10; Examples
	 * "cabbage" should be scored as worth 14 points:
	 * 
	 * 3 points for C, 1 point for A, twice 3 points for B, twice 2 points for G, 1
	 * point for E And to total:
	 * 
	 * 3 + 2*1 + 2*3 + 2 + 1 = 3 + 2 + 6 + 3 = 5 + 9 = 14
	 * 
	 * @param string
	 * @return
	 */
	public int getScrabbleScore(String string) {
		Map<Character, Integer> values = new HashMap<Character, Integer>();
		values.put('a', 1);
		values.put('e', 1);
		values.put('i', 1);
		values.put('o', 1);
		values.put('u', 1);
		values.put('l', 1);
		values.put('n', 1);
		values.put('r', 1);
		values.put('s', 1);
		values.put('t', 1);
		values.put('d', 2);
		values.put('g', 2);
		values.put('b', 3);
		values.put('c', 3);
		values.put('m', 3);
		values.put('p', 3);
		values.put('f', 4);
		values.put('h', 4);
		values.put('v', 4);
		values.put('w', 4);
		values.put('y', 4);
		values.put('k', 5);
		values.put('j', 8);
		values.put('x', 8);
		values.put('q', 10);
		values.put('z', 10);
		int score = 0;
		for (int i = 0; i < string.length(); i++) {
			score += values.get(Character.toLowerCase(string.charAt(i)));
		}
		return score;
	}

	/**
	 * 5. Clean up user-entered phone numbers so that they can be sent SMS messages.
	 * 
	 * The North American Numbering Plan (NANP) is a telephone numbering system used
	 * by many countries in North America like the United States, Canada or Bermuda.
	 * All NANP-countries share the same international country code: 1.
	 * 
	 * NANP numbers are ten-digit numbers consisting of a three-digit Numbering Plan
	 * Area code, commonly known as area code, followed by a seven-digit local
	 * number. The first three digits of the local number represent the exchange
	 * code, followed by the unique four-digit number which is the subscriber
	 * number.
	 * 
	 * The format is usually represented as
	 * 
	 * 1 (NXX)-NXX-XXXX where N is any digit from 2 through 9 and X is any digit
	 * from 0 through 9.
	 * 
	 * Your task is to clean up differently formatted telephone numbers by removing
	 * punctuation and the country code (1) if present.
	 * 
	 * For example, the inputs
	 * 
	 * +1 (613)-995-0253 613-995-0253 1 613 995 0253 613.995.0253 should all produce
	 * the output
	 * 
	 * 6139950253
	 * 
	 * Note: As this exercise only deals with telephone numbers used in
	 * NANP-countries, only 1 is considered a valid country code.
	 */
	public String cleanPhoneNumber(String string) {
		ArrayList<Character> phoneNumber = new ArrayList<Character>();
		for (int i = 0; i < string.length(); i++) {
			if (string.charAt(i) >= '0' && string.charAt(i) <= '9') {
				phoneNumber.add(string.charAt(i));
			}
		}
		if (phoneNumber.size() == 11) {
			phoneNumber.remove(0);
		} else if (phoneNumber.size() > 11) {
			throw new IllegalArgumentException("Invalid Phone Number");
		} else if (phoneNumber.size() < 10) {
			throw new IllegalArgumentException("Invalid Phone Number");
		}
		String answer = "";
		for (char ch : phoneNumber) answer += ch;
		return answer;
	}

	/**
	 * 6. Given a phrase, count the occurrences of each word in that phrase.
	 * 
	 * For example for the input "olly olly in come free" olly: 2 in: 1 come: 1
	 * free: 1
	 * 
	 * @param string
	 * @return
	 */
	public Map<String, Integer> wordCount(String string) {
		String word = "";
		Map<String, Integer> count = new HashMap<String, Integer>();
		for (int i = 0; i < string.length(); i++) {
			if (string.charAt(i) >= 'a' && string.charAt(i) <= 'z') {
				word += string.charAt(i);
			} else {
				if (word.length() > 0) {
					if (count.containsKey(word)) {
						count.replace(word, count.get(word) + 1);
					} else {
						count.put(word, 1);
					}
					word = "";
				}
			}
		}
		if (word.length() > 0) {
			if (count.containsKey(word)) {
				count.replace(word, count.get(word) + 1);
			} else {
				count.put(word, 1);
			}
		}
		return count;
	}

	/**
	 * 7. Implement a binary search algorithm.
	 * 
	 * Searching a sorted collection is a common task. A dictionary is a sorted list
	 * of word definitions. Given a word, one can find its definition. A telephone
	 * book is a sorted list of people's names, addresses, and telephone numbers.
	 * Knowing someone's name allows one to quickly find their telephone number and
	 * address.
	 * 
	 * If the list to be searched contains more than a few items (a dozen, say) a
	 * binary search will require far fewer comparisons than a linear search, but it
	 * imposes the requirement that the list be sorted.
	 * 
	 * In computer science, a binary search or half-interval search algorithm finds
	 * the position of a specified input value (the search "key") within an array
	 * sorted by key value.
	 * 
	 * In each step, the algorithm compares the search key value with the key value
	 * of the middle element of the array.
	 * 
	 * If the keys match, then a matching element has been found and its index, or
	 * position, is returned.
	 * 
	 * Otherwise, if the search key is less than the middle element's key, then the
	 * algorithm repeats its action on the sub-array to the left of the middle
	 * element or, if the search key is greater, on the sub-array to the right.
	 * 
	 * If the remaining array to be searched is empty, then the key cannot be found
	 * in the array and a special "not found" indication is returned.
	 * 
	 * A binary search halves the number of items to check with each iteration, so
	 * locating an item (or determining its absence) takes logarithmic time. A
	 * binary search is a dichotomic divide and conquer search algorithm.
	 * 
	 */
	static class BinarySearch<T>{
		private List<T> sortedList;

		@SuppressWarnings("unchecked")
		public int indexOf(T t) {
			if (t instanceof Integer) {
				List<Integer> intList = (List<Integer>) this.sortedList;
				int value = (int) t;
				return intSearch(0, intList.size() - 1, intList, value);
			} else if (t instanceof String) {
				String value = (String) t;
				List<String> strList = (List<String>) this.sortedList;
				return strSearch(0, strList.size() - 1, strList, value);
			}
			return -1;
		}

		private int strSearch(int start, int end, List<String> strList, String value) {
			if (start - end == -1) {
				if (strList.get(end) == value) {
					return end;
				} else {
					return start;
				}
			}
			int index = (start + end) / 2;
			if (strList.get(index) == value) {
				return index;
			} else if (Integer.parseInt(strList.get(index)) > Integer.parseInt(value)) {
				return strSearch(start, index, strList, value);
			} else {
				return strSearch(index, end, strList, value);
			}
		}

		private int intSearch(int start, int end, List<Integer> intList, int value) {
			if (start - end == -1) {
				if (intList.get(end) == value) {
					return end;
				} else {
					return start;
				}
			}
			int index = (start + end) / 2;
			if (intList.get(index) == value) {
				return index;
			} else if (intList.get(index) > value) {
				return intSearch(start, index, intList, value);
			} else {
				return intSearch(index, end, intList, value);
			}
		}

		public BinarySearch(List<T> sortedList) {
			super();
			this.sortedList = sortedList;
		}

		public List<T> getSortedList() {
			return sortedList;
		}

		public void setSortedList(List<T> sortedList) {
			this.sortedList = sortedList;
		}

	}

	/**
	 * 8. Implement a program that translates from English to Pig Latin.
	 * 
	 * Pig Latin is a made-up children's language that's intended to be confusing.
	 * It obeys a few simple rules (below), but when it's spoken quickly it's really
	 * difficult for non-children (and non-native speakers) to understand.
	 * 
	 * Rule 1: If a word begins with a vowel sound, add an "ay" sound to the end of
	 * the word. Rule 2: If a word begins with a consonant sound, move it to the end
	 * of the word, and then add an "ay" sound to the end of the word. There are a
	 * few more rules for edge cases, and there are regional variants too.
	 * 
	 * See http://en.wikipedia.org/wiki/Pig_latin for more details.
	 * 
	 * @param string
	 * @return
	 */
	public String toPigLatin(String string) {
		char[] vowels = {'a', 'e', 'i', 'o', 'u'};
		List<String> words = new ArrayList<String>();
		String currentWord = "";
		for (int i = 0; i < string.length(); i++) {
			if (string.charAt(i) != ' ') {
				currentWord += string.charAt(i);
			} else {
				words.add(currentWord);
				currentWord = "";
			}
		}
		if (currentWord.length() > 0){
			words.add(currentWord);
		}
		ArrayList<String> solutionWords = new ArrayList<String>();
		boolean vowelWord = false;
		for (String word : words) {
			for (char vowel : vowels) {
				if (word.charAt(0) == vowel) {
					word = word + "ay";
					solutionWords.add(word);
					vowelWord = true;
					break;
				}
			}
			if (!vowelWord) {
				boolean specialQ = false;
				String currentNonVowelWord = word;
				boolean breakCondition = false;
				ArrayList<Character> consonants = new ArrayList<Character>();
				for (int i = 0; i < word.length(); i++) {
					for (int j = 0; j < vowels.length; j++) {
						if (word.charAt(i) == vowels[j]) {
							if (vowels[j] == 'u') {
								if (word.charAt(i-1) == 'q') {
									specialQ = true;
								}
							}
							breakCondition = true;
							break;
						}
					}
					if (!breakCondition) {
						consonants.add(word.charAt(i));
						currentNonVowelWord = currentNonVowelWord.substring(1);
					}
					if (breakCondition) break;
				}
				String leadingChunk = "";
				for (char ch : consonants) leadingChunk += ch;
				leadingChunk += "ay";
				currentNonVowelWord += leadingChunk;
				if (specialQ) {
					currentNonVowelWord = currentNonVowelWord.substring(1);
					currentNonVowelWord = currentNonVowelWord.substring(0, currentNonVowelWord.length() - 2);
					currentNonVowelWord += "uay";
				}
				solutionWords.add(currentNonVowelWord);
			}
		}
		String finalString = "";
		for (String word : solutionWords) {
			finalString += word + " ";
		}
		finalString = finalString.strip();
		return finalString;
	}

	/**
	 * 9. An Armstrong number is a number that is the sum of its own digits each
	 * raised to the power of the number of digits.
	 * 
	 * For example:
	 * 
	 * 9 is an Armstrong number, because 9 = 9^1 = 9 10 is not an Armstrong number,
	 * because 10 != 1^2 + 0^2 = 2 153 is an Armstrong number, because: 153 = 1^3 +
	 * 5^3 + 3^3 = 1 + 125 + 27 = 153 154 is not an Armstrong number, because: 154
	 * != 1^3 + 5^3 + 4^3 = 1 + 125 + 64 = 190 Write some code to determine whether
	 * a number is an Armstrong number.
	 * 
	 * @param input
	 * @return
	 */
	public boolean isArmstrongNumber(int input) {
		String strInput = String.valueOf(input);
		int inputLength = strInput.length();
		int sum = 0;
		for (int i = 0; i < strInput.length(); i++) {
			sum += Math.pow(Integer.parseInt(Character.toString(strInput.charAt(i))), inputLength);
		}
		return sum == input;
	}

	/**
	 * 10. Compute the prime factors of a given natural number.
	 * 
	 * A prime number is only evenly divisible by itself and 1.
	 * 
	 * Note that 1 is not a prime number.
	 * 
	 * @param l
	 * @return
	 */
	public List<Long> calculatePrimeFactorsOf(long l) {
		if (isPrime(l)) return Arrays.asList(l);
		List<Long> primeFactors = new ArrayList<Long>();
		long currentNum = l;
		for (int i = 2; i < l; i++) {
			if (currentNum % (long) i == 0) {
				if (isPrime((long) i)) {
					currentNum = currentNum / (long) i;
					primeFactors.add((long) i);
					i--;
					if (isPrime(currentNum)) {
						primeFactors.add(currentNum);
						break;
					}
				}
			}
		}
		return primeFactors;
	}
	
	public boolean isPrime(long l) {
		for (long i = 2; i < (l / 2) + 1; i++) {
			if (l % i == 0) return false;
		}
		return true;
	}

	/**
	 * 11. Create an implementation of the rotational cipher, also sometimes called
	 * the Caesar cipher.
	 * 
	 * The Caesar cipher is a simple shift cipher that relies on transposing all the
	 * letters in the alphabet using an integer key between 0 and 26. Using a key of
	 * 0 or 26 will always yield the same output due to modular arithmetic. The
	 * letter is shifted for as many values as the value of the key.
	 * 
	 * The general notation for rotational ciphers is ROT + <key>. The most commonly
	 * used rotational cipher is ROT13.
	 * 
	 * A ROT13 on the Latin alphabet would be as follows:
	 * 
	 * Plain: abcdefghijklmnopqrstuvwxyz Cipher: nopqrstuvwxyzabcdefghijklm It is
	 * stronger than the Atbash cipher because it has 27 possible keys, and 25
	 * usable keys.
	 * 
	 * Ciphertext is written out in the same formatting as the input including
	 * spaces and punctuation.
	 * 
	 * Examples: ROT5 omg gives trl ROT0 c gives c ROT26 Cool gives Cool ROT13 The
	 * quick brown fox jumps over the lazy dog. gives Gur dhvpx oebja sbk whzcf bire
	 * gur ynml qbt. ROT13 Gur dhvpx oebja sbk whzcf bire gur ynml qbt. gives The
	 * quick brown fox jumps over the lazy dog.
	 */
	static class RotationalCipher {
		private int key;
		private char[] alphabet = "abcdefghijklmnopqrstuvwxyz".toCharArray();
		private char[] shiftedAlphabet = new char[26];
		private char[] alphabetCapitals = "abcdefghijklmnopqrstuvwxyz".toUpperCase().toCharArray();
		private char[] shiftedAlphabetCapitals = new char[26];

		public RotationalCipher(int key) {
			super();
			this.key = key;
		}
		
		private void shiftAlphabet() {
			int index;
			for (int i = 0; i < 26; i++) {
				index = i - this.key;
				if (index < 0) index = 26 + index;
				this.shiftedAlphabet[index] = alphabet[i];
			}
			index = 0;
			for (int i = 0; i < 26; i++) {
				index = i - this.key;
				if (index < 0) index = 26 + index;
				this.shiftedAlphabetCapitals[index] = alphabetCapitals[i];
			}
		}

		public String rotate(String string) {
			this.shiftAlphabet();
			String newString = "";
			for (int i = 0; i < string.length(); i++) {
				if (string.charAt(i) >= 'a' && string.charAt(i) <= 'z') {
					newString += this.shiftedAlphabet[String.valueOf(this.alphabet).indexOf(string.charAt(i))];
				} else if (string.charAt(i) >= 'A' && string.charAt(i) <= 'Z') {
					newString += this.shiftedAlphabetCapitals[String.valueOf(this.alphabetCapitals).indexOf(string.charAt(i))];
				} else {
					newString += string.charAt(i);
				}
			}
			return newString;
		}

	}

	/**
	 * 12. Given a number n, determine what the nth prime is.
	 * 
	 * By listing the first six prime numbers: 2, 3, 5, 7, 11, and 13, we can see
	 * that the 6th prime is 13.
	 * 
	 * If your language provides methods in the standard library to deal with prime
	 * numbers, pretend they don't exist and implement them yourself.
	 * 
	 * @param i
	 * @return
	 */
	
	public int calculateNthPrime(int i) {
		if (i <= 0) throw new IllegalArgumentException("No Such Thing as 0th Prime (Or lower)");
		int quantityOfPrimes = 0;
		int index = 2;
		while (true) {
			if (isPrime2(index)) {
				quantityOfPrimes++;
				if (quantityOfPrimes == i) {
					return index;
				}
			}
			index++;
		}
	}
	
	public boolean isPrime2(int x) {
		for (int i = 2; i < (x / 2) + 1; i++) {
			if (x % i == 0) return false;
		}
		return true;
	}

	/**
	 * 13 & 14. Create an implementation of the atbash cipher, an ancient encryption
	 * system created in the Middle East.
	 * 
	 * The Atbash cipher is a simple substitution cipher that relies on transposing
	 * all the letters in the alphabet such that the resulting alphabet is
	 * backwards. The first letter is replaced with the last letter, the second with
	 * the second-last, and so on.
	 * 
	 * An Atbash cipher for the Latin alphabet would be as follows:
	 * 
	 * Plain: abcdefghijklmnopqrstuvwxyz Cipher: zyxwvutsrqponmlkjihgfedcba It is a
	 * very weak cipher because it only has one possible key, and it is a simple
	 * monoalphabetic substitution cipher. However, this may not have been an issue
	 * in the cipher's time.
	 * 
	 * Ciphertext is written out in groups of fixed length, the traditional group
	 * size being 5 letters, and punctuation is excluded. This is to make it harder
	 * to guess things based on word boundaries.
	 * 
	 * Examples Encoding test gives gvhg Decoding gvhg gives test Decoding gsvjf
	 * rxpyi ldmul cqfnk hlevi gsvoz abwlt gives thequickbrownfoxjumpsoverthelazydog
	 *
	 */
	static class AtbashCipher {
		
		static String alphabet = "abcdefghijklmnopqrstuvwxyz";
		static char[] reversedAlphabet = new StringBuilder(alphabet).reverse().toString().toCharArray();

		public static String encode(String string) {
			string = string.replaceAll("\\s", "");
			int everyFive = 0;
			String encodedString = "";
			for (int i = 0; i < string.length(); i++) {
				if (isLetter(string.charAt(i))) {
					if (isCapital(string.charAt(i))) {
						int charIndex = Character.toLowerCase(string.charAt(i)) - 'a';
						if (everyFive < 5) {
							encodedString += reversedAlphabet[charIndex];
							everyFive++;
						} else {
							encodedString += " ";
							encodedString += reversedAlphabet[charIndex];
							everyFive = 1;
						}
					} else {
						int charIndex = string.charAt(i) - 'a';
						if (everyFive < 5) {
							encodedString += reversedAlphabet[charIndex];
							everyFive++;
						} else {
							encodedString += " ";
							encodedString += reversedAlphabet[charIndex];
							everyFive = 1;
						}
					}
				} else if (isNumber(string.charAt(i))) {
					if (everyFive < 5) {
						encodedString += string.charAt(i);
						everyFive++;
					} else {
						encodedString += " ";
						encodedString += string.charAt(i);
						everyFive = 1;
					}
				}
			}
			encodedString = encodedString.trim();
			return encodedString;
		}
		
		private static boolean isNumber(char ch) {
			return ch >= '0' && ch <= '9';
		}
		
		private static boolean isLetter(char ch) {
			return isCapital(ch) || (ch >= 'a' && ch <= 'z');
		}
		
		private static boolean isCapital(char ch) {
			return ch >= 'A' && ch <= 'Z';
		}

		public static String decode(String string) {
			String decodedString = "";
			string = string.replaceAll("\\s", "");
			for (int i = 0; i < string.length(); i++) {
				for (int j = 0; j < reversedAlphabet.length; j++) {
					if (string.charAt(i) == reversedAlphabet[j]) {
						decodedString += alphabet.toCharArray()[j];
						break;
					} else if (isNumber(string.charAt(i))){
						decodedString += string.charAt(i);
						break;
					}
				}
				
			}
			return decodedString;
		}
	}

	/**
	 * 15. The ISBN-10 verification process is used to validate book identification
	 * numbers. These normally contain dashes and look like: 3-598-21508-8
	 * 
	 * ISBN The ISBN-10 format is 9 digits (0 to 9) plus one check character (either
	 * a digit or an X only). In the case the check character is an X, this
	 * represents the value '10'. These may be communicated with or without hyphens,
	 * and can be checked for their validity by the following formula:
	 * 
	 * (x1 * 10 + x2 * 9 + x3 * 8 + x4 * 7 + x5 * 6 + x6 * 5 + x7 * 4 + x8 * 3 + x9
	 * * 2 + x10 * 1) mod 11 == 0 If the result is 0, then it is a valid ISBN-10,
	 * otherwise it is invalid.
	 * 
	 * Example Let's take the ISBN-10 3-598-21508-8. We plug it in to the formula,
	 * and get:
	 * 
	 * (3 * 10 + 5 * 9 + 9 * 8 + 8 * 7 + 2 * 6 + 1 * 5 + 5 * 4 + 0 * 3 + 8 * 2 + 8 *
	 * 1) mod 11 == 0 Since the result is 0, this proves that our ISBN is valid.
	 * 
	 * @param string
	 * @return
	 */
	public boolean isValidIsbn(String string) {
		try {
			int x1 = Integer.parseInt(Character.toString(string.charAt(0)));
			int x2 = Integer.parseInt(Character.toString(string.charAt(2)));
			int x3 = Integer.parseInt(Character.toString(string.charAt(3)));
			int x4 = Integer.parseInt(Character.toString(string.charAt(4)));
			int x5 = Integer.parseInt(Character.toString(string.charAt(6)));
			int x6 = Integer.parseInt(Character.toString(string.charAt(7)));
			int x7 = Integer.parseInt(Character.toString(string.charAt(8)));
			int x8 = Integer.parseInt(Character.toString(string.charAt(9)));
			int x9 = Integer.parseInt(Character.toString(string.charAt(10)));
			int x10;
			if (string.charAt(12) == 'X') {
				x10 = 10;
			} else if (string.charAt(12) >= '0' && string.charAt(12) <= '9') {
				x10 = Integer.parseInt(Character.toString(string.charAt(12)));
			} else {
				return false;
			}
			int[] xs = {x1, x2, x3, x4, x5, x6, x7, x8, x9, x10};
			return equation(xs);
		} catch (NumberFormatException e) {
			return false;
		}
	}
	
	private boolean equation(int[] xs) {
		int product = xs[0] * 10 + xs[1] * 9 + xs[2] * 8 + xs[3] * 7 + xs[4] * 6 + xs[5] * 5 + xs[6] * 4 + xs[7] * 3 + xs[8] * 2 + xs[9] * 1;
		return product % 11 == 0;
	}

	/**
	 * 16. Determine if a sentence is a pangram. A pangram (Greek: παν γράμμα, pan
	 * gramma, "every letter") is a sentence using every letter of the alphabet at
	 * least once. The best known English pangram is:
	 * 
	 * The quick brown fox jumps over the lazy dog.
	 * 
	 * The alphabet used consists of ASCII letters a to z, inclusive, and is case
	 * insensitive. Input will not contain non-ASCII symbols.
	 * 
	 * @param string
	 * @return
	 */
	public boolean isPangram(String string) {
		Map<Character, Integer> counter = new HashMap<>();
		char[] alphabet = "abcdefghijklmnopqrstuvwxyz".toCharArray();
		for (char letter : alphabet) {
			counter.put(letter, 0);
		}
		for (int i = 0; i < string.length(); i++) {
			char currentChar = Character.toLowerCase(string.charAt(i));
			if (currentChar >= 'a' && currentChar <= 'z') {
				counter.replace(string.charAt(i), counter.get(string.charAt(i)) + 1);
			}
		}
		for (char ch : counter.keySet()) {
			if (counter.get(ch) == 0) return false;
		}
		return true;
	}

	/**
	 * 17. Calculate the moment when someone has lived for 10^9 seconds.
	 * 
	 * A gigasecond is 10^9 (1,000,000,000) seconds.
	 * 
	 * @param given
	 * @return
	 */
	public Temporal getGigasecondDate(Temporal given) {
		if (given instanceof LocalDateTime) {
			return ((LocalDateTime) given).plusSeconds(1000000000);	
		} else {
			LocalDate ld = LocalDate.from(given);
			LocalDateTime dt = LocalDateTime.of(ld.getYear(), ld.getMonth(), ld.getDayOfMonth(), 0, 0, 0);
			dt = dt.plusSeconds(1000000000);
			return dt;
		}
	}

	/**
	 * 18. Given a number, find the sum of all the unique multiples of particular
	 * numbers up to but not including that number.
	 * 
	 * If we list all the natural numbers below 20 that are multiples of 3 or 5, we
	 * get 3, 5, 6, 9, 10, 12, 15, and 18.
	 * 
	 * The sum of these multiples is 78.
	 * 
	 * @param i
	 * @param set
	 * @return
	 */
	public int getSumOfMultiples(int i, int[] set) {
		List<ArrayList<Integer>> multiples = new ArrayList<>();
		int currentIndex = 0;
		for (int m : set) {
			multiples.add(new ArrayList<Integer>());
			for (int j = 0; j < i; j++) {
				if (j % m == 0) {
					multiples.get(currentIndex).add(j);
				}
			}
			currentIndex++;
		}
		List<Integer> allMultiples = new ArrayList<>();
		for (ArrayList<Integer> list : multiples) {
			for (int element : list) {
				allMultiples.add(element);
			}
		}
		Set<Integer> multipleSet = new HashSet<>();
		multipleSet.addAll(allMultiples);
		int sum = 0;
		for (int element : multipleSet) {
			sum += element;
		}
		return sum;
	}

	/**
	 * 19. Given a number determine whether or not it is valid per the Luhn formula.
	 * 
	 * The Luhn algorithm is a simple checksum formula used to validate a variety of
	 * identification numbers, such as credit card numbers and Canadian Social
	 * Insurance Numbers.
	 * 
	 * The task is to check if a given string is valid.
	 * 
	 * Validating a Number Strings of length 1 or less are not valid. Spaces are
	 * allowed in the input, but they should be stripped before checking. All other
	 * non-digit characters are disallowed.
	 * 
	 * Example 1: valid credit card number 1 4539 1488 0343 6467 The first step of
	 * the Luhn algorithm is to double every second digit, starting from the right.
	 * We will be doubling
	 * 
	 * 4_3_ 1_8_ 0_4_ 6_6_ If doubling the number results in a number greater than 9
	 * then subtract 9 from the product. The results of our doubling:
	 * 
	 * 8569 2478 0383 3437 Then sum all of the digits:
	 * 
	 * 8+5+6+9+2+4+7+8+0+3+8+3+3+4+3+7 = 80 If the sum is evenly divisible by 10,
	 * then the number is valid. This number is valid!
	 * 
	 * Example 2: invalid credit card number 1 8273 1232 7352 0569 Double the second
	 * digits, starting from the right
	 * 
	 * 7253 2262 5312 0539 Sum the digits
	 * 
	 * 7+2+5+3+2+2+6+2+5+3+1+2+0+5+3+9 = 57 57 is not evenly divisible by 10, so
	 * this number is not valid.
	 * 
	 * @param string
	 * @return
	 */
	public boolean isLuhnValid(String string) {
		String cleanedString = "";
		for(char ch : string.toCharArray()) {
			if (ch >= '0' && ch <= '9') {
				cleanedString += ch;
			} else if (ch == ' ') {
				continue;
			} else {
				return false;
			}
		}
		string = cleanedString;
		String numsToDouble = "";
		for (int i = string.length() - 2; i >= 0; i -= 2) {
			numsToDouble += string.charAt(i);
		}
		String newNums = "";
		for (int i = 0; i < numsToDouble.length(); i++) {
			int newNum = Integer.parseInt(Character.toString(numsToDouble.charAt(i))) * 2;
			if (newNum > 9) {
				newNum -= 9;
			}
			newNums += (char) (newNum + '0');
		}
		int sum = 0;
		for (int i = string.length() - 1; i >= 0; i -= 2) {
			sum += Integer.parseInt(Character.toString(string.charAt(i)));
		}
		for (char ch : newNums.toCharArray()) {
			sum += Integer.parseInt(Character.toString(ch));
		}
		if (sum % 10 == 0) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * 20. Parse and evaluate simple math word problems returning the answer as an
	 * integer.
	 * 
	 * Add two numbers together.
	 * 
	 * What is 5 plus 13?
	 * 
	 * 18
	 * 
	 * Now, perform the other three operations.
	 * 
	 * What is 7 minus 5?
	 * 
	 * 2
	 * 
	 * What is 6 multiplied by 4?
	 * 
	 * 24
	 * 
	 * What is 25 divided by 5?
	 * 
	 * 5
	 * 
	 * @param string
	 * @return
	 */
	public int solveWordProblem(String string) {
		String[] operations = {"plus", "minus", "multiplied", "divided"};
		String operationToPerform = "";
		for (String operation : operations) {
			if (string.contains(operation)) {
				operationToPerform = operation;
				break;
			}
		}
		List<String> words = new ArrayList<>();
		String currentWord = "";
		for (int i = 0; i < string.length() - 1; i++) {
			if (string.charAt(i) == ' ') {
				words.add(currentWord);
				currentWord = "";
			} else {
				currentWord += string.charAt(i);
			}
		}
		if (currentWord != "") words.add(currentWord);
		List<Integer> ints = new ArrayList<>();
		for (String word : words) {
			int possibleInt = 0;
			try {
				possibleInt = Integer.parseInt(word);
				ints.add(possibleInt);
			} catch (Exception e) {
				continue;
			}
		}
		int result = 0;
		switch (operationToPerform) {
		case "plus":
			result = ints.get(0) + ints.get(1);
			break;
		case "minus":
			result = ints.get(0) - ints.get(1);
			break;
		case "multiplied":
			result = ints.get(0) * ints.get(1);
			break;
		case "divided":
			result = ints.get(0) / ints.get(1);
			break;
		}
		return result;
	}

}
