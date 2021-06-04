#20 de Marzo del 2017
#Jesús Martínez 
#21.194.440
#Calduladora de binarios y hexadecimales

.section .data
	over: .asciz "--Overflow\n"
	under: .asciz "--Underflow\n"
	sp: .asciz "-----------\n"
	ch: .asciz "--Selecciona base\n"
	error: .asciz "Base no seleccionada, ejecute 'setsys X' para cambiarla\n"
	base: .asciz "Base erronea, debe ser 2 o 16\n"
	bin: .asciz "--cambiando a sistema en base 2\n"
	hex: .asciz "--cambiando a sistema en base 16\n"
	sh: .asciz ">> "
	exit: .asciz "exit"
	ad: .asciz "add"
	su: .asciz "sub"
	an: .asciz "and"
	oo: .asciz "or"
	xo: .asciz "xor"
	set: .asciz "setsys"
	chs: .asciz "chsys"
	cero: .byte '0'
	uno: .byte '1'
	dos: .byte '2'
	tres: .byte '3'
	cuatro: .byte '4'
	cinco: .byte '5'
	seis: .byte '6'
	siete: .byte '7'
	ocho: .byte '8'
	nueve: .byte '9'
	a: .byte 'a'
	b: .byte 'b'
	c: .byte 'c'
	d: .byte 'd'
	e: .byte 'e'
	f: .byte 'f'
	cerob: .asciz "0000"
	unob: .asciz "0001"
	dosb: .asciz "0010"
	tresb: .asciz "0011"
	cuatrob: .asciz "0100"
	cincob: .asciz "0101"
	seisb: .asciz "0110"
	sieteb: .asciz "0111"
	ochob: .asciz "1000"
	nueveb: .asciz "1001"
	ab: .asciz "1010"
	bb: .asciz "1011"
	cb: .asciz "1100"
	db: .asciz "1101"
	eb: .asciz "1110"
	fb: .asciz "1111"
	mode: .long 0
	res: .long 0
	menu: .asciz "aaaaaa"
	leer: .asciz "%s"
	print: .asciz "%s\n"
	print2: .asciz "  %s\n"
	leern: .asciz "%i"
	salto: .asciz "\n"
	Sadd: .asciz "+ "
	Sor: .asciz "| "
	Sand: .asciz "& "
	Sxor: .asciz "^ "
	aux: .asciz "00000001"
.section .bss
	.comm n1,10,1
	.comm n2,10,1
	.comm resultado,10,1
	.comm hex1,10,1
	.comm hex2,10,1
	.comm hex3,10,1
	.comm bin1,10,1
	.comm bin2,10,1
.section .text
.globl _start
_start:
	
do:	
	#>>
	leal sh, %eax
	pushl %eax
	call printf
	addl $4, %esp

	#Lee menu
	leal menu, %eax
	pushl %eax
	leal leer,%eax
	pushl %eax
	call scanf
	addl $8, %esp

	movl menu, %eax
	#compara menu con setsys
	movl set, %ebx
	cmpl %eax, %ebx
	je setsys
	#compara menu con chsys
	movl chs, %ebx
	cmpl %eax, %ebx
	je chsys
	#verifica la base
	cmpl $2, mode
	je si1b
	cmpl $16, mode
	je si1x
	jmp err
	
	si1b:
		#lee binarios
		pushl %eax
		call leer_binario
		popl %eax
		jmp valido
	si1x:
		#lee los hexadecimales
		pushl %eax
		call leer_hexa
		popl %eax
		jmp valido
	err:
		#imprime error
		pushl $error
		call printf
		addl $4, %esp
		jmp ciclo

	valido:

	#compara menu con add
	movl ad, %ebx
	cmpl %eax, %ebx
	je ADD
	#compara menu con sub
	movl su, %ebx
	cmpl %eax, %ebx
	je SUB
	#compara menu con and
	movl an, %ebx
	cmpl %eax, %ebx
	je AND
	#compara menu con or
	movw oo, %bx
	cmpw %ax, %bx
	je OR
	#compara menu con xor
	movl xo, %ebx
	cmpl %eax, %ebx
	je XOR
	

	jmp ciclo

	ADD:#suma

		#imprime la operacion
		leal Sadd, %eax
		pushl %eax
		call printf
		addl $4, %esp

		#efectua la comparacion
		movl $7, %ebx
		movl $0, res
		add1:
			movl $0, %ecx

			#compara digito de n1 con 1
			movb n1(%ebx), %al	
			movb uno, %dl		
			cmp %dl, %al
			jne no1
			incl %ecx
		no1:
			#compara digito de n2 con 1
			movb n2(%ebx), %al
			movb uno, %dl
			cmp %dl, %al
			jne no2
			incl %ecx
		no2:
			addl res, %ecx
			cmpl $0, %ecx
			je s0
			cmpl $1, %ecx
			je s1
			cmpl $2, %ecx
			je s2
			cmpl $3, %ecx
			je s3

		s0:
			movb cero, %al
			movb %al, resultado(%ebx)
			movl $0, res
			jmp FADD
		s1:
			movb uno, %al
			movb %al, resultado(%ebx)
			movl $0, res
			jmp FADD
		s2:
			movb cero, %al
			movb %al, resultado(%ebx)
			movl $1, res
			jmp FADD
		s3:
			movb uno, %al
			movb %al, resultado(%ebx)
			movl $1, res

		FADD:
			decl %ebx
			cmpl $0, %ebx
			jge add1

		#si es hexadeciaml se salta imprimir binarios
		cmpl $16, mode
		je hexa1

		#imprime resultado en binario
		call imprimir_respuesta_bin

		jmp bina1

	hexa1:
		#imprime resultado en hexa
		call imprimir_respuesta_hexa

	bina1:
		#comprueba si hay overflow
		movl $0, %ebx
		movb n1(%ebx), %al
		movb n2(%ebx), %dl
		cmpb %al, %dl
		jne ciclo

		movb resultado(%ebx), %dl
		cmpb %al, %dl
		je ciclo
		cmpb cero, %dl
		je UNDER
	OVER:
		leal over, %eax
		pushl %eax
		call printf
		addl $4, %esp
		jmp asd

	UNDER:
		leal under, %eax
		pushl %eax
		call printf
		addl $4, %esp

	asd:
		#imprime salto de linea
		leal salto, %eax
		pushl %eax
		call printf
		addl $4,%esp
		jmp ciclo
			
	SUB:#resta

		#invierte digitos de n2
		movl $0, %ebx
		inv:
			movb n2(%ebx), %al	
			movb uno, %dl		
			cmp %dl, %al
			je un

			movb %dl, n2(%ebx)
			jmp finv

		un:
			movb cero, %dl
			movb %dl, n2(%ebx)
		finv:
			incl %ebx
			cmpl $7, %ebx
			jle inv

		#le suma uno a n2
		movl $7, %ebx
		movl $0, res
		res1:
			movl $0, %ecx

			#compara digito de n2 con 1
			movb n2(%ebx), %al	
			movb uno, %dl		
			cmp %dl, %al
			jne no11
			incl %ecx
		no11:
			#compara digito de aux con 1
			movb aux(%ebx), %al
			movb uno, %dl
			cmp %dl, %al
			jne no22
			incl %ecx
		no22:
			addl res, %ecx
			cmpl $0, %ecx
			je s00
			cmpl $1, %ecx
			je s11
			cmpl $2, %ecx
			je s22
			cmpl $3, %ecx
			je s33

		s00:
			movb cero, %al
			movb %al, n2(%ebx)
			movl $0, res
			jmp fres1
		s11:
			movb uno, %al
			movb %al, n2(%ebx)
			movl $0, res
			jmp fres1
		s22:
			movb cero, %al
			movb %al, n2(%ebx)
			movl $1, res
			jmp fres1
		s33:
			movb cero, %al
			movb %al, n2(%ebx)
			movl $1, res

		fres1:
			decl %ebx
			cmpl $0, %ebx
			jge res1

		jmp ADD

	AND:	#and

		#imprime la operacion
		leal Sand, %eax
		pushl %eax
		call printf
		addl $4, %esp
	
		#efectua la comparacion
		movl $0, %ebx
		nd1:
			#compara digito de n1 con 1
			movb n1(%ebx), %al	
			movb uno, %dl		
			cmp %dl, %al
			jne falso5

			#compara digito de n2 con 1
			movb n2(%ebx), %al
			movb uno, %dl
			cmp %dl, %al
			jne falso5
			
			#guarda 1 en respuesta
			movb uno, %al
			movb %al, resultado(%ebx)
			jmp FAND

			#guarda 0 en respuesta
		falso5:
			movb cero, %al
			movb %al, resultado(%ebx)

		FAND:
			incl %ebx
			cmpl $7, %ebx
			jle nd1

		cmpl $16, mode
		je andh

		#imprime resultado e binario
		call imprimir_respuesta_bin
		jmp ciclo

	andh:
		#imprime respuesta hexadecimal
		call imprimir_respuesta_hexa
		jmp ciclo

	OR:	#orr

		#imprime la operacion
		leal Sor, %eax
		pushl %eax
		call printf
		addl $4, %esp
	
		#efectua la comparacion
		movl $0, %ebx
		or1:
			#compara digito de n1 con 1
			movb n1(%ebx), %al	
			movb uno, %dl		
			cmp %dl, %al
			je true7

			#compara digito de n2 con 1
			movb n2(%ebx), %al
			movb uno, %dl
			cmp %dl, %al
			je true7
			
			#guarda 1 en respuesta
			movb cero, %al
			movb %al, resultado(%ebx)
			jmp for1

			#guarda 0 en respuesta
		true7:
			movb uno, %al
			movb %al, resultado(%ebx)

		for1:
			incl %ebx
			cmpl $7, %ebx
			jle or1

		cmpl $16, mode
		je orh

		#imprime resultado binario
		call imprimir_respuesta_bin
		jmp ciclo
		
	orh:
		#imprime respuesta hexadecimal
		call imprimir_respuesta_hexa
		jmp ciclo

	XOR:	#call xorr

		#imprime la operacion
		leal Sxor, %eax
		pushl %eax
		call printf
		addl $4, %esp

		#efectua la comparacion
		movl $0, %ebx
		xor1:
			#compara digito de n1 con 1
			movb n1(%ebx), %al	
			movb uno, %dl		
			cmp %dl, %al
			jne n1cero

			#compara digito de n2 con 1
			movb n2(%ebx), %al
			movb uno, %dl
			cmp %dl, %al
			je false9

			#compara digito de n2 con 0
			movb n2(%ebx), %al
			movb cero, %dl
			cmp %dl, %al
			je true9
		n1cero:
			#compara digito de n2 con 1
			movb n2(%ebx), %al
			movb uno, %dl
			cmp %dl, %al
			je true9

			#compara digito de n2 con 0
			movb n2(%ebx), %al
			movb cero, %dl
			cmp %dl, %al
			je false9

			#guarda 1 en respuesta
		false9:
			movb cero, %al
			movb %al, resultado(%ebx)
			jmp fxor1

			#guarda 0 en respuesta
		true9:
			movb uno, %al
			movb %al, resultado(%ebx)

		fxor1:
			incl %ebx
			cmpl $7, %ebx
			jle xor1

		cmpl $16, mode
		je xorh

		#imprime resultado e binario
		call imprimir_respuesta_bin
		jmp ciclo

	xorh:
		#imprime respuesta hexadecimal
		call imprimir_respuesta_hexa
		jmp ciclo

	setsys:
		#lee el nuevo sistema
		leal mode, %eax
		pushl %eax
		leal leern, %eax
		pushl %eax
		call scanf
		addl $8, %esp
		#compara si es binario
		movl mode, %eax
		movl $2, %ebx
		cmpl %eax, %ebx
		je bina
		#compara si es hexadecimal
		movl mode, %eax
		movl $16, %ebx
		cmpl %eax, %ebx
		je hexa
		#sino imprime error
		movl $0, mode
		pushl $base
		call printf
		addl $4, %esp
		jmp do

	chsys:
		movl $0, mode
		leal ch, %eax
		pushl %eax
		call printf
		addl $4, %esp
		jmp ciclo
	bina:
		pushl $bin
		call printf
		addl $4, %esp
		jmp ciclo
	hexa:
		pushl $hex
		call printf
		addl $4, %esp

ciclo:
	movl menu, %eax
	movl exit, %ebx
	cmpl %ebx, %eax
	jne do
	jmp Fin


Fin:
	movl $1, %eax
	movl $0, %ebx
	int $0x80

leer_binario:		
	#lee n1
	leal n1, %eax
	pushl %eax
	leal leer, %eax
	pushl %eax
	call scanf
	addl $8, %esp

	#lee n2
	leal n2, %eax
	pushl %eax
	leal leer, %eax
	pushl %eax
	call scanf
	addl $8, %esp

	#imprime salto de linea
	leal salto, %eax
	pushl %eax
	call printf
	addl $4,%esp

	ret

leer_hexa:
	#lee hex1
	leal hex1, %eax
	pushl %eax
	leal leer, %eax
	pushl %eax
	call scanf
	addl $8, %esp

	#lee hex2
	leal hex2, %eax
	pushl %eax
	leal leer, %eax
	pushl %eax
	call scanf
	addl $8, %esp

	#convierte a binario para operar
	leal hex1, %eax
	pushl %eax
	leal n1, %eax
	pushl %eax
	call transformar_hexa_to_bin
	addl $8, %esp

	leal hex2, %eax
	pushl %eax
	leal n2, %eax
	pushl %eax
	call transformar_hexa_to_bin
	addl $8, %esp

	#imprime salto de linea
	leal salto, %eax
	pushl %eax
	call printf
	addl $4,%esp

	ret

imprimir_binario:
	#imprime n1
	leal n1, %eax
	pushl %eax
	leal print, %eax
	pushl %eax
	call printf
	addl $8, %esp

	#imprime n2
	leal n2, %eax
	pushl %eax
	leal print2, %eax
	pushl %eax
	call printf
	addl $8, %esp

	ret

imprimir_respuesta_bin:
	#imprime los binarios
	call imprimir_binario

	#imprime linea
	leal sp, %eax
	pushl %eax
	call printf
	addl $4,%esp

	#imprime resultado
	movl $0, %ebx
	leal resultado, %eax
	pushl %eax
	leal print2, %eax
	pushl %eax
	call printf
	addl $8, %esp

	ret

imprimir_hexa:
	#imprime hex1
	leal hex1, %eax
	pushl %eax
	leal print, %eax
	pushl %eax
	call printf
	addl $8, %esp

	#imprime hex2
	leal hex2, %eax
	pushl %eax
	leal print2, %eax
	pushl %eax
	call printf
	addl $8, %esp

	ret

imprimir_respuesta_hexa:
	#imprime los hexadecimales
	call imprimir_hexa

	#imprime linea
	leal sp, %eax
	pushl %eax
	call printf
	addl $4,%esp

	#agarra los binarios para transformar a hexa
	call llenar_binarios_aux

	#tranforma a hexa
	movl $0,%ebx
	leal bin1, %eax
	pushl %eax
	leal hex3, %eax
	pushl %eax
	call transformar_bin_to_hexa
	addl $8, %esp
	
	movl $1,%ebx
	leal bin2, %eax
	pushl %eax
	leal hex3, %eax
	pushl %eax
	call transformar_bin_to_hexa
	addl $8, %esp

	#imprime respuesta hexadecimal
	leal hex3, %eax
	pushl %eax
	leal print2, %eax
	pushl %eax
	call printf
	addl $8, %esp

	ret

transformar_hexa_to_bin:
	pushl %ebp
	movl %esp, %ebp
	movl 8(%ebp), %edi #n
	movl 12(%ebp), %esi #hex

	movl $0, %ebx
	movl $0, %ecx
transform:
	movb (%esi,%ebx), %al

	movb cero, %dl
	cmpb %dl, %al
	je cero1

	movb uno, %dl
	cmpb %dl, %al
	je uno1

	movb dos, %dl
	cmpb %dl, %al
	je dos1

	movb tres, %dl
	cmpb %dl, %al
	je tres1

	movb cuatro, %dl
	cmpb %dl, %al
	je cuatro1

	movb cinco, %dl
	cmpb %dl, %al
	je cinco1

	movb seis, %dl
	cmpb %dl, %al
	je seis1

	movb siete, %dl
	cmpb %dl, %al
	je siete1

	movb ocho, %dl
	cmpb %dl, %al
	je ocho1

	movb nueve, %dl
	cmpb %dl, %al
	je nueve1

	movb a, %dl
	cmpb %dl, %al
	je a1

	movb b, %dl
	cmpb %dl, %al
	je b1

	movb c, %dl
	cmpb %dl, %al
	je c1

	movb d, %dl
	cmpb %dl, %al
	je d1

	movb e, %dl
	cmpb %dl, %al
	je e1

	movb f, %dl
	cmpb %dl, %al
	je f1

cero1:
	movb cero, %al
	movb %al, (%edi,%ecx)
	incl %ecx
	movb %al, (%edi,%ecx)
	incl %ecx
	movb %al, (%edi,%ecx)
	incl %ecx
	movb %al, (%edi,%ecx)
	incl %ecx

	jmp ftrans
uno1:
	movb cero, %al
	movb %al, (%edi,%ecx)
	incl %ecx
	movb %al, (%edi,%ecx)
	incl %ecx
	movb %al, (%edi,%ecx)
	incl %ecx

	movb uno, %al
	movb %al, (%edi,%ecx)
	incl %ecx
	
	jmp ftrans
dos1:
	movb cero, %al
	movb %al, (%edi,%ecx)
	incl %ecx
	movb %al, (%edi,%ecx)
	incl %ecx

	movb uno, %al
	movb %al, (%edi,%ecx)
	incl %ecx

	movb cero, %al
	movb %al, (%edi,%ecx)
	incl %ecx
	
	jmp ftrans
tres1:
	movb cero, %al
	movb %al, (%edi,%ecx)
	incl %ecx
	movb %al, (%edi,%ecx)
	incl %ecx

	movb uno, %al
	movb %al, (%edi,%ecx)
	incl %ecx
	movb %al, (%edi,%ecx)
	incl %ecx
	
	jmp ftrans
cuatro1:
	movb cero, %al
	movb %al, (%edi,%ecx)
	incl %ecx

	movb uno, %al
	movb %al, (%edi,%ecx)
	incl %ecx

	movb cero, %al
	movb %al, (%edi,%ecx)
	incl %ecx
	movb %al, (%edi,%ecx)
	incl %ecx

	jmp ftrans
cinco1: 
	movb cero, %al
	movb %al, (%edi,%ecx)
	incl %ecx

	movb uno, %al
	movb %al, (%edi,%ecx)
	incl %ecx
	
	movb cero, %al
	movb %al, (%edi,%ecx)
	incl %ecx
	
	movb uno, %al
	movb %al, (%edi,%ecx)
	incl %ecx
	
	jmp ftrans
seis1:
	movb cero, %al
	movb %al, (%edi,%ecx)
	incl %ecx

	movb uno, %al
	movb %al, (%edi,%ecx)
	incl %ecx
	movb %al, (%edi,%ecx)
	incl %ecx

	movb cero, %al
	movb %al, (%edi,%ecx)
	incl %ecx
	
	jmp ftrans
siete1:
	movb cero, %al
	movb %al, (%edi,%ecx)
	incl %ecx

	movb uno, %al
	movb %al, (%edi,%ecx)
	incl %ecx
	movb %al, (%edi,%ecx)
	incl %ecx
	movb %al, (%edi,%ecx)
	incl %ecx
	
	jmp ftrans
ocho1:
	movb uno, %al
	movb %al, (%edi,%ecx)
	incl %ecx

	movb cero, %al
	movb %al, (%edi,%ecx)
	incl %ecx
	movb %al, (%edi,%ecx)
	incl %ecx
	movb %al, (%edi,%ecx)
	incl %ecx			
	
	jmp ftrans
nueve1:
	movb uno, %al
	movb %al, (%edi,%ecx)
	incl %ecx
	
	movb cero, %al
	movb %al, (%edi,%ecx)
	incl %ecx
	movb %al, (%edi,%ecx)
	incl %ecx

	movb uno, %al
	movb %al, (%edi,%ecx)
	incl %ecx
	
	jmp ftrans
a1:
	movb uno, %al
	movb %al, (%edi,%ecx)
	incl %ecx

	movb cero, %al
	movb %al, (%edi,%ecx)
	incl %ecx

	movb uno, %al
	movb %al, (%edi,%ecx)
	incl %ecx

	movb cero, %al
	movb %al, (%edi,%ecx)
	incl %ecx
	
	jmp ftrans
b1:
	movb uno, %al
	movb %al, (%edi,%ecx)
	incl %ecx

	movb cero, %al
	movb %al, (%edi,%ecx)
	incl %ecx

	movb uno, %al
	movb %al, (%edi,%ecx)
	incl %ecx
	movb %al, (%edi,%ecx)
	incl %ecx
	
	jmp ftrans
c1:
	movb uno, %al
	movb %al, (%edi,%ecx)
	incl %ecx
	movb %al, (%edi,%ecx)
	incl %ecx

	movb cero, %al
	movb %al, (%edi,%ecx)
	incl %ecx
	movb %al, (%edi,%ecx)
	incl %ecx
	
	jmp ftrans
d1:
	movb uno, %al
	movb %al, (%edi,%ecx)
	incl %ecx
	movb %al, (%edi,%ecx)
	incl %ecx

	movb cero, %al
	movb %al, (%edi,%ecx)
	incl %ecx

	movb uno, %al
	movb %al, (%edi,%ecx)
	incl %ecx
	
	jmp ftrans
e1:
	movb uno, %al
	movb %al, (%edi,%ecx)
	incl %ecx
	movb %al, (%edi,%ecx)
	incl %ecx
	movb %al, (%edi,%ecx)
	incl %ecx

	movb cero, %al
	movb %al, (%edi,%ecx)
	incl %ecx
	
	jmp ftrans
f1:
	movb uno, %al
	movb %al, (%edi,%ecx)
	incl %ecx
	movb %al, (%edi,%ecx)
	incl %ecx
	movb %al, (%edi,%ecx)
	incl %ecx
	movb %al, (%edi,%ecx)
	incl %ecx

ftrans:
	incl %ebx
	cmpl $1, %ebx
	jle transform
leave
ret

transformar_bin_to_hexa:
	pushl %ebp
	movl %esp, %ebp
	movl 8(%ebp), %edi #hex
	movl 12(%ebp), %esi #bin 

	movl cerob, %eax
	cmpl %eax, (%esi)
	je cero22

	movl unob, %eax
	cmpl %eax, (%esi)
	je uno22

	movl dosb, %eax
	cmpl %eax, (%esi)
	je dos22

	movl tresb, %eax
	cmpl %eax, (%esi)
	je tres22

	movl cuatrob, %eax
	cmpl %eax, (%esi)
	je cuatro22

	movl cincob, %eax
	cmpl %eax, (%esi)
	je cinco22

	movl seisb, %eax
	cmpl %eax, (%esi)
	je seis22

	movl sieteb, %eax
	cmpl %eax, (%esi)
	je siete22

	movl ochob, %eax
	cmpl %eax, (%esi)
	je ocho22

	movl nueveb, %eax
	cmpl %eax, (%esi)
	je nueve22

	movl ab, %eax
	cmpl %eax, (%esi)
	je a22

	movl bb, %eax
	cmpl %eax, (%esi)
	je b22

	movl cb, %eax
	cmpl %eax, (%esi)
	je c22

	movl db, %eax
	cmpl %eax, (%esi)
	je d22

	movl eb, %eax
	cmpl %eax, (%esi)
	je e22

	movl fb, %eax
	cmpl %eax, (%esi)
	je f22

	cero22:
		movb cero, %al
		movb %al, (%edi,%ebx)
		jmp fhtb
	uno22:
		movb uno, %al
		movb %al, (%edi,%ebx)
		jmp fhtb
	dos22:
		movb dos, %al
		movb %al, (%edi,%ebx)
		jmp fhtb
	tres22:
		movb tres, %al
		movb %al, (%edi,%ebx)
		jmp fhtb
	cuatro22:
		movb cuatro, %al
		movb %al, (%edi,%ebx)
		jmp fhtb
	cinco22:
		movb cinco, %al
		movb %al, (%edi,%ebx)
		jmp fhtb
	seis22:
		movb seis, %al
		movb %al, (%edi,%ebx)
		jmp fhtb
	siete22:
		movb siete, %al
		movb %al, (%edi,%ebx)
		jmp fhtb
	ocho22:
		movb ocho, %al
		movb %al, (%edi,%ebx)
		jmp fhtb
	nueve22:
		movb nueve, %al
		movb %al, (%edi,%ebx)
		jmp fhtb
	a22:
		movb a, %al
		movb %al, (%edi,%ebx)
		jmp fhtb
	b22:
		movb b, %al
		movb %al, (%edi,%ebx)
		jmp fhtb
	c22:
		movb c, %al
		movb %al, (%edi,%ebx)
		jmp fhtb
	d22:
		movb d, %al
		movb %al, (%edi,%ebx)
		jmp fhtb
	e22:
		movb e, %al
		movb %al, (%edi,%ebx)
		jmp fhtb
	f22:
		movb f, %al
		movb %al, (%edi,%ebx)
fhtb:
	leave
	ret

llenar_binarios_aux:
	#transforma de binario a hexadecemal
	movl $0, %ebx
	w:
		movb resultado(%ebx), %al
		movb %al, bin1(%ebx)
		incl %ebx
		cmpl $4, %ebx
		jl w
	movl $0, %ecx
	w1:
		movb resultado(%ebx), %al
		movb %al, bin2(%ecx)
		incl %ebx
		incl %ecx
		cmpl $8, %ebx
		jl w1
	ret
	