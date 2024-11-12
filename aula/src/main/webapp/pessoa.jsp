<%@ page import="java.util.List" %>
<%@ page import="com.example.pessoa.Pessoa" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html lang="pt-br">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Cadastro e Listagem de Pessoas</title>

    <!-- Link para o Bootstrap -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-KyZXEJ6X8pR4M5j6b8t//vCkzTt0tG6R7bBmbRna2b6QtQGZ6A2Gd/KtvVrS4kFc" crossorigin="anonymous">
</head>
<body>

    <div class="container mt-5">
        <!-- Formulário de Cadastro -->
        <h1>Cadastro de Pessoa</h1>
        <c:if test="${not empty mensagem}">
            <div class="alert alert-success">
                ${mensagem}
            </div>
        </c:if>

        <form action="pessoa" method="post" class="mt-3">
            <div class="mb-3">
                <label for="nome" class="form-label">Nome:</label>
                <input type="text" id="nome" name="nome" class="form-control" required>
            </div>

            <div class="mb-3">
                <label for="email" class="form-label">Email:</label>
                <input type="email" id="email" name="email" class="form-control" required>
            </div>

            <button type="submit" class="btn btn-primary">Cadastrar</button>
        </form>

        <!-- Tabela de Pessoas Cadastradas -->
        <h2 class="mt-5">Lista de Pessoas Cadastradas</h2>
        <table class="table table-striped mt-3">
            <thead>
                <tr>
                    <th>ID</th>
                    <th>Nome</th>
                    <th>Email</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="pessoa" items="${pessoas}">
                    <tr>
                        <td>${pessoa.idPessoa}</td>
                        <td>${pessoa.nome}</td>
                        <td>${pessoa.email}</td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </div>

    <!-- Script do Bootstrap -->
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js" integrity="sha384-pzjw8f+ua7Kw1TIq0tG0+2a0qf0f6FJl8S4z5BtrtBz5T2Cg7w5BZdVhxb8GJ9t8" crossorigin="anonymous"></script>
</body>
</html>
