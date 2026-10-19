@experiment(try)

a?: int
y: [1]
x: {
	for v in y try
	{b: v}
}
z: {
	if true try
	{a: 1}
}
l: [
	for v in y try
	{c: v},
]
w: {
	try
	{d: 1}
}
