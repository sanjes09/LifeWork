#include <stdio.h>
#include <string.h>
#include "lib.h"

void trans(char aux[], char t[]){ 	/*Transforma de hex a bin*/
	int i;
	strcpy(aux,"");
	for(i=0;i<8;i++){
		switch (t[i]){
		   case '0':strcat (aux ,"0000");
				    break;
		   case '1':strcat (aux ,"0001");
		   		    break;
		   case '2':strcat (aux ,"0010");
		   			break;
		   case '3':strcat (aux ,"0011");
		   	  		break;
		   case '4':strcat (aux ,"0100");
		   			break;
		   case '5':strcat (aux ,"0101");
		    		break;
		   case '6':strcat (aux ,"0110");
		    		break;
		   case '7':strcat (aux ,"0111");
		    		break;
		   case '8':strcat (aux ,"1000");
		    		break;
		   case '9':strcat (aux ,"1001");
		    		break;
		   case 'A':strcat (aux ,"1010");
		    		break;
		   case 'B':strcat (aux ,"1011");
		    		break;
		   case 'C':strcat (aux ,"1100");
		    		break;
		   case 'D':strcat (aux ,"1101");
		    		break;
		   case 'E':strcat (aux ,"1110");
		    		break;
		   case 'F':strcat (aux ,"1111");
		    		break;
		   case 'a':strcat (aux ,"1010");
		    		break;
		   case 'b':strcat (aux ,"1011");
		    		break;
		   case 'c':strcat (aux ,"1100");
		    		break;
		   case 'd':strcat (aux ,"1101");
		    		break;
		   case 'e':strcat (aux ,"1110");
		    		break;
		   case 'f':strcat (aux ,"1111");
		    		break;
		  }
	}
}

void transform(char finalbin[], char finalhex[]){  /*Transforma de bin a hex*/
	int i,j,x;
	char f[5];
	strcpy(finalhex,"");
	for (i=0;i<32;i+=4){
		strcpy(f,"");
		x=i;
		for (j=0;j<4;j++){
			if(finalbin[x]=='0') strcat(f,"0");
			else strcat(f,"1");
			x++;
		}
			if (strcmp(f,"0000")==0) strcat(finalhex,"0");
			if (strcmp(f,"0001")==0) strcat(finalhex,"1");
			if (strcmp(f,"0010")==0) strcat(finalhex,"2");
			if (strcmp(f,"0011")==0) strcat(finalhex,"3");
			if (strcmp(f,"0100")==0) strcat(finalhex,"4");
			if (strcmp(f,"0101")==0) strcat(finalhex,"5");
			if (strcmp(f,"0110")==0) strcat(finalhex,"6");
			if (strcmp(f,"0111")==0) strcat(finalhex,"7");
			if (strcmp(f,"1000")==0) strcat(finalhex,"8");
			if (strcmp(f,"1001")==0) strcat(finalhex,"9");
			if (strcmp(f,"1010")==0) strcat(finalhex,"A");
			if (strcmp(f,"1011")==0) strcat(finalhex,"B");
			if (strcmp(f,"1100")==0) strcat(finalhex,"C");
			if (strcmp(f,"1101")==0) strcat(finalhex,"D");
			if (strcmp(f,"1110")==0) strcat(finalhex,"E");
			if (strcmp(f,"1111")==0) strcat(finalhex,"F");
	}
}
