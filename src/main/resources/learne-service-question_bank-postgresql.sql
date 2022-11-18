--
-- PostgreSQL database dump
--

-- Dumped from database version 14.6 (Ubuntu 14.6-1.pgdg20.04+1)
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

DROP DATABASE IF EXISTS dfadjkel9cmlon;
--
-- Name: dfadjkel9cmlon; Type: DATABASE; Schema: -; Owner: -
--

CREATE DATABASE dfadjkel9cmlon WITH TEMPLATE = template0 ENCODING = 'UTF8' LOCALE_PROVIDER = libc LOCALE = 'en_US.UTF-8';


\connect dfadjkel9cmlon

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
-- Name: dfadjkel9cmlon; Type: DATABASE PROPERTIES; Schema: -; Owner: -
--

ALTER DATABASE dfadjkel9cmlon SET search_path TO '$user', 'public', 'heroku_ext';


\connect dfadjkel9cmlon

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
-- Name: answer; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE public.answer (
    id integer NOT NULL,
    questionid character varying(255) NOT NULL,
    iscorrect boolean NOT NULL,
    text text NOT NULL,
    updatetime timestamp(6) without time zone,
    createtime timestamp(6) without time zone,
    audio character varying(1000)
);


--
-- Name: answer_id_seq; Type: SEQUENCE; Schema: public; Owner: -
--

CREATE SEQUENCE public.answer_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- Name: answer_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: -
--

ALTER SEQUENCE public.answer_id_seq OWNED BY public.answer.id;


--
-- Name: question; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE public.question (
    id character varying(255) NOT NULL,
    questiontype character varying(255) NOT NULL,
    text text NOT NULL,
    image character varying(1000),
    audio character varying(1000),
    updatetime timestamp(6) without time zone,
    createtime timestamp(6) without time zone,
    header text,
    "time" integer,
    pdf character varying(1000),
    groupid character varying(255) NOT NULL
);


--
-- Name: answer id; Type: DEFAULT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.answer ALTER COLUMN id SET DEFAULT nextval('public.answer_id_seq'::regclass);


--
-- Name: answer answer_pkey; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.answer
    ADD CONSTRAINT answer_pkey PRIMARY KEY (id);


--
-- Name: question question_pkey; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.question
    ADD CONSTRAINT question_pkey PRIMARY KEY (id);


--
-- Name: answer fkanswer406451; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.answer
    ADD CONSTRAINT fkanswer406451 FOREIGN KEY (questionid) REFERENCES public.question(id) ON UPDATE CASCADE ON DELETE CASCADE;


--
-- Name: DATABASE dfadjkel9cmlon; Type: ACL; Schema: -; Owner: -
--

REVOKE CONNECT,TEMPORARY ON DATABASE dfadjkel9cmlon FROM PUBLIC;


--
-- Name: SCHEMA heroku_ext; Type: ACL; Schema: -; Owner: -
--

GRANT USAGE ON SCHEMA heroku_ext TO udfrqepeatccsz;


--
-- Name: SCHEMA public; Type: ACL; Schema: -; Owner: -
--

REVOKE USAGE ON SCHEMA public FROM PUBLIC;
GRANT ALL ON SCHEMA public TO PUBLIC;


--
-- Name: LANGUAGE plpgsql; Type: ACL; Schema: -; Owner: -
--

GRANT ALL ON LANGUAGE plpgsql TO udfrqepeatccsz;


--
-- PostgreSQL database dump complete
--

