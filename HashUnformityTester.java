
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import Tests.APHash;
import Tests.BKDRHash;
import Tests.CSCHash;
import Tests.HashTest;
import Tests.PJWHash;
import Tests.PreissHash;
import Tests.UnsignedCSCHash;

public class HashUnformityTester implements Runnable {
  ArrayList<String> words = new ArrayList<String>();
  
  HashTest[] tests = {new CSCHash(), new PJWHash(), new PreissHash(), 
      new UnsignedCSCHash(),new BKDRHash(),new APHash()};
  
  /**
   * Creates a new word hash uniformity tester using a given dictionary
   * @param dictionaryFile The new-line delimited word dictionary
   */
  public HashUnformityTester(String dictionaryFile) {
    try {
      ingestDictionary(dictionaryFile);
    } catch(IOException ex) {
      System.err.println("Could not read dictionary file");
      System.exit(-1);
    }
  }
  
  /**
   * Reads the dictionary file into the word list
   * @param dictFile The path of the dictionary file
   * @throws IOException The file could not be read
   */
  private void ingestDictionary(String dictFile) throws IOException {
    Scanner scan = new Scanner(new FileReader(dictFile));
    while(scan.hasNextLine()) {
      words.add(scan.nextLine().toLowerCase());
    }
    System.out.println(String.format("Dictionary contained %d words", 
        words.size()));
    scan.close();
  }
  
  /**
   * Runs the tests for the given words and tests
   * @return The list of results in order of the tests
   */
  private List<List<Integer>> runTests() {
    List<List<Integer>> testResults = new ArrayList<List<Integer>>();
    for(HashTest test : tests) {
      testResults.add(test.getHashes(words));
    }
    return testResults;
  }
  
  /**
   * Outputs the results of the test functions to a csv file
   * @param results The list of results in order of the tests
   * @param fileName The name of the output file
   * @throws IOException The file could not be accessed
   */
  private void outputResults(List<List<Integer>> results, String fileName) 
      throws IOException {
    FileWriter writer = new FileWriter(fileName);
    
    //Write out the header fields
    List<String> headers = new ArrayList<String>();
    for(HashTest test : tests) {
      headers.add(test.getName());
    }
    writer.write(String.join(",", headers) + "\n");
    
    
    //Write out the results, line by line
    List<String> dataRow = new ArrayList<String>();
    for(int wordIndex = 0; wordIndex < words.size(); wordIndex++) {
      dataRow.clear();
      //Add each hash for the given word to the output stream
      for(int testIndex = 0; testIndex < tests.length; testIndex++) {
        dataRow.add(results.get(testIndex).get(wordIndex).toString());
      }
      writer.write(String.join(",", dataRow) + "\n");
    }
    
    writer.close();
  }
  
  @Override
  public void run() {
    List<List<Integer>> results = runTests();
    try {
      outputResults(results, "results.csv");
    } catch (IOException e) {
      System.err.println("Could not output results to file");
      System.exit(-1);
    }
    System.out.println(String.format("Successfully ran %d tests", tests.length));
  }

  public static void main(String[] args) {
    if(args.length < 1) {
      System.err.println("Requires dictionary file as first argument");
      return;
    }
    
    
    String dictionaryFile = args[0];
    HashUnformityTester timer = new HashUnformityTester(dictionaryFile);
    timer.run();
  }
}
