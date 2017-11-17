# scanner-parser
Compilador: Scanner + Parser usando Jflex e BYacc respectivamente.

### Exemplo para compilar e executar:
`rm -rf *.java *.class; ./yacc.linux -J inicioCT.y; ./jflex-1.6.1/bin/jflex inicioCT.jflex; javac *.java; java Parser teste_ct.txt;`
