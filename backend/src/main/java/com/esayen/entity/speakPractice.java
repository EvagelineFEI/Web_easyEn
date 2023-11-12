//在Java工程中，实体类（也称为JavaBean）是一种特殊的类，通常用于表示业务数据的对象。
// 这些类的主要目的是封装数据，以便在程序中传递和操作。

//关系也可以用对象表达，这样的话，就能使用面向对象编程，来操作关系型数据库。

public class speakPractice{
    private int recordId; // 记录ID
    private int userId; // 用户ID
    private Date practiceDate; // 练习日期
    private List<String> oralQuestions; // 练习的口语问题列表
    private List<String> aiAnswers; // AI给出的话题答案列表
    public speakPractice(){
    }
    public int getRecordId() {
        return recordId;
    }
    public void setRecordId(int recordId) {
        this.recordId = recordId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public Date getPracticeDate() {
        return practiceDate;
    }

    public void setPracticeDate(Date practiceDate) {
        this.practiceDate = practiceDate;
    }

    public List<String> getOralQuestions() {
        return oralQuestions;
    }

    public void setOralQuestions(List<String> oralQuestions) {
        this.oralQuestions = oralQuestions;
    }

    public List<String> getAiAnswers() {
        return aiAnswers;
    }

    public void setAiAnswers(List<String> aiAnswers) {
        this.aiAnswers = aiAnswers;
    }
}