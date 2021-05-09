package board.board.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import board.board.domain.Board;
import board.board.exception.NoEntityException;
import board.board.model.BoardDto;
import board.board.repository.BoardRepository;
import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class BoardServiceImpl implements BoardService {

    private final BoardRepository boardRepository;

    // 게시글 리스트
    public List<Board> findAll(Integer pageNum) {
        Page<Board> page = boardRepository
                .findAll(PageRequest.of(pageNum - 1, 4, Sort.by(Sort.Direction.ASC, "createdDate")));
        List<Board> boardEntities = page.getContent();

        List<Board> list = new ArrayList<>();

        for (Board boardEntity : boardEntities) {
            list.add(boardEntity);
        }
        // boardRepository.findAll().forEach(e -> list.add(e));
        return list;
    }

    // 게시글 저장
    public BoardDto save(BoardDto boardDto) {
        Board board = boardDto.toEntity();
        boardRepository.save(board);
        return boardDto;
    }

    // id를 이용해 Board에 대한 정보 가져옴
    @Transactional
    public BoardDto findById(Long id) {
        Board board = boardRepository.findById(id).get();

        BoardDto boardDto = BoardDto.builder().id(board.getId()).writer(board.getWriter()).title(board.getTitle())
                .content(board.getContent()).fileId(board.getFileId()).createdDate(board.getCreatedDate()).build();
        return boardDto;
    }

    // 게시글 삭제
    public void delete(Long id) {
        Board board = boardRepository.findById(id).orElseThrow(() -> new NoEntityException());

        board.remove();

        boardRepository.save(board);
    }

    @Override
    public List<Board> searchTitle(String keyword) {
        List<Board> boards = boardRepository.findByTitleContainingIgnoreCase(keyword);
        // List<BoardDto> boardDtoList = new ArrayList<>();
        // if (boards.isEmpty())
        // return boardDtoList;

        // for (Board board : boards) {
        // boardDtoList.add(this.convertEntityToDto(board));
        // }
        return boards;
    }

    @Override
    @Transactional
    public Long getBoardCount() {
        return boardRepository.count();
    }

    @Override
    @Transactional
    public Integer[] getPageList(Integer curPageNum) {
        Integer[] pageList = new Integer[5];
        // 총 게시글 갯수
        Double boardTotalCount = Double.valueOf(this.getBoardCount());
        // 총 게시글 기준으로 계산한 마지막 페이지 번호 계산 (올림으로 계산)
        Integer totalLastPageNum = (int) (Math.ceil((boardTotalCount / 4)));
        // 현재 페이지를 기준으로 블럭의 마지막 페이지 번호 계산
        Integer blockLastPageNum = (totalLastPageNum > curPageNum + 5) ? curPageNum + 5 : totalLastPageNum;

        // 페이지 시작 번호 조정
        curPageNum = (curPageNum <= 3) ? 1 : curPageNum - 2;

        // 페이지 번호 할당
        for (int val = curPageNum, idx = 0; val <= blockLastPageNum; val++, idx++) {
            pageList[idx] = val;
        }
        return pageList;

    }

    private BoardDto convertEntityToDto(Board board) {
        return BoardDto.builder().id(board.getId()).writer(board.getWriter()).title(board.getTitle())
                .content(board.getContent()).fileId(board.getFileId()).createdDate(board.getCreatedDate()).build();
    }

}