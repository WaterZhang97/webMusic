package com.niit.controller;

import com.niit.service.interfaces.IMusicService;
import com.niit.util.JSONUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

@Controller
public class MusicController {

    @Autowired
    private JSONUtil jsonUtil;

    @Autowired
    private IMusicService musicService;

    @RequestMapping(value = "/getNewMusic", produces = "plain/text; charset=UTF-8")
    @ResponseBody
    public String getNewMusic(@RequestParam(required = false) Integer userId, HttpServletRequest request, HttpServletResponse response) {
        System.out.println("getNewMusic");
        String newMusic = musicService.getNewMusic();
        System.out.println(newMusic);
        return newMusic;
    }

    @RequestMapping(value = "/getSearchMusic", produces = "plain/text; charset=UTF-8")
    @ResponseBody
    public String getSearchMusic(@RequestBody String json, HttpServletRequest request, HttpServletResponse response) {
        System.out.println("getSearchMusic" + json);
        Map<String, Object> map = jsonUtil.readValue(json, Map.class);
        String search = "";
        for (Map.Entry<String, Object> entry : map.entrySet()) {
            search = (String) entry.getValue();
        }
        String comment = musicService.getSearchMusic(search);
        return comment;
    }

    @RequestMapping(value = "/getIntroAlbum", produces = "plain/text; charset=UTF-8")
    @ResponseBody
    public String getIntroAlbum(@RequestParam(required = false) Integer userId, HttpServletRequest request, HttpServletResponse response) {
        System.out.println("getIntroAlbum");
        String introAlbums = musicService.getIntroAlbum();
        return introAlbums;
    }

    @RequestMapping(value = "/deleteNewMusic", produces = "plain/text; charset=UTF-8")
    @ResponseBody
    public String deleteNewMusic(@RequestBody String json, HttpServletRequest request, HttpServletResponse response) {
        System.out.println("deleteNewMusic" + json);
        Map<String, Object> map = jsonUtil.readValue(json, Map.class);
        int mid = 0;
        for (Map.Entry<String, Object> entry : map.entrySet()) {
            mid = (Integer) entry.getValue();
        }
        return jsonUtil.toJSon(musicService.deleteNewMusic(mid));
    }

}
