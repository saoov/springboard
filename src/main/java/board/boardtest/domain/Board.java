package board.boardtest.domain;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * 게시글 엔티티
 * 작성일 : 2021-05-06
 * 
 */
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "board")
public class Board extends BaseTimeEntity{
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String writer;

    @Column(nullable = false)
    private String content;
   
    @Column
    private Long fileId;

    @Builder
    public Board(Long id, String writer, String title, String content, Long fileId){
        this.id = id;
        this.writer = writer;
        this.title = title;
        this.content = content;
        this.fileId = fileId;
    }

    /**
     * Board Entity삭제 용도
     * DB에서 delYn을 Y로  바꾸는 방식으로 flag처리.
     * 
     * Q. 선언 위치와 방식에 대해 고민해보기.
     * ->왜 service에서 처리 안하고 여기에다가 만들어 놓았는가??
     */
    public void remove(){
        super.delYn = 'Y';
    }

}
