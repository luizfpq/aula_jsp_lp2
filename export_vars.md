
# Configuração de Variáveis de Ambiente no Tomcat

Este documento descreve o processo de configuração de variáveis de ambiente no servidor Tomcat e como ajustar o arquivo `catalina.options`.

## 1. Definindo Variáveis de Ambiente no Tomcat

### Para Linux/Mac:

1. Vá para o diretório de instalação do Tomcat.
2. Localize o arquivo `setenv.sh` na pasta `bin`. Caso o arquivo não exista, crie-o.
3. Adicione as variáveis de ambiente no arquivo `setenv.sh`:

```bash
export DB_URL="jdbc:mysql://ip_do_server:porta/banco"
export DB_USERNAME="root"
export DB_PASSWORD="minhasenha"
```

### Para Windows:

1. Vá para o diretório de instalação do Tomcat.
2. Localize o arquivo `setenv.bat` na pasta `bin`. Caso o arquivo não exista, crie-o.
3. Adicione as variáveis de ambiente no arquivo `setenv.bat`:

```batch
set DB_URL=jdbc:mysql://ip_do_server:porta/banco
set DB_USERNAME=root
set DB_PASSWORD=minhasenha
```

Após salvar o arquivo, reinicie o Tomcat para que as variáveis de ambiente sejam carregadas.

## 2. Reinicie o Tomcat

Após configurar as variáveis de ambiente ou ajustar a configuração do Tomcat, reinicie o servidor Tomcat para garantir que ele reconheça as novas variáveis de ambiente.

## 3. Verifique se o Tomcat Tem Acesso às Variáveis de Ambiente

Se você estiver rodando o Tomcat em um servidor, as variáveis precisam estar definidas no ambiente onde o Tomcat está sendo executado. Se o Tomcat for iniciado via `systemd` ou como um serviço no Linux, as variáveis de ambiente podem precisar ser definidas no arquivo de configuração do serviço.

### Exemplo de Definição em um Serviço `systemd`:

Se você estiver usando `systemd` para gerenciar o Tomcat, você pode adicionar as variáveis ao arquivo de serviço do Tomcat, por exemplo, em `/etc/systemd/system/tomcat.service`:

```ini
[Service]
Environment="DB_URL=jdbc:mysql://ip_do_server:porta/banco"
Environment="DB_USERNAME=root"
Environment="DB_PASSWORD=minhasenha"
```

Após alterar o arquivo, reinicie o serviço com os seguintes comandos:

```bash
sudo systemctl daemon-reload
sudo systemctl restart tomcat
```

## 4. Verifique o Acesso às Variáveis no Tomcat

Você pode testar se o Tomcat está conseguindo acessar as variáveis de ambiente usando um servlet simples que imprime essas variáveis na resposta HTTP. Isso pode ajudar a depurar o problema.

### Exemplo de Servlet para Verificar Variáveis:

```java
@WebServlet("/verificarVariaveis")
public class VerificarVariaveisServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String dbUrl = System.getenv("DB_URL");
        String dbUsername = System.getenv("DB_USERNAME");
        String dbPassword = System.getenv("DB_PASSWORD");

        response.setContentType("text/plain");
        response.getWriter().println("DB_URL: " + dbUrl);
        response.getWriter().println("DB_USERNAME: " + dbUsername);
        response.getWriter().println("DB_PASSWORD: " + dbPassword);
    }
}
```

Acesse este servlet para verificar se as variáveis estão sendo lidas corretamente. Se o valor das variáveis aparecer como `null`, significa que o Tomcat não consegue acessar as variáveis de ambiente.

## 5. Configuração do `catalina.options`

Para ajustar configurações adicionais no Tomcat, como memória ou parâmetros específicos de execução, edite o arquivo `catalina.options`.

### Exemplo de como configurar opções no `catalina.options`:

No diretório `bin` do Tomcat, você pode configurar opções de inicialização para o Tomcat. Crie ou edite o arquivo `catalina.options` com as opções desejadas, como:

```bash
JAVA_OPTS="-Xms512m -Xmx1024m -Dfile.encoding=UTF-8"
```

Estas opções podem ser utilizadas para definir a quantidade mínima e máxima de memória heap, encoding de arquivos e outras propriedades.

Dentre essas propriedades, você pode criar os dados de ambiente, simplesmente setando uma variável, sem pontos e sem espaços:
```
DB_URL="jdbc:mysql://ip_do_server:porta/banco"
DB_USERNAME="root"
DB_PASSWORD="minhasenha"
```

Após editar o arquivo, reinicie o Tomcat para aplicar as novas configurações.

Para acessar os dados definidos no catalina.options:

```java
@WebServlet("/verificarVariaveis")
public class VerificarVariaveisServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String dbUrl = System.getProperties("DB_URL");
        String dbUsername = System.getProperties("DB_USERNAME");
        String dbPassword = System.getProperties("DB_PASSWORD");

        response.setContentType("text/plain");
        response.getWriter().println("DB_URL: " + dbUrl);
        response.getWriter().println("DB_USERNAME: " + dbUsername);
        response.getWriter().println("DB_PASSWORD: " + dbPassword);
    }
}