package kr.idu.OInjo_Shop.dto.Page;

import lombok.Data;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Data
public class PageResultDTO<DTO, EN> {
    private List<DTO> dtoList; // 객체들

    private int totalPage; // 총 페이지 번호
    private int curPage; // 현재 페이지 번호

    private int perPage; // 한 페이지 게시물(레코드 갯수)

    private int perPagination; // 한 페이지 번호 갯수

    private long totalRows; // 총 행의 수(총 갯수)

    private int startRow, endRow; // 시작, 끝 레코드 번호

    private int startNum, endNum; // 시작 페이지 번호, 끝 페이지 번호 : 게시물
    private boolean prev, next; // 버튼 표시

    private List<Integer> pageList; // 페이지 번호 목록

    public PageResultDTO(Page<EN> result, Function<EN, DTO> fn, int perPagination) {
        totalRows = result.getTotalElements();
        dtoList = result.stream().map(fn).collect(Collectors.toList()); // get records
        totalPage = result.getTotalPages();
        this.perPagination = perPagination;
        makePageList(result.getPageable());
    }

    private void makePageList(Pageable pageable) {
        this.curPage = pageable.getPageNumber() + 1;

        this.startRow = 1 + (curPage - 1) * perPage;
        this.endRow = startRow + perPage - 1;

        this.perPage = pageable.getPageSize();

        int tempEnd = (int)(Math.ceil(curPage / ((double) perPagination))) * perPagination;

        startNum = tempEnd - (perPagination - 1);
        endNum = (totalPage > tempEnd) ? tempEnd: totalPage;

        prev = startNum > 1; // 1보다 크면 true, 작으면 false
        next = totalPage > tempEnd;

        // 메소드 체이닝
        pageList = IntStream.rangeClosed(startNum, endNum).boxed().collect(Collectors.toList()); // get pageNumber list
    }
}
