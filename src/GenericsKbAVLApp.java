

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**This is an AVL tree program that stores nodes that are of class Data, containing a term, sentence and confidence score.
 * Author: Lindokuhle Mdlalose
 * Some parts of code was taken from happycoders.eu and implemented to fit the functioning of this specific program and some parts were taken form the authors assignment 1
 * Date: 20 March 2024
 */
public class GenericsKbAVLApp {
    Node root;
    private int comparisonCountInsert;
    private int comparisonCountSearch;

    
    /**
     * Loads knowledge base entries from a file into the Binary Search Tree.
     * @param filename The name of the file containing knowledge base entries.
     */
   public void loadKnowledgeBaseFromFile(String filename){
    //opens a BufferedReader to read from specified file using a FileReader
      try (BufferedReader br = new BufferedReader(new FileReader(filename))){
      //variable line of String type to store each line from the file
         String line;
         //will read each line from the file until the end
         while ((line = br.readLine()) != null){
            //comparisonCountInsert++;
            //Splits the line by a tab (\t)
            String[] parts = line.split("\t");
            //if an object has 3 instances
            //take first 1st instance as the term, 2nd as the statement and 3rd as the confidenceScore
            if (parts.length == 3){
               String term = parts[0];
               String statement = parts[1];
               double confidenceScore = Double.parseDouble(parts[2]);
               //make a data object uses the instances above,
               //take the data object and create a node object with the data object as the parameter
               //then use insert method to to add the node to the BST
               Data data = new Data(term,statement,confidenceScore);
               Node node = new Node(data);
               insert(node);
            }
         }
         System.out.println("Knowledge base loaded successfully.");
         System.out.println("");
      }
      catch (IOException e){
         System.err.println("Error loading base file " + e.getMessage());
         System.out.println("");

      }
   }

   /**
    * Searches quiries from a file, in the AVL tree and then saves the output of the search in a new separate file called "QueryOutput.txt"
    * @param filename The file containing the queries
    */
   public void searchQueriesFromFile(String filename){
      try (BufferedReader queryReader = new BufferedReader(new FileReader(filename));
         BufferedWriter writer = new BufferedWriter(new FileWriter("QueryOutput.txt"))){
         String query;
         while ((query = queryReader.readLine()) != null){
            Data searchData = search(root,query.trim());
            if (searchData != null){
               writer.write("Term \"" + query.trim() + "\" found. Sentence is \"" + searchData.getSentence() + "\", and the confidence score is " + searchData.getConfidenceScore());
               writer.newLine();
            }
            else{
               writer.write("Term not found: " + query.trim());
               writer.newLine();
            }
         }
      }
      catch (IOException e){
         System.err.println("Error reading queries file: " + e.getMessage());
      }
   }   

    /**
     * Inserts a node into the Binary Search Tree.
     * @param node The node to be inserted.
     */
   public void insert(Node node){
      comparisonCountInsert = 0;
      root = insertHelper(root, node);
      root = rebalance(root);
     }
     /**
       * Recursive helper method to insert a node into the Binary Search Tree.
       * @param root The root node of the subtree.
       * @param node The node to be inserted.
       * @return The updated root of the subtree.
       */
     // Recursive helper method to insert a node into the Binary Search Tree
   private Node insertHelper(Node root, Node node){
      Data newData = node.getData();
        //If the root is null, create a new node with the data
      if (root == null){
         return new Node(newData);
      }
        // If the term matches, update the statement and confidence score
      else if (newData.getTerm().compareTo(root.getData().getTerm()) == 0){
         root.getData().setSentence(newData.getSentence());
         root.getData().setConfidenceScore(newData.getConfidenceScore());
      }
        // Recursively traverse the tree based on term comparison
      else if (newData.getTerm().compareTo(root.getData().getTerm()) < 0){
         comparisonCountInsert++;
         root.left = insertHelper(root.left,node);
      }
      else{
         comparisonCountInsert++;
         root.right = insertHelper(root.right,node);
      }
      //update height of the parent node
      updateHeight(root);
      return rebalance(root);
     }

   /**
    * Evaluates the height of the specified node
    * @param node The node whose height is being evaluated
    * @return The height of the specified node
    */
   public int height(Node node){
      if (node == null){
         return 0;
      }
      else{
         return 1 + Math.max(height(node.left), height(node.right));
      }
   }    

   /**
    * Updates the height of the specified node in the AVL tree
    * @param node The node whose height is being updated
    */
   public void updateHeight(Node node){
      int heightOfLeftChild = height(node.left);
      int heightOfRightChild = height(node.right);
      node.height = Math.max(heightOfLeftChild,heightOfRightChild) + 1;
   }  

   /**
    * Calculates the balance factor of the specified node
    * @param node The node whose balance factor is being calculated
    * @return The balance factor of the specified node
    */
   private int balanceFactor(Node node){
     return height(node.right) - height(node.left);
   }

   /**
    * Rebalances the avl tree when required (after insertion or deletion)
    * @param node The node at which rebalancing is to be performed
    * @return The root node of the subtree after rebalancing
    */
   public Node rebalance(Node node){
     int balanceFactor = balanceFactor(node);

     if (balanceFactor < -1){
         if (balanceFactor(node.left) <= 0){
            node = rotateRight(node);
         }
         else{
            node.left = rotateLeft(node.left);
            node = rotateRight(node);
         }
      }
      if (balanceFactor > 1){
         if (balanceFactor(node.right) >= 0){
            node = rotateLeft(node);
         }
         else{
            node.right = rotateRight(node.right);
            node = rotateLeft(node);
         }
      }
      return node;
   }

   /**
    * Performs a right rotation on the specified node
    * @param node The node where the rotation is performed
    * @return The new root node of the subtree after rotation
    */
   public Node rotateRight(Node node){
      Node leftChild = node.left;

      node.left = leftChild.right;
      leftChild.right = node;

      updateHeight(node);
      updateHeight(leftChild);

      return leftChild;
   }
     
   /**
    * Performs a left rotation on the specified node
    * @param node The node where the rotation is performed
    * @return The new root node of the subtree after rotation
    */
   public Node rotateLeft(Node node){
      Node rightChild = node.right;

      node.right = rightChild.left;
      rightChild.left = node;

      updateHeight(node);
      updateHeight(rightChild);

      return rightChild;
   }

   /**
    * Deletes node based on the term as the key
    * @param key The term of the node to delete
    * @param node The root of the subtree to delete
    * @return The root node of the subtree after deletion
    */
   public Node deleteNode(String key, Node node) {
      // No node at current position --> go up the recursion
      if (node == null) {
        return null;
      }
  
      // Traverse the tree to the left or right depending on the key
      if (key.compareTo(node.data.getTerm()) < 0) {
        node.left = deleteNode(key, node.left);
      } else if (key.compareTo(node.data.getTerm()) > 0) {
        node.right = deleteNode(key, node.right);
      }
      else{
         if (node.left == null){
            return node.right;
         }
         else if (node.right == null){
            return node.left;
         }
         Node inOrderSuccessor = findMinimum(node.right);
         node.data = inOrderSuccessor.data;
         node.right = deleteNode(inOrderSuccessor.data.getTerm(), node.right);
      }
      updateHeight(node);
      return rebalance(node);
   }

   /**
    * find the node with the minimum value in the avl tree rooted at the specified node
    * @param node the root node of the subtree to search
    * @return the nide with the minimum value in the subtree
    */
   private Node findMinimum(Node node){
      if (node == null){
         return null;
      }
      while (node.left != null){
         node = node.left;
      }
      return node;
   }

   /**
    * Searched for a knowledge base in the avl using the term
    * @param node The root node of the subtree to search
    * @param term The term to be searched
    * @return The data of the node (term, sentence and confidence score)
    */
   public Data search(Node node, String term){
      if (node == null || node.data.getTerm().equals(term)){
         return node != null ? node.getData() : null;
      }
      if (term.compareTo(node.data.getTerm()) < 0){
         comparisonCountSearch++;
         return search(node.left, term);
      }
      else{
         comparisonCountSearch++;
         return search(node.right, term);
      }
   }

   /**
    * prints the nodes of the avl tree in level order
    */
   public void printLevelOrder(){
      int height = height(root);
      for (int i = 1; i <= height; i++){
         printGivenLevel(i);
      }
   }
   /**
    * Prints the nodes at the specified level of the avl tree
    * @param level The level of the tree to print
    */
   public void printGivenLevel(int level){
      printGivenLevel(root,level);
   }
   /**
    * helper method to print nodes in the specified level
    * @param node the root node of the sutree
    * @param level The level of the tree to print
    */
   public void printGivenLevel(Node node,int level){
      if (node == null){
         return;
      }
      if (level == 1){
         System.out.println(node.data.toString() + " ");
      }
      else if (level > 1){
         printGivenLevel(node.left,level -1);
         printGivenLevel(node.right, level-1);
      }
   }


   /**
    * Reads data from a dile, shiffles it and then creates a subset of n elements
    * @param filename The name of the file with the data
    * @param n Number of elements in the subset
    * @return  Subset of the data read from the file
    */
   public List<Data> createShuffledSubset(String filename, int n) {
      List<Data> dataList = new ArrayList<>();

      // Read data from the file and store it in the dataList
      try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
         String line;
         while ((line = br.readLine()) != null) {
            String parts[] = line.split("\t");
            if (parts.length == 3){
               String term = parts[0];
               String sentence = parts[1];
               double confidenceScore = Double.parseDouble(parts[2]);
               dataList.add(new Data(term,sentence,confidenceScore));
            }
         }
      } catch (IOException e) {
         System.err.println("Error reading file: " + e.getMessage());
      }
      // Shuffle the dataList
      Collections.shuffle(dataList);
      // Create a subset containing the first n elements of the shuffled dataList
      List<Data> subset = new ArrayList<>();
      for (int i = 0; i < Math.min(n, dataList.size()); i++){
         subset.add(dataList.get(i));
      }
      return subset;
   }

   public void comparisonCount(){
      System.out.println(comparisonCountInsert);
      System.out.println(comparisonCountSearch);
   }

   public void operationCounter(int n) {
      // Create a random subset of size n from the file
      List<Data> subset = createShuffledSubset("GenericsKB.txt", n);
  
      // Count the number of insert operations
      int insertOperations = 0;
      int searchOperations = 0;
      GenericsKbAVLApp avlTree = new GenericsKbAVLApp();

      for (Data data : subset) {
          Node newNode = new Node(data);
          insertOperations += avlTree.countInsertOperations(newNode);
      }
  
      // Count the number of search operations
      for (Data data : subset) {
          searchOperations += avlTree.countSearchOperations(data.getTerm());
      }
  
      // Print out the results
      System.out.println("Number of Insert Operations: " + insertOperations);
      System.out.println("Number of Search Operations: " + searchOperations);
  }

   private int countInsertOperations(Node newNode) {
      // Count the number of comparisons needed to insert the new node into the AVL tree
      // Traverse the tree to find the appropriate position for insertion
      // Increment the counter for each comparison made
      int comparisons = 0;
      Node current = root;
      Node parent = null;
  
      // Traverse the tree to find the appropriate position for insertion
      while (current != null) {
          parent = current;
          int compareResult = newNode.getData().getTerm().compareTo(current.getData().getTerm());
          if (compareResult < 0) {
              current = current.getLeft();
          } else if (compareResult > 0) {
              current = current.getRight();
          } else {
              // If the term already exists, no insert operation is performed
              return comparisons;
          }
          comparisons++;
      }
  
      // Insert the new node
      if (parent == null) {
          root = newNode;
      } else {
          int compareResult = newNode.getData().getTerm().compareTo(parent.getData().getTerm());
          if (compareResult < 0) {
              parent.setLeft(newNode);
          } else {
              parent.setRight(newNode);
          }
      }
  
      return comparisons;
  }
  
  private int countSearchOperations(String term) {
      // Count the number of comparisons needed to search for the given term in the AVL tree
      // Traverse the tree to find the term
      // Increment the counter for each comparison made
      int comparisons = 0;
      Node current = root;
  
      while (current != null) {
          int compareResult = term.compareTo(current.getData().getTerm());
          if (compareResult == 0) {
              // Term found
              return comparisons + 1; // Add 1 for the final comparison
          } else if (compareResult < 0) {
              current = current.getLeft();
          } else {
              current = current.getRight();
          }
          comparisons++;
      }
  
      // Term not found
      return comparisons;
  }
  
   /**
    * Dispalys menu with options to choose from when program is executed
    */
   public void displayMenu(){
      System.out.println("Choose an action from the menu.");
      System.out.println("1. Add knowledge bases from a file.");
      System.out.println("2. Insert a new knowledge base to the AVL tree.");
      System.out.println("3. Delete a knowledge base from the AVL tree.");
      System.out.println("4. Search for a knowledge base in the tree, using term.");
      System.out.println("5. Search for query item in the AVL tree.");
      System.out.println("6. Traverse through the AVL tree (levelOrder traversal)and print out all the data in the nodes.");
      System.out.println("7. Run Experiments.");
      System.out.println("8. Quit");
      System.out.println("###########################################################################################");
   }
}
