Definindo Variáveis de Ambiente no Tomcat:

    Vá para o diretório de instalação do Tomcat.
    Localize o arquivo setenv.sh (para Linux) ou setenv.bat (para Windows) na pasta bin.
    Caso o arquivo não exista, crie-o e adicione as variáveis de ambiente nele.

Exemplo de setenv.sh (Linux/Mac):

export DB_URL="jdbc:mysql://localhost:3306/meubanco"
export DB_USERNAME="root"
export DB_PASSWORD="minhasenha"

Exemplo de setenv.bat (Windows):

set DB_URL=jdbc:mysql://localhost:3306/meubanco
set DB_USERNAME=root
set DB_PASSWORD=minhasenha

Após salvar o arquivo, reinicie o Tomcat para que as variáveis de ambiente sejam carregadas.
5. Reinicie o Tomcat

Após configurar as variáveis de ambiente ou ajustar a configuração do Tomcat, reinicie o servidor Tomcat para garantir que ele reconheça as novas variáveis de ambiente.


Verifique se o Tomcat Tem Acesso às Variáveis de Ambiente

Se você estiver rodando o Tomcat em um servidor, as variáveis precisam estar definidas no ambiente onde o Tomcat está sendo executado. Se o Tomcat for iniciado via systemd ou como um serviço no Linux, as variáveis de ambiente podem precisar ser definidas no arquivo de configuração do serviço.
Exemplo de definição em um serviço systemd:

Se você estiver usando systemd para gerenciar o Tomcat, você pode adicionar as variáveis ao arquivo de serviço do Tomcat, por exemplo, em /etc/systemd/system/tomcat.service:

[Service]
Environment="DB_URL=jdbc:mysql://localhost:3306/meubanco"
Environment="DB_USERNAME=root"
Environment="DB_PASSWORD=minhasenha"

Após alterar o arquivo, reinicie o serviço:

sudo systemctl daemon-reload
sudo systemctl restart tomcat

3. Verifique o Acesso às Variáveis no Tomcat

Você pode testar se o Tomcat está conseguindo acessar as variáveis de ambiente usando um servlet simples que imprime essas variáveis na resposta HTTP. Isso pode ajudar a depurar o problema.
Exemplo de Servlet para Verificar Variáveis:

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

Acesse este servlet para verificar se as variáveis estão sendo lidas corretamente. Se o valor das variáveis aparecerem como null, significa que o Tomcat não consegue acessar as variáveis de ambiente.