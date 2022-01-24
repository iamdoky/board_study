package com.board_study.web;

import com.board_study.dto.board.BoardRequestDto;
import com.board_study.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author : HP
 * @version 1.0
 * <p>
 * Copyright (C) 2010 by G-INNO  All right reserved.
 * @Class Name : BoardController
 * @Description :
 * @Modification Information
 * <p>
 * 수정일           수정자          수정내용
 * -------------------------------------------------------
 * 2022.01.10      HP        수정내용 1
 * 2010-04-10      O O O        수정내용 2
 * @since 2022.01.10
 */

@RequiredArgsConstructor
@Controller
public class BoardController {

    private final BoardService boardService;

    @GetMapping("/board/list")
    public String getBoardListPage(
            Model model,
            @RequestParam( required = false, defaultValue = "0" ) Integer page,
            @RequestParam( required = false, defaultValue = "5" ) Integer size ) throws Exception {
        try {
            model.addAttribute("resultMap", boardService.findAll( page, size ) );
        } catch ( Exception e ) {
            throw  new Exception( e.getMessage() );
        }

        return "/board/list";
    }

    @GetMapping("/board/write")
    public String getBoardWritePage(Model model, BoardRequestDto boardRequestDto ) {
        return "/board/write";
    }

    @GetMapping("/board/view")
    public String getBoardViewPage(Model model, BoardRequestDto boardRequestDto ) throws Exception {

        try {
            if ( boardRequestDto.getId() != null ) {
                model.addAttribute("info", boardService.findById( boardRequestDto.getId() ));
            }
        } catch ( Exception e ) {
            throw new Exception( e.getMessage() );
        }

        return "/board/view";
    }

    @PostMapping("/board/write/action")
    public String boardWriteAction( Model model, BoardRequestDto boardRequestDto ) throws Exception {
        try {
            Long result = boardService.save(boardRequestDto);

            if ( result < 0 ) {
                throw new Exception( "#Exception boardWriteAction!!" );
            }
        } catch ( Exception e ) {
            throw new Exception( e.getMessage() );
        }

        return "redirect:/board/list";
    }

}
