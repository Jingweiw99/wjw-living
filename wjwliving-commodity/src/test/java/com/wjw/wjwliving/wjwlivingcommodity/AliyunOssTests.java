package com.wjw.wjwliving.wjwlivingcommodity;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClient;
import com.aliyun.oss.OSSClientBuilder;
import com.wjw.wjwliving.commodity.WjwlivingCommodityApplication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

@SpringBootTest(classes= WjwlivingCommodityApplication.class)
@RunWith(SpringRunner.class)
// junit4需要加上这两个测试时启动springboot
public class AliyunOssTests {
    @Autowired
    private OSSClient ossClient;

    /**
     * aliyun-oss原生SDK
     *
     * @throws FileNotFoundException
     */
    @Test
    public void test1() throws FileNotFoundException {
        // 风险，不然无法上传远程 直接写不通过配置文件不能上传

    }

    /**
     * spring-cloud-starter-alicloud-oss
     * @throws FileNotFoundException
     */
    @Test
    public void test2() throws FileNotFoundException {
        InputStream inputStream = new FileInputStream("C:\\Users\\21176\\Desktop\\wjwimgs\\0523\\abc.png");
        ossClient.putObject("wjwliving-oss", "haha/111.png", inputStream);
        ossClient.shutdown();
    }

}
