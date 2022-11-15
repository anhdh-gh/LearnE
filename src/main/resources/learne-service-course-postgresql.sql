--
-- PostgreSQL database dump
--

-- Dumped from database version 14.5 (Ubuntu 14.5-2.pgdg20.04+2)
-- Dumped by pg_dump version 15.0

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;

DROP DATABASE IF EXISTS d595gh195uo74p;
--
-- Name: d595gh195uo74p; Type: DATABASE; Schema: -; Owner: -
--

CREATE DATABASE d595gh195uo74p WITH TEMPLATE = template0 ENCODING = 'UTF8' LOCALE_PROVIDER = libc LOCALE = 'en_US.UTF-8';


\connect d595gh195uo74p

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;

--
-- Name: d595gh195uo74p; Type: DATABASE PROPERTIES; Schema: -; Owner: -
--

ALTER DATABASE d595gh195uo74p SET search_path TO '$user', 'public', 'heroku_ext';


\connect d595gh195uo74p

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;

--
-- Name: heroku_ext; Type: SCHEMA; Schema: -; Owner: -
--

CREATE SCHEMA heroku_ext;


--
-- Name: public; Type: SCHEMA; Schema: -; Owner: -
--

-- *not* creating schema, since initdb creates it


SET default_tablespace = '';

SET default_table_access_method = heap;

--
-- Name: answerchoice; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE public.answerchoice (
    id character varying(255) NOT NULL,
    lessonquestionhistoryid character varying(255) NOT NULL,
    answerid character varying(255) NOT NULL,
    createtime timestamp(6) without time zone,
    updatetime timestamp(6) without time zone
);


--
-- Name: chapter; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE public.chapter (
    id bigint NOT NULL,
    courseid character varying(255) NOT NULL,
    name character varying(255) NOT NULL,
    createtime timestamp(6) without time zone,
    updatetime timestamp(6) without time zone
);


--
-- Name: chapter_id_seq; Type: SEQUENCE; Schema: public; Owner: -
--

CREATE SEQUENCE public.chapter_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- Name: chapter_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: -
--

ALTER SEQUENCE public.chapter_id_seq OWNED BY public.chapter.id;


--
-- Name: course; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE public.course (
    id character varying(255) NOT NULL,
    name character varying(255) NOT NULL,
    description text,
    author character varying(255),
    image character varying(255),
    level character varying(255),
    price character varying(255),
    createtime timestamp(6) without time zone,
    updatetime timestamp(6) without time zone
);


--
-- Name: lesson; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE public.lesson (
    id bigint NOT NULL,
    chapterid bigint NOT NULL,
    name character varying(255) NOT NULL,
    duration character varying(255) NOT NULL,
    description text,
    video character varying(255) NOT NULL,
    createtime timestamp(6) without time zone,
    updatetime timestamp(6) without time zone
);


--
-- Name: lesson_id_seq; Type: SEQUENCE; Schema: public; Owner: -
--

CREATE SEQUENCE public.lesson_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- Name: lesson_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: -
--

ALTER SEQUENCE public.lesson_id_seq OWNED BY public.lesson.id;


--
-- Name: lessonexercise; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE public.lessonexercise (
    id bigint NOT NULL,
    lessonid bigint NOT NULL,
    name character varying(255) NOT NULL,
    description text,
    createtime timestamp(6) without time zone,
    updatetime timestamp(6) without time zone
);


--
-- Name: lessonexercise_id_seq; Type: SEQUENCE; Schema: public; Owner: -
--

CREATE SEQUENCE public.lessonexercise_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- Name: lessonexercise_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: -
--

ALTER SEQUENCE public.lessonexercise_id_seq OWNED BY public.lessonexercise.id;


--
-- Name: lessonexercisestatus; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE public.lessonexercisestatus (
    id character varying(255) NOT NULL,
    lessonexerciseid bigint NOT NULL,
    lessonid bigint NOT NULL,
    userid character varying(255) NOT NULL,
    status character varying(255) NOT NULL,
    createtime timestamp(6) without time zone,
    updatetime timestamp(6) without time zone
);


--
-- Name: lessonquestion; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE public.lessonquestion (
    id bigint NOT NULL,
    lessonexerciseid bigint NOT NULL,
    createtime timestamp(6) without time zone,
    updatetime timestamp(6) without time zone,
    questionid character varying(255) NOT NULL,
    score real NOT NULL
);


--
-- Name: lessonquestion_id_seq; Type: SEQUENCE; Schema: public; Owner: -
--

CREATE SEQUENCE public.lessonquestion_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- Name: lessonquestion_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: -
--

ALTER SEQUENCE public.lessonquestion_id_seq OWNED BY public.lessonquestion.id;


--
-- Name: lessonquestionhistory; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE public.lessonquestionhistory (
    id character varying(255) NOT NULL,
    lessonquestionid bigint NOT NULL,
    userid character varying(255) NOT NULL,
    createtime timestamp(6) without time zone,
    updatetime timestamp(6) without time zone,
    score real NOT NULL
);


--
-- Name: lessonstatus; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE public.lessonstatus (
    id character varying(255) NOT NULL,
    lessonid bigint NOT NULL,
    userid character varying(255) NOT NULL,
    status character varying(255) NOT NULL,
    createtime timestamp(6) without time zone,
    updatetime timestamp(6) without time zone
);


--
-- Name: request; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE public.request (
    id character varying(255) NOT NULL,
    courseid character varying(255) NOT NULL,
    text text NOT NULL,
    createtime timestamp(6) without time zone,
    updatetime timestamp(6) without time zone
);


--
-- Name: target; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE public.target (
    id character varying(255) NOT NULL,
    courseid character varying(255) NOT NULL,
    text text NOT NULL,
    createtime timestamp(6) without time zone,
    updatetime timestamp(6) without time zone
);


--
-- Name: chapter id; Type: DEFAULT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.chapter ALTER COLUMN id SET DEFAULT nextval('public.chapter_id_seq'::regclass);


--
-- Name: lesson id; Type: DEFAULT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.lesson ALTER COLUMN id SET DEFAULT nextval('public.lesson_id_seq'::regclass);


--
-- Name: lessonexercise id; Type: DEFAULT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.lessonexercise ALTER COLUMN id SET DEFAULT nextval('public.lessonexercise_id_seq'::regclass);


--
-- Name: lessonquestion id; Type: DEFAULT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.lessonquestion ALTER COLUMN id SET DEFAULT nextval('public.lessonquestion_id_seq'::regclass);


--
-- Data for Name: answerchoice; Type: TABLE DATA; Schema: public; Owner: -
--

COPY public.answerchoice (id, lessonquestionhistoryid, answerid, createtime, updatetime) FROM stdin;
\.


--
-- Data for Name: chapter; Type: TABLE DATA; Schema: public; Owner: -
--

COPY public.chapter (id, courseid, name, createtime, updatetime) FROM stdin;
7	746c8e3a-f39f-4360-9df2-6f396062393a	Knowledge base	2022-10-18 11:13:44.556	\N
8	746c8e3a-f39f-4360-9df2-6f396062393a	Start practicing	2022-10-18 11:13:44.608	\N
9	746c8e3a-f39f-4360-9df2-6f396062393a	Acceleration practice and mock exam	2022-10-18 11:13:44.63	\N
\.


--
-- Data for Name: course; Type: TABLE DATA; Schema: public; Owner: -
--

COPY public.course (id, name, description, author, image, level, price, createtime, updatetime) FROM stdin;
746c8e3a-f39f-4360-9df2-6f396062393a	Toeic english course	<p className='text-gray-500'>Toeic English course from basic to advanced, the result of this course is that you have the knowledge of Toeic English to be able to take the exam and get the highest score possible. <a href='https://ptiteduvn-my.sharepoint.com/personal/anhdh_b18cn010_stu_ptit_edu_vn/_layouts/15/onedrive.aspx?id=%2Fpersonal%2Fanhdh%5Fb18cn010%5Fstu%5Fptit%5Fedu%5Fvn%2FDocuments%2FCac%5Fmon%5Fhoc%2FToeic&ga=1' target="_blank" className='no-underline'>Document</a>.</p>	Kim Dung	https://www.testcenter.vn/blog/wp-content/uploads/2020/11/Banner-002.jpg	Basic	Free	2022-10-18 11:13:44.523	\N
\.


--
-- Data for Name: lesson; Type: TABLE DATA; Schema: public; Owner: -
--

COPY public.lesson (id, chapterid, name, duration, description, video, createtime, updatetime) FROM stdin;
80	8	General review of knowledge	2:06:34	<div className='mt-4 text-base'> <div className="mb-2 text-lg"> <a href='https://ptiteduvn-my.sharepoint.com/:f:/g/personal/anhdh_b18cn010_stu_ptit_edu_vn/EqsjX_xeiKBPqDeqZL7mlOIBHJxSS8BhichsGvUeuK_w4A?e=VRU9ne' target="_blank" className='no-underline'>Homework</a> </div> <div className="flex justify-start mb-2"><span className='me-3'><i className="fa-solid fa-arrows-to-dot"></i></span> Từ vựng unit 19.</div> <div className="flex justify-start mb-2"><span className='me-3'><i className="fa-solid fa-arrows-to-dot"></i></span> Key unit 18: 10-A, 11-C, 12- ,A 13-A, 14-C, 15-B, 16-B, 17-D, 18-A, 19-D, 20-A, 21-B, 22-C, 23-B.</div> <div className="flex justify-start mb-2"><span className='me-3'><i className="fa-solid fa-arrows-to-dot"></i></span> Reading đọc tiếp 186 - 200 test 2.</div> <div className="flex justify-start mb-2"><span className='me-3'><i className="fa-solid fa-arrows-to-dot"></i></span> Listening part 4 test 5.</div> <div className="flex justify-start mb-2"><span className='me-3'><i className="fa-solid fa-arrows-to-dot"></i></span> Xem lại part 3 test 5, key bài 19, 20.</div> <div className="flex justify-start mb-2"><span className='me-3'><i className="fa-solid fa-arrows-to-dot"></i></span> Key 171 - 185 test 2: 171-C, 172-A, 173-C, 174-C, 175-D, 176-B, 177-D, 178-B, 179-C, 180-A, 181-D, 182-D, 183-B, 184-C, 185-A.</div> <div className="flex justify-start mb-2"><span className='me-3'><i className="fa-solid fa-arrows-to-dot"></i></span> Lesson 19: 10-B, 11-B, 12-C, 13-A, 14-C, 15-B, 16-A, 17-D, 18-C, 19-C, 20-D, 21-B, 22-A, 23-C.</div> <div className="flex justify-start mb-2"><span className='me-3'><i className="fa-solid fa-arrows-to-dot"></i></span> Lesson 20: 10-B, 11-A, 12-A, 13-D, 14-D, 15-A, 16-A, 17-A, 18-B, 19-B, 20-C, 21-B, 22-C, 23-A.</div> <div className="flex justify-start mb-2"><span className='me-3'><i className="fa-solid fa-arrows-to-dot"></i></span> Word reivew: 1-A, 2-B, 3-A, 4-A, 5-C, 6-C, 7-B, 8-A, 9-D, 10-B, 11-D, 12-B, 13-A, 14-C, 15-D, 16-B, 17-A, 18-C, 19-D.</div> </div>	https://youtu.be/DuTMXdpGGWc	2022-10-18 11:13:44.628	\N
81	9	Mock exam (1st)	2:33:32	<div className='mt-4 text-base'> <div className="mb-2 text-lg"> <a href='https://ptiteduvn-my.sharepoint.com/:f:/g/personal/anhdh_b18cn010_stu_ptit_edu_vn/EkLdv4mdLa1Hje7jdLBrDvMBfAqhgOAz45ig-Klk1bojUA?e=LZNI0v' target="_blank" className='no-underline'>Homework</a> </div> <div className="flex justify-start mb-2"><span className='me-3'><i className="fa-solid fa-arrows-to-dot"></i></span> Xem lại bài test.</div> <div className="flex justify-start mb-2"><span className='me-3'><i className="fa-solid fa-arrows-to-dot"></i></span> <a href='https://www.facebook.com/groups/351705076734456/' target="_blank" className='no-underline'>Join group</a>.</div> </div>	https://youtu.be/wAWNHx5loCI	2022-10-18 11:13:44.632	\N
61	7	Part of speech	2:09:10	<div className='mt-4 text-base'><div className="mb-2 text-lg"><a href='https://ptiteduvn-my.sharepoint.com/:f:/g/personal/anhdh_b18cn010_stu_ptit_edu_vn/Etp51e5ZJANOivOj2XVMqRwBpLmgnRno77ExQvYGq_9q4w?e=7aO1F7' target="_blank" className='no-underline'>Homework</a></div><div className="flex justify-start mb-2"><span className='me-3'><i className="fa-solid fa-arrows-to-dot"></i></span> Từ mới chủ đề contract quyển 600 từ.</div><div className="flex justify-start mb-2"><span className='me-3'><i className="fa-solid fa-arrows-to-dot"></i></span> Làm bt trang 5,6, dịch part 7 ra vở.</div><div className="flex justify-start mb-2"><span className='me-3'><i className="fa-solid fa-arrows-to-dot"></i></span> Từ loại - Part of speech (sau unit 1) phần từ vựng unit 1-20.</div><div className="flex justify-start mb-2"><span className='me-3'><i className="fa-solid fa-arrows-to-dot"></i></span> Chuẩn bị trước từ mới chủ đề Marketing.</div><div className="flex justify-start mb-2"><span className='me-3'><i className="fa-solid fa-arrows-to-dot"></i></span> Xem lại các thì hiện tại (Present tense).</div></div>	https://youtu.be/mPLXxcSZICI	2022-10-18 11:13:44.583	\N
62	7	Present tense	2:05:04	<div className='mt-4 text-base'><div className="mb-2 text-lg"><a href='https://ptiteduvn-my.sharepoint.com/:f:/g/personal/anhdh_b18cn010_stu_ptit_edu_vn/Ev7Ic_OPLA1MqDrIunHW-q4BNfwlh915PyZnEXZbj3qh2g?e=kAg6wF' target="_blank" className='no-underline'>Homework</a></div><div className="flex justify-start mb-2"><span className='me-3'><i className="fa-solid fa-arrows-to-dot"></i></span> Làm tiếp unit 2 quyển 600 từ.</div><div className="flex justify-start mb-2"><span className='me-3'><i className="fa-solid fa-arrows-to-dot"></i></span> Key unit 1: 10-A, 11-A, 12-B, 13-D, 14-B, 15-D, 16-D, 17-B, 18-D, 19-A, 20-B, 21-C, 22-A, 23-D.</div><div className="flex justify-start mb-2"><span className='me-3'><i className="fa-solid fa-arrows-to-dot"></i></span> Dịch trước unit 3- warranty.</div><div className="flex justify-start mb-2"><span className='me-3'><i className="fa-solid fa-arrows-to-dot"></i></span> Làm BT trang 22 - 23 sách ngữ pháp very easy.</div><div className="flex justify-start mb-2"><span className='me-3'><i className="fa-solid fa-arrows-to-dot"></i></span> Xem trước past tense (thì quá khứ).</div><div className="flex justify-start mb-2"><span className='me-3'><i className="fa-solid fa-arrows-to-dot"></i></span> Nghe tiếp unit 2 Trang 32 - 35 sách nghe (very easy), track 7- 10.</div><div className="flex justify-start mb-2"><span className='me-3'><i className="fa-solid fa-arrows-to-dot"></i></span> Nghe lại và xem key unit 1 track 2-5.</div></div>	https://youtu.be/A1moI-Lf4Co	2022-10-18 11:13:44.587	\N
63	7	Part 1: Describe a photo with a person - Past tense	1:52:23	<div className='mt-4 text-base'> <div className="mb-2 text-lg"> <a href='https://ptiteduvn-my.sharepoint.com/:f:/g/personal/anhdh_b18cn010_stu_ptit_edu_vn/Ei6p7eSLy_VLo33-i-XgRKkBKIX3q2hhA3IIop7AF_6WPA?e=X6Hnmo' target="_blank" className='no-underline'>Homework</a> </div> <div className="flex justify-start mb-2"><span className='me-3'><i className="fa-solid fa-arrows-to-dot"></i></span> Làm tiếp bài từ vựng unit 3.</div> <div className="flex justify-start mb-2"><span className='me-3'><i className="fa-solid fa-arrows-to-dot"></i></span> Key unit 2: 10-C, 11-A, 12-C, 13-A, 14-D, 15-B, 16-C, 17-A, 18-D, 19-B, 20-A, 21-C, 22-D, 23-B.</div> <div className="flex justify-start mb-2"><span className='me-3'><i className="fa-solid fa-arrows-to-dot"></i></span> Ngữ pháp trang 36, 37 sách very easy 3.</div> <div className="flex justify-start mb-2"><span className='me-3'><i className="fa-solid fa-arrows-to-dot"></i></span> Nghe mini test unit 3 trang 46 - 49 sách very easy 4.</div> <div className="flex justify-start mb-2"><span className='me-3'><i className="fa-solid fa-arrows-to-dot"></i></span> Nghe 10 bức tranh tả 1 người (tomato leap).</div> </div>	https://youtu.be/hDsiGIP0ggY	2022-10-18 11:13:44.59	\N
90	9	Advanced Practice 7	1:56:46	<div className='mt-4 text-base'> <div className="mb-2 text-lg"> <a href='https://ptiteduvn-my.sharepoint.com/:f:/g/personal/anhdh_b18cn010_stu_ptit_edu_vn/EogW5Rj8cOdIhVuHU6nVuWgB9aukwXulH8S_7ENqiGzx4g?e=IxDfyg' target="_blank" className='no-underline'>Homework</a> </div> <div className="flex justify-start mb-2"><span className='me-3'><i className="fa-solid fa-arrows-to-dot"></i></span> Reading đọc tiếp test 6.</div> <div className="flex justify-start mb-2"><span className='me-3'><i className="fa-solid fa-arrows-to-dot"></i></span> Nghe lại test 5.</div> <div className="flex justify-start mb-2"><span className='me-3'><i className="fa-solid fa-arrows-to-dot"></i></span> Nghe tiếp test 6.</div> </div>	https://youtu.be/CbyX6DbTKhI	2022-10-18 11:13:44.651	\N
91	9	Advanced Practice 8	27:06	\N	https://youtu.be/juVYVnJoLZ0	2022-10-18 11:13:44.653	\N
64	7	Part 1: Describe a photo with two or more people - Future tense	1:42:11	<div className='mt-4 text-base'> <div className="mb-2 text-lg"> <a href='https://ptiteduvn-my.sharepoint.com/:f:/g/personal/anhdh_b18cn010_stu_ptit_edu_vn/EqHNNFMR5_JHrMJlkb8P778BM6unOrhtZYMKBzLD7e7YYg?e=b4CbUk' target="_blank" className='no-underline'>Homework</a> </div> <div className="flex justify-start mb-2"><span className='me-3'><i className="fa-solid fa-arrows-to-dot"></i></span> Từ vựng unit 4.</div> <div className="flex justify-start mb-2"><span className='me-3'><i className="fa-solid fa-arrows-to-dot"></i></span> Key unit 3: 10-D, 11-B, 12-D, 13-C, 14-A, 15-D, 16-A, 17-B, 18-C, 19-A, 20-D, 21-D, 22-B, 23-A.</div> <div className="flex justify-start mb-2"><span className='me-3'><i className="fa-solid fa-arrows-to-dot"></i></span> Ngữ pháp Verb tense (sau phần từ mới unit 4).</div> <div className="flex justify-start mb-2"><span className='me-3'><i className="fa-solid fa-arrows-to-dot"></i></span> Nghe mini test unit 4 sách very easy.</div> <div className="flex justify-start mb-2"><span className='me-3'><i className="fa-solid fa-arrows-to-dot"></i></span> Nghe 10 bức tranh có 2 người trở lên (tomato leap).</div> </div>	https://youtu.be/Lum10F-cP1Y	2022-10-18 11:13:44.592	\N
65	7	Part 1: Describe the photo of the object or scene - Passive voice	1:59:51	<div className='mt-4 text-base'> <div className="mb-2 text-lg"> <a href='https://ptiteduvn-my.sharepoint.com/:f:/g/personal/anhdh_b18cn010_stu_ptit_edu_vn/EgINd3VeUVRBlevvxK9rs8oB5Wk4rT1FnRUXxhapaTjnzQ?e=ikk3a3' target="_blank" className='no-underline'>Homework</a> </div> <div className="flex justify-start mb-2"><span className='me-3'><i className="fa-solid fa-arrows-to-dot"></i></span> Từ vựng unit 5.</div> <div className="flex justify-start mb-2"><span className='me-3'><i className="fa-solid fa-arrows-to-dot"></i></span> Key unit 4: 10-A, 11-A, 12-C, 13-D, 14-C, 15-A, 16-D, 17-A, 18-C, 19-C, 20-A, 21-B, 22-A, 23-D.</div> <div className="flex justify-start mb-2"><span className='me-3'><i className="fa-solid fa-arrows-to-dot"></i></span> Nghe mini test unit 5.</div> <div className="flex justify-start mb-2"><span className='me-3'><i className="fa-solid fa-arrows-to-dot"></i></span> Nghe 10 bức tranh sự vật, khung cảnh (track 025).</div> <div className="flex justify-start mb-2"><span className='me-3'><i className="fa-solid fa-arrows-to-dot"></i></span> Ngữ pháp câu bi động (sau từ mới bài 5).</div> </div>	https://youtu.be/1fC-g5LKM8Q	2022-10-18 11:13:44.593	\N
67	7	Part 2: Identifying time, identifying people - Pronouns	1:54:50	<div className='mt-4 text-base'> <div className="mb-2 text-lg"> <a href='https://ptiteduvn-my.sharepoint.com/:f:/g/personal/anhdh_b18cn010_stu_ptit_edu_vn/Eu5wQLDYvRBIn3ii3JNo5UIB24P2WJJ1lf_Y-YH4JHP6Bw?e=LpQsvS' target="_blank" className='no-underline'>Homework</a> </div> <div className="flex justify-start mb-2"><span className='me-3'><i className="fa-solid fa-arrows-to-dot"></i></span> Từ vựng unit 7.</div> <div className="flex justify-start mb-2"><span className='me-3'><i className="fa-solid fa-arrows-to-dot"></i></span> Key unit 6: 10-A, 11-B, 12-B, 13-B, 14-B, 15-C, 16-B, 17-A, 18-D, 19-C, 20-C, 21-D, 22-B, 23-A.</div> <div className="flex justify-start mb-2"><span className='me-3'><i className="fa-solid fa-arrows-to-dot"></i></span> Ngữ pháp pronouns (trang kế tiếp sau phần từ mới unit 7).</div> <div className="flex justify-start mb-2"><span className='me-3'><i className="fa-solid fa-arrows-to-dot"></i></span> Nghe mini test unit 7.</div> <div className="flex justify-start mb-2"><span className='me-3'><i className="fa-solid fa-arrows-to-dot"></i></span> Nghe lại câu hỏi when & who (part 2).</div> </div>	https://youtu.be/YEGx4paY63Y	2022-10-18 11:13:44.599	\N
68	7	Part 2: Identifying a opinion, identifying a choice - Relative pronouns	2:00:36	<div className='mt-4 text-base'> <div className="mb-2 text-lg"> <a href='https://ptiteduvn-my.sharepoint.com/:f:/g/personal/anhdh_b18cn010_stu_ptit_edu_vn/EowTsiM1W0ROnHYoHwPdu-8BDzRqPbkoc1379WxxDz-HoQ?e=RMcIUu' target="_blank" className='no-underline'>Homework</a> </div> <div className="flex justify-start mb-2"><span className='me-3'><i className="fa-solid fa-arrows-to-dot"></i></span> Từ vựng unit 8.</div> <div className="flex justify-start mb-2"><span className='me-3'><i className="fa-solid fa-arrows-to-dot"></i></span> Key unit 7: 10-D, 11-C, 12-A, 13-B, 14-D, 15-B, 16-A, 17-C, 18-D, 19-A, 20-C, 21-D, 22-B, 23-A.</div> <div className="flex justify-start mb-2"><span className='me-3'><i className="fa-solid fa-arrows-to-dot"></i></span>Ngữ pháp trang 92 - 93 relative pronouns.</div> <div className="flex justify-start mb-2"><span className='me-3'><i className="fa-solid fa-arrows-to-dot"></i></span> Nghe mini test 4.</div> <div className="flex justify-start mb-2"><span className='me-3'><i className="fa-solid fa-arrows-to-dot"></i></span> Nghe lại câu hỏi opinion, choice (part 2).</div> </div>	https://youtu.be/fYh5IgQuApY	2022-10-18 11:13:44.601	\N
69	7	Part 2: Identifying a suggestion - Conjunctions	2:03:56	<div className='mt-4 text-base'> <div className="mb-2 text-lg"> <a href='https://ptiteduvn-my.sharepoint.com/:f:/g/personal/anhdh_b18cn010_stu_ptit_edu_vn/Et2qLqzIM6xDkiTFZsrcBKkBsVHf-H75N8McoNtuADZWxw?e=MNGkcq' target="_blank" className='no-underline'>Homework</a> </div> <div className="flex justify-start mb-2"><span className='me-3'><i className="fa-solid fa-arrows-to-dot"></i></span> Từ vựng unit 9.</div> <div className="flex justify-start mb-2"><span className='me-3'><i className="fa-solid fa-arrows-to-dot"></i></span> Key unit 8: 10-A, 11-B, 12-B, 13-D, 14-A, 15-B, 16-C, 17-C, 18-B, 19-B, 20-C, 21-B, 22-C, 23-A.</div> <div className="flex justify-start mb-2"><span className='me-3'><i className="fa-solid fa-arrows-to-dot"></i></span>Ngữ pháp liên từ.</div> <div className="flex justify-start mb-2"><span className='me-3'><i className="fa-solid fa-arrows-to-dot"></i></span> Nghe mini test.</div> <div className="flex justify-start mb-2"><span className='me-3'><i className="fa-solid fa-arrows-to-dot"></i></span> Nghe lại câu hỏi suggestion part 2.</div> </div>	https://youtu.be/tt5tniAMpEU	2022-10-18 11:13:44.603	\N
70	7	Part 2: Identifying a reason, identifying a location - Modifiers	1:55:29	<div className='mt-4 text-base'> <div className="mb-2 text-lg"> <a href='https://ptiteduvn-my.sharepoint.com/:f:/g/personal/anhdh_b18cn010_stu_ptit_edu_vn/ErLzjzOZLTdEsDVha_2h3GwBYf1wMthI6nUeZuB55bCrYg' target="_blank" className='no-underline'>Homework</a> </div> <div className="flex justify-start mb-2"><span className='me-3'><i className="fa-solid fa-arrows-to-dot"></i></span> Từ vựng unit 10.</div> <div className="flex justify-start mb-2"><span className='me-3'><i className="fa-solid fa-arrows-to-dot"></i></span> Key unit 9: 10-C, 11-D, 12-B, 13-A, 14-C, 15-A, 16-B, 17-C, 18-C, 19-B, 20-D, 21-B, 22-A, 23-B.</div> <div className="flex justify-start mb-2"><span className='me-3'><i className="fa-solid fa-arrows-to-dot"></i></span>Ngữ pháp modifiers.</div> <div className="flex justify-start mb-2"><span className='me-3'><i className="fa-solid fa-arrows-to-dot"></i></span> Nghe mini test unit 10.</div> <div className="flex justify-start mb-2"><span className='me-3'><i className="fa-solid fa-arrows-to-dot"></i></span> Nghe lại câu hỏi where and why.</div> <div className="flex justify-start mb-2"><span className='me-3'><i className="fa-solid fa-arrows-to-dot"></i></span> Nghe part 2 tổng hợp 25 câu.</div> </div>	https://youtu.be/vNHtWpeh7BQ	2022-10-18 11:13:44.606	\N
71	8	Correct the exercise and practice 1	1:56:54	<div className='mt-4 text-base'> <div className="mb-2 text-lg"> <a href='https://ptiteduvn-my.sharepoint.com/:f:/g/personal/anhdh_b18cn010_stu_ptit_edu_vn/EkKhW5Rd2v5HhNaDEnOHwNMBmdFUW6cFVSn5a2ijQzIdnA?e=htLlWH' target="_blank" className='no-underline'>Homework</a> </div> <div className="flex justify-start mb-2"><span className='me-3'><i className="fa-solid fa-arrows-to-dot"></i></span> Từ vựng unit 11.</div> <div className="flex justify-start mb-2"><span className='me-3'><i className="fa-solid fa-arrows-to-dot"></i></span> Key unit 10: 10-A, 11-B, 12-A, 13-A, 14-B, 15-C, 16-D, 17-C, 18-C, 19-B, 20-D, 21-B, 22-B, 23-C.</div> <div className="flex justify-start mb-2"><span className='me-3'><i className="fa-solid fa-arrows-to-dot"></i></span>Reading 101 - 130 test 1.</div> <div className="flex justify-start mb-2"><span className='me-3'><i className="fa-solid fa-arrows-to-dot"></i></span> Listening part 3 test 1.</div> <div className="flex justify-start mb-2"><span className='me-3'><i className="fa-solid fa-arrows-to-dot"></i></span> Xem lại part 2 đã check trên lớp.</div> </div>	https://youtu.be/2U3J090VNrA	2022-10-18 11:13:44.609	\N
72	8	Correct the exercise and practice 2	1:35:00	<div className='mt-4 text-base'> <div className="mb-2 text-lg"> <a href='https://ptiteduvn-my.sharepoint.com/:f:/g/personal/anhdh_b18cn010_stu_ptit_edu_vn/ErSQ4Osa-MFCsZfaOfJ11SsByBueTGFBU9PeCCOeuYljwQ' target="_blank" className='no-underline'>Homework</a> </div> <div className="flex justify-start mb-2"><span className='me-3'><i className="fa-solid fa-arrows-to-dot"></i></span> Từ vựng unit 12.</div> <div className="flex justify-start mb-2"><span className='me-3'><i className="fa-solid fa-arrows-to-dot"></i></span> Key unit 11: 10-C, 11-A, 12-A, 13-B, 14-D, 15-A, 16-B, 17-D, 18-A, 19-B, 20-B, 21-A, 22-D, 23-C.</div> <div className="flex justify-start mb-2"><span className='me-3'><i className="fa-solid fa-arrows-to-dot"></i></span> Reading đọc tiếp 131 - 150 test 1.</div> <div className="flex justify-start mb-2"><span className='me-3'><i className="fa-solid fa-arrows-to-dot"></i></span> Listening part 4 test 1.</div> <div className="flex justify-start mb-2"><span className='me-3'><i className="fa-solid fa-arrows-to-dot"></i></span> Check lại part 3 test 1.</div> </div>	https://youtu.be/4g0_nkxRppM	2022-10-18 11:13:44.611	\N
73	8	Correct the exercise and practice 3	1:52:28	<div className='mt-4 text-base'> <div className="mb-2 text-lg"> <a href='https://ptiteduvn-my.sharepoint.com/:f:/g/personal/anhdh_b18cn010_stu_ptit_edu_vn/EjQedXLK0RxEl_KkwsYxObsBxfThR1rPluO12iu7XFlI1w?e=p8TDUg' target="_blank" className='no-underline'>Homework</a> </div> <div className="flex justify-start mb-2"><span className='me-3'><i className="fa-solid fa-arrows-to-dot"></i></span> Từ vựng unit 13.</div> <div className="flex justify-start mb-2"><span className='me-3'><i className="fa-solid fa-arrows-to-dot"></i></span> Key unit 12: 10-D, 11-C, 12-A, 13-D, 14-B, 15-A, 16-B, 17-C, 18-D, 19-C, 20-C, 21-D, 22-A, 23-D.</div> <div className="flex justify-start mb-2"><span className='me-3'><i className="fa-solid fa-arrows-to-dot"></i></span> Reading đọc tiếp 151 - 171 test 1.</div> <div className="flex justify-start mb-2"><span className='me-3'><i className="fa-solid fa-arrows-to-dot"></i></span> Listening part 3 test 2.</div> <div className="flex justify-start mb-2"><span className='me-3'><i className="fa-solid fa-arrows-to-dot"></i></span> Check lại part 1, 2, 4 test 1.</div> </div>	https://youtu.be/cUTbU_sN0VM	2022-10-18 11:13:44.613	\N
74	8	Correct the exercise and practice 4	1:58:42	<div className='mt-4 text-base'> <div className="mb-2 text-lg"> <a href='https://ptiteduvn-my.sharepoint.com/:f:/g/personal/anhdh_b18cn010_stu_ptit_edu_vn/Ej-I8aY7cedDutXeFkGEa3UBYEaws0zax8yvxSy5A-8isg?e=uOVkEy' target="_blank" className='no-underline'>Homework</a> </div> <div className="flex justify-start mb-2"><span className='me-3'><i className="fa-solid fa-arrows-to-dot"></i></span> Từ vựng unit 14.</div> <div className="flex justify-start mb-2"><span className='me-3'><i className="fa-solid fa-arrows-to-dot"></i></span> Key unit 13: 10-D, 11-B, 12-C, 13-A, 14-D, 15-B, 16-A, 17-D, 18-D, 19-B, 20-D, 21-A, 22-D, 23-C</div> <div className="flex justify-start mb-2"><span className='me-3'><i className="fa-solid fa-arrows-to-dot"></i></span> Reading đọc Reading đọc tiếp 172 - 185 test 1.</div> <div className="flex justify-start mb-2"><span className='me-3'><i className="fa-solid fa-arrows-to-dot"></i></span> Listening part 4 test 2.</div> <div className="flex justify-start mb-2"><span className='me-3'><i className="fa-solid fa-arrows-to-dot"></i></span> Key 101 - 171 test 1: 101-A, 102-D, 103-D, 104-D, 105-A, 106-B, 107-C, 108-D, 109-D, 110-A, 111-B, 112-B, 113-C, 114-B, 115-D, 116-B, 117-C, 118-C, 119-C, 120-A, 121-A, 122-C, 123-D, 124-A, 125-C, 126-D, 127-B, 128-B, 129-C, 130-B, 131-D, 132-B, 133-A, 134-D, 135-A, 136-B, 137-B, 138-C, 139-B, 140-D, 141-A, 142-B, 143-D, 144-A, 145-B, 146-C, 147-B, 148-C, 149-B, 150-D, 151-B, 152-C, 153-A, 154-D, 155-D, 156-A, 157-B, 158-A, 159-C, 160-A, 161-D, 162-A, 163-D, 164-A, 165-C, 166-B, 167-C, 168-A, 169-B, 170-C, 171-A.</div> </div>	https://youtu.be/cJjIg2gKfBs	2022-10-18 11:13:44.616	\N
82	9	Advanced Practice 1	1:55:47	<div className='mt-4 text-base'> <div className="mb-2 text-lg"> <a href='https://ptiteduvn-my.sharepoint.com/:f:/g/personal/anhdh_b18cn010_stu_ptit_edu_vn/EkG4swa-53dJqpaDPZXJnJMBt362Nkwbz9HnpSAihrJ-GA?e=GAYFHN' target="_blank" className='no-underline'>Homework</a> </div> <div className="flex justify-start mb-2"><span className='me-3'><i className="fa-solid fa-arrows-to-dot"></i></span> Reading đọc tiếp 101 - 131 test 3.</div> <div className="flex justify-start mb-2"><span className='me-3'><i className="fa-solid fa-arrows-to-dot"></i></span> Nghe part 3 test 1 bộ mới.</div> <div className="flex justify-start mb-2"><span className='me-3'><i className="fa-solid fa-arrows-to-dot"></i></span> Xem lại part 1, 2, 4 test 5 bộ cũ.</div> <div className="flex justify-start mb-2"><span className='me-3'><i className="fa-solid fa-arrows-to-dot"></i></span> Key 186 - 200 test 1: 186-B, 187-A, 188-C, 189-D, 190-B, 191-D, 192-C, 193-A, 194-B, 195-D, 196-C, 197-B, 198-A, 199-D, 200-D.</div> </div>	https://youtu.be/3KYc-JJx_Tw	2022-10-18 11:13:44.634	\N
75	8	Correct the exercise and practice 5	1:48:48	<div className='mt-4 text-base'> <div className="mb-2 text-lg"> <a href='https://ptiteduvn-my.sharepoint.com/:f:/g/personal/anhdh_b18cn010_stu_ptit_edu_vn/EqhUBgp2U49NpsW25FIMG0QBeBNGG1spSPPcyodffB8IiA?e=h57HGK' target="_blank" className='no-underline'>Homework</a> </div> <div className="flex justify-start mb-2"><span className='me-3'><i className="fa-solid fa-arrows-to-dot"></i></span> Từ vựng unit 15.</div> <div className="flex justify-start mb-2"><span className='me-3'><i className="fa-solid fa-arrows-to-dot"></i></span> Key unit 14: 10-A, 11-D, 12-B, 13-B, 14-C, 15-D, 16-A, 17-C, 18-C, 19-C, 20-B, 21-A, 22-D, 21-A.</div> <div className="flex justify-start mb-2"><span className='me-3'><i className="fa-solid fa-arrows-to-dot"></i></span> Reading đọc tiếp 186 - 200 test 1.</div> <div className="flex justify-start mb-2"><span className='me-3'><i className="fa-solid fa-arrows-to-dot"></i></span> Listening part 3 test 3.</div> <div className="flex justify-start mb-2"><span className='me-3'><i className="fa-solid fa-arrows-to-dot"></i></span> Nghe lai part 1, 2, 4 test 2.</div> <div className="flex justify-start mb-2"><span className='me-3'><i className="fa-solid fa-arrows-to-dot"></i></span> Key 172 - 185 test 1: 172-B, 173-C, 174-B, 175-D, 176-C, 177-B, 178-C, 179-D, 180-A, 181-C, 182-B, 183-A, 184-B, 185-C.</div> </div>	https://youtu.be/S1YjiTxVIE8	2022-10-18 11:13:44.618	\N
76	8	Correct the exercise and practice 6	2:02:25	<div className='mt-4 text-base'> <div className="mb-2 text-lg"> <a href=' https://ptiteduvn-my.sharepoint.com/:f:/g/personal/anhdh_b18cn010_stu_ptit_edu_vn/EqweQgqI-8pCo6c0nHRaEMcB3AiRHE3rFwEZjwXSqUX-RQ?e=9gPgcR' target="_blank" className='no-underline'>Homework</a> </div> <div className="flex justify-start mb-2"><span className='me-3'><i className="fa-solid fa-arrows-to-dot"></i></span> Từ vựng unit 16.</div> <div className="flex justify-start mb-2"><span className='me-3'><i className="fa-solid fa-arrows-to-dot"></i></span> Key unit 15: 10-C, 11-C, 12-C, 13-C, 14-B, 15-A, 16-D, 17-B, 18-A, 19-C, 20-D, 21-B, 22-A, 23-B.</div> <div className="flex justify-start mb-2"><span className='me-3'><i className="fa-solid fa-arrows-to-dot"></i></span> Reading đọc tiếp 101 - 130 test 2.</div> <div className="flex justify-start mb-2"><span className='me-3'><i className="fa-solid fa-arrows-to-dot"></i></span> Listening part 1, 2 test 3.</div> <div className="flex justify-start mb-2"><span className='me-3'><i className="fa-solid fa-arrows-to-dot"></i></span> Xem lại part 3 test 3 và xem lại part 5 test 3.</div> <div className="flex justify-start mb-2"><span className='me-3'><i className="fa-solid fa-arrows-to-dot"></i></span> Key 186 - 200 test 1: 180-A, 181-C, 182-B, 183-A, 184-B, 185-C, 186-B, 187-C, 188-A, 189-D, 190-A, 191-B, 192-C, 193-A, 194-D, 195-D, 196-A, 197-C, 198-B, 199-C, 200-D.</div> <div className="flex justify-start mb-2"><span className='me-3'><i className="fa-solid fa-arrows-to-dot"></i></span> Key 101 - 130 test 3: 101-B, 102-A, 103-A, 104-C, 105-D, 106-C, 107-A, 108-C, 109-B, 110-A, 111-B, 112-A, 113-A, 114-D, 115-C, 116-D, 117-C, 118-D, 119-D, 120-C, 121-D, 122-C, 123-A, 124-B, 125-B, 126-D, 127-D, 128-B, 129-D, 130-A.</div> </div>	https://youtu.be/urLZJuBQG1o	2022-10-18 11:13:44.62	\N
77	8	Correct the exercise and practice 7	1:46:38	<div className='mt-4 text-base'> <div className="mb-2 text-lg"> <a href='https://ptiteduvn-my.sharepoint.com/:f:/g/personal/anhdh_b18cn010_stu_ptit_edu_vn/EkBWQoViy0VGpxV4mSBao1sBvYycQcdrf23v0ZyufbVSKQ?e=a8Wohn' target="_blank" className='no-underline'>Homework</a> </div> <div className="flex justify-start mb-2"><span className='me-3'><i className="fa-solid fa-arrows-to-dot"></i></span> Từ vựng unit 17.</div> <div className="flex justify-start mb-2"><span className='me-3'><i className="fa-solid fa-arrows-to-dot"></i></span> Key unit 16: 10-C, 11-A, 12-D, 13-A, 14-C, 15-C, 16-C, 17-C, 18-D, 19-C, 20-B, 21-A, 22-D, 23-A.</div> <div className="flex justify-start mb-2"><span className='me-3'><i className="fa-solid fa-arrows-to-dot"></i></span> Reading đọc tiếp 131 - 151 test 2.</div> <div className="flex justify-start mb-2"><span className='me-3'><i className="fa-solid fa-arrows-to-dot"></i></span> Listening part 3 test 4.</div> <div className="flex justify-start mb-2"><span className='me-3'><i className="fa-solid fa-arrows-to-dot"></i></span> Xem lại key part 1, 2, 4 test 3.</div> <div className="flex justify-start mb-2"><span className='me-3'><i className="fa-solid fa-arrows-to-dot"></i></span> Key 101 - 131: 101-A, 102-C, 103-A, 104-D, 105-A, 106-D, 107-A, 108-B, 109-B, 110-A, 111-C, 112-B, 113-D, 114-C, 115-B, 116-D, 117-A, 118-B, 119-C, 120-D, 121-A, 122-C, 123-B, 124-A, 125-C, 126-B, 127-B, 128-A, 129-D, 130-C.</div> </div>	https://youtu.be/s76GBbXHpfE	2022-10-18 11:13:44.622	\N
78	8	Correct the exercise and practice 8	2:06:35	<div className='mt-4 text-base'> <div className="mb-2 text-lg"> <a href='https://ptiteduvn-my.sharepoint.com/:f:/g/personal/anhdh_b18cn010_stu_ptit_edu_vn/Em15IoErbkVEjVaRtcqIzZgBiE-zYzay_Xw8sCoTd4YNxA?e=1tmMLI' target="_blank" className='no-underline'>Homework</a> </div> <div className="flex justify-start mb-2"><span className='me-3'><i className="fa-solid fa-arrows-to-dot"></i></span> Từ vựng unit 18.</div> <div className="flex justify-start mb-2"><span className='me-3'><i className="fa-solid fa-arrows-to-dot"></i></span> Key unit 17: 10-C, 11-C, 12-C, 13-C, 14-B, 15-A, 16-B, 17-D, 18-B, 19-A, 20-C, 21-D, 22-A, 23-D.</div> <div className="flex justify-start mb-2"><span className='me-3'><i className="fa-solid fa-arrows-to-dot"></i></span> Reading đọc tiếp 152 - 171 test 2.</div> <div className="flex justify-start mb-2"><span className='me-3'><i className="fa-solid fa-arrows-to-dot"></i></span> Listening nghe tiếp part 1, 2 test 4.</div> <div className="flex justify-start mb-2"><span className='me-3'><i className="fa-solid fa-arrows-to-dot"></i></span> Xem lại key part 3 test 4, part 5.</div> <div className="flex justify-start mb-2"><span className='me-3'><i className="fa-solid fa-arrows-to-dot"></i></span> Key 131 - 151 test 2: 131-C, 132-D, 133-A, 134-B, 135-D, 136-D, 137-B, 138-A, 139-B, 140-A, 141-B, 142-D, 143-C, 144-C, 145-A, 146-B, 147-B, 148-D, 149-B, 150-C, 151-D.</div> </div>	https://youtu.be/xIaIkdo8vt8	2022-10-18 11:13:44.624	\N
79	8	Correct the exercise and practice 9	2:00:27	<div className='mt-4 text-base'> <div className="mb-2 text-lg"> <a href='https://ptiteduvn-my.sharepoint.com/:f:/g/personal/anhdh_b18cn010_stu_ptit_edu_vn/EmfmZOfYf8ZApEFqgQ441LkBukJt6Ti_GGJtcr81UqHfRw?e=5jYUX6' target="_blank" className='no-underline'>Homework</a> </div> <div className="flex justify-start mb-2"><span className='me-3'><i className="fa-solid fa-arrows-to-dot"></i></span> Từ vựng unit 19.</div> <div className="flex justify-start mb-2"><span className='me-3'><i className="fa-solid fa-arrows-to-dot"></i></span> Key unit 18: 10-A, 11-C, 12- ,A 13-A, 14-C, 15-B, 16-B, 17-D, 18-A, 19-D, 20-A, 21-B, 22-C, 23-B.</div> <div className="flex justify-start mb-2"><span className='me-3'><i className="fa-solid fa-arrows-to-dot"></i></span> Reading 171 - 185 test 2.</div> <div className="flex justify-start mb-2"><span className='me-3'><i className="fa-solid fa-arrows-to-dot"></i></span> Listening part 3 test 5.</div> <div className="flex justify-start mb-2"><span className='me-3'><i className="fa-solid fa-arrows-to-dot"></i></span> Xem lại part 1, 2, 4 test 4.</div> <div className="flex justify-start mb-2"><span className='me-3'><i className="fa-solid fa-arrows-to-dot"></i></span> Key 151 - 171 test 2: 151-D, 152-D, 153-D, 154-B, 155-A, 156-B, 157-A, 158-B, 159-B, 160-C, 161-A, 162-C, 163-B, 164-B, 165-D, 166-D, 167-A, 168-D, 169-C, 170-A, 171-C.</div> </div>	https://youtu.be/KpN5W-I4LgQ	2022-10-18 11:13:44.626	\N
83	9	Advanced Practice 2	1:51:16	<div className='mt-4 text-base'> <div className="mb-2 text-lg"> <a href='https://ptiteduvn-my.sharepoint.com/:f:/g/personal/anhdh_b18cn010_stu_ptit_edu_vn/EscwXNh2_SlNqnzM62TfoXgBnPDm0bmWeTfuduhF8r9E5w?e=fveeU6' target="_blank" className='no-underline'>Homework</a> </div> <div className="flex justify-start mb-2"><span className='me-3'><i className="fa-solid fa-arrows-to-dot"></i></span> Reading đọc tiếp 131 - 160 test 3.</div> <div className="flex justify-start mb-2"><span className='me-3'><i className="fa-solid fa-arrows-to-dot"></i></span> Nghe part 1, 2 test 1 bộ mới.</div> <div className="flex justify-start mb-2"><span className='me-3'><i className="fa-solid fa-arrows-to-dot"></i></span> Xem lại part 3, 4 test 1, part 5 test 4.</div> <div className="flex justify-start mb-2"><span className='me-3'><i className="fa-solid fa-arrows-to-dot"></i></span> Key 101 - 130 test 3: 101-C, 102-A, 103-B, 104-D, 105-B, 106-A, 107-A, 108-B, 109-D, 110-D, 111-A, 112-D, 113-C, 114-D, 115-B, 116-B, 117-A, 118-C, 119-A, 120-D, 121-C, 122-C, 123-D, 124-B, 125-A, 126-B, 127-C, 128-B, 129-D, 130-C. </div> </div>	https://youtu.be/6EuxnQLcy8w	2022-10-18 11:13:44.636	\N
84	9	Advanced Practice 3	1:49:59	<div className='mt-4 text-base'> <div className="mb-2 text-lg"> <a href='https://ptiteduvn-my.sharepoint.com/:f:/g/personal/anhdh_b18cn010_stu_ptit_edu_vn/Erc8uhtY2q1HoeF_7j8NYmAB4qWintWEyi1nuKTo3bq1ew?e=xS5Hgg' target="_blank" className='no-underline'>Homework</a> </div> <div className="flex justify-start mb-2"><span className='me-3'><i className="fa-solid fa-arrows-to-dot"></i></span> Reading đọc tiếp 161 - 185 test 3.</div> <div className="flex justify-start mb-2"><span className='me-3'><i className="fa-solid fa-arrows-to-dot"></i></span> Nghe part 4 test 2.</div> <div className="flex justify-start mb-2"><span className='me-3'><i className="fa-solid fa-arrows-to-dot"></i></span> Xem lại part 5 test 3, part 1, 2 test 1 và part 3 test 2 đã chữa.</div> <div className="flex justify-start mb-2"><span className='me-3'><i className="fa-solid fa-arrows-to-dot"></i></span> Key 131 - 160 test 3: 131-C, 132-A, 133-A, 134-D, 135-D, 136-A, 137-B, 138-C, 139-D, 140-C, 141-D, 142-A, 143-D, 144-A, 145-D, 146-B, 147-C, 148-B, 149-B, 150-C, 151-C, 152-A, 153-B, 154-A, 155-B, 156-A, 157-D, 158-B, 159-C, 160-A.</div> </div>	https://youtu.be/8GSqo1SDA1Y	2022-10-18 11:13:44.638	\N
85	9	Advanced Practice 4	1:55:25	<div className='mt-4 text-base'> <div className="mb-2 text-lg"> <a href='https://ptiteduvn-my.sharepoint.com/:f:/g/personal/anhdh_b18cn010_stu_ptit_edu_vn/Etfm5nf2xPlEjs9EPEQQYv0BGNH6DUiNxPTZWX9BISwPRw?e=ZtWDeI' target="_blank" className='no-underline'>Homework</a> </div> <div className="flex justify-start mb-2"><span className='me-3'><i className="fa-solid fa-arrows-to-dot"></i></span> Reading đọc tiếp 186 - 200 test 3.</div> <div className="flex justify-start mb-2"><span className='me-3'><i className="fa-solid fa-arrows-to-dot"></i></span> Xem lại part 5 - test 7.</div> <div className="flex justify-start mb-2"><span className='me-3'><i className="fa-solid fa-arrows-to-dot"></i></span> Nghe lại part 1, 2, 4 test 2.</div> <div className="flex justify-start mb-2"><span className='me-3'><i className="fa-solid fa-arrows-to-dot"></i></span> Key 161 - 185 test 3: 161-C, 162-D, 163-A, 164-B, 165-C, 166.B 167-A, 168-B, 169-A, 170-C, 171-D, 172.A 173-D, 174-C, 175-A, 176-A, 177-C, 178.B 179-D, 180-B, 181-D, 182-B, 183-A, 184-A, 185-B. </div> </div>	https://youtu.be/TXudGbrwkhE	2022-10-18 11:13:44.64	\N
86	9	Mock exam (2st)	2:27:32	<div className='mt-4 text-base'> <div className="mb-2 text-lg"> <a href='https://ptiteduvn-my.sharepoint.com/:f:/g/personal/anhdh_b18cn010_stu_ptit_edu_vn/EjG0k0etZv1Gma0a9JjTysABUdGtSrUM98xwNFXptx_ocA?e=Kpab1Y' target="_blank" className='no-underline'>Homework</a> </div> <div className="flex justify-start mb-2"><span className='me-3'><i className="fa-solid fa-arrows-to-dot"></i></span> Xem lại bài test.</div> </div>	https://youtu.be/z8sqwD0K7Ys	2022-10-18 11:13:44.642	\N
87	9	Advanced Practice 5	1:45:34	<div className='mt-4 text-base'> <div className="mb-2 text-lg"> <a href='https://ptiteduvn-my.sharepoint.com/:f:/g/personal/anhdh_b18cn010_stu_ptit_edu_vn/EhgghWp-MIJMgl98XRcXT_MBoX7H4NU2_sfeqOjMONH3EQ?e=FGHAgJ' target="_blank" className='no-underline'>Homework</a> </div> <div className="flex justify-start mb-2"><span className='me-3'><i className="fa-solid fa-arrows-to-dot"></i></span> Reading đọc tiếp 101 - 146 test 5.</div> <div className="flex justify-start mb-2"><span className='me-3'><i className="fa-solid fa-arrows-to-dot"></i></span> Xem lại part 5 test 9, part 3 test 3.</div> <div className="flex justify-start mb-2"><span className='me-3'><i className="fa-solid fa-arrows-to-dot"></i></span> Nghe part 4 test 3.</div> <div className="flex justify-start mb-2"><span className='me-3'><i className="fa-solid fa-arrows-to-dot"></i></span> <a href='https://drive.google.com/drive/mobile/folders/1WxNftKhVDhkt38Z15s76WZr2Qp2gE-0U?fbclid=IwAR2x9Pu6NalBvINtZtTcOBUoxxoMuh3C0itZppthETKIOY-SXQQ8LIKBhC5' target="_blank" className='no-underline'>Bộ đề ôn tập</a> </div> <div className="flex justify-start mb-2"><span className='me-3'><i className="fa-solid fa-arrows-to-dot"></i></span> Key 186 - 200 test 3: 186-A, 187-C, 188-C, 189-D, 190-B, 191-B, 192-D, 193-A, 194-C, 195-C, 196-D, 197-B, 198-A, 199-C, 200-C.</div> </div>	https://youtu.be/6zrGwyfQV_g	2022-10-18 11:13:44.644	\N
88	9	Mock exam (3st)	1:43:26	<div className='mt-4 text-base'> <div className="mb-2 text-lg"> <a href='https://ptiteduvn-my.sharepoint.com/:f:/g/personal/anhdh_b18cn010_stu_ptit_edu_vn/Eu8XyjepKCFIjRbFKd3ijd8Btmpst6JHaI1DaYzigkHY0A?e=kBAb8F' target="_blank" className='no-underline'>Homework</a> </div> <div className="flex justify-start mb-2"><span className='me-3'><i className="fa-solid fa-arrows-to-dot"></i></span> Reading đọc tiếp 147 - 167 test 5.</div> <div className="flex justify-start mb-2"><span className='me-3'><i className="fa-solid fa-arrows-to-dot"></i></span> Xem lại part 5 test 10, part 1, 2, 4 test 3.</div> <div className="flex justify-start mb-2"><span className='me-3'><i className="fa-solid fa-arrows-to-dot"></i></span> Nghe tiếp part 4 test 4.</div> <div className="flex justify-start mb-2"><span className='me-3'><i className="fa-solid fa-arrows-to-dot"></i></span> Key 101 - 146 test 5: 101-C, 102.D 103-D, 104-A, 105-C, 106.C 107-D, 108-A, 109-B, 110-B, 111-C, 112.C 113-D, 114-D, 115-B, 116-C, 117-D, 118.A 119-C, 120-A, 121-D, 122-C, 123-B, 124-A, 125-B, 126-A, 127-B, 128-B, 129-B, 130-C, 131-B, 132-C, 133-D, 134-A, 135-C, 136-A, 137-C, 138-B, 139-C, 140-C, 141-B, 142-D, 143-B, 144-D, 145-A, 146-D.</div> </div>	https://youtu.be/c09ojQL86aw	2022-10-18 11:13:44.646	\N
89	9	Advanced Practice 6	2:02:22	<div className='mt-4 text-base'> <div className="mb-2 text-lg"> <a href='https://ptiteduvn-my.sharepoint.com/:f:/g/personal/anhdh_b18cn010_stu_ptit_edu_vn/Ek8HTRV126VFoZddNez80bwB7T6K4EqtNJcRebWSsjWV1A?e=LI62JY' target="_blank" className='no-underline'>Homework</a> </div> <div className="flex justify-start mb-2"><span className='me-3'><i className="fa-solid fa-arrows-to-dot"></i></span> Reading đọc tiếp 168 - 200 test 5.</div> <div className="flex justify-start mb-2"><span className='me-3'><i className="fa-solid fa-arrows-to-dot"></i></span> Xem lại part 3, 4 test 4, part 5 test 1.</div> <div className="flex justify-start mb-2"><span className='me-3'><i className="fa-solid fa-arrows-to-dot"></i></span> Nghe part 1, 2 test 4.</div> <div className="flex justify-start mb-2"><span className='me-3'><i className="fa-solid fa-arrows-to-dot"></i></span> Key 147 - 167 test 5: 147-B, 148-C, 149-B, 150-A, 151-A, 152-D, 153-A, 154-C, 155-B, 156-C, 157-C, 158-B, 159-A, 160-D, 161-C, 162-D, 163-B, 164-D, 165-D, 166-B, 167-A.</div> <div className="flex justify-start mb-2"><span className='me-3'><i className="fa-solid fa-arrows-to-dot"></i></span> Key part 5 test 1: 101-B, 102-D, 103-B, 104-A, 105-D, 106-B, 107-C, 108-B, 109-D, 110-C, 111-B, 112-B, 113-A, 114-B, 115-A, 116-A, 117-D, 118-D, 119-C, 120-A, 121-B, 122-A, 123-D, 124-D, 125-B, 126-A, 127-B, 128-A, 129-B, 130-C.</div> </div>	https://youtu.be/1P-p8-JCZUA	2022-10-18 11:13:44.648	\N
92	9	Mock exam (4st)	2:26:39	<div className='mt-4 text-base'> <div className="mb-2 text-lg"> <a href='https://ptiteduvn-my.sharepoint.com/:f:/g/personal/anhdh_b18cn010_stu_ptit_edu_vn/Ern_vBb0eCBOg79ENamwPfUB2K1nMp-4TlrowcuVt81KHw?e=w1BxS10' target="_blank" className='no-underline'>Homework</a> </div> <div className="flex justify-start mb-2"><span className='me-3'><i className="fa-solid fa-arrows-to-dot"></i></span> Xem lại bài test.</div> </div>	https://youtu.be/A9BBrHvXt54	2022-10-18 11:13:44.655	\N
66	7	Ving and Vinf	2:05:55	<div className='mt-4 text-base'> <div className="mb-2 text-lg"> <a href='https://ptiteduvn-my.sharepoint.com/:f:/g/personal/anhdh_b18cn010_stu_ptit_edu_vn/EuxWKJilwOpIq_D4H0Nn_3MBan14vGA4weFweRAgVnBs5A?e=GgXJFd' target="_blank" className='no-underline'>Homework</a> </div> <div className="flex justify-start mb-2"><span className='me-3'><i className="fa-solid fa-arrows-to-dot"></i></span> Từ vựng unit 6.</div> <div className="flex justify-start mb-2"><span className='me-3'><i className="fa-solid fa-arrows-to-dot"></i></span> Key unit 5: 10-B, 11-C, 12-B, 13-A, 14-A, 15-D, 16-A, 17-D, 18-C, 19-B, 20-B, 21-C, 22-D, 23-A.</div> <div className="flex justify-start mb-2"><span className='me-3'><i className="fa-solid fa-arrows-to-dot"></i></span> Ngữ pháp - Ving and Vinf trang 45-46 3.</div> <div className="flex justify-start mb-2"><span className='me-3'><i className="fa-solid fa-arrows-to-dot"></i></span> Mini test unit 6.</div> <div className="flex justify-start mb-2"><span className='me-3'><i className="fa-solid fa-arrows-to-dot"></i></span> Nghe lại các phần tranh.</div> </div>	https://youtu.be/KrA1PV0PsWI	2022-10-18 11:13:44.596	\N
\.


--
-- Data for Name: lessonexercise; Type: TABLE DATA; Schema: public; Owner: -
--

COPY public.lessonexercise (id, lessonid, name, description, createtime, updatetime) FROM stdin;
\.


--
-- Data for Name: lessonexercisestatus; Type: TABLE DATA; Schema: public; Owner: -
--

COPY public.lessonexercisestatus (id, lessonexerciseid, lessonid, userid, status, createtime, updatetime) FROM stdin;
\.


--
-- Data for Name: lessonquestion; Type: TABLE DATA; Schema: public; Owner: -
--

COPY public.lessonquestion (id, lessonexerciseid, createtime, updatetime, questionid, score) FROM stdin;
\.


--
-- Data for Name: lessonquestionhistory; Type: TABLE DATA; Schema: public; Owner: -
--

COPY public.lessonquestionhistory (id, lessonquestionid, userid, createtime, updatetime, score) FROM stdin;
\.


--
-- Data for Name: lessonstatus; Type: TABLE DATA; Schema: public; Owner: -
--

COPY public.lessonstatus (id, lessonid, userid, status, createtime, updatetime) FROM stdin;
e13b4eb7-ffd5-4df2-b196-be51295d5ab8	64	9q7TldOr3SeHswDjvcZjOVityUH2	PROCESSING	2022-11-02 17:42:07.226	\N
6f510511-a9b8-41ca-baee-556cef2cbc62	63	9q7TldOr3SeHswDjvcZjOVityUH2	PROCESSING	2022-11-02 17:42:55.104	\N
cd4dc136-095b-46d4-bb91-d2b3daecd04e	82	9q7TldOr3SeHswDjvcZjOVityUH2	PROCESSING	2022-11-02 17:43:14.371	\N
add1262b-eebb-49bf-808e-916338f198ae	62	9q7TldOr3SeHswDjvcZjOVityUH2	FINISHED	2022-11-02 17:41:45.882	2022-11-02 17:47:02.825
de8d3df0-09ff-41b3-86c1-ae6165951e45	61	9q7TldOr3SeHswDjvcZjOVityUH2	FINISHED	2022-11-02 17:41:25.215	2022-11-02 17:51:36.72
8639f679-5525-44da-9797-755906318973	61	Dxd0gFz1f0RLPVKmkpwc7i1bfXx1	FINISHED	2022-11-04 02:53:48.875	2022-11-04 02:54:50.131
c572737e-b77d-4c75-bd5a-492c7ea03e2a	63	G1fQ7gQeBXbMvx2OsCZlhnNdpfL2	PROCESSING	2022-11-06 09:58:52.709	\N
ea95e449-1e62-4824-9a74-2b4d5a67a09d	61	YE0vynGVmhflMIzrO8VaSwMSXgN2	PROCESSING	2022-11-06 14:33:12.99	\N
9892d688-ed57-4f38-bd58-84ced63cee5e	62	YE0vynGVmhflMIzrO8VaSwMSXgN2	PROCESSING	2022-11-07 15:02:35.006	\N
a9b2f02a-df5f-4ae0-9416-826eadbec241	63	YE0vynGVmhflMIzrO8VaSwMSXgN2	PROCESSING	2022-11-07 15:02:50.797	\N
0a5494d0-362d-43c4-876d-0014cf0f822a	61	ijee4aPmp7UYzJPsDM0R5UQdIQ22	PROCESSING	2022-11-07 23:39:23.302	\N
53a43770-8fb4-4b06-bb51-c3b4c913d4c3	64	YE0vynGVmhflMIzrO8VaSwMSXgN2	PROCESSING	2022-11-13 15:43:47.985	\N
\.


--
-- Data for Name: request; Type: TABLE DATA; Schema: public; Owner: -
--

COPY public.request (id, courseid, text, createtime, updatetime) FROM stdin;
9e145479-3427-410a-b273-9928f25ef814	746c8e3a-f39f-4360-9df2-6f396062393a	Chủ động học tập và làm theo sự hướng dẫn	2022-10-18 11:13:44.657	\N
02c378ed-9c55-46d8-b68b-85bcfa313cd7	746c8e3a-f39f-4360-9df2-6f396062393a	Cần có nền tảng tiếng anh căn bản	2022-10-18 11:13:44.657	\N
8beb46cc-76d8-4e92-a527-2f08ecc5bc64	746c8e3a-f39f-4360-9df2-6f396062393a	Tích cực trao đổi với cố giáo và các học viên khác	2022-10-18 11:13:44.657	\N
ed2054cd-e54b-4592-b0ae-d515cdd46514	746c8e3a-f39f-4360-9df2-6f396062393a	Làm các bài tập đầy đủ	2022-10-18 11:13:44.657	\N
\.


--
-- Data for Name: target; Type: TABLE DATA; Schema: public; Owner: -
--

COPY public.target (id, courseid, text, createtime, updatetime) FROM stdin;
13023aec-f17a-4eb7-b31e-626b86a155ff	746c8e3a-f39f-4360-9df2-6f396062393a	Mất gốc lấy lại căn bản, tạo cảm hứng	2022-10-18 11:13:44.657	\N
be63d75e-093a-40a0-af1f-ccae0400614c	746c8e3a-f39f-4360-9df2-6f396062393a	Học cách quản lý thời gian, phân tích đề, tìm hiểu thói quen ra đề của Toeic.	2022-10-18 11:13:44.657	\N
3a2b36a3-1817-4ffb-87aa-fa145e80c651	746c8e3a-f39f-4360-9df2-6f396062393a	Tăng ít nhất 50-100 điểm so với điểm ban đầu.	2022-10-18 11:13:44.657	\N
674751b9-b056-4b84-b705-e75743e8e5cb	746c8e3a-f39f-4360-9df2-6f396062393a	Mở rộng kiến thức, hướng dẫn các tài liệu sát với đề thi nhất.	2022-10-18 11:13:44.658	\N
\.


--
-- Name: chapter_id_seq; Type: SEQUENCE SET; Schema: public; Owner: -
--

SELECT pg_catalog.setval('public.chapter_id_seq', 57, true);


--
-- Name: lesson_id_seq; Type: SEQUENCE SET; Schema: public; Owner: -
--

SELECT pg_catalog.setval('public.lesson_id_seq', 572, true);


--
-- Name: lessonexercise_id_seq; Type: SEQUENCE SET; Schema: public; Owner: -
--

SELECT pg_catalog.setval('public.lessonexercise_id_seq', 1, false);


--
-- Name: lessonquestion_id_seq; Type: SEQUENCE SET; Schema: public; Owner: -
--

SELECT pg_catalog.setval('public.lessonquestion_id_seq', 1, false);


--
-- Name: answerchoice answerchoice_pkey; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.answerchoice
    ADD CONSTRAINT answerchoice_pkey PRIMARY KEY (id);


--
-- Name: chapter chapter_pkey; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.chapter
    ADD CONSTRAINT chapter_pkey PRIMARY KEY (id);


--
-- Name: course course_pkey; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.course
    ADD CONSTRAINT course_pkey PRIMARY KEY (id);


--
-- Name: lesson lesson_pkey; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.lesson
    ADD CONSTRAINT lesson_pkey PRIMARY KEY (id);


--
-- Name: lessonexercise lessonexercise_pkey; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.lessonexercise
    ADD CONSTRAINT lessonexercise_pkey PRIMARY KEY (id);


--
-- Name: lessonexercisestatus lessonexercisestatus_pkey; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.lessonexercisestatus
    ADD CONSTRAINT lessonexercisestatus_pkey PRIMARY KEY (id);


--
-- Name: lessonquestion lessonquestion_pkey; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.lessonquestion
    ADD CONSTRAINT lessonquestion_pkey PRIMARY KEY (id);


--
-- Name: lessonquestionhistory lessonquestionhistory_pkey; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.lessonquestionhistory
    ADD CONSTRAINT lessonquestionhistory_pkey PRIMARY KEY (id);


--
-- Name: lessonstatus lessonstatus_pkey; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.lessonstatus
    ADD CONSTRAINT lessonstatus_pkey PRIMARY KEY (id);


--
-- Name: request request_pkey; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.request
    ADD CONSTRAINT request_pkey PRIMARY KEY (id);


--
-- Name: target target_pkey; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.target
    ADD CONSTRAINT target_pkey PRIMARY KEY (id);


--
-- Name: answerchoice fkanswerchoi188174; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.answerchoice
    ADD CONSTRAINT fkanswerchoi188174 FOREIGN KEY (lessonquestionhistoryid) REFERENCES public.lessonquestionhistory(id) ON UPDATE CASCADE ON DELETE CASCADE;


--
-- Name: chapter fkchapter746961; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.chapter
    ADD CONSTRAINT fkchapter746961 FOREIGN KEY (courseid) REFERENCES public.course(id) ON UPDATE CASCADE ON DELETE CASCADE;


--
-- Name: lesson fklesson123481; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.lesson
    ADD CONSTRAINT fklesson123481 FOREIGN KEY (chapterid) REFERENCES public.chapter(id) ON UPDATE CASCADE ON DELETE CASCADE;


--
-- Name: lessonexercisestatus fklessonexer257807; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.lessonexercisestatus
    ADD CONSTRAINT fklessonexer257807 FOREIGN KEY (lessonexerciseid) REFERENCES public.lessonexercise(id) ON UPDATE CASCADE ON DELETE CASCADE;


--
-- Name: lessonexercise fklessonexer910564; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.lessonexercise
    ADD CONSTRAINT fklessonexer910564 FOREIGN KEY (lessonid) REFERENCES public.lesson(id) ON UPDATE CASCADE ON DELETE CASCADE;


--
-- Name: lessonexercisestatus fklessonexer936762; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.lessonexercisestatus
    ADD CONSTRAINT fklessonexer936762 FOREIGN KEY (lessonid) REFERENCES public.lesson(id);


--
-- Name: lessonquestionhistory fklessonques214875; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.lessonquestionhistory
    ADD CONSTRAINT fklessonques214875 FOREIGN KEY (lessonquestionid) REFERENCES public.lessonquestion(id) ON UPDATE CASCADE ON DELETE CASCADE;


--
-- Name: lessonquestion fklessonques641174; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.lessonquestion
    ADD CONSTRAINT fklessonques641174 FOREIGN KEY (lessonexerciseid) REFERENCES public.lessonexercise(id) ON UPDATE CASCADE ON DELETE CASCADE;


--
-- Name: lessonstatus fklessonstat434395; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.lessonstatus
    ADD CONSTRAINT fklessonstat434395 FOREIGN KEY (lessonid) REFERENCES public.lesson(id) ON UPDATE CASCADE ON DELETE CASCADE;


--
-- Name: request fkrequest930580; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.request
    ADD CONSTRAINT fkrequest930580 FOREIGN KEY (courseid) REFERENCES public.course(id) ON UPDATE CASCADE ON DELETE CASCADE;


--
-- Name: target fktarget487279; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.target
    ADD CONSTRAINT fktarget487279 FOREIGN KEY (courseid) REFERENCES public.course(id) ON UPDATE CASCADE ON DELETE CASCADE;


--
-- Name: DATABASE d595gh195uo74p; Type: ACL; Schema: -; Owner: -
--

REVOKE CONNECT,TEMPORARY ON DATABASE d595gh195uo74p FROM PUBLIC;


--
-- Name: SCHEMA heroku_ext; Type: ACL; Schema: -; Owner: -
--

GRANT USAGE ON SCHEMA heroku_ext TO smulgjknowzfdc;


--
-- Name: SCHEMA public; Type: ACL; Schema: -; Owner: -
--

REVOKE USAGE ON SCHEMA public FROM PUBLIC;
GRANT ALL ON SCHEMA public TO PUBLIC;


--
-- Name: LANGUAGE plpgsql; Type: ACL; Schema: -; Owner: -
--

GRANT ALL ON LANGUAGE plpgsql TO smulgjknowzfdc;


--
-- PostgreSQL database dump complete
--

