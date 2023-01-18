package com.dw.emp.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.dw.emp.vo.LogsVO;

@Mapper
public interface LogsMapper {
	
	//로그는 수정,삭제를 하지 않는다.
	public int insertLogs(LogsVO vo); //로그 추가
	
	public List<Map<String, Object>> selectLogs(); //로그 조회
	
	public LogsVO selectLogsByLogId(int logId); //로그 상세정보
}
