package gitlet;

// TODO: any imports you need here

import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Represents a gitlet commit object.
 *  TODO: It's a good idea to give a description here of what else this Class
 *  does at a high level.
 *  提供对一个指令的基本操作，以及HEAD、NEW的指针
 * @author TODO
 */
public class Commit implements Serializable {
    /**
     * TODO: add instance variables here.
     * <p>
     * List all instance variables of the Commit class here with a useful
     * comment above them describing what that variable represents and how that
     * variable is used. We've provided one example for `message`.
     */
    /** commits的总文件夹 */
    private static final File COMMITS_DIR = Repository.COMMITS_DIR;
    /** staged的总文件夹 */
    private static final File STAGED_DIR=Repository.STAGED_DIR;
    /** 提交链的HEAD指针（当前指针） */
    public static Commit HEAD;
    /** 提交链的最新提交指针 */
    public static Commit NEW;
    /** 当前commit的父提交 */
    private final String parent;
    /** The message of this Commit. */
    private final String message;
    /** 提交的节点的时间戳 */
    private final String timeScale;
    /** 每个节点的所有文件的引用 */
    private final Map<String,String> blobs;

    /* TODO: fill in the rest of this class. */
    public Commit(String message, String timeScale,String parent){
        this.message=message;
        this.timeScale = timeScale;
        this.parent=parent;
        if(this.parent!=null)this.blobs=this.parent().blobs;
        else this.blobs=new HashMap<>();
    }
    /**
     * 新建一个序列化自身后的文件到commits文件夹，用自身的sha1码命名
     */
    public void submitCommit() {
        refreshBlobs();
        String sha1=Utils.sha1(this);
        File commit=Utils.join(COMMITS_DIR,sha1);
        Utils.writeContents(commit,this);
        try {
            commit.createNewFile();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        HEAD=this;//TODO:修改
        NEW=this;
    }
    /** 依据staged里的文件更新当前commit的blobs引用，已存在的更新，不存在的添加 */
    private void refreshBlobs(){
        List<String> keys=Utils.plainFilenamesIn(STAGED_DIR);//staged文件夹里add的文件
        for (String key:keys) {
            File keyFile=Utils.join(STAGED_DIR,key);
            String sha1=Utils.sha1(keyFile);
            if(blobs.containsKey(key))blobs.replace(key,sha1);//如果存在则更新，反之添加
            else blobs.put(key,sha1);
        }
    }

    public Commit parent(){
        File commit=Utils.join(COMMITS_DIR,parent);
        return Utils.readObject(commit,Commit.class);
    }
    public String message(){
        return message;
    }
    public String timeScale(){
        return timeScale;
    }
    public Map<String,String> blobs(){
        return blobs;
    }
}
