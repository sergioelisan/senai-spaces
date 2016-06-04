SENAI-SPACES
============

Sobre
-----

Meu primeiro projeto com meus colegas da turma 1INFO07.1, em 2008. O início  
da minha carreira na programação. Esse projeto está praticamente intacto,  
com o mesmo código de 2008

Como usar
---------

1) Instale o MySQL, crie três tabelas (TB_MANHA, TB_TARDE, e TB_NOITE) com  
os seguintes campos:  

- matricula varchar(64)
- nome varchar(64)
- disciplina varchar(64)
- turma varchar(64)
- sala varchar(64)

Todas as tabelas são iguais.

2) Dẽ privilégios no MySQL ao usuário **sspaces_server**, senha **1nf0071**

3) Existem 3 executáveis:

- Server
- Mapa
- Cadastro

O IP do servidor é o da máquina que ele rodar, a porta é 7000.  

Por padrão, Mapa e Cadastro apontam pra 192.168.0.2, mas pode configurar  
dentro dos aplicativos na aba "Preferências".

O cliente é o Mapa, onde ao ir cadastrando os itens, vai aparecendo conforme  
o usuário for clicando nos botões da tela.

Prints
------

Mapa  

![alt text](https://raw.githubusercontent.com/SergioELisan/senai-spaces/master/src/image/2016-06-04_rbrth.png)

Servidor e cadastro  

![alt text](https://raw.githubusercontent.com/SergioELisan/senai-spaces/master/src/image/2016-06-04_rbrth2.png)
