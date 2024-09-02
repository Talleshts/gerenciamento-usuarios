# gerenciamento-usuarios
2024-1 - Trabalho Final - Projeto de Sistemas de Software - Criação e Manutenção de usuários

# Sistema de Gerenciamento de Usuários

### Equipe Desenvolvedora
>Talles Henrique Teófilo dos Santos - talles.h.santos@edu.ufes.br

>Eduardo de Mello Portilho - eduardo.portilho@edu.ufes.br

>Caio Cordeiro Jacome - 

## Descrição

Este projeto é parte do trabalho final da disciplina de Projeto de Sistemas de Software. O sistema permite o gerenciamento de usuários, incluindo cadastro, autenticação, autorização, envio de notificações e gerenciamento de senhas, conforme os requisitos especificados.

## Funcionalidades

- **Cadastro de Usuários:** O primeiro usuário que se cadastra torna-se o administrador do sistema. Usuários adicionais podem se cadastrar, mas precisam ser autorizados pelo administrador antes de acessar o sistema.
- **Autenticação:** Todos os usuários devem fazer login com nome de usuário e senha.
- **Envio de Notificações:** Administradores podem enviar notificações para um ou mais usuários. Os usuários visualizam e marcam as notificações como lidas.
- **Gerenciamento de Senhas:** Usuários podem alterar suas senhas a qualquer momento após autenticação.
- **Registro de Logs:** O sistema registra em arquivo de log as operações de inclusão, alteração ou exclusão de usuários, envio e leitura de notificações, alteração de senha e autorização de usuário.

## Requisitos Funcionais e Não Funcionais

- **Persistência de Dados:** Utilizamos o SQLite como banco de dados, seguindo o padrão DAO para persistência.
- **Interface Gráfica:** A interface gráfica foi desenvolvida utilizando Java Swing, adotando o padrão MDI para navegação entre telas.
- **Padrões de Projeto:**
  - **State:** Implementado nos casos de uso do tipo CRUD.
  - **DAO:** Para a camada de persistência.
  - **Command:** Integrado ao padrão State.
  - **Observer:** Para garantir que todas as janelas abertas sejam atualizadas em tempo real.
  - **Adapter:** Utilizado no módulo de log para suportar diferentes formatos de arquivos (CSV e JSON).
- **Validador de Senhas:** Implementado com base no Validador de Senhas disponível em [GitHub](https://github.com/claytonfraga/validadorsenha), incluído como JAR no projeto Maven.

## Tecnologias Utilizadas

- **Linguagem:** Java 17
- **Framework:** Maven
- **Banco de Dados:** SQLite
- **Interface Gráfica:** Java Swing
- **Controle de Versão:** Git/GitHub

## Como Executar

1. Clone o repositório:
   ```
   git clone https://github.com/Talleshts/gerenciamento-usuarios.git
   ```
2. Importe o projeto em sua IDE de preferência (recomendamos NetBeans).
3. Execute o projeto através do Maven:
   ```
   mvn clean install
   mvn exec:java
   ```

## Estrutura do Projeto

- `src/main/java`: Contém o código-fonte do sistema.
- `src/main/resources`: Contém arquivos de configuração e recursos.
- `src/test/java`: Contém os testes unitários.
- `logs`: Diretório onde os arquivos de log serão armazenados.

## Contribuição

- Certifique-se de criar uma branch para cada nova funcionalidade ou correção de bug.
- Submeta suas alterações através de pull requests.
- Garanta que todas as funcionalidades estejam cobertas por testes antes de submeter.

## Contato

- Professor: Dr. Clayton Vieira Fraga Filho - clayton.fraga@ufes.br
