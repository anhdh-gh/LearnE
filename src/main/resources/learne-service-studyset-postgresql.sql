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

DROP DATABASE IF EXISTS db0pt7meirvfkq;
--
-- Name: db0pt7meirvfkq; Type: DATABASE; Schema: -; Owner: -
--

CREATE DATABASE db0pt7meirvfkq WITH TEMPLATE = template0 ENCODING = 'UTF8' LOCALE_PROVIDER = libc LOCALE = 'en_US.UTF-8';


\connect db0pt7meirvfkq

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
-- Name: db0pt7meirvfkq; Type: DATABASE PROPERTIES; Schema: -; Owner: -
--

ALTER DATABASE db0pt7meirvfkq SET search_path TO '$user', 'public', 'heroku_ext';


\connect db0pt7meirvfkq

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
-- Name: studyset; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE public.studyset (
    id character varying(255) NOT NULL,
    title character varying(255) NOT NULL,
    description text NOT NULL,
    createtime timestamp without time zone,
    updatetime timestamp without time zone,
    owneruserid character varying(255) NOT NULL
);


--
-- Name: testresult; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE public.testresult (
    id character varying(255) NOT NULL,
    studysetid character varying(255) NOT NULL,
    createtime timestamp without time zone,
    updatetime timestamp without time zone,
    userid character varying(255) NOT NULL,
    score real NOT NULL,
    completiontime bigint NOT NULL
);


--
-- Name: wordcard; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE public.wordcard (
    id character varying(255) NOT NULL,
    studysetid character varying(255) NOT NULL,
    key text NOT NULL,
    value text NOT NULL,
    createtime timestamp without time zone,
    updatetime timestamp without time zone,
    image character varying(255)
);


--
-- Name: studyset studyset_pkey; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.studyset
    ADD CONSTRAINT studyset_pkey PRIMARY KEY (id);


--
-- Name: testresult testresult_pkey; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.testresult
    ADD CONSTRAINT testresult_pkey PRIMARY KEY (id);


--
-- Name: wordcard wordcard_pkey; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.wordcard
    ADD CONSTRAINT wordcard_pkey PRIMARY KEY (id);


--
-- Name: testresult fktestresult80833; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.testresult
    ADD CONSTRAINT fktestresult80833 FOREIGN KEY (studysetid) REFERENCES public.studyset(id) ON UPDATE CASCADE ON DELETE CASCADE;


--
-- Name: wordcard fkwordcard380497; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.wordcard
    ADD CONSTRAINT fkwordcard380497 FOREIGN KEY (studysetid) REFERENCES public.studyset(id) ON UPDATE CASCADE ON DELETE CASCADE;


--
-- Name: DATABASE db0pt7meirvfkq; Type: ACL; Schema: -; Owner: -
--

REVOKE CONNECT,TEMPORARY ON DATABASE db0pt7meirvfkq FROM PUBLIC;


--
-- Name: SCHEMA heroku_ext; Type: ACL; Schema: -; Owner: -
--

GRANT USAGE ON SCHEMA heroku_ext TO dbbhwsrxdjgxhx;


--
-- Name: SCHEMA public; Type: ACL; Schema: -; Owner: -
--

REVOKE USAGE ON SCHEMA public FROM PUBLIC;
GRANT ALL ON SCHEMA public TO PUBLIC;


--
-- Name: LANGUAGE plpgsql; Type: ACL; Schema: -; Owner: -
--

GRANT ALL ON LANGUAGE plpgsql TO dbbhwsrxdjgxhx;


--
-- PostgreSQL database dump complete
--

