package board.board.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import board.board.domain.File;

/**
 * FileRepository File Entity용 JPA Extend Repository
 */
public interface FileRepository extends JpaRepository<File, Long> {

}
