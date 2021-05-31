package cn.t.util.common.test;

import cn.t.util.common.JsonUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author <a href="mailto:yangjian@liby.ltd">研发部-杨建</a>
 * @version V1.0
 * @since 2021-01-03 23:14
 **/
public class GroupTest {

    public static void main(String[] args) throws Exception {
        List<Item> response = new ArrayList<>();
        response.add(new Item("云生活", "漫50减5"));
        response.add(new Item("云生活", "漫60减10"));

        response.add(new Item("E健康", "直减5元"));
        response.add(new Item("E健康", "直减10"));
        Map<String, List<Item>> itemMap = response.stream().collect(Collectors.groupingBy(Item::getMerchantName));

        System.out.println(JsonUtil.serialize(itemMap));
    }

    private static class Item {
        private String merchantName;
        private String couponName;

        public String getMerchantName() {
            return merchantName;
        }

        public void setMerchantName(String merchantName) {
            this.merchantName = merchantName;
        }

        public String getCouponName() {
            return couponName;
        }

        public void setCouponName(String couponName) {
            this.couponName = couponName;
        }

        public Item(String merchantName, String couponName) {
            this.merchantName = merchantName;
            this.couponName = couponName;
        }

        @Override
        public String toString() {
            return "Item{" +
                "merchantName='" + merchantName + '\'' +
                ", couponName='" + couponName + '\'' +
                '}';
        }
    }
}
