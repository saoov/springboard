package board.boardtest.service;

import org.springframework.stereotype.Service;

import board.boardtest.Repository.FileRepository;
import board.boardtest.domain.File;
import board.boardtest.model.FileDto;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class FileServiceImpl implements FileService{

    private final FileRepository fileRepository;

    /**
     * 첨부 파일 저장
     */
    @Override
    public Long saveFile(FileDto fileDto) {
        return fileRepository.save(fileDto.toEntity()).getId();
    }

    /**
     * 첨부 파일 Dto 가져옴
     */
    @Override
    public FileDto getFile(Long id) {
        File file = fileRepository.findById(id).get();

        FileDto fileDto = FileDto.builder()
                    .id(id)
                    .origFilename(file.getOrigFilename())
                    .filename(file.getFilename())
                    .filePath(file.getFilePath())
                    .build();
                    return fileDto;
    }
    

}
