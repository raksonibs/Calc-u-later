#include <stdio.h>
#include <stdlib.h>
#include <string.h>
void main()
{
	// int *a, n,i, sum = 0;
	// printf("enter size: ");
	// scanf("%d", &n);

	// a = calloc(n, sizeof(int));

	// for (i = 0; i < n; i++) scanf("%d", &a[i]);
	// for (i = 0; i < n; i++) sum+=a[i];

	// printf("sum of all elements: %d, number of elements without free: %d\n", n, sum);

	// free(a); //frees up any unused space for perfect sized array

	// printf("sum of all elements: %d, number of elements with free: %d\n", n, sum);

	char *x[10];
	strcpy(x[1], "hello");
	printf("x[1] is: %s\n",x);

}