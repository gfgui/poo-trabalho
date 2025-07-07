
import controller.AlbumController;
import controller.ReviewController;
import model.*;

import java.util.*;

public class Main {
    private static final Scanner scanner = new Scanner(System.in);
    private static final AlbumController albumController = new AlbumController();
    private static final ReviewController reviewController = new ReviewController();
    private static final List<IReviewer> reviewers = new ArrayList<>();

    public static void main(String[] args) {
        System.out.println("🎵 Bem-vindo ao Sistema de Reviews de Álbuns 🎵");

        while (true) {
            System.out.println("\nEscolha uma opção:");
            System.out.println("1. Cadastrar álbum");
            System.out.println("2. Cadastrar reviewer");
            System.out.println("3. Criar review");
            System.out.println("4. Ver nota média de todos os reviewers");
            System.out.println("5. Ver nota média dos reviewers especialistas");
            System.out.println("6. Ver nota média dos reviewers comuns");
            System.out.println("7. Listar álbuns");
            System.out.println("8. Listar reviewers");
            System.out.println("9. Listar reviews");

            System.out.println("10. Sair");

            System.out.print("Opção: ");

            int option = Integer.parseInt(scanner.nextLine());

            switch (option) {
                case 1 -> cadastrarAlbum();
                case 2 -> cadastrarReviewer();
                case 3 -> criarReview();
                case 4 -> verNotaMedia();
                case 5 -> verNotaMediaEspecialistas();
                case 6 -> verNotaMediaComuns();
                case 7 -> listarAlbuns();
                case 8 -> listarReviewers();
                case 9 -> listarReviews();
                case 10 -> {
                    System.out.println("Saindo... 🎧");
                    return;
                }
                default -> System.out.println("Opção inválida.");
            }

        }
    }

    private static void listarAlbuns() {
        List<Album> albuns = albumController.getAllAlbuns();
        if (albuns.isEmpty()) {
            System.out.println("📁 Nenhum álbum cadastrado.");
        } else {
            System.out.println("🎵 Lista de Álbuns:");
            for (Album album : albuns) {
                System.out.printf("- [%d] %s (%s), [%s] min, [%d] faixas. Compositores: %s%n",
                        album.getId(),
                        album.getNome(),
                        album.getAnoDeLancamento(),
                        album.getDuracao(),
                        album.getNumeroDeFaixas(),
                        String.join(", ", album.getCompositores()));
            }
        }
    }

    private static void listarReviewers() {
        if (reviewers.isEmpty()) {
            System.out.println("👤 Nenhum reviewer cadastrado.");
        } else {
            System.out.println("👥 Lista de Reviewers:");
            for (IReviewer reviewer : reviewers) {
                System.out.printf("- [%d] %s | Gênero favorito: %s | Reviews: %d | Nota média: %.2f%n",
                        reviewer.getId(),
                        reviewer.getNome(),
                        reviewer.getGeneroFavorito(),
                        reviewer.getNumeroDeReviews(),
                        reviewer.getNotaMedia());

                if (reviewer instanceof ReviewerEspecialista especialista) {
                    System.out.printf("  🔧 Especialista | Credibilidade: %.2f | Empresa: %s%n",
                            especialista.getNivelDeCredibilidade(),
                            especialista.getEmpresa());
                } else if (reviewer instanceof ReviewerComum comum) {
                    System.out.printf("  🎧 Comum | Plataforma favorita: %s%n",
                            comum.getPlataformaDeStreamFavorita());
                }

                System.out.println();
            }
        }
    }



    private static void listarReviews() {
        List<Review> reviews = reviewController.getAllReviews();
        if (reviews.isEmpty()) {
            System.out.println("📝 Nenhuma review cadastrada.");
        } else {
            System.out.println("📝 Lista de Reviews:");
            for (Review review : reviews) {
                System.out.printf("- Álbum ID: %d | Reviewer ID: %d | Nota: %d | Comentário: %s | Data: %s%n",
                        review.getAlbumId(),
                        review.getReviewerId(),
                        review.getNota(),
                        review.getConteudo(),
                        review.getDataDePublicacao().toString());
            }
        }
    }


    private static void cadastrarAlbum() {
        System.out.print("ID do álbum: ");
        int id = Integer.parseInt(scanner.nextLine());

        System.out.print("Nome do álbum: ");
        String nome = scanner.nextLine();

        System.out.print("Ano de lançamento: ");
        String ano = scanner.nextLine();

        System.out.print("Compositores (separados por vírgula): ");
        String[] compositores = scanner.nextLine().split(",");

        System.out.print("Duração (em minutos): ");
        int duracao = Integer.parseInt(scanner.nextLine());

        System.out.print("Número de faixas: ");
        int faixas = Integer.parseInt(scanner.nextLine());

        Album album = new Album(id, nome, ano, compositores, duracao, faixas);
        albumController.createAlbum(album);

        System.out.println("✅ Álbum cadastrado com sucesso!");
    }

    private static void cadastrarReviewer() {
        System.out.print("ID do reviewer: ");
        int id = Integer.parseInt(scanner.nextLine());

        System.out.print("Nome: ");
        String nome = scanner.nextLine();

        System.out.print("Gênero favorito: ");
        String genero = scanner.nextLine();

        System.out.println("Tipo de reviewer:");
        System.out.println("1. Comum");
        System.out.println("2. Especialista");
        System.out.print("Escolha: ");
        int tipo = Integer.parseInt(scanner.nextLine());

        IReviewer reviewer;

        if (tipo == 1) {
            System.out.print("Plataforma de streaming favorita: ");
            String plataforma = scanner.nextLine();

            reviewer = new ReviewerComum(id, nome, genero, plataforma);

        } else if (tipo == 2) {
            System.out.print("Nível de credibilidade (1 a 5): ");
            int nivel = Integer.parseInt(scanner.nextLine());

            System.out.print("Empresa: ");
            String empresa = scanner.nextLine();

            reviewer = new ReviewerEspecialista(id, nome, genero, nivel, empresa);

        } else {
            System.out.println("❌ Tipo inválido.");
            return;
        }

        reviewers.add(reviewer);
        System.out.println("✅ Reviewer cadastrado com sucesso!");
    }

    private static void criarReview() {
        if (reviewers.isEmpty()) {
            System.out.println("❗ Nenhum reviewer cadastrado.");
            return;
        }

        System.out.print("ID do reviewer: ");
        int reviewerId = Integer.parseInt(scanner.nextLine());

        IReviewer reviewer = reviewers.stream()
                .filter(r -> r.getId() == reviewerId)
                .findFirst()
                .orElse(null);

        if (reviewer == null) {
            System.out.println("❌ Reviewer não encontrado.");
            return;
        }

        System.out.print("ID do álbum: ");
        int albumId = Integer.parseInt(scanner.nextLine());

        if (!albumController.exists(albumId)) {
            System.out.println("❌ Álbum não encontrado.");
            return;
        }

        System.out.print("Nota (0 a 10): ");
        int nota = Integer.parseInt(scanner.nextLine());

        System.out.print("Comentário: ");
        String comentario = scanner.nextLine();

        Review.TipoReviewer tipo =
                (reviewer instanceof ReviewerEspecialista)
                        ? Review.TipoReviewer.ESPECIALISTA
                        : Review.TipoReviewer.COMUM;

        int novoId = reviewController.generateNewId();

        Review review = new Review(
                novoId,
                nota,
                comentario,
                new Date(),
                reviewerId,
                albumId,
                tipo
        );

        reviewer.createNewReview(review);
        reviewController.createReview(review);

        System.out.println("✅ Review registrada com sucesso!");
    }


    private static void verNotaMedia() {
        List<Integer> albumIds = albumController.getAllAlbuns()
                .stream()
                .map(Album::getId)
                .toList();

        if (albumIds.isEmpty()) {
            System.out.println("❗ Nenhum álbum cadastrado.");
            return;
        }

        for (int albumId : albumIds) {
            String nomeAlbum = albumController.getNomeDoAlbum(albumId); // Método que retorna o nome do álbum dado o id
            List<Review> reviewsDoAlbum = reviewController.getAllReviews()
                    .stream()
                    .filter(r -> r.getAlbumId() == albumId)
                    .toList();

            if (reviewsDoAlbum.isEmpty()) {
                System.out.printf("🎵 %s (ID: %d) - Sem reviews.\n", nomeAlbum, albumId);
            } else {
                double media = reviewsDoAlbum.stream()
                        .mapToInt(Review::getNota)
                        .average()
                        .orElse(0.0);

                System.out.printf("🎵 %s (ID: %d) - Nota média: %.2f\n", nomeAlbum, albumId, media);
            }
        }
    }

    private static void verNotaMediaEspecialistas() {
        List<Integer> albumIds = albumController.getAllAlbuns()
                .stream()
                .map(Album::getId)
                .toList();

        if (albumIds.isEmpty()) {
            System.out.println("❗ Nenhum álbum cadastrado.");
            return;
        }

        for (int albumId : albumIds) {
            String nomeAlbum = albumController.getNomeDoAlbum(albumId);

            List<Review> reviewsEspecialistas = reviewController.getAllReviews()
                    .stream()
                    .filter(r -> r.getAlbumId() == albumId)
                    .filter(r -> r.getTipo() == Review.TipoReviewer.ESPECIALISTA)
                    .toList();

            if (reviewsEspecialistas.isEmpty()) {
                System.out.printf("🎵 %s (ID: %d) - Sem reviews de especialistas.\n", nomeAlbum, albumId);
            } else {
                double media = reviewsEspecialistas.stream()
                        .mapToInt(Review::getNota)
                        .average()
                        .orElse(0.0);

                System.out.printf("🎵 %s (ID: %d) - Nota média (especialistas): %.2f\n", nomeAlbum, albumId, media);
            }
        }
    }


    private static void verNotaMediaComuns() {
        List<Integer> albumIds = albumController.getAllAlbuns()
                .stream()
                .map(Album::getId)
                .toList();

        if (albumIds.isEmpty()) {
            System.out.println("❗ Nenhum álbum cadastrado.");
            return;
        }

        for (int albumId : albumIds) {
            String nomeAlbum = albumController.getNomeDoAlbum(albumId);

            List<Review> reviewsComuns = reviewController.getAllReviews()
                    .stream()
                    .filter(r -> r.getAlbumId() == albumId)
                    .filter(r -> r.getTipo() == Review.TipoReviewer.COMUM)
                    .toList();

            if (reviewsComuns.isEmpty()) {
                System.out.printf("🎵 %s (ID: %d) - Sem reviews de reviewers comuns.\n", nomeAlbum, albumId);
            } else {
                double media = reviewsComuns.stream()
                        .mapToInt(Review::getNota)
                        .average()
                        .orElse(0.0);

                System.out.printf("🎵 %s (ID: %d) - Nota média (comuns): %.2f\n", nomeAlbum, albumId, media);
            }
        }
    }

}
