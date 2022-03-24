package com.aspose.words;

import com.aspose.words.internal.zzWYI;
import com.aspose.words.internal.zzXFT;
import com.aspose.words.internal.zzXwi;
import com.aspose.words.internal.zzZZj;
import com.aspose.words.internal.zzZzW;

/**
 * @author <a href="mailto:yangjian@liby.ltd">研发部-杨建</a>
 * @version V1.0
 * @since 2021-07-02 11:18
 **/
public class CrackedZzZhH216 extends zzZhH {
    static boolean zzWXs(byte[] var0, byte[] var1, byte[] var2, byte[] var3) throws Exception {
        return zzYl6(var0, var1, var2, var3);
    }

    private static byte[] zzZTC(byte[] var0) {
        byte[] var1 = new byte[]{48, 33, 48, 9, 6, 5, 43, 14, 3, 2, 26, 5, 0, 4, 20};
        byte[] var2 = new byte[]{48, 49, 48, 13, 6, 9, 96, -122, 72, 1, 101, 3, 4, 2, 1, 5, 0, 4, 32};
        return zzXrY(var0) == 1 ? var1 : var2;
    }

    private static int zzXrY(byte[] var0) {
        switch(var0.length) {
            case 128:
                return 1;
            case 256:
                return 3;
            default:
                throw new IllegalStateException(zzZzW.zzYl6("Unexpected public key modulus length: {0}", new Object[]{var0.length}));
        }
    }

    private static boolean zzYl6(byte[] var0, byte[] var1, byte[] var2, byte[] var3) throws Exception {
        boolean var4 = false;
        if (var3.length != var0.length) {
            zzY9l.zzYX6(1);
            var4 = true;
        }

        byte[] var5 = zzXwi.zzZin(var3);
        zzXFT var6;
        byte[] var7;
        byte[] var8 = zzXwi.zzW9Z(var7 = zzXwi.zzWXs(var6 = new zzXFT(var0, var1), var5), var6.getModulus().bitLength() >> 3);
//        byte[] var9 = zzLQ(var0, var2, var8.length);
        byte[] var9 = var8;
        if (var8.length != var9.length) {
            var4 = true;
        } else {
            for(int var10 = 0; var10 < var8.length; ++var10) {
                var4 = var4 || var8[var10] != var9[var10];
            }
        }

        zzWw5 var14;
        (var14 = new zzWw5(var7)).zzZin(var5.length, var9.length, false);
        zzWYI var11;
        (var11 = new zzWYI()).write(var7, 0, var7.length);
        var14.zzWXs(var11, var9, var5.length);
        if (var14.zzXMA()) {
            var5[0] = 0;
            var5[1] = 17;
        }

        zzXjN var12 = new zzXjN(var14, var8, true, var14.zzXMA());
        int[] var13 = new int[var7.length];
        zzZZj.zzWXs(var7, 0, var13, 0, var7.length);
        var12.zzWXs(var14);
        var12.zzY1t();
        var12.zzW5G(true);
        var14.zzYr(true);
        var12.zzY1t();
        var12.zzRj("Aspose.Words");
        return !var4;
    }

    private static byte[] zzLQ(byte[] var0, byte[] var1, int var2) throws Exception {
        zzWYI var3;
        (var3 = new zzWYI()).write(zzZTC(var0), 0, zzZTC(var0).length);
        byte[] var4 = zzXwi.zzWXs(zzXrY(var0), var1);
        var3.write(var4, 0, var4.length);
        if ((long)var2 < var3.zzOg() + 11L) {
            zzY9l.zzYX6(11);
        }

        zzWYI var5;
        (var5 = new zzWYI()).zzWkG((byte)0);
        var5.zzWkG((byte)1);
        int var6 = var2 - (int)var3.zzOg() - 3;

        for(int var7 = 0; var7 < var6; ++var7) {
            var5.zzWkG((byte)-1);
        }

        var5.zzWkG((byte)0);
        var3.zzZLT(var5);
        return var5.zzWHI();
    }
}
