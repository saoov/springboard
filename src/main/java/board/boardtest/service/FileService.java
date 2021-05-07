package board.boardtest.service;

import board.boardtest.model.FileDto;

/**
 * File Entity에 대한 Service(upload, download) 제공 예정
 * 작성일 : 2021-05-07
 */
public interface FileService {
    
    /**
     * 업로드한 파일에 대한 정보 기록
     * @param fileDto
     * @return Long
     */
    public Long saveFile(FileDto fileDto);

    /**
     * id값을 사용하여 파일에 대한 정보 가져옴
     * @param id
     * @return FileDto
     */
    public FileDto getFile(Long id);


}
