<h1 align="center">Hotel Alura</h1> 

<p align="center">
<img src="https://img.shields.io/github/stars/edielson-assis?style=social"/>
<img src="https://img.shields.io/badge/languange-java-java"/>
<img src="https://img.shields.io/badge/license-Mit-mit"/>
</p>

<br>

> Status do Projeto: :heavy_check_mark: (Concluído)

<br>

### Tópicos 

:small_blue_diamond: [Descrição do projeto](#descrição-do-projeto)

:small_blue_diamond: [Funcionalidades](#funcionalidades)

:small_blue_diamond: [Layout da Aplicação](#layout-da-aplicação-dash)

:small_blue_diamond: [Pré-requisitos](#pré-requisitos)

:small_blue_diamond: [Como rodar a aplicação](#como-rodar-a-aplicação-arrow_forward)

## Descrição do projeto 

<p align="justify">
Este é um projeto de sistema de gerenciamento de reservas desenvolvido em Java e Swing. O sistema permite que hotéis e estabelecimentos similares administrem suas reservas de forma eficaz e organizada. Alguns dos principais recursos do sistema incluem:

- Cadastro de Hóspedes: Adicione informações detalhadas sobre os hóspedes, incluindo nome, sobrenome, data de nascimento, nacionalidade e número de telefone.

- Reservas de Hospedagem: Crie e gerencie reservas de quartos. Isso inclui datas de entrada e saída, valores e informações de pagamento.
</p>

## Funcionalidades

:heavy_check_mark: Realiza o registro de reservas e hóspedes

:heavy_check_mark: Realiza todas as operações de CRUD (create, read, update, delete)   

## Layout da Aplicação :dash:

![Screenshot 2023-09-03 122007](https://github.com/edielson-assis/hotel-alura/assets/105529988/af3bb2f3-4f2c-4250-af52-15975105fa83)

## Pré-requisitos

- [x] Conhecer a sintaxe do Java<br>
- [x] Java JDK 17<br>
- [x] IDE para desenvolvimento Java (utilizei o Vs Code)<br>
- [x] Git<br>
- [x] Conta no GitHub<br>
 
## Como rodar a aplicação :arrow_forward:

Faça um fork do projeto, após isso, abra o terminal do git bash, na pasta onde deseja salvar o projeto, e digite o seguinte comando: 

```
git clone git@github.com:edielson-assis/hotel-alura.git
```
Em seguida, abra o projeto na IDE de sua preferência. 
Crie uma base de dados no MySQL, utilize o script [SQL](https://github.com/edielson-assis/hotel-alura/blob/main/db/db.sql) que está dentro da pasta db.

Antes de rodar a aplicação a partir do método main, siga estas etapas essenciais:

- Vá até as <b>Configurações de Segurança</b> em sua conta do Google. Se você ainda não tem uma, terá que criar.
- Dentro das configurações, acesse <b>Verificação em Duas Etapas</b>, ative a verificação em duas etapas e depois clique em <b>Senhas de Apps</b>.
- Selecione "Selecionar App" e escolha <b>E-mail</b>. Depois, selecione <b>Dispositivo</b> e escolha "Computador Windows".
- Clique em <b>Gerar</b>. Isso criará um token com 16 caracteres. Este token será usado como a senha do serviço de e-mail no arquivo <b>email.properties</b>. Dê um nome para token gerado.
- Use o e-mail configurado na etapa anterior como seu login no arquivo <b>email.properties</b>.
- Agora você concluiu as configurações necessárias. Basta executar a aplicação através da classe <b>Main</b>.

<b>Nota Importante:</b>

Durante o cadastro de usuário na aplicação, um código de confirmação será enviado para o e-mail fornecido pelo usuário. Isso é essencial para verificar a validade do e-mail informado.

## Linguagens, dependencias e libs utilizadas :books:

- [Java](https://docs.oracle.com/en/java/javase/17/docs/api/index.html)
- [Maven](https://maven.apache.org/ref/3.9.3/maven-core/index.html)
- [JCalendar](https://mvnrepository.com/artifact/com.toedter/jcalendar)
- [MySQL Connector](https://mvnrepository.com/artifact/mysql/mysql-connector-java)
- [JavaMail API](https://mvnrepository.com/artifact/com.sun.mail/javax.mail)
- [JavaBeans(TM) Activation Framework](https://mvnrepository.com/artifact/javax.activation/activation)
- [MiGLayout](https://mvnrepository.com/artifact/com.miglayout/miglayout)
- [Timing Framework](https://mvnrepository.com/artifact/net.java.dev.timingframework/timingframework)
- [JBCrypt](https://mvnrepository.com/artifact/org.mindrot/jbcrypt/0.4)
- [Apache Commons Validator](https://mvnrepository.com/artifact/commons-validator/commons-validator/1.7)

## Contribuindo 🤝

<p>
Este é um projeto open source, então contribua com ele.<br>
Se te ajudei de alguma forma, ficarei feliz em saber. E caso você conheça alguém que se identifique com o conteúdo, não deixe de compartilhar.<br>
<br>
Se possível:<br>
⭐️  Star o projeto<br>
🐛 Encontrar e relatar issues<br>
</p>

## Desenvolvedor :octocat:

| [<img src="https://github.com/edielson-assis/conversor/assets/105529988/90c01d9d-ccf5-4b60-b740-c0db10e28b2a" width=115><br><sub>Edielson Assis</sub>](https://github.com/edielson-assis) |
| :---: |

## Licença 

The [MIT License](https://github.com/edielson-assis/hotel-alura/blob/main/LICENSE) (MIT)

Copyright :copyright: 2023 - Hotel Alura
