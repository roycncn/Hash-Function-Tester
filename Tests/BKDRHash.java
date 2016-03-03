package Tests;

import java.util.ArrayList;
import java.util.List;

public class BKDRHash implements HashTest {

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return "BKDR";
	}

	  @Override
	  public List<Integer> getHashes(List<String> words) {
	    List<Integer> results = new ArrayList<Integer>(words.size());
	    for(String word : words) {
	      results.add(getHash(word, words.size()));
	    }
	    return results;
	  }

	@Override
	public Integer getHash(String word, int mod) {
	    int seed = 131; // 31 131 1313 13131 131313 etc..
	    int hashValue = 0;
	    
	    for(int i = 0; i < word.length(); i++) {
	        int curr = word.charAt(i);
	        hashValue = hashValue*seed + curr;  
	      }

	    
	    return (hashValue & 0x7FFFFFFF ) % mod;
	}

}
