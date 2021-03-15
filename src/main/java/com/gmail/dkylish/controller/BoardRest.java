package com.gmail.dkylish.controller;

import com.gmail.dkylish.converters.BoardConverter;
import com.gmail.dkylish.dto.BoardDTO;
import com.gmail.dkylish.entity.Board;
import com.gmail.dkylish.service.BoardService;
import com.gmail.dkylish.service.FilesStorageService;
import org.apache.commons.io.IOUtils;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class BoardRest {

    private BoardConverter boardConverter;
    private BoardService boardService;
    private  FilesStorageService filesStorageService;

    public BoardRest(BoardService boardService, FilesStorageService filesStorageService,
                     BoardConverter boardConverter) {
        this.boardService = boardService;
        this.filesStorageService = filesStorageService;
        this.boardConverter = boardConverter;
    }

    @PostMapping("/board")
    public Board createBoard (@RequestParam(value = "name") String name){
        return boardService.createBoard(name);
    }

    @GetMapping("/boardFull/{id}")
    public Board getFullBoard(@PathVariable(value = "id") Long id){
       return boardService.getFullBoardById(id);
    }

    @GetMapping("/board/{id}")
    public BoardDTO getBoard(@PathVariable(value = "id") Long id){
        Board boardIdAndNameById = boardService.findBoardIdAndNameById(id);
        return  boardConverter.boardEntityToDTO(boardIdAndNameById);

    }

    @GetMapping("/board")
    public List<BoardDTO> getAllBoards(){
        List<Board> allBoardsEntity = boardService.getAllBoardsWithoutRelations();
        return  allBoardsEntity.stream()
                .map(entity -> boardConverter.boardEntityToDTO(entity))
                .collect(Collectors.toList());

    }

    @PutMapping("/board/{id}")
    public Board updateBoard(@PathVariable(value = "id") Long id,
                             @RequestBody Board board){
        return boardService.updateBoard(id, board);
    }

    @DeleteMapping("/board/{id}")
    public Board deleteBoard(@PathVariable(value = "id") Long id){
        return boardService.deleteBoard(id);
    }

    @PostMapping("/board/upload")
    public String uploadFile(@RequestParam("file") MultipartFile file) {
        String message;
        try {
            filesStorageService.save(file);

            message = "Uploaded the file successfully: " + file.getOriginalFilename();
            return message;
        } catch (Exception e) {
            message = "Could not upload the file: " + file.getOriginalFilename() + "!";
            return message;
        }
    }

    @GetMapping(value = "/board/get-image", produces = MediaType.IMAGE_GIF_VALUE)
    public @ResponseBody byte[] getImageWithMediaType() throws IOException {
        InputStream in = new FileInputStream("src/main/resources/img/Cat.gif");
        return IOUtils.toByteArray(in);
    }





}
