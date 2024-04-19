package com.app.common.dto;

import org.apache.commons.lang3.ObjectUtils;

import com.fasterxml.jackson.annotation.JsonIgnore;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * 공통 페이지 DTO
 * @author guney
 * @date 2024. 3. 6.
 */
@Getter
@Setter
@NoArgsConstructor
@ToString
@Schema(name = "PageDTO", description = "공통 Page DTO")
public class PageDTO {

    @Schema(description = "페이지번호", example = "1")
    private Integer pageNo;

    @Schema(description = "페이지사이즈", example = "20")
    private Integer pageSize;

    @Schema(description = "전체카운트", example = "")
    private Integer totalRecords;

    @JsonIgnore
    private String strPaging;

    public PageDTO(Integer pageNo, Integer pageSize, Integer totalRecords) {
        this.pageNo = ObjectUtils.isEmpty(pageNo) ? 1 : pageNo;
        this.pageSize = ObjectUtils.isEmpty(pageSize) ? 10 : pageSize;
        this.totalRecords = ObjectUtils.isEmpty(totalRecords) ? null : totalRecords;
    }

//    public int getStartNum() {
//        return getStartNum(null);
//    }
    
    public int getStartNum(Integer p) {
        if (ObjectUtils.isEmpty(p)) return pageNo == 0 ? 0 : (pageNo - 1) * pageSize; 
        return ((p - 1) * pageSize) + 1;
    }

    public void genGrid() {
        this.strPaging = this.genStrPaging();
    }

    public String genStrPaging() {
        String rtnValue = "";

        int perPage = (this.pageSize == -1 || this.pageSize >= 500) ? 500 : this.pageSize;
        int startNum = this.pageNo == 0 ? 0 : (this.pageNo - 1) * this.pageSize;

        if (startNum >= 0 && perPage > 0) {
            rtnValue = String.format("LIMIT %s, %s", startNum, perPage);
        }

        return rtnValue;
    }
}
