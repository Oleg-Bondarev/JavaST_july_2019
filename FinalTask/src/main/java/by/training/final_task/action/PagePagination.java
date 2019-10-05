package by.training.final_task.action;

import java.util.Objects;

public class PagePagination {
    private int pagesAmount;
    private int pageNumber;
    private int pageOffset;

    public PagePagination(final int amountRowsFound, final int rowcount,
                          final String pageParameter) {
        if (amountRowsFound % rowcount == 0) {
            pagesAmount = amountRowsFound / rowcount;
        } else {
            pagesAmount = amountRowsFound / rowcount + 1;
        }
    }

    public int getPagesAmount() {
        return pagesAmount;
    }

    public void setPagesAmount(final int newPagesAmount) {
        pagesAmount = newPagesAmount;
    }

    public int getPageNumber() {
        return pageNumber;
    }

    public void setPageNumber(final int newPageNumber) {
        pageNumber = newPageNumber;
    }

    public int getPageOffset() {
        return pageOffset;
    }

    public void setPageOffset(final int newPageOffset) {
        pageOffset = newPageOffset;
    }

    @Override
    public boolean equals(final Object newO) {
        if (this == newO) {
            return true;
        }
        if (newO == null || getClass() != newO.getClass()) {
            return false;
        }
        PagePagination that = (PagePagination) newO;
        return pagesAmount == that.pagesAmount && pageNumber == that.pageNumber
                && pageOffset == that.pageOffset;
    }

    @Override
    public int hashCode() {
        return Objects.hash(getPagesAmount(), getPageNumber(), getPageOffset());
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("PagePagination{");
        builder.append("pagesAmount=");
        builder.append(pagesAmount);
        builder.append(", pageNumber=");
        builder.append(pageNumber);
        builder.append(", pageOffset=");
        builder.append(pageOffset);
        builder.append("}");
        return builder.toString();
    }
}
