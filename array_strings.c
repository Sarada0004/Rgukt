#include<stdio.h>
int main() {
    int n,d;
    scanf("%d",&n);
    char s[n][10];
    int i,j,count=0;
    for(i=0;i<n;i++){
        scanf("%s",&s[i]);
    }
    scan("%d",&d);
    char f[d][10];
    for(i=0;i<d;i++){
        scanf("%s",&f[i]);
    }
    for(i=0;i<d;i++){
		count=0;
		for(j=0;j<n;j++){
			if(strcmp(f[i],s[j]) == 0){
				count++;
			}
		}
		printf("%d ",count);
    }
    return 0;
}
