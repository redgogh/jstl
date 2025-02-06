package org.redgogh.commons.io;

import org.redgogh.commons.exception.IOReadException;

import java.io.IOException;
import java.io.InputStream;

/**
 * 自定义的输入流读取器，扩展自 {@link InputStream} 类。
 * <p>
 * 该类封装了一个原始的输入流（`InputStream`），并提供了一个便利的 `call()` 方法，
 * 用于执行传入的回调函数并自动处理资源关闭，避免手动管理流的关闭操作。
 * <p>
 * 使用时，可以通过 `call()` 方法执行指定操作，操作执行完成后，输入流将自动关闭。
 *
 * @author Red Gogh
 */
public class StreamReader extends InputStream {

    private final InputStream stream;

    /**
     * 回调接口，用于执行自定义的流处理操作。
     */
    public interface StreamReaderResource {
        /**
         * 自定义的流处理操作方法。
         *
         * @param reader 当前的 {@link StreamReader} 实例
         * @throws Exception 如果处理过程中出现异常
         */
        void call(StreamReader reader) throws Exception;
    }

    /**
     * 构造方法，初始化 StreamReader。
     *
     * @param stream 原始的 {@link InputStream} 实例
     */
    public StreamReader(InputStream stream) {
        this.stream = stream;
    }

    /**
     * 执行指定的流处理操作，并确保在操作完成后关闭输入流。
     * <p>
     * 该方法会自动关闭流，避免了手动关闭的麻烦，且对异常进行了封装。
     *
     * @param streamReaderResource 自定义的流处理回调接口
     */
    public void call(StreamReaderResource streamReaderResource) {
        try {
            streamReaderResource.call(this);
        } catch (Exception e) {
            throw new IOReadException(e);
        } finally {
            IOUtils.closeQuietly(this);
        }
    }

    /**
     * 从流中读取一个字节。
     *
     * @return 读取到的字节（0~255），如果已经到达流末尾，返回 -1。
     * @throws IOException 如果读取时发生异常
     */
    @Override
    public int read() throws IOException {
        return stream.read();
    }

}
