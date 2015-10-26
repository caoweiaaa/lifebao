package com.chs.lifebao.bean;

import java.util.List;

/**
 * Created by llbt on 2015/10/16.
 */
public class NewsBean {

    /**
     * template : manual
     * hasCover : false
     * hasHead : 1
     * skipID : 00AJ0003|573541
     * replyCount : 2920
     * alias : Entertainment
     * hasImg : 1
     * digest :
     * hasIcon : true
     * skipType : photoset
     * cid : C1348648351901
     * docid : 9IG74V5H00963VRO_B5D1S8HTzongssupdateDoc
     * title : 黄晓明Baby唯美婚纱照曝光 浪漫亲吻
     * hasAD : 1
     * order : 1
     * imgextra : [{"imgsrc":"http://img3.cache.netease.com/3g/2015/10/8/20151008082820eb614.jpg"},{"imgsrc":"http://img3.cache.netease.com/3g/2015/10/8/20151008082822731ea.jpg"}]
     * priority : 250
     * lmodify : 2015-10-08 08:26:53
     * ename : yule
     * tname : 娱乐
     * imgsrc : http://img4.cache.netease.com/3g/2015/10/8/2015100809345374a95.jpg
     * ads : [{"title":"柳岩穿吊带裙性感亮相人气高","tag":"photoset","imgsrc":"http://img2.cache.netease.com/3g/2015/10/8/2015100810491666afa.jpg","subtitle":"","url":"00AJ0003|573525"}]
     * photosetID : 00AJ0003|573541
     * ptime : 2015-10-08 08:26:53
     */

    private String template;
    private boolean hasCover;
    private int hasHead;
    private String skipID;
    private int replyCount;
    private String alias;
    private int hasImg;
    private String digest;
    private boolean hasIcon;
    private String skipType;
    private String cid;
    private String docid;
    private String title;
    private int hasAD;
    private int order;
    private int priority;
    private String lmodify;
    private String ename;
    private String tname;
    private String imgsrc;
    private String photosetID;
    private String ptime;
    private List<ImgextraEntity> imgextra;
    private List<AdsEntity> ads;

    public void setTemplate(String template) {
        this.template = template;
    }

    public void setHasCover(boolean hasCover) {
        this.hasCover = hasCover;
    }

    public void setHasHead(int hasHead) {
        this.hasHead = hasHead;
    }

    public void setSkipID(String skipID) {
        this.skipID = skipID;
    }

    public void setReplyCount(int replyCount) {
        this.replyCount = replyCount;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public void setHasImg(int hasImg) {
        this.hasImg = hasImg;
    }

    public void setDigest(String digest) {
        this.digest = digest;
    }

    public void setHasIcon(boolean hasIcon) {
        this.hasIcon = hasIcon;
    }

    public void setSkipType(String skipType) {
        this.skipType = skipType;
    }

    public void setCid(String cid) {
        this.cid = cid;
    }

    public void setDocid(String docid) {
        this.docid = docid;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setHasAD(int hasAD) {
        this.hasAD = hasAD;
    }

    public void setOrder(int order) {
        this.order = order;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public void setLmodify(String lmodify) {
        this.lmodify = lmodify;
    }

    public void setEname(String ename) {
        this.ename = ename;
    }

    public void setTname(String tname) {
        this.tname = tname;
    }

    public void setImgsrc(String imgsrc) {
        this.imgsrc = imgsrc;
    }

    public void setPhotosetID(String photosetID) {
        this.photosetID = photosetID;
    }

    public void setPtime(String ptime) {
        this.ptime = ptime;
    }

    public void setImgextra(List<ImgextraEntity> imgextra) {
        this.imgextra = imgextra;
    }

    public void setAds(List<AdsEntity> ads) {
        this.ads = ads;
    }

    public String getTemplate() {
        return template;
    }

    public boolean getHasCover() {
        return hasCover;
    }

    public int getHasHead() {
        return hasHead;
    }

    public String getSkipID() {
        return skipID;
    }

    public int getReplyCount() {
        return replyCount;
    }

    public String getAlias() {
        return alias;
    }

    public int getHasImg() {
        return hasImg;
    }

    public String getDigest() {
        return digest;
    }

    public boolean getHasIcon() {
        return hasIcon;
    }

    public String getSkipType() {
        return skipType;
    }

    public String getCid() {
        return cid;
    }

    public String getDocid() {
        return docid;
    }

    public String getTitle() {
        return title;
    }

    public int getHasAD() {
        return hasAD;
    }

    public int getOrder() {
        return order;
    }

    public int getPriority() {
        return priority;
    }

    public String getLmodify() {
        return lmodify;
    }

    public String getEname() {
        return ename;
    }

    public String getTname() {
        return tname;
    }

    public String getImgsrc() {
        return imgsrc;
    }

    public String getPhotosetID() {
        return photosetID;
    }

    public String getPtime() {
        return ptime;
    }

    public List<ImgextraEntity> getImgextra() {
        return imgextra;
    }

    public List<AdsEntity> getAds() {
        return ads;
    }

    public static class ImgextraEntity {
        /**
         * imgsrc : http://img3.cache.netease.com/3g/2015/10/8/20151008082820eb614.jpg
         */

        private String imgsrc;

        public void setImgsrc(String imgsrc) {
            this.imgsrc = imgsrc;
        }

        public String getImgsrc() {
            return imgsrc;
        }
    }

    public static class AdsEntity {
        /**
         * title : 柳岩穿吊带裙性感亮相人气高
         * tag : photoset
         * imgsrc : http://img2.cache.netease.com/3g/2015/10/8/2015100810491666afa.jpg
         * subtitle :
         * url : 00AJ0003|573525
         */

        private String title;
        private String tag;
        private String imgsrc;
        private String subtitle;
        private String url;

        public void setTitle(String title) {
            this.title = title;
        }

        public void setTag(String tag) {
            this.tag = tag;
        }

        public void setImgsrc(String imgsrc) {
            this.imgsrc = imgsrc;
        }

        public void setSubtitle(String subtitle) {
            this.subtitle = subtitle;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public String getTitle() {
            return title;
        }

        public String getTag() {
            return tag;
        }

        public String getImgsrc() {
            return imgsrc;
        }

        public String getSubtitle() {
            return subtitle;
        }

        public String getUrl() {
            return url;
        }
    }
}
