/*	Step	4.	Define	your	ASM	function	here:	
* 				in	ASM	we	call	the	function 				asmfunction
* 				In	C	we	call	the	function 				void	asmfunction(int	myvariable)
*	
*										When	the	C	main	function	calls	asmfunction	it	will	pass	a	variable
* 				to	asmfunction.		This	variable,	myvariable,	will	be	stored	in	
* 				Register	a0	and	is	accessible	within	the	assembler	function.
*	
*/
				.global	asmfunction					/*	Global,	So	that	the	C	code	can	find	it	*/
.ent				asmfunction									/*	"entry"	point	for	the	function,	as	is	required
																													*	for	the	executable	ELF	file	that	is	produced
					*	during	compiling.*/
asmfunction:																/*	Assembler	label	*/
				/*	Step	4a.	Function	Prologue	(start)	*/
				addiu			sp,	sp,	-4 				/*	change	the	stack	value	by	4	bytes.	*/
				sw						s0,	0(sp)							/*	Store	contents	of	register	s0	onto	new	stack	address	*/
				
				/*	Step	4b.	Body	of	the	Function	*/
				nop 				/*	no	operation.		Do	nothing.		Usually,	you	would
					*	want	to	put	a	task	in	here.	*/
				
				/*	Step	4c.	Function	Epilogue	(wrap	up)	*/
				lw						s0,	0(sp) 				/*	Return	value	from	stack	into	register	s0.	*/
				addiu			sp,	sp,	4 				/*	The	stack	is	back	to	its	original	state.	*/
				
				/*	Step	4d.	Return	to	caller	(the	C	function	that	called	us	here)	*/
				jr		ra																		/*	jump	to	return	address	(found	in	ra	register)	*/
				nop 				/*	no	operation	*/
.end	asmfunction												/*	This	is	the	end	of	the	assembler	function.	*/