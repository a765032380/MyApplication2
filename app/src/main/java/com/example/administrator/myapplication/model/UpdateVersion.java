package com.example.administrator.myapplication.model;

/**
 * Created by Administrator on 2017/6/24 0024.
 */

public class UpdateVersion {

    /**
     * code : 1000
     * msg : 获取成功
     * obj : {"verSn":"2.0","addTime":{"date":5,"day":3,"hours":14,"minutes":14,"month":6,"nanos":0,"seconds":49,"time":1499235289000,"timezoneOffset":-480,"year":117},"verDescript":"<p>最新版<\/p>","ver_force":0,"verNo":2,"verUrl":"http://47.92.106.249:8087/upload/Wisdom_community.apk"}
     */

    private String code;
    private String msg;
    private ObjBean obj;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public ObjBean getObj() {
        return obj;
    }

    public void setObj(ObjBean obj) {
        this.obj = obj;
    }

    public static class ObjBean {
        /**
         * verSn : 2.0
         * addTime : {"date":5,"day":3,"hours":14,"minutes":14,"month":6,"nanos":0,"seconds":49,"time":1499235289000,"timezoneOffset":-480,"year":117}
         * verDescript : <p>最新版</p>
         * ver_force : 0
         * verNo : 2
         * verUrl : http://47.92.106.249:8087/upload/Wisdom_community.apk
         */

        private String verSn;
        private AddTimeBean addTime;
        private String verDescript;
        private int ver_force;
        private int verNo;
        private String verUrl;

        public String getVerSn() {
            return verSn;
        }

        public void setVerSn(String verSn) {
            this.verSn = verSn;
        }

        public AddTimeBean getAddTime() {
            return addTime;
        }

        public void setAddTime(AddTimeBean addTime) {
            this.addTime = addTime;
        }

        public String getVerDescript() {
            return verDescript;
        }

        public void setVerDescript(String verDescript) {
            this.verDescript = verDescript;
        }

        public int getVer_force() {
            return ver_force;
        }

        public void setVer_force(int ver_force) {
            this.ver_force = ver_force;
        }

        public int getVerNo() {
            return verNo;
        }

        public void setVerNo(int verNo) {
            this.verNo = verNo;
        }

        public String getVerUrl() {
            return verUrl;
        }

        public void setVerUrl(String verUrl) {
            this.verUrl = verUrl;
        }

        public static class AddTimeBean {
            /**
             * date : 5
             * day : 3
             * hours : 14
             * minutes : 14
             * month : 6
             * nanos : 0
             * seconds : 49
             * time : 1499235289000
             * timezoneOffset : -480
             * year : 117
             */

            private int date;
            private int day;
            private int hours;
            private int minutes;
            private int month;
            private int nanos;
            private int seconds;
            private long time;
            private int timezoneOffset;
            private int year;

            public int getDate() {
                return date;
            }

            public void setDate(int date) {
                this.date = date;
            }

            public int getDay() {
                return day;
            }

            public void setDay(int day) {
                this.day = day;
            }

            public int getHours() {
                return hours;
            }

            public void setHours(int hours) {
                this.hours = hours;
            }

            public int getMinutes() {
                return minutes;
            }

            public void setMinutes(int minutes) {
                this.minutes = minutes;
            }

            public int getMonth() {
                return month;
            }

            public void setMonth(int month) {
                this.month = month;
            }

            public int getNanos() {
                return nanos;
            }

            public void setNanos(int nanos) {
                this.nanos = nanos;
            }

            public int getSeconds() {
                return seconds;
            }

            public void setSeconds(int seconds) {
                this.seconds = seconds;
            }

            public long getTime() {
                return time;
            }

            public void setTime(long time) {
                this.time = time;
            }

            public int getTimezoneOffset() {
                return timezoneOffset;
            }

            public void setTimezoneOffset(int timezoneOffset) {
                this.timezoneOffset = timezoneOffset;
            }

            public int getYear() {
                return year;
            }

            public void setYear(int year) {
                this.year = year;
            }
        }
    }
}
