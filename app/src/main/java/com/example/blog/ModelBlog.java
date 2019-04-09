package com.example.blog;

public class ModelBlog {
    private String judul;
    private String isi;
    private  String idPembuat;
    private  String blogId;

    public String getJudul() {
        return judul;
    }

    public String getIsi() {
        return isi;
    }

    public  String getIdPembuat(){return  idPembuat;}

    public  String getBlogId(){return  blogId;}



    public ModelBlog(String blogId, String idPembuat, String judul, String isi ) {
        this.blogId = blogId;
        this.idPembuat = idPembuat;
        this.judul = judul;
        this.isi = isi;

    }

}
