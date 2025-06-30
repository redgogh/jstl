package org.veronica.security.codec;

import org.veronica.utils.Captor;
import org.veronica.string.StringUtils;
import org.veronica.security.URL;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;

/**
 * @author Red Gogh
 */
public class URLCodec implements URL {

    private static final String CRYPT_PREFIX_HTTP = "http://";
    private static final String CRYPT_PREFIX_HTTPS = "https://";

    @Override
    public String encode(String source) {
        return encode(source, "UTF-8");
    }

    @Override
    public String encode(String source, String enc) {
        try {
            String temporary = "";
            if (source.startsWith(CRYPT_PREFIX_HTTPS)) {
                source = StringUtils.strcut(source, StringUtils.strlen(CRYPT_PREFIX_HTTPS), 0);
                temporary = CRYPT_PREFIX_HTTPS;
            } else if (source.startsWith(CRYPT_PREFIX_HTTP)) {
                source = StringUtils.strcut(source, StringUtils.strlen(CRYPT_PREFIX_HTTP), 0);
                temporary = CRYPT_PREFIX_HTTP;
            }
            return StringUtils.strwfmt("%s%s", temporary, URLEncoder.encode(source, enc));
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public String decode(String source) {
        return decode(source, "UTF-8");
    }

    @Override
    public String decode(String source, String enc) {
        return Captor.call(() -> URLDecoder.decode(source, enc));
    }

}
