package com.innverse.elearn.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.innverse.elearn.json.QuestionAnswer;
import com.innverse.elearn.json.SaveUserQueAns;
import com.innverse.elearn.model.Level;
import com.innverse.elearn.services.CommonServiceImpl;

@Controller
@Scope("request")
@RequestMapping("/rest")
public class RestController {

	@Autowired
	protected CommonServiceImpl commonService;	

	@RequestMapping(value = "play/quiz/{id}", method = RequestMethod.GET)
	public @ResponseBody Level playQuiz(@PathVariable("id") String levelId){
		System.out.println("levelId :- " + levelId);
		Level jsonLevel = new Level();
		Level level = commonService.getLevelById(levelId);
		jsonLevel.setQuestions(level.getQuestions());
		jsonLevel.setId(level.getId());
		jsonLevel.setLevelname(level.getLevelname());
		jsonLevel.setMasteryScore(level.getMasteryScore());
		jsonLevel.setSeries(level.getSeries());
		return jsonLevel;
	}
	
	@RequestMapping(value="play/saveUserQueAns", method ={RequestMethod.POST})
	public @ResponseBody String saveUserQueAns(@RequestBody SaveUserQueAns saveUserQueAns,HttpSession request){
		System.out.println("Level Id :- " + saveUserQueAns.getLevelId());
		System.out.println("Series Id :- " + saveUserQueAns.getSeriesId());
		System.out.println("user Id :- " + saveUserQueAns.getUserId());
		for(QuestionAnswer qa : saveUserQueAns.getResult()){
			System.out.println("QID :- " + qa.getQuestionId() + " , ANS :- " + qa.getAnswer() + " , Correct :- " + qa.getCorrect());
		}
		commonService.insertQuizData(saveUserQueAns,request);
		return "{\"status\":\"Success\"}";
	}
	
	@RequestMapping(value="play/saveUserQueAnsPreview", method ={RequestMethod.POST})
	public @ResponseBody String saveUserQueAnsPreview(@RequestBody SaveUserQueAns saveUserQueAns,HttpSession request){
		System.out.println("Level Id :- " + saveUserQueAns.getLevelId());
		System.out.println("Series Id :- " + saveUserQueAns.getSeriesId());
		System.out.println("user Id :- " + saveUserQueAns.getUserId());
		for(QuestionAnswer qa : saveUserQueAns.getResult()){
			System.out.println("QID :- " + qa.getQuestionId() + " , ANS :- " + qa.getAnswer() + " , Correct :- " + qa.getCorrect());
		}
		commonService.insertQuizDataPreview();
		return "{\"status\":\"Success\"}";
	}

}
