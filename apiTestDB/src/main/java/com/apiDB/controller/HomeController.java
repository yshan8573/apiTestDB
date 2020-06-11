package com.apiDB.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.text.DateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.mybatis.spring.SqlSessionTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	@Autowired
	private SqlSessionTemplate session;
	
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		logger.info("Welcome home! The client locale is {}.", locale);
		
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
		
		String formattedDate = dateFormat.format(date);
		
		model.addAttribute("serverTime", formattedDate );
		
		return "home";
	}
	
	@RequestMapping(value = "/apiDBList")
	public String apiDBList() {
		logger.info("This is a apiDBList Method");
		
		return "apiDBList";
	}
	
	@RequestMapping(value = "/PublicData.do", produces="application/text; charset=utf8")
	@ResponseBody
	public String publicData() throws IOException {
		logger.info("This is a publicData Method");
		
		//String numS = Integer.toString(num);
		int count = 0;
		
        StringBuilder urlBuilder = new StringBuilder("http://apis.data.go.kr/1470000/RareMdcinInfoService/getRareMdcinList"); /*URL*/
        urlBuilder.append("?" + URLEncoder.encode("ServiceKey","UTF-8") + "=clwG26IfMXKWuoQJxzZUTaxwoW0XVvV%2BqvtVeIyz7jtM2v7dkhx0GKs6KhmVH7smrdq6biNzfdnWZHL6%2BxrCtg%3D%3D"); /*Service Key*/
        urlBuilder.append("&" + URLEncoder.encode("pageNo","UTF-8") + "=" + URLEncoder.encode("5", "UTF-8")); /*페이지번호*/
        urlBuilder.append("&" + URLEncoder.encode("numOfRows","UTF-8") + "=" + URLEncoder.encode("60", "UTF-8")); /*한 페이지 결과 수*/
        URL url = new URL(urlBuilder.toString());
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        conn.setRequestProperty("Content-type", "application/json");
        //System.out.println("Response code: " + conn.getResponseCode());
        BufferedReader rd;
        if(conn.getResponseCode() >= 200 && conn.getResponseCode() <= 300) {
            rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        } else {
            rd = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
        }
        StringBuilder sb = new StringBuilder();
        String line;
        while ((line = rd.readLine()) != null) {
            sb.append(line + "\n");
            
        }
        rd.close();
        conn.disconnect();
        //System.out.println(sb.toString());
        
        
        
        
        
        Map<String,String> dataMap = new HashMap<String,String>(); 
        
        
        DocumentBuilderFactory t_dbf = null;
        DocumentBuilder t_db = null;
        Document t_doc = null;
        NodeList t_nodes = null;
        Node t_node = null;
        Element t_element = null;
        String loc = "";
        String value = "";
        InputSource t_is = new InputSource();
 
        try
        {
            t_dbf = DocumentBuilderFactory.newInstance();
            t_db = t_dbf.newDocumentBuilder();
            t_is = new InputSource();
            t_is.setCharacterStream(new StringReader(sb.toString()));
            t_doc = t_db.parse(t_is);
            t_nodes = t_doc.getElementsByTagName("item");
 
            for (int i = 0, t_len = t_nodes.getLength();
                    i < t_len; i ++)
            {
                t_element = (Element)t_nodes.item(i);
                
                loc = "RARITY_DRUG_APPOINT_NO";
                if(t_element.getElementsByTagName(loc).item(0).getTextContent() == "") {
                	dataMap.put(loc, "");
                } else {
                	dataMap.put(loc, t_element.getElementsByTagName(loc).item(0).getChildNodes().item(0).getTextContent());
                }
                
                loc = "MANUFPLACE_NAME";
                if(t_element.getElementsByTagName(loc).item(0).getTextContent() == "") {
                	dataMap.put(loc, "");
                } else {
                	dataMap.put(loc, t_element.getElementsByTagName(loc).item(0).getChildNodes().item(0).getTextContent());
                }
                
                loc = "APPLY_NAME";
                if(t_element.getElementsByTagName(loc).item(0).getTextContent() == "") {
                	dataMap.put(loc, "");
                } else {
                	dataMap.put(loc, t_element.getElementsByTagName(loc).item(0).getChildNodes().item(0).getTextContent());
                }

                loc = "APPLY_TEL_NO";
                if(t_element.getElementsByTagName(loc).item(0).getTextContent() == "") {
                	dataMap.put(loc, "");
                } else {
                	dataMap.put(loc, t_element.getElementsByTagName(loc).item(0).getChildNodes().item(0).getTextContent());
                }
                
                loc = "MANUFPLACE_ZIP_NO";
                if(t_element.getElementsByTagName(loc).item(0).getTextContent() == "") {
                	dataMap.put(loc, "");
                } else {
                	dataMap.put(loc, t_element.getElementsByTagName(loc).item(0).getChildNodes().item(0).getTextContent());
                }
                
                loc = "MANUFPLACE_ADDR1";
                if(t_element.getElementsByTagName(loc).item(0).getTextContent() == "") {
                	dataMap.put(loc, "");
                } else {
                	dataMap.put(loc, t_element.getElementsByTagName(loc).item(0).getChildNodes().item(0).getTextContent());
                }
                
                loc = "MANUFPLACE_ADDR2";
                if(t_element.getElementsByTagName(loc).item(0).getTextContent() == "") {
                	dataMap.put(loc, "");
                } else {
                	dataMap.put(loc, t_element.getElementsByTagName(loc).item(0).getChildNodes().item(0).getTextContent());
                }
                
                loc = "MANUFPLACE_TEL_NO";
                if(t_element.getElementsByTagName(loc).item(0).getTextContent() == "") {
                	dataMap.put(loc, "");
                } else {
                	dataMap.put(loc, t_element.getElementsByTagName(loc).item(0).getChildNodes().item(0).getTextContent());
                }
                
                loc = "PRODT_NAME";
                if(t_element.getElementsByTagName(loc).item(0).getTextContent() == "") {
                	dataMap.put(loc, "");
                } else {
                	dataMap.put(loc, t_element.getElementsByTagName(loc).item(0).getChildNodes().item(0).getTextContent());
                }
                
                loc = "TARGET_DISEASE";
                if(t_element.getElementsByTagName(loc).item(0).getTextContent() == "") {
                	dataMap.put(loc, "");
                } else {
                	dataMap.put(loc, t_element.getElementsByTagName(loc).item(0).getChildNodes().item(0).getTextContent());
                }
                
                loc = "GOODS_NAME";
                if(t_element.getElementsByTagName(loc).item(0).getTextContent() == "") {
                	dataMap.put(loc, "");
                } else {
                	dataMap.put(loc, t_element.getElementsByTagName(loc).item(0).getChildNodes().item(0).getTextContent());
                }
                
                loc = "MANUF_NAME";
                if(t_element.getElementsByTagName(loc).item(0).getTextContent() == "") {
                	dataMap.put(loc, "");
                } else {
                	dataMap.put(loc, t_element.getElementsByTagName(loc).item(0).getChildNodes().item(0).getTextContent());
                }

                loc = "APPOINT_DATE";
                if(t_element.getElementsByTagName(loc).item(0).getTextContent() == "") {
                	dataMap.put(loc, "");
                } else {
                	dataMap.put(loc, t_element.getElementsByTagName(loc).item(0).getChildNodes().item(0).getTextContent());
                }
                
                loc = "APPOINT_CANCEL_DATE";
                if(t_element.getElementsByTagName(loc).item(0).getTextContent() == "") {
                	dataMap.put(loc, "");
                } else {
                	dataMap.put(loc, t_element.getElementsByTagName(loc).item(0).getChildNodes().item(0).getTextContent());
                }
                
                loc = "RECEIPT_NO";
                if(t_element.getElementsByTagName(loc).item(0).getTextContent() == "") {
                	dataMap.put(loc, "");
                } else {
                	dataMap.put(loc, t_element.getElementsByTagName(loc).item(0).getChildNodes().item(0).getTextContent());
                }
                
                //System.out.println(dataMap.toString());
                session.insert("api.insertApi",dataMap);
                count++;
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        System.out.println(count);
        
        
		return sb.toString();
	}
	
}
