# 🚲 Projeto Wheels - Sistema de Aluguel de Bicicletas


## 📌 Descrição do Projeto

Wheels é uma aplicação desktop desenvolvida em **Java**, com o objetivo de simular um sistema de aluguel de bicicletas de forma intuitiva e funcional. A aplicação oferece uma interface gráfica moderna com funcionalidades completas para **usuários** e **administradores**, incluindo geração de **recibos em PDF** e envio por **e-mail** — tudo isso **sem o uso de banco de dados**, utilizando arquivos locais para persistência.

---

## ⚙️ Principais Funcionalidades

### Usuário
- 👤 Cadastro e login
- 🚴‍♂️ Escolha de bicicletas disponíveis
- 🕒 Definição da quantidade de horas
- 📝 Campo de observações
- 📄 Geração de recibo em PDF
- 📧 Envio automático do recibo por e-mail
- 📂 Histórico pessoal de aluguéis
- 🔒 Logout

### Administrador
- 📊 Visualização de gráfico em pizza com estatísticas de aluguéis (fixas)
- 📁 Leitura do histórico geral de todos os usuários
- 🔐 Acesso restrito por e-mail e senha

---

## 🧱 Tecnologias Utilizadas

### Linguagem
- Java (JDK 23)

### Bibliotecas e Frameworks
- **Swing** (interface gráfica)
- **Simple Java Mail** – envio de e-mails com anexo
- **iText PDF (7)** – geração de recibos em PDF
- **JFreeChart** – exibição de gráficos

---

## 🖥️ Execução do Projeto

### Pré-requisitos
- IntelliJ IDEA (ou outro IDE com suporte a Java)
- JDK 23 instalado

### Como executar
1.Clone o repositório:

2.Abra o projeto em sua IDE e execute a classe Main.java.

3.Configure o envio de e-mails:

Acesse: https://myaccount.google.com/apppasswords

Gere uma senha de aplicativo.

No arquivo `EmailService.java`, substitua:

### private static final String FROM_EMAIL = `"seuEmail@gmail.com"`;

### private static final String PASSWORD = `"sua-senha-de-aplicativo"`;

É necessário que o e-mail usado seja real e com envio liberado (como uma conta do Gmail com verificação em duas etapas ativada).

Cadastre um usuário com esse e-mail real para que os recibos possam ser enviados corretamente.

## Principais Componentes

-`model`: Entidades como Usuario, Bicicleta, Administrador

-`service`: Lógica de autenticação, PDF, e-mail, histórico

-`telas`: Telas Swing: Login, Cadastro, Aluguel, Admin

-`historico`: Arquivos .txt com histórico de aluguel

-`recibos`: PDFs gerados para cada aluguel

-`users.txt`: Arquivo onde os usuários são armazenados

