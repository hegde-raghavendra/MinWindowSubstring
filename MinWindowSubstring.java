import java.util.*;


class Main {

 
  public static String MinWindowSubstring(String[] strArr) {
    // code goes here  

    String stringToSearch = strArr[0];
    String searchString = strArr[1];

    List<String> variousCombinationsFromStringToSearch = new ArrayList<String>();
    List<String> matchedStringList = new ArrayList<String>();

    Hashtable<Character,Integer> searchStringHashTable = new Hashtable<Character,Integer> ();

    createHashTable(searchString,searchStringHashTable);

    for (int i = 0; i < stringToSearch.length(); i++) {
      for (int j = i + searchString.length() ; j < stringToSearch.length() + 1; j++) {
        variousCombinationsFromStringToSearch.add(stringToSearch.substring(i, j));
      }
    }

    Hashtable<Character,Integer> variousCombHashTable = new Hashtable<Character,Integer> ();
    
    for (int i = 0; i < variousCombinationsFromStringToSearch.size(); i++) {

      variousCombHashTable.clear();

      createHashTable(variousCombinationsFromStringToSearch.get(i),variousCombHashTable);

      if (compareHashTables(searchStringHashTable, variousCombHashTable)) {
        System.out.println(variousCombinationsFromStringToSearch.get(i) + "matches the" + searchString);
        matchedStringList.add(variousCombinationsFromStringToSearch.get(i));
      }

    }

   // System.out.println(matchedStringList);

    int smallestLength = 0;

    if (matchedStringList.size() > 0 ) {
      for (int j = 0; j < matchedStringList.size() - 1; j++) {

        if  (matchedStringList.get(j).length() > matchedStringList.get(j+1).length() ) {
         smallestLength = j+1;
        }
        
       }

       return matchedStringList.get(smallestLength);

    }

    return "Not found";
    
  }


  private static boolean compareHashTables(Hashtable<Character, Integer> searchStringHashTable,
      Hashtable<Character, Integer> variousCombHashTable) {

    Enumeration<Character> charsInSearchStringHashTable = searchStringHashTable.keys();

    while (charsInSearchStringHashTable.hasMoreElements()) {

      Character ch = charsInSearchStringHashTable.nextElement();

      if (variousCombHashTable.get(ch) == null) {
        return false;
      }
      else {
        if (variousCombHashTable.get(ch) < searchStringHashTable.get(ch)) {
          return false;
        }
      }
      
    }

    return true;

  }

  public static void createHashTable(String searchString, Hashtable<Character, Integer> searchStringHashTable) {

    Character charTemp;
    for (int i = 0; i < searchString.length(); i++) {
      
      charTemp = searchString.charAt(i);

      if (searchStringHashTable.get(searchString.charAt(i)) == null) {
        searchStringHashTable.put(charTemp, 1);
      }
      else {
        searchStringHashTable.put(charTemp, searchStringHashTable.get(searchString.charAt(i)) + 1);
      }
      
    }



    //System.out.println("hashtable for " + searchString + "created");

    
  }

  public static void main (String[] args) {  
    // keep this function call here     
    Scanner s = new Scanner(System.in);

    String[] strInput = new String[2];

    System.out.print("Enter string to search : ");

    strInput[0] = s.nextLine();

    System.out.print("Enter search string : ");

    strInput[1] = s.nextLine();

        
    System.out.println(MinWindowSubstring(strInput)); 


    s.close();
  }

}