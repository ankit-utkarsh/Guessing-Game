import java.io.*;
import java.nio.file.*;
import java.util.*;

public class Game {

    // declaring constants
    static final String FILE_NAME= "game1.txt";
    static final int COUNT = 10;

    //declaring scanner classes
    public static Scanner obj = new Scanner(System.in);
    public static Scanner scan = new Scanner(System.in);

    //creating an empty binary tree
    static BinaryTree<String> tree = new BinaryTree<String>();

    public static void main(String args[]) throws IOException{

        // initializing required variables
        boolean play = true, correctChoice;
        String userInput, correctAns, question , NoAns;
        int choice;

        System.out.println("-----------------------------");
        System.out.println("Welcome the the Guessing Game");
        System.out.println("-----------------------------");

        // checking for existing tree data
       if (!dataExist()) {

           System.out.println("No Game Data to Load!");
           System.out.println("Status: Populating treee");
           // populating the binnary tree using the populateTree() method
           populateTree(tree);

       } else {
            // if no existing data present
           System.out.println("Game Data Found!");
            System.out.println("Status: Loading tree");
            // loading tree from data file using loadTree() method
            tree.setRootNode(loadTree());

        }

        System.out.println("-----------------------------");
        System.out.println("------- Starting Game -------\n");

        // printTree(tree.getRootNode());

        // code for the game starts here
        //while loop runs until quit option is chosen
        while (play) {

            // getting root node of the tree
            BinaryNodeInterface<String> currentNode = tree.getRootNode();


            // loop runs till the last node i.e. leaf is reached
            while (!currentNode.isLeaf()) {

                // loop to run the choice again if wrong input
                while (true) {

                    // getting yes or no from user
                    System.out.print(currentNode.getData() + "(Y/N) : ");
                    userInput = obj.next();

                    if (userInput.compareToIgnoreCase("Y") == 0) {
                        // if the input is yes then current node is updated to it's left node
                        currentNode = currentNode.getLeftChild();
                        break;
                    } else if (userInput.compareToIgnoreCase("N") == 0) {
                        // if the input is no then current node is updated to it's right node
                        currentNode = currentNode.getRightChild();
                        break;
                    } else {
                        System.out.println("Enter Valid Answer");
                    }
                }
            }

            // presenting the guess data at the leaf node to the user
            System.out.println("The Guess: " + currentNode.getData());
            System.out.print("Correct? (Y/N): ");
            userInput = obj.next();

            // loop repeates the choice until correct input is enterd
            while (true) {

                if (userInput.compareToIgnoreCase("Y") == 0) {
                    // if the guess was corrent then exits the loop and go to the end
                    break;

                } else if (userInput.compareToIgnoreCase("N") == 0) {

                    // if the guess was incorrect
                    // correct guess and distinguishable question and other guess is got from user
                    System.out.print("I don't Know! \nEnter your answer (Yes option): ");
                    correctAns = "Is it "+obj.next()+ "?";

                    System.out.print("Enter Distinguishable Question: ");
                    /*
                    I have used another scanner object "scan" to get the distinguishable question because
                    when I try to get the 2 inputs with obj.nextLine() statement then one of the questions is
                    skipped from getting user's input. Using 2 scanner object only solves this problem
                     */
                    question = scan.nextLine();

                    System.out.print("Enter value for No option: ");
                    NoAns = obj.next();

                    // creating nodes with the answers input from the user
                    BinaryNode<String> YesNode = new BinaryNode<>(correctAns);
                    BinaryNode<String> NoNode = new BinaryNode<>(NoAns);
                    // replacing the leaf node (node containing the guess) with question
                    currentNode.setData(question);
                    // setting right node to the user's input Yes answer
                    currentNode.setRightChild(NoNode);
                    // setting the left node to user's input No answer
                    currentNode.setLeftChild(YesNode);

                    break;

                } else {
                    System.out.println("Enter Valid Answer");

                }
            }


                correctChoice = false;

                // loop runs until correct choice is entered
                while (correctChoice == false) {

                    // getting input from user after ending the game
                    System.out.println("---------------- \n1 - Play Again \n2 - Save Game Data and Play Again \n3 - Load Saved Tree and Play \n4 - Save and Quit \n5 - Print Tree and Quit \n6 - Quit without Saving");
                    System.out.print("Enter your choice :");
                    choice = obj.nextInt();
                    System.out.println("----------------\n");

                    switch (choice) {

                        case 1:
                            // returns to the start of the game
                            correctChoice = true;
                            play = true;

                            break;

                        case 2:
                            // saves the tree data using saveTree() method and returns to start of the game
                            //correctChoice = true;
                            //saveTree(tree);
                            //System.out.println("Game Data Saved !");
                            //System.out.println("Reloading game");
                            //break;
                            break;

                        case 3:
                            // tree is loaded from data file and starts the game
                            correctChoice = true;
                            loadTree();
                            System.out.println("Loaded game from stored data");
                            break;

                        case 4:
                            // game data is saved and the program is exited
                            //correctChoice = true;
                            //saveTree(tree);
                            //System.out.println("Game Data Saved !");
                            correctChoice = true;
                            play = false;
                            System.out.println("Exiting");

                            break;

                        case 5:
                            // tree data is printed and program is exited
                            correctChoice = true;
                            play = false;
                            printTree(tree.getRootNode(), 0);

                            break;

                        case 6:
                            // the program is exited
                            correctChoice = true;
                            play = false;

                            break;

                        default:
                            // default condition to handle wrong input
                            correctChoice = false;
                            System.out.println("Enter Correct choice");

                    }
                }
           }
    }
    // populate tree inserts nodes up to 4 levels containing questions and guesses
    public static void populateTree(BinaryTree<String> tree){
        // creating trees with data and joining them with other trees
        BinaryTree<String> tree19 = new BinaryTree<String>("Is it a Wakame Algae?");
        BinaryTree<String> tree18 = new BinaryTree<String>("Is it a Sac Fungi?");
        BinaryTree<String> tree17 = new BinaryTree<String>("Is it a Fungi?",tree18,tree19);
        BinaryTree<String> tree16 = new BinaryTree<String>("Is it Frog?");
        BinaryTree<String> tree15 = new BinaryTree<String>("Is it a Shark?");
        BinaryTree<String> tree14 = new BinaryTree<String>("Is it a Ferns?");
        BinaryTree<String> tree13 = new BinaryTree<String>("Is it a pincuchion Moss?");
        BinaryTree<String> tree12 = new BinaryTree<String>("Is it Non-vascular?",tree13,tree14);
        BinaryTree<String> tree11 = new BinaryTree<String>("Is it Lion?");
        BinaryTree<String> tree10 = new BinaryTree<String>("Is it Cow?");
        BinaryTree<String> tree9  = new BinaryTree<String>("Is it a penguin?");
        BinaryTree<String> tree8  = new BinaryTree<String>("Is it a Eagle?");
        BinaryTree<String> tree7  = new BinaryTree<String>("Can it fly?",tree8,tree9);
        BinaryTree<String> tree6  = new BinaryTree<String>("Is it fish?",tree15,tree16);
        BinaryTree<String> tree5  = new BinaryTree<String>("Is it a Herbivorous?",tree10,tree11);
        BinaryTree<String> tree4  = new BinaryTree<String>("Is it a bird?",tree7,tree6);
        BinaryTree<String> tree3  = new BinaryTree<String>("Is it a plant?",tree12,tree17);
        BinaryTree<String> tree2  = new BinaryTree<String>("Is it a mammal?",tree5,tree4);
        // joining the tree with root node
        tree.setTree("Are you thinking of an animal?",tree2,tree3);

    }
    // saveTree() funtion save the tree data of the Binary tree passes in the arguments in a .txt file
    public static void saveTree(BinaryTree tree) throws IOException{

        // the tree is serialized and stored as a string
        StringBuilder str = new StringBuilder();
        serializeBinaryTree(tree.getRootNode(),str);
        String treeData = str.toString();
        // creating a .txt file
        Path path = Paths.get(FILE_NAME);
        //converting string to bytes array
        byte[] strToBytes= treeData.getBytes();
        // writing the bytes array to the file
        Files.write(path, strToBytes);

    }
    // serializeBinaryTree() is a recurssion function that does preorder travesal and store the node data as a single string seperated by ","
    private static void serializeBinaryTree(BinaryNodeInterface root, StringBuilder str) {

        if (root == null) {
            str.append("null").append(",");
        } else {
            // adding root value to the string
            str.append(root.getData()).append(",");
            // getting and adding left child
            serializeBinaryTree(root.getLeftChild(), str);
            // getting and adding right child
            serializeBinaryTree(root.getRightChild(), str);
        }

    }
    // loadTree() function returns BinaryTree decoded from the file data saved
    public static BinaryNodeInterface loadTree() throws IOException{

        // getting String value from the saved data file
        Path path_preorder = Paths.get(FILE_NAME);
        String treeData = Files.readAllLines(path_preorder).get(0);

        // getting rootNode after decoding and building tree from the string data
        BinaryNodeInterface rootNode = deserializeBinaryTree(treeData);

        return rootNode;

    }

    // deserializeBinaryTree() returns the root node of the tree created
    public static BinaryNode deserializeBinaryTree(String data){

        // initalizing array to store node data from the saved file
        Deque<String> nodes = new LinkedList<>();
        // seperating the strings by detecting the seperator "," and storing in array list
        nodes.addAll(Arrays.asList(data.split(",")));
        // returning rootnode created by buildTree() function
        return buildTree(nodes);

    }
    // buildTree() function build a Binary tree from array list which contains tree node in preorder traversal
    // Double ended queue (deque) is used to store the node values
    private static BinaryNode buildTree(Deque<String> nodes) {
        // the node value from the queue is stored as a string and then removed from queue
        String data = nodes.remove();
        // if the string value is null it means that is a lead node so null is returned
        if (data.equals("null"))
            return null;
        else {
            // there is a value present
            // a new node is created with the value of the data string
            BinaryNode node = new BinaryNode(data);
            // setting left child using recursion
            node.setLeftChild(buildTree(nodes));
            // setting right child using recursion
            node.setRightChild(buildTree(nodes));
            // returning the node after adding all the children
            return node;
        }
    }

    // dataExitst() method check whether the tree data file is present
    public static boolean dataExist(){

        Path path = Paths.get(FILE_NAME);
        // returns true if file present else false
        return Files.exists(path);

    }
    // printing tree representation in horizontal and this is a recursive function
    static void printTree(BinaryNodeInterface root, int space)
    {
        // Base case
        if (root == null)
            return;

        // increasing space between levels
        space += COUNT;

        // priting right child first
        printTree(root.getRightChild(), space);

        // Printing space
        for (int i = COUNT; i < space; i++) {
            System.out.print("  ");
        }
        // printing current node value
        System.out.print("-->  "+root.getData() + "\n");
        // printing left child
        printTree(root.getLeftChild(), space);
    }
}