// "..." after an operand is a spread, with or without a space before it.
#A: {a: int}
b: #A ...
c: [1 ...]
d: {#A ...}
// After a comma, including one inserted at the end of a line, it is an ellipsis.
e: {
	#A
	...
}
