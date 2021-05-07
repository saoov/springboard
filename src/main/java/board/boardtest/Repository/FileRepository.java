package board.boardtest.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import board.boardtest.domain.File;

/**
 * FileRepository
 * File Entity용 JPA Extend Repository
 */
public interface FileRepository extends JpaRepository<File, Long>{
    
}
