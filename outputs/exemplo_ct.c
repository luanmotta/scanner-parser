


// Inclusao de Bibliotecas
#include <stdio.h>
#include <math.h>
#define tamanho 50
// Funcao media que retorna um real

double media (int a) {
int i;
int soma;
int quantidade;
double retorno;
soma = 0;
quantidade = 0;
for ( i = 1 ; i <= a ; i++ ) {
if ( a % i == 0 ) {
quantidade++;
soma = soma + i;
}

}

retorno = soma / quantidade;
return retorno;
}


int soma (int a) {
int i;
int somatorio;
somatorio = 0;
for ( i = 1 ; i <= a ; i++ ) {
if ( a % i == 0 ) somatorio = somatorio + i;
}

return somatorio;
}


int fatorial (int a) {
int i;
int fat;
fat = 1;
for ( i = 1 ; i <= a ; i++ ) {
fat = fat * i;
}

return fat;
}


char geraFuncao () {
int valor;
valor = rand() % 10;
if ( valor <= 3 ) {
return 'm';
}
else  {
if ( valor <= 6 ) {
return 's';
}
else  {
return 'f';
}

}

}


int geraValores () {
int retorno;
retorno = (rand() % 10 + 1);
return retorno;
}


int main() {
char funcoes[tamanho];
int valores[tamanho];
double resultado[tamanho];
int i;
i = 0;
while ( i < tamanho ) {
funcoes[i] = geraFuncao();
valores[i] = geraValores();
i++;
}

i = 0;
do  {
switch ( funcoes[i] ) {
case 'm':
{
resultado[i] = media(valores[i]);
break;
}

case 's':
{
resultado[i] = soma(valores[i]);
break;
}

case 'f':
{
resultado[i] = fatorial(valores[i]);
break;
}

}

i++;
}
while ( i >= tamanho );

/* A parte final é só para verificar o resultado */
for ( i = 0 ; i < tamanho ; i++ ) {
printf("%d ", valores[i]);
printf("%c ", funcoes[i]);
printf("%f\n", resultado[i]);
}

return 0;
}


