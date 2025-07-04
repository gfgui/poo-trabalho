
import controller.AlbumController;
import controller.ReviewController;
import model.Album;
import model.Review;
import model.Reviewer;

import java.util.*;

public class Main {
    private static final Scanner scanner = new Scanner(System.in);
    private static final AlbumController albumController = new AlbumController();
    private static final ReviewController reviewController = new ReviewController();
    private static final List<Reviewer> reviewers = new ArrayList<>();

    public static void main(String[] args) {
        System.out.println("🎵 Bem-vindo ao Sistema de Reviews de Álbuns 🎵");

        while (true) {
            System.out.println("\nEscolha uma opção:");
            System.out.println("1. Cadastrar álbum");
            System.out.println("2. Cadastrar reviewer");
            System.out.println("3. Criar review");
            System.out.println("4. Ver nota média de reviewer");
            System.out.println("5. Listar álbuns");
            System.out.println("6. Listar reviewers");
            System.out.println("7. Listar reviews");
            System.out.println("8. Sair");

            System.out.print("Opção: ");

            int option = Integer.parseInt(scanner.nextLine());

            switch (option) {
                case 1 -> cadastrarAlbum();
                case 2 -> cadastrarReviewer();
                case 3 -> criarReview();
                case 4 -> verNotaMedia();
                case 5 -> listarAlbuns();
                case 6 -> listarReviewers();
                case 7 -> listarReviews();
                case 8 -> {
                    System.out.println("Saindo... 🎧");
                    return;
                }
                default -> System.out.println("Opção inválida.");
            }

        }
    }

    private static void listarAlbuns() {
        List<Album> albuns = albumController.findAll();
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
            for (Reviewer reviewer : reviewers) {
                System.out.printf("- [%d] %s | Gênero favorito: %s | Reviews: %d | Nota média: %.2f%n",
                        reviewer.getId(),
                        reviewer.getNome(),
                        reviewer.getGeneroFavorito(),
                        reviewer.getNumeroDeReviews(),
                        reviewer.getNotaMedia());
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

        Reviewer reviewer = new Reviewer(id, nome, genero);
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

        Reviewer reviewer = reviewers.stream()
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

        Review review = new Review(nota, comentario, new Date(), reviewerId, albumId);
        reviewer.createNewReview(review);
        reviewController.createReview(review);

        System.out.println("✅ Review registrada com sucesso!");
    }

    private static void verNotaMedia() {
        System.out.print("ID do reviewer: ");
        int reviewerId = Integer.parseInt(scanner.nextLine());

        Reviewer reviewer = reviewers.stream()
                .filter(r -> r.getId() == reviewerId)
                .findFirst()
                .orElse(null);

        if (reviewer == null) {
            System.out.println("❌ Reviewer não encontrado.");
        } else {
            System.out.printf("📊 Nota média de %s: %.2f%n", reviewer.getNome(), reviewer.getNotaMedia());
        }
    }
}
