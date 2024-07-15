package dan.libraryrestfulapi.domain.service;

import dan.libraryrestfulapi.domain.model.Autor;
import dan.libraryrestfulapi.domain.model.Library;
import dan.libraryrestfulapi.domain.repository.AutorRepository;
import dan.libraryrestfulapi.domain.repository.LibraryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
public class LibraryService {
    private final LibraryRepository libraryRepository;

    public LibraryService(LibraryRepository libraryRepository, AutorRepository autorRepository) {
        this.libraryRepository = libraryRepository;
        this.autorRepository = autorRepository;
    }
   final AutorRepository autorRepository;


    public List<Library> findAll(){
        return libraryRepository.findAll();
    }

    public Library findById(Long id){
        return libraryRepository.findById(id).orElseThrow(NoSuchElementException::new);
    }
    public Library create(Library libraryToCreate){
        if (libraryRepository.existsById(libraryToCreate.getId())){
            throw new IllegalArgumentException("this book already exists");
        }else {
            return libraryRepository.save(libraryToCreate);
        }
    }
    public List<Library> findAutorBooks(String nomeAutor){
        return libraryRepository.findAll().stream()
                .filter(library -> library.getAutores().stream()
                        .anyMatch(autor -> autor.getNomeAutor().equalsIgnoreCase(nomeAutor)))
                .collect(Collectors.toList());
    }
    public void deleteLibary(Long id){
        libraryRepository.deleteById(id);
    }
}
