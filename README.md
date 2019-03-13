# GuitarFX 
__Versão:__ _1.6.2_

## Sumário
* Apresentação
* Pré-requisitos
* Instalação
* Licença
* Autoria e contribuições

## Apresentação
O __"GuitarFX"__ é um jogo baseado no lendário
__“Guitar Hero”__, onde o usuário poderá
jogar partidas com uma biblioteca de
músicas adicionadas pelo administrador.
O usuário poderá se cadastrar
usando nome, e-mail e senha. As
partidas gerarão pontos para o usuário,
que será mostrado no ‘Ranking'.
As músicas, os gêneros e os usuários
são mantidos pelo administrador.

[Apresentação completa (PDF)](https://drive.google.com/open?id=10zD6LBiUUX7oDZ-isjOPX65HvNdgtuop)

## Pré-requisitos
#### Desenvolvimento
* [Java SE Development Kit 8](https://www.oracle.com/technetwork/pt/java/javase/downloads/jdk8-downloads-2133151.html)
* Servidor com banco SQL
* [Documentação](https://drive.google.com/open?id=1qvVTaav2JIoMOsL_ShBUva4JS3Zbxd2s)

#### Versão de Uso
* [Java Runtime Environment](https://www.java.com/pt_BR/download/)

## Instalação
### Desenvolvimento
#### Configurando o banco
###### Execute o código SQL de [criação do banco](https://drive.google.com/open?id=1hyXNwxthoiR_kh_grLlt2QsmeuVhROBD) para criar as tabelas necessárias.
###### Você pode usar o script de [povoamento do banco](https://drive.google.com/open?id=1NcUwG56QIxMTWyCUJfD2SF6i1vjQy0Bt).
###### Após criar as tabelas, utilize os script funcionais listados abaixo:
* [Funções](https://drive.google.com/open?id=1Yx7rKpPBskrI1y9pmk53KEkSUe3zqQCW)
* [Stored Procedures](https://drive.google.com/open?id=1avRbkQMCXpp-aZEtgUA1sCKBD9vda8TK)
* [Visões](https://drive.google.com/open?id=1YGPYrs4Ajnl5Uk6cmW-TMykRP-nejOPB)
* [Event]()

_Para uma descrição mais detalhada de cada script ou possíveis problemas, veja a [documentação]() do banco._

#### Abrindo o projeto
###### Abra a sua __IntelliJ IDEA__ (recomendado), clique em __"Check out from Version Control"__.
![](https://drive.google.com/uc?id=1cRSgvv18rE2Q5kLAQZ7UkvgdauYGqnRf)

###### Copie o link do repositorio e clique em __"Clone"__.
![](https://drive.google.com/uc?id=1JskosTBOgKaAuxgYRzX6hhSNxbNcHNul)

#### Conectando a aplicação ao banco
###### Com o banco configurado, abra a classe __"FabricaConexao.java"__ e configure o trecho de código abaixo:
``` java
public class FabricaConexao {

    //private static Connection con;
    private static final String DRIVER = "com.mysql.cj.jdbc.Driver"; //Driver do JDBC
    private static final String USER = "USER"; //Usuário do banco
    private static final String PASSWORD = "PASS"; //Senha do banco
    private static final String URL = "URL DATA BASE"; //URL do banco
    
}
```
#### Configurando as bibliotecas e dependências
###### No Intellij, abra __"File"__ e __"Project Structure..."__. Na aba __"Modules"__ e __"Dependencies"__. Clique no ícone de __"+"__, __"JARs or Directories..."__. Selecione a pasta __"lib"__ do projeto, clique em __"Apply"__ e __"OK"__.
![](https://drive.google.com/uc?id=16rKaSZyjbyb2AHNE9Ujg5r7a-rEbi3_u)

#### Versão de Uso
* [JAR - Google Drive](veverv)
* [JAR - Mega](everv)

## Licença
#### [Informar]

## Autoria e contribuições
#### [Informar]


