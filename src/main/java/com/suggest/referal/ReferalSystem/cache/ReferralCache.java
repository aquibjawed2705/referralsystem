package com.suggest.referal.ReferalSystem.cache;

import com.suggest.referal.ReferalSystem.response.SearchResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Component
@Slf4j
public class ReferralCache {

	private Map<String, List<SearchResponse>> searchResponseMap = new ConcurrentHashMap<>();

	public void put(String searchString, List<SearchResponse> searchResponse) {
		searchResponseMap.put(searchString, searchResponse);
	}

	public List<SearchResponse> get(String searchString) {
		return searchResponseMap.get(searchString);
	}

	public void clear() {
		searchResponseMap.clear();
	}

}
