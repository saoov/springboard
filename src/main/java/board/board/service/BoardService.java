package board.board.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import board.board.domain.Board;
import board.board.model.BoardDto;

/**
 * Board Entity에 대한 CRUD 제공
 */
public interface BoardService {

    /**
     * 게시글 리스트 가져옴
     * 
     * @return List
     */
    public Page<Board> findAll(Pageable pageable);

    /**
     * 1개의 게시글 저장
     * 
     * @param board
     * @return Board
     */
    public Long save(BoardDto boardDto);

    /**
     * Board Entity의 id 값으로 1개의 게시글을 Dto를 통해 가져옴
     * 
     * @param id
     * @return BoardDto
     */
    public BoardDto findById(Long id);

    /**
     * Board Entity의 id를 이용해 게시글 삭제 처리 flag 처리 예정 작성일 : 2021-05-07
     * 
     * @param bno
     */
    public void delete(Long id);

    /**
     * Title과 Content를 이용한 검색기능
     * 
     * @param keyword
     * @return
     */
    public List<BoardDto> searchTitle(String keyword);

    public Long getBoardCount();

    public Integer[] getPageList(Integer curPageNum);

}
