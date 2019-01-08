package com.example.taehyungkim.week2;

public class contents {
    private String ctitle;
    private String ccontent;


    public String getCtitle() {
        return ctitle;
    }

    public void setCtitle(String ctitle) {
        this.ctitle = ctitle;
    }

    public String getCcontent() {
        return ccontent;
    }

    public void setCcontent(String ccontent) {
        this.ccontent = ccontent;
    }



    public contents(String ctitle, String ccontent) {
        this.ctitle = ctitle;
        this.ccontent = ccontent;
    }

}
