package com.lmgroup.groupbusiness.security.cipher;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClient;
import com.aliyun.oss.OSSException;
import com.aliyun.oss.model.ObjectMetadata;
import com.aliyun.oss.model.PutObjectResult;
import com.lmgroup.groupbusiness.domain.user.SysLoginVO;
import com.lmgroup.groupbusiness.utils.ParamException;

import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;


public class UpLoadImg {


    public static final String accessKeyId = "LTAIWS5gESliqo4o";

    public static final String accessKeySecret = "p2CAeB7gqsqOTUeDDGZjm5r5N692RT";

    public static final String endpoint = "oss-cn-qingdao.aliyuncs.com";

    public static final String bucketName = "businessshop";

    public static final String read_img_url = "https://businessshop.oss-cn-qingdao.aliyuncs.com";

    public static String upLoadFile(MultipartFile file) throws Exception {
        String result = null;
        InputStream in = file.getInputStream();
        //创建oss客户端
        OSS client = new OSSClient(endpoint, accessKeyId, accessKeySecret);
        try {

            String fileName = file.getOriginalFilename();
            //创建上传Object的Metadate
            ObjectMetadata metadata = new ObjectMetadata();
            metadata.setContentLength(in.available());//定义长度，设置为获取到流的长度
            metadata.setContentEncoding("utf-8");
            metadata.setHeader("Pragma", "no-cache");//设置头部，不缓存
            metadata.setContentType("image/jpeg");
            metadata.setContentDisposition("filename/filesize=" + fileName + "/");//指定该Object被下载时的名称
            //上传文件
            PutObjectResult putResult = client.putObject(bucketName, fileName, in, metadata);
            //获得解析的结果,是唯一MD5数字签名
           // result = putResult.getETag();
            result=read_img_url + "/" + fileName;
            System.out.println(read_img_url + "/" + fileName);
        } catch (OSSException e) {
            throw new ParamException("上传阿里云oss服务器异常");
        } finally {
            in.close();
            client.shutdown();
        }
        return result;
    }
}