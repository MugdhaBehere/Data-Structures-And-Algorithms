package Tries;

import java.util.HashMap;

public class Trie {
    Node root;
    public Trie(){
        root=new Node();
        
    }
    public void insert(String word){
        char[]c =word.toCharArray();
        Node current=root;
        for(int j=0; j<c.length; j++){
            if(!current.contains(c[j]))
            current.addNode(c[j]);
            current=current.getNode(c[j]);
        }
        current.setWord(true);
    }
    public boolean hasPrefix(String prefix){
        char []c = prefix.toCharArray();
        Node current=root;
        for(int j=0;j<c.length;j++){
            if(!current.contains(c[j]))
            return false;
            current=current.getNode(c[j]);
        }
        return true;
    }
    public boolean hasWord(String word){
        char c[]=word.toCharArray();
        Node current =root;
        for(int j =0; j< c.length;j++){
            if(!current.contains(c[j]))
            return false;
            current=current.getNode(c[j]);
        }
        return current.isWord();
    }
    public void delete(String word){
        if(word==null || word.isEmpty())
        return;
        Node lastNode=getLastNode(word);
        if(lastNode==null || !lastNode.isWord())
        return;
        lastNode.setWord(false);
        if(lastNode.getMap().size()>0){
            return;
        }
        removeLastNodeWithMultipleChildren(word);
    }
    private Node getLastNode(String word){
        char c[]=word.toCharArray();
        Node current=root;
        for(int j=0; j<c.length;j++){
            if(!current.contains(c[j]))
            return null;
            current = current.getNode(c[j]);
        }
        return current; 
    }    
    private void removeLastNodeWithMultipleChildren(String word){
        char c[]=word.toCharArray();
        Node lastNodeWithMultipleChildren=null;
        char childToBreak=0;
        Node current=root;
        for(int j=0;j<c.length;j++){
            if(!current.contains(c[j]))
            return;
            current = current.getNode(c[j]);
            if(current.getMap().size()>1||current.isWord()){
                lastNodeWithMultipleChildren=current;
                childToBreak=c[j+1];
            }
        }
        if(lastNodeWithMultipleChildren!=null){
            lastNodeWithMultipleChildren.getMap().remove(childToBreak);
        }
        else{
            root.getMap().remove(c[0]);
        }
    }
    public static void main(String args[]){
        Trie t=new Trie();
        t.insert("cat");
        t.insert("catalyst");
        t.insert("cataract");
        t.insert("catastrophe");
        t.insert("dot");
        t.insert("dog");
        t.insert("app");
        t.insert("application");
        t.insert("applicable");
        t.insert("apply");
        boolean has_prefix=t.hasPrefix("ca");
        System.out.println(has_prefix);
        boolean has_word=t.hasWord("catalyst");
        System.out.println(has_word);
        has_word=t.hasWord("do");
        System.out.println(has_word);
        t.delete("dot");
    }
}
 class Node{
    HashMap<Character, Node>map;
    boolean isWord;
    public Node(){
        map=new HashMap<>();
        isWord=false;
        
    }
    public boolean isWord(){
        return isWord;
    }
    public void setWord(boolean isWord){
        this.isWord=isWord;
    }
    public boolean contains(char c) {
        return map.containsKey(c);
    }
    public void addNode(char c){
        if(!map.containsKey(c)){
            map.put(c, new Node());
        }
    }
    public Node getNode(char c){
        return map.get(c);
    }
    public HashMap<Character, Node> getMap(){
        return map;
    }
}


