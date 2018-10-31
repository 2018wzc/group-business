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
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;


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
            String name=file.getOriginalFilename();
            String suffix = name.substring(name.lastIndexOf(".") + 0);
            String fileName = getRandomFileName()+suffix;
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

    public static void delImage(String imageName)throws Exception{
        //创建OSSClient实例
        OSSClient ossClient=new OSSClient(endpoint,accessKeyId,accessKeySecret);
        //删除文件
        ossClient.deleteObject(bucketName,imageName);
        //关闭OSSClient
        ossClient.shutdown();
    }

    /**
     * 随机生成文件名
     * @return
     */
    public static String getRandomFileName() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd");
        String str = simpleDateFormat.format(new Date());
        Random random = new Random();
        int rannum = (int) (random.nextDouble() * (99999 - 10000 + 1)) + 10000;// 获取5位随机数
        return rannum + str;// 当前时间
    }
}