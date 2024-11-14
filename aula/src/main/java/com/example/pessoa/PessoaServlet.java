package com.example.pessoa;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/pessoa")
public class PessoaServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    private List<Pessoa> pessoas = new ArrayList<>();

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("pessoas", pessoas);

        String cadastro = request.getParameter("cadastro");
        if (cadastro != null && cadastro.equals("true")) {
            request.setAttribute("mensagem", "Cadastro realizado com sucesso");
        }

        RequestDispatcher dispatcher = request.getRequestDispatcher("/pessoa.jsp");
        dispatcher.forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        /*
         * captura dados do form
         */
        String nome = request.getParameter("nome");
        String email = request.getParameter("email");

        /*
         * validação do e-mail
         */
        String mensagem = validarEmail(email);

        if (mensagem != null) {
            // Se o e-mail for inválido, exibe a mensagem de erro
            request.setAttribute("mensagem", mensagem);
            request.setAttribute("pessoas", pessoas);
            RequestDispatcher dispatcher = request.getRequestDispatcher("/pessoa.jsp");
            dispatcher.forward(request, response);
            return; // Interrompe o processo caso a validação falhe
        }

        /*
         * Cria e adiciona os itens na lista
         */
        Pessoa pessoa = new Pessoa();
        pessoa.setIdPessoa(pessoas.size() + 1);
        pessoa.setNome(nome);
        pessoa.setEmail(email);

        pessoas.add(pessoa);

        /*
         * Mensagem de sucesso
         */
        request.setAttribute("mensagem",   "<div class=\"alert alert-success\">\n" +
                                                "    Cadastro realizado com sucesso\n" +
                                                "</div>");
        request.setAttribute("pessoas", pessoas);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/pessoa.jsp");
        dispatcher.forward(request, response);
    }

    // Método para validar o e-mail
    private String validarEmail(String email) {
        // Expressão regular para validar o e-mail
        String regex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(email);

        if (!matcher.matches()) {
            return "<div class='alert alert-danger' role='alert'>" +
                      "Erro no cadastro: Formato de email incorreto" +
                      "</div>"; // Retorna mensagem de erro caso o e-mail seja inválido
        }
        return null; // Retorna null se o e-mail for válido
    }
}
