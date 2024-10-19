
# DESAFIO BACK-END LIFTERS 🤩

Esse projeto é resolução de um desafio proposto durante o processo seletivo para uma vaga de desenvolvedor back-end Java júnior.




## Stack utilizada

**Back-end:** 
- ✅ Java 17 
- ✅ Spring Boot 3 
- ✅ Spring Data JPA
- ✅ Spring Data Redis
- ✅ Model Mapper 
- ✅ PostgreSQL 
- ✅ Docker Compose
- ✅Liquibase
- ✅Redis 
- ✅Spring Doc Open API
- ✅Lombok
- ✅Jakarta Bean Validation
- ✅Jakarta Persistence API
- ✅Jasper Reports

## Introdução

Olá, tudo certo? Espero que sim! Primeiramente muito obrigado por terem proposto esse desafio para mim, foi muito bacana poder resolvê-lo e com certeza aprendi bastante enquanto desenvolvia a solução😄

A princípio gostaria de ter feito o deploy da aplicação na AWS ou numa ferramenta mais simples e gratuita para hospedagem como Render. Entretanto o tempo era curto e a prioridade era criar as funcionalidades principais requeridadas, infelizmente não sobrou tempo para realizar o deploy, mas consegui fazer a utilização do Liquibase(Embora eu ache o Flyway bem mais fácil de se utilizar🙃) para versionamento do banco de dados e Redis para "cachear" os recursos. A aplicação utiliza docker compose para "conteinerizar" o Redis e o PostgreSQL, infelizmente o container da aplicação não conseguia de maneira nenhuma se conectar ao container do banco (Mesmo as portas dos serviços e networks estando definidas corretamente) e eu não queria ficar perdendo muito tempo tentando resolver, portanto resolvi "containerizar" somente o Redis e o banco(O que já ajuda muito a vida de qualquer dev né?!😂).

Concluindo, gostaria de agradecer mais uma vez pela oportunidade e espero que gostem do resultado, foram dois dias intensos de bugs e código, mas que para mim tiveram um resultado satisfatório.

## OBSERVAÇÕES ❗❗❗

Ao ler o desafio surgiram algumas dúvidas sobre ele, que por ser um desafio assíncrono não possuia ninguém para saná-las, os questionamentos foram os seguintes:

- No diagrama disponibilizado o relacionamento entre *Votos* e *Eleitores* é de Muitos para Um(ManyToOne), mas logo abaixo na seção de "Carcterísticas a serem seguidas" é dito que só deve ser permitido um voto por eleitor. Dessa forma, me surgiu um questionamento, pois isso implicaria um relacionamento de Um para Um(OneToOne), que foi o que eu acabei seguindo na minha solução. Portanto, um eleitor está relacionado com somente um voto e um voto está relacionado com somente um eleitor.

- Outro ponto que ficou um pouco ambíguo para mim foi com relação a votação, se um eleitor poderia votar em mais de um candidato, mas que fosse de um cargo diferente(O que de fato ocorre no mundo real). Por fim, como o relacionamento entre eleitor e voto me parecia ser OneToOne eu acabei implementando a solução de modo que um eleitor só pode votar em um único candidato independentemente de seu cargo.

- Enfim, uma outra característica pedida seria que a API fosse RestFull. Minha intenção de início era seguir todas as constraints definidas no *Modelo de Maturidade de Richardson*, o que implicaria na utilização de HATEOAS, que eu iria implementar com Spring HATEOAS, entretanto como mencionei anteriormente, o prazo era curto e acabei focando nas principais features. Ademais, fica esse ponto como possível implementação futura, por mais que Hypermedia não seja utilizada pela massiva maioria das REST API´s.

## FERRAMENTAS NECESSÁRIAS PARA O PROJETO 💥

- Para utilizar o projeto é necessário ter alguma distribuição da JDK 17 devidamente instalada em sua máquina(Veja esse vídeo do lendário Nélio Alves: https://www.youtube.com/watch?v=QekeJBShCy4&pp=ygUgY29tbyBpbnN0YWxhciBqYXZhIDE3IG5vIHdpbmRvd3M%3D)

- Outra ferramenta imprescindível ter instalada é o Docker. Se estiver no Windows é muito simples, basta instalar o Docker Desktop(Veja esse vídeo: https://www.youtube.com/watch?v=HdW2BMRBDZ8&pp=ygUfY29tbyBpbnN0YWxhciBkb2NrZXIgbm8gd2luZG93cw%3D%3D), mas já se estiver utilizando MacOS(Ou seja, se você tiver o money🤑) ou em alguma distribuição Linux(Ou seja, não quer pagar licença pra Microsoft😆) o processo pode ser um pouco mais demorado(Veja a documentação oficial: https://docs.docker.com/desktop/install/mac-install/  |  https://docs.docker.com/desktop/install/linux/)

- Outra ferramenta muito simples de instalar e que ajuda a reduzir muito código Boilerplate é o famoso Lombok, que eu optei por utilizar no projeto. Esse você só tem que instalar se utilizar o STS para programar(Até onde eu sei), veja esse vídeo se for seu caso(Nélio Alves salvando programadores Java novamente 😅): https://www.youtube.com/watch?v=W0ywxkvc4_M&t=528s&pp=ygUfY29tbyBpbnN0YWxhciBsb21ib2sgbm8gd2luZG93cw%3D%3D


## COMO RODAR O PROJETO 🌠

Feitas as devidas instalações chegou a hora de finalmente rodar o projeto!(Eu ouvi um amém?🙏). Para isso vá até a pasta onde está o aquivo *docker-compose.yaml* e abra o terminal de sua preferência nela. Feito isso, execute *docker-compose up -d* (O -d é para o docker não travar o bendito terminal), se tudo deu certo o Docker irá ter levantado um container para o PostgreSQL e um container para o Redis.


Com isso feito você já pode executar o spring boot através da sua IDE, eu criei um arquivo no Liquibase para popular o banco de dados com alguns dados, são poucos, portanto eu sugiro que você explore as requisições POST para inserir mais dados. Você irá notar ao analisar o código que quando uma exceção é lançada no sistema ela é capturada, tratada e uma resposta adequada seguindo o padrão Problem Details for HTTP API´s (*RFC 7807* -> https://datatracker.ietf.org/doc/html/rfc7807 ) é retornada, o que eu pessoalmente acho uma abordagem muito interessante e elegante para o tratamento de exceções.

Por fim, mas não menos importante, não se esqueça de acessar o endereço http://localhost:8080/swagger-ui/index.html para visualizar o Swagger UI(documentação), eu tentei caprichar bastante nela para deixá-la muito intuitiva. Quando não quiser mais utilizar a aplicação basta utilizar no terminal na pasta onde está o *docker-compose.yaml* o comando *docker-compose down* e o Docker irá "matar" os containers.

## AGRADECIMENTOS 🧡🧡🧡

Por fim, gostaria de deixar meu agradecimento por estar participando desse processo seletivo, que independentemente do resultado foi muito bacana colocar minhas habilidades em prática e resolver esse desafio. Espero que gostem do que vocês irão ver aqui e espero conseguir essa oportunidade incrível de trilhar meu caminho na Lifters, seria uma grande realização para mim.🧡

*Vinicius N Cruz, 17/10/2024*

## ATUALIZAÇÕES 💫

Olá! Acabei tomando liberdade de implementar uma pequena feature muito legal para emissão do relatório em PDF também! Para obter em PDF basta passar o cabeçalho *application/pdf* no endpoint *v1/candidatos/relatorio* , acho uma feature muito bacana, pois traz os dados organizados de maneira gráfica. Entretanto, se quiserem avaliar somente o que fiz dentro do prazo estabelecido (Quinta, 17/10 até às 19hrs) podem voltar o código até o último commit da Quinta e avaliar somente aquilo.


