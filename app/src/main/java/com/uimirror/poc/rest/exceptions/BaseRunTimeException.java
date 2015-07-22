package com.uimirror.poc.rest.exceptions;

/**
 * Created by jpradhan on 7/20/15.
 */
public class BaseRunTimeException extends RuntimeException{

    private static final long serialVersionUID = 7025206296958737529L;
    private int errorCode;
    // Eagerly load the NestedExceptionUtils class to avoid classloader deadlock
    // issues on OSGi when calling getMessage().
    static{
        ExceptionUtil.class.getName();
    }

    public BaseRunTimeException(int errorCode) {
        this.errorCode = errorCode;
    }
    public BaseRunTimeException(int errorCode, String msg) {
        super(msg);
        this.errorCode = errorCode;
    }
    public BaseRunTimeException(int errorCode, String msg, Throwable cause){
        super(msg, cause);
        this.errorCode = errorCode;
    }
    public BaseRunTimeException(String msg) {
        super(msg);
    }

    public BaseRunTimeException(String msg, Throwable cause){
        super(msg, cause);
    }

    @Override
    public String getMessage(){
        return ExceptionUtil.buildMessage(super.getMessage(), getCause());
    }

    /**
     * Retrieve the innermost cause of this exception, if any.
     * @return the innermost exception, or {@code null} if none
     * @since 1.0
     */
    public Throwable getRootCause() {
        Throwable rootCause = null;
        Throwable cause = getCause();
        while (cause != null && cause != rootCause) {
            rootCause = cause;
            cause = cause.getCause();
        }
        return rootCause;
    }

    /**
     * Retrieve the most specific cause of this exception, that is,
     * either the innermost cause (root cause) or this exception itself.
     * <p>Differs from {@link #getRootCause()} in that it falls back
     * to the present exception if there is no root cause.
     * @return the most specific cause (never {@code null})
     * @since 1.0
     */
    public Throwable getMostSpecificCause() {
        Throwable rootCause = getRootCause();
        return (rootCause != null ? rootCause : this);
    }

    /**
     * Check whether this exception contains an exception of the given type:
     * either it is of the given class itself or it contains a nested cause
     * of the given type.
     * @param exType the exception type to look for
     * @return whether there is a nested exception of the specified type
     */
    public boolean contains(Class<?> exType) {
        if (exType == null) {
            return false;
        }
        if (exType.isInstance(this)) {
            return true;
        }
        Throwable cause = getCause();
        if (cause == this) {
            return false;
        }
        if (cause instanceof BaseRunTimeException) {
            return ((BaseRunTimeException) cause).contains(exType);
        }
        else {
            while (cause != null) {
                if (exType.isInstance(cause)) {
                    return true;
                }
                if (cause.getCause() == cause) {
                    break;
                }
                cause = cause.getCause();
            }
            return false;
        }
    }


    /**
     * @return the error code for this exception if present else 0
     */
    public int getErrorCode() {
        return errorCode;
    }

}
