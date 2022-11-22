package com.dd.alcohol.controller;

import com.dd.alcohol.dto.BoardDto;
import com.dd.alcohol.dto.FileDto;
import com.dd.alcohol.entity.Board;
import com.dd.alcohol.repository.BoardRepository;
import com.dd.alcohol.service.BoardService;
import com.dd.alcohol.service.FileService;
import com.dd.alcohol.util.MD5Generator;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;

@Controller
@RequestMapping(value="/board")

public class BoardController {

    private BoardService boardService;
    private FileService fileService;
    private final BoardRepository boardRepository;

    public BoardController(BoardService boardService, FileService fileService, BoardRepository boardRepository) {
        this.boardService = boardService;
        this.fileService = fileService;
        this.boardRepository = boardRepository;
    }


    @GetMapping("/list")
    public String list(Model model, @PageableDefault(size = 2) Pageable pageable, String searchText) {
        Page<Board> boards = boardRepository.findByTitleContainingOrContentContaining(searchText, searchText, pageable);
        int startPage = Math.max(1, boards.getPageable().getPageNumber()-4);
        int endPage = Math.min(boards.getTotalPages(),boards.getPageable().getPageNumber()+4);
        model.addAttribute("startPage", startPage);
        model.addAttribute("endPage", endPage);
        model.addAttribute("boards", boards);

        return "boards/list";
    }



    @GetMapping("/post")
    public String post() {
        return "boards/post";
    }

    @PostMapping("/post")
    public String write(@RequestParam("file") MultipartFile files, BoardDto boardDto) {
        try {
            String origFilename = files.getOriginalFilename();
            String filename = new MD5Generator(origFilename).toString();
            /* 실행되는 위치의 'files' 폴더에 파일이 저장됩니다. */
            String savePath = System.getProperty("user.dir") + "\\files";
            /* 파일이 저장되는 폴더가 없으면 폴더를 생성합니다. */
            if (!new File(savePath).exists()) {
                try{
                    new File(savePath).mkdir();
                }
                catch(Exception e){
                    e.getStackTrace();
                }
            }
            String filePath = savePath + "\\" + filename;
            files.transferTo(new File(filePath));

            FileDto fileDto = new FileDto();
            fileDto.setOrigFilename(origFilename);
            fileDto.setFilename(filename);
            fileDto.setFilePath(filePath);
            

            Long fileId = fileService.saveFile(fileDto);

//            boardDto.setFileId(fileId);
            boardService.savePost(boardDto);
        } catch(Exception e) {
            e.printStackTrace();
        }
        return "redirect:/board/list";
    }

    @GetMapping("/post/{id}")
    public String detail(@PathVariable("id") Long id, Model model) {
        BoardDto boardDto = boardService.getPost(id);
        model.addAttribute("post", boardDto);
        return "boards/detail";
    }

    @GetMapping("/post/edit/{id}")
    public String edit(@PathVariable("id") Long id, Model model) {
        BoardDto boardDto = boardService.getPost(id);
        model.addAttribute("post", boardDto);
        return "boards/edit";
    }

    @PutMapping("/post/edit/{id}")
    public String update(BoardDto boardDto) {
        boardService.savePost(boardDto);
        return "redirect:/board/list";
    }

    @DeleteMapping("/post/{id}")
    public String delete(@PathVariable("id") Long id) {
        boardService.deletePost(id);
        return "redirect:/board/list";
    }





}

