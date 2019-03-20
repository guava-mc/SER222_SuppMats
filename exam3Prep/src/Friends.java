import java.util.*;


/**
 * QUESTION1:
 * Given:
 *
 *  class Friends {
 *     String friend1;
 *     String friend2;
 *
 *     public Friends(String name1, String name2){
 *         friend1 = name1;
 *         friend2 = name2;
 *     }
 * }
 * You are asked to determined based on a List<Friends> who in the list has the most friends.
 *
 * From the structures covered in this Exam: BST, Hashtables, Undirected Graphs, Directed Graphs, which structures
 * could be used to find/represent who has the most friends? Explain.
 *
 *
 *
 * *Note: you can assume that the friends associations in a Friends object is bi-directional, as in, both people in
 * the Friends object are friendly with the other person in the object. And that all friends are unique
 * (there aren't two Rubens, there can be only one)*
 *
 * ANSWER:
 * Hashtable: you could iterate the List<Friends> and use a HashMap<String, List<String>>
 *
 * for each name in every Friends object, you use the name as a Key and add to the Value list the other name.
 * The Key with the largest List size as its value has the most friends.
 *
 *
 *
 * Undirected Graph: if you let each friend represent a Vertex and every edge between two vertices represents a
 * friendship connection, the graph would represents all connections and based on the number of adjacent vertices,
 * you could determine who has the most friends.
 */

/**
 * QUESTION2:
 * Write a method using a structure from your answer in Q3 with a signature as seen below that returns a String representing the friend with the most friends.
 *
 * public String letsTalkAboutPopular(List<Friends> friendlies){
 * 	//code goes here
 * }
 * *You can assume there exists a most popular person*
 *
 * ANSWER:
 *     //example using HashMap
 *     //we use Set here to account for any Friends objects that are mirrors
 *     //without needing to use any extra contains methods
 *     //as in Friends("Ruben", "Scott") and Friends("Scott", "Ruben")
 *     public String letsTalkAboutPopular(List<Friends> friendlies) {
 *         Map<String, Set<String>> friendMap = new HashMap<>();
 *         for(Friends f : friendlies){
 *             int listsExist = checkContains(f, friendMap);
 *             if(listsExist > 0){
 *                 if((listsExist^0x01) != 1){ //if friend1 already existed
 *                     friendMap.get(f.friend1).add(f.friend2);
 *                 }
 *                 if((listsExist^0x10) != 2){ //if friend1 already existed
 *                     friendMap.get(f.friend2).add(f.friend1);
 *                 }
 *             }
 *         }
 *         return mostPopular(friendMap);
 *     }
 *
 *
 *     //if friend1 is being added to Map return 0x01
 *     //if friend2 is being added to Map return 0x10
 *     //if both are new will return 0x11
 *     private int checkContains(Friends f, Map<String, Set<String>> friendMap){
 *         int createdNewList = 0;
 *         if(!friendMap.containsKey(f.friend1)) {
 *             Set<String> friendList = new HashSet<>();
 *             friendList.add(f.friend2);
 *             friendMap.put(f.friend1, friendList);
 *             createdNewList += 0x01;
 *         }
 *         if(!friendMap.containsKey(f.friend2)) {
 *             Set<String> friendList = new HashSet<>();
 *             friendList.add(f.friend1);
 *             friendMap.put(f.friend2, friendList);
 *             createdNewList += 0x10;
 *         }
 *         return createdNewList;
 *     }
 *
 *     //find the person in the Map with the largest Set of friends
 *     private String mostPopular(Map<String, Set<String>> friendMap) {
 *         String mostPop = null;
 *         int mostFriends = -1;
 *         for(Map.Entry<String, Set<String>> e : friendMap.entrySet()){
 *             if(e.getValue().size() > mostFriends){
 *                 mostPop = e.getKey();
 *                 mostFriends = e.getValue().size();
 *             }
 *         }
 *         return mostPop;
 *     }
 */

class Friends {
    String friend1;
    String friend2;

    public Friends(String name1, String name2) {
        friend1 = name1;
        friend2 = name2;
    }


    //example using HashMap
    //we use Set here to account for any Friends objects that are mirrors
    //without needing to use any extra contains methods
    //as in Friends("Ruben", "Scott") and Friends("Scott", "Ruben")
    public static String letsTalkAboutPopular(List<Friends> friendlies) {
        Map<String, Set<String>> friendMap = new HashMap<>();
        for(Friends f : friendlies){
            int setExists = checkContains(f, friendMap);
            if(setExists > 0){
                if((setExists^0x01) != 1){ //if friend1 already existed
                    friendMap.get(f.friend1).add(f.friend2);
                }
                if((setExists^0x10) != 2){ //if friend1 already existed
                    friendMap.get(f.friend2).add(f.friend1);
                }
            }
        }
        return mostPopular(friendMap);
    }


    //if friend1 is being added to Map return 0x01
    //if friend2 is being added to Map return 0x10
    //if both are new will return 0x11
    private static int checkContains(Friends f, Map<String, Set<String>> friendMap){
        int createdNewList = 0;
        if(!friendMap.containsKey(f.friend1)) {
            Set<String> friendSet = new HashSet<>();
            friendSet.add(f.friend2);
            friendMap.put(f.friend1, friendSet);
            createdNewList += 0x01;
        }
        if(!friendMap.containsKey(f.friend2)) {
            Set<String> friendSet = new HashSet<>();
            friendSet.add(f.friend1);
            friendMap.put(f.friend2, friendSet);
            createdNewList += 0x10;
        }
        return createdNewList;
    }

    //find the person in the Map with the largest Set of friends
    private static String mostPopular(Map<String, Set<String>> friendMap) {
        String mostPop = null;
        int mostFriends = -1;
        for(Map.Entry<String, Set<String>> e : friendMap.entrySet()){
            if(e.getValue().size() > mostFriends){
                mostPop = e.getKey();
                mostFriends = e.getValue().size();
            }
        }
        System.out.println(mostFriends);
        return mostPop;
    }

    public static void main(String[] args) {

        List<Friends> test1 = new ArrayList<>();
        List<Friends> test2 = new ArrayList<>();
        List<Friends> test3 = new ArrayList<>();
        List<Friends> test4 = new ArrayList<>();

        Friends f1 = new Friends("Ruben", "Scott");
        Friends f2 = new Friends("Scott", "Ruben");
        Friends f3 = new Friends("Ruben", "Jon");
        Friends f4 = new Friends("Ruben", "Mason");
        Friends f5 = new Friends("Scott", "Jon");
        Friends f6 = new Friends("Kevin", "Ruben");
        Friends f7 = new Friends("Alexandra", "Ruben");
        Friends f8 = new Friends("Ruben", "Rochus");

        test1.add(f1);
        test1.add(f2);
        test1.add(f3);
        test1.add(f4);
        test1.add(f5);
        test1.add(f6);
        test1.add(f7);
        test1.add(f8);

        System.out.println("Expect Ruben: " + letsTalkAboutPopular(test1));
        System.out.println();

        test2.add(f1);
        test2.add(f2);
        test2.add(f5);

        System.out.println("Expect Scott: " + letsTalkAboutPopular(test2));

        System.out.println();

        test3.add(new Friends("Ruben", "Ruben"));

        System.out.println("Expect Ruben: " + letsTalkAboutPopular(test3));





    }

}


