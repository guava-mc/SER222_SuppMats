# Details

This Practice Exam focuses on programming problems and is meant to provide questions similar to what you may see on the actual Exam.

This should be used in conjunction with the other Exam Review Material including but not limited to: reviewing exercises, short answers, programming assignments, Exam 3 Practice Exam, live Exam 3 Review, and Exam 3 Muddiest Point material.

This Practice Exam has a time limit and the questions have point values that reflect, roughly, what they would be worth if on the exam. Remember points correspond to the amount of work you need to do. This is for practice only, *this practice exam will not have any _direct_ impact on your grade.

There are 4 questions. Time limit 45 minutes. Have fun!

Author: Mason Cole

# Exam

## Question1

You are given a node that looks like this: 

```Java
public class Node {
    int value;
    Node left;
    Node right;

    public Node(int x){
        value = x;
    }
}
```

You are told the node is the root of a BST, however, the person telling you is not very reliable.

What type of tree traversal: inOrder, preOder, postOrder, levelOrder, would be best for verifying the tree is a valid BST? Justify.

## Question 2

Write a method using your answer from Q1 with a signature as seen below that returns a boolean to check if the Node really is the root of a valid BST [your solution should run in O(N)]:

```Java
public boolean isValidBST(Node root){
	//code here
}
```

_As always and as shown in lecture you can use helper methods if needed_

## Question 3

Given:

```Java
 class Friends {
    String friend1;
    String friend2;

    public Friends(String name1, String name2){
        friend1 = name1;
        friend2 = name2;
    }
}
```
You are asked to determine, based on a List<Friends>, who in the list has the most friends.

For example:

```
Let a Friends Object be represented as a tuple: {"friend1", "friend2"}. 
Given the list of friends:
[ {"Ruben","Scott"} , {"Jon","Ruben"} , {"Ruben","Mason"} , {"Mason","Jon"} ]

the result would be: "Ruben". 

Explanation
Because Ruben has 3 friends. Whereas Jon and Mason have 2. And poor Scott has 1.
```
From the structures covered in this Exam: BST, Hashtables, Undirected Graphs, and Directed Graphs, which structures could be used to find/represent who has the most friends? Justify.

 

_Note: you can assume that the friends associations in a Friends object is bi-directional, as in, both people in the Friends object are friendly with the other person in the object. And that all friends are unique (there aren't two Rubens, there can be only one)_

## Question 4

*Friendly Disclaimer*: The solution to this question requires a bit more work than a question you will find on the Exam. You should use the skills you've acquired in this class to still take a crack at it. You definitely have the tools needed to solve it and there is value in at least attempting it. If you are running out of time or getting a little lost try using pseudo-code templates to provide framework for a potential solution. 😅😅😅

Write a method using a structure from your answer in Q3 with a signature as seen below that returns a String representing the friend with the most friends.

remember, the choices in Q3 were: BST, Hashtables, Undirected Graphs, and/or Directed Graphs. 

```Java
public String letsTalkAboutPopular(List<Friends> friendlies){
	//code goes here
}
```

_You can assume there exists a most popular person_

For your convenience here is the example again:

```
For example:
Let a Friends Object be represented as a tuple: {"friend1", "friend2"}. 
Given the list of friends:
[ {"Ruben","Scott"} , {"Jon","Ruben"} , {"Ruben","Mason"} , {"Mason","Jon"} ]

the result would be: "Ruben". 

Explanation: 
Because Ruben has 3 friends. Whereas Jon and Mason have 2. And poor Scott has 1.
```

# Solutions

## Question 1 SOLUTION

inOrder traversal because we would be able to see if the inOrder traversal represents an ascending sorted list.

## Question 2 SOLUTION

```Java
//There are many ways to solve this problem
//Iteratively, Recursively, inefficiently, efficiently.
//The below solution represents a possible
//easy to read and quick to code solution
public boolean isValidBST(Node root) {
	List<Integer> nodeVals = new ArrayList<>();
    inOrder(root, nodeVals);
    return isSorted(nodeVals);
    }
     
    //Traverse the structure in order and add values
    //to the List
    private void inOrder(Node node, List<Integer> vals){
    if(node == null) return;
        inOrder(node.left, vals);
        vals.add(node.val);
        inOrder(node.right, vals);
    }
     
    //Iterate the list comparing each value
    //to validate the tree is valid.
    private boolean isSorted(List<Integer> vals){
        long lastVal = Long.MIN_VALUE; //a value smaller than the smallest possible integer
        for(Integer x : vals){
            if(x <= lastVal) return false;
                lastVal = x;
            }
            return true;
        }
```

## Question 3 SOLUTION

Hashtable: you could iterate the List<Friends> and use a HashMap<String, Integer>

for each name in every Friends object, you use the name as a Key and increment Value. The Key with the largest Value has the most friends.

Note: As in the example solution for Q4, if you needed to account for repeating names, or for if two friend associations are represented, but mirrored, i.e. {"Ruben", "Scott"} and {"Scott", "Ruben"}, or if you wanted to have the friends data along with total friends, it would be better to use a Set instead of an Integer for the Value.


Undirected Graph: if you let each friend represent a Vertex and every edge between two vertices represents a friendship connection, the graph would represent all connections and based on the number of adjacent vertices, you could determine who has the most friends.

## Question 4 SOLUTION

```Java
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
}
```
