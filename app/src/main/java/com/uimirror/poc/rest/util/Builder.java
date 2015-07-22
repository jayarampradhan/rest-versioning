package com.uimirror.poc.rest.util;

/**
 * Builder for the constructor to builder pattern.
 * Strategic pattern implementation, every class which needs to provide the builder
 * pattern needs to implement this.
 *
 * @author Jay
 */
public interface Builder<T> {
    public T build();
}
