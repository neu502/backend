package com.neu.demo.controller;

import com.neu.demo.entity.R;
import com.neu.demo.util.AliUtil;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;
@RestController
public class OssController {
    @GetMapping("/getOssSignature")
    public R getOssSignature() {
        try {
            // 获取 OSS 签名
            Map<String, String> signature = AliUtil.getSignature("doc-storage");
            System.out.println(signature);
            return R.ok(signature);  // 返回签名
        } catch (Exception e) {
            return R.error("Failed to get OSS signature");
        }
    }
}
