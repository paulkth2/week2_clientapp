package com.example.taehyungkim.week2;

import java.util.ArrayList;

public class contents {
    private String ctitle;
    private String ccontent;
    private ArrayList<String> ccomments;

    public ArrayList<String> getCcomments() {
        return ccomments;
    }

    public void setCcomments(ArrayList<String> ccomments) {
        this.ccomments = ccomments;
    }

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


    public contents(String ctitle, String ccontent, ArrayList<String> ccomments) {
        this.ctitle = ctitle;
        this.ccontent = ccontent;
        this.ccomments = ccomments;
    }
}
