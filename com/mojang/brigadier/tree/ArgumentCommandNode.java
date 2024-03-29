/*
 * Decompiled with CFR <Could not determine version>.
 */
package com.mojang.brigadier.tree;

import com.mojang.brigadier.Command;
import com.mojang.brigadier.RedirectModifier;
import com.mojang.brigadier.StringReader;
import com.mojang.brigadier.arguments.ArgumentType;
import com.mojang.brigadier.builder.ArgumentBuilder;
import com.mojang.brigadier.builder.RequiredArgumentBuilder;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.context.CommandContextBuilder;
import com.mojang.brigadier.context.ParsedArgument;
import com.mojang.brigadier.context.StringRange;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.mojang.brigadier.suggestion.SuggestionProvider;
import com.mojang.brigadier.suggestion.Suggestions;
import com.mojang.brigadier.suggestion.SuggestionsBuilder;
import com.mojang.brigadier.tree.CommandNode;
import java.util.Collection;
import java.util.concurrent.CompletableFuture;
import java.util.function.Predicate;

public class ArgumentCommandNode<S, T>
extends CommandNode<S> {
    private static final String USAGE_ARGUMENT_OPEN = "<";
    private static final String USAGE_ARGUMENT_CLOSE = ">";
    private final String name;
    private final ArgumentType<T> type;
    private final SuggestionProvider<S> customSuggestions;

    public ArgumentCommandNode(String name, ArgumentType<T> type, Command<S> command, Predicate<S> requirement, CommandNode<S> redirect, RedirectModifier<S> modifier, boolean forks, SuggestionProvider<S> customSuggestions) {
        super(command, requirement, redirect, modifier, (boolean)forks);
        this.name = name;
        this.type = type;
        this.customSuggestions = customSuggestions;
    }

    public ArgumentType<T> getType() {
        return this.type;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public String getUsageText() {
        return USAGE_ARGUMENT_OPEN + this.name + USAGE_ARGUMENT_CLOSE;
    }

    public SuggestionProvider<S> getCustomSuggestions() {
        return this.customSuggestions;
    }

    @Override
    public void parse(StringReader reader, CommandContextBuilder<S> contextBuilder) throws CommandSyntaxException {
        int start = reader.getCursor();
        T result = this.type.parse((StringReader)reader);
        ParsedArgument<S, T> parsed = new ParsedArgument<S, T>((int)start, (int)reader.getCursor(), result);
        contextBuilder.withArgument((String)this.name, parsed);
        contextBuilder.withNode(this, (StringRange)parsed.getRange());
    }

    @Override
    public CompletableFuture<Suggestions> listSuggestions(CommandContext<S> context, SuggestionsBuilder builder) throws CommandSyntaxException {
        if (this.customSuggestions != null) return this.customSuggestions.getSuggestions(context, (SuggestionsBuilder)builder);
        return this.type.listSuggestions(context, (SuggestionsBuilder)builder);
    }

    @Override
    public RequiredArgumentBuilder<S, T> createBuilder() {
        RequiredArgumentBuilder<S, T> builder = RequiredArgumentBuilder.argument((String)this.name, this.type);
        builder.requires(this.getRequirement());
        builder.forward(this.getRedirect(), this.getRedirectModifier(), (boolean)this.isFork());
        builder.suggests(this.customSuggestions);
        if (this.getCommand() == null) return builder;
        builder.executes(this.getCommand());
        return builder;
    }

    @Override
    public boolean isValidInput(String input) {
        try {
            StringReader reader = new StringReader((String)input);
            this.type.parse((StringReader)reader);
            if (!reader.canRead()) return true;
            if (reader.peek() == ' ') return true;
            return false;
        }
        catch (CommandSyntaxException ignored) {
            return false;
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ArgumentCommandNode)) {
            return false;
        }
        ArgumentCommandNode that = (ArgumentCommandNode)o;
        if (!this.name.equals((Object)that.name)) {
            return false;
        }
        if (this.type.equals(that.type)) return super.equals((Object)o);
        return false;
    }

    @Override
    public int hashCode() {
        int result = this.name.hashCode();
        return 31 * result + this.type.hashCode();
    }

    @Override
    protected String getSortedKey() {
        return this.name;
    }

    @Override
    public Collection<String> getExamples() {
        return this.type.getExamples();
    }

    public String toString() {
        return "<argument " + this.name + ":" + this.type + USAGE_ARGUMENT_CLOSE;
    }
}

