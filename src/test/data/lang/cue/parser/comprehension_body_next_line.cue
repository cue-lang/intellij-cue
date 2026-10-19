@experiment(try)

x: true
l: [1]
a?: int
b: {
	if x
	{
		c: 1
	}
	for y in l
	{
		d: y
	}
	try z = a?
	{
		e: z
	}
}
// try at the end of a line is a reference, followed here by a struct
try: {f: 1}
g: {
	try
	{h: 2}
}
