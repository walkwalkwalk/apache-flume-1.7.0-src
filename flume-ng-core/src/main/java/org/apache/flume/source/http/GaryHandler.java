/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.apache.flume.source.http;

import java.io.InputStream;
import java.nio.charset.UnsupportedCharsetException;
import java.security.Timestamp;
import java.text.SimpleDateFormat;
import java.util.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.collections.map.HashedMap;
import org.apache.commons.lang.StringUtils;
import org.apache.flume.Context;
import org.apache.flume.Event;
import org.apache.flume.conf.LogPrivacyUtil;
import org.apache.flume.event.EventBuilder;
import org.apache.http.HttpRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.base.Preconditions;

/**
 *
 * GaryHandler for HTTPSource that accepts any binary stream of data as event.
 * BX gary
 *
 */

/**
 * 用来存放 每一条traffic 信息
 * */
class Traffic {

    public static final String PARAMETER_SEPARATOR = "@%@";
    public String arriveTime;
    public String preUrl;
    public String currentUrl;
    public String logId;
    public String sessionId;
    public String userId;
    public String catEnglishName;
    public String remoteIp;
    public String cityId;
    public String clickId;
    public String profile;
    public String testKey1;
    public String testKey2;
    public String testKey3;
    public String testKey4;
    public String originUrlType;
    public String pageDepth;
    public String customValue1;
    public String customValue2;
    public String customValue3;
    public String customValue4;
    public String trackId;
    public String bannerId;
    public String userAgent;
    public String contentUserId;
    public String contentAdId;
    public String cityEnglishName;
    public String reId;
    public String pvId;
    public String fromPvId;
    public String fromDiv;
    public String latlng;
    public String latlngCity;

    public Traffic() {
    }

    public String getArriveTime() {
        return arriveTime;
    }

    public void setArriveTime(String arriveTime) {
        this.arriveTime = arriveTime;
    }

    public String getPreUrl() {
        return preUrl;
    }

    public void setPreUrl(String preUrl) {
        this.preUrl = preUrl;
    }

    public String getCurrentUrl() {
        return currentUrl;
    }

    public void setCurrentUrl(String currentUrl) {
        this.currentUrl = currentUrl;
    }

    public String getLogId() {
        return logId;
    }

    public void setLogId(String logId) {
        this.logId = logId;
    }

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getCatEnglishName() {
        return catEnglishName;
    }

    public void setCatEnglishName(String catEnglishName) {
        this.catEnglishName = catEnglishName;
    }

    public String getRemoteIp() {
        return remoteIp;
    }

    public void setRemoteIp(String remoteIp) {
        this.remoteIp = remoteIp;
    }

    public String getCityId() {
        return cityId;
    }

    public void setCityId(String cityId) {
        this.cityId = cityId;
    }

    public String getClickId() {
        return clickId;
    }

    public void setClickId(String clickId) {
        this.clickId = clickId;
    }

    public String getProfile() {
        return profile;
    }

    public void setProfile(String profile) {
        this.profile = profile;
    }

    public String getTestKey1() {
        return testKey1;
    }

    public void setTestKey1(String testKey1) {
        this.testKey1 = testKey1;
    }

    public String getTestKey2() {
        return testKey2;
    }

    public void setTestKey2(String testKey2) {
        this.testKey2 = testKey2;
    }

    public String getTestKey3() {
        return testKey3;
    }

    public void setTestKey3(String testKey3) {
        this.testKey3 = testKey3;
    }

    public String getTestKey4() {
        return testKey4;
    }

    public void setTestKey4(String testKey4) {
        this.testKey4 = testKey4;
    }

    public String getOriginUrlType() {
        return originUrlType;
    }

    public void setOriginUrlType(String originUrlType) {
        this.originUrlType = originUrlType;
    }

    public String getPageDepth() {
        return pageDepth;
    }

    public void setPageDepth(String pageDepth) {
        this.pageDepth = pageDepth;
    }

    public String getCustomValue1() {
        return customValue1;
    }

    public void setCustomValue1(String customValue1) {
        this.customValue1 = customValue1;
    }

    public String getCustomValue2() {
        return customValue2;
    }

    public void setCustomValue2(String customValue2) {
        this.customValue2 = customValue2;
    }

    public String getCustomValue3() {
        return customValue3;
    }

    public void setCustomValue3(String customValue3) {
        this.customValue3 = customValue3;
    }

    public String getCustomValue4() {
        return customValue4;
    }

    public void setCustomValue4(String customValue4) {
        this.customValue4 = customValue4;
    }

    public String getTrackId() {
        return trackId;
    }

    public void setTrackId(String trackId) {
        this.trackId = trackId;
    }

    public String getBannerId() {
        return bannerId;
    }

    public void setBannerId(String bannerId) {
        this.bannerId = bannerId;
    }

    public String getUserAgent() {
        return userAgent;
    }

    public void setUserAgent(String userAgent) {
        this.userAgent = userAgent;
    }

    public String getContentUserId() {
        return contentUserId;
    }

    public void setContentUserId(String contentUserId) {
        this.contentUserId = contentUserId;
    }

    public String getContentAdId() {
        return contentAdId;
    }

    public void setContentAdId(String contentAdId) {
        this.contentAdId = contentAdId;
    }

    public String getCityEnglishName() {
        return cityEnglishName;
    }

    public void setCityEnglishName(String cityEnglishName) {
        this.cityEnglishName = cityEnglishName;
    }

    public String getReId() {
        return reId;
    }

    public void setReId(String reId) {
        this.reId = reId;
    }

    public String getPvId() {
        return pvId;
    }

    public void setPvId(String pvId) {
        this.pvId = pvId;
    }

    public String getFromPvId() {
        return fromPvId;
    }

    public void setFromPvId(String fromPvId) {
        this.fromPvId = fromPvId;
    }

    public String getFromDiv() {
        return fromDiv;
    }

    public void setFromDiv(String fromDiv) {
        this.fromDiv = fromDiv;
    }

    public String getLatlng() {
        return latlng;
    }

    public void setLatlng(String latlng) {
        this.latlng = latlng;
    }

    public String getLatlngCity() {
        return latlngCity;
    }

    public void setLatlngCity(String latlngCity) {
        this.latlngCity = latlngCity;
    }

    public String getGaryFormatStr() {
        StringBuilder gary_origin = new StringBuilder(1024);

        //0
        gary_origin.append(getArriveTime());
        gary_origin.append(PARAMETER_SEPARATOR);

        //1
        gary_origin.append(getPreUrl());
        gary_origin.append(PARAMETER_SEPARATOR);

        //2
        gary_origin.append(getCurrentUrl());
        gary_origin.append(PARAMETER_SEPARATOR);

        //3
        gary_origin.append(getLogId());
        gary_origin.append(PARAMETER_SEPARATOR);

        //4
        gary_origin.append(getSessionId());
        gary_origin.append(PARAMETER_SEPARATOR);

        //5
        gary_origin.append(getUserId());
        gary_origin.append(PARAMETER_SEPARATOR);

        //6
        gary_origin.append(getCatEnglishName());
        gary_origin.append(PARAMETER_SEPARATOR);

        //7
        gary_origin.append(getRemoteIp());
        gary_origin.append(PARAMETER_SEPARATOR);

        //8
        gary_origin.append(getCityId());
        gary_origin.append(PARAMETER_SEPARATOR);

        //9
        gary_origin.append(getClickId());
        gary_origin.append(PARAMETER_SEPARATOR);

        //10
        gary_origin.append(getProfile());
        gary_origin.append(PARAMETER_SEPARATOR);

        //11
        gary_origin.append(getTestKey1());
        gary_origin.append(PARAMETER_SEPARATOR);

        //12
        gary_origin.append(getTestKey2());
        gary_origin.append(PARAMETER_SEPARATOR);

        //13
        gary_origin.append(getTestKey3());
        gary_origin.append(PARAMETER_SEPARATOR);

        //14
        gary_origin.append(getTestKey4());
        gary_origin.append(PARAMETER_SEPARATOR);

        //15
        gary_origin.append(getOriginUrlType());
        gary_origin.append(PARAMETER_SEPARATOR);

        //16
        gary_origin.append(getPageDepth());
        gary_origin.append(PARAMETER_SEPARATOR);

        //17
        gary_origin.append(getCustomValue1());
        gary_origin.append(PARAMETER_SEPARATOR);

        //18
        gary_origin.append(getCustomValue2());
        gary_origin.append(PARAMETER_SEPARATOR);

        //19
        gary_origin.append(getCustomValue3());
        gary_origin.append(PARAMETER_SEPARATOR);

        //20
        gary_origin.append(getCustomValue4());
        gary_origin.append(PARAMETER_SEPARATOR);

        //21
        gary_origin.append(getTrackId());
        gary_origin.append(PARAMETER_SEPARATOR);

        //22
        gary_origin.append(getBannerId());
        gary_origin.append(PARAMETER_SEPARATOR);

        //23
        gary_origin.append(getUserAgent());
        gary_origin.append(PARAMETER_SEPARATOR);

        //24
        gary_origin.append(getContentUserId());
        gary_origin.append(PARAMETER_SEPARATOR);

        //25
        gary_origin.append(getContentAdId());
        gary_origin.append(PARAMETER_SEPARATOR);

        //26
        gary_origin.append(getCityEnglishName());
        gary_origin.append(PARAMETER_SEPARATOR);

        //27
        gary_origin.append(getReId());
        gary_origin.append(PARAMETER_SEPARATOR);

        //28
        gary_origin.append(getPvId());
        gary_origin.append(PARAMETER_SEPARATOR);

        //29
        gary_origin.append(getFromPvId());
        gary_origin.append(PARAMETER_SEPARATOR);

        //30
        gary_origin.append(getFromDiv());
        gary_origin.append(PARAMETER_SEPARATOR);

        //31
        gary_origin.append(getLatlng());
        gary_origin.append(PARAMETER_SEPARATOR);

        //32
        gary_origin.append(getLatlngCity());

        String gary_str = gary_origin.toString();

        gary_str = gary_str.toLowerCase();
        String dest[] = {"%5c", "%22", "%2c", "%0d%0a", "\\", ",", "\"", "\r\n"};

        for (String d : dest) {
            gary_str = gary_str.replace(d, "");
        }
        gary_str = gary_str.replace(PARAMETER_SEPARATOR, ",");
        return gary_str;
    }
}

public class GaryHandler implements HTTPSourceHandler {

    private static final Logger LOG = LoggerFactory.getLogger(GaryHandler.class);

    public static final String DEFAULT_MANDATORY_PARAMETERS = "";


    //gary 的访问格式，为了和老的统一
    public static final SimpleDateFormat GARY_FROMAT_TIME = new SimpleDateFormat("YYYY-MM-dd HH:mm:ss");

    /**
     * {@inheritDoc}
     */
    @SuppressWarnings("unchecked")
    @Override
    public List<Event> getEvents(HttpServletRequest request, HttpServletResponse response) throws Exception {

        String charset = request.getCharacterEncoding();
        //UTF-8 is default for gary. If no charset is specified, UTF-8 is to
        //be assumed.
        if (charset == null) {
            LOG.debug("Charset is null, default charset of UTF-8 will be used.");
            charset = "UTF-8";
        } else if (!(charset.equalsIgnoreCase("utf-8")
                || charset.equalsIgnoreCase("utf-16")
                || charset.equalsIgnoreCase("utf-32"))) {
            LOG.error("Unsupported character set in request {}. "
                    + "GaryHandler supports UTF-8, "
                    + "UTF-16 and UTF-32 only.", charset);
            throw new UnsupportedCharsetException("GaryHandler supports UTF-8, "
                    + "UTF-16 and UTF-32 only.");
        }

        Traffic traffic = initTraffic(request);
        String gary_str = traffic.getGaryFormatStr();
        LOG.debug("gary str is {}", gary_str);
        updateResponse(traffic, response);
        Event event = EventBuilder.withBody(gary_str.getBytes("UTF-8"));
        List<Event> eventList = new ArrayList<Event>();
        eventList.add(event);
        return eventList;

    }

    public Traffic initTraffic(HttpServletRequest request) {

        Map<String, String> headers = new HashMap<String, String>();
        Map<String, String> cookies = new HashedMap();

        Map<String, String[]> parameters = request.getParameterMap();
        for (String parameter : parameters.keySet()) {
            String value = parameters.get(parameter)[0];
            //LOG.debug("Setting Header [Key, Value] as [{},{}] ", parameter, value);
            headers.put(parameter, value);
        }

        Cookie[] requestCookies = request.getCookies();
        if (requestCookies != null) {
            for (Cookie cookie : requestCookies) {
                cookies.put(cookie.getName(), cookie.getValue());
            }
            //LOG.debug("cookies: {}", cookies.toString());
        }

        HttpSession httpSession = request.getSession();
        LOG.debug(httpSession.toString());

        Date reuqestTime = new Date();
        Traffic traffic = new Traffic();

        //0
        traffic.setArriveTime(getArriveTime(reuqestTime));

        //1
        traffic.setPreUrl(getPreUrl(headers));

        //2
        traffic.setCurrentUrl(getCurrentUrl(headers, request));

        //3
        traffic.setLogId(getLogId(cookies));

        //4
        traffic.setSessionId(getSessionId(cookies));

        //5
        traffic.setUserId(getUserId(headers, cookies));

        //6
        traffic.setCatEnglishName(getCategoryEnglishName(headers));

        //7
        traffic.setRemoteIp(getRemoteIp(request));

        //8
        traffic.setCityId(getCityId(headers));

        //9
        traffic.setClickId(getClickId(cookies));

        //10
        traffic.setProfile(getProfile(cookies));

        //11
        traffic.setTestKey1(getTestKey1(headers));

        //12
        traffic.setTestKey2(getTestKey2(headers));

        //13
        traffic.setTestKey3(getTestKey3(headers));

        //14
        traffic.setTestKey4(getTestKey4(headers));

        //15
        traffic.setOriginUrlType(getOriginUrlType(cookies, headers, request));

        //16
        traffic.setPageDepth(getPageDepth(cookies));

        //17
        traffic.setCustomValue1(getCustomValue1(headers));

        //18
        traffic.setCustomValue2(getCustomValue2(headers));

        //19
        traffic.setCustomValue3(getCustomValue3(headers));

        //20
        traffic.setCustomValue4(getCustomValue4(headers));

        //21
        traffic.setTrackId(getTrackId(headers, cookies));

        //22
        traffic.setBannerId(getBannerId(cookies));

        //23
        traffic.setUserAgent(getUserAget(request));

        //24
        traffic.setContentUserId(getContentUserId(headers));

        //25
        traffic.setContentAdId(getAdId(headers));

        //26
        traffic.setCityEnglishName(getCityEnglishName(headers, cookies));

        //27
        traffic.setReId(getReId((headers)));

        //28
        traffic.setPvId(getPvId(headers));

        //29
        traffic.setFromPvId(getFromPvId(headers));

        //30
        traffic.setFromDiv(getFromDiv(headers));

        //31
        traffic.setLatlng(getLatlng(headers));

        //32
        traffic.setLatlngCity(getLatlngCity(headers));

        return traffic;
    }

    /*
    * 生成一个response ，写成这样主要是为了和老的脚本匹配
    * */
    public String generateUID() {
        StringBuffer uid = new StringBuffer(100);
        Long timestamp = System.currentTimeMillis();
        Random r = new Random(timestamp);
        uid.append(timestamp);
        uid.append(String.valueOf(r.nextInt(1000000000)));
        return uid.toString();

    }


    public void genDeleteCookie(HttpServletResponse response, String key, String domain) {
        Cookie delCookie = new Cookie(key, null);
        delCookie.setMaxAge(0);
        delCookie.setPath("/");
        delCookie.setDomain(domain);
        response.addCookie(delCookie);
    }

    public void setCookie( HttpServletResponse response, String key, String value, int maxAge, String path, String domain) {
        Cookie cookie = new Cookie(key, value);
        cookie.setMaxAge(maxAge);
        cookie.setPath(path);
        cookie.setDomain(domain);
        response.addCookie(cookie);
    }

    /*
    * 设置response
    *
    * */
    public void updateResponse(Traffic traffic, HttpServletResponse response) {

        if (traffic.getLogId() == null) {
            String uid = generateUID();
            setCookie(response, "kjj_log_log_id", uid, 315360000, "/", "gary.baixing.com");
        }

        if (traffic.getSessionId() == null) {
            String uid = generateUID();
            setCookie(response, "kjj_log_session_id", uid, -1, "/", "gary.baixing.com");
        }

//        if (traffic.getClickId() != null) {
//            genDeleteCookie(response, "kjj_trace_click_id", "ary.baixing.com");
//            genDeleteCookie(response, "kjj_trace_click_id", "baixing.com");
//            String clickId = traffic.getClickId();
//            setCookie(response, "kjj_trace_click_id_new", clickId, 315360000, "/", "baixing.com");
//        }

        //这段不需要了，因为现在cookie中只包含了kjj_trace_profile_new
//        if (traffic.getProfile() == null) {
//            genDeleteCookie(response, "kjj_trace_profile", "ary.baixing.com");
//            genDeleteCookie(response, "kjj_trace_profile", "baixing.com");
//            String profile = cookies.get("kjj_trace_profile");
//            setCookie(response, "kjj_trace_profile_new", profile, 315360000, "/", "baixing.com");
//        }

        //将pageDepth + 1
        setCookie(response, "kjj_log_session_depth", traffic.getPageDepth(), -1, "/", "gary.baixing.com");

        String currentUlr = traffic.getCurrentUrl();
        if (currentUlr != null && currentUlr.indexOf("bannerid") != -1) {
            setCookie(response, "kjj_log_origin", traffic.getOriginUrlType(), -1, "/", "gary.baixing.com");
        }
        LOG.debug("update response finished");

    }

    public String getArriveTime(Date requestDate) {
        return GARY_FROMAT_TIME.format(requestDate);
    }

    public String getPreUrl(Map<String, String> headers) {

        return headers.get("ref") != null ? headers.get("ref").trim() : "" ;
    }


    public String getCurrentUrl (Map<String, String> headers, HttpServletRequest request) {
        String currentUrl = "";

        if (headers.get("url") != null) {
            currentUrl = headers.get("url");
        } else if (request.getHeader("referer") != null) {
            currentUrl = request.getHeader("referer");
        }
        return currentUrl;
    }


    public String getCityId(Map<String, String> headers) {
        String cityId = headers.get("ct");
        if (cityId == null || cityId == "" || cityId.equals("null") ) {
            cityId = "9999";
        } else if ( StringUtils.isNumeric(cityId) && Integer.parseInt(cityId) > 9999)  {
            cityId = "9999";
        }

        return cityId;
    }

    public String getRemoteIp(HttpServletRequest request) {
        String remoteIp = request.getRemoteAddr();
        return remoteIp!=null ? remoteIp : "127.0.0.1";
    }

    public String getCityEnglishName(Map<String, String> headers, Map<String, String> cookies) {
        String cityEnglishName = "";
        String headerName = headers.get("ctn");
        String cookieName = cookies.get("__city");

        if (headerName != null) {
            cityEnglishName = headerName;
        } else if (cookieName != null) {
            cityEnglishName = cookieName;
        }
        return  cityEnglishName.trim();

    }

    public String getCategoryEnglishName(Map<String, String> headers) {

        return headers.get("cat") != null ? headers.get("cat").trim() : "" ;
    }

    public String getUserId(Map<String, String> headers, Map<String, String> cookies) {
        String vid = "";
        String headerVid = headers.get("vid");
        String cookieVid = cookies.get("__u");
        if (headerVid != null) {
            vid = headerVid;
        } else if (cookieVid != null) {
            vid = cookieVid;
        }
        return vid.trim();
    }

    public String getContentUserId(Map<String, String> headers) {
        return headers.get("uid") != null ? headers.get("uid").trim() : "0" ;
    }

    public String getAdId(Map<String, String> headers) {
        return headers.get("adid") != null ? headers.get("adid").trim() : "" ;
    }

    public String getReId(Map<String, String> headers) {
        return headers.get("reid") != null ? headers.get("reid").trim() : "" ;
    }

    public String getPvId(Map<String, String> headers) {
        return headers.get("pvid") != null ? headers.get("pvid").trim() : "" ;
    }

    public String getFromPvId(Map<String, String> headers) {
        return headers.get("ppvid") != null ? headers.get("ppvid").trim() : "" ;
    }

    public String getLogId(Map<String, String> cookies) {
        return cookies.get("kjj_log_log_id") != null ? cookies.get("kjj_log_log_id").trim() : "" ;
    }

    public String getSessionId(Map<String, String> cookies) {
        return cookies.get("kjj_log_session_id") != null ? cookies.get("kjj_log_session_id").trim() : "0" ;
    }

    public String getClickId(Map<String, String> cookies) {
        String clickId = "";
        String clickIdNew = cookies.get("kjj_trace_click_id_new");
        String clickIdOld = cookies.get("kjj_trace_click_id");

        if (clickIdNew != null) {
            clickId = clickIdNew;
        }
        else if (clickIdOld != null) {
            clickId = clickIdOld;
        }

        return clickId;
    }

    public String getTrackId(Map<String, String> headers, Map<String, String> cookies) {
        String tid = "";
        String headerTid = headers.get("tid");
        String cookieTid = cookies.get("__trackId");
        if (headerTid != null) {
            tid = headerTid;
        } else if (cookieTid != null) {
            tid = cookieTid;
        }
        return tid.trim();
    }

    public String getUserAget(HttpServletRequest request) {
        return request.getHeader("User-Agent") != null ? request.getHeader("User-Agent").trim() : "";
    }

    public String getProfile(Map<String, String> cookies) {
        String profile = "";
        String cookieProflieNew = cookies.get("kjj_trace_profile_new");
        String cookieProlfileOld = cookies.get("kjj_trace_profile");

        if (cookieProflieNew != null) {
            profile = cookieProflieNew;
        } else if (cookieProlfileOld != null) {
            profile = cookieProlfileOld;
        }

        return profile;
    }

    public String getPageDepth(Map<String, String> cookies) {
        String pageDepth = cookies.get("kjj_log_session_depth");

        if (pageDepth != null && pageDepth.length() > 0 && Integer.parseInt(pageDepth) >= 0
                && StringUtils.isNumeric(pageDepth)) {
            return String.valueOf(Integer.parseInt(pageDepth) + 1);
        } else {
            return "0";
        }
    }

    public String getBannerId(Map<String, String> cookies) {
        String bannerId = cookies.get("kjj_trace_banner_id");

        if (bannerId != null && bannerId.length() > 0 && StringUtils.isNumeric(bannerId)
                && Integer.parseInt(bannerId) >= 0) {
            return String.valueOf(Integer.parseInt(bannerId) + 1);
        } else {
            return "0";
        }
    }

    public String getOriginUrlType(Map<String, String> cookies, Map<String, String> headers, HttpServletRequest request) {
        String originUrlType = "0";
        String cookieOriginUrlType = cookies.get("kjj_log_origin");

        if (cookieOriginUrlType != null && cookieOriginUrlType.length() > 0 && StringUtils.isNumeric(cookieOriginUrlType)
                && Integer.parseInt(cookieOriginUrlType) > 0) {
            originUrlType =  cookieOriginUrlType;
        } else {
            String currentUlr = getCurrentUrl(headers, request);
            if (currentUlr.indexOf("bannerid") != -1) {
                originUrlType = "4";
            }
        }
        return originUrlType;

    }

    public String getTestKey1(Map<String, String> headers) {
        return headers.get("tk1") != null ? headers.get("tk1").trim() : "" ;
    }

    public String getTestKey2(Map<String, String> headers) {
        return headers.get("tk2") != null ? headers.get("tk2").trim() : "" ;
    }

    public String getTestKey3(Map<String, String> headers) {
        return headers.get("tk3") != null ? headers.get("tk3").trim() : "" ;
    }

    public String getTestKey4(Map<String, String> headers) {
        return headers.get("tk4") != null ? headers.get("tk4").trim() : "" ;
    }

    public String getFromDiv(Map<String, String> headers) {
        return headers.get("click") != null ? headers.get("click").trim() : "" ;
    }

    public String getLatlng(Map<String, String> headers) {
        return headers.get("latlng") != null ? headers.get("latlng").trim() : "" ;
    }

    public String getLatlngCity(Map<String, String> headers) {
        return headers.get("latlngCity") != null ? headers.get("latlngCity").trim() : "" ;
    }

    public String getCustomValue1(Map<String, String> headers) {
        return headers.get("tk5") != null ? headers.get("tk5").trim() : "" ;
    }

    public String getCustomValue2(Map<String, String> headers) {
        return headers.get("cv2") != null ? headers.get("cv2").trim() : "" ;
    }

    public String getCustomValue3(Map<String, String> headers) {
        return headers.get("nojs") != null ? headers.get("nojs").trim() : "" ;
    }

    public String getCustomValue4(Map<String, String> headers) {
        return headers.get("cv4") != null ? headers.get("cv4").trim() : "" ;
    }

    @Override
    public void configure(Context context) {

    }

}
