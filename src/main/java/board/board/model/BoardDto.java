package board.board.model;

import java.time.LocalDateTime;

import board.board.domain.Board;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Board Entity에 대한 Dto Model
 */
@Getter
@Setter
@NoArgsConstructor
public class BoardDto {

    private Long id;
    private String writer;
    private String title;
    private String content;
    private Long fileId;
    private LocalDateTime createdDate;
    private LocalDateTime modifiedDate;

    /**
     * BoardDto를 Entity로 전환하기 위해 사용
     * 
     * @return
     */
    public Board toEntity() {
        Board build = Board.builder().id(id).writer(writer).title(title).content(content).fileId(fileId).build();
        return build;
    }

    @Builder
    public BoardDto(Long id, String writer, String title, String content, Long fileId, LocalDateTime createdDate,
            LocalDateTime modifiedDate) {
        this.id = id;
        this.writer = writer;
        this.title = title;
        this.content = content;
        this.fileId = fileId;
        this.createdDate = createdDate;
        this.modifiedDate = modifiedDate;
    }
}
