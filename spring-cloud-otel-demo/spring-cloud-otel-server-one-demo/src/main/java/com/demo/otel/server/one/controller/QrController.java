package com.demo.otel.server.one.controller;


import com.demo.otel.server.one.qrcode.ZXingUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.OutputStream;

@Slf4j
@RestController
@RequestMapping("/qrcode")
public class QrController {

    @GetMapping("/createQrCode")
    public void createQrCode(HttpServletRequest request, HttpServletResponse response) {
        String urlPath = request.getParameter("urlPath");
        //String urlPath = "https://blog.csdn.net/weixin_44146379";
        try {
            OutputStream os = response.getOutputStream();
            //requestUrl:需要生成二维码的连接，os：响应输出流
            ZXingUtil.encode(urlPath,  os);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
