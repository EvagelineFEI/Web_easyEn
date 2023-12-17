package com.easyen.easyenglish.dto;

// 用于传递给GPT的信息，包括批改要点requirements、作文originalEssay
public class essayGenerate {
    private Integer essay_id;
    private String requirements;
    private String essay_title;
    private String essay_content;

    public essayGenerate(Integer essayId, String requirements, String essay_content, String essayTitle) {
        this.essay_id = essayId;
        this.requirements = requirements;
        this.essay_content = essay_content;
        this.essay_title = essayTitle;
    }

    public String getRequirements() {
        return requirements;
    }

    public void setRequirements(String requirements) {
        this.requirements = requirements;
    }

    public String getEssay_content() {
        return essay_content;
    }

    public void setEssay_content(String essay_content) {
        this.essay_content = essay_content;
    }
    public String getEssay_title() {
        return essay_title;
    }
    public void setEssay_title(String essay_title) {
        this.essay_title = essay_title;
    }
    public Integer getEssay_Id() {
        return essay_id;
    }
    public void setEssay_Id(Integer essay_id) {
        this.essay_id = essay_id;
    }

}
