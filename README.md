# ATENDIMENTO API

    API responsável por solicitar um atendimento de um assunto específico, 
    atribuir para o time responsável sobre o assunto, e cada atendente atenda no máximo 3
    pessoas de forma simultânea, e caso todos os atendentes de um time estejam
    ocupados, os atendimentos devem ser enfileirados e distribuídos assim que possível,
    o atendente pode finalizar seu atendimento e será atribuido outros caso tenha
    algum aguardando atendimento, além de listar todos os atendimentos por paginação.

## Iniciar Projeto

    - Rodar o projeto com o comando: mvn clean install (para baixar as dependências).
    
    - O projeto foi utilizado a IDE do Intellij.
    
    - Para rodar os testes, basta utilizar o comando: mvn clean test

## Técnologias e Frameworks

    - Java11
    - SpringBoot 2.3.3.RELEASE
    - H2
    - JUnit5
    - Mockito
    - Rest
    - SpringData

## H2

    Subir a API com o banco de dados em memória.

    http://localhost:8080/h2-console

    url: jdbc:h2:mem:atendimento_db
    username: sa
    password: sa123

## Endpoints e Json

    - Metod: POST    
        http://localhost:8080/api/atendimentos

        Assunto disponiveis: 
            {
                "assunto":"Problemas com cartão",
                "descricao":"Digite aqui seu problema referente ao cartão"
            }
            
        ou    
    
            {
                "assunto":"Contratação de empréstimo",
                "descricao":"Digite aqui seu problema referente a empréstimo"
            }

        ou

            {
                "assunto":"Outros Assuntos",
                "descricao":"Digite aqui seu problema referente a qualquer assunto"
            }

    - Metod: PUT
        http://localhost:8080/api/atendimentos/{numeroChamado}/finaliza
        
        ****Obs.: Precisa informar o numero do chamado na URL****

        ****** Sem Body ******

    - Metod: GET
        http://localhost:8080/api/atendimentos

        ****** Sem Body ******