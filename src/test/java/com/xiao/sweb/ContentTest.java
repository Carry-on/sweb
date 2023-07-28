package com.xiao.sweb;

import net.minidev.json.JSONArray;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ContentTest {

    public static void main(String[] args) {
        ArrayList content_list = new ArrayList();
        // 添加文本内容
        Map c_text = new HashMap();
        c_text.put("type", "text");
        c_text.put("text", "这是一段文本形式的内容");
        content_list.add(c_text);
//        // 添加图片内容
//        Map c_image = new HashMap();
//        c_image.put("type", "image");
//        c_image.put("file", "这是图片形式的内容,这里是图片的 url");
//        content_list.add(c_image);
//        // 添加音频内容
//        Map c_audio = new HashMap();
//        c_audio.put("type", "audio");
//        c_audio.put("file", "这是一段文本形式的内容,这里是音频文件的 url");
//        content_list.add(c_audio);
//        // 添加视频内容
//        Map c_video = new HashMap();
//        c_video.put("type", "video");
//        c_video.put("file", "这是视频形式的内容,这里是视频文件的 url','duration':10'");
//        content_list.add(c_video);
        // 添加患者信息
        Map c_patient_meta = new HashMap();
        c_patient_meta.put("type", "patient_meta");
        c_patient_meta.put("age", "15 岁");
        c_patient_meta.put("sex", "男");
        content_list.add(c_patient_meta);

        // 输出结果
        String content = JSONArray.toJSONString(content_list);
        System.out.println(content);
    }
}
