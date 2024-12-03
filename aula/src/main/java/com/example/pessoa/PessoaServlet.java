package com.example.pessoa;

import java.io.IOException;
import java.sql.Connection;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.example.dao.PessoaDAO;
import com.example.util.ConexaoFactory;

@WebServlet("/pessoa")
public class PessoaServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try (Connection connection = ConexaoFactory.getConnection()) {
            PessoaDAO pessoaDAO = new PessoaDAO(); // Sem passar connection aqui
            List<Pessoa> pessoas = pessoaDAO.listar();

            request.setAttribute("pessoas", pessoas);

            String cadastro = request.getParameter("cadastro");
            if (cadastro != null && cadastro.equals("true")) {
                request.setAttribute("mensagem", "Cadastro realizado com sucesso");
            }

            RequestDispatcher dispatcher = request.getRequestDispatcher("/pessoa.jsp");
            dispatcher.forward(request, response);
        } catch (Exception e) {
            request.setAttribute("mensagem", "<div class='alert alert-danger'>Erro ao carregar dados: " + e.getMessage() + "</div>");
            RequestDispatcher dispatcher = request.getRequestDispatcher("/pessoa.jsp");
            dispatcher.forward(request, response);
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String nome = request.getParameter("nome");
        String email = request.getParameter("email");

        String mensagem = validarEmail(email);

        if (mensagem != null) {
            request.setAttribute("mensagem", mensagem);
            doGet(request, response); // Reutiliza o método doGet para carregar a página com as pessoas
            return;
        }

        Pessoa pessoa = new Pessoa();
        pessoa.setNome(nome);
        pessoa.setEmail(email);

        try {
            PessoaDAO pessoaDAO = new PessoaDAO(); // Sem passar connection aqui
            pessoaDAO.salvar(pessoa);

            request.setAttribute("mensagem", "<div class='alert alert-success'>Cadastro realizado com sucesso!</div>");
            response.sendRedirect(request.getContextPath() + "/pessoa?cadastro=true");
        } catch (Exception e) {
            request.setAttribute("mensagem", "<div class='alert alert-danger'>Erro ao salvar pessoa: " + e.getMessage() + "</div>");
            doGet(request, response);
        }
    }

    private String validarEmail(String email) {
        String regex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(email);

        if (!matcher.matches()) {
            return "<div class='alert alert-danger' role='alert'>" +
                      "Erro no cadastro: Formato de email incorreto" +
                      "</div>";
        }
        return null;
    }
}
