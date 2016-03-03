package Tests;
import java.util.ArrayList;
import java.util.List;

public class UnsignedCSCHash implements HashTest{

  @Override
  public String getName() {
    return "UnsignedCSC";
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
    
    for(int i = 0; i < word.length(); i++) {
      int curr = word.charAt(i);
      int leftShiftedValue = hashValue << 5;
      int rightShiftedValue = hashValue >>> 27;
      
      hashValue = (leftShiftedValue | rightShiftedValue) ^ curr; 
    }
    
    return Math.abs(hashValue) % mod;
  }
}
