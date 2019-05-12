package com.yzy.heatmap.Utils;

import java.util.Map;

public class Result extends XMap {
    private static final String ERROR_CODE = "errorCode";
    private static final String ERROR_MESSAGE = "errorMessage";
    private static final String SYNTAX_ERROR = "syntax error";

    public Result() {
        this.put(ERROR_CODE, 0);
    }

    public Result(Map m) {
        super(m);
        if (!this.containsKey(ERROR_CODE)) {
            this.put(ERROR_CODE, 0);
        }
    }

    public Result(int errorCode, String errorMessage) {
        super();
        this.put(ERROR_CODE, errorCode);
        this.put(ERROR_MESSAGE, errorMessage);
    }

    public int getErrorCode() {
        return this.getInt(ERROR_CODE);
    }

    public void setErrorCode(int errorCode) {
        this.put(ERROR_CODE, errorCode);
    }

    public String getErrorMessage() {
        return this.getString(ERROR_MESSAGE);
    }

    public void setErrorMessage(String errorMessage) {
        this.put(ERROR_MESSAGE, errorMessage);
    }

    @SuppressWarnings({"rawtypes", "unchecked"})
    public XMap getXMap() {
        XMap xmap = new XMap();
        xmap.putAll(this);
        xmap.remove(ERROR_CODE);
        xmap.remove(ERROR_MESSAGE);
        return xmap;
    }
}

