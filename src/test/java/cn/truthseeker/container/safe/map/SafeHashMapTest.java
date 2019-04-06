package cn.truthseeker.container.safe.map;

import cn.truthseeker.TestUtil;
import cn.truthseeker.container.safe.Safes;
import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.Map;

/**
 * @Description:
 * @author: qiang.fang
 * @email: lowping@163.com
 * @date: Created by on 19/3/14
 */
public class SafeHashMapTest {

    @Test
    public void init() {
        Map<Object, Object> map = Maps.of(null, null);
        TestUtil.withException(() -> new SafeHashMap(map));

        map.clear();
        map.put("", "");
        TestUtil.noException(() -> new SafeHashMap(map));

        new SafeHashMap(1);
        new SafeHashMap(1, 0.5f);
    }

    @Test
    public void put() {
        SafeHashMap hashMap = new SafeHashMap();
        TestUtil.withException(() -> hashMap.put(null, ""));
        TestUtil.withException(() -> hashMap.put("", null));

        TestUtil.noException(() -> hashMap.put("", ""));
        TestUtil.noException(() -> hashMap.put("", ""));
    }

    @Test
    public void putAll() {
        Map<Object, Object> map = Maps.of(null, null);

        SafeHashMap hashMap = new SafeHashMap();
        TestUtil.withException(() -> hashMap.putAll(map));

        map.clear();
        map.put("", "");
        TestUtil.noException(() -> hashMap.putAll(map));
    }

    @Test
    public void getNullable() {
        SafeHashMap safeHashMap = new SafeHashMap();
        Assert.assertFalse(safeHashMap.getNullable("").isPresent());
    }

    @Test
    public void newInstance() {
        SafeHashMap safeHashMap = new SafeHashMap();
        safeHashMap.newInstance();
    }

    @Test
    public void get() {
        SafeHashMap safeHashMap = new SafeHashMap();
        Assert.assertTrue(safeHashMap.get("") == null);
    }

    @Test
    public void mapKey() {
        SafeHashMap<String, String> all = CommonMaps.of("1", "1", SafeHashMap::new);

        SafeMap<Integer, String> ret = all.mapKey((a) -> Integer.parseInt(a));
        Assert.assertTrue(ret.getNullable(1).get().equals("1"));
    }

    @Test
    public void mapValue() {
        SafeHashMap<String, String> all = CommonMaps.of("1", "1", SafeHashMap::new);

        SafeMap<String, Integer> ret = all.mapValue((a) -> Integer.parseInt(a));
        Assert.assertTrue(ret.getNullable("1").get() == 1);
    }

    @Test
    public void mapKeyValue() {
        SafeHashMap<String, String> all = CommonMaps.of("1", "1", SafeHashMap::new);

        SafeMap<Integer, Integer> ret = all.mapKeyValue((a) -> Integer.parseInt(a), (a) -> Integer.parseInt(a));
        Assert.assertTrue(ret.getNullable(1).get() == 1);
    }

    @Test
    public void filterByKey() {
        SafeHashMap<String, String> all = CommonMaps.of("a", "1", "b", "1", SafeHashMap::new);

        Assert.assertTrue(all.filterByKey(a -> a.equals("a")).size() == 1);
        Assert.assertTrue(all.filterByKey(a -> a.equals("c")).size() == 0);
    }

    @Test
    public void filterByValue() {
        SafeHashMap<String, String> all = CommonMaps.of("a", "1", "b", "2", SafeHashMap::new);

        Assert.assertTrue(all.filterByValue(a -> a.equals("1")).size() == 1);
        Assert.assertTrue(all.filterByValue(a -> a.equals("3")).size() == 0);
    }

    @Test
    public void filterByKeyValue() {
        SafeHashMap<String, String> all = CommonMaps.of("a", "1", "b", "2", SafeHashMap::new);

        Assert.assertTrue(all.filterByKeyValue((a, b) -> a.equals("a") && b.equals("1")).size() == 1);
        Assert.assertTrue(all.filterByKeyValue((a, b) -> a.equals("a") && b.equals("2")).size() == 0);
    }

    @Test
    public void containsMap() {
        SafeHashMap<String, Integer> all = CommonMaps.of("a", 1, "b", 2, "c", 3, SafeHashMap::new);
        SafeHashMap<String, Integer> part = CommonMaps.of("a", 1, "b", 2, SafeHashMap::new);
        Assert.assertTrue(all.containsMap(part));
        part.put("b", 3);
        Assert.assertFalse(all.containsMap(part));
    }

    @Test
    public void getSubMap() {
        SafeHashMap<String, Integer> all = CommonMaps.of("a", 1, "b", 2, "c", 3, SafeHashMap::new);
        Map<String, Integer> subMap = all.getSubMap(Arrays.asList("a", "b"));
        Assert.assertTrue(all.containsMap(subMap));
    }

    @Test
    public void containsKeys() {
        SafeHashMap<String, Integer> all = CommonMaps.of("a", 1, "b", 2, "c", 3, SafeHashMap::new);
        Assert.assertTrue(all.containsKeys(Arrays.asList("a", "b")));
        Assert.assertFalse(all.containsKeys(Arrays.asList("a", "d")));
    }

    @Test
    public void removeIf() {
        SafeHashMap<String, Integer> all = CommonMaps.of("a", 1, "b", 2, SafeHashMap::new);
        Assert.assertTrue(all.size() == 2);
        all.removeIf((k, v) -> k.equals("a") && v == 1);
        Assert.assertTrue(all.size() == 1);
        Assert.assertTrue(all.getTheOnlyKey().get().equals("b"));
    }

    @Test
    public void anySatisfied() {
        Assert.assertTrue(CommonMaps.of(10, 1, 20, 2, SafeHashMap::new).anySatisfied((a, b) -> a + b == 11));
        Assert.assertTrue(!CommonMaps.of(10, 1, 20, 2, SafeHashMap::new).anySatisfied((a, b) -> a + b == 12));
    }

    @Test
    public void forEach2() {
        Assert.assertTrue(Safes.newSafeMap(1, 1).forEach2((k, v) -> System.out.println(k)).size() == 1);
    }

    @Test
    public void getOrCreate() {
        SafeMap<Object, Object> map = Safes.newSafeMap();
        Object orCreate = map.getOrCreate(1, () -> 2);

        Assert.assertTrue((int)orCreate == 2);
        Assert.assertTrue(map.size() == 1);
    }
}