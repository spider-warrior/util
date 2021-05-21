package cn.t.util.media.video.mp4.enums;

public enum BoxType {

    /* file type and compatibility */
    FTYP("ftyp", (byte) 1, true),
    /* progressive download information */
    PDIN("pdin", (byte) 1, false),
    /* container for all the metadata */
    MOOV("moov", (byte) 1, true),
    /* movie fragment */
    MOOF("moof", (byte) 1, false),
    /* media data container */
    MFRA("mfra", (byte) 1, false),
    /* media data container */
    MDAT("mdat", (byte) 1, false),
    /* free space */
    FREE("free", (byte) 1, false),
    /* free space */
    SKIP("skip", (byte) 1, false),
    /* metadata */
    META_1("meta", (byte) 1, false),


    /* movie header, overall declarations */
    MVHD("mvhd", (byte) 2, true),
    /* metadata */
    META_2("meta", (byte) 2, false),
    /* container for an individual track or stream */
    TRAK("trak", (byte) 2, true),
    /* movie extends box */
    MVEX("mvex", (byte) 2, false),
    /*movie fragment header*/
    MFHD("mfhd", (byte) 2, true),
    /*track fragment*/
    TRAF("traf", (byte) 2, false),
    /*track fragment random access*/
    TFRA("tfra", (byte) 2, false),
    /*movie fragment random access offset*/
    MFRO("mfro", (byte) 2, false),
    /*user-data*/
    UDTA_2("udta", (byte) 2, false),
    /*handler, declares the metadata (handler) type*/
    HDLR_2("hdlr", (byte) 2, true),
    /* data information box, container */
    DINF_2("dinf", (byte) 2, false),


    /* track header, overall information about the track */
    TKHD("tkhd", (byte) 3, true),
    /* track reference container */
    TREF("tref", (byte) 3, false),
    /* track grouping indication */
    TRGR("trgr", (byte) 3, false),
    /* edit list container */
    EDTS("edts", (byte) 3, false),
    /* metadata */
    META_3("meta", (byte) 3, false),
    /* container for the media information in a track */
    MDIA("mdia", (byte) 3, true),
    /*user-data*/
    UDTA_3("udta", (byte) 3, false),
    /*movie extends header box*/
    MEHD("mehd", (byte) 3, false),
    /*track extends defaults*/
    trex("trex", (byte) 3, true),
    /*level assignment*/
    LEVA("leva", (byte) 3, true),
    /*track fragment header*/
    TFHD("tfhd", (byte) 3, true),
    /*track fragment run*/
    TRUN("trun", (byte) 3, false),
    /*sample-to-group*/
    SBGP_3("sbgp", (byte) 3, false),
    /*sample group description*/
    SGPD_3("sgpd", (byte) 3, false),
    /*sub-sample information*/
    SUBS_3("subs", (byte) 3, false),
    /*sample auxiliary information sizes*/
    SAIZ_3("saiz", (byte) 3, false),
    /*sample auxiliary information offsets*/
    SAIO_3("saio", (byte) 3, false),
    /*track fragment decode time*/
    TFDT("tfdt", (byte) 3, false),
    /*copyright etc.*/
    CPRT("cprt", (byte) 3, false),
    /*track selection box*/
    TSEL("tsel", (byte) 3, false),
    /*sub track box*/
    STRK("strk", (byte) 3, false),
    /*data reference box, declares source(s) of metadata items*/
    DREF_3("dref", (byte) 3, false),


    /* an edit list */
    ELST("elst", (byte) 4, false),
    /* media header, overall information about the media */
    MDHD("mdhd", (byte) 4, true),
    /* handler, declares the media (handler) type */
    HDLR_4("hdlr", (byte) 4, true),
    /* extended language tag */
    ELNG("elng", (byte) 4, false),
    /* media information container */
    MINF("minf", (byte) 4, true),
    /*sub track information box*/
    STRI("stri", (byte) 4, false),
    /*sub track definition box*/
    STRD("strd", (byte) 4, false),


    /* video media header, overall information (video track only) */
    VMHD("vmhd", (byte) 5, false),
    /* sound media header, overall information (sound track only) */
    SMHD("smhd", (byte) 5, false),
    /* hint media header, overall information (hint track only) */
    HMHD("hmhd", (byte) 5, false),
    /* subtitle media header, overall information (subtitle track only) */
    STHD("sthd", (byte) 5, false),
    /* Null media header, overall information (some tracks only) */
    NMHD("nmhd", (byte) 5, false),
    /* data information box, container */
    DINF_5("dinf", (byte) 5, true),
    /*sample table box, container for the time/space map*/
    STBL("stbl", (byte) 5, true),

    /*data reference box, declares source(s) of media data in track*/
    DREF_6("dref", (byte) 6, true),
    /*sample descriptions (codec types, initialization etc.)*/
    STSD("stsd", (byte) 6, true),
    /*(decoding) time-to-sample*/
    STTS("stts", (byte) 6, true),
    /*(composition) time to sample*/
    CTTS("ctts", (byte) 6, false),
    /*composition to decode timeline mapping*/
    CSLG("cslg", (byte) 6, false),
    /*sample-to-chunk, partial data-offset information*/
    STSC("stsc", (byte) 6, true),
    /*sample sizes (framing)*/
    STSZ("stsz", (byte) 6, false),
    /*compact sample sizes (framing)*/
    STZ2("stz2", (byte) 6, false),
    /*chunk offset, partial data-offset information*/
    STCO("stco", (byte) 6, true),
    /*64-bit chunk offset*/
    CO64("co64", (byte) 6, false),
    /*sync sample table*/
    STSS("stss", (byte) 6, false),
    /*shadow sync sample table*/
    STSH("stsh", (byte) 6, false),
    /*sample padding bits*/
    PADB("padb", (byte) 6, false),
    /*sample degradation priority*/
    STDP("stdp", (byte) 6, false),
    /*independent and disposable samples*/
    SDTP("sdtp", (byte) 6, false),
    /*sample-to-group*/
    SBGP_6("sbgp", (byte) 6, false),
    /*sample group description*/
    SGPD_6("sgpd", (byte) 6, false),
    /*sub-sample information*/
    SUBS_6("subs", (byte) 6, false),
    /*sample auxiliary information sizes*/
    SAIZ_6("saiz", (byte) 6, false),
    /*sample auxiliary information offsets*/
    SAIO_6("saio", (byte) 6, false),;
    public final String value;

    public final byte level;

    public final boolean required;

    BoxType(String value, byte level, boolean required) {
        this.value = value;
        this.level = level;
        this.required = required;
    }

    public static BoxType getBoxType(String value, byte level) {
        for (BoxType type : values()) {
            if (type.value.equals(value) && type.level == level) {
                return type;
            }
        }
        return null;
    }

    public static boolean isRequired(String value, byte level) {
        BoxType type = getBoxType(value, level);
        return type != null && type.required;
    }
}
