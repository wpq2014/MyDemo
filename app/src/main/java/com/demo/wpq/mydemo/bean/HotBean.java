package com.demo.wpq.mydemo.bean;

import java.io.Serializable;
import java.util.List;

/**
 * @desc :
 */
public class HotBean implements Serializable{

    /**
     * msg : 获取成功！
     * data : {"list":[{"id":1,"uid":1,"uuid":"35649955-862e-c216-ca0a-0da7e8e49455","name":"三只松鼠","num":100,"price":"0.01","description":"三只松鼠100g大礼包","standard":"1000*15*20","cover_path":"/uploads/picture/20160926/341ea62f12bc779a9f498e0159cce1d8.jpg","photo_path_1":"/uploads/picture/20160926/341ea62f12bc779a9f498e0159cce1d8.jpg","photo_path_2":"/uploads/picture/20160926/a4f1436144bf14650d247bf7891c045f.jpg","photo_path_3":"","content":"<p>三只松鼠详情<br/><\/p>","click_count":0,"status":1,"is_best":1,"is_new":1,"is_hot":1,"sell_num":0,"createtime":1475824874,"score_num":1}]}
     * code : 1
     */

    private String msg;
    private DataBean data;
    private int code;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    @Override
    public String toString() {
        return "HotBean{" +
                "msg='" + msg + '\'' +
                ", data=" + data +
                ", code=" + code +
                '}';
    }

    public static class DataBean {
        /**
         * id : 1
         * uid : 1
         * uuid : 35649955-862e-c216-ca0a-0da7e8e49455
         * name : 三只松鼠
         * num : 100
         * price : 0.01
         * description : 三只松鼠100g大礼包
         * standard : 1000*15*20
         * cover_path : /uploads/picture/20160926/341ea62f12bc779a9f498e0159cce1d8.jpg
         * photo_path_1 : /uploads/picture/20160926/341ea62f12bc779a9f498e0159cce1d8.jpg
         * photo_path_2 : /uploads/picture/20160926/a4f1436144bf14650d247bf7891c045f.jpg
         * photo_path_3 :
         * content : <p>三只松鼠详情<br/></p>
         * click_count : 0
         * status : 1
         * is_best : 1
         * is_new : 1
         * is_hot : 1
         * sell_num : 0
         * createtime : 1475824874
         * score_num : 1
         */

        private List<ListBean> list;

        public List<ListBean> getList() {
            return list;
        }

        public void setList(List<ListBean> list) {
            this.list = list;
        }

        @Override
        public String toString() {
            return "DataBean{" +
                    "list=" + list +
                    '}';
        }

        public static class ListBean {
            private int id;
            private int uid;
            private String uuid;
            private String name;
            private int num;
            private String price;
            private String description;
            private String standard;
            private String cover_path;
            private String photo_path_1;
            private String photo_path_2;
            private String photo_path_3;
            private String content;
            private int click_count;
            private int status;
            private int is_best;
            private int is_new;
            private int is_hot;
            private int sell_num;
            private int createtime;
            private int score_num;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public int getUid() {
                return uid;
            }

            public void setUid(int uid) {
                this.uid = uid;
            }

            public String getUuid() {
                return uuid;
            }

            public void setUuid(String uuid) {
                this.uuid = uuid;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public int getNum() {
                return num;
            }

            public void setNum(int num) {
                this.num = num;
            }

            public String getPrice() {
                return price;
            }

            public void setPrice(String price) {
                this.price = price;
            }

            public String getDescription() {
                return description;
            }

            public void setDescription(String description) {
                this.description = description;
            }

            public String getStandard() {
                return standard;
            }

            public void setStandard(String standard) {
                this.standard = standard;
            }

            public String getCover_path() {
                return cover_path;
            }

            public void setCover_path(String cover_path) {
                this.cover_path = cover_path;
            }

            public String getPhoto_path_1() {
                return photo_path_1;
            }

            public void setPhoto_path_1(String photo_path_1) {
                this.photo_path_1 = photo_path_1;
            }

            public String getPhoto_path_2() {
                return photo_path_2;
            }

            public void setPhoto_path_2(String photo_path_2) {
                this.photo_path_2 = photo_path_2;
            }

            public String getPhoto_path_3() {
                return photo_path_3;
            }

            public void setPhoto_path_3(String photo_path_3) {
                this.photo_path_3 = photo_path_3;
            }

            public String getContent() {
                return content;
            }

            public void setContent(String content) {
                this.content = content;
            }

            public int getClick_count() {
                return click_count;
            }

            public void setClick_count(int click_count) {
                this.click_count = click_count;
            }

            public int getStatus() {
                return status;
            }

            public void setStatus(int status) {
                this.status = status;
            }

            public int getIs_best() {
                return is_best;
            }

            public void setIs_best(int is_best) {
                this.is_best = is_best;
            }

            public int getIs_new() {
                return is_new;
            }

            public void setIs_new(int is_new) {
                this.is_new = is_new;
            }

            public int getIs_hot() {
                return is_hot;
            }

            public void setIs_hot(int is_hot) {
                this.is_hot = is_hot;
            }

            public int getSell_num() {
                return sell_num;
            }

            public void setSell_num(int sell_num) {
                this.sell_num = sell_num;
            }

            public int getCreatetime() {
                return createtime;
            }

            public void setCreatetime(int createtime) {
                this.createtime = createtime;
            }

            public int getScore_num() {
                return score_num;
            }

            public void setScore_num(int score_num) {
                this.score_num = score_num;
            }

            @Override
            public String toString() {
                return "ListBean{" +
                        "id=" + id +
                        ", uid=" + uid +
                        ", uuid='" + uuid + '\'' +
                        ", name='" + name + '\'' +
                        ", num=" + num +
                        ", price='" + price + '\'' +
                        ", description='" + description + '\'' +
                        ", standard='" + standard + '\'' +
                        ", cover_path='" + cover_path + '\'' +
                        ", photo_path_1='" + photo_path_1 + '\'' +
                        ", photo_path_2='" + photo_path_2 + '\'' +
                        ", photo_path_3='" + photo_path_3 + '\'' +
                        ", content='" + content + '\'' +
                        ", click_count=" + click_count +
                        ", status=" + status +
                        ", is_best=" + is_best +
                        ", is_new=" + is_new +
                        ", is_hot=" + is_hot +
                        ", sell_num=" + sell_num +
                        ", createtime=" + createtime +
                        ", score_num=" + score_num +
                        '}';
            }
        }
    }
}
