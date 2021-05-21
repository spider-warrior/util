package cn.t.util.media.video.mp4.modal.level4;

import cn.t.util.media.video.mp4.modal.Box;

import java.util.ArrayList;
import java.util.List;

public class ElstBox extends Box {

    private byte version;

    private byte[] flags;

    private int count;

    private List<Edit> editList = new ArrayList<>();

    public byte getVersion() {
        return version;
    }

    public void setVersion(byte version) {
        this.version = version;
    }

    public byte[] getFlags() {
        return flags;
    }

    public void setFlags(byte[] flags) {
        this.flags = flags;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public List<Edit> getEditList() {
        return editList;
    }

    public void setEditList(List<Edit> editList) {
        this.editList = editList;
    }
}

