module mycircuit (z,a,b,c)
	output z;
	input a,b,c;
	wire and1out;

	and myand1(and1out, a, b);
	and myand2(z, and1out, c);

end module
