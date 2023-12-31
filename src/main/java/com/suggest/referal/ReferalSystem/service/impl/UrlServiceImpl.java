package com.suggest.referal.ReferalSystem.service.impl;

import com.suggest.referal.ReferalSystem.entity.UrlFinder;
import com.suggest.referal.ReferalSystem.repository.UrlRepository;
import com.suggest.referal.ReferalSystem.service.UrlService;
import jakarta.transaction.Transactional;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UrlServiceImpl implements UrlService {

  @Autowired
  private UrlRepository urlRepository;

  @Override
  public List<UrlFinder> getUrlByName(String name) {
    return urlRepository.findByName(name);
  }

  @Override
  @Transactional
  public void saveUrlByName(UrlFinder urlFinder) {
    urlRepository.save(urlFinder);
  }
}
