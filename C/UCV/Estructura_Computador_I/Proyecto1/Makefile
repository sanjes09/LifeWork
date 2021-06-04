all: CalculadoraBits

CalculadoraBits: proyecto.o transformar.o validar.o
	gcc proyecto.o transformar.o validar.o -o CalculadoraBits
	
proyecto.o: proyecto.c
	gcc -c proyecto.c
	
transformar.o: transformar.c
	gcc -c transformar.c
	
validar.o: validar.c
	gcc -c validar.c
	
clean: 
	rm -rf *o CalculadoraBits
