package hui.a7ifun.com.a7ifun.okhttp.bean;

import java.util.List;

/**
 * Created by 7yan on 2017/1/8.
 */

public class MovieInfo
{
    /**
     * total : 4220
     * rows : [{"id":"4079","spid":"电影","sname":"西游记之孙悟空三打白骨精","ssname":"西游记之大闹天宫续集/西游记之三打白骨精/三打白骨精/The Monkey King 2","sdbf":"5.9","surl":"xyjzswksdbg_319.html","simg":"uploads/2016-01-14/2016011403010689344.jpg","dhit":"3234","vhit":"10693","sforbid":"0","szd":"1","shot":"0","sshow":"1","stime":"2016-01-15 02:14:18","sutime":"2016-04-10 00:13:56","fsize":"0"},{"id":"4211","spid":"电影","sname":"火锅英雄","ssname":"火锅/Chongqing Hot Pot","sdbf":"7.5","surl":"hgyx_494.html","simg":"uploads/2016-04-06/2016040602045510442.jpg","dhit":"10","vhit":"34","sforbid":"0","szd":"1","shot":"0","sshow":"1","stime":"2016-04-07 01:27:08","sutime":"2016-04-09 23:15:04","fsize":"0"},{"id":"4204","spid":"电影","sname":"床下有人3","ssname":"","sdbf":"2.8","surl":"cxyr3_730.html","simg":"uploads/2016-03-21/2016040806044666245.jpg","dhit":"3","vhit":"21","sforbid":"0","szd":"1","shot":"0","sshow":"1","stime":"2016-03-22 06:25:18","sutime":"2016-04-09 14:33:55","fsize":"0"},{"id":"4222","spid":"电影","sname":"渎职","ssname":"欺诈之外/Beyond Deceit","sdbf":"4.7","surl":"dz_985.html","simg":"uploads/2016-04-08/2016040806043837891.jpg","dhit":"1","vhit":"1","sforbid":"0","szd":"1","shot":"0","sshow":"1","stime":"2016-04-09 05:28:55","sutime":"2016-04-09 05:28:55","fsize":"0"},{"id":"4221","spid":"电影","sname":"倒数追击","ssname":"","sdbf":"5.1","surl":"dszj_411.html","simg":"uploads/2016-04-08/2016040806040081158.jpg","dhit":"4","vhit":"8","sforbid":"0","szd":"1","shot":"0","sshow":"1","stime":"2016-04-09 05:28:25","sutime":"2016-04-09 05:28:25","fsize":"0"},{"id":"4170","spid":"电影","sname":"卧虎藏龙：青冥宝剑","ssname":"卧虎藏龙2：青冥宝剑/卧虎藏龙Ⅱ青冥宝剑/铁骑银瓶/Crouching Tiger, Hidden Dragon 2/Crouching Tiger Hidden Dragon II: The Gre","sdbf":"5","surl":"whclqmbj_501.html","simg":"uploads/2016-02-19/2016021904020259701.jpg","dhit":"386","vhit":"829","sforbid":"0","szd":"1","shot":"0","sshow":"1","stime":"2016-02-20 03:19:23","sutime":"2016-04-09 00:12:52","fsize":"0"},{"id":"4218","spid":"电影","sname":"我的特工爷爷","ssname":"老卫兵/My Beloved Bodyguard","sdbf":"5.5","surl":"wdtgyy_77.html","simg":"uploads/2016-04-06/2016040602044239385.jpg","dhit":"63","vhit":"218","sforbid":"0","szd":"1","shot":"0","sshow":"1","stime":"2016-04-07 01:43:02","sutime":"2016-04-07 01:43:02","fsize":"0"},{"id":"4217","spid":"电影","sname":"蝙蝠侠大战超人：正义黎明","ssname":"蝙蝠侠大战超人/蝙蝠侠对超人：正义曙光(港/台)/蝙蝠侠与超人：正义黎明/Batman vs. Superman","sdbf":"6.7","surl":"bfxdzcr_648.html","simg":"uploads/2016-04-06/2016040602045365201.jpg","dhit":"87","vhit":"210","sforbid":"0","szd":"1","shot":"0","sshow":"1","stime":"2016-04-07 01:42:26","sutime":"2016-04-07 01:42:26","fsize":"0"},{"id":"4216","spid":"电影","sname":"消失爱人","ssname":"消失的爱人(港)/看不见的秘密/The Secret","sdbf":"4.7","surl":"xsar_769.html","simg":"uploads/2016-04-06/2016040602040385319.jpg","dhit":"18","vhit":"48","sforbid":"0","szd":"1","shot":"0","sshow":"1","stime":"2016-04-07 01:40:27","sutime":"2016-04-07 01:40:27","fsize":"0"},{"id":"4215","spid":"电影","sname":"红衣小女孩","ssname":"The Tag-Along","sdbf":"5.6","surl":"hyxnh_736.html","simg":"uploads/2016-04-06/2016040602041046015.jpg","dhit":"4","vhit":"24","sforbid":"0","szd":"1","shot":"0","sshow":"1","stime":"2016-04-07 01:35:47","sutime":"2016-04-07 01:35:47","fsize":"0"},{"id":"4178","spid":"电影","sname":"死侍","ssname":"死侍：不死现身(港)/恶棍英雄：死侍(台)/X战警：死侍","sdbf":"8.2","surl":"ss_17.html","simg":"uploads/2016-02-22/2016022206024519068.jpg","dhit":"519","vhit":"1484","sforbid":"0","szd":"1","shot":"0","sshow":"1","stime":"2016-02-23 05:08:22","sutime":"2016-03-29 12:29:31","fsize":"0"},{"id":"4035","spid":"电影","sname":"星球大战：原力觉醒","ssname":"星球大战7：原力觉醒/Star Wars: Episode VII","sdbf":"7.9","surl":"xqdzyljx_866.html","simg":"uploads/2015-12-25/2015122504122989449.jpg","dhit":"703","vhit":"1890","sforbid":"0","szd":"1","shot":"1","sshow":"1","stime":"2015-12-26 03:10:03","sutime":"2016-03-24 12:21:46","fsize":"0"}]
     */

    private String total;
    private List<RowsBean> rows;

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public List<RowsBean> getRows() {
        return rows;
    }

    public void setRows(List<RowsBean> rows) {
        this.rows = rows;
    }

    public static class RowsBean {
        /**
         * id : 4079
         * spid : 电影
         * sname : 西游记之孙悟空三打白骨精
         * ssname : 西游记之大闹天宫续集/西游记之三打白骨精/三打白骨精/The Monkey King 2
         * sdbf : 5.9
         * surl : xyjzswksdbg_319.html
         * simg : uploads/2016-01-14/2016011403010689344.jpg
         * dhit : 3234
         * vhit : 10693
         * sforbid : 0
         * szd : 1
         * shot : 0
         * sshow : 1
         * stime : 2016-01-15 02:14:18
         * sutime : 2016-04-10 00:13:56
         * fsize : 0
         */

        private String id;
        private String spid;
        private String sname;
        private String ssname;
        private String sdbf;
        private String surl;
        private String simg;
        private String dhit;
        private String vhit;
        private String sforbid;
        private String szd;
        private String shot;
        private String sshow;
        private String stime;
        private String sutime;
        private String fsize;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getSpid() {
            return spid;
        }

        public void setSpid(String spid) {
            this.spid = spid;
        }

        public String getSname() {
            return sname;
        }

        public void setSname(String sname) {
            this.sname = sname;
        }

        public String getSsname() {
            return ssname;
        }

        public void setSsname(String ssname) {
            this.ssname = ssname;
        }

        public String getSdbf() {
            return sdbf;
        }

        public void setSdbf(String sdbf) {
            this.sdbf = sdbf;
        }

        public String getSurl() {
            return surl;
        }

        public void setSurl(String surl) {
            this.surl = surl;
        }

        public String getSimg() {
            return simg;
        }

        public void setSimg(String simg) {
            this.simg = simg;
        }

        public String getDhit() {
            return dhit;
        }

        public void setDhit(String dhit) {
            this.dhit = dhit;
        }

        public String getVhit() {
            return vhit;
        }

        public void setVhit(String vhit) {
            this.vhit = vhit;
        }

        public String getSforbid() {
            return sforbid;
        }

        public void setSforbid(String sforbid) {
            this.sforbid = sforbid;
        }

        public String getSzd() {
            return szd;
        }

        public void setSzd(String szd) {
            this.szd = szd;
        }

        public String getShot() {
            return shot;
        }

        public void setShot(String shot) {
            this.shot = shot;
        }

        public String getSshow() {
            return sshow;
        }

        public void setSshow(String sshow) {
            this.sshow = sshow;
        }

        public String getStime() {
            return stime;
        }

        public void setStime(String stime) {
            this.stime = stime;
        }

        public String getSutime() {
            return sutime;
        }

        public void setSutime(String sutime) {
            this.sutime = sutime;
        }

        public String getFsize() {
            return fsize;
        }

        public void setFsize(String fsize) {
            this.fsize = fsize;
        }
    }
}
