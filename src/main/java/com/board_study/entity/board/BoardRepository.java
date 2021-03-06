package com.board_study.entity.board;

import com.board_study.dto.board.BoardRequestDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

public interface BoardRepository extends JpaRepository < Board, Long > {

    String UPDATE_BOARD = " UPDATE board_study.board " +
            " SET TITLE = :#{#boardRequestDto.title}, " +
            " CONTENT = :#{#boardRequestDto.content}, " +
            " UPDATE_TIME = NOW() " +
            " WHERE ID = :#{#boardRequestDto.id}";

    static final String UPDATE_BOARD_READ_CNT_INC = " UPDATE board_study.board " +
            " SET READ_CNT = READ_CNT +1 " +
            " WHERE ID = :id ";

    static final String DELETE_BOARD = " DELETE FROM board_study.board WHERE ID IN ( :deleteList )";

    @Transactional
    @Modifying
    @Query(value = UPDATE_BOARD_READ_CNT_INC, nativeQuery = true )
    public int updateBoardReadCntInc ( @Param("id") Long id ) ;

    @Transactional
    @Modifying
    @Query(value = DELETE_BOARD, nativeQuery = true )
    public int deleteBoard ( @Param("deleteList") Long[] deleteList ) ;


    @Transactional
    @Modifying
    @Query( value = UPDATE_BOARD, nativeQuery = true )
    public int updateBoard(@Param( "boardRequestDto" )BoardRequestDto boardRequestDto );

}
