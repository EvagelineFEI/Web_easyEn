package com.easyen.easyenglish.dto;

// 用于传递给GPT的信息，包括批改要点requirements、作文originalEssay
public class essayGenerate {
    private String requirements;
    private String originalEssay;

    public essayGenerate(String requirements, String originalEssay) {
        this.requirements = requirements;
        this.originalEssay = originalEssay;
    }

    public String getRequirements() {
        return requirements;
    }

    public void setRequirements(String requirements) {
        this.requirements = requirements;
    }

    public String getOriginalEssay() {
        return originalEssay;
    }

    public void setOriginalEssay(String originalEssay) {
        this.originalEssay = originalEssay;
    }
}
