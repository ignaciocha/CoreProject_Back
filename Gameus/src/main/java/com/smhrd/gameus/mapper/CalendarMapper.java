package com.smhrd.gameus.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.smhrd.gameus.model.CalendarInfo;

@Mapper
public interface CalendarMapper {
	
	@Insert("insert into calendar_info values(null, #{start}, #{end}, #{title}, null, 'user_id 001', 1)")
	public void addCalendar(Map<String, Object> schedule);
	
	@Select("select * from calendar_info where team_seq=1")
	public List<CalendarInfo> viewAllCalendar();
}