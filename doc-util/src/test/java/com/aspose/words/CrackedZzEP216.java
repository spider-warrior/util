//package com.aspose.words;
//
//import com.aspose.words.internal.zzVTO;
//import com.aspose.words.internal.zzVUL;
//import com.aspose.words.internal.zzWi7;
//import com.aspose.words.internal.zzX6W;
//import com.aspose.words.internal.zzXwi;
//import com.aspose.words.internal.zzY8k;
//import com.aspose.words.internal.zzYJt;
//import com.aspose.words.internal.zzZt9;
//import com.aspose.words.internal.zziy;
//import java.io.File;
//import java.io.InputStream;
//import java.math.BigInteger;
//import java.security.KeyFactory;
//import java.security.PublicKey;
//import java.security.spec.RSAPublicKeySpec;
//import java.text.ParseException;
//import java.text.SimpleDateFormat;
//import java.util.Date;
//import java.util.Locale;
//import javax.xml.parsers.DocumentBuilder;
//import javax.xml.parsers.DocumentBuilderFactory;
//import org.w3c.dom.Document;
//import org.w3c.dom.Element;
//import org.w3c.dom.Node;
//import org.w3c.dom.NodeList;
//
///**
// * @author <a href="mailto:yangjian@liby.ltd">研发部-杨建</a>
// * @version V1.0
// * @since 2021-07-02 11:23
// **/
//public class CrackedZzEP216 extends zzEP {
//    private com.aspose.words.internal.zzYJt zzYOQ;
//    private String[] zzYWy;
//    private String zzZDx;
//    private Date zzXGz;
//    private Date zzXA8;
//    private byte[] zzWk1;
//    private static com.aspose.words.internal.zzYJt zzUj = com.aspose.words.internal.zzYJt.zzWQZ();
//    private static CrackedZzEP216 zzZGV = null;
//    static long zzYiJ = 0L;
//    private static long zzZau = 7133812862090241280L;
//    private static zzVTO<String> zzVOy;
//    private static zzVTO<String> zzYUy;
//    private static final String zzWEc;
//    private static final String zzYGe;
//    private static final String zzZhm;
//    private static final String zzVYL;
//
//    CrackedZzEP216() {
//        this.zzWk1 = zzXyu.zzZXG;
//        this.zzYOQ = zzYJt.zzWQZ();
//    }
//
//    void zzYYk(String var1, Class var2) throws Exception {
//        if (!"".equals(var1)) {
//            InputStream var3 = this.zzSb(var1, var2);
//
//            try {
//                this.zzYYk(var3);
//            } finally {
//                if (var3 != null) {
//                    var3.close();
//                }
//
//            }
//        } else {
//            zzZGV = this;
//        }
//
//    }
//
//    void zzYYk(InputStream var1) throws Exception {
//        if (var1 == null) {
//            throw new NullPointerException(this.zzYOQ.zzEd(new byte[]{115, 116, 114, 101, 97, 109}));
//        } else if (!this.zzSb(var1)) {
//            throw new IllegalStateException(this.zzYOQ.zzEd(new byte[]{73, 110, 118, 97, 108, 105, 100, 32, 108, 105, 99, 101, 110, 115, 101, 32, 115, 105, 103, 110, 97, 116, 117, 114, 101, 46, 32, 80, 108, 101, 97, 115, 101, 32, 109, 97, 107, 101, 32, 115, 117, 114, 101, 32, 116, 104, 101, 32, 108, 105, 99, 101, 110, 115, 101, 32, 102, 105, 108, 101, 32, 119, 97, 115, 32, 110, 111, 116, 32, 109, 111, 100, 105, 102, 105, 101, 100, 46}));
//        } else {
//            zzVTO var2 = zzVOy;
//            if (var2 == null) {
//                var2 = zzXpM(this.zzYOQ.zzEd(new byte[]{65, 115, 112, 111, 115, 101, 46, 76, 105, 99, 101, 110, 115, 101, 46, 66, 108, 97, 99, 107, 76, 105, 115, 116}), (String)null);
//                zzVOy = var2;
//            }
//
//            zzVTO var3 = zzYUy;
//            if (var3 == null) {
//                var3 = zzXpM(this.zzYOQ.zzEd(new byte[]{67, 111, 110, 104, 111, 108, 100, 97, 116, 101, 46, 76, 105, 99, 101, 110, 115, 101, 46, 66, 108, 97, 99, 107, 76, 105, 115, 116}), zzYGe);
//                zzYUy = var3;
//            }
//
//            if (zzY9l.zzY0j() > 0) {
//                throw new IllegalStateException(this.zzYOQ.zzEd(new byte[]{73, 110, 118, 97, 108, 105, 100, 32, 108, 105, 99, 101, 110, 115, 101, 32, 115, 105, 103, 110, 97, 116, 117, 114, 101, 46, 32, 80, 108, 101, 97, 115, 101, 32, 109, 97, 107, 101, 32, 115, 117, 114, 101, 32, 116, 104, 101, 32, 108, 105, 99, 101, 110, 115, 101, 32, 102, 105, 108, 101, 32, 119, 97, 115, 32, 110, 111, 116, 32, 109, 111, 100, 105, 102, 105, 101, 100, 46}));
//            } else if (!var2.contains(this.zzZDx) && !var3.contains(this.zzZDx)) {
//                boolean var4 = false;
//                String[] var5 = this.zzYWy;
//                int var6 = var5.length;
//                int var7 = 0;
//
//                while(var7 < var6) {
//                    String var8 = var5[var7];
//                    if (!var8.equals(this.zzYOQ.zzEd(new byte[]{65, 115, 112, 111, 115, 101, 46, 84, 111, 116, 97, 108, 32, 102, 111, 114, 32}) + this.zzYOQ.zzEd(new byte[]{74, 97, 118, 97})) && !var8.equals(this.zzYOQ.zzEd(new byte[]{67, 111, 110, 104, 111, 108, 100, 97, 116, 101, 46, 84, 111, 116, 97, 108, 32, 102, 111, 114, 32}) + this.zzYOQ.zzEd(new byte[]{74, 97, 118, 97}))) {
//                        if ((var8.equals(this.zzYOQ.zzEd(new byte[]{65, 115, 112, 111, 115, 101, 46, 84, 111, 116, 97, 108})) || var8.equals(this.zzYOQ.zzEd(new byte[]{67, 111, 110, 104, 111, 108, 100, 97, 116, 101, 46, 84, 111, 116, 97, 108}))) && this.zzYOQ.zzEd(new byte[]{74, 97, 118, 97}).equals(this.zzYOQ.zzEd(new byte[]{46, 78, 69, 84}))) {
//                            var4 = true;
//                            break;
//                        }
//
//                        if (!var8.equals(this.zzYOQ.zzEd(new byte[]{65, 115, 112, 111, 115, 101, 46, 84, 111, 116, 97, 108, 32, 80, 114, 111, 100, 117, 99, 116, 32, 70, 97, 109, 105, 108, 121})) && !var8.equals(this.zzYOQ.zzEd(new byte[]{67, 111, 110, 104, 111, 108, 100, 97, 116, 101, 46, 84, 111, 116, 97, 108, 32, 80, 114, 111, 100, 117, 99, 116, 32, 70, 97, 109, 105, 108, 121}))) {
//                            if (var8.equals(this.zzYOQ.zzEd(new byte[]{65, 115, 112, 111, 115, 101, 46, 87, 111, 114, 100, 115, 32, 102, 111, 114, 32, 74, 97, 118, 97}))) {
//                                var4 = true;
//                                break;
//                            }
//
//                            String var9;
//                            String var10;
//                            if (this.zzYOQ.zzEd(new byte[]{46, 78, 69, 84}).equals(this.zzYOQ.zzEd(new byte[]{74, 97, 118, 97}))) {
//                                var9 = this.zzYOQ.zzEd(new byte[]{65, 115, 112, 111, 115, 101, 46, 87, 111, 114, 100, 115}) + this.zzYOQ.zzEd(new byte[]{32, 102, 111, 114, 32}) + this.zzYOQ.zzEd(new byte[]{88, 97, 109, 97, 114, 105, 110, 46, 65, 110, 100, 114, 111, 105, 100});
//                                var10 = this.zzYOQ.zzEd(new byte[]{65, 115, 112, 111, 115, 101, 46, 84, 111, 116, 97, 108, 32, 102, 111, 114, 32, 88, 97, 109, 97, 114, 105, 110, 46, 65, 110, 100, 114, 111, 105, 100});
//                                String var11 = this.zzYOQ.zzEd(new byte[]{65, 115, 112, 111, 115, 101, 46, 87, 111, 114, 100, 115}) + this.zzYOQ.zzEd(new byte[]{32, 102, 111, 114, 32}) + this.zzYOQ.zzEd(new byte[]{65, 110, 100, 114, 111, 105, 100, 32, 118, 105, 97, 32, 88, 97, 109, 97, 114, 105, 110});
//                                String var12 = this.zzYOQ.zzEd(new byte[]{65, 115, 112, 111, 115, 101, 46, 84, 111, 116, 97, 108, 32, 102, 111, 114, 32, 65, 110, 100, 114, 111, 105, 100, 32, 118, 105, 97, 32, 88, 97, 109, 97, 114, 105, 110});
//                                String var13 = this.zzYOQ.zzEd(new byte[]{65, 115, 112, 111, 115, 101, 46, 87, 111, 114, 100, 115}) + this.zzYOQ.zzEd(new byte[]{32, 102, 111, 114, 32}) + this.zzYOQ.zzEd(new byte[]{105, 79, 83, 32, 118, 105, 97, 32, 88, 97, 109, 97, 114, 105, 110});
//                                String var14 = this.zzYOQ.zzEd(new byte[]{65, 115, 112, 111, 115, 101, 46, 84, 111, 116, 97, 108, 32, 102, 111, 114, 32, 105, 79, 83, 32, 118, 105, 97, 32, 88, 97, 109, 97, 114, 105, 110});
//                                String var15 = this.zzYOQ.zzEd(new byte[]{65, 115, 112, 111, 115, 101, 46, 87, 111, 114, 100, 115}) + this.zzYOQ.zzEd(new byte[]{32, 102, 111, 114, 32}) + this.zzYOQ.zzEd(new byte[]{77, 97, 99, 32, 118, 105, 97, 32, 88, 97, 109, 97, 114, 105, 110});
//                                String var16 = this.zzYOQ.zzEd(new byte[]{65, 115, 112, 111, 115, 101, 46, 84, 111, 116, 97, 108, 32, 102, 111, 114, 32, 77, 97, 99, 32, 118, 105, 97, 32, 88, 97, 109, 97, 114, 105, 110});
//                                if (var8.equals(var9) || var8.equals(var10) || var8.equals(var11) || var8.equals(var12) || var8.equals(var13) || var8.equals(var14) || var8.equals(var15) || var8.equals(var16)) {
//                                    var4 = true;
//                                    break;
//                                }
//                            }
//
//                            if (this.zzYOQ.zzEd(new byte[]{74, 97, 118, 97, 46, 65, 110, 100, 114, 111, 105, 100}).equals(this.zzYOQ.zzEd(new byte[]{74, 97, 118, 97}))) {
//                                var9 = this.zzYOQ.zzEd(new byte[]{65, 115, 112, 111, 115, 101, 46, 87, 111, 114, 100, 115}) + this.zzYOQ.zzEd(new byte[]{32, 102, 111, 114, 32}) + this.zzYOQ.zzEd(new byte[]{65, 110, 100, 114, 111, 105, 100});
//                                var10 = this.zzYOQ.zzEd(new byte[]{65, 115, 112, 111, 115, 101, 46, 84, 111, 116, 97, 108, 32, 102, 111, 114, 32, 65, 110, 100, 114, 111, 105, 100});
//                                if (var8.equals(var9) || var8.equals(var10)) {
//                                    var4 = true;
//                                    break;
//                                }
//                            }
//
//                            if (var8.equals(this.zzYOQ.zzEd(new byte[]{65, 115, 112, 111, 115, 101, 46, 87, 111, 114, 100, 115})) && this.zzYOQ.zzEd(new byte[]{74, 97, 118, 97}).equals(this.zzYOQ.zzEd(new byte[]{46, 78, 69, 84}))) {
//                                var4 = true;
//                                break;
//                            }
//
//                            if (var8.equals(this.zzYOQ.zzEd(new byte[]{65, 115, 112, 111, 115, 101, 46, 87, 111, 114, 100, 115}) + this.zzYOQ.zzEd(new byte[]{32, 80, 114, 111, 100, 117, 99, 116, 32, 70, 97, 109, 105, 108, 121}))) {
//                                var4 = true;
//                                break;
//                            }
//
//                            ++var7;
//                            continue;
//                        }
//
//                        var4 = true;
//                        break;
//                    }
//
//                    var4 = true;
//                    break;
//                }
//
//                if (!var4) {
//                    throw new IllegalStateException(this.zzYOQ.zzEd(new byte[]{84, 104, 101, 32, 108, 105, 99, 101, 110, 115, 101, 32, 105, 115, 32, 110, 111, 116, 32, 118, 97, 108, 105, 100, 32, 102, 111, 114, 32, 116, 104, 105, 115, 32, 112, 114, 111, 100, 117, 99, 116, 46}));
//                } else {
//                    Date var17 = (new SimpleDateFormat("yyyy.MM.dd")).parse("2021.06.01");
//                    if (var17.after(this.zzXGz)) {
//                        throw new IllegalStateException(this.zzYOQ.zzEd(new byte[]{84, 104, 101, 32, 115, 117, 98, 115, 99, 114, 105, 112, 116, 105, 111, 110, 32, 105, 110, 99, 108, 117, 100, 101, 100, 32, 105, 110, 32, 116, 104, 105, 115, 32, 108, 105, 99, 101, 110, 115, 101, 32, 97, 108, 108, 111, 119, 115, 32, 102, 114, 101, 101, 32, 117, 112, 103, 114, 97, 100, 101, 115, 32, 117, 110, 116, 105, 108, 32}) + (new SimpleDateFormat(this.zzYOQ.zzEd(new byte[]{100, 100, 32, 77, 77, 77, 32, 121, 121, 121, 121}), Locale.ENGLISH)).format(this.zzXGz) + this.zzYOQ.zzEd(new byte[]{44, 32}) + this.zzYOQ.zzEd(new byte[]{98, 117, 116, 32, 116, 104, 105, 115, 32, 118, 101, 114, 115, 105, 111, 110, 32, 111, 102, 32, 116, 104, 101, 32, 112, 114, 111, 100, 117, 99, 116, 32, 119, 97, 115, 32, 114, 101, 108, 101, 97, 115, 101, 100, 32, 111, 110, 32}) + (new SimpleDateFormat(this.zzYOQ.zzEd(new byte[]{100, 100, 32, 77, 77, 77, 32, 121, 121, 121, 121}), Locale.ENGLISH)).format(var17) + this.zzYOQ.zzEd(new byte[]{46, 32}) + this.zzYOQ.zzEd(new byte[]{80, 108, 101, 97, 115, 101, 32, 114, 101, 110, 101, 119, 32, 116, 104, 101, 32, 115, 117, 98, 115, 99, 114, 105, 112, 116, 105, 111, 110, 32, 111, 114, 32, 117, 115, 101, 32, 97, 32, 112, 114, 101, 118, 105, 111, 117, 115, 32, 118, 101, 114, 115, 105, 111, 110, 32, 111, 102, 32, 116, 104, 101, 32, 112, 114, 111, 100, 117, 99, 116, 46}));
//                    } else if ((new Date()).after(this.zzXA8)) {
//                        throw new IllegalStateException(this.zzYOQ.zzEd(new byte[]{84, 104, 101, 32, 108, 105, 99, 101, 110, 115, 101, 32, 104, 97, 115, 32, 101, 120, 112, 105, 114, 101, 100, 46}));
//                    } else if (this.zzXGz.getYear() < 2099) {
//                        this.zzWk1 = zzXyu.zz1Y;
//                        zzZGV = this;
//                    }
//                }
//            } else {
//                throw new IllegalStateException(this.zzYOQ.zzEd(new byte[]{84, 104, 105, 115, 32, 108, 105, 99, 101, 110, 115, 101, 32, 105, 115, 32, 100, 105, 115, 97, 98, 108, 101, 100, 44, 32, 112, 108, 101, 97, 115, 101, 32, 99, 111, 110, 116, 97, 99, 116, 32, 65, 115, 112, 111, 115, 101, 32, 116, 111, 32, 111, 98, 116, 97, 105, 110, 32, 97, 32, 110, 101, 119, 32, 108, 105, 99, 101, 110, 115, 101, 46}));
//            }
//        }
//    }
//
//    static byte[] zzXNk() {
//        boolean var0 = zzZGV == null || zzZGV.zzWk1 == zzXyu.zzZXG || (new Date()).after(zzZGV.zzXA8) || zzY9l.zzWNn() == 4096;
//        boolean var1 = zzWS3.zzWJu() == 0;
//        byte[] var2 = var0 && var1 ? zzXyu.zzZXG : zzXyu.zz1Y;
//        if (zzYiJ == 0L) {
//            zzYiJ ^= zzZau;
//        }
//
//        return var2;
//    }
//
//    static byte[] zzYRF() {
//        boolean var0 = zzZGV == null || zzZGV.zzWk1 == zzXyu.zzZXG || (new Date()).after(zzZGV.zzXA8) || zzY9l.zzWNn() == 4096;
//        boolean var1 = zzWS3.zzWJu() == 0;
//        byte[] var2 = var0 && var1 ? zzXyu.zzZXG : zzXyu.zz1Y;
//        return var2;
//    }
//
//    private boolean zzSb(InputStream var1) throws Exception {
//        DocumentBuilderFactory var2 = com.aspose.words.internal.zzX6W.zzVQD();
//        javax.xml.parsers.DocumentBuilder var3 = var2.newDocumentBuilder();
//        org.w3c.dom.Document var4 = var3.parse(var1);
//        Element var5 = var4.getDocumentElement();
//        Element var6 = zzGf(var5, this.zzYOQ.zzEd(new byte[]{68, 97, 116, 97}));
//        Element var7 = zzGf(var5, this.zzYOQ.zzEd(new byte[]{83, 105, 103, 110, 97, 116, 117, 114, 101}));
//        boolean var8 = zzYl6((org.w3c.dom.Node)var6, (org.w3c.dom.Node)var7);
//        Element var9 = zzGf(var6, this.zzYOQ.zzEd(new byte[]{80, 114, 111, 100, 117, 99, 116, 115}));
//        org.w3c.dom.NodeList var10 = var9.getElementsByTagName(this.zzYOQ.zzEd(new byte[]{80, 114, 111, 100, 117, 99, 116}));
//        this.zzYWy = new String[var10.getLength()];
//
//        for(int var11 = 0; var11 < this.zzYWy.length; ++var11) {
//            this.zzYWy[var11] = var10.item(var11).getFirstChild().getNodeValue();
//        }
//
//        this.zzZDx = zzYl6(var6, this.zzYOQ.zzEd(new byte[]{83, 101, 114, 105, 97, 108, 78, 117, 109, 98, 101, 114}));
//        this.zzXGz = zzLQ(var6, this.zzYOQ.zzEd(new byte[]{83, 117, 98, 115, 99, 114, 105, 112, 116, 105, 111, 110, 69, 120, 112, 105, 114, 121}));
//        this.zzXA8 = zzLQ(var6, this.zzYOQ.zzEd(new byte[]{76, 105, 99, 101, 110, 115, 101, 69, 120, 112, 105, 114, 121}));
//        return var8;
//    }
//
//    private static boolean zzYl6(org.w3c.dom.Node var0, org.w3c.dom.Node var1) throws Exception {
//        return zzWXs((org.w3c.dom.Node)var0, (org.w3c.dom.Node)var1, (String)null);
//    }
//
//    private static boolean zzWXs(org.w3c.dom.Node var0, org.w3c.dom.Node var1, String var2) throws Exception {
//        byte[] var3;
//        if (var0 != null) {
//            StringBuilder var4 = new StringBuilder();
//            zzWXs(var4, var0);
//            var3 = var4.toString().getBytes("UTF-16LE");
//        } else {
//            var3 = new byte[0];
//        }
//
//        byte[] var6;
//        if (var1 != null) {
//            String var5 = var1.getFirstChild().getNodeValue();
//            var6 = com.aspose.words.internal.zzXwi.zzTI(var5);
//        } else {
//            var6 = new byte[0];
//        }
//
//        if (var2 == null) {
//            if (zzH2(var0)) {
//                var2 = var6.length == 128 ? zzYGe : zzVYL;
//            } else {
//                var2 = var6.length == 128 ? zzWEc : zzZhm;
//            }
//        }
//
//        return zzWXs(var3, var6, var2);
//    }
//
//    private static boolean zzWXs(byte[] var0, byte[] var1, String var2) throws Exception {
//        String var3 = zzUj.zzEd(new byte[]{65, 81, 65, 66});
//        byte[] var4 = com.aspose.words.internal.zzXwi.zzTI(var3);
//        byte[] var5 = com.aspose.words.internal.zzXwi.zzTI(var2);
//        return CrackedZzZhH216.zzWXs(var5, var4, var0, var1);
//    }
//
//    private InputStream zzSb(String var1, Class var2) throws Exception {
//        File var3 = new File(var1);
//        if (var3.exists()) {
//            return new com.aspose.words.internal.zzY8k(var3);
//        } else {
//            String var4 = com.aspose.words.internal.zzZt9.zzZGg(this.getClass());
//            var3 = new File(var4, var1);
//            if (var3.exists()) {
//                return new com.aspose.words.internal.zzY8k(var3);
//            } else {
//                var4 = zzZt9.zzZGg(var2);
//                var3 = new File(var4, var1);
//                if (var3.exists()) {
//                    return new zzY8k(var3);
//                } else {
//                    throw new IllegalStateException(this.zzYOQ.zzEd(new byte[]{67, 97, 110, 110, 111, 116, 32, 102, 105, 110, 100, 32, 108, 105, 99, 101, 110, 115, 101, 32, 39}) + var1 + "'.");
//                }
//            }
//        }
//    }
//
//    private static synchronized zzVTO<String> zzXpM(String var0, String var1) throws Exception {
//        InputStream var2 = zzVUL.zzLQ(var0 + zzUj.zzEd(new byte[]{46, 82, 101, 97, 108, 46, 120, 109, 108}), License.class);
//        if (var2 == null) {
//            throw new IllegalStateException(zzUj.zzEd(new byte[]{67, 97, 110, 110, 111, 116, 32, 102, 105, 110, 100, 32, 98, 108, 97, 99, 107, 32, 108, 105, 115, 116, 101, 100, 32, 108, 105, 99, 101, 110, 115, 101, 115, 32, 114, 101, 115, 111, 117, 114, 99, 101, 46, 32, 80, 108, 101, 97, 115, 101, 32, 114, 101, 112, 111, 114, 116, 32, 116, 104, 105, 115, 32, 101, 114, 114, 111, 114, 32, 116, 111, 32, 65, 115, 112, 111, 115, 101, 46}));
//        } else {
//            try {
//                DocumentBuilderFactory var3 = zzX6W.zzVQD();
//                DocumentBuilder var4 = var3.newDocumentBuilder();
//                Document var5 = var4.parse(var2);
//                Element var6 = var5.getDocumentElement();
//                Element var7 = zzGf(var6, zzUj.zzEd(new byte[]{68, 97, 116, 97}));
//                Element var8 = zzGf(var6, zzUj.zzEd(new byte[]{83, 105, 103, 110, 97, 116, 117, 114, 101}));
//                if (!zzWXs((org.w3c.dom.Node)var7, (org.w3c.dom.Node)var8, var1)) {
//                    zzWi7.zzWXs(false, "Incorrect signature.");
//                }
//
//                zzVTO var9 = new zzVTO();
//                org.w3c.dom.NodeList var10 = var7.getElementsByTagName(zzUj.zzEd(new byte[]{83, 78}));
//
//                for(int var11 = 0; var11 < var10.getLength(); ++var11) {
//                    var9.add(var10.item(var11).getFirstChild().getNodeValue());
//                }
//
//                zzVTO var15 = var9;
//                return var15;
//            } finally {
//                var2.close();
//            }
//        }
//    }
//
//    private static String zzYl6(Element var0, String var1) {
//        Element var2 = zzGf(var0, var1);
//        return var2 != null ? var2.getFirstChild().getNodeValue() : "";
//    }
//
//    private static Date zzLQ(Element var0, String var1) throws ParseException {
//        String var2 = zzYl6(var0, var1);
//        return !"".equals(var2) ? (new SimpleDateFormat("yyyyMMdd")).parse(var2) : new Date(253402300799999L);
//    }
//
//    private static Element zzGf(Element var0, String var1) {
//        org.w3c.dom.NodeList var2 = var0.getElementsByTagName(var1);
//        return var2.getLength() > 0 ? (Element)var2.item(0) : null;
//    }
//
//    private static void zzWXs(StringBuilder var0, org.w3c.dom.Node var1) {
//        if (var1.getNodeType() == 1) {
//            var0.append('<');
//            var0.append(var1.getNodeName());
//            var0.append('>');
//            org.w3c.dom.NodeList var2 = var1.getChildNodes();
//            if (zzWXs(var2)) {
//                org.w3c.dom.Node var5 = var2.item(0);
//                String var4 = var5.getNodeValue();
//                var4 = var4.replace("&", "&amp;");
//                var4 = var4.replace("<", "&lt;");
//                var4 = var4.replace(">", "&gt;");
//                var0.append(var4);
//            } else {
//                for(int var3 = 0; var3 < var2.getLength(); ++var3) {
//                    zzWXs(var0, var2.item(var3));
//                }
//            }
//
//            var0.append('<');
//            var0.append('/');
//            var0.append(var1.getNodeName());
//            var0.append('>');
//        }
//
//    }
//
//    private static PublicKey zzWEi(String var0, String var1) throws Exception {
//        byte[] var2 = com.aspose.words.internal.zzXwi.zzTI(var0);
//        byte[] var3 = zzXwi.zzTI(var1);
//        var2 = zzXdw(var2);
//        var3 = zzXdw(var3);
//        BigInteger var4 = new BigInteger(1, var2);
//        BigInteger var5 = new BigInteger(1, var3);
//        RSAPublicKeySpec var6 = new RSAPublicKeySpec(var4, var5);
//        return KeyFactory.getInstance("RSA").generatePublic(var6);
//    }
//
//    private static byte[] zzXdw(byte[] var0) {
//        int var2 = -1;
//
//        for(int var3 = 0; var3 < var0.length && var0[var3] == 0; var2 = var3++) {
//        }
//
//        ++var2;
//        byte[] var1;
//        if (var2 > 0) {
//            var1 = new byte[var0.length - var2];
//            System.arraycopy(var0, var2, var1, 0, var1.length);
//        } else {
//            var1 = var0;
//        }
//
//        return var1;
//    }
//
//    private static boolean zzWXs(org.w3c.dom.NodeList var0) {
//        org.w3c.dom.Node var1;
//        return var0 != null && var0.getLength() == 1 && (var1 = var0.item(0)) != null && var1.getNodeType() == 3;
//    }
//
//    private static org.w3c.dom.NodeList zzW9Z(org.w3c.dom.Node var0) {
//        if (var0 == null) {
//            return null;
//        } else {
//            Element var1 = (Element)var0;
//            return var1.getElementsByTagName(zzUj.zzEd(new byte[]{80, 114, 111, 100, 117, 99, 116, 115}));
//        }
//    }
//
//    private static boolean zzH2(org.w3c.dom.Node var0) {
//        NodeList var1 = zzW9Z(var0);
//        if (var1 == null) {
//            return false;
//        } else {
//            for(int var2 = 0; var2 < var1.getLength(); ++var2) {
//                Node var3 = var1.item(var2);
//                String var4 = var3.getTextContent();
//                if (com.aspose.words.internal.zziy.zzY7Q(var4) && zziy.zzGf(var4, zzUj.zzEd(new byte[]{67, 111, 110, 104, 111, 108, 100, 97, 116, 101}), true)) {
//                    return true;
//                }
//            }
//
//            return false;
//        }
//    }
//
//    static {
//        zzWEc = zzUj.zzEd(new byte[]{48, 110, 82, 117, 119, 78, 69, 100, 100, 88, 119, 76, 102, 88, 66, 55, 112, 119, 54, 54, 71, 55, 49, 77, 83, 57, 51, 103, 87, 56, 109, 78, 122, 74, 55, 118, 117, 104, 51, 83, 102, 52, 86, 65, 69, 79, 66, 102, 112, 120, 116, 72, 76, 67, 111, 116, 121, 109, 118, 49, 80, 111, 101, 117, 107, 120, 89, 101, 51, 49, 75, 52, 52, 49, 73, 118, 113, 48, 80, 107, 118, 120, 49, 121, 90, 90, 71, 52, 79, 49, 75, 67, 118, 51, 79, 109, 100, 98, 115, 55, 117, 113, 122, 85, 66, 52, 120, 88, 72, 108, 79, 117, 98, 52, 86, 115, 84, 79, 68, 122, 68, 74, 53, 77, 87, 72, 113, 108, 82, 67, 66, 49, 72, 72, 99, 71, 106, 108, 121, 84, 50, 115, 86, 71, 105, 111, 118, 76, 116, 48, 71, 114, 118, 113, 119, 53, 43, 81, 88, 66, 117, 105, 110, 111, 66, 89, 48, 115, 117, 88, 48, 61});
//        zzYGe = zzUj.zzEd(new byte[]{113, 75, 70, 113, 113, 104, 70, 111, 118, 90, 118, 69, 89, 67, 72, 101, 68, 51, 78, 56, 79, 121, 43, 65, 117, 120, 114, 79, 65, 56, 99, 86, 118, 73, 108, 52, 117, 52, 113, 73, 66, 77, 81, 108, 122, 101, 106, 71, 121, 88, 107, 105, 84, 85, 106, 111, 114, 121, 117, 122, 108, 104, 108, 83, 50, 98, 71, 56, 48, 70, 71, 109, 70, 115, 72, 43, 119, 85, 75, 98, 89, 105, 69, 107, 87, 47, 52, 122, 115, 101, 90, 67, 86, 47, 69, 106, 47, 117, 115, 98, 117, 54, 121, 72, 65, 67, 81, 75, 79, 47, 83, 67, 121, 51, 112, 89, 119, 54, 56, 80, 99, 118, 57, 48, 49, 112, 118, 121, 119, 85, 110, 111, 97, 89, 67, 73, 50, 99, 99, 67, 71, 50, 57, 43, 88, 77, 43, 70, 119, 112, 70, 81, 117, 105, 80, 98, 50, 72, 55, 89, 98, 73, 47, 43, 43, 83, 81, 115, 54, 72, 107, 61});
//        zzZhm = zzUj.zzEd(new byte[]{51, 107, 105, 52, 53, 84, 54, 67, 52, 108, 116, 49, 50, 74, 53, 77, 98, 75, 102, 114, 65, 68, 66, 67, 90, 99, 69, 56, 79, 84, 101, 102, 100, 110, 103, 99, 57, 73, 68, 75, 103, 43, 108, 122, 67, 71, 89, 76, 117, 120, 74, 70, 68, 116, 49, 54, 97, 119, 104, 74, 70, 110, 65, 50, 51, 115, 88, 43, 107, 81, 52, 47, 101, 90, 81, 53, 112, 78, 65, 89, 106, 99, 43, 90, 74, 48, 43, 112, 87, 119, 118, 81, 82, 52, 104, 56, 71, 74, 51, 101, 87, 118, 101, 99, 100, 70, 115, 55, 75, 83, 87, 119, 78, 109, 70, 88, 90, 67, 83, 78, 43, 115, 98, 114, 120, 119, 69, 106, 122, 122, 110, 115, 49, 107, 73, 72, 117, 76, 78, 102, 53, 114, 43, 90, 97, 103, 103, 110, 115, 43, 56, 114, 113, 88, 82, 49, 57, 82, 83, 74, 66, 79, 99, 117, 70, 113, 86, 105, 112, 73, 72, 118, 53, 54, 108, 70, 53, 51, 72, 99, 43, 104, 120, 43, 121, 57, 85, 82, 73, 97, 97, 100, 79, 49, 87, 56, 100, 107, 84, 113, 103, 119, 69, 120, 121, 102, 106, 110, 98, 68, 79, 97, 67, 66, 69, 72, 48, 67, 113, 85, 76, 49, 89, 73, 73, 67, 83, 47, 119, 73, 85, 84, 69, 75, 104, 77, 48, 90, 108, 119, 69, 99, 73, 99, 72, 108, 56, 88, 84, 72, 76, 86, 120, 57, 54, 68, 77, 88, 52, 98, 98, 86, 97, 106, 106, 55, 56, 76, 52, 75, 122, 101, 118, 81, 99, 52, 52, 50, 68, 88, 50, 56, 75, 71, 68, 74, 84, 118, 101, 69, 66, 49, 112, 83, 75, 87, 115, 114, 48, 100, 52, 70, 84, 120, 55, 119, 75, 83, 51, 54, 82, 66, 110, 87, 118, 53, 108, 119, 115, 82, 69, 114, 116, 84, 90, 98, 53, 99, 105, 86, 73, 71, 49, 105, 73, 74, 114, 112, 56, 55, 86, 81, 61, 61});
//        zzVYL = zzUj.zzEd(new byte[]{120, 52, 65, 79, 70, 75, 112, 110, 66, 87, 103, 54, 99, 81, 84, 99, 116, 72, 51, 72, 57, 122, 85, 65, 88, 48, 101, 54, 121, 119, 107, 83, 106, 57, 100, 68, 77, 75, 100, 80, 77, 111, 116, 77, 108, 50, 65, 110, 66, 104, 107, 71, 66, 100, 49, 77, 50, 118, 51, 102, 57, 102, 108, 55, 120, 103, 108, 115, 67, 112, 43, 75, 100, 67, 72, 66, 82, 65, 80, 120, 68, 113, 88, 121, 48, 87, 67, 98, 80, 102, 83, 98, 114, 118, 43, 119, 104, 79, 56, 90, 70, 115, 84, 48, 65, 86, 108, 101, 88, 69, 77, 74, 53, 71, 109, 121, 122, 79, 98, 97, 81, 80, 72, 55, 111, 102, 102, 69, 73, 120, 120, 83, 119, 106, 88, 115, 109, 86, 57, 72, 52, 120, 86, 65, 90, 85, 67, 105, 71, 97, 109, 100, 56, 102, 69, 87, 115, 100, 43, 54, 75, 98, 109, 77, 98, 88, 68, 97, 110, 79, 111, 78, 79, 65, 108, 55, 72, 75, 74, 122, 105, 87, 120, 121, 108, 103, 43, 80, 105, 99, 81, 70, 56, 88, 50, 57, 100, 90, 114, 71, 52, 82, 113, 57, 72, 54, 69, 119, 56, 98, 103, 55, 102, 114, 98, 113, 99, 77, 104, 47, 43, 56, 75, 111, 122, 122, 53, 120, 90, 103, 99, 116, 69, 105, 81, 78, 89, 103, 118, 89, 54, 99, 99, 57, 78, 81, 97, 105, 52, 112, 122, 104, 102, 51, 52, 79, 78, 97, 108, 47, 68, 84, 55, 118, 111, 98, 69, 79, 78, 43, 72, 72, 79, 116, 54, 106, 122, 86, 77, 72, 108, 122, 115, 81, 51, 85, 100, 99, 105, 119, 122, 105, 70, 68, 57, 109, 55, 81, 100, 86, 104, 104, 98, 98, 83, 103, 75, 98, 53, 55, 86, 47, 77, 55, 90, 86, 48, 75, 98, 76, 49, 54, 56, 110, 97, 110, 105, 99, 51, 52, 86, 103, 101, 117, 78, 103, 54, 103, 105, 117, 69, 119, 61, 61});
//    }
//}
