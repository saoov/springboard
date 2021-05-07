package board.boardtest.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import board.boardtest.Repository.BoardRepository;
import board.boardtest.domain.Board;
import board.boardtest.domain.BoardDto;
import board.boardtest.exception.NoEntityException;
import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class BoardServiceImpl implements BoardService{
    
    private final BoardRepository boardRepository;

    //게시글 리스트
    public List<Board> findAll(){
        List<Board> list = new ArrayList<>();
        boardRepository.findAll().forEach(e -> list.add(e));
        return list;
    }

    //게시글 저장
    public BoardDto save(BoardDto boardDto){
        Board board = boardDto.toEntity();
        boardRepository.save(board);
        return boardDto;
    }

    //id를 이용해 Board에 대한 정보 가져옴
    @Transactional
    public BoardDto findById(Long id){
        Board board = boardRepository.findById(id).get();

        BoardDto boardDto = BoardDto.builder()
                    .id(board.getId())
                    .writer(board.getWriter())
                    .title(board.getTitle())
                    .content(board.getContent())
                    .fileId(board.getFileId())
                    .createdDate(board.getCreatedDate())
                    .build();
        return boardDto;
    }

    //게시글 삭제
    public void delete(Long id){
        Board board = boardRepository.findById(id).orElseThrow(() -> new NoEntityException());

        board.remove();

        boardRepository.save(board);
    }
}
