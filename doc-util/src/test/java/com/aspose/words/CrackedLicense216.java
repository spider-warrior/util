package com.aspose.words;

import com.aspose.words.internal.zzYJt;
import com.aspose.words.internal.zzZt9;

import java.io.InputStream;

/**
 * @author <a href="mailto:yangjian@liby.ltd">研发部-杨建</a>
 * @version V1.0
 * @since 2021-07-01 17:11
 **/
public class CrackedLicense216 extends License {

    private com.aspose.words.internal.zzYJt zzYOQ = zzYJt.zzWQZ();

    public void setLicense(String licenseName) throws Exception {
        if (licenseName == null) {
            throw new NullPointerException(this.zzYOQ.zzEd(new byte[]{108, 105, 99, 101, 110, 115, 101, 78, 97, 109, 101}));
        } else {
            (new zzEP()).zzYYk(licenseName, zzZt9.zzKK());
        }
    }

    public void setLicense(InputStream stream) throws Exception {
        if (stream == null) {
            throw new NullPointerException(this.zzYOQ.zzEd(new byte[]{115, 116, 114, 101, 97, 109}));
        } else {
            (new CrackedZzEP216()).zzYYk(stream);
        }
    }
}
