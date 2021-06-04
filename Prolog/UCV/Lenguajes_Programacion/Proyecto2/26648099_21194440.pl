distancia([9,_,_,_,_,_,_,_,_],S):- S is 4.
distancia([_,9,_,_,_,_,_,_,_],S):- S is 3.
distancia([_,_,9,_,_,_,_,_,_],S):- S is 2.
distancia([_,_,_,9,_,_,_,_,_],S):- S is 3.
distancia([_,_,_,_,9,_,_,_,_],S):- S is 2.
distancia([_,_,_,_,_,9,_,_,_],S):- S is 1.
distancia([_,_,_,_,_,_,9,_,_],S):- S is 2.
distancia([_,_,_,_,_,_,_,9,_],S):- S is 1.
distancia([_,_,_,_,_,_,_,_,9],S):- S is 0.

esMenor(9,_,S):- S is 1,!.
esMenor(A,B,S):- A<B, S is 0,!.
esMenor(A,B,S):- A>B, S is 1.

impar(X):- R is X mod 2, R==0.

tiempoTotal(_,[],0).
tiempoTotal(Y,[X|XS],S):- esMenor(Y,X,S1), tiempoTotal(Y,XS,S2), S is S2 + S1.

tiempo([],0).
tiempo([X|XS],S):- tiempoTotal(X,XS,S2), tiempo(XS,S3), S is S2+S3.

medir(Res,Temp):- tiempo(Res,S1), distancia(Res,S2), Temp is S1+S2.

mezclar([X,Y,Z],Temp):- append(X,Y,W), append(W,Z,Temp).

esResoluble([]):- fail,!. /*SI ES VACIA*/
esResoluble([_|[]]):- fail,!. /*SI SOLO TIENE 1 ELEMENTO*/
esResoluble([_,_|[]]):- fail,!. /*SI SOLO TIEN 2 ELEMENTOS*/
esResoluble([_,_,_|_]):- fail,!. /*SI TIENE MAS DE 3 ELEMENTOS*/
esResoluble(X):- mezclar(X,Temp), medir(Temp,Temp2), impar(Temp2),!.

final([[1,2,3],[4,5,6],[7,8,9]]).

mover([[9,X1,X2],[X3,X4,X5],[X6,X7,X8]],[[X1,9,X2],[X3,X4,X5],[X6,X7,X8]]). 
mover([[9,X1,X2],[X3,X4,X5],[X6,X7,X8]],[[X3,X1,X2],[9,X4,X5],[X6,X7,X8]]).

mover([[X1,9,X2],[X3,X4,X5],[X6,X7,X8]],[[9,X1,X2],[X3,X4,X5],[X6,X7,X8]]).
mover([[X1,9,X2],[X3,X4,X5],[X6,X7,X8]],[[X1,X2,9],[X3,X4,X5],[X6,X7,X8]]).
mover([[X1,9,X2],[X3,X4,X5],[X6,X7,X8]],[[X1,X4,X2],[X3,9,X5],[X6,X7,X8]]).

mover([[X1,X2,9],[X3,X4,X5],[X6,X7,X8]],[[X1,9,X2],[X3,X4,X5],[X6,X7,X8]]).
mover([[X1,X2,9],[X3,X4,X5],[X6,X7,X8]],[[X1,X2,X5],[X3,X4,9],[X6,X7,X8]]).

mover([[X1,X2,X3],[9,X4,X5],[X6,X7,X8]],[[9,X2,X3],[X1,X4,X5],[X6,X7,X8]]).
mover([[X1,X2,X3],[9,X4,X5],[X6,X7,X8]],[[X1,X2,X3],[X4,9,X5],[X6,X7,X8]]).
mover([[X1,X2,X3],[9,X4,X5],[X6,X7,X8]],[[X1,X2,X3],[X6,X4,X5],[9,X7,X8]]).

mover([[X1,X2,X3],[X4,9,X5],[X6,X7,X8]],[[X1,9,X3],[X4,X2,X5],[X6,X7,X8]]).
mover([[X1,X2,X3],[X4,9,X5],[X6,X7,X8]],[[X1,X2,X3],[X4,X7,X5],[X6,9,X8]]).
mover([[X1,X2,X3],[X4,9,X5],[X6,X7,X8]],[[X1,X2,X3],[9,X4,X5],[X6,X7,X8]]).
mover([[X1,X2,X3],[X4,9,X5],[X6,X7,X8]],[[X1,X2,X3],[X4,X5,9],[X6,X7,X8]]).

mover([[X1,X2,X3],[X4,X5,9],[X6,X7,X8]],[[X1,X2,9],[X4,X5,X3],[X6,X7,X8]]).
mover([[X1,X2,X3],[X4,X5,9],[X6,X7,X8]],[[X1,X2,X3],[X4,X5,X8],[X6,X7,9]]).
mover([[X1,X2,X3],[X4,X5,9],[X6,X7,X8]],[[X1,X2,X3],[X4,9,X5],[X6,X7,X8]]).

mover([[X1,X2,X3],[X4,X5,X6],[9,X7,X8]],[[X1,X2,X3],[9,X5,X6],[X4,X7,X8]]).
mover([[X1,X2,X3],[X4,X5,X6],[9,X7,X8]],[[X1,X2,X3],[X4,X5,X6],[X7,9,X8]]).

mover([[X1,X2,X3],[X4,X5,X6],[X7,9,X8]],[[X1,X2,X3],[X4,9,X6],[X7,X5,X8]]).
mover([[X1,X2,X3],[X4,X5,X6],[X7,9,X8]],[[X1,X2,X3],[X4,X5,X6],[X7,X8,9]]).
mover([[X1,X2,X3],[X4,X5,X6],[X7,9,X8]],[[X1,X2,X3],[X4,X5,X6],[9,X7,X8]]).

mover([[X1,X2,X3],[X4,X5,X6],[X7,X8,9]],[[X1,X2,X3],[X4,X5,9],[X7,X8,X6]]).
mover([[X1,X2,X3],[X4,X5,X6],[X7,X8,9]],[[X1,X2,X3],[X4,X5,X6],[X7,9,X8]]).

recorrer(X,N,L):- N>=L, final(X), assert(listo(L)).
recorrer(X,N,L):- N>=L, mover(X,S), L1 is L+1, recorrer(S,N,L1).

rompeCabezas(X,N):- esResoluble(X), recorrer(X,N,0), findall(L,listo(L),S1), write(S1), retractall(listo(_)).