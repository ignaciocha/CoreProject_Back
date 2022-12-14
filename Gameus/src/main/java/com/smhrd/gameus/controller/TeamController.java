package com.smhrd.gameus.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;
import com.smhrd.gameus.model.CategoryInfo;
import com.smhrd.gameus.model.TeamInfo;
import com.smhrd.gameus.model.TeamMember;
import com.smhrd.gameus.service.TeamService;

@RestController
public class TeamController {
	
	Gson gson = new Gson();
	
	@Autowired
	TeamService teamService;
	
	@PostMapping("/api/team")
	public void teamAdd(@RequestBody Map<String, Object> newTeamInfo) {
		teamService.teamAdd(newTeamInfo);
	}
	
	@GetMapping("/api/allteam")
    public List<Map<String,Object>> selectAllTeam(){
        return teamService.selectAllTeam();
	}

	@PostMapping("/api/teamcheck/{team_seq}")
	public String teamMore(@RequestBody HashMap<String, Object> map) {
		
		HashMap<String, Object> hsMap = new HashMap<>();
		
		hsMap.put("selectTm",teamService.selectTm(map));
		hsMap.put("selectOneTeam", teamService.selectOneTeam(map));
		if(teamService.isJoined(map) == null) {
			hsMap.put("isJoined", 0);
		}else {
			hsMap.put("isJoined", teamService.isJoined(map));
		}
		
		Gson gson = new Gson();
		
		String result = gson.toJson(hsMap);
		
		return result;
	}
	
	@PostMapping("/api/teamjoin")
	public String teamJoin(@RequestBody HashMap<String, Object> tJoin) {
		teamService.teamJoin(tJoin);
		teamService.capNoti(tJoin);
		return teamService.isJoined(tJoin);
	}
	
	@GetMapping("/api/gamesetting")
	public List<CategoryInfo> teamGameSetting() {
		return teamService.teamGameSetting();
	}
	
	// 내 팀 목록 보기
	@PostMapping("/api/myteam")
	public List<TeamInfo> myteam(@RequestBody HashMap<String, Object> map) {
		System.out.println(map);
		return teamService.selectMyTeam(map);
	}
	
	@PostMapping("/api/notiteamname")
	public String notiTeamName(@RequestBody Map<String, Object> team_seq) {
		return teamService.notiTeamName(team_seq);
	}
	
	@PostMapping("/api/teamaccess/{team_seq}")
	public Map<String, Object> teamAccess(@PathVariable("team_seq")String team_seq, @RequestBody Map<String, Object> map) {
		map.put("team_seq", team_seq);
		return teamService.teamAccess(map);
	}

	
}
