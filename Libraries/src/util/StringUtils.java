package util;

public class StringUtils {

	static boolean isPalindrome(String s) {
		for (int i=0;i<s.length()/2;i++) {
			if (s.charAt(i) != s.charAt(s.length()-1-i)) {
				return false;
			}
		}
		return true;
	}

	static boolean isPalindrome(int n) {
		String s = String.valueOf(n);
		for (int i=0;i<s.length()/2;i++) {
			if (s.charAt(i) != s.charAt(s.length()-1-i)) {
				return false;
			}
		}
		return true;
	}

	// get "abc" from "abcabcabc"
	static String getRepeatElement(String s) {
		String mini=s;
		for (int i=1;i<s.length();i++) {
			if (s.length()%i!=0) {
				continue;
			}
			String test = s.substring(0, i);
			boolean bOK=true;
			for (int j=0;j<s.length()/i;j++) {
				if (!test.equals(s.substring(j*i, (j+1)*i))) {
					bOK=false;break;
				}
			}
			if (bOK){
				mini=test;
				break;
			}
		}
		return mini;
	}
	
}
