package board.board.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import board.board.domain.Board;

/**
 * BoardRepository Board Entity용 JPA Extend Repository
 */
public interface BoardRepository extends JpaRepository<Board, Long> {

    List<Board> findByTitleContainingIgnoreCase(String title);

}
