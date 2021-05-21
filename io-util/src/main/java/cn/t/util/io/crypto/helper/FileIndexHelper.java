package cn.t.util.io.crypto.helper;

import cn.t.util.common.ArrayUtil;
import cn.t.util.common.digital.ByteUtil;
import cn.t.util.io.crypto.Context;
import cn.t.util.io.crypto.entity.ResourceIndex;
import cn.t.util.io.crypto.exception.IndexFileInvalidException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;

public class FileIndexHelper {

    private static final Logger logger = LoggerFactory.getLogger(FileIndexHelper.class);

    private Context context;

    public ResourceIndex queryResourceIndex(FileChannel indexChannel, byte[] resourceKeyBytes) throws IOException {
        MappedByteBuffer mappedByteBuffer = indexChannel.map(FileChannel.MapMode.READ_ONLY, 1, indexChannel.size() - 1);
        while (mappedByteBuffer.hasRemaining()) {
            byte resourceKeyLength = mappedByteBuffer.get();
            if(mappedByteBuffer.remaining() < resourceKeyLength) {
                throw new IndexFileInvalidException(String.format("resourceKey: %s", new String(resourceKeyBytes)));
            }
            if(resourceKeyBytes.length == resourceKeyLength) {
                final byte[] resourceKeyData = new byte[resourceKeyLength];
                mappedByteBuffer.get(resourceKeyData);
                if(ArrayUtil.binarySearch(resourceKeyData, resourceKeyBytes) > -1) {
                    ResourceIndex resourceIndex = new ResourceIndex();
                    resourceIndex.setResourceKeyLength(resourceKeyLength);
                    resourceIndex.setResourceKeyBytes(resourceKeyBytes);
                    logger.info("find index for: {}", new String(resourceKeyBytes));
                    byte dataPathLength = mappedByteBuffer.get();
                    resourceIndex.setDataPathLength(dataPathLength);
                    byte[] dataPaths = new byte[dataPathLength];
                    mappedByteBuffer.get(dataPaths);
                    resourceIndex.setDataPath(dataPaths);
                    resourceIndex.setDataLength(mappedByteBuffer.getLong());
                    resourceIndex.setStartIndex(mappedByteBuffer.getLong());
                    resourceIndex.setDataLength(mappedByteBuffer.getLong());
                    resourceIndex.setCrTime(mappedByteBuffer.getLong());
                    return resourceIndex;
                }
            }
            //skip resource key
            mappedByteBuffer.position(mappedByteBuffer.position() + resourceKeyLength);
            int dataPathLength = mappedByteBuffer.getInt();
            //skip data path
            mappedByteBuffer.position(mappedByteBuffer.position() + dataPathLength);
            //skip start index
            mappedByteBuffer.position(mappedByteBuffer.position() + 8);
            //skip data length
            mappedByteBuffer.position(mappedByteBuffer.position() + 8);
            //skip create time
            mappedByteBuffer.position(mappedByteBuffer.position() + 8);
        }
        return null;
    }

    public byte queryChunkIndex(FileChannel indexChannel) throws IOException {
        MappedByteBuffer mappedByteBuffer = indexChannel.map(FileChannel.MapMode.READ_ONLY, 0, 1);
        return mappedByteBuffer.get();
    }

    public void persistFileIndex(FileChannel indexChannel, byte[] resourceKeyBytes, byte[] dataPaths, long dataLength, long startIndex, long endIndex) throws IOException {
        int len = 1 + resourceKeyBytes.length + 1 + dataPaths.length + 32;
        MappedByteBuffer mappedByteBuffer = indexChannel.map(FileChannel.MapMode.READ_WRITE, indexChannel.size(), len);
        mappedByteBuffer.put((byte)resourceKeyBytes.length);
        mappedByteBuffer.put(resourceKeyBytes);
        mappedByteBuffer.put((byte)dataPaths.length);
        mappedByteBuffer.put(dataPaths);
        mappedByteBuffer.putLong(dataLength);
        mappedByteBuffer.putLong(startIndex);
        mappedByteBuffer.putLong(endIndex);
        mappedByteBuffer.putLong(System.currentTimeMillis());
        mappedByteBuffer.force();
    }

    public int queryResourceIndex(byte[] resourceKeyBytes) {
        return ByteUtil.hashCode(resourceKeyBytes) % context.getRepositoryConfiguration().getIndexBucketSize();
    }



    public FileIndexHelper(Context context) {
        this.context = context;
    }
}
