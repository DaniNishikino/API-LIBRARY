package dan.libraryrestfulapi.domain.controller;

import dan.libraryrestfulapi.domain.model.Autor;
import dan.libraryrestfulapi.domain.model.Library;
import dan.libraryrestfulapi.domain.repository.LibraryRepository;
import dan.libraryrestfulapi.domain.service.LibraryService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/library")
public class LibraryController {

    final
    LibraryService libraryService;

    public LibraryController(LibraryService libraryService) {
        this.libraryService = libraryService;
    }



    @GetMapping
    public ResponseEntity<List<Library>> getAll(){
        return ResponseEntity.ok(libraryService.findAll());
    }
    @GetMapping("/{id}")
    public ResponseEntity<Library> findById(@PathVariable Long id){
        var libraryFound = libraryService.findById(id);
        return ResponseEntity.ok(libraryFound);
    }
    @GetMapping("/autorbooks/{nomeAutor}")
    public ResponseEntity<List<Library>> getAutorBooks(@PathVariable String nomeAutor) {
        var autorBooks = libraryService.findAutorBooks(nomeAutor);
        return ResponseEntity.ok(autorBooks);
    }
    @PostMapping
    public ResponseEntity<Library>createLibrary(@RequestBody Library libraryToCreate){
         var libraryCreated = libraryService.create(libraryToCreate);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(libraryCreated.getId())
                .toUri();
        return ResponseEntity.created(location).body(libraryCreated);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Library> deleteLibrary(@PathVariable Long id){
        var libraryToDelete = libraryService.findById(id);
        libraryService.deleteLibary(libraryToDelete.getId());
        return ResponseEntity.ok(libraryToDelete);
    }
}
