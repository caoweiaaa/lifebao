package com.chs.lifebao.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by llbt on 2015/9/28.
 */
public class PicBean implements Serializable {
    private List<PicBeanList> list;

    public List<PicBeanList> getList() {
        return list;
    }

    public void setList(List<PicBeanList> list) {
        this.list = list;
    }

    public static class PicBeanList{
        private String id;
        private String title;
        private String long_title;
        private String source;
        private String link;
        private String pic;
        private String kpic;
        private String bpic;
        private String intro;
        private String pubDate;
        private String comments;
        private Pics pics;
        private String feedShowStyle;
        private String category;
        private String comment;
        private Comment comment_count_info;
        private int type;

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getLong_title() {
            return long_title;
        }

        public void setLong_title(String long_title) {
            this.long_title = long_title;
        }

        public String getSource() {
            return source;
        }

        public void setSource(String source) {
            this.source = source;
        }

        public String getLink() {
            return link;
        }

        public void setLink(String link) {
            this.link = link;
        }

        public String getPic() {
            return pic;
        }

        public void setPic(String pic) {
            this.pic = pic;
        }

        public String getKpic() {
            return kpic;
        }

        public void setKpic(String kpic) {
            this.kpic = kpic;
        }

        public String getBpic() {
            return bpic;
        }

        public void setBpic(String bpic) {
            this.bpic = bpic;
        }

        public String getIntro() {
            return intro;
        }

        public void setIntro(String intro) {
            this.intro = intro;
        }

        public String getPubDate() {
            return pubDate;
        }

        public void setPubDate(String pubDate) {
            this.pubDate = pubDate;
        }

        public String getComments() {
            return comments;
        }

        public void setComments(String comments) {
            this.comments = comments;
        }

        public Pics getPics() {
            return pics;
        }

        public void setPics(Pics pics) {
            this.pics = pics;
        }

        public String getFeedShowStyle() {
            return feedShowStyle;
        }

        public void setFeedShowStyle(String feedShowStyle) {
            this.feedShowStyle = feedShowStyle;
        }

        public String getCategory() {
            return category;
        }

        public void setCategory(String category) {
            this.category = category;
        }

        public String getComment() {
            return comment;
        }

        public void setComment(String comment) {
            this.comment = comment;
        }

        public Comment getComment_count_info() {
            return comment_count_info;
        }

        public void setComment_count_info(Comment comment_count_info) {
            this.comment_count_info = comment_count_info;
        }

        public static class Pics{
            private String total;
            private String picTemplate;
            private java.util.List<PicList> list;

            public String getTotal() {
                return total;
            }

            public void setTotal(String total) {
                this.total = total;
            }

            public String getPicTemplate() {
                return picTemplate;
            }

            public void setPicTemplate(String picTemplate) {
                this.picTemplate = picTemplate;
            }

            public List<PicList> getList() {
                return list;
            }

            public void setList(List<PicList> list) {
                this.list = list;
            }

            public static class PicList{
                private String pic;
                private String alt;
                private String kpic;

                public String getPic() {
                    return pic;
                }

                public void setPic(String pic) {
                    this.pic = pic;
                }

                public String getAlt() {
                    return alt;
                }

                public void setAlt(String alt) {
                    this.alt = alt;
                }

                public String getKpic() {
                    return kpic;
                }

                public void setKpic(String kpic) {
                    this.kpic = kpic;
                }
            }
        }
        public static class Comment{
            private String qreply;
            private String total;
            private String show;
            private String comment_status;

            public String getQreply() {
                return qreply;
            }

            public void setQreply(String qreply) {
                this.qreply = qreply;
            }

            public String getTotal() {
                return total;
            }

            public void setTotal(String total) {
                this.total = total;
            }

            public String getShow() {
                return show;
            }

            public void setShow(String show) {
                this.show = show;
            }

            public String getComment_status() {
                return comment_status;
            }

            public void setComment_status(String comment_status) {
                this.comment_status = comment_status;
            }
        }
    }
}
