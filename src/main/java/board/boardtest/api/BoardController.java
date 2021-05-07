package board.boardtest.api;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;


import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import board.boardtest.domain.Board;
import board.boardtest.domain.BoardDto;
import board.boardtest.model.FileDto;
import board.boardtest.service.BoardService;
import board.boardtest.service.FileService;
import board.boardtest.util.MD5Generator;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class BoardController {
    
    private final BoardService boardService;
    private final FileService fileService;

    //단순 등록 페이지 요청
    @GetMapping("/regist")
    public void regist(){
    }

    //테스트 게시판 insert
    @PostMapping("/regist")
    public String regist(@RequestParam("file") MultipartFile files, BoardDto boardDto){
        try{
        String origFilename = files.getOriginalFilename();
        String filename = new MD5Generator(origFilename).toString();
        //실행되는 위치의 'files'폴더에 파일이 저장
        String savePath = System.getProperty("user.dir") + "\\files";
        //파일 저장 폴더가 없으면 생성
        if(!new File(savePath).exists()){
            new File(savePath).mkdir();
        }

        
        String filePath = savePath + "\\" + filename;
        files.transferTo(new File(filePath));

        FileDto fileDto = new FileDto();
        fileDto.setOrigFilename(origFilename);
        fileDto.setFilename(filename);
        fileDto.setFilePath(filePath);

        Long fileId = fileService.saveFile(fileDto);
        boardDto.setFileId(fileId);

        boardService.save(boardDto); //board 등록
        } catch(Exception e){

        }
        return "redirect:/list";
    }

    //게시글 list 요청
    @GetMapping("/list")
    public void getList(Model model){
        List<Board> list = new ArrayList<>();
        boardService.findAll().forEach(e -> list.add(e));
        model.addAttribute("list", list);
    }

    //read, update 페이지 정보 요청
    @GetMapping({"/read","/update"})
    public void getBoard(@RequestParam("id") Long id, Model model){
        BoardDto boardDto = boardService.findById(id);
        model.addAttribute("board", boardDto);
    }

    //delete 요청
    @GetMapping("/delete/{id}")
    public String delete(@PathVariable("id") Long id){
        boardService.delete(id);
        return "redirect:/list";
    }

    //update 요청
    @PostMapping("/update")
    public String update(BoardDto boardDto){
        boardService.save(boardDto);
        return "redirect:/list";
    }

    @GetMapping("/download/{fileId}")
    public ResponseEntity<Resource> fileDownload(@PathVariable("fileId") Long fileId) throws IOException{
        FileDto fileDto = fileService.getFile(fileId);
        Path path = Paths.get(fileDto.getFilePath());
        Resource resource = new InputStreamResource(Files.newInputStream(path));
        return ResponseEntity.ok()
            .contentType(MediaType.parseMediaType("application/octet-stream"))
            .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + fileDto.getOrigFilename() + "\"")
            .body(resource);

    }

    
}
