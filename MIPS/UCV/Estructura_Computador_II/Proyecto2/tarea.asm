.data
	es: .asciiz "si es capicua"
	noes: .asciiz "no es capicua"
.text
	li $v0, 5		#carga para leer int
	syscall
	add $s1, $v0, $0 	#guarda la lectura en $s0
	add $s0, $s1, $s0
	li $t0, 10
	li $s2, 0
while:
	beq $s1, $0, probar
	div $s1, $t0
	mflo $s1
	mfhi $s3
	mul $s2, $s2, $t0
	add $s2, $s2, $s3
	j while
	
	
probar:
	beq $s0, $s2, si
no: 
	li $v0, 4
	la $a0, noes
	syscall
	j fin
si:
	li $v0, 4
	la $a0, es
	syscall
fin:
	li $v0, 10
	syscall