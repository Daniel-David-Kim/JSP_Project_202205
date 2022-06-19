package section01;

import java.sql.*;

public class LectureBean {
	private int scat_num; // 1
	private String title; // 2
	private String content; // 3
	private String vid_title; // 4
	private String vid_url; // 5
	private String code; // 6
	private String summary; // 7
	private Blob img; // 8
	private Date writeDate; // 9
	private String vid_title2; // 10
	private String vid_url2; // 11
	private String code2; // 12
	private String summary2; // 13
	private Blob img2; // 14
	
	public int getScat_num() {return scat_num;}
	public void setScat_num(int scat_num) {this.scat_num = scat_num;}
	public String getTitle() {return title;}
	public void setTitle(String title) {this.title = title;}
	public String getContent() {return content;}
	public void setContent(String content) {this.content = content;}
	public String getVid_title() {return vid_title;}
	public void setVid_title(String vid_title) {this.vid_title = vid_title;}
	public String getVid_url() {return vid_url;}
	public void setVid_url(String vid_url) {this.vid_url = vid_url;}
	public String getCode() {return code;}
	public void setCode(String code) {this.code = code;}
	public String getSummary() {return summary;}
	public void setSummary(String summary) {this.summary = summary;}
	public Blob getImg() {return img;}
	public void setImg(Blob img) {this.img = img;}
	public Date getWriteDate() {return writeDate;}
	public void setWriteDate(Date writeDate) {this.writeDate = writeDate;}
	public String getVid_title2() {return vid_title2;}
	public void setVid_title2(String vid_title2) {this.vid_title2 = vid_title2;}
	public String getVid_url2() {return vid_url2;}
	public void setVid_url2(String vid_url2) {this.vid_url2 = vid_url2;}
	public String getCode2() {return code2;}
	public void setCode2(String code2) {this.code2 = code2;}
	public String getSummary2() {return summary2;}
	public void setSummary2(String summary2) {this.summary2 = summary2;}
	public Blob getImg2() {return img2;}
	public void setImg2(Blob img2) {this.img2 = img2;}
	
	public LectureBean(int scat_num, String title, String content, String vid_title, String vid_url, String code, String summary, Blob img, Date writeDate, String vid_title2, String vid_url2, String code2, String summary2, Blob img2) {
		this.scat_num = scat_num; this.title = title; this.content = content; this.vid_title = vid_title; this.vid_url = vid_url; this.code = code;
		this.summary = summary; this.img = img; this.writeDate = writeDate; this.vid_title2 = vid_title2; this.vid_url2 = vid_url2; this.code2 = code2; this.summary2 = summary2; this.img2 = img2;
	}
	
}
