## 1. Basic Bit Operator 

### 1. Bitwiese And (&)

It takes two equal-length bit patterns as parameters. The two bit integers are compared. If both bits in the compared positions of the bit patterns are 1, then the resulting bit is 1. If not, it is 0.
For example; take two bit values X*X* and Y*Y*, where X = 10 = $(1010)_{2}$ and Y = 9 = $(1001)_{2}$, then X&Y
$$
X\&Y = \frac{(1010)_{2}\\(1001)_{2}}{(1000)_{2}}=8
$$

### 2. Bitwiese Or ( | )

 It takes two equal-length bit patterns as parameters, if both bits in the compared position are 0, the resulting bit is zero. If not, it is 1.
For example; take two bit values X*X* and Y*Y*, where X = 10 = $(1010)_{2}$ and Y = 9 = $(1001)_{2}$, then X|Y
$$
X\vert Y = \frac{(1010)_{2}\\(1001)_{2}}{(1011)_{2}}=11
$$

### 3. Bitwise Xor (^)

This operator stands for exclusive or. If two bits are the same, it return 0.
For example; take two bit values X*X* and Y*Y*, where X = 10 = $(1010)_{2}$ and Y = 9 = $(1001)_{2}$, then X^Y
$$
X\wedge Y = \frac{(1010)_{2}\\(1001)_{2}}{(0011)_{2}}=3
$$

### 4. Bitwise Left Shift (<<)

The left shift operator (<<) shifts the first operand the specified number of bits to the left. Excess bits shifted off to the left are discarded. Left shift is equivalent to multiplying the bit pattern with pow(2,k) ( if we are shifting k bits ).
$$
1 << 1 = 2 = 1*pow(2,1)\\
1 << 2 = 4 = 1*pow(2,2)\\
5 << 1 = 10 = 5*pow(2,1)\\
i << k = i*pow(2,k)
$$

### 5. Bitwise Right Shift (>>)

The right shift operator (>>) shifts the first operand the specified number of bits to the right. Excess bits shifted off to the right are discarded. Right shift is equivalent to dividing the bit pattern by pow(2,k) ( if we are shifting k bits ).
$$
4 >> 1 = 4/pow(2,1)=2\\
6 >> 1 = 6/pow(2,1)=3\\
5 >> 1 = 5/pow(2,1)=2\\
16 >> 4 = 16/pow(2,4)=1\\
$$
For example, x = 50 = $(0110010)_{2}$. 
$$
x << 1: (1100100)_{2}=100\\
x >> 1: (0011001)_{2}=25
$$
left shift: delete the leftmost bit and add one zero to the right
right shift: delete the rightmost bit and add one zero to the left

### 6. Bitwise Not (~)

This operator flips the bits of a number. Convert from 1 to 0 and 0 to 1
For example, x = $(1010)_{2}$, then ~x = $(0101)_{2}$

## 2. Bit Tricks/Hacks

1. **MULTIPLICATION BY POWER OF 2 :** 

   ```python
   num = num << 1 //multiplication  by pow(2,i)
   ```

2. **DIVISION BY POWER OF 2 :-** 

   ```python
   num = num >> i;  //division by pow(2,i)
   ```

3. **CHECK WHETHER NUMBER IS ODD OR EVEN :-**

   ```python
   return "even" if num & 1 == 0 else 'odd'
   ```

4. **TO SWAP TWO NUMBERS :-**

   ```python
   x = x^y
   y = x^y
   x = x^y
   ```

   y = (x ^ y) ^ y = x ^ (y ^ y) = x ^ 0 = x ..... we got y = x 

   x = (x ^ y) ^ x = (x ^ x) ^ y = 0 ^ y = y ..... We got x = y

   **NOTE :-** there are a lot of cons of using xor for swapping. Avoid this technique if x and y are pointers or references to objects, and both point to the same location.
   In some instances it may work slower than the swapping by using third value.

5. **TO CHECK ith BIT IS SET OR NOT OF A NUMBER :-**

   ```python
   x = num & (1<<(i-1))
   return 'set' if x else 'not set'
   ```

   - **Approach** :-
     - suppose we want to check the second bit of 10 (1010) which is 1 ( **bits counting always start from right **) 
     - 10 & (1<<1) so 1<<1 corresponds to 1*pow(2,1)=2=0010(binary)
     - 10 & 2 => 1010 & 0010 = 2 if we ge t non zero, means ith bit is set so here we got 2 means our ith means 2nd bit is set.

6. **TO FLIP ith BIT OF A NUMBER :-**

   ```python
   x = num ^ (1 << (i-1))
   ```

7. **TO TURN ON ith BIT OF A NUMBER :-**

   ```python
   x = num | (1 << (i-1));
   ```

8. **TO TURN OFF ith BIT OF A NUMBER :-**

   ```python
   x = num & ~(1 << (i-1));
   ```

9. **TO CHECK IF A NUMBER IS POWER OF 2 OR NOT :-**

   ```python
   x = num & (num-1)
   return 'power of 2' if not x else 'not'
   ```

   **Approach** :-

   - 8=(1000) , 7=(0111) 16=(10000), 15=(01111) 32=(100000), 31=(011111)
   - from the above cases we can easily notice that , the number which is power of 2 (say n) has only one bit set.
   - and for n-1 all the bits are set except the one which is set in the n. because Subtracting 1 from a decimal number flips all the bits after the rightmost set bit(which is 1) including the rightmost set bit.
   - so if we perform n&(n-1), n=8 , 1000 & 0111 we got 0 in case of power of 2 otherwise 1

10. **TO CONVERT UPPERCASE TO LOWERCASE LETTER :-**

    ```python
    ch = ch | ' '; 
    ```

11. **TO CONVERT LOWERCASE LETTER TO UPPERCASE LETTER :-**

    ```python
    ch = ch & '_'; 
    ```

    **NOTE:-**

    ```python
        //for both lower to upper and upper to lower.
         ch = ch ^ (1 << 5)
    ```

12. **TO FIND MIN MAX OF TWO NUMBERS :-**

    ```python
    minimum = y ^ ((x ^ y) & -(x < y)); // min(x, y) 
    maximum = x ^ ((x ^ y) & -(x < y)); // max(x, y) 
    ```

13.  **TO COUNT ALL SET BITS OF AN NUMBER :-**

    ```python
    count = 0
    while num != 0:
      num = num & (num-1)
      count += 1
    return count 
    ```

14.  **TO COUNT LEADING ZEROS :-**

    ```java
    int count_zeros(int num)
       {
       unsigned y;
       int n = 32;
       y = num >> 16;
       if (y != 0) {
           n = n - 16;
           num = y;
       }
       y = num >> 8;
       if (y != 0) {
           n = n - 8;
           num = y;
       }
       y = num >> 4;
       if (y != 0) {
           n = n - 4;
           num = y;
       }
       y = num >> 2;
       if (y != 0) {
           n = n - 2;
           num = y;
       }
       y = num>> 1;
       if (y != 0)
           return n - 2;
       return n - num;
      }
    ```

    **approach** :-

    - The approach of this one is very simple.
    - we have to simply divide the number if our number is suppose that >= 16 so it is confirmed that the number will require more than 4 bits because by using 4 bits we can create a number in the range 0 to 15.
    - so each time we are dividing the number ith power of 2 (num>>i) if we got quotient(y) !=0 means digits will atleast required i bits so we are substracting the i bits from our 32 bits sets.
    - update num, num=y and repeat.
    - EXAMPLE :- num=16, 16>>4 we got y=1, ans=32-4=28.....then our num will be updated to num=y=1; num=1 ,1>>2 we got y==0, for 1>>1 we got 0 again so return ans-num=28-1 return 27

15. **TO COUNT TRAILING ZEROS :-**

    ```python
    count = 0
    while (x&1)==0:
      x = x >> 1
      count += 1
    return count 
    ```



## 3 Practice Problems

[1178. Number of Valid Words for Each Puzzle](https://leetcode.com/problems/number-of-valid-words-for-each-puzzle/)
[78. Subsets](https://leetcode.com/problems/subsets/)
[169. Majority Element](https://leetcode.com/problems/majority-element/)
[1239. Maximum Length of a Concatenated String with Unique Characters](https://leetcode.com/problems/maximum-length-of-a-concatenated-string-with-unique-characters/)
[393. UTF-8 Validation](https://leetcode.com/problems/utf-8-validation/)
[187. Repeated DNA Sequences](https://leetcode.com/problems/repeated-dna-sequences/)
[1611. Minimum One Bit Operations to Make Integers Zero](https://leetcode.com/problems/minimum-one-bit-operations-to-make-integers-zero/)
[371. Sum of Two Integers](https://leetcode.com/problems/sum-of-two-integers/)
[268. Missing Number](https://leetcode.com/problems/missing-number/)
[1290. Convert Binary Number in a Linked List to Integer](https://leetcode.com/problems/convert-binary-number-in-a-linked-list-to-integer/)

## 4 Interview Questions

1. Find the missing number in an array without using any extra space 
2. Compute modulus division without division and modulo operator 
3. Reverse bits of an integer 
4. Find the odd occurring element in an array 
5. Swap adjacent bits of a number 
6. Print all distinct subsets of a given set 
7. Swap two bits at a given position in an integer 
8. Add binary representation of two integers 
9. Find two duplicate elements in a limited range array (using XOR) 
10. Generate binary numbers between 1 to `n` 
11. Find the missing number and duplicate elements in an array 
12. Round up to the previous power of 2 
13. Round up to the next highest power of 2 
14. Find XOR of two numbers without using the XOR operator 
15. Detect if two integers have opposite signs or not 