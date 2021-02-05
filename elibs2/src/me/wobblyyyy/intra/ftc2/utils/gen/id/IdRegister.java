/*
 * **
 *
 * Copyright (c) 2020
 * Copyright last updated on 6/10/20, 10:58 PM
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

package me.wobblyyyy.intra.ftc2.utils.gen.id;

/**
 * Static IdRegister which holds the current id thingy.
 *
 * @author Colin Robertson
 */
public class IdRegister {
    /**
     * Static integer which keeps a running tally of the highest
     * Id integer that has been used yet.
     */
    private static int nextId = 0;

    /**
     * Increment the int nextId and return the value it was prior to it's incrementation.
     *
     * @return the next id to be used.
     */
    public static Id getNextId() {
        return new Id(nextId++);
    }
}
