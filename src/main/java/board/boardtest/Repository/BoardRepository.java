package board.boardtest.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import board.boardtest.domain.Board;

/**
 * BoardRepository
 * Board Entity용 JPA Extend Repository
 */
public interface BoardRepository extends JpaRepository<Board,Long>{
    

}
