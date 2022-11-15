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

DROP DATABASE IF EXISTS df1c7fnf0g7h6f;
--
-- Name: df1c7fnf0g7h6f; Type: DATABASE; Schema: -; Owner: eptmywlpphtiak
--

CREATE DATABASE df1c7fnf0g7h6f WITH TEMPLATE = template0 ENCODING = 'UTF8' LOCALE_PROVIDER = libc LOCALE = 'en_US.UTF-8';


ALTER DATABASE df1c7fnf0g7h6f OWNER TO eptmywlpphtiak;

\connect df1c7fnf0g7h6f

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
-- Name: df1c7fnf0g7h6f; Type: DATABASE PROPERTIES; Schema: -; Owner: eptmywlpphtiak
--

ALTER DATABASE df1c7fnf0g7h6f SET search_path TO '$user', 'public', 'heroku_ext';


\connect df1c7fnf0g7h6f

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
-- Name: heroku_ext; Type: SCHEMA; Schema: -; Owner: u58ctpihmi0kql
--

CREATE SCHEMA heroku_ext;


ALTER SCHEMA heroku_ext OWNER TO u58ctpihmi0kql;

--
-- Name: public; Type: SCHEMA; Schema: -; Owner: eptmywlpphtiak
--

-- *not* creating schema, since initdb creates it


ALTER SCHEMA public OWNER TO eptmywlpphtiak;

SET default_tablespace = '';

SET default_table_access_method = heap;

--
-- Name: account; Type: TABLE; Schema: public; Owner: eptmywlpphtiak
--

CREATE TABLE public.account (
    id character varying(255) NOT NULL,
    email character varying(255) NOT NULL,
    password character varying(255) NOT NULL,
    createtime timestamp(6) without time zone,
    updatetime timestamp(6) without time zone
);


ALTER TABLE public.account OWNER TO eptmywlpphtiak;

--
-- Name: address; Type: TABLE; Schema: public; Owner: eptmywlpphtiak
--

CREATE TABLE public.address (
    id character varying(255) NOT NULL,
    nation character varying(255),
    city character varying(255),
    province character varying(255),
    district character varying(255),
    street character varying(255),
    numberhouse character varying(255),
    createtime timestamp(6) without time zone,
    updatetime timestamp(6) without time zone
);


ALTER TABLE public.address OWNER TO eptmywlpphtiak;

--
-- Name: fullname; Type: TABLE; Schema: public; Owner: eptmywlpphtiak
--

CREATE TABLE public.fullname (
    id character varying(255) NOT NULL,
    firstname character varying(255),
    midname character varying(255),
    lastname character varying(255),
    createtime timestamp(6) without time zone,
    updatetime timestamp(6) without time zone
);


ALTER TABLE public.fullname OWNER TO eptmywlpphtiak;

--
-- Name: user; Type: TABLE; Schema: public; Owner: eptmywlpphtiak
--

CREATE TABLE public."user" (
    id character varying(255) NOT NULL,
    role character varying(255) NOT NULL,
    gender character varying(255),
    username character varying(255) NOT NULL,
    dateofbirth date,
    phonenumber character varying(255),
    avatar character varying(255),
    createtime timestamp(6) without time zone,
    updatetime timestamp(6) without time zone,
    fullnameid character varying(255),
    addressid character varying(255),
    accountid character varying(255) NOT NULL
);


ALTER TABLE public."user" OWNER TO eptmywlpphtiak;

--
-- Name: user User_pkey; Type: CONSTRAINT; Schema: public; Owner: eptmywlpphtiak
--

ALTER TABLE ONLY public."user"
    ADD CONSTRAINT "User_pkey" PRIMARY KEY (id);


--
-- Name: user User_username_key; Type: CONSTRAINT; Schema: public; Owner: eptmywlpphtiak
--

ALTER TABLE ONLY public."user"
    ADD CONSTRAINT "User_username_key" UNIQUE (username);


--
-- Name: account account_email_key; Type: CONSTRAINT; Schema: public; Owner: eptmywlpphtiak
--

ALTER TABLE ONLY public.account
    ADD CONSTRAINT account_email_key UNIQUE (email);


--
-- Name: account account_pkey; Type: CONSTRAINT; Schema: public; Owner: eptmywlpphtiak
--

ALTER TABLE ONLY public.account
    ADD CONSTRAINT account_pkey PRIMARY KEY (id);


--
-- Name: address address_pkey; Type: CONSTRAINT; Schema: public; Owner: eptmywlpphtiak
--

ALTER TABLE ONLY public.address
    ADD CONSTRAINT address_pkey PRIMARY KEY (id);


--
-- Name: fullname fullname_pkey; Type: CONSTRAINT; Schema: public; Owner: eptmywlpphtiak
--

ALTER TABLE ONLY public.fullname
    ADD CONSTRAINT fullname_pkey PRIMARY KEY (id);


--
-- Name: user fkuser568361; Type: FK CONSTRAINT; Schema: public; Owner: eptmywlpphtiak
--

ALTER TABLE ONLY public."user"
    ADD CONSTRAINT fkuser568361 FOREIGN KEY (fullnameid) REFERENCES public.fullname(id);


--
-- Name: user fkuser847479; Type: FK CONSTRAINT; Schema: public; Owner: eptmywlpphtiak
--

ALTER TABLE ONLY public."user"
    ADD CONSTRAINT fkuser847479 FOREIGN KEY (addressid) REFERENCES public.address(id);


--
-- Name: user fkuser921429; Type: FK CONSTRAINT; Schema: public; Owner: eptmywlpphtiak
--

ALTER TABLE ONLY public."user"
    ADD CONSTRAINT fkuser921429 FOREIGN KEY (accountid) REFERENCES public.account(id) ON UPDATE CASCADE ON DELETE CASCADE;


--
-- Name: DATABASE df1c7fnf0g7h6f; Type: ACL; Schema: -; Owner: eptmywlpphtiak
--

REVOKE CONNECT,TEMPORARY ON DATABASE df1c7fnf0g7h6f FROM PUBLIC;


--
-- Name: SCHEMA heroku_ext; Type: ACL; Schema: -; Owner: u58ctpihmi0kql
--

GRANT USAGE ON SCHEMA heroku_ext TO eptmywlpphtiak;


--
-- Name: SCHEMA public; Type: ACL; Schema: -; Owner: eptmywlpphtiak
--

REVOKE USAGE ON SCHEMA public FROM PUBLIC;
GRANT ALL ON SCHEMA public TO PUBLIC;


--
-- Name: LANGUAGE plpgsql; Type: ACL; Schema: -; Owner: postgres
--

GRANT ALL ON LANGUAGE plpgsql TO eptmywlpphtiak;


--
-- PostgreSQL database dump complete
--

