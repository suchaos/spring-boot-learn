package com.suchaos.param;

import lombok.Getter;
import lombok.Setter;

/**
 * PageParam
 *
 * @author suchao
 * @date 2018/11/26
 */
@Getter
@Setter
public class PageParam {
    private int beginLine;
    private Integer pageSize = 3;
    private Integer currentPage = 0;

    public int getBeginLine() {
        return pageSize * currentPage;
    }
}
