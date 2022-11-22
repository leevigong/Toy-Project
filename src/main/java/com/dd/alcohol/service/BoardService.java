package com.dd.alcohol.service;

import com.dd.alcohol.dto.BoardDto;
import com.dd.alcohol.entity.Board;
import com.dd.alcohol.repository.BoardRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class BoardService {
    private final BoardRepository boardRepository;

    public BoardService(BoardRepository boardRepository) {
        this.boardRepository = boardRepository;
    }

    @Transactional
    public Long savePost(BoardDto boardDto) {
        return boardRepository.save(boardDto.toEntity()).getId();
    }
    @Transactional
    public List<BoardDto> getBoardList() {
        List<Board> boardList = boardRepository.findAll();
        List<BoardDto> boardDtoList = new ArrayList<>();

        for(Board board : boardList) {
            BoardDto boardDto = BoardDto.builder()
                    .id(board.getId())
                    .user(board.getUser())
                    .title(board.getTitle())
                    .content(board.getContent())
                    .regTime(board.getRegTime())
                    .build();
            boardDtoList.add(boardDto);
        }
        return boardDtoList;
    }

    @Transactional
    public BoardDto getPost(Long id) {
        Board board = boardRepository.findById(id).get();

        BoardDto boardDto = BoardDto.builder()
                .id(board.getId())
                .user(board.getUser())
                .title(board.getTitle())
                .content(board.getContent())
//                .fileId(board.getFileId())
                .regTime(board.getRegTime())
                .build();
        return boardDto;
    }
    @Transactional
    public void deletePost(Long id) {
        boardRepository.deleteById(id);
    }


}