package dan.libraryrestfulapi.domain.repository;

import dan.libraryrestfulapi.domain.model.Library;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LibraryRepository extends JpaRepository<Library, Long> {

}
