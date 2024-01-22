<h2>Tech Challenge</h2>

<h3>O PROBLEMA</h3>
<P>Há uma lanchonete de bairro que está expandindo devido seu grande sucesso. Porém, com a expansão e sem um sistema de controle de pedidos, o atendimento aos clientes pode ser caótico e confuso. Por exemplo, imagine que um cliente faça um pedido complexo, como um hambúrguer personalizado com ingredientes específicos, acompanhado de batatas fritas e uma bebida. O atendente pode anotar o pedido em um papel e entregá-lo à cozinha, mas não há garantia de que o pedido será preparado corretamente. Sem um sistema de controle de pedidos, pode haver confusão entre os atendentes e a cozinha, resultando em atrasos na preparação e entrega dos pedidos. Os pedidos podem ser perdidos, mal interpretados ou esquecidos, levando à insatisfação dos clientes e a perda de negócios.</P>
<P>Em resumo, um sistema de controle de pedidos é essencial para garantir que a lanchonete possa atender os clientes de maneira eficiente, gerenciando seus pedidos e estoques de forma adequada. Sem ele, expandir a lanchonete pode acabar não dando certo, resultando em clientes insatisfeitos e impactando os negócios de forma negativa. Para solucionar o problema, a lanchonete irá investir em um sistema de autoatendimento de fast food, que é composto por uma série de dispositivos e interfaces que permitem aos clientes selecionar e fazer pedidos sem precisar interagir com um atendente, com as seguintes funcionalidades:</P>

<P><B>PEDIDO</B></P>
<p>Os clientes são apresentados a uma interface de seleção na qual podem optar por se identificarem via CPF, se cadastrarem com nome, e-mail ou não se identificar, podendo montar o combo na seguinte sequência, sendo todas elas opcionais:</p>
- Lanche<br>
- Acompanhamento<br>
- Bebida<br>
- Sobremesa<br>
<br>
<p>Em cada etapa é exibido o nome, descrição e preço de cada produto.</p>

<P><B>PAGAMENTO</B></P>
<p>O sistema deverá possuir uma opção de pagamento integrada para MVP. A forma de pagamento oferecida será via QRCode do Mercado Pago.</p>

<P><B>ACOMPANHAMENTO</B></P>
<p>Uma vez que o pedido é confirmado e pago, ele é enviado para a cozinha para ser preparado. Simultaneamente deve aparecer em um monitor para o cliente acompanhar o progresso do seu pedido com as seguintes etapas:</p>
- Recebido <br>
- Em preparação <br>
- Pronto <br>
- Finalizado <br>
<br>
<P><B>ENTREGA</B></P>
<p>Quando o pedido estiver pronto, o sistema deverá notificar o cliente que ele está pronto para retirada. Ao ser retirado, o pedido deve ser atualizado para o status finalizado. Além das etapas do cliente, o estabelecimento precisa de um acesso administrativo: </p>
- Gerenciar clientes: Com a identificação dos clientes o estabelecimento pode trabalhar em campanhas promocionais. <BR>
- Gerenciar produtos e categorias: Os produtos dispostos para escolha do cliente serão gerenciados pelo estabelecimento, definindo nome, categoria, preço, descrição e imagens. Para esse sistema teremos categorias fixas:<BR>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;a) Lanche <BR>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;b) Acompanhamento <BR>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;c) Bebida <BR>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;d) Sobremesa <BR>
- Acompanhamento de pedidos: Deve ser possível acompanhar os pedidos em andamento e tempo de espera de cada pedido. <BR>
<p>As informações dispostas no sistema de pedidos precisarão ser gerenciadas pelo estabelecimento através de um painel administrativo.</p>

<H3>ENTREGA FASE 1 </H3>
- Documentação do sistema (DDD) utilizando a linguagem ubíqua, dos seguintes fluxos:<BR>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;a) Realização do pedido e pagamento <BR>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;b) Preparação e entrega do pedido <BR>
- Uma aplicação para todo sistema de backend (monolito) que deverá ser desenvolvido seguindo os padrões apresentados nas aulas: <BR>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;a) Utilizando arquitetura hexagonal <BR>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;b) APIs: <BR>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;* Cadastro do Cliente <BR>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;* Identificação do Cliente via CPF <BR>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;* Criar, editar e remover de produto <BR>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;* Buscar produtos por categoria <BR>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;* Fake checkout, apenas enviar os produtos escolhidos para a fila <BR>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;* Listar os pedidos <BR>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;c) Aplicação deverá ser escalável para atender grandes volumes nos horários de pico <BR>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;d) Banco de dados a sua escolha <BR>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;* Inicialmente deveremos trabalhar e organizar a fila dos pedidos apenas em banco de dados <BR>
- A aplicação deve ser entregue com um Dockerfile configurado para executá-la corretamente. Para validação da POC, temos a seguinte limitação de infraestrutura: <BR>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;1 instância para banco de dados <BR>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;1 instâncias para executar aplicação <BR>
<BR>
<p>Não será necessário o desenvolvimento de interfaces para o frontend, o foco deve ser total no backend.</p>

<H2>Instruções</H2>

Para executar o projeto é necessário ter o docker instalado para que uma imagem do postgres seja iniciada automaticamente.<BR>
Para tudo funcionar, é preciso configure as seguintes variáveis de ambiente no seu sistema operacional.
- POSTGRES_USER
- POSTGRES_PASSWORD
- POSTGRES_DB

<p>As variáveis podem ser configuradas com os valores da sua preferência. Eles representam, respectivamente: O usuário do banco, a senha do usuário e o nome do banco da aplicação. Caso deseje utilizar alguma ferramenta para acessar o banco, utilize as credenciais fornecidas nas variáveis de ambiente.</p>
<p>Para executar a aplicação, basta executar o comando "docker compose up" na pasta docker dentro do projeto. Caso opte por executar o projeto sem necessidade de baixar todo o repositório, basta realizar o download do arquivo [compose.yaml](https://raw.githubusercontent.com/1SOAT2023/TechChallenge/main/docker/compose.yaml)</p>
<p>Documentação da API: http://localhost:8080/swagger-ui/index.html</p>
