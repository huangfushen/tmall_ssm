package com.tmall.util;

public class Page {
    private int start; //开始页数
    private int count; //每页显示个数
    private int total; //总个数
    private String param; //参数

    private static final int defaultCount = 10; //默认每页显示10条

    public void setStart(int start) {
        this.start = start;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public void setTotal(int total) {
        this.total = total;
    }


    public int getStart() {
        return start;
    }

    public int getCount() {
        return count;
    }

    public int getTotal() {
        return total;
    }

    public String getParam() {
        return param;
    }
    public void setParam(String param) {
        this.param = param;
    }

    public Page(){
        count = defaultCount;
    }
    public Page(int strat , int count){
        this();
        this.start = strat;
        this.count = count;
    }

    //根据 每页显示的数量count以及总共有多少条数据total，计算出总共有多少页
    public int getTotalPage(){
        int totalPage;
        if(0 == total %count)
            totalPage = total /count;
            // 假设总数是51，不能够被5整除的，那么就有11页
        else
            totalPage = total / count + 1;
        if(0==totalPage)
            totalPage = 1;
        return totalPage;
    }

    //计算出最后一页开始的值的数值
    public int getLast(){
        int last;
        // 假设总数是50，是能够被5整除的，那么最后一页的开始就是45
        if (0 == total % count)
            last = total - count;
            // 假设总数是51，不能够被5整除的，那么最后一页的开始就是50
        else
            last = total - total % count;
        last = last<0?0:last;
        return last;
    }

    //判断是否有前一页
    public boolean isHasPreviouse(){
        if(start==0)
            return false;
        return true;
    }

    //判断是否有后一页
    public boolean isHasNext(){
        if(start==getLast())
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "Page [start=" + start + ", count=" + count + ", total=" + total + ", getStart()=" + getStart()
                + ", getCount()=" + getCount() + ", isHasPreviouse()=" + isHasPreviouse() + ", isHasNext()="
                + isHasNext() + ", getTotalPage()=" + getTotalPage() + ", getLast()=" + getLast() + "]";
    }


}
