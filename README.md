# ASD course playground
Just a funny project.

## Structure of input files
This application has the ability to generate a file with a data structure with 
random numbers in it. All the file formats are listed below:

### Vectors
```bash
cd build/install/ASDPlayground/bin
./ASDPlayground --generate-vector 50 -o vector.txt -m 1 -M 10 -s 42
```
This command generate a file called `vector.txt` that contains `50` random 
numbers, from `1` to `10` using `42` as seed to generate it.

The file format is the follow:
```
n
x1 x2 [...] xn
```
Where `n` is the given length of vector (50 in the example) and `x1 x2 [...] xn`
are all the generated numbers (with the properties see previously in the 
example) separated by space.

### Ordered Vector
```bash
cd build/install/ASDPlayground/bin
./ASDPlayground --generate-ordered-vector 50 25 -o ord-vector.txt -m 1 -s 42
```
This command generate a file called `ord-vector.txt` that contains `50` ordered 
numbers, from `1`, with maximum step between of `25`, using `42` as seed to 
generate it.

The file format is the follow:
```
n
x1 x2 [...] xn
```
Where `n` is the given length of vector (50 in the example) and `x1 x2 [...] xn`
are all the generated numbers separated by space with the property: 
`x1 < x2 < [...] < xn`

### Matrix
```bash
cd build/install/ASDPlayground/bin
./ASDPlayground --generate-matrix 10 10 -o matrix.txt -m 1 -M 10 -s 42
```
This command generate a file called `matrix.txt` that contains a `10x10` matrix
of random numbers, from `1` to `10` using `42` as seed to generate it.

The file format is the follow:
```
m n
x11 [...] x1n
x21 
[...]
xm1 [...] xmn
```
Where `m n` is the given size of matrix (10 10 in the example) and listed below 
that all the elements of matrix: columns separated by space and rows by new line.

### Interval
```bash
cd build/install/ASDPlayground/bin
./ASDPlayground --generate-interval 50 4 -o interval.txt -m -10 -M 10 -s 42
```
This command generate a file called `interval.txt` that contains a  `50` pairs 
of random integers in the format `x y`, where `x` could be from `-10` to `10` 
and `y` could be form `1` to `4` (in this example), so `y > x` is always true.
`x` is generated using `42` as seed (otherwise current time is used), 
differently the the seed used to generate the interval step is always the
current time in milliseconds.

The format file is listed below:
```
n
x1 y1
x2 y2
[...]
xn yn
```
Where `n` is the number of interval listed below.
