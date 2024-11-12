package com.example.pessoa;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/pessoa")
public class PessoaServlet extends HttpServlet {

    private static final long servialVersionUID = 1L;

    private List <Pessoa> pessoas = new ArrayList<>();

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
         * Captura os dados do form 
         */
        String nome  = request.getParameter("nome");
        String email = request.getParameter("email");

        /*
         * @todo: verificar tratamento dos dados antes de inserir na lista
         */

        /*
         * Cria e adiciona o item na lista
         */

        Pessoa pessoa = new Pessoa();
        pessoa.setIdPessoa(pessoas.size()+1);
        pessoa.setNome(nome);
        pessoa.setEmail(email);

        pessoas.add(pessoa);

        /*
         * Definimos o destino da pagina de resultado
         */
        request.setAttribute("pessoas", pessoas);
        RequestDispatcher dispatcher = new request.getRequestDispatcher("/pessoa.jsp");
        dispatcher.forward(request, response);
    }


}