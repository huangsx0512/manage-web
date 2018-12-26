package com.zimmur.platform.manage.web.util;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.security.GeneralSecurityException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLException;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocket;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLContextBuilder;
import org.apache.http.conn.ssl.TrustStrategy;
import org.apache.http.conn.ssl.X509HostnameVerifier;
import org.apache.http.entity.FileEntity;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import com.alibaba.fastjson.JSONObject;

/**
 * Http Client工具类
 * @author michale
 *
 */
public class HttpClientUtil {	
	private static PoolingHttpClientConnectionManager connMgr;  
    private static RequestConfig requestConfig;  
    private static final int MAX_TIMEOUT = 7000;
	public static void main(String[] args) {
		String strResponse;
		try {
			strResponse = doGet("http://www.baidu.com", null);
			System.out.println(strResponse);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	static {  
        // 设置连接池  
        connMgr = new PoolingHttpClientConnectionManager();  
        // 设置连接池大小  
        connMgr.setMaxTotal(100);  
        connMgr.setDefaultMaxPerRoute(connMgr.getMaxTotal());  
  
        RequestConfig.Builder configBuilder = RequestConfig.custom();  
        // 设置连接超时  
        configBuilder.setConnectTimeout(MAX_TIMEOUT);  
        // 设置读取超时  
        configBuilder.setSocketTimeout(MAX_TIMEOUT);  
        // 设置从连接池获取连接实例的超时  
        configBuilder.setConnectionRequestTimeout(MAX_TIMEOUT);  
        // 在提交请求之前 测试连接是否可用  
        configBuilder.setStaleConnectionCheckEnabled(true);  
        requestConfig = configBuilder.build();  
    }  
		
	public static String doGet(String url, List params) {
		String response="";
		try {
			response = doGet(url, params, 5000);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return response;
	}
	
	public static InputStream String2Inputstream(String str) {
		return new ByteArrayInputStream(str.getBytes());
	}
		/**
	   httpClient的get请求方式
	   * @return
	   * @throws Exception
	   */
	  public static String doGet(String url,List params, Integer timeOut)
	      throws Exception {
		HttpClientBuilder httpClientBuilder = HttpClientBuilder.create();
		// HttpClient
		CloseableHttpClient closeableHttpClient = httpClientBuilder.build();
		RequestConfig requestConfig = RequestConfig.custom().setConnectionRequestTimeout(timeOut)
				.setConnectTimeout(timeOut).setSocketTimeout(timeOut).build();
		
		URIBuilder uri = new URIBuilder();
	    uri.setPath(url);
	    if(params != null){
	    	uri.addParameters(params);
	    }	    
	    
		/* 2 生成 GetMethod 对象并设置参数 */
		HttpGet httpGet = new HttpGet(uri.build());
		httpGet.setConfig(requestConfig);
		
		String response = "";
		/* 3 执行 HTTP GET 请求 */
		try {			
			HttpResponse res = closeableHttpClient.execute(httpGet);
			if (res.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
				// 读取 HTTP 响应内容，这里简单打印网页内容
				response = EntityUtils.toString(res.getEntity());
			}
		} catch (Exception e) {
			// 发生致命的异常，可能是协议不对或者返回的内容有问题
			e.printStackTrace();
		} finally {
			/* 6 .释放连接 */
			closeableHttpClient.close();
		}
		return response;
	  }
	  /**
	   * get請求
	   * @param url
	   * @param charset
	   * @return jsonObject
	   */
	  public static JSONObject doGetParserJson(String url, List params){
		  JSONObject jsonObject = null;
		  try {
			  jsonObject = JSONObject.parseObject(doGet(url, params));
			} catch (Exception e) {
				e.printStackTrace();
			}
		  return jsonObject;
	  }
	  /**
	   * post请求
	   * @param url
	   * @param map
	   * @return string
	   */
	  public static String doPost(String url, Map<String, Object> map) {
		  String response="";
		  // 创建参数队列  
	      List<NameValuePair> formparams = new ArrayList<NameValuePair>();
	      Iterator<Entry<String, Object>> iter = map.entrySet().iterator();
	      while(iter.hasNext()){
	    	  Entry<String, Object> entry = iter.next();
	    	  formparams.add(new BasicNameValuePair(entry.getKey(), entry.getValue().toString()));  
	      }
	      
	      UrlEncodedFormEntity entity;
	      try{
	    	  entity = new UrlEncodedFormEntity(formparams,"UTF-8");
	    	  response = doPost(url, entity);
	      }catch(Exception ex){
	    	  ex.printStackTrace();
	      }
		  return response;
	  }
	  /**
	   * Post請求
	   * @param url
	   * @param map
	   * @return JsonObject
	   */
	  public static JSONObject doPostParseJson(String url, Map<String, Object> map){
		  return JSONObject.parseObject(doPost(url, map));
	  }
	  /**
	   * post请求
	   * @param url
	   * @param json
	   * @return jsonObject
	   */
	public static JSONObject doPost(String url, JSONObject json) {
		JSONObject response = null;
		try {
			StringEntity s = new StringEntity(json.toString());
			s.setContentEncoding("UTF-8");
			s.setContentType("application/json");// 发送json数据需要设置contentType
			String result = doPost(url, s);
			response = JSONObject.parseObject(result);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return response;
	}
	public static String doPostUploadImage(String url, File file) {
		String response = null;
		try {
			HttpEntity entity=new FileEntity(file);
			 response=doPost(url, entity);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return response;
	}
	
	public static String doPostXml(String url,String xmlString){
		String response = null;
		try {
			StringEntity s = new StringEntity(xmlString,"UTF-8");
			s.setContentEncoding("UTF-8");
			s.setContentType("text/xml");// 发送json数据需要设置contentType
			response = doPost(url, s);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return response;
	}
	
	private static String doPost(String url, HttpEntity entity) {
		HttpClientBuilder httpClientBuilder = HttpClientBuilder.create();
		// HttpClient
		CloseableHttpClient closeableHttpClient = httpClientBuilder.build();

		HttpPost post = new HttpPost(url);
		String response = null;
		try {
			
			post.setEntity(entity);
			HttpResponse res = closeableHttpClient.execute(post);
			if (res.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
				response = EntityUtils.toString(res.getEntity(),"utf-8");
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		} finally {
			// 释放资源
			try {
				closeableHttpClient.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return response;
	}
	/**
	   httpClient的get请求方式
	   * @return
	   * @throws Exception
	   */
	  public static String doGet(String url,List<NameValuePair> params, List<NameValuePair> headers,Integer timeOut)
	      throws Exception {
		HttpClientBuilder httpClientBuilder = HttpClientBuilder.create();
		// HttpClient
		CloseableHttpClient closeableHttpClient = httpClientBuilder.build();
		
		RequestConfig requestConfig = RequestConfig.custom().setConnectionRequestTimeout(timeOut)
				.setConnectTimeout(timeOut).setSocketTimeout(timeOut).build();
		
		URIBuilder uri = new URIBuilder();
	    uri.setPath(url);
	    if(params != null){
	    	uri.addParameters(params);
	    }	    
	    
		/* 2 生成 GetMethod 对象并设置参数 */
		HttpGet httpGet = new HttpGet(uri.build());
		httpGet.setConfig(requestConfig);
		if(headers != null){
			for (NameValuePair nameValuePair : headers) {
				httpGet.addHeader(nameValuePair.getName(),nameValuePair.getValue());
			}
		}
		String response = "";
		/* 3 执行 HTTP GET 请求 */
		try {			
			HttpResponse res = closeableHttpClient.execute(httpGet);
			if (res.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
				// 读取 HTTP 响应内容，这里简单打印网页内容
				response = EntityUtils.toString(res.getEntity());
			}
		} catch (Exception e) {
			// 发生致命的异常，可能是协议不对或者返回的内容有问题
			e.printStackTrace();
		} finally {
			/* 6 .释放连接 */
			closeableHttpClient.close();
		}
		return response;
	  }
	  public static String doGet(String url,List<NameValuePair> params, List<NameValuePair> headers)
			  throws Exception {
		  HttpClientBuilder httpClientBuilder = HttpClientBuilder.create();
		  // HttpClient
		  CloseableHttpClient closeableHttpClient = httpClientBuilder.build();
		  
		  RequestConfig requestConfig = RequestConfig.custom().build();
		  
		  URIBuilder uri = new URIBuilder();
		  uri.setPath(url);
		  if(params != null){
			  uri.addParameters(params);
		  }	    
		  
		  /* 2 生成 GetMethod 对象并设置参数 */
		  HttpGet httpGet = new HttpGet(uri.build());
		  httpGet.setConfig(requestConfig);
		  if(headers != null){
			  for (NameValuePair nameValuePair : headers) {
				  httpGet.addHeader(nameValuePair.getName(),nameValuePair.getValue());
			  }
		  }
		  String response = "";
		  /* 3 执行 HTTP GET 请求 */
		  try {			
			  HttpResponse res = closeableHttpClient.execute(httpGet);
			  if (res.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
				  // 读取 HTTP 响应内容，这里简单打印网页内容
				  response = EntityUtils.toString(res.getEntity());
			  }
		  } catch (Exception e) {
			  // 发生致命的异常，可能是协议不对或者返回的内容有问题
			  e.printStackTrace();
		  } finally {
			  /* 6 .释放连接 */
			  closeableHttpClient.close();
		  }
		  return response;
	  }
	  /**
	   * post请求
	   * @param url
	   * @param json
	   * @return jsonObject
	   */
	  public static String doPost(String url, List<NameValuePair> params, List<NameValuePair> headers) {
			HttpClientBuilder httpClientBuilder = HttpClientBuilder.create();
			// HttpClient
			CloseableHttpClient closeableHttpClient = httpClientBuilder.build();
			
			 //init PostMethod object.
			HttpPost post = new HttpPost(url);
			String response = null;
			try {
				if(params != null){
					UrlEncodedFormEntity entity = new UrlEncodedFormEntity(params, "utf-8");
					post.setEntity(entity);
				}
				
				if(headers != null){
					for (NameValuePair nameValuePair : headers) {
						post.addHeader(nameValuePair.getName(), nameValuePair.getValue());
					}
				}
				HttpResponse res = closeableHttpClient.execute(post);
				if (res.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
					response = EntityUtils.toString(res.getEntity(),"utf-8");
				}
			} catch (Exception e) {
				throw new RuntimeException(e);
			} finally {
				// 释放资源
				try {
					closeableHttpClient.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			return response;
	}
	  /**
	   * post请求
	   * @param url
	   * @param json
	   * @return jsonObject
	   */
	  public static String doPostSendBody(String url,String body, List<NameValuePair> headers) {
		  HttpClientBuilder httpClientBuilder = HttpClientBuilder.create();
		  // HttpClient
		  CloseableHttpClient closeableHttpClient = httpClientBuilder.build();
		  
		  //init PostMethod object.
		  HttpPost post = new HttpPost(url);
		  
		  String response = null;
		  try {
			  if(body != null){
				  post.setEntity(new StringEntity(body,"utf-8"));
			  }
			  
			  if(headers != null){
				  for (NameValuePair nameValuePair : headers) {
					  post.addHeader(nameValuePair.getName(), nameValuePair.getValue());
				  }
			  }
			  HttpResponse res = closeableHttpClient.execute(post);
			  if (res.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
				  response = EntityUtils.toString(res.getEntity(),"utf-8");
			  }
		  } catch (Exception e) {
			  throw new RuntimeException(e);
		  } finally {
			  // 释放资源
			  try {
				  closeableHttpClient.close();
			  } catch (IOException e) {
				  e.printStackTrace();
			  }
		  }
		  return response;
	  }
	  /**
	   * put请求  
	   * @param url
	   * @param json
	   * @return jsonObject
	   */
	  public static String doPutSendBody(String url,String body, List<NameValuePair> headers) {
		  HttpClientBuilder httpClientBuilder = HttpClientBuilder.create();
		  // HttpClient
		  CloseableHttpClient closeableHttpClient = httpClientBuilder.build();
		  
		  HttpPut put = new HttpPut(url);
		  
		  String response = null;
		  try {
			  if(body != null){
				  put.setEntity(new StringEntity(body,"utf-8"));
			  }
			  
			  if(headers != null){
				  for (NameValuePair nameValuePair : headers) {
					  put.addHeader(nameValuePair.getName(), nameValuePair.getValue());
				  }
			  }
			  HttpResponse res = closeableHttpClient.execute(put);
			  if (res.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
				  response = EntityUtils.toString(res.getEntity(),"utf-8");
			  }
		  } catch (Exception e) {
			  throw new RuntimeException(e);
		  } finally {
			  // 释放资源
			  try {
				  closeableHttpClient.close();
			  } catch (IOException e) {
				  e.printStackTrace();
			  }
		  }
		  return response;
	  }
	  /** 
	     * 发送 SSL POST 请求（HTTPS），K-V形式 
	     * @param apiUrl API接口URL 
	     * @param params 参数map 
	     * @return 
	     */  
	    public static String doPostSSL(String apiUrl, List<NameValuePair> params,List<NameValuePair> headers) {  
	        CloseableHttpClient httpClient = HttpClients.custom().setSSLSocketFactory(createSSLConnSocketFactory()).setConnectionManager(connMgr).setDefaultRequestConfig(requestConfig).build();  
	        HttpPost httpPost = new HttpPost(apiUrl);  
	        CloseableHttpResponse response = null;  
	        String httpStr = null;  
	  
	        try {  
	            httpPost.setConfig(requestConfig);  
	            if(params != null){
					UrlEncodedFormEntity entity = new UrlEncodedFormEntity(params, "utf-8");
					httpPost.setEntity(entity);
				}
				
				if(headers != null){
					for (NameValuePair nameValuePair : headers) {
						httpPost.addHeader(nameValuePair.getName(), nameValuePair.getValue());
					}
				}
	            response = httpClient.execute(httpPost);  
	            int statusCode = response.getStatusLine().getStatusCode();  
	            if (statusCode != HttpStatus.SC_OK) {  
	                return null;  
	            }  
	            HttpEntity entity = response.getEntity();  
	            if (entity == null) {  
	                return null;  
	            }  
	            httpStr = EntityUtils.toString(entity, "utf-8");  
	        } catch (Exception e) {  
	            e.printStackTrace();  
	        } finally {  
	            if (response != null) {  
	                try {  
	                    EntityUtils.consume(response.getEntity());  
	                } catch (IOException e) {  
	                    e.printStackTrace();  
	                }  
	            }  
	        }  
	        return httpStr;  
	    }  
	    /** 
	     * 发送 SSL POST 请求（HTTPS），K-V形式 
	     * @param apiUrl API接口URL 
	     * @param params 参数map 
	     * @return 
	     */  
	    public static String doGetSSL(String apiUrl, List<NameValuePair> params,List<NameValuePair> headers) {  
	        CloseableHttpClient httpClient = HttpClients.custom().setSSLSocketFactory(createSSLConnSocketFactory()).setConnectionManager(connMgr).setDefaultRequestConfig(requestConfig).build();  
	        CloseableHttpResponse response = null;  
	        String httpStr = null;  
	  
	        try {  
	        	URIBuilder uri = new URIBuilder();
	        	uri.setPath(apiUrl);
	            if(params != null){
				    uri.addParameters(params);
				}
	            HttpGet httpGet = new HttpGet(uri.build());
	    		httpGet.setConfig(requestConfig);
				
				if(headers != null){
					for (NameValuePair nameValuePair : headers) {
						httpGet.addHeader(nameValuePair.getName(), nameValuePair.getValue());
					}
				}
	            response = httpClient.execute(httpGet);  
	            int statusCode = response.getStatusLine().getStatusCode();  
	            if (statusCode != HttpStatus.SC_OK) {  
	                return null;  
	            }  
	            HttpEntity entity = response.getEntity();  
	            if (entity == null) {  
	                return null;  
	            }  
	            httpStr = EntityUtils.toString(entity, "utf-8");  
	        } catch (Exception e) {  
	            e.printStackTrace();  
	        } finally {  
	            if (response != null) {  
	                try {  
	                    EntityUtils.consume(response.getEntity());  
	                } catch (IOException e) {  
	                    e.printStackTrace();  
	                }  
	            }  
	        }  
	        return httpStr;  
	    }  
	  
	    /** 
	     * 发送 SSL POST 请求（HTTPS），JSON形式 
	     * @param apiUrl API接口URL 
	     * @param json JSON对象 
	     * @return 
	     */  
	    public static String doPostSSL(String apiUrl, Object json) {  
	        CloseableHttpClient httpClient = HttpClients.custom().setSSLSocketFactory(createSSLConnSocketFactory()).setConnectionManager(connMgr).setDefaultRequestConfig(requestConfig).build();  
	        HttpPost httpPost = new HttpPost(apiUrl);  
	        CloseableHttpResponse response = null;  
	        String httpStr = null;  
	  
	        try {  
	            httpPost.setConfig(requestConfig);  
	            StringEntity stringEntity = new StringEntity(json.toString(),"UTF-8");//解决中文乱码问题  
	            stringEntity.setContentEncoding("UTF-8");  
	            stringEntity.setContentType("application/json");  
	            httpPost.setEntity(stringEntity);  
	            response = httpClient.execute(httpPost);  
	            int statusCode = response.getStatusLine().getStatusCode();  
	            if (statusCode != HttpStatus.SC_OK) {  
	                return null;  
	            }  
	            HttpEntity entity = response.getEntity();  
	            if (entity == null) {  
	                return null;  
	            }  
	            httpStr = EntityUtils.toString(entity, "utf-8");  
	        } catch (Exception e) {  
	            e.printStackTrace();  
	        } finally {  
	            if (response != null) {  
	                try {  
	                    EntityUtils.consume(response.getEntity());  
	                } catch (IOException e) {  
	                    e.printStackTrace();  
	                }  
	            }  
	        }  
	        return httpStr;  
	    }  
	  
	    /** 
	     * 创建SSL安全连接 
	     * 
	     * @return 
	     */  
	    private static SSLConnectionSocketFactory createSSLConnSocketFactory() {  
	        SSLConnectionSocketFactory sslsf = null;  
	        try {  
	            SSLContext sslContext = new SSLContextBuilder().loadTrustMaterial(null, new TrustStrategy() {
					
					@Override
					public boolean isTrusted(X509Certificate[] chain, String authType) throws CertificateException {
						// TODO Auto-generated method stub
						return false;
					}
				}).build();  
	            sslsf = new SSLConnectionSocketFactory(sslContext, new X509HostnameVerifier() {  
	  
	                @Override  
	                public boolean verify(String arg0, SSLSession arg1) {  
	                    return true;  
	                }  
	  
	                @Override  
	                public void verify(String host, SSLSocket ssl) throws IOException {  
	                }  
	  
	                @Override  
	                public void verify(String host, X509Certificate cert) throws SSLException {  
	                }  
	  
	                @Override  
	                public void verify(String host, String[] cns, String[] subjectAlts) throws SSLException {  
	                }  
	            });  
	        } catch (GeneralSecurityException e) {  
	            e.printStackTrace();  
	        }  
	        return sslsf;  
	    } 
	  
	      
}
