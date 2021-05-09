package board.board.model;

import board.board.domain.File;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * File Entity에 대한 Dto 파일 upload/download 위해 사용 예정 작성일 : 2021-05-07
 */
@Getter
@Setter
@NoArgsConstructor
public class FileDto {

    private Long id;
    private String origFilename;
    private String filename;
    private String filePath;

    /**
     * FileDto를 Entity로 변경하기 위해 사용
     * 
     * @return
     */
    public File toEntity() {
        File build = File.builder().id(id).origFilename(origFilename).filename(filename).filePath(filePath).build();
        return build;
    }

    @Builder
    public FileDto(Long id, String origFilename, String filename, String filePath) {
        this.id = id;
        this.origFilename = origFilename;
        this.filename = filename;
        this.filePath = filePath;
    }

}
