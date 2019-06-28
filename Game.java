/*
DAN ZHANG
dzhang29
LINAN LI
dzhang29@u.rochester.edu
MW 2:00-3:15pm
I did not collaborate with anyone on this assignment.
 */

import java.util.*;

public class Game {
    int[][] arr = new int[4][4];
    Random rand = new Random();
    int move = 0;

    //constructor
    public Game() {
        start();
    }

    //randomly generate a new number
    public int randomNum() {
        //80% of time is 2, 20% of time is 4
        if (rand.nextDouble() < 0.80) {
            return 2;
        } else {
            return 4;
        }
    }

    //add number to a random position
    public void addNum(int num) {
        while (true) {
            int i = rand.nextInt(4);
            int j = rand.nextInt(4);

            //check if the place is available
            if (arr[i][j] == 0) {
                arr[i][j] = randomNum();
                break;
            }
        }
    }

    //check if there are space for new number
    public boolean ifSpace() {
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                if (arr[i][j] == 0) {
                    return true;
                }
            }
        }
        return false;
    }

    //print the rule at the beginning of the game
    public void printRules(){
        System.out.println("WELCOME TO 2048!");
        System.out.println();
        System.out.print("Please enter 'w','s','a','d' to move. ");
        System.out.println("Enter 'r' to restart and 'q' to quit.");
    }

    //initialize the game
    public void start() {
        //set everything to 0
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                arr[i][j] = 0;
            }
        }

        //randomly generate 2 or 4 in random place
        addNum(randomNum());
        addNum(randomNum());

        //print the rules
        printRules();
        //print the array
        printArray(arr);
    }

    //restart
    public void restart(){
        refresh();
        //set everything to 0
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                arr[i][j] = 0;
            }
        }

        //randomly generate 2 or 4 in random place
        addNum(randomNum());
        addNum(randomNum());

        System.out.println("NEW GAME!");
        System.out.println();

        //print the rules
        printRules();
        //print the array
        printArray(arr);
    }

    //refresh
    public void refresh(){
        for (int i = 0; i < 20; i++){
            System.out.println();
        }
    }

    //print the array
    public void printArray(int[][] array) {
        for (int i = 0; i < 6; i++){
            System.out.printf("%-5s","-");
        }
        System.out.println();

        for (int i = 0; i < 4; i++) {
            System.out.printf("%-5s","|");
            for (int j = 0; j < 4; j++) {
                if (array[i][j] == 0) {
                    System.out.printf("%-5s","*");
                } else {
                    System.out.printf("%-5s",array[i][j]);
                }
            }
            System.out.printf("%-5s","|");
            System.out.println();
        }

        for (int i = 0; i < 6; i++){
            System.out.printf("%-5s","-");
        }
        System.out.println();
    }

    public static int[][] deepCopy(int[][] array) {

        final int[][] result = new int[array.length][];
        for (int i = 0; i < array.length; i++) {
            result[i] = Arrays.copyOf(array[i], array[i].length);
        }
        return result;
    }

    //movement of the numbers
    public void runningSum2DArray(char dir) {
        //check if the movement is valid
        int[][] temp = deepCopy(arr);

        switch (dir) {
            //left
            case 'a':
                //check each row
                for (int i = 0; i < 4; i++) {
                    //initialize a stack for numbers
                    Stack<Integer> numbers = new Stack<>();
                    Stack<Integer> tempStack = new Stack<>();

                    //initialize a stack for checked numbers
                    Queue<Integer> checkedNum = new ArrayDeque<>();

                    //add numbers into the stack
                    for (int j = 3; j >= 0; j--) {
                        if (arr[i][j] != 0) {
                            numbers.push(arr[i][j]);
                        }
                    }

                    //check if the stack is empty
                    if (!numbers.empty()) {
                        int a = numbers.pop();
                        tempStack.add(a);              //add the first element into tempStack

                        //keep checking until the numbers stack is empty
                        while (!numbers.empty()) {
                            //pop the next number
                            int b = numbers.pop();

                            //compare and add the number
                            if (tempStack.empty()){
                                tempStack.add(b);
                            }else if (tempStack.peek() == b){
                                b += tempStack.pop();
                                checkedNum.add(b);
                            } else {
                                int k = tempStack.pop();
                                checkedNum.add(k);
                                tempStack.add(b);

                            }
                        }


                    }

                    //at last, check if tempStack is empty
                    if (!tempStack.empty()){
                        int t = tempStack.pop();

                        checkedNum.add(t);
                    }


                    //set the array to be 0
                    for (int k = 0; k < 4; k++) {
                        arr[i][k] = 0;
                    }

                    // add the number in the queue to the array
                    int size =  checkedNum.size();
                    for (int t = 0; t < size; t++) {
                        int a = checkedNum.poll();
                        arr[i][t] = a;
                    }
                }

                break;

            //right
            case 'd':
                //check each row
                for (int i = 0; i < 4; i++) {
                    //initialize a stack for numbers
                    Stack<Integer> numbers = new Stack<>();
                    Stack<Integer> tempStack = new Stack<>();

                    //initialize a stack for checked numbers
                    Queue<Integer> checkedNum = new ArrayDeque<>();

                    //add numbers into the stack
                    for (int j = 0; j < 4; j++) {
                        if (arr[i][j] != 0) {
                            numbers.push(arr[i][j]);
                        }
                    }

                    //check if the stack is empty
                    if (!numbers.empty()) {
                        int a = numbers.pop();
                        tempStack.add(a);              //add the first element into tempStack

                        //keep checking until the numbers stack is empty
                        while (!numbers.empty()) {
                            //pop the next number
                            int b = numbers.pop();

                            //compare and add the number
                            if (tempStack.empty()){
                                tempStack.add(b);
                            }else if (tempStack.peek() == b){
                                b += tempStack.pop();
                                checkedNum.add(b);
                            } else {
                                int k = tempStack.pop();
                                checkedNum.add(k);
                                tempStack.add(b);

                            }
                        }


                    }

                    //at last, check if tempStack is empty
                    if (!tempStack.empty()){
                        int t = tempStack.pop();

                        checkedNum.add(t);
                    }


                    //set the array to be 0
                    for (int k = 0; k < 4; k++) {
                        arr[i][k] = 0;
                    }

                    // add the number in the queue to the array
                    int size =  checkedNum.size();
                    for (int t = 0; t < size; t++) {
                        int a = checkedNum.poll();
                        arr[i][3 - t] = a;
                    }
                }
                break;

            //up
            case 'w':
                //check each row
                for (int i = 0; i < 4; i++) {
                    //initialize a stack for numbers
                    Stack<Integer> numbers = new Stack<>();
                    Stack<Integer> tempStack = new Stack<>();

                    //initialize a stack for checked numbers
                    Queue<Integer> checkedNum = new ArrayDeque<>();

                    //add numbers into the stack
                    for (int j = 3; j >= 0; j--) {
                        if (arr[j][i] != 0) {
                            numbers.push(arr[j][i]);
                        }
                    }

                    //check if the stack is empty
                    if (!numbers.empty()) {
                        int a = numbers.pop();
                        tempStack.add(a);              //add the first element into tempStack

                        //keep checking until the numbers stack is empty
                        while (!numbers.empty()) {
                            //pop the next number
                            int b = numbers.pop();

                            //compare and add the number
                            if (tempStack.empty()){
                                tempStack.add(b);
                            }else if (tempStack.peek() == b){
                                b += tempStack.pop();
                                checkedNum.add(b);
                            } else {
                                int k = tempStack.pop();
                                checkedNum.add(k);
                                tempStack.add(b);

                            }
                        }


                    }

                    //at last, check if tempStack is empty
                    if (!tempStack.empty()){
                        int t = tempStack.pop();

                        checkedNum.add(t);
                    }


                    //set the array to be 0
                    for (int k = 0; k < 4; k++) {
                        arr[k][i] = 0;
                    }

                    // add the number in the queue to the array
                    int size =  checkedNum.size();
                    for (int t = 0; t < size; t++) {
                        int a = checkedNum.poll();
                        arr[t][i] = a;
                    }
                }
                break;


            //down
            case 's':
                //check each row
                for (int i = 0; i < 4; i++) {
                    //initialize a stack for numbers
                    Stack<Integer> numbers = new Stack<>();
                    Stack<Integer> tempStack = new Stack<>();

                    //initialize a stack for checked numbers
                    Queue<Integer> checkedNum = new ArrayDeque<>();

                    //add numbers into the stack
                    for (int j = 0; j < 4; j++) {
                        if (arr[j][i] != 0) {
                            numbers.push(arr[j][i]);
                        }
                    }

                    //check if the stack is empty
                    if (!numbers.empty()) {
                        int a = numbers.pop();
                        tempStack.add(a);              //add the first element into tempStack

                        //keep checking until the numbers stack is empty
                        while (!numbers.empty()) {
                            //pop the next number
                            int b = numbers.pop();

                            //compare and add the number
                            if (tempStack.empty()){
                                tempStack.add(b);
                            }else if (tempStack.peek() == b){
                                b += tempStack.pop();
                                checkedNum.add(b);
                            } else {
                                int k = tempStack.pop();
                                checkedNum.add(k);
                                tempStack.add(b);

                            }
                        }


                    }

                    //at last, check if tempStack is empty
                    if (!tempStack.empty()){
                        int t = tempStack.pop();

                        checkedNum.add(t);
                    }


                    //set the array to be 0
                    for (int k = 0; k < 4; k++) {
                        arr[k][i] = 0;
                    }

                    // add the number in the queue to the array
                    int size =  checkedNum.size();
                    for (int t = 0; t < size; t++) {
                        int a = checkedNum.poll();
                        arr[3-t][i] = a;
                    }
                }
                break;
        }

        //check if is valid
        boolean isValid = false;
        for (int i = 0; i < 4; i++){
            for (int j = 0; j < 4; j++){
                if (temp[i][j] != arr[i][j]){
                    isValid = true;
                }
            }
        }

        if (isValid) {
            if (ifSpace()) {
                move++;
                addNum(randomNum());
                refresh();
                printArray(arr);                      //after each valid move check if there are more space
                                                      //if have space, add random number
            } else {
                refresh();
                System.out.println("GAME OVER!");
                System.out.println();
                restart();
            }
        } else {
            refresh();
            System.out.println("INVALID MOVE!");
            System.out.println();
            printArray(arr);
        }
    }

}
