DAN ZHANG
dzhang29
LINAN LI
dzhang29@u.rochester.edu
MW 2:00-3:15pm
I did not collaborate with anyone on this assignment.

Compile and run UI.

The main problem in this project is about how react after moving the numbers. And also, while printing the table, need to use the printf() to make sure the width between each number is same.

For the problem of moving numbers, my solution is check each row.For each row, store all the numbers into a stack except for 0s. Then pop out the first number and store into a tempStack. While the stack is not empty, continue pop a number and compare with the number in the tempStack. If the numbers are same, add them and put into a queue. Else, just move the number in the tempStack into the queue, and put the number into tempStack. After all, check if tempStack is empty, and put the last number into the queue. Then add the number in the queue into the array orderly. Compare if this array is the same as the previous array to check if the move is valid.

