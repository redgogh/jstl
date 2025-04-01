package org.karasuba.jwtc;

import org.karasuba.iface.TypeMapper;
import org.karasuba.time.Chrono;
import org.karasuba.utils.Transformer;

import java.util.HashMap;

import static org.karasuba.utils.Transformer.atol;
import static org.karasuba.utils.Transformer.atos;

/**
 * `JWTClaims` 是一个扩展自 {@link HashMap} 的类，表示 JWT 中的声明部分。
 *
 * <p>该类用于存储 JWT 的所有声明（如 subject、issuer、expiration 等），每个声明都是一个键值对。
 * 它继承自 {@link HashMap}，因此可以方便地进行键值对操作。
 *
 * <p>该类提供了与 JWT 声明相关的额外方法，可以灵活地获取或修改 JWT 声明。
 * 它的主要目的是将 JWT 中的声明封装为一个可操作的集合，便于处理 JWT 数据。<p>
 *
 * <h2>使用示例</h2>
 * <pre>
 *     JWTClaims claims = new JWTClaims();
 *     claims.put("sub", "user123");
 *     claims.put("iss", "myApp");
 *     String subject = (String) claims.get("sub");
 * </pre>
 *
 * @author Red Gogh
 * @since 1.0
 */
public class JWTClaims extends HashMap<String, Object> {

    /**
     * #brief: 获取 JWT 中的 subject（主题）
     *
     * <p>从 JWT 声明中获取 "subject" 属性并返回其值。
     *
     * @return JWT 中的 subject 字段值
     */
    public String getSubject() {
        return atos(get("subject"));
    }

    /**
     * #brief: 获取 JWT 中的 audience（受众）
     *
     * <p>从 JWT 声明中获取 "audience" 属性并返回其值。
     *
     * @return JWT 中的 audience 字段值
     */
    public String getAudience() {
        return atos(get("audience"));
    }

    /**
     * #brief: 获取 JWT 中的 issuer（签发者）
     *
     * <p>从 JWT 声明中获取 "iss" 属性并返回其值。
     *
     * @return JWT 中的 issuer 字段值
     */
    public String getIssuer() {
        return atos(get("iss"));
    }

    /**
     * #brief: 获取 JWT 的 issuedAt（签发时间）
     *
     * <p>从 JWT 声明中获取 "iat" 属性并将其转换为 Chrono 对象，表示签发时间。
     *
     * @return JWT 的签发时间
     */
    public Chrono getIssuedAt() {
        return Chrono.from(atol(get("iat")) * 1000);
    }

    /**
     * #brief: 获取 JWT 的 expiration（过期时间）
     *
     * <p>从 JWT 声明中获取 "exp" 属性并将其转换为 Chrono 对象，表示过期时间。
     *
     * @return JWT 的过期时间
     */
    public Chrono getExpiration() {
        return Chrono.from(atol(get("exp")) * 1000);
    }

    /**
     * #brief: 检查 JWT 是否已过期
     *
     * <p>通过检查 JWT 的 expiration 属性与当前时间的对比，判断 JWT 是否已过期。
     *
     * @return 如果 JWT 已过期，返回 `true`；否则返回 `false`
     */
    public boolean isExpiration() {
        return getExpiration().isBefore(Chrono.now());
    }

    /**
     * #brief: 获取 JWT 的指定属性
     *
     * <p>根据指定的键获取 JWT 中的属性，并通过默认转换器（BasicConverter）进行转换。
     *
     * @param key 属性的键
     * @return 属性的值
     */
    public String getAttribute(String key) {
        return getAttribute(key, Transformer::atos);
    }

    /**
     * #brief: 获取 JWT 的指定属性并应用自定义转换器
     *
     * <p>根据指定的键获取 JWT 中的属性，并应用提供的转换器对值进行转换。
     *
     * @param key 属性的键
     * @param mapper 自定义的类型转换器
     * @param <T> 原始值的类型
     * @param <R> 转换后的值的类型
     * @return 转换后的属性值
     */
    @SuppressWarnings("unchecked")
    public <T, R> R getAttribute(String key, TypeMapper<T, R> mapper) {
        return mapper.call((T) get(key));
    }

}
