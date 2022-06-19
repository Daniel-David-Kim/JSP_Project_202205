create database yndcoding;
use yndcoding;

create table members(
	m_id varchar(30) not null,
    m_pw varchar(50) not null,
    m_name varchar(24) not null,
    m_findq text not null,
    m_finda text not null,
    m_contact varchar(30) not null,
    m_profile mediumblob null,
    m_class int not null default 2,
    primary key(m_id)
);
insert into members values('admin1', '01234', '운영자', 'a1.나는 누구일까?', '운영자', '010-1000-1111', null, 0);

insert into members values('teacher1', '1234', '생활코딩', 't1.나는 누구일까?', '선생님1', '010-1100-1111', null, 1);
insert into members values('teacher2', '2345', '나도코딩', 't2.나는 누구일까?', '선생님2', '010-1100-2222', null, 1);
insert into members values('teacher3', '3456', '서울스마트시티센터', 't3.나는 누구일까?', '선생님3', '010-1100-3333', null, 1);

insert into members values('user1', '1234', '소린', 'u1.나는 누구일까?', '난쟁이1', '010-1111-2222', null, 2);
insert into members values('user2', '2345', '드왈린', 'u2.나는 누구일까?', '난쟁이2', '010-2222-3333', null, 2);
insert into members values('user3', '3456', '발린', 'u3.나는 누구일까?', '난쟁이3', '010-3333-4444', null, 2);
insert into members values('user4', '4567', '필리', 'u4.나는 누구일까?', '난쟁이4', '010-4444-5555', null, 2);
insert into members values('user5', '5678', '킬리', 'u5.나는 누구일까?', '난쟁이5', '010-5555-6666', null, 2);
insert into members values('user6', '6789', '오리', 'u6.나는 누구일까?', '난쟁이6', '010-6666-7777', null, 2);
insert into members values('user7', '8901', '노리', 'u7.나는 누구일까?', '난쟁이7', '010-7777-8888', null, 2);
insert into members values('user8', '9012', '비푸르', 'u8.나는 누구일까?', '난쟁이8', '010-8888-9999', null, 2);
insert into members values('user9', '0123', '보푸르', 'u9.나는 누구일까?', '난쟁이9', '010-9999-0000', null, 2);

create table menus(
	menu_name varchar(30)  not null,
    menu_catnum bigint not null,
    menu_snum bigint not null,
    m_id varchar(30) not null,
    p_no bigint not null default 0,
    primary key(menu_name)
);
insert into menus values('C', 1001, 2001, 'teacher2', 0);
insert into menus values('Java', 1001, 2002, 'teacher1', 0);
insert into menus values('Python', 1001, 2003, 'teacher2', 0);
insert into menus values('JSP', 1002, 2004, 'teacher3', 0);
insert into menus values('Spring', 1002, 2005, 'teacher3', 0);
insert into menus values('Node.js', 1002, 2006, 'teacher1', 0);
insert into menus values('Oracle', 1003, 2007, 'teacher1', 0);
insert into menus values('MySQL', 1003, 2008, 'teacher1', 0);
insert into menus values('VirtualBox', 1004, 2009, 'teacher1', 0);
insert into menus values('Github', 1005, 2010, 'teacher1', 0);
insert into menus values('Git', 1005, 2011, 'teacher1', 0);
insert into menus values('알고리즘', 1006, 2012, 'teacher1', 0);
insert into menus values('OAuth', 1006, 2013, 'teacher1', 0);

create table C_TBL(
	scat_num int not null auto_increment,
    title text not null,
    content text null,
    vid_title varchar(50) not null,
    vid_url text not null,
    code text null,
    summary text null,
    img mediumblob null,
    writeDate date default (current_date()),
    vid_title2 varchar(50) null,
    vid_url2 text null,
    code2 text null,
    summary2 text null,
    img2 mediumblob null,
    primary key(scat_num)
);
insert into C_TBL (title, content, vid_title, vid_url, code, summary, img) values('소개', '"너무 좋습니다 ! 책으로 먼저 배열과 포인터를 공부했는데 이해가 되지 않아서 이 강의 영상을 보니 단번에 이해가 되었어요!"

"예시가 적절해서 굉장히 배우기 좋았습니다"

"강의 빠져드네요 ㅋㅋㅋ 비전공자인데 재밌게 보게 됩니다"

"크...설명 진짜 잘하시네"

"강사님을 고용하고 싶습니다. 얼마면 되나요?"




이미 많은 분들이 공부하셨습니다.

이제는 여러분 차례입니다.



본 강의는 게임을 직접 만들며 배우는, 초보자를 위한 C 프로그래밍 입니다. 배우긴 배웠는데 어떻게 활용하지? 나만의 프로그램을 만들 수 있을까? 포인터가 정말 어렵다는데? …



걱정하지 마세요!


여러분들이 어려워하는 부분을 누구보다 잘 알고 있기에 누구보다 쉽게 가르칩니다. 그리고 매 강의마다 배운 내용을 토대로 직접 게임을 만들어 봅니다.



왜 어려운 C 를 배워야 하나요?


C 는 조금 어렵지만, 아주 기본이 되는 언어입니다. C 를 마스터 하고 나면, 다른 언어는 굉장히 쉽게 배울 수 있습니다. 그리고, 나도코딩이 깜짝! 놀랄만큼 쉽게 알려드리겠습니다.



C 프로그래밍, 지금 바로 시작하세요 !', '강의', 'https://www.youtube.com/embed/dEykoFZkf5Y', null, null, null);
insert into C_TBL (title, content, vid_title, vid_url, code, summary, img) values('환경설정', '', '강의', 'https://www.youtube.com/embed/_jjZysGQS_w', null, null, null);
insert into C_TBL (title, content, vid_title, vid_url, code, summary, img) values('Hello World', '', '강의', 'https://www.youtube.com/embed/xNMGGQIU8FU', null, null, null);
insert into C_TBL (title, content, vid_title, vid_url, code, summary, img) values('경찰서 조서', '', '강의', 'https://www.youtube.com/embed/LF46oSNFP_U', null, null, null);

create table Java_TBL(
	scat_num int not null auto_increment,
    title text not null,
    content text null,
    vid_title varchar(50) not null,
    vid_url text not null,
    code text null,
    summary text null,
    img mediumblob null,
    writeDate date default (current_date()),
    vid_title2 varchar(50) null,
    vid_url2 text null,
    code2 text null,
    summary2 text null,
    img2 mediumblob null,
    primary key(scat_num)
);


/*
create table comment_2001_1( # 2001(C과목) 1번 게시글 댓글 모음
	c_num bigint not null primary key,
    p_num bigint not null default 0,
    m_id varchar(30) not null,
    title text not null,
    content text not null,
    writeDate date not null default (current_date())
);
create table comment_2001_2( # 2001(C과목) 2번 게시글 댓글 모음
	c_num bigint not null primary key,
    p_num bigint not null default 0,
    m_id varchar(30) not null,
    title text not null,
    content text not null,
    writeDate date not null default (current_date())
);
create table comment_2001_3( # 2001(C과목) 3번 게시글 댓글 모음
	c_num bigint not null primary key,
    p_num bigint not null default 0,
    m_id varchar(30) not null,
    title text not null,
    content text not null,
    writeDate date not null default (current_date())
);
create table comment_2001_4( # 2001(C과목) 4번 게시글 댓글 모음
	c_num bigint not null primary key,
    p_num bigint not null default 0,
    m_id varchar(30) not null,
    title text not null,
    content text not null,
    writeDate date not null default (current_date())
);
*/
# 슬슬 생각나는 보완점 : 원래는 과목의 강의마다 이렇게 댓글 테이블을 하나하나 생성하려 그랬다. 근데 그러면 테이블이 너무 많아진다.
# 그냥 과목마다 댓글 테이블을 하나씩 만들고, 강의 번호(특소분류 번호)로 구분을 지어서 하나로 합치는 건 어떨까? 밑에처럼?
create table comment_C( # C과목 모든 댓글 모음
	c_num bigint not null primary key,
    p_num bigint not null default 0,
    m_id varchar(30) not null,
    title text not null,
    content text not null,
    writeDate date not null default (current_date()),
    scat_num int not null,   # 게시글 분류 (이건 C_TBL에 있죠?) : 그래서 외래키! 게시글과 동고동락을 함께 한다! 
    foreign key(scat_num) references C_TBL(scat_num) on delete cascade on update cascade
);


select * from members;
select * from menus;
select * from C_TBL;
select count(*) from comment_C;