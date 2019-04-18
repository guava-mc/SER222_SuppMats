import java.util.*;

public class BST<Key extends Comparable<? super Key>, Value> {

    static class Node<Key extends Comparable<? super Key>, Value> {
        int N;
        Key key;
        Value value;
        Node leftChild;
        Node rightChild;

        Node(Key k, Value v, int N){
            key = k;
            value = v;
            this.N = N;
            leftChild = null;
            rightChild = null;
        }

        public String toString() {
            return value.toString();
        }
    }

    Node root;


    public void put(Key k, Value v){
        root = put(root, k, v);
    }

    private Node<Key,Value> put(Node x, Key k, Value v){
        if (x == null)
            return new Node(k,v,1);

        int cmp = x.key.compareTo(k);
        if (cmp < 0)
            x.rightChild = put(x.rightChild, k, v);
        else if (cmp > 0)
            x.leftChild = put(x.leftChild, k, v);
        else
            x.value = v;
        x.N = size(x.leftChild) + size(x.rightChild) + 1;
        return x;
    }

    private int size(Node x) {
        if(x == null) return 0;
        return x.N;
    }


    public static void main(String[] args) {
        BST<Integer, String> bst = new BST<>();

        bst.put(10, "ten");
        bst.put(1, "one");
        bst.put(30, "thirty");
        bst.put(Integer.MAX_VALUE, "0x7fffffff");
        bst.put(Integer.MIN_VALUE, "0xffffffff");
        bst.put(23, "twenty-three");
        bst.put(22, "twenty-two");
        bst.put(1337, "leet");

        bst.printLevelOrderTiers();
    }










    private void printLevelOrder(Node root) {
        Queue<Node<Key, Value>> levelOrder = new LinkedList<>();
        levelOrder.add(root);
        while(!levelOrder.isEmpty()){
            Node n = levelOrder.remove();
            //System.out.print(n.key+":"+n.value + " ");
            if(n.leftChild != null) levelOrder.add(n.leftChild);
            if(n.rightChild != null) levelOrder.add(n.rightChild);
        }
    }




    private void printLevelOrderTiers() {
        List<Queue<Node<Key, Value>>> levels = new LinkedList<>();
        //levelOrder.add(root);
        levels.add(new LinkedList<>());
        levels.get(0).add(root);
        int i = 0;
        while(i != levels.size()) {
            Queue<Node<Key, Value>> levelOrder = copy(levels.get(i++));
            levels.add(new LinkedList<>());
            while (!levelOrder.isEmpty()) {
                Node n = levelOrder.remove();
                //System.out.print(n.key + ":" + n.value + " ");
                if (n.leftChild != null) levels.get(i).add(n.leftChild);
                if (n.rightChild != null) levels.get(i).add(n.rightChild);
            }
            //System.out.println();
            if(levels.get(i) == null || levels.get(i).isEmpty())
                levels.remove(i);
        }
        printTree(levels);
    }

    private Queue<Node<Key, Value>> copy(Queue<Node<Key, Value>> nodes) {
        Queue<Node<Key, Value>> temp = new LinkedList();
        for(Node<Key, Value> n : nodes){
            temp.add(n);
        }
        return temp;
    }

    private void printTree(List<Queue<Node<Key, Value>>> levels) {
        int i = 0;
        for(Queue q : levels) {
            System.out.println("L"+i+++": " + Arrays.deepToString(q.toArray()));
        }
    }

}
