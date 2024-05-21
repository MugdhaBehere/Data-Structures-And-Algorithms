package Graphs;

import java.util.ArrayList;

 class GraphList {
    
     static void addEdge(ArrayList<ArrayList<Integer>> adj , int u, int v){
    adj.get(u).add(v);
    adj.get(v).add(u);
  }
   static void printList(ArrayList<ArrayList<Integer>> adj){
    for(int j=0; j<adj.size(); j++){
      System.out.println("Adjacency list of" + " " +  j);
      for(int k=0; k<adj.get(j).size(); k++){
        System.out.print(adj.get(j).get(k) + " ");
      }
      System.out.println();
    }
  }
  public static void main(String args[]){
    int V=5;
    ArrayList<ArrayList<Integer>> adj=new ArrayList<ArrayList<Integer>>(V);
    for(int j=0; j<V;j++){
      adj.add(new ArrayList<Integer>());
    }
      addEdge(adj, 0, 1);
      addEdge(adj, 0, 4);
      addEdge(adj, 1, 4);
      addEdge(adj, 2, 3);
      addEdge(adj, 3, 4);
      printList(adj);
    }
}
