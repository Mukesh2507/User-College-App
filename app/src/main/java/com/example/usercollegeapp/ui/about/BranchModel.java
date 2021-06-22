package com.example.usercollegeapp.ui.about;

public class BranchModel {
    private  int img;
    private String branchTitle,branchDesc;

    public BranchModel(int img, String branchTitle, String branchDesc) {
        this.img = img;
        this.branchTitle = branchTitle;
        this.branchDesc = branchDesc;
    }

    public int getImg() {
        return img;
    }

    public void setImg(int img) {
        this.img = img;
    }

    public String getBranchTitle() {
        return branchTitle;
    }

    public void setBranchTitle(String branchTitle) {
        this.branchTitle = branchTitle;
    }

    public String getBranchDesc() {
        return branchDesc;
    }

    public void setBranchDesc(String branchDesc) {
        this.branchDesc = branchDesc;
    }
}
