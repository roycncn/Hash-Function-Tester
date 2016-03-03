package Tests;
import java.util.ArrayList;
import java.util.List;

public class PreissHash implements HashTest{

  @Override
  public String getName() {
    return "Preiss";
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
    int hashValue = 0;
    int preissHashMask = 0 << (128 - 6);
    
    for(int i = 0; i < word.length(); i++) {
      int curr = word.charAt(i);
      hashValue = (hashValue & preissHashMask) ^ (hashValue << 6) ^ curr;  
    }
    
    return Math.abs(hashValue) % mod;
  }
}
