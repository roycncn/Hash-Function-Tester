package Tests;
import java.util.List;

public interface HashTest {
  /**
   * Gets the qualified name of the hash
   * @return The name of the hash
   */
  public String getName();
  
  /**
   * Gets the list of hashes for the given word list
   * @param words The list of words
   * @return The list of hashes for the given word list
   */
  public List<Integer> getHashes(List<String> words);
  
  /**
   * Hashes a given word
   * @param word The word to hash
   * @param mod The number to mod the hash with
   * @return The hash integer
   */
  public Integer getHash(String word, int mod);
}
