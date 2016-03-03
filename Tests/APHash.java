package Tests;

import java.util.ArrayList;
import java.util.List;

public class APHash implements HashTest {

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return "APH";
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
		int i;
	    int hashValue = 0;
	    
	    for(i = 0; i < word.length(); i++) {
	        int curr = word.charAt(i);
	        if ((i & 1) == 0)
	        {
	        	hashValue ^= ((hashValue << 7) ^ curr ^ (hashValue >> 3));
	        }
	        else
	        {
	        	hashValue ^= (~((hashValue << 11) ^ curr ^ (hashValue >> 5)));
	        }
	      }

	    
	    return Math.abs(hashValue) % mod;
	}

}
