

#include <stdio.h>
#include <string.h>
#include <stdlib.h>

int checkCell(int i, int j, int **a)
{
	int b;
	int flag, counter;
	printf("%d",a[i][j]);

	flag = a[i][j];

	if (a[i-1][j-1] == 1 && a[i-1][j-1] != NULL)counter++;
	if (a[i][j-1] == 1 && a[i][j-1] != NULL)counter++;
	if (a[i+1][j-1] == 1 && a[i+1][j-1] != NULL)counter++;
	if (a[i+1][j] == 1 && a[i+1][j] != NULL)counter++;
	if (a[i+1][j+1] == 1 && a[i+1][j+1] != NULL)counter++;
	if (a[i][j+1] == 1 && a[i][j+1] != NULL)counter++;
	if (a[i-1][j+1] == 1 && a[i-1][j+1] != NULL)counter++;	
	if (a[i-1][j] == 1 && a[i-1][j] != NULL)counter++;

	if (flag == 1)
	{
		if (counter < 2 || counter > 3)
		{
			b = 0;
		}
		else
		{
			b = 1;
		}
	}
	else
	{
		if (counter == 3)
		{
			b = 1;
		}
		else
		{
			b = 0;
		}
	}
	return b;
}

int main()
{
	char line[10]; //string to store the scanned input
	int r, c; //rows, columns
	int **old; //Multidimentional pointer - pointer to pointer
	int **new; //Multidimentional pointer - pointer to pointer

	int *q; //pointer to store values
	int i, j; //loop variables
	int g; //generations
	int checkCell(int i, int j, int **a);
	
	
	fgets(line, 9, stdin); //scan line 
	sscanf(line, "%d %d", &r, &c); //set first decimal to r, second decimal to c
	scanf("%d",&g);
	
	
	old = (int**)malloc(r * sizeof(int*)); //create a memory of rows * int memory
	for(i = 0; i < r; i++)
	{ //create memory for columns
	old[i] = (int*)malloc(c * sizeof(int*));
	}
	
	new = (int**)malloc(r * sizeof(int*)); //create a memory of rows * int memory
	for(i = 0; i < r; i++)
	{ //create memory for columns
	new[i] = (int*)malloc(c * sizeof(int*));
	}
	//storing the values of the board
	
	for(i = 0; i < r; i++)
	{
		for(j = 0; j < c; j++)
		{
			scanf("%d", &old[i][j]); //stores values in their respective locations
		}
	}
	

	for(i = 0; i < r; i++)
	{
		for(j = 0; j < c; j++)
		{
			// goes through every cell
			//printf("%d", *(*(old + i) + j)); //Check Values
			new[i][j] = checkCell(i, j, old);
			
		}
		puts("");
	}
	
	
	
	//This is the output
	for(i = 0; i < r; i++)
	{
		for(j = 0; j < c; j++)
		{
			// goes through every cell
			//printf("%d", *(*(old + i) + j)); //Check Values
			
			
		}
		//puts("");
	}
	
	//q  = (int*)malloc(c * sizeof(int*));
	
	
	/*
	
	//free memory space
	for(i = 0; i < r; i++)
	{
	free(old[i]);	
	}
	free(old);
	*/
	
	return 0;
}