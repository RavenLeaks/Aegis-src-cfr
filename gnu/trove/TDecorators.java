/*
 * Decompiled with CFR <Could not determine version>.
 */
package gnu.trove;

import gnu.trove.decorator.TByteByteMapDecorator;
import gnu.trove.decorator.TByteCharMapDecorator;
import gnu.trove.decorator.TByteDoubleMapDecorator;
import gnu.trove.decorator.TByteFloatMapDecorator;
import gnu.trove.decorator.TByteIntMapDecorator;
import gnu.trove.decorator.TByteListDecorator;
import gnu.trove.decorator.TByteLongMapDecorator;
import gnu.trove.decorator.TByteObjectMapDecorator;
import gnu.trove.decorator.TByteSetDecorator;
import gnu.trove.decorator.TByteShortMapDecorator;
import gnu.trove.decorator.TCharByteMapDecorator;
import gnu.trove.decorator.TCharCharMapDecorator;
import gnu.trove.decorator.TCharDoubleMapDecorator;
import gnu.trove.decorator.TCharFloatMapDecorator;
import gnu.trove.decorator.TCharIntMapDecorator;
import gnu.trove.decorator.TCharListDecorator;
import gnu.trove.decorator.TCharLongMapDecorator;
import gnu.trove.decorator.TCharObjectMapDecorator;
import gnu.trove.decorator.TCharSetDecorator;
import gnu.trove.decorator.TCharShortMapDecorator;
import gnu.trove.decorator.TDoubleByteMapDecorator;
import gnu.trove.decorator.TDoubleCharMapDecorator;
import gnu.trove.decorator.TDoubleDoubleMapDecorator;
import gnu.trove.decorator.TDoubleFloatMapDecorator;
import gnu.trove.decorator.TDoubleIntMapDecorator;
import gnu.trove.decorator.TDoubleListDecorator;
import gnu.trove.decorator.TDoubleLongMapDecorator;
import gnu.trove.decorator.TDoubleObjectMapDecorator;
import gnu.trove.decorator.TDoubleSetDecorator;
import gnu.trove.decorator.TDoubleShortMapDecorator;
import gnu.trove.decorator.TFloatByteMapDecorator;
import gnu.trove.decorator.TFloatCharMapDecorator;
import gnu.trove.decorator.TFloatDoubleMapDecorator;
import gnu.trove.decorator.TFloatFloatMapDecorator;
import gnu.trove.decorator.TFloatIntMapDecorator;
import gnu.trove.decorator.TFloatListDecorator;
import gnu.trove.decorator.TFloatLongMapDecorator;
import gnu.trove.decorator.TFloatObjectMapDecorator;
import gnu.trove.decorator.TFloatSetDecorator;
import gnu.trove.decorator.TFloatShortMapDecorator;
import gnu.trove.decorator.TIntByteMapDecorator;
import gnu.trove.decorator.TIntCharMapDecorator;
import gnu.trove.decorator.TIntDoubleMapDecorator;
import gnu.trove.decorator.TIntFloatMapDecorator;
import gnu.trove.decorator.TIntIntMapDecorator;
import gnu.trove.decorator.TIntListDecorator;
import gnu.trove.decorator.TIntLongMapDecorator;
import gnu.trove.decorator.TIntObjectMapDecorator;
import gnu.trove.decorator.TIntSetDecorator;
import gnu.trove.decorator.TIntShortMapDecorator;
import gnu.trove.decorator.TLongByteMapDecorator;
import gnu.trove.decorator.TLongCharMapDecorator;
import gnu.trove.decorator.TLongDoubleMapDecorator;
import gnu.trove.decorator.TLongFloatMapDecorator;
import gnu.trove.decorator.TLongIntMapDecorator;
import gnu.trove.decorator.TLongListDecorator;
import gnu.trove.decorator.TLongLongMapDecorator;
import gnu.trove.decorator.TLongObjectMapDecorator;
import gnu.trove.decorator.TLongSetDecorator;
import gnu.trove.decorator.TLongShortMapDecorator;
import gnu.trove.decorator.TObjectByteMapDecorator;
import gnu.trove.decorator.TObjectCharMapDecorator;
import gnu.trove.decorator.TObjectDoubleMapDecorator;
import gnu.trove.decorator.TObjectFloatMapDecorator;
import gnu.trove.decorator.TObjectIntMapDecorator;
import gnu.trove.decorator.TObjectLongMapDecorator;
import gnu.trove.decorator.TObjectShortMapDecorator;
import gnu.trove.decorator.TShortByteMapDecorator;
import gnu.trove.decorator.TShortCharMapDecorator;
import gnu.trove.decorator.TShortDoubleMapDecorator;
import gnu.trove.decorator.TShortFloatMapDecorator;
import gnu.trove.decorator.TShortIntMapDecorator;
import gnu.trove.decorator.TShortListDecorator;
import gnu.trove.decorator.TShortLongMapDecorator;
import gnu.trove.decorator.TShortObjectMapDecorator;
import gnu.trove.decorator.TShortSetDecorator;
import gnu.trove.decorator.TShortShortMapDecorator;
import gnu.trove.list.TByteList;
import gnu.trove.list.TCharList;
import gnu.trove.list.TDoubleList;
import gnu.trove.list.TFloatList;
import gnu.trove.list.TIntList;
import gnu.trove.list.TLongList;
import gnu.trove.list.TShortList;
import gnu.trove.map.TByteByteMap;
import gnu.trove.map.TByteCharMap;
import gnu.trove.map.TByteDoubleMap;
import gnu.trove.map.TByteFloatMap;
import gnu.trove.map.TByteIntMap;
import gnu.trove.map.TByteLongMap;
import gnu.trove.map.TByteObjectMap;
import gnu.trove.map.TByteShortMap;
import gnu.trove.map.TCharByteMap;
import gnu.trove.map.TCharCharMap;
import gnu.trove.map.TCharDoubleMap;
import gnu.trove.map.TCharFloatMap;
import gnu.trove.map.TCharIntMap;
import gnu.trove.map.TCharLongMap;
import gnu.trove.map.TCharObjectMap;
import gnu.trove.map.TCharShortMap;
import gnu.trove.map.TDoubleByteMap;
import gnu.trove.map.TDoubleCharMap;
import gnu.trove.map.TDoubleDoubleMap;
import gnu.trove.map.TDoubleFloatMap;
import gnu.trove.map.TDoubleIntMap;
import gnu.trove.map.TDoubleLongMap;
import gnu.trove.map.TDoubleObjectMap;
import gnu.trove.map.TDoubleShortMap;
import gnu.trove.map.TFloatByteMap;
import gnu.trove.map.TFloatCharMap;
import gnu.trove.map.TFloatDoubleMap;
import gnu.trove.map.TFloatFloatMap;
import gnu.trove.map.TFloatIntMap;
import gnu.trove.map.TFloatLongMap;
import gnu.trove.map.TFloatObjectMap;
import gnu.trove.map.TFloatShortMap;
import gnu.trove.map.TIntByteMap;
import gnu.trove.map.TIntCharMap;
import gnu.trove.map.TIntDoubleMap;
import gnu.trove.map.TIntFloatMap;
import gnu.trove.map.TIntIntMap;
import gnu.trove.map.TIntLongMap;
import gnu.trove.map.TIntObjectMap;
import gnu.trove.map.TIntShortMap;
import gnu.trove.map.TLongByteMap;
import gnu.trove.map.TLongCharMap;
import gnu.trove.map.TLongDoubleMap;
import gnu.trove.map.TLongFloatMap;
import gnu.trove.map.TLongIntMap;
import gnu.trove.map.TLongLongMap;
import gnu.trove.map.TLongObjectMap;
import gnu.trove.map.TLongShortMap;
import gnu.trove.map.TObjectByteMap;
import gnu.trove.map.TObjectCharMap;
import gnu.trove.map.TObjectDoubleMap;
import gnu.trove.map.TObjectFloatMap;
import gnu.trove.map.TObjectIntMap;
import gnu.trove.map.TObjectLongMap;
import gnu.trove.map.TObjectShortMap;
import gnu.trove.map.TShortByteMap;
import gnu.trove.map.TShortCharMap;
import gnu.trove.map.TShortDoubleMap;
import gnu.trove.map.TShortFloatMap;
import gnu.trove.map.TShortIntMap;
import gnu.trove.map.TShortLongMap;
import gnu.trove.map.TShortObjectMap;
import gnu.trove.map.TShortShortMap;
import gnu.trove.set.TByteSet;
import gnu.trove.set.TCharSet;
import gnu.trove.set.TDoubleSet;
import gnu.trove.set.TFloatSet;
import gnu.trove.set.TIntSet;
import gnu.trove.set.TLongSet;
import gnu.trove.set.TShortSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class TDecorators {
    private TDecorators() {
    }

    public static Map<Double, Double> wrap(TDoubleDoubleMap map) {
        return new TDoubleDoubleMapDecorator((TDoubleDoubleMap)map);
    }

    public static Map<Double, Float> wrap(TDoubleFloatMap map) {
        return new TDoubleFloatMapDecorator((TDoubleFloatMap)map);
    }

    public static Map<Double, Integer> wrap(TDoubleIntMap map) {
        return new TDoubleIntMapDecorator((TDoubleIntMap)map);
    }

    public static Map<Double, Long> wrap(TDoubleLongMap map) {
        return new TDoubleLongMapDecorator((TDoubleLongMap)map);
    }

    public static Map<Double, Byte> wrap(TDoubleByteMap map) {
        return new TDoubleByteMapDecorator((TDoubleByteMap)map);
    }

    public static Map<Double, Short> wrap(TDoubleShortMap map) {
        return new TDoubleShortMapDecorator((TDoubleShortMap)map);
    }

    public static Map<Double, Character> wrap(TDoubleCharMap map) {
        return new TDoubleCharMapDecorator((TDoubleCharMap)map);
    }

    public static Map<Float, Double> wrap(TFloatDoubleMap map) {
        return new TFloatDoubleMapDecorator((TFloatDoubleMap)map);
    }

    public static Map<Float, Float> wrap(TFloatFloatMap map) {
        return new TFloatFloatMapDecorator((TFloatFloatMap)map);
    }

    public static Map<Float, Integer> wrap(TFloatIntMap map) {
        return new TFloatIntMapDecorator((TFloatIntMap)map);
    }

    public static Map<Float, Long> wrap(TFloatLongMap map) {
        return new TFloatLongMapDecorator((TFloatLongMap)map);
    }

    public static Map<Float, Byte> wrap(TFloatByteMap map) {
        return new TFloatByteMapDecorator((TFloatByteMap)map);
    }

    public static Map<Float, Short> wrap(TFloatShortMap map) {
        return new TFloatShortMapDecorator((TFloatShortMap)map);
    }

    public static Map<Float, Character> wrap(TFloatCharMap map) {
        return new TFloatCharMapDecorator((TFloatCharMap)map);
    }

    public static Map<Integer, Double> wrap(TIntDoubleMap map) {
        return new TIntDoubleMapDecorator((TIntDoubleMap)map);
    }

    public static Map<Integer, Float> wrap(TIntFloatMap map) {
        return new TIntFloatMapDecorator((TIntFloatMap)map);
    }

    public static Map<Integer, Integer> wrap(TIntIntMap map) {
        return new TIntIntMapDecorator((TIntIntMap)map);
    }

    public static Map<Integer, Long> wrap(TIntLongMap map) {
        return new TIntLongMapDecorator((TIntLongMap)map);
    }

    public static Map<Integer, Byte> wrap(TIntByteMap map) {
        return new TIntByteMapDecorator((TIntByteMap)map);
    }

    public static Map<Integer, Short> wrap(TIntShortMap map) {
        return new TIntShortMapDecorator((TIntShortMap)map);
    }

    public static Map<Integer, Character> wrap(TIntCharMap map) {
        return new TIntCharMapDecorator((TIntCharMap)map);
    }

    public static Map<Long, Double> wrap(TLongDoubleMap map) {
        return new TLongDoubleMapDecorator((TLongDoubleMap)map);
    }

    public static Map<Long, Float> wrap(TLongFloatMap map) {
        return new TLongFloatMapDecorator((TLongFloatMap)map);
    }

    public static Map<Long, Integer> wrap(TLongIntMap map) {
        return new TLongIntMapDecorator((TLongIntMap)map);
    }

    public static Map<Long, Long> wrap(TLongLongMap map) {
        return new TLongLongMapDecorator((TLongLongMap)map);
    }

    public static Map<Long, Byte> wrap(TLongByteMap map) {
        return new TLongByteMapDecorator((TLongByteMap)map);
    }

    public static Map<Long, Short> wrap(TLongShortMap map) {
        return new TLongShortMapDecorator((TLongShortMap)map);
    }

    public static Map<Long, Character> wrap(TLongCharMap map) {
        return new TLongCharMapDecorator((TLongCharMap)map);
    }

    public static Map<Byte, Double> wrap(TByteDoubleMap map) {
        return new TByteDoubleMapDecorator((TByteDoubleMap)map);
    }

    public static Map<Byte, Float> wrap(TByteFloatMap map) {
        return new TByteFloatMapDecorator((TByteFloatMap)map);
    }

    public static Map<Byte, Integer> wrap(TByteIntMap map) {
        return new TByteIntMapDecorator((TByteIntMap)map);
    }

    public static Map<Byte, Long> wrap(TByteLongMap map) {
        return new TByteLongMapDecorator((TByteLongMap)map);
    }

    public static Map<Byte, Byte> wrap(TByteByteMap map) {
        return new TByteByteMapDecorator((TByteByteMap)map);
    }

    public static Map<Byte, Short> wrap(TByteShortMap map) {
        return new TByteShortMapDecorator((TByteShortMap)map);
    }

    public static Map<Byte, Character> wrap(TByteCharMap map) {
        return new TByteCharMapDecorator((TByteCharMap)map);
    }

    public static Map<Short, Double> wrap(TShortDoubleMap map) {
        return new TShortDoubleMapDecorator((TShortDoubleMap)map);
    }

    public static Map<Short, Float> wrap(TShortFloatMap map) {
        return new TShortFloatMapDecorator((TShortFloatMap)map);
    }

    public static Map<Short, Integer> wrap(TShortIntMap map) {
        return new TShortIntMapDecorator((TShortIntMap)map);
    }

    public static Map<Short, Long> wrap(TShortLongMap map) {
        return new TShortLongMapDecorator((TShortLongMap)map);
    }

    public static Map<Short, Byte> wrap(TShortByteMap map) {
        return new TShortByteMapDecorator((TShortByteMap)map);
    }

    public static Map<Short, Short> wrap(TShortShortMap map) {
        return new TShortShortMapDecorator((TShortShortMap)map);
    }

    public static Map<Short, Character> wrap(TShortCharMap map) {
        return new TShortCharMapDecorator((TShortCharMap)map);
    }

    public static Map<Character, Double> wrap(TCharDoubleMap map) {
        return new TCharDoubleMapDecorator((TCharDoubleMap)map);
    }

    public static Map<Character, Float> wrap(TCharFloatMap map) {
        return new TCharFloatMapDecorator((TCharFloatMap)map);
    }

    public static Map<Character, Integer> wrap(TCharIntMap map) {
        return new TCharIntMapDecorator((TCharIntMap)map);
    }

    public static Map<Character, Long> wrap(TCharLongMap map) {
        return new TCharLongMapDecorator((TCharLongMap)map);
    }

    public static Map<Character, Byte> wrap(TCharByteMap map) {
        return new TCharByteMapDecorator((TCharByteMap)map);
    }

    public static Map<Character, Short> wrap(TCharShortMap map) {
        return new TCharShortMapDecorator((TCharShortMap)map);
    }

    public static Map<Character, Character> wrap(TCharCharMap map) {
        return new TCharCharMapDecorator((TCharCharMap)map);
    }

    public static <T> Map<T, Double> wrap(TObjectDoubleMap<T> map) {
        return new TObjectDoubleMapDecorator<T>(map);
    }

    public static <T> Map<T, Float> wrap(TObjectFloatMap<T> map) {
        return new TObjectFloatMapDecorator<T>(map);
    }

    public static <T> Map<T, Integer> wrap(TObjectIntMap<T> map) {
        return new TObjectIntMapDecorator<T>(map);
    }

    public static <T> Map<T, Long> wrap(TObjectLongMap<T> map) {
        return new TObjectLongMapDecorator<T>(map);
    }

    public static <T> Map<T, Byte> wrap(TObjectByteMap<T> map) {
        return new TObjectByteMapDecorator<T>(map);
    }

    public static <T> Map<T, Short> wrap(TObjectShortMap<T> map) {
        return new TObjectShortMapDecorator<T>(map);
    }

    public static <T> Map<T, Character> wrap(TObjectCharMap<T> map) {
        return new TObjectCharMapDecorator<T>(map);
    }

    public static <T> Map<Double, T> wrap(TDoubleObjectMap<T> map) {
        return new TDoubleObjectMapDecorator<T>(map);
    }

    public static <T> Map<Float, T> wrap(TFloatObjectMap<T> map) {
        return new TFloatObjectMapDecorator<T>(map);
    }

    public static <T> Map<Integer, T> wrap(TIntObjectMap<T> map) {
        return new TIntObjectMapDecorator<T>(map);
    }

    public static <T> Map<Long, T> wrap(TLongObjectMap<T> map) {
        return new TLongObjectMapDecorator<T>(map);
    }

    public static <T> Map<Byte, T> wrap(TByteObjectMap<T> map) {
        return new TByteObjectMapDecorator<T>(map);
    }

    public static <T> Map<Short, T> wrap(TShortObjectMap<T> map) {
        return new TShortObjectMapDecorator<T>(map);
    }

    public static <T> Map<Character, T> wrap(TCharObjectMap<T> map) {
        return new TCharObjectMapDecorator<T>(map);
    }

    public static Set<Double> wrap(TDoubleSet set) {
        return new TDoubleSetDecorator((TDoubleSet)set);
    }

    public static Set<Float> wrap(TFloatSet set) {
        return new TFloatSetDecorator((TFloatSet)set);
    }

    public static Set<Integer> wrap(TIntSet set) {
        return new TIntSetDecorator((TIntSet)set);
    }

    public static Set<Long> wrap(TLongSet set) {
        return new TLongSetDecorator((TLongSet)set);
    }

    public static Set<Byte> wrap(TByteSet set) {
        return new TByteSetDecorator((TByteSet)set);
    }

    public static Set<Short> wrap(TShortSet set) {
        return new TShortSetDecorator((TShortSet)set);
    }

    public static Set<Character> wrap(TCharSet set) {
        return new TCharSetDecorator((TCharSet)set);
    }

    public static List<Double> wrap(TDoubleList list) {
        return new TDoubleListDecorator((TDoubleList)list);
    }

    public static List<Float> wrap(TFloatList list) {
        return new TFloatListDecorator((TFloatList)list);
    }

    public static List<Integer> wrap(TIntList list) {
        return new TIntListDecorator((TIntList)list);
    }

    public static List<Long> wrap(TLongList list) {
        return new TLongListDecorator((TLongList)list);
    }

    public static List<Byte> wrap(TByteList list) {
        return new TByteListDecorator((TByteList)list);
    }

    public static List<Short> wrap(TShortList list) {
        return new TShortListDecorator((TShortList)list);
    }

    public static List<Character> wrap(TCharList list) {
        return new TCharListDecorator((TCharList)list);
    }
}

