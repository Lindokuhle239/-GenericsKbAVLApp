

import java.util.Scanner;

/**
 * class runs the GenericsKbAVLApp
 */
public class GenericsKbAVLAppTester{
    /**
    * Main method
    * @param args Command line arguments
    */
   public static void main(String[] args){
    System.out.println("\r\n" + //
                "   _____                      _          _  ___        __      ___                           \r\n" + //
                "  / ____|                    (_)        | |/ / |      /\\ \\    / / |        /\\                \r\n" + //
                " | |  __  ___ _ __   ___ _ __ _  ___ ___| ' /| |__   /  \\ \\  / /| |       /  \\   _ __  _ __  \r\n" + //
                " | | |_ |/ _ \\ '_ \\ / _ \\ '__| |/ __/ __|  < | '_ \\ / /\\ \\ \\/ / | |      / /\\ \\ | '_ \\| '_ \\ \r\n" + //
                " | |__| |  __/ | | |  __/ |  | | (__\\__ \\ . \\| |_) / ____ \\  /  | |____ / ____ \\| |_) | |_) |\r\n" + //
                "  \\_____|\\___|_| |_|\\___|_|  |_|\\___|___/_|\\_\\_.__/_/    \\_\\/   |______/_/    \\_\\ .__/| .__/ \r\n" + //
                "                                                                                | |   | |    \r\n" + //
                "                                                                                |_|   |_|    \r\n" + //
                "");
      
   GenericsKbAVLApp entries = new GenericsKbAVLApp();
      Scanner keyboard = new Scanner(System.in);
      int choice = 0;

      while (choice != 8) {
         entries.displayMenu();
         System.out.print("Enter your choice: ");
         choice = keyboard.nextInt();
         keyboard.nextLine(); // Consume newline

         switch (choice) {
            case 1:
               System.out.print("Enter file name: ");
               String filename = keyboard.nextLine();
               entries.loadKnowledgeBaseFromFile(filename);
               System.out.println("###########################################################################################");
               break;
            case 2:
               System.out.print("Enter term: ");
               String term = keyboard.nextLine();
               System.out.print("Enter statement: ");
               String statement = keyboard.nextLine();
               System.out.print("Enter confidence score: ");
               double confidenceScore = keyboard.nextDouble();
               Data data = new Data(term, statement, confidenceScore);
               Node entry = new Node(data);
               entries.insert(entry);
               System.out.println("Statement for " + term + " has been updated.");
               System.out.println("###########################################################################################");
               break;
            case 3:
               System.out.print("Enter term: ");
               String termToDelete = keyboard.nextLine();
               entries.root = entries.deleteNode(termToDelete, entries.root);
               System.out.println("Knowledge base entry with term \"" + termToDelete + "\" deleted.");
               System.out.println("###########################################################################################");
               break;
            case 4:
               System.out.print("Enter term to search: ");
               String searchTerm = keyboard.nextLine();
               Data searchData = entries.search(entries.root, searchTerm);
               if (searchData != null){
                  System.out.println("Term \"" + searchTerm + "\" found. Sentence is \"" + searchData.getSentence() + "\", and the confidence score is " + searchData.getConfidenceScore());
               }
               else{
                  System.out.println("Term \"" + searchTerm + "\" not found.");
               }
               System.out.println("###########################################################################################");
               break;
            case 5:
               System.out.println("Enter file name for queries: ");
               String queriesFilename = keyboard.nextLine();
               entries.searchQueriesFromFile(queriesFilename);
               System.out.println("###########################################################################################");
               break;
            case 6:
               entries.printLevelOrder();
            case 7:
               System.out.println("Enter size of your subset: ");
               int n = keyboard.nextInt();
               entries.operationCounter(n);
               System.out.println();
               break;
            case 8:
               entries.printLevelOrder();
               System.out.println("Exiting...");
               break;
            default:
               System.out.println("Invalid choice. Please select a valid option.");
               break;
         }
      }
      keyboard.close();
 }
}