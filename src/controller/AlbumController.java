package controller;

import model.Album;
import java.util.ArrayList;
import java.util.List;

public class AlbumController {
    private List<Album> albuns = new ArrayList<>();

    public List<Album> findAll() {
        return albuns;
    }

    public void createAlbum(Album album) {
        albuns.add(album);
    }

    public void deleteAlbum(int albumId) {
        albuns.removeIf(album -> album.getId() == albumId);
    }

    public void updateAlbum(int id, Album updatedData) {
        for (int i = 0; i < albuns.size(); i++) {
            if (albuns.get(i).getId() == id) {
                albuns.set(i, updatedData);
                return;
            }
        }
    }

    public boolean exists(int albumId) {
        return albuns.stream().anyMatch(album -> album.getId() == albumId);
    }

}
