package com.board_study;

import com.board_study.dto.board.BoardRequestDto;
import com.board_study.dto.board.BoardResponseDto;
import com.board_study.service.BoardService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class BoardStudy01ApplicationTests {
    
    @Autowired
    private BoardService boardService;
            
    @Test
    void save() {
        BoardRequestDto boardSaveDto = new BoardRequestDto();
        
        boardSaveDto.setTitle("테스트");
        boardSaveDto.setContent("헬로우");
        boardSaveDto.setRegisterId("이츠미");

        Long result = boardService.save(boardSaveDto);

        if ( result > 0 ) {
            System.out.println("######## 성공 ##########");
            findAll();
            findById(result);
        } else {
            System.out.println("########## 실패 ############");
        }
    }
    
    void findAll() {
        List<BoardResponseDto> list = boardService.findAll();
        
        if ( list != null ) {
            System.out.println("### 성공 findAll() : " + list.toString());
        } else {
            System.out.println("## 실패 findAll() ###");
        }
    }
    
    void findById( Long id ) {
        BoardResponseDto info = boardService.findById(id);
        
        if ( info != null ) {
            System.out.println("### 성공 findById() : " + info.toString());
            updateBoard(id);
        } else {
            System.out.println("### 실패 findById() : ");
        }
    } 
    
    void updateBoard ( Long id ) {
        BoardRequestDto boardRequestDto = new BoardRequestDto();
        
        boardRequestDto.setId(id);
        boardRequestDto.setTitle("업데이트 제목");
        boardRequestDto.setContent("업데이트 내용");
        boardRequestDto.setRegisterId("작성자");
        
        int result = boardService.updateBoard(boardRequestDto);
        
        if (  result > 0 ) {
            System.out.println("### 성공 updaterBoard() ###");
        } else {
            System.out.println("### 실패 updaterBoard() ###");
        }
        
    }
    

}
