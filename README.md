# tec502-pbl2-server

<p align="center">
  <img src="https://i.imgur.com/wkRYgEN.png" alt="server icon" width="300px" height="300px">
</p>

------------

## 📚 Descrição ##
**Resolução do problema 2 do MI - Concorrência e Conectividade (TEC 502).**<br/><br/>
O projeto trata-se de um servidor (utilizando [ServerSocket](https://docs.oracle.com/javase/7/docs/api/java/net/ServerSocket.html)) que recebe requisições [HTTP](https://developer.mozilla.org/pt-BR/docs/Web/HTTP/Methods) no formato [JSON](https://www.json.org/json-en.html), e devolve respostas nesse mesmo formato. <br/>
Este servidor permite múltiplas conexões com os *clients* ao mesmo tempo, em que cada conexão é processada por uma *thread* diferente. Além disso, o mesmo se comunica com várias *Fogs* para obter os dados dos sensores dos pacientes, e dessa forma repassá-los para a interface de monitoramento que será utilizada por um profissional da saúde.

### ⛵ Navegação pelos projetos: ###
- \> Servidor
- [Fog](https://github.com/AllanCapistrano/tec502-pbl2-fog)
- [Emulador de Sensores](https://github.com/JoaoErick/tec502-pbl2-emulator)
- [Monitoramento de Pacientes](https://github.com/JoaoErick/tec502-pbl2-monitoring)

### 🔗 Tecnologias utilizadas: ### 
- [Java JDK 8](https://www.oracle.com/br/java/technologies/javase/javase-jdk8-downloads.html)

### 📊 Dependências: ### 
- [JSON](https://www.json.org/json-en.html)

------------

## 🖥️ Como utilizar ##
Para o utilizar este projeto é necessário ter instalado o JDK 8u111.

- [JDK 8u111 com Netbeans 8.2](https://www.oracle.com/technetwork/java/javase/downloads/jdk-netbeans-jsp-3413139-esa.html)
- [JDK 8u111](https://www.oracle.com/br/java/technologies/javase/javase8-archive-downloads.html)

### Através de uma IDE ###
Caso esteja utilizando alguma IDE, basta **executar o projeto**, por exemplo, utilizando o *NetBeans IDE 8.2* aperte o botão `F6`; <br/>

------------

## 📌 Autores ##
- Allan Capistrano: [Github](https://github.com/AllanCapistrano) - [Linkedin](https://www.linkedin.com/in/allancapistrano/) - [E-mail](https://mail.google.com/mail/u/0/?view=cm&fs=1&tf=1&source=mailto&to=asantos@ecomp.uefs.br)
- João Erick Barbosa: [Github](https://github.com/JoaoErick) - [Linkedin](https://www.linkedin.com/in/joão-erick-barbosa-9050801b0/) - [E-mail](https://mail.google.com/mail/u/0/?view=cm&fs=1&tf=1&source=mailto&to=jsilva@ecomp.uefs.br)

------------

## ⚖️ Licença ##
[MIT License (MIT)](./LICENSE)
