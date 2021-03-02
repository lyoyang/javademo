package com.lyoyang.test;

import cn.hutool.core.collection.CollectionUtil;
import com.lyoyang.entity.ShowDoc;
import com.lyoyang.entity.ShowDocModel;
import org.apache.commons.io.FileUtils;
import org.apache.http.HttpEntity;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class JulyShowDocUtil {

//    private static final String URL = "https://www.showdoc.cc/server/api/item/updateByApi";
    private static final String URL = "http://10.10.220.121:4999/server/index.php?s=/api/item/updateByApi";


    public static void main(String[] args) throws Exception {
        ShowDoc showDoc = new ShowDoc();
        showDoc.setIsOpenLocal(true);
        showDoc.setApiKey("0eb1187dbe45570eefc832cc8572a7bb906311182");
        showDoc.setApiToken("e6c1c2d8c3f85de5271ca406e44cb2961496239737");
        showDoc.setShowDocUrl(URL);
        List<ShowDocModel> list = new ArrayList<>();
        ShowDocModel showDocModel = new ShowDocModel();
        showDocModel.setEncode(true);
        showDocModel.setFolder("测试上传/测试");
        showDocModel.setTitle("我是一个接口");
        File file = new File("/Users/brian/git_code/api-doc-test/src/main/resources/static/doc/AllInOne.md");
//        File file = new File("/Users/brian/git_code/api-doc-test/src/main/resources/static/doc/index.adoc");
        String content = FileUtils.readFileToString(file);
        showDocModel.setContent(content);
        list.add(showDocModel);
        showDoc.setShowDocModels(list);
        doPost(showDoc);
    }


    public static void doPost(ShowDoc showDoc) throws Exception {
        if(showDoc.getIsOpenLocal()){
            if(CollectionUtil.isEmpty(showDoc.getShowDocModels())){
                throw new Exception("Please provide markdown information！");
            }else{
                for (ShowDocModel showDocModel : showDoc.getShowDocModels()) {
                    HttpClientPost(showDoc.getShowDocUrl(),showDoc.getApiKey(),showDoc.getApiToken(),showDocModel);
                }
            }
        }
    }


    public static void HttpClientPost(String showDocUrl, String apiKey, String apiToken, ShowDocModel showDocModel) {
        try{
            // 获取默认的请求客户端
            CloseableHttpClient client = HttpClients.createDefault();
            // 通过HttpPost来发送post请求
            HttpPost httpPost = new HttpPost(showDocUrl );

            // 第三步：构造list集合，往里面丢数据
            List<BasicNameValuePair> list = new ArrayList<>();
            BasicNameValuePair basicNameValuePair = new BasicNameValuePair("api_key", apiKey);
            BasicNameValuePair basicNameValuePair2 = new BasicNameValuePair("api_token", apiToken);
            BasicNameValuePair basicNameValuePair3 = new BasicNameValuePair("cat_name", showDocModel.getFolder());
            BasicNameValuePair basicNameValuePair4 = new BasicNameValuePair("page_title", showDocModel.getTitle());
            BasicNameValuePair basicNameValuePair5 = new BasicNameValuePair("page_content",showDocModel.getContent());
            list.add(basicNameValuePair);
            list.add(basicNameValuePair2);
            list.add(basicNameValuePair3);
            list.add(basicNameValuePair4);
            list.add(basicNameValuePair5);

            //第二步：我们发现Entity是一个接口，所以只能找实现类，发现实现类又需要一个集合，集合的泛型是NameValuePair类型
            UrlEncodedFormEntity formEntity = new UrlEncodedFormEntity(list,"UTF-8");
            //第一步：通过setEntity，将我们的entity对象传递过去
            httpPost.setEntity(formEntity);
            //HttpEntity
            //是一个中间的桥梁，在httpClient里面，是连接我们的请求与响应的一个中间桥梁，所有的请求参数都是通过HttpEntity携带过去的
            //通过client来执行请求，获取一个响应结果
            CloseableHttpResponse response = client.execute(httpPost);
            HttpEntity entity = response.getEntity();
            String str = EntityUtils.toString(entity, "UTF-8");
            System.out.println(str);
            //关闭
            response.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

}
