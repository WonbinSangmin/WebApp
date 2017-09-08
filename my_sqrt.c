#include <stdio.h>
#include <math.h>

double my_sqrt(double number){
	double result;
	result = 20;
	for(int i=0; i<1000 ;i++){
		result = 0.5*(result + number/result);
	}
	return result; 

}

int main(int argc, char const *argv[]){
	double integer;
	printf("Tell me non-negative number X: ");
	scanf("%lf", &integer);
	printf("\nsqrt(x) = %10lf, sqrt(x)^2 = %.30e\n"
		"my_sqrt(x) = %10lf, my_sqrt(x)^2 = %.30e\n",
		sqrt(integer), sqrt(integer)*sqrt(integer),
		my_sqrt(integer), my_sqrt(integer)*my_sqrt(integer));
	return 0;
}
