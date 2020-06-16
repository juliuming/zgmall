package club.banyuan.mall.integration.model;

import com.github.pagehelper.Page;
import lombok.Data;

import java.util.List;

@Data
public class PageInfomations {
    private int pageNum;
    private int pageSize;
    private int totalPage;
    private long total;
    private List list;
    public PageInfomations(Page page, List list ){
        this.pageNum = page.getPageNum();
        this.pageSize = page.getPageSize();
        this.totalPage = page.getPages();
        this.total = page.getTotal();
        this.list = list;
    }
}
