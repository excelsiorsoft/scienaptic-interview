Matrix:
 1   2   3   4
 5   6   7   8
 9  10  11  12
13  14  15  16

Print the above matrix in snakey/zigzag form.

Required output: 1 2 3 4 8 12 16 15 14 13 9 5 6 7 11 10
```
     * --------->
     1   2    3    4   |
   ^ 5   6    7    8   |
   | 9   10   11   12  v
   | 13  14   15   16
         <----
```
[Pardon the crap traversal sample!]


Dos/ Notes:
- Use Java/Scala for the solution
- Extra brownie points for JUnit test cases.
- Your solution should "scale" - it should work for 3x3, 4x4, 5x5, etc matrices.
- Your solution should answer : what happens if matrix is of size 2x3, 4x3, 4x1, aka not a "square" matrix.
- We prefer [readable but moderately fast] code to [unreadable mess but blazingly fast] code - so err on the side of human readability.


Donts :
- DO NOT USE third party libraries
- Avoid too much 
  - if-else conditions
  - comments: types that you use should be self explanatory
- DO NOT USE Scanner(System.in) ==> please DO NOT take inputs from commandline/System.in
- yes, you guessed it right, we are not "that" online test platform!


#See tests for solutions:
https://github.com/excelsiorsoft/scienaptic-interview/blob/a44d419ec0615e29fdf4551c311129a351e79e4a/src/test/java/com/excelsiorsoft/mazewalk/MatrixWalkerTest.java#L106
