package board.board.service;

import org.springframework.stereotype.Service;

import board.board.domain.File;
import board.board.model.FileDto;
import board.board.repository.FileRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class FileServiceImpl implements FileService {

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

        FileDto fileDto = FileDto.builder().id(id).origFilename(file.getOrigFilename()).filename(file.getFilename())
                .filePath(file.getFilePath()).build();
        return fileDto;
    }

}
