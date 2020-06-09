/*
 * Decompiled with CFR <Could not determine version>.
 */
package joptsimple;

import java.util.Collections;
import java.util.List;
import joptsimple.AbstractOptionSpec;
import joptsimple.ArgumentList;
import joptsimple.OptionParser;
import joptsimple.OptionSet;

/*
 * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
 */
class NoArgumentOptionSpec
extends AbstractOptionSpec<Void> {
    NoArgumentOptionSpec(String option) {
        this(Collections.singletonList(option), (String)"");
    }

    NoArgumentOptionSpec(List<String> options, String description) {
        super(options, (String)description);
    }

    @Override
    void handleOption(OptionParser parser, ArgumentList arguments, OptionSet detectedOptions, String detectedArgument) {
        detectedOptions.add(this);
    }

    @Override
    public boolean acceptsArguments() {
        return false;
    }

    @Override
    public boolean requiresArgument() {
        return false;
    }

    @Override
    public boolean isRequired() {
        return false;
    }

    @Override
    public String argumentDescription() {
        return "";
    }

    @Override
    public String argumentTypeIndicator() {
        return "";
    }

    @Override
    protected Void convert(String argument) {
        return null;
    }

    public List<Void> defaultValues() {
        return Collections.emptyList();
    }
}

