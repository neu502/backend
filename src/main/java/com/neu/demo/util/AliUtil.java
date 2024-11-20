package com.neu.demo.util;

import com.aliyun.oss.OSSClient;
import com.aliyun.oss.common.utils.BinaryUtil;
import com.aliyun.oss.model.GetObjectRequest;
import com.aliyun.oss.model.MatchMode;
import com.aliyun.oss.model.PolicyConditions;
import com.aliyun.oss.model.PutObjectResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.ClassPathResource;

import java.io.File;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @description: 阿里云OSS工具
 * 官方链接地址:https://help.aliyun.com/document_detail/31926.html?spm=a2c4g.11186623.6.1737.5f3e3bd36kleqs
 * 公共云下OSS Region和Endpoint对照表:https://help.aliyun.com/document_detail/31837.htm?spm=a2c4g.11186623.0.0.7b6b2c20Qv3xQw#concept-zt4-cvy-5db
 * 在线预览问题:https://help.aliyun.com/noticelist/articleid/1060057906.html
 * @author TAO
 * @date 2021/11/30 22:26
 */
@Slf4j
public class AliUtil {

    private static String accessKeyId = "LTAI5tSEWCtbQnf9DqmoApY5";

    private static String accessKeySecret = "8WaOghHl6oKsvKfujvWredw79gHmAr";

    //公共云下OSS Region和Endpoint对照表:https://help.aliyun.com/document_detail/31837.htm?spm=a2c4g.11186623.0.0.7b6b2c20Qv3xQw#concept-zt4-cvy-5db
    private static String endpoint = "oss-cn-beijing.aliyuncs.com";

    /**
     * 获取临时访问OSS签名  前端-用签名的方式上传文件
     * @param bucketName
     * @param dir bucket中的目录
     * @return
     */
    public static Map<String, String> getSignature(String bucketName, String dir) {
        Map<String, String> respMap = new LinkedHashMap<>();
        String endpointForSig = endpoint;
        String host = "https://" + bucketName + "." + endpointForSig;

        OSSClient client = new OSSClient(endpointForSig, accessKeyId, accessKeySecret);
        try {
            // 设置过期时间为半小时1800L
            long expireTime = 60 * 30;
            long expireEndTime = System.currentTimeMillis() + expireTime * 1000;
            java.sql.Date expiration = new java.sql.Date(expireEndTime);

            // 创建策略条件
            PolicyConditions policyConditions = new PolicyConditions();
            policyConditions.addConditionItem(PolicyConditions.COND_CONTENT_LENGTH_RANGE, 0, 1048576000);  // 最大文件大小限制
            policyConditions.addConditionItem(MatchMode.StartWith, PolicyConditions.COND_KEY, dir);  // 限定上传目录前缀

            // 打印目录前缀
            log.info("Directory Prefix: {}", dir);

            // 生成PostPolicy
            String postPolicy = client.generatePostPolicy(expiration, policyConditions);
            log.info("Generated PostPolicy: {}", postPolicy);

            byte[] binaryData = postPolicy.getBytes(StandardCharsets.UTF_8);
            String encodedPolicy = BinaryUtil.toBase64String(binaryData);
            log.info("Encoded PostPolicy: {}", encodedPolicy);

            // 计算签名
            String postSignature = client.calculatePostSignature(postPolicy);
            log.info("Generated PostSignature: {}", postSignature);

            // 填充签名参数
            respMap.put("accessKeyId", accessKeyId);
            respMap.put("accessKeySecret", accessKeySecret);
            respMap.put("bucketName", "doc-storage");
            respMap.put("policy", encodedPolicy);
            respMap.put("signature", postSignature);
            respMap.put("dir", dir);
            respMap.put("host", host);
            respMap.put("expire", String.valueOf(expireEndTime / 1000));

            // 打印返回的签名信息
            log.info("Generated OSS signature map: {}", respMap);

        } catch (Exception e) {
            log.error("获取阿里云OSS签名失败", e);  // 打印完整的异常堆栈信息
        }

        return respMap;
    }


    public static Map<String, String> getSignature(String bucketName) {
        System.out.println(bucketName);
        return getSignature(bucketName, "");
    }

    /**
     * 上传文件
     * @param bucketName
     * @param fileName 文件名
     * @param file 文件
     */
    public static void uploadFile(String bucketName, String fileName, File file) {
        // 创建OSSClient实例
        OSSClient ossClient = new OSSClient(endpoint, accessKeyId, accessKeySecret);
        // 上传文件
        PutObjectResult putObjectResult = ossClient.putObject(bucketName, fileName, file);
        String eTag = putObjectResult.getETag();
        log.info("eTag===>", eTag);
        // 关闭client
        ossClient.shutdown();
    }

    /**
     * 从OSS下载文件，将文件存储在项目tmp目录下，文件名是时间戳
     * @param objectName objectName oss文件名
     * @param bucketName 文件本地存储路径
     * @return
     */
    public static String downloadFileFromOSS(String objectName, String bucketName) {
        String resource;
        String OS = System.getProperty("os.name").toLowerCase();
        if (OS.equals("linux")) {
            resource = new ClassPathResource("/background/template_bg_image.jpg").getPath();

        } else {
            resource = AliUtil.class.getClassLoader().getResource("background/template_bg_image.jpg").getPath();
        }
        // 创建OSSClient实例
        OSSClient ossClient = new OSSClient(endpoint, accessKeyId, accessKeySecret);
        // 填写不包含Bucket名称在内的Object完整路径，例如testfolder/exampleobject.txt。
        ossClient.getObject(new GetObjectRequest(bucketName, objectName), new File(resource));
        ossClient.shutdown();
        return resource;
    }

    /***
     * 获取下载地址
     */
    public static String getDownUrl(String key, String bucket) {
        OSSClient client = new OSSClient(endpoint, accessKeyId, accessKeySecret);
        //判断文件是否存在
        // 设置URL过期时间为10年
        Date expiration = new Date(System.currentTimeMillis() + 3600L * 1000 * 24 * 10 * 365);
        // 生成URL
        URL url = client.generatePresignedUrl(bucket, key, expiration);
        client.shutdown();
        String s = url.toString().substring(0, url.toString().indexOf("?"));
        //生产环境中，OSS地址为内网地址，在此处转成外网地址
        if (s.contains("-internal")) {
            s = s.replace("-internal", "");
        }
        return s;
    }

    public static void main(String[] args) {
        String bucketName = "doc-storage";
        File file = new File("C:\\Users\\Administrator\\Desktop\\test.png");
        String fileName = "tttyyy.jpg";
        Map<String, String> signature = AliUtil.getSignature(bucketName);
        log.info("signature===>",signature.toString());
        AliUtil.uploadFile(bucketName, fileName, file);
    }

}

