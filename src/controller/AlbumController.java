package controller;

import model.Album;
import java.util.ArrayList;
import java.util.List;

public class AlbumController {
    private List<Album> albuns = new ArrayList<>();

    public void createAlbum(Album album) {
        albuns.add(album);
    }

    public List<Album> getAllAlbuns() {
        return albuns;
    }

    public String getNomeDoAlbum(int id) {
        return albuns.stream()
                .filter(a -> a.getId() == id)
                .map(Album::getNome)
                .findFirst()
                .orElse("Álbum não encontrado");
    }

    public boolean exists(int albumId) {
        return albuns.stream().anyMatch(album -> album.getId() == albumId);
    }

}
