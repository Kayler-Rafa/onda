# Aplicativo de Cadastro de Alunos - OndaTec

Este projeto é um aplicativo Android desenvolvido em Kotlin para realizar o cadastro de alunos no sistema OndaTec. O aplicativo permite que os alunos se cadastrem fornecendo seu email e senha, enviando esses dados para um servidor remoto. Caso o cadastro seja bem-sucedido, o aluno será redirecionado para a tela de login.

## Funcionalidades do Aplicativo

- Cadastro de Alunos: Permite que alunos se cadastrem no sistema fornecendo email e senha.
- Requisição POST: Envia os dados de cadastro para um servidor remoto.
- Redirecionamento: Caso o cadastro seja bem-sucedido, o usuário é redirecionado para a tela de login.
- Interface Moderna: Interface construída apenas com Jetpack Compose, sem o uso de XML.

## Estrutura do Projeto

O projeto segue uma organização simples com todos os arquivos Kotlin localizados no pacote `com.example.onda`.

### Principais Componentes

1. Tela de Cadastro
   - Campos de entrada para o email e senha do aluno.
   - Botão de envio para realizar o cadastro.
   - Exibição de mensagens de erro em caso de falha no cadastro.

2. Requisição ao Servidor
   - Implementação da requisição POST para cadastrar o aluno.
   - Tratamento de erros como falha na comunicação ou problemas com os dados fornecidos.

3. Navegação
   - Caso o cadastro seja bem-sucedido, o aluno será redirecionado para a tela de login.

## Executando o Projeto

1. Pré-requisitos
   - Android Studio instalado (versão mínima recomendada: Arctic Fox ou superior).
   - Dispositivo físico ou emulador configurado para rodar aplicativos Android.

2. Clonando o Repositório
   - Clone o repositório do projeto para o seu ambiente local:
     `git clone https://github.com/Kayler-Rafa/onda`

3. Abrindo no Android Studio
   - Abra o Android Studio e importe o projeto.

4. Executando no Emulador ou Dispositivo
   - Conecte um dispositivo físico ou inicie um emulador.
   - Clique no botão Run para instalar e iniciar o aplicativo.

## Testando o Cadastro

- Para testar o cadastro, você pode usar as credenciais abaixo:
  - **Email**: `admin`
  - **Senha**: `admin`

  No entanto, o funcionamento pode depender do status do servidor, já que a aplicação na AWS pode estar desligada. Se o servidor estiver fora do ar, o cadastro não será concluído com sucesso.

## Estrutura de Arquivos

- `AlunoCadastroActivity.kt`: Gerencia a interface de cadastro e eventos do usuário.
- `CadastroUtils.kt`: Contém a lógica para realizar a requisição de cadastro para o servidor.
- `ApiService.kt`: Implementa a comunicação com o servidor para o cadastro de alunos.

## Contribuições

Contribuições são bem-vindas! Sinta-se à vontade para abrir issues ou enviar pull requests com sugestões de melhorias.

## Licença

Este projeto está licenciado sob a MIT License.

## Time do Projeto

- Dilermando Afonso
- Labelle Cândido
- Michael Azevedo
- Rafael "A lenda" Diniz
