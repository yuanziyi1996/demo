package com.yzy.heatmap.Utils;

import java.sql.Timestamp;
import java.util.Date;

public class Convert {
    private static Date minDateValue = new Date(0L);

    public Convert() {
    }

    public static String toString(Object o) {
        if (o == null) {
            return "";
        } else {
            return o instanceof String ? (String)o : o.toString();
        }
    }

    public static int toInt(Object o) {
        if (null == o) {
            return 0;
        } else if (o instanceof Integer) {
            return ((Integer)o).intValue();
        } else if (o instanceof Number) {
            return ((Number)o).intValue();
        } else {
            String s = o.toString().trim();
            if (s.equals("")) {
                return 0;
            } else {
                return !s.contains(".") ? parseInt(s) : (int)parseDouble(s);
            }
        }
    }

    public static long toLong(Object o) {
        if (null == o) {
            return 0L;
        } else if (o instanceof Long) {
            return ((Long)o).longValue();
        } else if (o instanceof Number) {
            return ((Number)o).longValue();
        } else {
            String s = o.toString().trim();
            if (s.equals("")) {
                return 0L;
            } else {
                return !s.contains(".") ? parseLong(s) : (long)parseDouble(s);
            }
        }
    }

    public static float toFloat(Object o) {
        if (o == null) {
            return 0.0F;
        } else if (o instanceof Float) {
            return ((Float)o).floatValue();
        } else {
            try {
                return Float.parseFloat(o.toString());
            } catch (Exception var2) {
                return 0.0F;
            }
        }
    }

    public static Date toDate(Object o) {
        if (o == null) {
            return minDateValue;
        } else if (o instanceof Date) {
            return (Date)o;
        } else {
            try {
                return Timestamp.valueOf(o.toString());
            } catch (Exception var2) {
                return minDateValue;
            }
        }
    }

    public static boolean toBool(Object o) {
        if (o == null) {
            return false;
        } else {
            return o instanceof Boolean ? ((Boolean)o).booleanValue() : o.toString().trim().equalsIgnoreCase("true");
        }
    }

    private static int parseInt(String s) {
        try {
            return Integer.parseInt(s);
        } catch (Exception var2) {
            return 0;
        }
    }

    private static long parseLong(String s) {
        try {
            return Long.parseLong(s);
        } catch (Exception var2) {
            return 0L;
        }
    }

    private static double parseDouble(String s) {
        try {
            return Double.parseDouble(s);
        } catch (Exception var2) {
            return 0.0D;
        }
    }
}
