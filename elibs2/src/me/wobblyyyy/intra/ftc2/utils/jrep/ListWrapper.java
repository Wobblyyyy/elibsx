/*
 * **
 *
 * Copyright (c) 2020
 * Copyright last updated on 6/9/20, 5:49 PM
 * Part of the _1125c library
 *
 * **
 *
 * Permission is granted, free of charge, to any person obtaining
 * a copy of this software and / or any of it's related source code or
 * documentation ("Software") to copy, merge, modify, publish,
 * distribute, sublicense, and / or sell copies of Software.
 *
 * All Software included is provided in an "as is" state, without any
 * type or form of warranty. The Authors and Copyright Holders of this
 * piece of software, documentation, or source code waive all
 * responsibility and shall not be liable for any claim, damages, or
 * other forms of liability, regardless of the form it may take.
 *
 * Any form of re-distribution of Software is required to have this same
 * copyright notice included in any source files or forms of documentation
 * which have stemmed or branched off of the original Software.
 *
 * **
 *
 */

package me.wobblyyyy.intra.ftc2.utils.jrep;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Custom implementation of Java's default List.
 * This is primarily used so items can be added as an array of
 * variable arguments instead of having to execute the add function
 * of the default list several times.
 * The list contained inside of this can still be retried, so
 * should, for some reason, you want to access that list, you
 * can do so with 'list name'.list.
 * TODO add a method of getting list values
 *
 * @param <E>
 * @author Colin Robertson
 */
public class ListWrapper<E> {
    /**
     * The list itself.
     */
    public List<E> list;

    /**
     * Create an entirely empty list without any content.
     */
    public ListWrapper() {
        list = new ArrayList<>();
    }

    /**
     * Create a list based on another, already existing
     * ArrayList of the same type.
     */
    public ListWrapper(ArrayList<E> list) {
        this.list = list;
    }

    public E value;
    public int index;

    /**
     * Add an element (or several elements) to list.
     */
    @SafeVarargs
    public final void add(E... es) {
        list.addAll(Arrays.asList(es));
    }

    /**
     * Prepend something to a list.
     *
     * <p>
     * Java's default list doesn't contain a prepend method,
     * so I added it here. This is (probably) pretty important
     * for setting things up in the right order, especially when
     * the order in which instructions are processed is VERY
     * important.
     * </p>
     */
    public final void prepend(E e) {
        List<E> previous = list;
        list.clear();
        add(e);
        list.addAll(previous);
    }

    public final void forEach(Runnable runnable) {
        index = 0;
        int length = list.size() - 1;
        while (index < length) {
            value = list.get(index);
            runnable.run();
            index++;
        }
    }
}
