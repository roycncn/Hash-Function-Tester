package Tests;
import java.util.ArrayList;
import java.util.List;

public class PJWHash implements HashTest{

  @Override
  public String getName() {
    return "PJW";
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
      hashValue = (hashValue << 4) + curr;
      
      long rotateBits = hashValue & 4026531840l;
      hashValue ^= rotateBits | (rotateBits >> 24);
    }
    return Math.abs(hashValue) % mod;
  }
}
