package hadoop.ngram.demo;

public class Parser {
    
    public String[] ngrams(long n, String str){
    	char[] chars = str.toCharArray();
    	String[] grams = new String[(int) (chars.length - n + 1)];
    	for (int i = 0; i < grams.length; i++) {
			for (int j = 0; j < n; j++) {
				grams[i] = new String(chars, i, (int) n);
			}
		}
    	return grams;
    }
    

}
