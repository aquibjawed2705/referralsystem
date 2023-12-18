package com.suggest.referal.ReferalSystem.controller;

import com.suggest.referal.ReferalSystem.entity.UrlFinder;
import com.suggest.referal.ReferalSystem.service.UrlService;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/referral/system")
@Slf4j
public class ReferralSystemController {

    @Autowired
    UrlService urlService;

    @GetMapping("/search")
    public List<UrlFinder> getUrlName(@RequestParam String name, HttpServletResponse httpResponse){
        httpResponse.addHeader("Access-Control-Allow-Origin","*");
        List<UrlFinder> urlFinders=new ArrayList<>();
        try {
            log.info("Received request {}",name);
            urlFinders= urlService.getUrlByName(name);
        }catch (Exception e){
            log.error("Exception is {}",e);
        }
        return urlFinders;
    }

    @GetMapping("/save/url")
    public String saveUrl(@RequestParam String name,@RequestParam String url){
        UrlFinder urlFinder=new UrlFinder();
        urlFinder.setName(name);
        getData(url,urlFinder);
        urlService.saveUrlByName(urlFinder);
        return "Data saved successfully!!";
    }

    private void getData(String link,UrlFinder urlFinder){
        String href=null;
        String src=null;
        if(link.startsWith("<a")){
            String[] parts = link.split("href=");
            String part1[] = parts[1].split("target=\"_blank\"");
            String part2[] = part1[1].split("src=");
            String part3[]=part2[1].split(" ></a><img ");
            href=part1[0];
            src=part3[0];
        }else if(link.contains("<iframe")){
            String[] parts = link.split("src=");
            String part1[] = parts[1].split("></iframe>");
            src=part1[0];
        }
        if(href!=null){
            href=href.substring( 1, href.length() - 1 );
            urlFinder.setHref(href);
        }
        src=src.substring( 1, src.length() - 1 );
        urlFinder.setSrc(src);
    }
}
