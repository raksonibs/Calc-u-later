#include <stdio.h>
#include <string.h>

int main()
{
	char s[100];
	int i = 0, a = 0, b = 0, ss;
	fgets(s, 97, stdin);
	printf("working with: %s", s);

	while (i < strlen(s))
	{
		if (s[i] == '1')
		{
			a++;
		}
		else if (s[i] == '0')
		{
			b++;
		}
		i++;
	}
	ss = a - b;
	printf("result: %d\n", ss);

	return 0;
}