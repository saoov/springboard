package board.boardtest.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

/*
* File Entity
* 참고 : https://kyuhyuk.kr/article/spring-boot/2020/07/22/Spring-Boot-JPA-MySQL-Board-Post-File-Upload-Download
*/
@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class File {
    
    @Id
    @GeneratedValue
    private Long id;

    //업로드 된 실제 파일명
    @Column(nullable = false)
    private String origFilename;

    //서버에 저장된 파일명
    @Column(nullable = false)
    private String filename;

    //파일이 서버에 저장된 위치
    @Column(nullable = false)
    private String filePath;

    @Builder
    public File(Long id, String origFilename, String filename, String filePath){
        this.id = id;
        this.origFilename = origFilename;
        this.filename = filename;
        this.filePath = filePath;
    }
}
