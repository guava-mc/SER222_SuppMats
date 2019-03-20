public class isValid {

    /**
     * QUESTION1:
     * You are given a node that looks like this:
     *
     * public class Node {
     *     int value;
     *     Node left;
     *     Node right;
     *
     *     public Node(int x){
     *         value = x;
     *     }
     * }
     * You are told the node is the root of a BST, however, the person telling you is not very reliable.
     *
     * What type of tree traversal: inOrder, preOder, postOrder, inLevel, would be best for verifying the tree is a valid BST? Explain.
     *
     * ANSWER:
     * inOrder traversal because we would be able to see if the inOrder traversal represents an ascending sorted list.
     */

    /**
     * QUESTION2:
     * Write a method using your answer from Q1 with a signature as seen below that returns a boolean to check if the Node really is the root of a valid BST [your solution to run in O(N)]:
     *
     * public boolean isValidBST(Node root){
     * 	//code here
     * }
     * *As always and as shown in lecture you can use helper methods if needed*
     *
     * ANSWER:
     * //There are many ways to solve this problem
     * //Iteratively, Recursively, inefficiently, efficiently.
     * //The below solution represents a possible
     * //easy to read and quick to code solution
     * public boolean isValidBST(Node root) {
     *         List<Integer> nodeVals = new ArrayList<>();
     *         inOrder(root, nodeVals);
     *         return isSorted(nodeVals);
     *     }
     *
     *     //Traverse the structure in order and add values
     *     //to the List
     *     private void inOrder(Node node, List<Integer> vals){
     *         if(node == null) return;
     *         inOrder(node.left, vals);
     *         vals.add(node.val);
     *         inOrder(node.right, vals);
     *     }
     *
     *     //Iterate the list comparing each value
     *     //to validate the tree is valid.
     *     private boolean isSorted(List<Integer> vals){
     *         long lastVal = Long.MIN_VALUE; //a value smaller than the smallest possible integer
     *         for(Integer x : vals){
     *             if(x <= lastVal) return false;
     *             lastVal = x;
     *         }
     *         return true;
     *     }
     */



}
