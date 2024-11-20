# Bookstore - Gutendex Book API

## 📚 Descrição do Projeto
Aplicação de Bookstore que integra a API do Gutendex para gerenciamento de livros em domínio público.

Este projeto foi desenvolvido para ser executado localmente. Certifique-se de configurar corretamente as variáveis de ambiente antes de rodá-lo para garantir seu funcionamento adequado.

## Repositorio para o FrontEnd

https://github.com/jkhlf/bookstore-front

## 🛠 Tecnologias Utilizadas
- **Backend**: Spring Boot
- **Banco de Dados**: PostgreSQL
- **Frontend**: React + Vite
- **API de Livros**: Gutendex (https://gutendex.com)

## 🌐 Endpoints

### `/api/books`
- **Descrição**: Recupera lista de livros disponíveis
- **Retorno**: Lista de livros

### `/sync`
- **Descrição**: Sincroniza livros da API Gutendex com banco de dados local
- **Funcionalidade**:
    - Busca livros na API Gutendex
    - Armazena no banco PostgreSQL


