package com.rminfo.protobuf.item;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author junior
 */
public class DgtKafkaDto {

    public Map<String, Object> head = new HashMap<>();

    public ContentDto content = new ContentDto();

    public Map<String, Object> getHead() {
        return head;
    }

    public void setHead(Map<String, Object> head) {
        this.head = head;
    }

    public ContentDto getContent() {
        return content;
    }

    public void setContent(ContentDto content) {
        this.content = content;
    }

    public void putHead(String key, Object value) {
        head.put(key, value);
    }

    public void putBaseDatas(String key, Object value) {
        content.getBaseData().put(key, value);
    }

    public List<Map<String, Object>> fingdInPartner() {
        return content.getPartnerData();
    }

    public Object getBaseDatas(String key) {
        return content.getBaseData().get(key);
    }

    public void removeBaseDatas(String key) {
        content.getBaseData().remove(key);
    }

    public class ContentDto {

        public Map<String, Object> baseData = new HashMap<>();

        public List<Map<String, Object>> partnerData = new ArrayList<>();

        public Map<String, Object> getBaseData() {
            return baseData;
        }

        public void setBaseData(Map<String, Object> baseData) {
            this.baseData = baseData;
        }

        public List<Map<String, Object>> getPartnerData() {
            return partnerData;
        }

        public void setPartnerData(List<Map<String, Object>> partnerData) {
            this.partnerData = partnerData;
        }
    }

}
