package com.redgogh.springframework.boot.ibatis;

/* -------------------------------------------------------------------------------- *\
|*                                                                                  *|
|*    Copyright (C) 2023 RedGogh                                                    *|
|*                                                                                  *|
|*    This program is free software: you can redistribute it and/or modify          *|
|*    it under the terms of the GNU General Public License as published by          *|
|*    the Free Software Foundation, either version 3 of the License, or             *|
|*    (at your option) any later version.                                           *|
|*                                                                                  *|
|*    This program is distributed in the hope that it will be useful,               *|
|*    but WITHOUT ANY WARRANTY; without even the implied warranty of                *|
|*    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the                 *|
|*    GNU General Public License for more details.                                  *|
|*                                                                                  *|
|*    You should have received a copy of the GNU General Public License             *|
|*    along with this program.  If not, see <https://www.gnu.org/licenses/>.        *|
|*                                                                                  *|
|*    This program comes with ABSOLUTELY NO WARRANTY; for details type `show w'.    *|
|*    This is free software, and you are welcome to redistribute it                 *|
|*    under certain conditions; type `show c' for details.                          *|
|*                                                                                  *|
\* -------------------------------------------------------------------------------- */

/* Creates on 2023/4/16. */

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.redgogh.libext.StreamMapping;
import com.redgogh.libext.collection.Collects;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import static com.redgogh.libext.collection.Collects.listMap;

/**
 * @author RedGogh
 */
@SuppressWarnings("UnusedReturnValue")
public interface IbatisService<T> extends IService<T> {

    /**
     * 获取单个对象，接收条件（ID）
     */
    default T sqlfnQuery(Serializable id) {
        return getById(id);
    }

    /**
     * 获取单个对象，接收条件（eq 1）
     */
    default T sqlfnQuery(String k1, Object v1) {
        return getOne(new QueryWrapper<T>().eq(k1, v1));
    }

    /**
     * 获取单个对象，接收条件（eq n）
     */
    default T sqlfnQuery(Map<String, Object> cond) {
        return getOne(new QueryWrapper<T>().allEq(cond));
    }

    /**
     * 获取单个对象，接收条件（QueryWrapper）
     */
    default T sqlfnQuery(Wrapper<T> wrapper) {
        return getOne(wrapper);
    }

    @SuppressWarnings("unchecked")
    default List<T> sqlfnQueryBatch(List<? extends Serializable> id) {
        if (Collects.isEmptyList(id))
            return Collects.EMPTY_LIST;
        return listByIds(id);
    }

    /**
     * 获取多个对象，接收条件（QueryWrapper）
     */
    default List<T> sqlfnQueryBatch(Wrapper<T> wrapper) {
        return list(wrapper);
    }

    /**
     * 分页查询
     */
    default <E extends IPage<T>> E sqlfnQueryPapge(E page, Wrapper<T> wrapper) {
        return page(page, wrapper);
    }

    /**
     * 保存单个对象
     */
    default void sqlfnSave(T entity) {
        save(entity);
    }

    /**
     * 批量保存
     */
    @Transactional(rollbackFor = Exception.class)
    default void sqlfnSaveBatch(Collection<T> entityList) {
        saveBatch(entityList);
    }

    /**
     * 批量保存
     */
    @Transactional(rollbackFor = Exception.class)
    default <E> List<T> sqlfnSaveBatch(Collection<E> list, StreamMapping<E, T> mapper) {
        List<T> ret = listMap(list, mapper);
        saveBatch(ret);
        return ret;
    }

    /**
     * 根据对象id更新对象
     */
    default void sqlfnUpdate(T object) {
        updateById(object);
    }

    /**
     * 批量更新
     */
    @Transactional(rollbackFor = Exception.class)
    default void sqlfnUpdateBatch(Collection<T> entityList) {
        updateBatchById(entityList);
    }

    /**
     * 删除单个对象
     */
    default void sqlfnRemove(Wrapper<T> queryWrapper) {
        remove(queryWrapper);
    }

    //////////////////////////////////////////////////////////////////////////////////////
    /// new
    //////////////////////////////////////////////////////////////////////////////////////

    default LambdaQueryWrapper<T> newLambdaQueryWrapper() {
        return new LambdaQueryWrapper<T>();
    }

    default LambdaUpdateWrapper<T> newLambdaUpdateWrapper() {
        return new LambdaUpdateWrapper<T>();
    }

}
