<h1>🧪 Projeto BDD com Cucumber</h1>

<p>Aplicação em Java utilizando ferramentas e conceitos modernos para desenvolvimento de APIs robustas e bem testadas:</p>

<ul>
  <li>✅ Rest e RestFull</li>
  <li>✅ Migrations</li>
  <li>✅ Lombok</li>
  <li>✅ JWT</li>
  <li>✅ Spring Boot (JPA, Data, Validations, Security...)</li>
  <li>✅ Docker e Docker Compose</li>
  <li>✅ Git Actions e Git</li>
  <li>✅ Swagger para documentação e testes dos endpoints</li>
  <li>✅ Testes de Comportamento com Cucumber e JUnit</li>
  <li>✅ JSON Schemas</li>
  <li>✅ JSON Validate</li>
</ul>

<h2>⚙️ Pré-requisitos</h2>
<ul>
  <li>Java SDK 21</li>
  <li>Docker</li>
  <li>Git</li>
  <li>Maven</li>
</ul>

<h2>🚀 Como Buildar e Inicializar a Aplicação</h2>

<details>
  <summary><strong>📌 Passo a Passo:</strong></summary>

  <p><strong>1️⃣ Clone o repositório:</strong></p>
  <pre><code>git clone https://github.com/Paulo4526/tornado-testes.git</code></pre>

  <p><strong>2️⃣ Execute o build e suba os containers com Docker Compose:</strong></p>
  <pre><code>docker compose up --build</code></pre>

  <p><strong>3️⃣ Acesse o Swagger para interagir com os endpoints:</strong></p>
  <p>👉 <a href="http://localhost:8080/swagger-ui/index.html#/" target="_blank">http://localhost:8080/swagger-ui/index.html#/</a></p>

</details>


<h2>📋 Hierarquia de Uso da API</h2>
<ul>
  <li>✅ Cadastrar um usuário</li>
  <li>✅ Resgatar um token JWT (para autenticação nas requisições seguintes)</li>
  <li>✅ Cadastrar um novo Tornado</li>
  <li>✅ Cadastrar informações de Clima (relacionadas ao Tornado)</li>
  <li>✅ Cadastrar informações de Danos (relacionadas ao Tornado)</li>
</ul>

<p><strong>🔔 Observação Importante:</strong>  
Todos os cadastros descritos acima deverão ser feitos diretamente via <strong>Swagger</strong>, <strong>Postman</strong> ou algum interpretador de API's.  
Os endpoints para criação de Tornados, Clima e Danos estão protegidos por autenticação JWT.  
Certifique-se de obter um token válido antes de realizar estas requisições.</p>

<p><strong>❗ Atenção:</strong>  
É necessário <strong>cadastrar o Tornado primeiro</strong>, pois os cadastros de Clima e Danos exigem um Tornado já existente.  
Caso contrário, a API retornará <code>403 Forbidden</code> devido aos relacionamentos obrigatórios.</p>

<h2>📄 Documentação e Testes via Swagger</h2>
<p>Todos os endpoints da aplicação podem ser testados diretamente no Swagger UI.</p>

<p><strong>Link Swagger:</strong>  
👉 <a href="http://localhost:8080/swagger-ui/index.html#/" target="_blank">http://localhost:8080/swagger-ui/index.html#/</a></p>

<p><img src="/.templates/images/swagger.png/" alt="Swagger UI" style="max-width: 100%; border-radius: 8px;"></p>

<h2>✔️ Status do Projeto:</h2>
<p>✅ Aplicação funcional com BDD e Testes de integração já implementados.<br>
✅ Melhorias e novas features em andamento.</p>
