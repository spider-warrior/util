package cn.t.util.io.crypto.helper;

import cn.t.util.common.ArrayUtil;
import cn.t.util.io.FileUtil;
import cn.t.util.io.crypto.Context;
import cn.t.util.io.crypto.FileType;
import cn.t.util.io.crypto.entity.PersistDataResult;
import cn.t.util.io.crypto.exception.InitFileException;
import cn.t.util.io.crypto.exception.ReadFileException;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.StandardOpenOption;
import java.util.Arrays;

public class FileDataHelper {

    private Context context;

    public PersistDataResult encryptAndPersistData(File original, int index, byte chunkIndex) throws IOException {
        String dataStorageDirectoryPath = FileUtil.appendFilePath(context.getRuntimeConfiguration().getDataDirectory().getAbsolutePath(), String.valueOf(index));
        long chunkSize = context.getRepositoryConfiguration().getUnitChunkSize();
        PersistDataResult result = new PersistDataResult();
        doEncryptAndPersistData(original, dataStorageDirectoryPath, chunkIndex, chunkSize, result);
        return result;
    }

    public void doEncryptAndPersistData(File original, String dataStorageDirectoryPath, byte chunkIndex, long chunkSize, PersistDataResult result) throws IOException {
        String dataStorageFilePath = FileUtil.appendFilePath(dataStorageDirectoryPath, String.valueOf(chunkIndex));
        File dataStorageFile = new File(dataStorageFilePath);
        boolean success = FileUtil.initFile(dataStorageFile);
        if(!success) {
            throw new InitFileException(dataStorageFile.getAbsolutePath());
        }
        if(dataStorageFile.length() > chunkSize) {
            chunkIndex++;
            doEncryptAndPersistData(original, dataStorageDirectoryPath, chunkIndex, chunkSize, result);
        } else {
            try(
                FileChannel dataStorageChannel = FileChannel.open(dataStorageFile.toPath(), StandardOpenOption.READ, StandardOpenOption.WRITE)
            ) {
                long startIndex = dataStorageChannel.size();
                byte[] fileNameBytes = original.getName().getBytes();
                if(original.isFile()) {
                    try(
                        FileInputStream is = new FileInputStream(original)
                    ) {
                        byte[] data = new byte[is.available()];
                        int len = is.read(data);
                        if(data.length != len) {
                            throw new ReadFileException(original.getAbsolutePath());
                        }
                        data = encryptData(data);
                        int dataLength = persistData(dataStorageChannel, FileType.FILE, fileNameBytes, data);
                        updatePersistResult(result, chunkIndex, dataLength, startIndex, dataStorageChannel.size());
                    }
                } else {
                    int len = persistData(dataStorageChannel, FileType.DIRECTORY_START, fileNameBytes, new byte[0]);
                    updatePersistResult(result, chunkIndex, len, startIndex, dataStorageChannel.size());
                    for(File subFile: original.listFiles()) {
                        doEncryptAndPersistData(subFile, dataStorageDirectoryPath, chunkIndex, chunkSize, result);
                    }
                    len = persistData(dataStorageChannel, FileType.DIRECTORY_END, fileNameBytes, new byte[0]);
                    updatePersistResult(result, chunkIndex, len, startIndex, dataStorageChannel.size());
                }
            }
        }

    }

    private void updatePersistResult(PersistDataResult result, byte chunkIndex, int dataLength, long startIndex, long endIndex) {
        result.setDataLength(result.getDataLength() + dataLength);
        if(Arrays.binarySearch(result.getDataPath(), chunkIndex) == -1) {
            byte[] dataPathNew = ArrayUtil.add(result.getDataPath(), chunkIndex);
            result.setDataPath(dataPathNew);
        }
        if(result.getStartIndex() == -1) {
            result.setStartIndex(startIndex);
        }
        result.setEndIndex(endIndex);
    }

    public byte[] encryptData(byte[] data) {
        return data;
    }

    public int persistData(FileChannel dataStorageChannel, byte type, byte[] fileNameBytes, byte[] data) throws IOException {
        int len = 1 + 1 + fileNameBytes.length + 4 + data.length;
        MappedByteBuffer mappedByteBuffer = dataStorageChannel.map(FileChannel.MapMode.READ_WRITE, dataStorageChannel.size(), len);
        mappedByteBuffer.put(type);
        mappedByteBuffer.put((byte)fileNameBytes.length);
        mappedByteBuffer.put(fileNameBytes);
        mappedByteBuffer.putInt(data.length);
        mappedByteBuffer.put(data);
        mappedByteBuffer.force();
        return len;
    }


    public FileDataHelper(Context context) {
        this.context = context;
    }
}
