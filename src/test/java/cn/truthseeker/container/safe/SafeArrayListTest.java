package cn.truthseeker.container.safe;

import cn.truthseeker.TestUtil;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @Description:
 * @author: qiang.fang
 * @email: lowping@163.com
 * @date: Created by on 19/3/14
 */
public class SafeArrayListTest {

    @Test
    public void init() {
        List list = new ArrayList();
        list.add(null);
        list.add(null);
        Assert.assertTrue(TestUtil.throwException((a) -> new SafeArrayList(list)));
        list.clear();
        Assert.assertFalse(TestUtil.throwException((a) -> new SafeArrayList(list)));

        new SafeArrayList<>(1);
    }

    @Test
    public void add() {
        SafeArrayList list = new SafeArrayList();
        Assert.assertTrue(TestUtil.throwException((a) -> list.add(null)));
        Assert.assertTrue(TestUtil.throwException((a) -> list.add(1,null)));

        Assert.assertFalse(TestUtil.throwException((a) -> list.add(1)));
        Assert.assertFalse(TestUtil.throwException((a) -> list.add(1,1)));
    }

    @Test
    public void addAll() {
        SafeArrayList list = new SafeArrayList();
        Assert.assertTrue(TestUtil.throwException((a) -> list.addAll(null)));
        Assert.assertTrue(TestUtil.throwException((a) -> list.addAll(1,null)));

        Assert.assertFalse(TestUtil.throwException((a) -> list.addAll(Arrays.asList(1))));
        Assert.assertFalse(TestUtil.throwException((a) -> list.addAll(1,Arrays.asList(1))));
    }
}