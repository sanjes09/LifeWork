.data 
	si:	 .asciiz "\n Alerta! ocurrió un error en la transmisión"
	no: 	 .asciiz "\n No hubo error"
	error:	 .asciiz "Desea insertar un error? (s/n)"
	N:	 .asciiz "n"
	fout:    .asciiz "outfile.txt"
	fin:     .asciiz "infile.txt"
	buffer:  .space 1024
.text

li $v0, 4
la $a0, error
syscall 	#imprime el mensaje de pedir error o no
li $v0, 12
syscall		#lee s 0 n
add $t2, $v0, $0

####### open file ###############
	li $v0, 13
	la $a0, fout
	li $a1, 0
	li $a2, 0
	syscall
	
	add $s7, $0, $v0
####### read from file ##########
	li $v0, 14
	add $a0, $0, $s7
	la $a1, buffer
	li $a2, 1024
	syscall
	
	la $t9, buffer
	li $t1, 0
	li $s4, 0
	li $s5, 0 
	li $s6, 0
	ciclo2: #cuenta cuantos elementos hay
		lb $t1, 0($t9)		#agarra el char
		add $s4, $t1, $s4	#se le suma al ascii total
		addi $t9, $t9, 1	#se mueve el apuntador al siguiente char
		addi $s5, $s5, 1	#cuenta cuantos chares hay
		blez $t1, seguir
		j ciclo2

	seguir:	
	lb $t3, N		#carga el char n	
	la $t9, buffer 		#resetea el apuntador al archivo
	div $s5, $s5, 2		#$s5 igual al acaracter en la mitad del archivo
	add $t9, $s5, $t9	#posiciona $t9 en la mitad del archivo
	beq $t2, $t3, adelante
	lb $s5, 0($t9)		#extrae el char en esa posicion	
	addi $s5, $s5,10	#le suma 10 a ese char
	sb $s5, 0($t9)		#lo guarda en buffer
	adelante:
#### write the file #############
	li $v0, 13
	la $a0, fin
	li $a1, 1
	li $a2, 0
	syscall
	
	add $s6, $0, $v0
	li $v0, 15
	add $a0, $0, $s6 
	la $a1, buffer
	li $a2, 1024
	syscall
	
####### open file ###############
	li $v0, 13
	la $a0, fin
	li $a1, 0
	li $a2, 0
	syscall
	
	add $s7, $0, $v0
####### read from file ##########
	li $v0, 14
	add $a0, $0, $s7
	la $a1, buffer
	li $a2, 1024
	syscall
	
	la $t9, buffer
	li $t1, 0
	li $s6, 0
	ciclo3: #cuenta cuantos elementos hay
		lb $t1, 0($t9)
		add $s6, $t1, $s6	#suma todos los ascii
		addi $t9, $t9, 1
		blez $t1, seguir2
		j ciclo3

	seguir2:
	beq $s4, $s6, noh	#compara las sumas de los ascii y si son iguales salta para imprimir no fue alterado
	li $v0, 4
	la $a0, si
	syscall
	j final
	noh:
	li $v0, 4
	la $a0, no
	syscall
	
	final:
		li $v0, 10
		syscall
