package com.neu.demo.controller;
import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.model.GeneratePresignedUrlRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URL;
import java.util.Date;

@RestController
@RequestMapping("/oss")
public class docController {
        // OSS 配置信息（从阿里云控制台获取）
        private static final String endpoint = "https://oss-cn-hangzhou.aliyuncs.com";  // OSS 服务区域
        private static final String accessKeyId = "LTAI5tAzaVUQx1rGpCFpkgZA";  // 从阿里云控制台获取
        private static final String accessKeySecret = "8WaOghHl6oKsvKfujvWredw79gHmAr";  // 从阿里云控制台获取
        private static final String bucketName = "doc-storage";  // 填写你的 OSS 存储空间名称

        // 获取 OSS 上传凭证
        @GetMapping("/get-oss-signature")
        public String getOssSignature() {
            try {
                // 创建 OSS 客户端实例
                OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);

                // 设置签名的过期时间（1小时有效）
                Date expiration = new Date(System.currentTimeMillis() + 3600 * 1000); // 3600秒有效期

                // 设置生成预签名 URL 的请求
                GeneratePresignedUrlRequest generatePresignedUrlRequest = new GeneratePresignedUrlRequest(bucketName, "your-object-key");
                generatePresignedUrlRequest.setExpiration(expiration);  // 设置过期时间

                // 生成预签名 URL
                URL signedUrl = ossClient.generatePresignedUrl(generatePresignedUrlRequest);

                // 关闭 OSS 客户端
                ossClient.shutdown();

                // 返回签名的 URL（供前端使用）
                return signedUrl.toString();
            } catch (Exception e) {
                e.printStackTrace();
                return "获取签名失败";
            }
        }
}
