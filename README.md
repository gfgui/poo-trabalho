# 🎵 Sistema de Reviews de Álbuns

Este é um projeto simples em Java que simula o cadastro de reviewers e reviews de álbuns musicais, permitindo calcular médias de notas e visualizar informações dos participantes.

## 🛠 Requisitos

- Java 21.0.7 ou superior
- Terminal ou IDE de sua preferência

## 📁 Estrutura do Projeto

```bash
src/
├── model/ # Classes de modelo (Review, Album, Reviewer, etc)
├── controller/ # Controladores (ReviewController, AlbumController, etc)
└── Main.java # Ponto de entrada do programa
```

## 🚀 Como compilar e executar

1. Navegue até a pasta do projeto onde está o código-fonte.
2. Compile o projeto:

```bash
javac -d out src/**/*.java
```

3. Execute o programa:
```bash 
java -cp out Main
```

🎯 Funcionalidades

✅ Cadastro de reviewers (comuns e especialistas)
✅ Cadastro de álbuns
✅ Registro de reviews com notas e comentários
✅ Cálculo de médias de notas por reviewer e por álbum
✅ Separação de análises por especialistas e reviewers comuns
✅ Listagem de reviewers e suas informações
💡 Observações

    O sistema roda totalmente em console.

    As listas são armazenadas apenas em memória (dados se perdem ao encerrar o programa).

    Ideal para fins acadêmicos ou como projeto de prática de POO.