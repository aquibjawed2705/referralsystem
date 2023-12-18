package com.suggest.referal.ReferalSystem.service;

import com.suggest.referal.ReferalSystem.entity.UrlFinder;

import java.util.List;

public interface UrlService {
    List<UrlFinder> getUrlByName(String name);

    void saveUrlByName(UrlFinder urlFinder);
}
