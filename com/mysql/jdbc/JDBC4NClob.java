/*
 * Decompiled with CFR <Could not determine version>.
 */
package com.mysql.jdbc;

import com.mysql.jdbc.Clob;
import com.mysql.jdbc.ExceptionInterceptor;
import java.sql.NClob;

public class JDBC4NClob
extends Clob
implements NClob {
    JDBC4NClob(ExceptionInterceptor exceptionInterceptor) {
        super((ExceptionInterceptor)exceptionInterceptor);
    }

    JDBC4NClob(String charDataInit, ExceptionInterceptor exceptionInterceptor) {
        super((String)charDataInit, (ExceptionInterceptor)exceptionInterceptor);
    }
}

