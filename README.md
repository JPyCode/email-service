# Email Service API
Esta API foi criada como parte de um desafio da plataforma Uber, com o objetivo de demonstrar o envio de e-mails utilizando o AWS Simple Email Service (SES). A API expõe um único endpoint que permite o envio de e-mails para um ou mais destinatários com assunto e corpo definidos.

## Funcionalidade
- Envio de e-mails via AWS SES.
- Suporta envio de e-mails para um ou mais destinatários.
- Recebe uma requisição POST com os dados necessários (destinatário, assunto e corpo do e-mail).
## Tecnologias
- Java 17 (ou superior)
- Spring Boot (para a criação da API)
- AWS SES (para envio de e-mails)
- Maven (gerenciador de dependências)
- Spring Web (para criação da API REST)
## Como Usar
### Pré-requisitos

1. AWS SES:
   - Você precisará de uma conta AWS e credenciais válidas para usar o AWS SES.
   - Certifique-se de ter configurado o AWS SES para a região correta e ter sua chave de acesso (accessKeyId) e chave secreta (secretAccessKey).


2. Java 17+:
   - A API foi desenvolvida com o Java 17, então é necessário ter uma versão compatível do Java instalada.

3. Configuração das credenciais do AWS SES:
   - No arquivo application.properties, defina as credenciais e a região do AWS SES.

## Configuração no application.properties
   Adicione as credenciais da AWS e a região do AWS SES no arquivo application.properties:

```properties
spring.application.name=email-service

# Configurações do AWS SES
aws.accessKeyId=your-access-key-id
aws.secretAccessKey=your-secret-access-key
aws.region=your-aws-region
```
Substitua os valores pelas suas credenciais reais da AWS.

## Como Executar
1. Clone o repositório para sua máquina local:

```bash
git clone https://github.com/JPyCode/email-service.git
cd email-service
```
2. Compile o projeto usando o Maven:

```bash
mvn clean install
```
3. Execute o aplicativo Spring Boot:

``` bash
mvn spring-boot:run
```
A API estará disponível em http://localhost:8080.

## Endpoints
### POST /api/email
- Descrição: Envia um e-mail utilizando o AWS SES.

- Requisição:

    - Body (JSON):
    ```json
    {
    "to": "destinatario@exemplo.com",
    "subject": "Assunto do E-mail",
    "body": "Corpo do e-mail"
    }
  ```

- Resposta:

    - 200 OK: E-mail enviado com sucesso.
    - 500 Internal Server Error: Se ocorrer um erro ao enviar o e-mail (por exemplo, falha ao se comunicar com o AWS SES).
## Exemplo de Requisição
```bash
curl -X POST http://localhost:8080/api/email \
-H "Content-Type: application/json" \
-d '{
"to": "destinatario@exemplo.com",
"subject": "Teste de Envio",
"body": "Este é um e-mail de teste enviado pela API."
}'
```

### Estrutura do Projeto
- me.henji.email_service: Pacote principal com as classes responsáveis pela aplicação.
    - EmailServiceApplication.java: Classe principal que inicializa a aplicação Spring Boot.
    - infra.ses: Contém a configuração e a implementação do envio de e-mails utilizando o AWS SES. 
    - core: Contém as classes e interfaces que representam o caso de uso do envio de e-mails.
    - adapters: Interface do gateway de envio de e-mails. 
    - controllers: Controlador responsável por receber as requisições HTTP.
## Considerações Finais
- A API foi desenvolvida como parte de um desafio da plataforma Uber e demonstra como integrar o AWS SES com o Spring Boot para o envio de e-mails.
- Certifique-se de ter configurado corretamente as credenciais do AWS SES para garantir que a API funcione corretamente.


## Contribuição
Contribuições são bem-vindas! Sinta-se à vontade para abrir uma <strong>issue</strong> ou enviar um <strong>pull request</strong>.

## Autor
Desenvolvido por <strong>Marcos</strong>. Entre em contato:
- Linkedin: [Clique aqui para acessar](https://www.linkedin.com/in/roberto-marcos/)
- Email: marcosrobertosilva.contato@gmail.com
