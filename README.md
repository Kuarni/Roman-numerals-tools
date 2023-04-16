# Roman-numerals-tools
It's SRC Quality Assurance and Test Automation for Code Analysis Tools test task

## validator 
The isValid function gets one string argument - Roman number.\
The function checks that in the number uses only correct Latin characters (**I, i, V, v, X, x, L, l, C, c, D, d, M, m**)
or Unicode Roman numerals (2160 - 2188, except 2083 (**Ↄ**)) or early Roman numerals (**Φ, ⊗, ⊕, Θ**).\
The function also checks that before the number can be only one digit that is less than the next.\
Numbers with a line above or below them are not permitted.\
If all are so, returns true, false otherwise.\
If string is shorter than 1 or greater than 15, throws error.

## converter
The converter function gets one string argument - Roman number.\
If Roman number satisfies the function isValid, returns the Arabic notation of the Roman number.
Throws error, otherwise.


In the program using symbols from Unicode 5.1