/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package apihit;

import java.io.IOException;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.ResponseHandler;
import org.apache.http.util.EntityUtils;

/**
 *
 * @author rock and roll
 */
public class MyStringResponseHandler implements ResponseHandler<String> {
     
@Override
    public String handleResponse(HttpResponse hr) throws ClientProtocolException, IOException {
        int status=hr.getStatusLine().getStatusCode();
        if(status>=200 && status<300){
            HttpEntity entity=hr.getEntity();
            try{
                return null == entity ?"": EntityUtils.toString(entity);
                
            }catch(Exception e){
                System.out.println(e);
            }
        }else{
            return "Unexpected response status:"+status;
        }
    HttpEntity entity = null;
        
        return EntityUtils.toString(entity);
    }
}
