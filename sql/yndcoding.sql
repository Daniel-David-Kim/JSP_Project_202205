create database yndcoding;
use yndcoding;

SET GLOBAL log_bin_trust_function_creators = 1;

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


create table admin1_board(
	q_num bigint not null primary key,
    p_num bigint not null default 0,
    m_id varchar(30) not null,
    title text not null,
    content text not null,
    writeDate date not null default(current_date())
);

drop function if exists admin1_getHierboard; # 스토어드 함수가 있다면 삭제하는 쿼리
delimiter %%
create function admin1_getHierboard() returns int     # 여기서 테이블 이름 바꾸어주기
begin
	declare v_no int;
    declare v_parent int;
    set v_no = -1;
    set v_parent = @num;
    while true do
		select min(q_num) into @num from admin1_board where p_num=v_parent and q_num > v_no;   # 여기서 테이블 이름 바꾸어주기
        if (@num is not null) or (v_parent = @init) then
			set @depth = @depth + 1;
            return @num;
		end if;
        set @depth = @depth - 1;
        select q_num, p_num into v_no, v_parent from admin1_board where q_num=v_parent;   # 여기서 테이블 이름 바꾸어주기
    end while;
end	%%
delimiter ;

create table teacher1_board(
	q_num bigint not null primary key,
    p_num bigint not null default 0,
    m_id varchar(30) not null,
    title text not null,
    content text not null,
    writeDate date not null default(current_date())
);
insert into teacher1_board values (1, 0, 'user1', 'test1', 'this is test1', '2020-01-01');
insert into teacher1_board values (2, 1, 'user2', 'test2', 'this is test2', '2020-01-01');
insert into teacher1_board values (3, 1, 'user3', 'test3', 'this is test3', '2020-01-03');
insert into teacher1_board values (4, 0, 'user4', 'test4', 'this is test4', '2020-01-05');
insert into teacher1_board values (5, 2, 'user5', 'test5', 'this is test5', '2020-01-17');
insert into teacher1_board values (6, 5, 'user6', 'test6', 'this is test6', '2020-01-21');
insert into teacher1_board values (7, 4, 'user7', 'test7', 'this is test7', '2020-01-30');
insert into teacher1_board values (8, 0, 'user8', 'test8', 'this is test8', '2020-02-01');
insert into teacher1_board values (9, 0, 'user9', 'test9', 'this is test9', '2020-02-01');
insert into teacher1_board values (10, 2, 'teacher1', 'test10', 'this is test10', '2020-02-13');
insert into teacher1_board values (11, 9, 'teacher2', 'test11', 'this is test11', '2020-02-17');
insert into teacher1_board values (12, 0, 'user3', 'test12', 'this is test12', '2020-02-28');

insert into teacher1_board values (13, 0, 'user1', 'test1', 'this is test1', '2020-01-01');
insert into teacher1_board values (14, 1, 'user2', 'test2', 'this is test2', '2020-01-01');
insert into teacher1_board values (15, 1, 'user3', 'test3', 'this is test3', '2020-01-03');
insert into teacher1_board values (16, 0, 'user4', 'test4', 'this is test4', '2020-01-05');
insert into teacher1_board values (17, 2, 'user5', 'test5', 'this is test5', '2020-01-17');
insert into teacher1_board values (18, 5, 'user6', 'test6', 'this is test6', '2020-01-21');
insert into teacher1_board values (19, 4, 'user7', 'test7', 'this is test7', '2020-01-30');
insert into teacher1_board values (20, 0, 'user8', 'test8', 'this is test8', '2020-02-01');
insert into teacher1_board values (21, 0, 'user9', 'test9', 'this is test9', '2020-02-01');
insert into teacher1_board values (22, 2, 'teacher1', 'test10', 'this is test10', '2020-02-13');
insert into teacher1_board values (23, 9, 'teacher2', 'test11', 'this is test11', '2020-02-17');
insert into teacher1_board values (24, 0, 'user3', 'test12', 'this is test12', '2020-02-28');
insert into teacher1_board values (25, 0, 'user1', 'test1', 'this is test1', '2020-01-01');
insert into teacher1_board values (26, 1, 'user2', 'test2', 'this is test2', '2020-01-01');
insert into teacher1_board values (27, 1, 'user3', 'test3', 'this is test3', '2020-01-03');
insert into teacher1_board values (28, 0, 'user4', 'test4', 'this is test4', '2020-01-05');
insert into teacher1_board values (29, 2, 'user5', 'test5', 'this is test5', '2020-01-17');
insert into teacher1_board values (30, 5, 'user6', 'test6', 'this is test6', '2020-01-21');
insert into teacher1_board values (31, 4, 'user7', 'test7', 'this is test7', '2020-01-30');
insert into teacher1_board values (32, 0, 'user8', 'test8', 'this is test8', '2020-02-01');
insert into teacher1_board values (33, 0, 'user9', 'test9', 'this is test9', '2020-02-01');
insert into teacher1_board values (34, 2, 'teacher1', 'test10', 'this is test10', '2020-02-13');
insert into teacher1_board values (35, 9, 'teacher2', 'test11', 'this is test11', '2020-02-17');
insert into teacher1_board values (36, 0, 'user3', 'test12', 'this is test12', '2020-02-28');
insert into teacher1_board values (37, 0, 'user1', 'test1', 'this is test1', '2020-01-01');
insert into teacher1_board values (38, 1, 'user2', 'test2', 'this is test2', '2020-01-01');
insert into teacher1_board values (39, 1, 'user3', 'test3', 'this is test3', '2020-01-03');
insert into teacher1_board values (40, 0, 'user4', 'test4', 'this is test4', '2020-01-05');
insert into teacher1_board values (41, 2, 'user5', 'test5', 'this is test5', '2020-01-17');
insert into teacher1_board values (42, 5, 'user6', 'test6', 'this is test6', '2020-01-21');
insert into teacher1_board values (43, 4, 'user7', 'test7', 'this is test7', '2020-01-30');
insert into teacher1_board values (44, 0, 'user8', 'test8', 'this is test8', '2020-02-01');
insert into teacher1_board values (45, 0, 'user9', 'test9', 'this is test9', '2020-02-01');
insert into teacher1_board values (46, 2, 'teacher1', 'test10', 'this is test10', '2020-02-13');
insert into teacher1_board values (47, 9, 'teacher2', 'test11', 'this is test11', '2020-02-17');
insert into teacher1_board values (48, 0, 'user3', 'test12', 'this is test12', '2020-02-28');
insert into teacher1_board values (49, 0, 'user1', 'test1', 'this is test1', '2020-01-01');
insert into teacher1_board values (50, 1, 'user2', 'test2', 'this is test2', '2020-01-01');
insert into teacher1_board values (51, 1, 'user3', 'test3', 'this is test3', '2020-01-03');
insert into teacher1_board values (52, 0, 'user4', 'test4', 'this is test4', '2020-01-05');
insert into teacher1_board values (53, 2, 'user5', 'test5', 'this is test5', '2020-01-17');
insert into teacher1_board values (54, 5, 'user6', 'test6', 'this is test6', '2020-01-21');
insert into teacher1_board values (55, 4, 'user7', 'test7', 'this is test7', '2020-01-30');
insert into teacher1_board values (56, 0, 'user8', 'test8', 'this is test8', '2020-02-01');
insert into teacher1_board values (57, 0, 'user9', 'test9', 'this is test9', '2020-02-01');
insert into teacher1_board values (58, 2, 'teacher1', 'test10', 'this is test10', '2020-02-13');
insert into teacher1_board values (59, 9, 'teacher2', 'test11', 'this is test11', '2020-02-17');
insert into teacher1_board values (60, 0, 'user3', 'test12', 'this is test12', '2020-02-28');
insert into teacher1_board values (61, 0, 'user1', 'test1', 'this is test1', '2020-01-01');
insert into teacher1_board values (62, 1, 'user2', 'test2', 'this is test2', '2020-01-01');
insert into teacher1_board values (63, 1, 'user3', 'test3', 'this is test3', '2020-01-03');
insert into teacher1_board values (64, 0, 'user4', 'test4', 'this is test4', '2020-01-05');
insert into teacher1_board values (65, 2, 'user5', 'test5', 'this is test5', '2020-01-17');
insert into teacher1_board values (66, 5, 'user6', 'test6', 'this is test6', '2020-01-21');
insert into teacher1_board values (67, 4, 'user7', 'test7', 'this is test7', '2020-01-30');
insert into teacher1_board values (68, 0, 'user8', 'test8', 'this is test8', '2020-02-01');
insert into teacher1_board values (69, 0, 'user9', 'test9', 'this is test9', '2020-02-01');
insert into teacher1_board values (70, 2, 'teacher1', 'test10', 'this is test10', '2020-02-13');
insert into teacher1_board values (71, 9, 'teacher2', 'test11', 'this is test11', '2020-02-17');
insert into teacher1_board values (72, 0, 'user3', 'test12', 'this is test12', '2020-02-28');
insert into teacher1_board values (73, 0, 'user1', 'test1', 'this is test1', '2020-01-01');
insert into teacher1_board values (74, 1, 'user2', 'test2', 'this is test2', '2020-01-01');
insert into teacher1_board values (75, 1, 'user3', 'test3', 'this is test3', '2020-01-03');
insert into teacher1_board values (76, 0, 'user4', 'test4', 'this is test4', '2020-01-05');
insert into teacher1_board values (77, 2, 'user5', 'test5', 'this is test5', '2020-01-17');
insert into teacher1_board values (78, 5, 'user6', 'test6', 'this is test6', '2020-01-21');
insert into teacher1_board values (79, 4, 'user7', 'test7', 'this is test7', '2020-01-30');
insert into teacher1_board values (80, 0, 'user8', 'test8', 'this is test8', '2020-02-01');
insert into teacher1_board values (81, 0, 'user9', 'test9', 'this is test9', '2020-02-01');
insert into teacher1_board values (82, 2, 'teacher1', 'test10', 'this is test10', '2020-02-13');
insert into teacher1_board values (83, 9, 'teacher2', 'test11', 'this is test11', '2020-02-17');
insert into teacher1_board values (84, 0, 'user3', 'test12', 'this is test12', '2020-02-28');
insert into teacher1_board values (85, 2, 'user5', 'test5', 'this is test5', '2020-01-17');
insert into teacher1_board values (86, 5, 'user6', 'test6', 'this is test6', '2020-01-21');
insert into teacher1_board values (87, 4, 'user7', 'test7', 'this is test7', '2020-01-30');
insert into teacher1_board values (88, 0, 'user8', 'test8', 'this is test8', '2020-02-01');
insert into teacher1_board values (89, 0, 'user9', 'test9', 'this is test9', '2020-02-01');
insert into teacher1_board values (90, 2, 'teacher1', 'test10', 'this is test10', '2020-02-13');
insert into teacher1_board values (91, 9, 'teacher2', 'test11', 'this is test11', '2020-02-17');
insert into teacher1_board values (92, 0, 'user3', 'test12', 'this is test12', '2020-02-28');
insert into teacher1_board values (93, 0, 'user1', 'test1', 'this is test1', '2020-01-01');
insert into teacher1_board values (94, 0, 'user3', 'test12', 'this is test12', '2020-02-28');
insert into teacher1_board values (95, 2, 'user5', 'test5', 'this is test5', '2020-01-17');
insert into teacher1_board values (96, 5, 'user6', 'test6', 'this is test6', '2020-01-21');
insert into teacher1_board values (97, 4, 'user7', 'test7', 'this is test7', '2020-01-30');
insert into teacher1_board values (98, 0, 'user8', 'test8', 'this is test8', '2020-02-01');
insert into teacher1_board values (99, 0, 'user9', 'test9', 'this is test9', '2020-02-01');
insert into teacher1_board values (100, 2, 'teacher1', 'test10', 'this is test10', '2020-02-13');
insert into teacher1_board values (101, 9, 'teacher2', 'test11', 'this is test11', '2020-02-17');
insert into teacher1_board values (102, 0, 'user3', 'test12', 'this is test12', '2020-02-28');
insert into teacher1_board values (103, 0, 'user1', 'test1', 'this is test1', '2020-01-01');

# 각 강의자 테이블마다 필요한 스토어드 함수
#drop function getHierboard;
delimiter %%
create function teacher1_getHierboard() returns int
begin
	declare v_no int;
    declare v_parent int;
    set v_no = -1;
    set v_parent = @num;
    while true do
		select min(q_num) into @num from teacher1_board where p_num=v_parent and q_num > v_no;
        if (@num is not null) or (v_parent = @init) then
			set @depth = @depth + 1;
            return @num;
		end if;
        set @depth = @depth - 1;
        select q_num, p_num into v_no, v_parent from teacher1_board where q_num=v_parent;
    end while;
end	%%
delimiter ;

# 스토어드 함수를 통한 계층형 쿼리
select depth, q_num, p_num, lpad(title, char_length(title) + (depth-1)*4, ' ') as title, content, m_id, writeDate
from (select teacher1_getHierboard() as hid, @depth as depth from (select @depth:=0, @init:=0, @num:=0) datas inner join teacher1_board) hier
left outer join teacher1_board tb on tb.q_num=hier.hid;

# limit 시작위치(0부터), 반환 갯수
select depth, q_num, p_num, lpad(title, char_length(title) + (depth-1)*4, ' ') as title, content, m_id, writeDate
from (select teacher1_getHierboard() as hid, @depth as depth from (select @depth:=0, @init:=0, @num:=0) datas inner join teacher1_board) hier
left outer join teacher1_board tb on tb.q_num=hier.hid limit 40, 10;

create table teacher2_board(
	q_num bigint not null primary key,
    p_num bigint not null default 0,
    m_id varchar(30) not null,
    title text not null,
    content text not null,
    writeDate date not null default(current_date())
);
insert into teacher2_board values (1, 0, 'user1', 'test1', 'this is test1', '2020-01-01');
insert into teacher2_board values (2, 1, 'user2', 'test2', 'this is test2', '2020-01-11');
insert into teacher2_board values (3, 0, 'user3', 'test3', 'this is test3', '2020-01-21');
insert into teacher2_board values (4, 1, 'user4', 'test4', 'this is test4', '2020-01-31');
insert into teacher2_board values (5, 2, 'user5', 'test5', 'this is test5', '2020-02-01');

#drop function getHierboard;
delimiter %%
create function teacher2_getHierboard() returns int
begin
	declare v_no int;
    declare v_parent int;
    set v_no = -1;
    set v_parent = @num;
    while true do
		select min(q_num) into @num from teacher2_board where p_num=v_parent and q_num > v_no;
        if (@num is not null) or (v_parent = @init) then
			set @depth = @depth + 1;
            return @num;
		end if;
        set @depth = @depth - 1;
        select q_num, p_num into v_no, v_parent from teacher2_board where q_num=v_parent;
    end while;
end	%%
delimiter ;

# 스토어드 함수를 통한 계층형 쿼리
select depth, q_num, p_num, lpad(title, char_length(title) + (depth-1)*4, ' ') as title, content, m_id, writeDate
from (select teacher2_getHierboard() as hid, @depth as depth from (select @depth:=0, @init:=0, @num:=0) datas inner join teacher2_board) hier
left outer join teacher2_board tb on tb.q_num=hier.hid;

# limit 시작위치(0부터), 반환 갯수
select depth, q_num, p_num, lpad(title, char_length(title) + (depth-1)*4, ' ') as title, content, m_id, writeDate
from (select teacher2_getHierboard() as hid, @depth as depth from (select @depth:=0, @init:=0, @num:=0) datas inner join teacher2_board) hier
left outer join teacher2_board tb on tb.q_num=hier.hid limit 0, 10;

#drop table teacher3_board;

create table if not exists teacher3_board(
	q_num bigint not null primary key,
    p_num bigint not null default 0,
    m_id varchar(30) not null,
    title text not null,
    content text not null,
    writeDate date not null default(current_date())
);
insert into teacher3_board values (1, 0, 'user1', 'test1', 'this is test1', '2020-01-01');
insert into teacher3_board values (2, 1, 'user2', 'test2', 'this is test2', '2020-01-11');
insert into teacher3_board values (3, 0, 'user3', 'test3', 'this is test3', '2020-01-21');
insert into teacher3_board values (4, 1, 'user4', 'test4', 'this is test4', '2020-01-31');
insert into teacher3_board values (5, 2, 'user5', 'test5', 'this is test5', '2020-02-01');
insert into teacher3_board values (6, 0, 'user1', 'test1', 'this is test1', '2020-01-01');
insert into teacher3_board values (7, 6, 'user2', 'test2', 'this is test2', '2020-01-11');
insert into teacher3_board values (8, 7, 'user3', 'test3', 'this is test3', '2020-01-21');
insert into teacher3_board values (9, 8, 'user4', 'test4', 'this is test4', '2020-01-31');
insert into teacher3_board values (0, 9, 'user5', 'test5', 'this is test5', '2020-02-01');

# 각 강의자 테이블마다 필요한 스토어드 함수
drop function if exists teacher3_getHierboard; # 스토어드 함수가 있다면 삭제하는 쿼리
delimiter %%
create function teacher3_getHierboard() returns int     # 여기서 테이블 이름 바꾸어주기
begin
	declare v_no int;
    declare v_parent int;
    set v_no = -1;
    set v_parent = @num;
    while true do
		select min(q_num) into @num from teacher3_board where p_num=v_parent and q_num > v_no;   # 여기서 테이블 이름 바꾸어주기
        if (@num is not null) or (v_parent = @init) then
			set @depth = @depth + 1;
            return @num;
		end if;
        set @depth = @depth - 1;
        select q_num, p_num into v_no, v_parent from teacher3_board where q_num=v_parent;   # 여기서 테이블 이름 바꾸어주기
    end while;
end	%%
delimiter ;

# 스토어드 함수를 통한 계층형 쿼리
select depth, q_num, p_num, lpad(title, char_length(title) + (depth-1)*4, ' ') as title, content, m_id, writeDate
from (select teacher3_getHierboard() as hid, @depth as depth from (select @depth:=0, @init:=0, @num:=0) datas inner join teacher3_board) hier     # 여기서 테이블 이름 2개! 바꾸어주기
left outer join teacher3_board tb on tb.q_num=hier.hid;    # 여기서 테이블 이름 바꾸어주기

# limit 시작위치(0부터), 반환 갯수
select depth, q_num, p_num, lpad(title, char_length(title) + (depth-1)*4, ' ') as title, content, m_id, writeDate
from (select teacher3_getHierboard() as hid, @depth as depth from (select @depth:=0, @init:=0, @num:=0) datas inner join teacher3_board) hier   # 여기서 테이블 이름 2개 바꾸어주기
left outer join teacher3_board tb on tb.q_num=hier.hid limit 0, 10;   # 여기서 테이블 이름 바꾸어주기

# 테이블이 있다면 삭제하는 쿼리
#drop table if exists user2_board;

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
insert into menus values('Nodejs', 1002, 2006, 'teacher1', 0);
insert into menus values('Oracle', 1003, 2007, 'teacher1', 0);
insert into menus values('MySQL', 1003, 2008, 'teacher1', 0);
insert into menus values('VirtualBox', 1004, 2009, 'teacher1', 0);
insert into menus values('Github', 1005, 2010, 'teacher1', 0);
insert into menus values('Git', 1005, 2011, 'teacher1', 0);
insert into menus values('알고리즘', 1006, 2012, 'teacher1', 0);
insert into menus values('OAuth', 1006, 2013, 'teacher1', 0);

select max(menu_snum) from menus;

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
insert into C_TBL (title, content, vid_title, vid_url, code, summary, img) values('소개', '"너무 좋습니다 ! 책으로 먼저 배열과 포인터를 공부했는데 이해가 되지 않아서 이 강의 영상을 보니 단번에 이해가 되었어요!", "예시가 적절해서 굉장히 배우기 좋았습니다", "강의 빠져드네요 ㅋㅋㅋ 비전공자인데 재밌게 보게 됩니다", "크...설명 진짜 잘하시네", "강사님을 고용하고 싶습니다. 얼마면 되나요?" 이미 많은 분들이 공부하셨습니다. 이제는 여러분 차례입니다. 본 강의는 게임을 직접 만들며 배우는, 초보자를 위한 C 프로그래밍 입니다. 배우긴 배웠는데 어떻게 활용하지? 나만의 프로그램을 만들 수 있을까? 포인터가 정말 어렵다는데? …걱정하지 마세요! 여러분들이 어려워하는 부분을 누구보다 잘 알고 있기에 누구보다 쉽게 가르칩니다. 그리고 매 강의마다 배운 내용을 토대로 직접 게임을 만들어 봅니다. 왜 어려운 C 를 배워야 하나요? C 는 조금 어렵지만, 아주 기본이 되는 언어입니다. C 를 마스터 하고 나면, 다른 언어는 굉장히 쉽게 배울 수 있습니다. 그리고, 나도코딩이 깜짝! 놀랄만큼 쉽게 알려드리겠습니다. C 프로그래밍, 지금 바로 시작하세요 !', '강의', 'https://www.youtube.com/embed/dEykoFZkf5Y', null, null, null);
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
insert into Java_TBL (title, content, vid_title, vid_url, code, summary, img) values('언어소개', 'Java의 역사 1995년 자바의 아버지라고 불리는 제임스 고슬링과 그의 동료들에 의해서 시작된 프로젝트다. Java는 원래 가전제품을 제어하기 위한 언어로 고안되었지만 웹의 등장으로 엄청난 성공을 거두면서 주류 언어가 되었다. 역사에 대한 더 자세한 내용은 직접 찾아보자. 한국에서는 정부나 기업의 시스템 통합 프로젝트가 대부분 자바로 구현되기 때문에 자바는 기업용 시장에서 두각을 나타내고 있다. 시스템 통합이란? System Integration의 약자로 기관이나 기업의 업무 관리를 소프트웨어화하는 것을 의미한다. 예를 들어 병원에 대한 SI라고 한다면 환자의 상태와 의료진의 상태에 따라서 효율적으로 진료가 이루어지게 한다거나, 제조 공정이라고 한다면 생산설비의 상태를 시스템적으로 관리하는 것이 있을 것이다. 아래는 국내에 대표적인 SI 업체인 삼성 SDS에서 하는 일에 대한 소개이다. http://www.sds.samsung.co.kr/bizintro/service/overview.jsp 모바일 플랫폼인 안드로이드가 대성공을 거두면서 자바의 수요가 급증했다. 현재(2013년 말) 모바일 시장에서 안드로이드의 점유율이 80%를 넘은 상황이다. 더불어서 자바의 중요성은 더욱 커지는 중이라고 할 수 있다. Java의 특징 : 기술 진보는 "절망의 성숙"을 통해서 이루어진다. 자바 이전에도 많은 언어들가 있었다. 이들 선배 언어들은 그 언어가 소속된 시대의 문제를 해결하기 위해서 고안되었다. 마침내 이들 언어들가 문제를 해결했을 때 이 언어들은 새로운 문제에 직면하게 된다. 문제들은 점점 처치 곤란한 절망이 되고 그 절망이 충분히 성숙했을 때 비로소 새로운 도약지점이 보이기 시작한다. 자바라는 언어를 온전하게 이해하기 위해서는 자바 이전 세대의 기술들이 초래한 절망을 이해해야 할 것이다. 하지만 자바는 이제 새로운 언어가 아니다. 주류 언어이자 고전언어의 반열로 추대되는 분위기다. 자바의 시대가 왔지만, 정작 지금 시점에서 자바를 배우는 사람들은 지금의 자바를 있게 한 그 절망을 경험하지 못한 세대일 가능성이 크다. (최소한 필자가 그렇다.) 그래서 필자는 지금 이 시점에서 역사적 맥락에서만 파악될 수 있는 자바의 상대적인 특성은 언급하지 않는 것이 좋겠다고 생각했다. 이러한 내용은 자바의 특징 링크를 참고하자. 또한, 객체지향처럼 단편적인 설명만으로는 오히려 혼란만을 초래할 수 있는 개념도 지금 소개하지 않을 것이다. 이러한 개념은 구체적인 코드를 보면서 설명해야 불필요하게 철학적으로 빠지지 않는다. 그렇게 빼다보니까 가상머신이 남았다. 그런데 가상머신의 핵심적인 가치라고 할 수 있는 "동일한 프로그램이 운영체제 가리지 않고 실행된다는 특성"은 C나 C++과 같은 기존의 언어에서는 혁명적인 발상이지만, 오늘날 대세가 되고 있는 고급언어들(Python, Ruby, PHP, JavaScript)의 관점에서는 그렇게 대단한 것이 아닐 수 있다. 그래서 필자는 지금 자바의 특성을 언급하지 않을 생각이다. 그러고보니까 정작 지금 단계에서 자바의 특징을 이야기 할 것이 남아있지 않다. 차차로 알아가도록 하자. (결론이 좀 이상하다) 학습방법 : 대신에 초심자에게 자바라는 언어가 어떻게 다가올 수 있는지를 소개하는 것이 좋을 것 같다. 만약 독자가 C를 했다면 자바의 객체지향이 어렵게 느껴질 수 있다. 그 외에는 큰 어려움은 없을 것 같다. C++를 했다면 자바의 객체지향은 C++의 급진적인 객체지향을 좀 더 현실적인 방향으로 다듬은 것처럼 느껴질 수 있을 것 같다. 쉬울 것 같다. 반면에 프로그래밍을 처음 시작하는 분이라면 자바 보다는 차라리 자바스크립트를 먼저 공부하는 것이 추천하고 싶다. 거칠게 표현하면 자바스크립트는 자바 보다 훨씬 쉽다. 그러면서 최근에는 차차로 자바의 경쟁자로 부상하고 있는 중이다. 자바스크립트로 프로그래밍에 대한 감을 조금 익히고 자바에 도전한다면 좀 더 수월할 것 같다. (자바스크립트 강의) 하지만 안드로이드 프로그래밍을 하고 싶다거나, 또는 합류한 직장이 자바를 쓰고 있다면 자바를 해야 한다. 이런 경우는 이를 악물고 도전하는 수 밖에 없다. 물론, 필자는 프로그래밍을 처음 시작하는 입문자를 상정하고 이 수업을 만들 것이다.', '강의', 'https://www.youtube.com/embed/qR90tdW0Hbo', 
null, '자바에 대한 자료들 : 자바에 대한 자료는 매우 풍부한 편이다. 그 중에서 학습에 필요할 수 있는 몇가지 중요한 소스들을 열거하면 다음과 같다. 자바 홈페이지 : 난 정말 자바를 공부한 적이 없어요 : 열혈 C 프로그래밍의 저자로 유명한 윤성우님의  자바 강의로 회원가입만하면 모든 강의를 무료로 볼 수 있다. 초보자의 입장을 배려해서 쓰여진 책과 강의다. 소설 자바 : 자바에 대한 매우 상세한 텍스트 수업이 무료로 제공된다. 동영상 강의는 책을 구입한 후에 시청 할 수 있다. 이 책은 원론적인 접근을 많이 하는 편이다. 반면에 프로그래밍에 기본적인 부분은 어느 정도 독자가 숙지하고 있다고 간주하고 있다. C나 C++ 개발자에게는 매우 휼룡한 책이 될 것 같고, 자바를 처음 시작하는 독자에게는 열혈 C 프로그래밍을 추천한다. 점프 투 자바', null);

create table Python_TBL(
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

create table JSP_TBL(
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

create table Spring_TBL(
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

create table Nodejs_TBL(
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

create table Oracle_TBL(
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

create table MySQL_TBL(
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

create table VirtualBox_TBL(
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

create table Github_TBL(
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

create table Git_TBL(
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

create table 알고리즘_TBL(
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

create table OAuth_TBL(
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


# 슬슬 생각나는 보완점 : 원래는 과목의 강의마다 이렇게 댓글 테이블을 하나하나 생성하려 그랬다. 근데 그러면 테이블이 너무 많아진다.
# 그냥 과목마다 댓글 테이블을 하나씩 만들고, 강의 번호(특소분류 번호)로 구분을 지어서 하나로 합치는 건 어떨까? 밑에처럼?
create table comment_C( # C과목 모든 댓글 모음
	c_num bigint not null primary key auto_increment,
    m_id varchar(30) not null,
    content text not null,
    writeDate date not null default (current_date()),
    scat_num int not null,   # 게시글 분류 (이건 C_TBL에 있죠?) : 그래서 외래키! 게시글과 동고동락을 함께 한다! 
    foreign key(scat_num) references C_TBL(scat_num) on delete cascade on update cascade
);
insert into comment_C (m_id, content, writeDate, scat_num) values ('user1', '하핳하 첫 댓글입니다!', '2020-01-01', 1);
insert into comment_C (m_id, content, writeDate, scat_num) values ('teacher1', '두번째 댓글입니다!', '2020-01-03', 1);
insert into comment_C (m_id, content, writeDate, scat_num) values ('user2', '삼 댓글입니다!', '2020-01-03', 1);
insert into comment_C (m_id, content, writeDate, scat_num) values ('user9', '흥! 네번째 댓글!', '2020-01-05', 1);
insert into comment_C (m_id, content, writeDate, scat_num) values ('user7', '음!! 이건 이렇게 하면 될텐데!', '2020-01-07', 1);
insert into comment_C (m_id, content, writeDate, scat_num) values ('teeacher2', '허허 라떼는 말이야! 녹차라떼지!!', '2020-01-09', 2);
insert into comment_C (m_id, content, writeDate, scat_num) values ('user5', '나는 내가 좋다!', '2020-01-11', 2);
insert into comment_C (m_id, content, writeDate, scat_num) values ('user2', '살어리 살어리랏다 쳥산에 살어리랏다!', '2020-01-13', 2);
insert into comment_C (m_id, content, writeDate, scat_num) values ('user3', '멀위랑 다래랑 먹고 쳥산에 살어리랏다!', '2020-01-15', 2);
insert into comment_C (m_id, content, writeDate, scat_num) values ('user1', '얄리얄리 얄라셩 얄라리 얄라!', '2020-01-17', 2);
insert into comment_C (m_id, content, writeDate, scat_num) values ('user7', '와! 여름이다!', '2020-01-19', 3);
insert into comment_C (m_id, content, writeDate, scat_num) values ('user6', '와 종강이다!!', '2020-01-21', 3);
insert into comment_C (m_id, content, writeDate, scat_num) values ('user4', '빨리 떠나자! 야이야이야이야 바다로!', '2020-01-23', 3);
insert into comment_C (m_id, content, writeDate, scat_num) values ('user2', '신데렐라 증후군! 이게 무엇인가요?', '2020-01-25', 3);
insert into comment_C (m_id, content, writeDate, scat_num) values ('user5', '따...딱히 댓글은 남기고 싶지 않았어!! 흥!', '2020-01-27', 3);
insert into comment_C (m_id, content, writeDate, scat_num) values ('user7', '따...딱히 댓글은 남기고 싶지 않았어!! 흥!', current_date(), 3);

create table comment_Java( # 과목 모든 댓글 모음
	c_num bigint not null primary key auto_increment,
    m_id varchar(30) not null,
    content text not null,
    writeDate date not null default (current_date()),
    scat_num int not null,   
    foreign key(scat_num) references Java_TBL(scat_num) on delete cascade on update cascade
);

create table comment_Python( # 과목 모든 댓글 모음
	c_num bigint not null primary key auto_increment,
    m_id varchar(30) not null,
    content text not null,
    writeDate date not null default (current_date()),
    scat_num int not null,   
    foreign key(scat_num) references Python_TBL(scat_num) on delete cascade on update cascade
);

create table comment_JSP( # 과목 모든 댓글 모음
	c_num bigint not null primary key auto_increment,
    m_id varchar(30) not null,
    content text not null,
    writeDate date not null default (current_date()),
    scat_num int not null,   
    foreign key(scat_num) references JSP_TBL(scat_num) on delete cascade on update cascade
);

create table comment_Spring( # 과목 모든 댓글 모음
	c_num bigint not null primary key auto_increment,
    m_id varchar(30) not null,
    content text not null,
    writeDate date not null default (current_date()),
    scat_num int not null,   
    foreign key(scat_num) references Spring_TBL(scat_num) on delete cascade on update cascade
);

create table comment_Nodejs( # 과목 모든 댓글 모음
	c_num bigint not null primary key auto_increment,
    m_id varchar(30) not null,
    content text not null,
    writeDate date not null default (current_date()),
    scat_num int not null,   
    foreign key(scat_num) references Nodejs_TBL(scat_num) on delete cascade on update cascade
);

create table comment_Oracle( # 과목 모든 댓글 모음
	c_num bigint not null primary key auto_increment,
    m_id varchar(30) not null,
    content text not null,
    writeDate date not null default (current_date()),
    scat_num int not null,   
    foreign key(scat_num) references Oracle_TBL(scat_num) on delete cascade on update cascade
);

create table comment_MySQL( # 과목 모든 댓글 모음
	c_num bigint not null primary key auto_increment,
    m_id varchar(30) not null,
    content text not null,
    writeDate date not null default (current_date()),
    scat_num int not null,   
    foreign key(scat_num) references MySQL_TBL(scat_num) on delete cascade on update cascade
);

create table comment_VirtualBox( # 과목 모든 댓글 모음
	c_num bigint not null primary key auto_increment,
    m_id varchar(30) not null,
    content text not null,
    writeDate date not null default (current_date()),
    scat_num int not null,   
    foreign key(scat_num) references VirtualBox_TBL(scat_num) on delete cascade on update cascade
);

create table comment_Github( # 과목 모든 댓글 모음
	c_num bigint not null primary key auto_increment,
    m_id varchar(30) not null,
    content text not null,
    writeDate date not null default (current_date()),
    scat_num int not null,   
    foreign key(scat_num) references Github_TBL(scat_num) on delete cascade on update cascade
);

create table comment_Git( # 과목 모든 댓글 모음
	c_num bigint not null primary key auto_increment,
    m_id varchar(30) not null,
    content text not null,
    writeDate date not null default (current_date()),
    scat_num int not null,   
    foreign key(scat_num) references Git_TBL(scat_num) on delete cascade on update cascade
);

create table comment_알고리즘( # 과목 모든 댓글 모음
	c_num bigint not null primary key auto_increment,
    m_id varchar(30) not null,
    content text not null,
    writeDate date not null default (current_date()),
    scat_num int not null,   
    foreign key(scat_num) references 알고리즘_TBL(scat_num) on delete cascade on update cascade
);

create table comment_OAuth( # 과목 모든 댓글 모음
	c_num bigint not null primary key auto_increment,
    m_id varchar(30) not null,
    content text not null,
    writeDate date not null default (current_date()),
    scat_num int not null,   
    foreign key(scat_num) references OAuth_TBL(scat_num) on delete cascade on update cascade
);

create table comment_intro( # 메인 화면 모든 댓글 모음
	c_num bigint not null primary key auto_increment,
    m_id varchar(30) not null,
    content text not null,
    writeDate date not null default (current_date()),
    scat_num int not null default 0  # 게시글 분류 : 메인 화면에서는 쓸모 없으므로 기본값을 다 0으로 합니다. 어떻게 바뀌든 상관없습니다. 코드에서 통일성을 위해서 일부러 이 필드를 지우지 않았습니다.
);
insert into comment_intro (m_id, content, writeDate) values ('user2', '살어리 살어리랏다 쳥산에 살어리랏다!', '2020-01-13');
insert into comment_intro (m_id, content, writeDate) values ('user3', '멀위랑 다래랑 먹고 쳥산에 살어리랏다!', '2020-01-15');
insert into comment_intro (m_id, content, writeDate) values ('user1', '얄리얄리 얄라셩 얄라리 얄라!', '2020-01-17');
insert into comment_intro (m_id, content, writeDate) values ('user7', '와! 여름이다!', '2020-01-19');
insert into comment_intro (m_id, content, writeDate) values ('user6', '와 종강이다!!', '2020-01-21');

# 조인을 이용한 댓글과 회원 정보 연동
select cc.c_num, cc.m_id, cc.content, cc.writeDate, cc.scat_num, m.m_name, m.m_profile from comment_C cc inner join members m on cc.m_id=m.m_id;

select * from members;
select * from menus;
select * from C_TBL;
select * from comment_C;
select * from comment_intro;
select count(*) from comment_C;