package com.ltns.rest_area.domain;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.ltns.rest_area.domain.memberInfo.memberInfoDTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class AjaxList extends AjaxResult{
	
	int totalCnt;	//총 dto 갯수

	int pageNo;		//현재 페이지
	int numOfRows; 	//한 페이지에 리스트할 dto 갯수
	int totalPage;	//총 페이지 갯수
	
	List<DTO> list;	//dto 데이터


	int pagenationPage;	//페이지네이션에 표시할 페이지 갯수
	int writePages ; // list 수 
	
	@Override
	public String toString() {
		return "AjaxList (count=" + getCount() + ", status=" + getStatus() + ", message=" + getMessage()
				+ "totalCnt=" + totalCnt + ", pageNo=" + pageNo + ", numOfRows=" + numOfRows + ", totalPage="
				+ totalPage + ", list=" + list + ", pagenationPage=" + pagenationPage + ", writePages=" + writePages
				+ ")";
	}
}