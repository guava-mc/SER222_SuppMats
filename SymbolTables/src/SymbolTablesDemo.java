import java.util.*;

public class SymbolTablesDemo<Key, Value> {


    //Little extra bonus exposure to things java has that you can use
    //A HashSet is a Set, an unordered collection of items wherein
    //each item is unique. In a set you only have a single Element.
    //for continuity I am calling it Key, but really it is both the
    //Key and the Value, or simply an Element.
    Set<Key> hashset = new HashSet<>();

    //the older version of HashMap in java, this is naturally synchronized
    Map<Key, Value> hashtable = new Hashtable<>();

    //this is the implementation you'd want to be using for most instances
    //and it actually has a way of becoming syncronized using:
    //Collections.synchronizedMap(hashmap);
    Map<Key, Value> hashmap = new HashMap<>();

    /**
     * Demo of using a HashMap
     *
     * WITHOUT RUNNING THE CODE --
     * try to walk through it and answer the questions
     * @param args
     */
    public static void main(String[] args) {

        //used args as a quick aside because I see
        //questions about args enough that it warranted
        //a brief introduction =]
        //
        //you can run this code from the cml going to
        //the pathname where you can find
        //SymbolTablesDemo.java and typing:
        //javac SymbolTablesDemo.java
        //java SymbolTablesDemo FirstName1.LastName1 FirstName2.LastName2 FirstName3.LastName3
        //ex:
        //java SymbolTablesDemo Jon.Bush Scott.Dunning Mason.Cole
        //or you can set your run time configurations from your ide.
        //example in intellij: https://docs.google.com/document/d/17PImSAIgaGZRtODld291o0VFRAVO7HMwkno8kTm4t5c/edit?usp=sharing
        //example in eclipse:  https://docs.google.com/document/d/1xsfQ6EQGJo2BwXlb7ZrMwtYiEwg4nCkhS8cAFJ8bWJg/edit?usp=sharing

        String s1 = args[0]; //Jon.Bush
        String s2 = args[1]; //Scott.Dunning
        String s3 = args[2]; //Mason.Cole

        //Create a new HashMap using the Map interface with
        //Key = String and Value = List<String>
        Map<String, List<String>>  studentClasses = new HashMap<>();

        //What are we doing here?
        //Why do we need to create new LinkedLists?
        studentClasses.put(s1, new LinkedList<>());
        studentClasses.put(s2, new LinkedList<>());
        studentClasses.put(s3, new LinkedList<>());


        //iterate using Keys
        for(String key : studentClasses.keySet()){
            System.out.println(key);
        }

        System.out.println(); //newline

        //iterate using Entries
        for(Map.Entry<String, List<String>> e : studentClasses.entrySet()){
            System.out.println(e.getKey());
            e.getValue().add("SER222"); //also add SER222 to each of their course lists
        }

        //When do you think using entrySet could be better for
        //iterating a HashMap?

        //When do you think keySet is better?

        //What is happening here?
        studentClasses.get(s2).add("SER334");
        studentClasses.get(s2).add("SER516");
        studentClasses.get(s2).add("SER599");

        //s1 and s3 (Jon and Mason)
        //both really want to do some
        //Independent Study, so they enroll in 499
        studentClasses.get(s1).add("SER499");
        studentClasses.get(s3).add("SER499");

        //Jon also wants to take 422
        studentClasses.get(s1).add("SER422");

        //Hey, a new 'Student', Professor Acuna heard
        //that in ENG 470 they are reading Harry Potter
        //wingardium levi-register!
        studentClasses.put("Ruben.Acuna", new LinkedList<>()); //re-explain to yourself what is happening here
        studentClasses.get("Ruben.Acuna").add("ENG470");       //and here

        System.out.println(); //newline

        //What is the output?
        for(String key : studentClasses.keySet()){
            System.out.println(key);
        }

        List<String> altSched = new LinkedList<>();

        altSched.add("ENG470"); //Read Harry Potter
        altSched.add("NTR348"); //Learn about food and culture
        altSched.add("GER101"); //Sprechen Sie Deutsch?

        //What happens here?!
        studentClasses.put(s3, altSched);

        //what size do we expect now that we are putting a new {K,V} pair
        //into schedulesClasses?

        System.out.println(); //newline

        //iterate the HashMap and print each person's scheduled courses
        //can you predict the output without executing the code?
        for(Map.Entry<String, List<String>> e : studentClasses.entrySet()){
            System.out.println(e.getKey() + " is scheduled for:");
                for(String course : e.getValue()){
                    System.out.println("\t" + course);
                }
        }

        oh();
    }

    private static void oh(){
        Map<String, String> myST = new HashMap<>();
        myST.put("IS AWESOME", "SER222");
        System.out.println("\n\nWhat IS AWESOME?");
        System.out.println(myST.get("IS AWESOME"));
    }
}




