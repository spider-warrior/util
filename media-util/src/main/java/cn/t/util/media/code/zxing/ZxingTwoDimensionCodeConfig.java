package cn.t.util.media.code.zxing;

import cn.t.util.media.code.TwoDimensionCodeConfig;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;

public class ZxingTwoDimensionCodeConfig extends TwoDimensionCodeConfig {

    /**
     * 容错级别
     */
    private ErrorCorrectionLevel errorCorrectionLevel = ErrorCorrectionLevel.M;

    /**
     * margin
     */
    private int margin = 5;

    /**
     * code type
     */
    private BarcodeFormat barcodeFormat;

    public ErrorCorrectionLevel getErrorCorrectionLevel() {
        return errorCorrectionLevel;
    }

    public void setErrorCorrectionLevel(ErrorCorrectionLevel errorCorrectionLevel) {
        this.errorCorrectionLevel = errorCorrectionLevel;
    }

    public int getMargin() {
        return margin;
    }

    public void setMargin(int margin) {
        this.margin = margin;
    }

    public BarcodeFormat getBarcodeFormat() {
        return barcodeFormat;
    }

    public void setBarcodeFormat(BarcodeFormat barcodeFormat) {
        this.barcodeFormat = barcodeFormat;
    }
}
