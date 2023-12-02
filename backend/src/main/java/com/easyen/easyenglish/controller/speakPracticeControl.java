//package controller;
//
//import service.speakEnService;
////import com.exam.util.ApiResultHandler;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RestController;
//
//
//@RestController
//public class speakPracticeControl {  // Controler负责请求转发，接受页面过来的参数，传给Service处理，接到返回值，再传给页面
//    @Autowired
//    private GPTService gptService;  // 假设有一个服务来处理GPT接口的调用
//    @Autowired
//    private speakEnService SpeakEnService;
//
//    @PostMapping("/practiceSpeaking")
//    public ApiResult practiceSpeaking(@RequestBody SpeakingPracticeRequest request) {
//        String prompt = generatePrompt(request.getTopic(), request.getExamType());
//        String gptResponse = gptService.getGPTResponse(prompt);
//        return ApiResultHandler.buildApiResult(200, "请求成功", gptResponse);
//    }
//
//    private String generatePrompt(String topic, String examType) {
//        // 根据话题和考试类型生成prompt
//        return "话题：" + topic + "，考试类型：" + examType + "。";
//    }
//    SpeakPractice speakPractice = new SpeakPractice();
//    speakPractice.setUserId(user.getId()); // 假设User对象有一个getId()方法
//    //在Java中，Collections 是一个包含静态方法的工具类，这些方法用于操作集合
//    speakPractice.setPracticeDate(new Date()); // 设置当前日期为练习日期
//    speakPractice.setOralQuestions(Collections.singletonList(request.getTopic())); // 设置练习的口语问题
//    speakPractice.setAiAnswers(Collections.singletonList(gptResponse)); // 设置AI的回答
//
//    SpeakEnService.add(speakPractice);
//
//    return ApiResultHandler.buildApiResult(200, "请求成功", gptResponse);
//}
//
