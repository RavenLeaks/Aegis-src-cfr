/*
 * Decompiled with CFR <Could not determine version>.
 */
package gnu.trove.impl.unmodifiable;

import gnu.trove.impl.unmodifiable.TUnmodifiableShortList;
import gnu.trove.list.TShortList;
import java.util.RandomAccess;

public class TUnmodifiableRandomAccessShortList
extends TUnmodifiableShortList
implements RandomAccess {
    private static final long serialVersionUID = -2542308836966382001L;

    public TUnmodifiableRandomAccessShortList(TShortList list) {
        super((TShortList)list);
    }

    @Override
    public TShortList subList(int fromIndex, int toIndex) {
        return new TUnmodifiableRandomAccessShortList((TShortList)this.list.subList((int)fromIndex, (int)toIndex));
    }

    private Object writeReplace() {
        return new TUnmodifiableShortList((TShortList)this.list);
    }
}

