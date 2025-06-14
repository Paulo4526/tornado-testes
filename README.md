<h1>🧪 Projeto BDD com Cucumber</h1>

<p>Aplicação em Java usando ferramentas e conceitos como:</p>
<ul>
  <li>Rest e RestFull</li>
  <li>Migrations</li>
  <li>Lombok</li>
  <li>JWT</li>
  <li>SpringBoot (JPA, Data, Validations, Security...)</li>
  <li>Docker e Docker Compose</li>
  <li>Git Actions e Git</li>
  <li>Swagger</li>
  <li>Testes de Comportamento com Cucumber e JUnit</li>
  <li>JSON Schemas</li>
  <li>Json Validate</li>
</ul>

<h2>⚙️ Pré-requisitos</h2>
<ul>
  <li>Java SDK 21</li>
  <li>Docker</li>
  <li>Git</li>
  <li>Maven</li>
</ul>

<h2>🚀 Build da aplicação / Inicialização da aplicação</h2>
<pre style="background:#f0f0f0; padding:10px; border-radius:6px;">
<code>docker compose up --build</code>
</pre>

<h2>📋 Hierarquia de uso</h2>
<ul>
  <li>Cadastrar um usuário</li>
  <li>Resgatar um token para poder realizar os testes e requisições</li>
  <li>Cadastrar um novo Tornado</li>
  <li>Cadastrar informações de Clima com relacionamento ao tornado cadastrado</li>
  <li>Cadastrar informações de Danos com relacionamento ao tornado cadastrado</li>
</ul>

<p><strong>OBS:</strong> É necessário criar o cadastro de tornado primeiro. Caso tente criar clima ou danos sem tornado, terá retorno <code>403</code>, pois há relacionamentos entre tornado e seus registros de clima e danos causados.</p>

<h2>📄 Documentação online Swagger</h2>
<p>Link para acessar o Swagger com JWT:  
<a href="http://localhost:8080/swagger-ui/index.html#/" target="_blank">http://localhost:8080/swagger-ui/index.html#/</a></p>

<p><img src="/.templates/images/swagger.png/" alt="Swagger UI" style="max-width: 100%; border-radius: 8px;"></p>
