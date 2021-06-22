package com.example.usercollegeapp.ebook;

public class EbookData {
    private String pdfTitle,pdfUri;

    public EbookData() {
}

    public EbookData(String pdfTitle, String pdfUri) {
        this.pdfTitle = pdfTitle;
        this.pdfUri = pdfUri;
    }

    public String getPdfTitle() {
        return pdfTitle;
    }

    public void setPdfTitle(String pdfTitle) {
        this.pdfTitle = pdfTitle;
    }

    public String getPdfUri() {
        return pdfUri;
    }

    public void setPdfUri(String pdfUri) {
        this.pdfUri = pdfUri;
    }
}
