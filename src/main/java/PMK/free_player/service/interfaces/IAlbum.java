package PMK.free_player.service.interfaces;

import PMK.free_player.models.Album;

import java.util.List;
import java.util.Optional;

public interface IAlbum {

    List<Album> listarAlbumes();

    List<Album> listarAlbumesPorArtista(Integer idArtista);

    Optional<Album> findAlbumPorId(Integer idAlbum);

    Optional<Album> findAlbumPorArtista(Integer idArtista);

    Album saveAlbum(Album album);

    void deleteAlbumById(Integer idAlbum);




}
