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
    id character varying(255) NOT NULL,
    questionid character varying(255) NOT NULL,
    iscorrect boolean NOT NULL,
    text text NOT NULL,
    updatetime timestamp(6) without time zone,
    createtime timestamp(6) without time zone
);


--
-- Name: question; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE public.question (
    id character varying(255) NOT NULL,
    questiontype character varying(255) NOT NULL,
    text text NOT NULL,
    image character varying(255),
    audio character varying(255),
    updatetime timestamp(6) without time zone,
    createtime timestamp(6) without time zone,
    header text,
    groupid character varying(255) NOT NULL
);


--
-- Data for Name: answer; Type: TABLE DATA; Schema: public; Owner: -
--

COPY public.answer (id, questionid, iscorrect, text, updatetime, createtime) FROM stdin;
27aed106-a2cd-4776-9acd-9df38a515b99	b6464824-b8a8-4a87-9c6c-133d090a5e9f	f	as a result	\N	2022-11-04 18:07:42.306
87cec0c5-4d24-4f01-ac2c-f6ac2b0eb1bb	b6464824-b8a8-4a87-9c6c-133d090a5e9f	f	in addition	\N	2022-11-04 18:07:42.307
d011adde-1a33-47a9-b1bf-ce9f1008ef53	b6464824-b8a8-4a87-9c6c-133d090a5e9f	f	although	\N	2022-11-04 18:07:42.307
84d512d3-fd06-4c0e-bd84-58ead33f0610	b6464824-b8a8-4a87-9c6c-133d090a5e9f	t	before	\N	2022-11-04 18:07:42.307
2205eb48-1960-4066-a0f8-b2a876dc2596	e5f36c37-97ed-43a0-baae-7dfc61911bde	t	regional	\N	2022-11-04 17:56:38.519
9f433d6a-75a8-42a3-86a5-4ede60686758	e5f36c37-97ed-43a0-baae-7dfc61911bde	f	regionally	\N	2022-11-04 17:56:38.52
effd2edd-2c18-4c5b-8bc9-c5e81e590e9c	e5f36c37-97ed-43a0-baae-7dfc61911bde	f	region	\N	2022-11-04 17:56:38.521
37d64139-4293-45b0-a9eb-166bd53d6f0d	e5f36c37-97ed-43a0-baae-7dfc61911bde	f	regions	\N	2022-11-04 17:56:38.521
d0b9ea1e-fd31-41cf-9d6a-ee012131c8c7	10124422-8291-48c1-8f73-fcde4cee274b	f	world	\N	2022-11-04 17:58:42.2
c45c24c0-b073-4ffa-8510-28bd6bcf214c	10124422-8291-48c1-8f73-fcde4cee274b	f	family	\N	2022-11-04 17:58:42.2
b5848d1b-5a6f-4d43-ac53-efbf925df2e2	10124422-8291-48c1-8f73-fcde4cee274b	f	region	\N	2022-11-04 17:58:42.2
20373712-7a58-46e6-b4db-bd2ac013285a	10124422-8291-48c1-8f73-fcde4cee274b	t	city	\N	2022-11-04 17:58:42.2
7fb0a454-3352-4de9-a1c2-efbacf2e10f2	25140f41-a3cb-4586-9c4b-3a97cba1ba21	f	you	\N	2022-11-04 17:59:40.542
8f4f7946-dda0-4786-8d88-7fd1bb0f10e7	25140f41-a3cb-4586-9c4b-3a97cba1ba21	f	yours	\N	2022-11-04 17:59:40.542
b779acf2-ac8a-4b8f-a573-d26df2acbe27	25140f41-a3cb-4586-9c4b-3a97cba1ba21	f	yourself	\N	2022-11-04 17:59:40.542
a540d51e-c7af-4d1f-a690-1e44feed64e3	25140f41-a3cb-4586-9c4b-3a97cba1ba21	t	your	\N	2022-11-04 17:59:40.543
e9b865bb-884b-4005-8a06-d21a6ee15fc5	6e55d18c-f1d8-4594-8b7b-5deb9a939454	f	up	\N	2022-11-04 18:00:47.39
2551c9fc-01a7-415f-9a92-14dbc7942e25	6e55d18c-f1d8-4594-8b7b-5deb9a939454	f	except	\N	2022-11-04 18:00:47.39
e3752201-66f5-4903-ba39-46a1495ce66a	6e55d18c-f1d8-4594-8b7b-5deb9a939454	f	onto	\N	2022-11-04 18:00:47.39
064348f0-a043-4df4-8302-918352857830	6e55d18c-f1d8-4594-8b7b-5deb9a939454	t	through	\N	2022-11-04 18:00:47.391
0898e2c7-e2c7-4c6c-88c1-1574e7eecf30	89bc1142-59e5-4e5a-99aa-a7d187eda88d	t	to arrange	\N	2022-11-04 18:02:46.081
860293fc-ddb4-4e96-bde2-2d6fa0aace3e	89bc1142-59e5-4e5a-99aa-a7d187eda88d	f	except	\N	2022-11-04 18:02:46.082
d9948bc5-28ca-4539-b20d-ca866137a663	89bc1142-59e5-4e5a-99aa-a7d187eda88d	f	arranging	\N	2022-11-04 18:02:46.082
8f7d4fff-4098-45f8-b32c-4ac9660d0abe	89bc1142-59e5-4e5a-99aa-a7d187eda88d	f	arrangement	\N	2022-11-04 18:02:46.082
c70af666-5678-4f20-b3ce-2ab6bfb1664b	6affe42d-b18b-4efd-92ad-6ce64e63e5d1	f	regularly	\N	2022-11-04 18:05:22.258
91de07bf-25d6-4619-9188-81ef9f283409	6affe42d-b18b-4efd-92ad-6ce64e63e5d1	t	conveniently	\N	2022-11-04 18:05:22.258
1fc18911-4e71-418b-a5b4-20a0573ea1a3	6affe42d-b18b-4efd-92ad-6ce64e63e5d1	f	brightly	\N	2022-11-04 18:05:22.258
f85bc483-f2ff-49fd-b229-005586ed0e21	6affe42d-b18b-4efd-92ad-6ce64e63e5d1	f	collectively	\N	2022-11-04 18:05:22.258
f65f60f2-a071-4615-ad2b-7c5527646e7f	9853b71c-4b87-478b-9c25-2a024cc4ba6f	f	are delayed	\N	2022-11-04 18:06:28.345
1a39638f-fa4e-421e-a81c-1f28d0ebb294	9853b71c-4b87-478b-9c25-2a024cc4ba6f	f	to delay	\N	2022-11-04 18:06:28.345
4e77cdf6-1c23-4ca9-b987-749e4ab2b9cf	9853b71c-4b87-478b-9c25-2a024cc4ba6f	t	delays	\N	2022-11-04 18:06:28.345
ca21f33e-90ea-44ca-bfe6-59be2a4aaf9a	9853b71c-4b87-478b-9c25-2a024cc4ba6f	f	has delayed	\N	2022-11-04 18:06:28.345
b94e10a7-230d-4897-9a51-3786fe05db2c	3c033b95-3f6d-433d-8879-4fd8d6c67530	f	clear	\N	2022-11-04 18:08:55.602
cb0b6f4f-8793-4ee4-9c75-9aeed87f2e02	3c033b95-3f6d-433d-8879-4fd8d6c67530	f	clearing	\N	2022-11-04 18:08:55.603
5dc36474-7680-406c-8ddd-4245704d21a7	3c033b95-3f6d-433d-8879-4fd8d6c67530	f	clearest	\N	2022-11-04 18:08:55.603
8e21a4f5-f7f3-4f88-81ba-6960bfdae247	3c033b95-3f6d-433d-8879-4fd8d6c67530	t	clearly	\N	2022-11-04 18:08:55.603
0c748659-8ffe-4fdc-8f3b-542e7970305f	0476475e-d5d5-4856-ae14-808d43d12573	t	regcognized	\N	2022-11-04 18:10:33.517
4500d43e-d1a8-4716-92e5-a12b92216ca2	0476475e-d5d5-4856-ae14-808d43d12573	f	controlled	\N	2022-11-04 18:10:33.517
692dc8aa-e4c2-451b-a2b5-f0a8bfeb961a	0476475e-d5d5-4856-ae14-808d43d12573	f	permitted	\N	2022-11-04 18:10:33.517
994dfa0d-c98b-48ec-bceb-7315ecb1ef80	0476475e-d5d5-4856-ae14-808d43d12573	f	prepared	\N	2022-11-04 18:10:33.517
b7377f1d-4724-4637-a2d4-f8cea670e4f1	111b9c0c-c095-43e9-ab11-2e064d0ecf5f	f	later	\N	2022-11-04 18:12:11.549
8e3a18e0-6226-42c7-a811-8d4f1d4e51aa	111b9c0c-c095-43e9-ab11-2e064d0ecf5f	t	after	\N	2022-11-04 18:12:11.549
f3bc7182-34a2-498f-aa53-6bf3b6812e3c	111b9c0c-c095-43e9-ab11-2e064d0ecf5f	f	than	\N	2022-11-04 18:12:11.549
ab2da815-fc6a-4f7e-a117-cd23a90dc55a	111b9c0c-c095-43e9-ab11-2e064d0ecf5f	f	often	\N	2022-11-04 18:12:11.549
cb43786b-a007-4a31-bb71-7a964bb98ed8	0a93cd72-ce0a-4aa2-a8a6-b21ac497faa4	f	adjusted	\N	2022-11-04 18:13:11.478
e0d3b75d-7acb-4a07-8d1d-69a9f32ac955	0a93cd72-ce0a-4aa2-a8a6-b21ac497faa4	t	advanced	\N	2022-11-04 18:13:11.478
1254153a-4a4d-4e3a-bb8d-4a4d5ea38ff7	0a93cd72-ce0a-4aa2-a8a6-b21ac497faa4	f	eager	\N	2022-11-04 18:13:11.478
37f955a1-9ff6-4ccb-a497-393e289b64ee	0a93cd72-ce0a-4aa2-a8a6-b21ac497faa4	f	faithful	\N	2022-11-04 18:13:11.478
7772b2ab-d9ad-4dc3-bbc2-a3e0b76e4e71	e3f67f97-1ce2-4cb4-8d61-37154f82972a	f	evaluation	\N	2022-11-04 18:14:28.506
35613a8f-ec5f-4d10-af39-7b30abc9b0c0	e3f67f97-1ce2-4cb4-8d61-37154f82972a	f	evaluate	\N	2022-11-04 18:14:28.506
a24053e1-c1f0-4b29-9eaa-8bfc6ac19564	e3f67f97-1ce2-4cb4-8d61-37154f82972a	t	evaluating	\N	2022-11-04 18:14:28.507
ca292f07-a284-42fd-929b-0e5cc0ac97aa	e3f67f97-1ce2-4cb4-8d61-37154f82972a	f	evaluated	\N	2022-11-04 18:14:28.507
a70690b4-dba5-49c3-bb7c-f87d2c7dbf2f	9b250d5e-f0b7-437b-bc71-05df4b10b194	f	on	\N	2022-11-04 18:16:08.057
5c26af2f-6719-4ab5-8a3d-030bdbec179f	9b250d5e-f0b7-437b-bc71-05df4b10b194	t	for	\N	2022-11-04 18:16:08.058
082173dc-f9ab-4402-a7dd-2cfe40d7ebc8	9b250d5e-f0b7-437b-bc71-05df4b10b194	f	to	\N	2022-11-04 18:16:08.058
9de5a801-c9be-4f07-a5e5-b0176cb7b67f	9b250d5e-f0b7-437b-bc71-05df4b10b194	f	under	\N	2022-11-04 18:16:08.058
1cc2743c-e7cd-40d0-b9ad-49c34c885311	41d050ef-0428-449b-90c9-923e4e102460	f	creation	\N	2022-11-04 18:17:39.933
f872f5e8-4b5e-44a0-9baf-6802c30ad695	41d050ef-0428-449b-90c9-923e4e102460	f	create	\N	2022-11-04 18:17:39.933
507e6124-b800-4218-b1e8-245962bdb406	41d050ef-0428-449b-90c9-923e4e102460	f	creativity	\N	2022-11-04 18:17:39.933
24cebdef-e0a4-4417-a604-5271a1e5f31f	41d050ef-0428-449b-90c9-923e4e102460	t	creative	\N	2022-11-04 18:17:39.933
29ec2b8c-e358-42e0-b71e-dbdef8460a72	7d13cc10-ef1f-4b27-8674-048bc0375f8d	f	even	\N	2022-11-04 18:18:53.413
69315743-f496-4a86-9185-3b4db43d049a	7d13cc10-ef1f-4b27-8674-048bc0375f8d	t	unless	\N	2022-11-04 18:18:53.413
9f3b33d2-e683-474f-ad3c-a620cafbb823	7d13cc10-ef1f-4b27-8674-048bc0375f8d	f	similary	\N	2022-11-04 18:18:53.413
a462a0f4-6eba-45ac-a5f5-907eb1ace41d	7d13cc10-ef1f-4b27-8674-048bc0375f8d	f	also	\N	2022-11-04 18:18:53.414
691b59f8-97a0-4ea6-9efb-87b45f22b1db	8e165bac-8222-42f7-9e0b-43b035694b33	f	renew	\N	2022-11-04 18:19:55.697
8dc311cf-a1da-4429-ac11-b6636a8d924b	8e165bac-8222-42f7-9e0b-43b035694b33	f	renewed	\N	2022-11-04 18:19:55.697
b37340b4-f0f1-46fc-98f0-df96b3e3c44a	8e165bac-8222-42f7-9e0b-43b035694b33	t	renewals	\N	2022-11-04 18:19:55.697
38ab5fb3-941d-40b0-bd4a-ee19fc049a31	8e165bac-8222-42f7-9e0b-43b035694b33	f	to renew	\N	2022-11-04 18:19:55.697
8091a227-93ab-4399-af41-47dfbfcd57b5	1675aa5d-16f2-4f78-bcfe-f918ad5acbbf	f	careful	\N	2022-11-04 18:21:33.775
0d476d79-3492-46ed-9f10-974b741f0a58	1675aa5d-16f2-4f78-bcfe-f918ad5acbbf	f	helpful	\N	2022-11-04 18:21:33.775
c3e1e9bb-358a-4f90-acc4-774c6eb0bba3	1675aa5d-16f2-4f78-bcfe-f918ad5acbbf	t	confident	\N	2022-11-04 18:21:33.776
a5acd396-92bc-4c15-a65f-21a6ab32f9d7	1675aa5d-16f2-4f78-bcfe-f918ad5acbbf	f	durable	\N	2022-11-04 18:21:33.776
3aff93b7-c412-4387-b941-1b165c78be8b	3d1aeaab-c2ee-44af-8b88-d8edf6ce5c10	f	consistent	\N	2022-11-04 18:22:24.936
8bf4ba77-ed71-462b-9639-bd5c66208f46	3d1aeaab-c2ee-44af-8b88-d8edf6ce5c10	f	consist	\N	2022-11-04 18:22:24.936
11a60296-02a2-4fdf-9ad4-7b067eb1d554	3d1aeaab-c2ee-44af-8b88-d8edf6ce5c10	t	consistently	\N	2022-11-04 18:22:24.937
6a4a4a5c-752c-4ee2-8653-f822d71d5f7d	3d1aeaab-c2ee-44af-8b88-d8edf6ce5c10	f	consisting	\N	2022-11-04 18:22:24.937
929112f5-aad4-425b-91fc-eee0a0456764	ace78108-17e5-43e0-9630-55af76727531	t	launch	\N	2022-11-04 18:24:34.445
2471d0ec-3a2d-4726-96df-325a6596d37e	ace78108-17e5-43e0-9630-55af76727531	f	facilitate	\N	2022-11-04 18:24:34.445
826e154a-cf61-4199-a647-d141dc3282da	ace78108-17e5-43e0-9630-55af76727531	f	arise	\N	2022-11-04 18:24:34.446
db8591c0-48de-44b8-8ce4-75e7cdb680bb	ace78108-17e5-43e0-9630-55af76727531	f	exert	\N	2022-11-04 18:24:34.446
2dff9543-42de-4f16-8a18-8e94f3bf3001	47a3fb5a-27e8-4643-a248-c984b02bdc47	t	if	\N	2022-11-04 18:25:23.725
0f0cb6ba-921f-4f45-b5df-b3bf5f4aac5f	47a3fb5a-27e8-4643-a248-c984b02bdc47	f	yet	\N	2022-11-04 18:25:23.725
d0e8828e-bece-437c-97e9-413d66c17b8f	47a3fb5a-27e8-4643-a248-c984b02bdc47	f	until	\N	2022-11-04 18:25:23.725
7ec51ed9-a309-4300-8d7b-e37187bfabba	47a3fb5a-27e8-4643-a248-c984b02bdc47	f	neither	\N	2022-11-04 18:25:23.725
f97e1245-97c9-4f49-9e76-85447bef402b	e79ececc-e750-4ed4-a50e-05a13501de9b	f	majority	\N	2022-11-04 18:26:27.172
d870fad7-b787-4481-a568-b3d54b4789aa	e79ececc-e750-4ed4-a50e-05a13501de9b	f	edition	\N	2022-11-04 18:26:27.172
a1f0e7c5-0209-4e52-bbef-33cfd19acc32	e79ececc-e750-4ed4-a50e-05a13501de9b	t	volume	\N	2022-11-04 18:26:27.172
aac9cb27-d90b-49ee-b690-3877b97eccfd	e79ececc-e750-4ed4-a50e-05a13501de9b	f	economy	\N	2022-11-04 18:26:27.173
7168b2d5-39f1-48a4-adf9-fd76f5023dca	1722ed88-07a2-4845-a6eb-fc38eab8b3c3	f	coordinated	\N	2022-11-04 18:28:22.386
011543d2-6afa-49c6-9d8d-c78258b458e7	1722ed88-07a2-4845-a6eb-fc38eab8b3c3	f	to coordinate	\N	2022-11-04 18:28:22.386
f93f95fe-3c66-4355-8bbe-a1c0ef36279b	1722ed88-07a2-4845-a6eb-fc38eab8b3c3	f	coordination	\N	2022-11-04 18:28:22.386
df84d4d8-7885-4984-855b-6a6980c11f95	1722ed88-07a2-4845-a6eb-fc38eab8b3c3	t	be coordinating	\N	2022-11-04 18:28:22.386
405d5cd1-8ad3-4c72-b661-c2f079a16ed1	9e6b9318-9fd9-432e-8892-8736cd4a960d	t	significantly	\N	2022-11-04 18:30:08.678
4afb55ec-4858-4636-af5f-6a563fd1e4b9	9e6b9318-9fd9-432e-8892-8736cd4a960d	f	persuasively	\N	2022-11-04 18:30:08.678
5a421b7c-43a7-4e85-b16f-2d11bc2fc256	9e6b9318-9fd9-432e-8892-8736cd4a960d	f	proficiently	\N	2022-11-04 18:30:08.678
d3ce6348-5c9a-4ead-ba76-00701383c35a	9e6b9318-9fd9-432e-8892-8736cd4a960d	f	gladly	\N	2022-11-04 18:30:08.678
32dc516f-2411-4684-b1b5-bad84a56089a	d2bb9bce-80c0-441e-932f-fc96507b55d1	f	substituted	\N	2022-11-04 18:31:19.018
2c43d0cb-9f89-41e9-b28c-7c4356d575c6	d2bb9bce-80c0-441e-932f-fc96507b55d1	f	substituting	\N	2022-11-04 18:31:19.019
b73792d6-de3d-4918-9cbc-e30c31bf8db2	d2bb9bce-80c0-441e-932f-fc96507b55d1	t	substitutions	\N	2022-11-04 18:31:19.019
1a234f44-0740-4911-8b02-0fea63522341	d2bb9bce-80c0-441e-932f-fc96507b55d1	f	substitute	\N	2022-11-04 18:31:19.019
2fa1e190-300f-43d6-bc17-7c7ec6c8e905	48de00b3-6e3d-4d26-ba78-ad087f59e52b	f	inform	\N	2022-11-04 18:32:22.79
6d73b7e7-9112-4a71-974a-b1b8329a3dd9	48de00b3-6e3d-4d26-ba78-ad087f59e52b	f	succeed	\N	2022-11-04 18:32:22.79
50ba5e53-d7b8-4ab3-91a4-c4a005fffdd6	48de00b3-6e3d-4d26-ba78-ad087f59e52b	f	estimate	\N	2022-11-04 18:32:22.791
9cbf126f-88fa-47f7-a2a3-95e4ef737902	48de00b3-6e3d-4d26-ba78-ad087f59e52b	t	establish	\N	2022-11-04 18:32:22.791
83486e1a-5acd-4467-9dd6-014a3366a4c7	bef5c18c-e49f-4011-a660-566b4b7ea68d	f	Happily	\N	2022-11-04 18:33:37.412
b862e700-d613-4cd7-8462-50b2c654d2aa	bef5c18c-e49f-4011-a660-566b4b7ea68d	t	Now that	\N	2022-11-04 18:33:37.412
cc94d4a5-e622-4a04-a386-9aea22ddfb15	bef5c18c-e49f-4011-a660-566b4b7ea68d	f	Despite	\N	2022-11-04 18:33:37.412
e069d9e2-5cf2-41d3-902e-ef832d8f8eed	bef5c18c-e49f-4011-a660-566b4b7ea68d	f	In fact	\N	2022-11-04 18:33:37.412
dcce9af6-4c45-40cc-b49b-e6d0cf56485d	1f999bad-23cf-4d05-90dd-0b7bea187b6c	f	readily	\N	2022-11-04 18:35:12.427
3c35d31e-2be5-4bb2-867c-a029d02df309	1f999bad-23cf-4d05-90dd-0b7bea187b6c	t	diligently	\N	2022-11-04 18:35:12.427
31750e4a-d55b-42b3-bf6d-4aabf9137b6c	1f999bad-23cf-4d05-90dd-0b7bea187b6c	f	extremely	\N	2022-11-04 18:35:12.427
6527e536-9521-400f-963f-e393a0ad05da	1f999bad-23cf-4d05-90dd-0b7bea187b6c	f	curiously	\N	2022-11-04 18:35:12.427
a922ac16-1951-4da6-9681-8b7f2e562345	5af80246-3ea7-4ae8-8d3b-2de336ade171	f	whose	\N	2022-11-04 18:36:08.368
f93c8029-5bee-4e80-a57b-99a98f0f1bff	5af80246-3ea7-4ae8-8d3b-2de336ade171	f	his	\N	2022-11-04 18:36:08.368
ff10fbce-bac6-4fc7-9887-0fed420087a8	5af80246-3ea7-4ae8-8d3b-2de336ade171	t	its	\N	2022-11-04 18:36:08.368
e0142a6a-50f3-4afd-8702-5dfd724d291e	5af80246-3ea7-4ae8-8d3b-2de336ade171	f	this	\N	2022-11-04 18:36:08.368
970f0142-54c9-4304-bd3f-5a9946269bb4	bc903efa-20ee-4b11-8431-711c00e93a1c	f	thus	\N	2022-11-04 18:37:23.098
1c65c54e-6aec-42cc-b474-b346df633a60	bc903efa-20ee-4b11-8431-711c00e93a1c	t	as well as	\N	2022-11-04 18:37:23.099
d25318e9-d4a7-4a46-acdf-7124088e513f	bc903efa-20ee-4b11-8431-711c00e93a1c	f	at last	\N	2022-11-04 18:37:23.099
24a77303-8f5a-4c5f-bafd-ca3e22037e1d	bc903efa-20ee-4b11-8431-711c00e93a1c	f	accordingly	\N	2022-11-04 18:37:23.099
390ef0e0-3616-4533-8636-c3de662003c9	2c369c9c-51e6-4dbc-91ca-1cb715ed310d	f	thus	\N	2022-11-04 18:37:53.085
c6af92e2-e116-4e03-af2f-684e39099ea6	2c369c9c-51e6-4dbc-91ca-1cb715ed310d	t	as well as	\N	2022-11-04 18:37:53.085
0e3726a6-1846-4f40-81cb-1d407dd716ed	2c369c9c-51e6-4dbc-91ca-1cb715ed310d	f	at last	\N	2022-11-04 18:37:53.085
f0262773-0687-431b-8b86-af39a487e77c	2c369c9c-51e6-4dbc-91ca-1cb715ed310d	f	accordingly	\N	2022-11-04 18:37:53.085
\.


--
-- Data for Name: question; Type: TABLE DATA; Schema: public; Owner: -
--

COPY public.question (id, questiontype, text, image, audio, updatetime, createtime, header, groupid) FROM stdin;
e5f36c37-97ed-43a0-baae-7dfc61911bde	INCOMPLETE_SENTENCES	Mougey Fine Gifts is known for its large range of ------ goods	\N	\N	2022-11-05 04:15:44.058	2022-11-04 17:56:38.483	\N	25cd9c11-9fed-4da9-b39f-a71aebd0241d
10124422-8291-48c1-8f73-fcde4cee274b	INCOMPLETE_SENTENCES	Income levels are rising in the ------ and surrounding areas	\N	\N	2022-11-05 04:15:44.21	2022-11-04 17:58:42.199	\N	c12a32aa-c530-4167-ba63-f5c4d77e501a
25140f41-a3cb-4586-9c4b-3a97cba1ba21	INCOMPLETE_SENTENCES	 Since we had a recent rate change, expect ------ next electricity bill to be slightly lower	\N	\N	2022-11-05 04:15:44.327	2022-11-04 17:59:40.542	\N	f9293ba9-ef3c-4e13-8ef5-dec1b3b8a9ff
6e55d18c-f1d8-4594-8b7b-5deb9a939454	INCOMPLETE_SENTENCES	Hotel guests have a lovely view of the ocean ------ the south-facing windows	\N	\N	2022-11-05 04:15:44.447	2022-11-04 18:00:47.39	\N	aa03b123-4724-41fe-90a9-d040c77ca0ec
89bc1142-59e5-4e5a-99aa-a7d187eda88d	INCOMPLETE_SENTENCES	Mr. Kim would like ----- a meeting about the Jasper account as soon as possible	\N	\N	2022-11-05 04:15:44.562	2022-11-04 18:02:46.081	\N	46d9e68e-b3e7-41bd-9ac8-b1c414323e46
6affe42d-b18b-4efd-92ad-6ce64e63e5d1	INCOMPLETE_SENTENCES	 The factory is ----- located near the station	\N	\N	2022-11-05 04:15:44.687	2022-11-04 18:05:22.258	\N	eace7aca-cda5-467e-a8e7-761c0df8b0f9
9853b71c-4b87-478b-9c25-2a024cc4ba6f	INCOMPLETE_SENTENCES	Because of transportation ------ due to winter weather, some conference participants may arrive late	\N	\N	2022-11-05 04:15:44.804	2022-11-04 18:06:28.344	\N	b65598aa-52f4-4e5c-8956-cf9435da9415
b6464824-b8a8-4a87-9c6c-133d090a5e9f	INCOMPLETE_SENTENCES	Proper maintenance of your heating equipment ensures that small issues can be fixed ----- they become big ones	\N	\N	2022-11-05 04:15:44.918	2022-11-04 18:07:42.306	\N	e3e08bec-404e-43a3-8e6b-2807d510eb11
3c033b95-3f6d-433d-8879-4fd8d6c67530	INCOMPLETE_SENTENCES	The information on the Web site of Croyell Decorators is ----- organized	\N	\N	2022-11-05 04:15:45.033	2022-11-04 18:08:55.601	\N	80dab42e-207f-4868-aa4d-960e272f9ef9
0476475e-d5d5-4856-ae14-808d43d12573	INCOMPLETE_SENTENCES	The Copley Corporation is frequently ----- as a company that employs workers from all over the world. 	\N	\N	2022-11-05 04:15:45.149	2022-11-04 18:10:33.517	\N	8170a22f-bdc3-4baf-8bc8-2a9d18015e16
111b9c0c-c095-43e9-ab11-2e064d0ecf5f	INCOMPLETE_SENTENCES	Payments made ----- 4:00 P.M. will be processed on the following business day. 	\N	\N	2022-11-05 04:15:45.264	2022-11-04 18:12:11.548	\N	778e6f8c-a64c-494c-ac4b-080943355049
0a93cd72-ce0a-4aa2-a8a6-b21ac497faa4	INCOMPLETE_SENTENCES	Greenfiddle Water Treatment hires engineers who have ----- mathematics skills. 	\N	\N	2022-11-05 04:15:45.38	2022-11-04 18:13:11.477	\N	096287d9-b51c-4f16-ab53-385f55dbf0c5
e3f67f97-1ce2-4cb4-8d61-37154f82972a	INCOMPLETE_SENTENCES	After ----- the neighborhood, Mr. Park decided not to move his cafe to Thomasville. 	\N	\N	2022-11-05 04:15:45.494	2022-11-04 18:14:28.506	\N	c013b45f-b4f7-49a4-8e39-13f7e1518d60
9b250d5e-f0b7-437b-bc71-05df4b10b194	INCOMPLETE_SENTENCES	The average precipitation in Campos ----- the past three years has been 22.7 centimeters	\N	\N	2022-11-05 04:15:45.61	2022-11-04 18:16:08.057	\N	ec446872-844d-4969-9da1-5489fe44e347
41d050ef-0428-449b-90c9-923e4e102460	INCOMPLETE_SENTENCES	Improving efficiency at Perwon Manufacturing will require a ----- revision of existing processes	\N	\N	2022-11-05 04:15:45.725	2022-11-04 18:17:39.932	\N	6de44874-0d8a-4285-ba29-82d81cd5f917
7d13cc10-ef1f-4b27-8674-048bc0375f8d	INCOMPLETE_SENTENCES	Conference attendees will share accommodations ----- they submit a special request for a single room.	\N	\N	2022-11-05 04:15:45.842	2022-11-04 18:18:53.413	\N	5af63317-8217-4c38-bcb3-59119009dc13
8e165bac-8222-42f7-9e0b-43b035694b33	INCOMPLETE_SENTENCES	To receive ----- , please be sure the appropriate box is checked on the magazine order form	\N	\N	2022-11-05 04:15:45.955	2022-11-04 18:19:55.697	\N	5ad71fd7-9ae9-427e-b906-b180c9ccbb31
1675aa5d-16f2-4f78-bcfe-f918ad5acbbf	INCOMPLETE_SENTENCES	Donations to the Natusi Wildlife Reserve rise when consumers feel  about the economy.	\N	\N	2022-11-05 04:15:46.073	2022-11-04 18:21:33.775	\N	7a8dbd3b-2f81-4cc8-8648-747afa33fe40
3d1aeaab-c2ee-44af-8b88-d8edf6ce5c10	INCOMPLETE_SENTENCES	When   applied, Tilda's Restorative Cream reduces the appearance of fine lines and wrinkles	\N	\N	2022-11-05 04:15:46.187	2022-11-04 18:22:24.936	\N	3130ffab-6a6d-49ea-a956-ab0893acc1db
ace78108-17e5-43e0-9630-55af76727531	INCOMPLETE_SENTENCES	The marketing director confirmed that the new software program would be ready to ----- by November 1.	\N	\N	2022-11-05 04:15:46.302	2022-11-04 18:24:34.445	\N	5081e294-6b61-407e-9273-087ab264502d
47a3fb5a-27e8-4643-a248-c984b02bdc47	INCOMPLETE_SENTENCES	Satinesse Seat Covers will refund your order ----- you are not completely satisfied	\N	\N	2022-11-05 04:15:46.416	2022-11-04 18:25:23.724	\N	e48aa7c1-d280-4b1d-8b1c-481c1bb09248
e79ececc-e750-4ed4-a50e-05a13501de9b	INCOMPLETE_SENTENCES	In the last five years, production at the Harris facility has almost doubled in -----	\N	\N	2022-11-05 04:15:46.529	2022-11-04 18:26:27.172	\N	9a7bba16-901c-4f6e-acda-b0b401817054
1722ed88-07a2-4845-a6eb-fc38eab8b3c3	INCOMPLETE_SENTENCES	Ms. Tsai will ------ the installation of the new workstations with the vendor.	\N	\N	2022-11-05 04:15:46.643	2022-11-04 18:28:22.386	\N	bd609cc5-d497-48d0-9624-290cc70236e9
9e6b9318-9fd9-432e-8892-8736cd4a960d	INCOMPLETE_SENTENCES	An upgrade in software would -- ---- increase the productivity of our administrative staff	\N	\N	2022-11-05 04:15:46.758	2022-11-04 18:30:08.678	\N	20cec078-4787-44f7-bb8c-cdb7fff76087
d2bb9bce-80c0-441e-932f-fc96507b55d1	INCOMPLETE_SENTENCES	The Rustic Diner's chef does allow patrons to make menu -----	\N	\N	2022-11-05 04:15:46.872	2022-11-04 18:31:19.018	\N	45163bfd-ca77-44fe-a645-3c62034a1ad9
48de00b3-6e3d-4d26-ba78-ad087f59e52b	INCOMPLETE_SENTENCES	Ms. Rodriguez noted that it is important to ------ explicit policies regarding the use of company computers	\N	\N	2022-11-05 04:15:46.988	2022-11-04 18:32:22.79	\N	83a3533b-926f-44d8-8534-6eff307fd864
bef5c18c-e49f-4011-a660-566b4b7ea68d	INCOMPLETE_SENTENCES	------ Peura Insurance has located a larger office space, it will begin negotiating the rental agreement	\N	\N	2022-11-05 04:15:47.112	2022-11-04 18:33:37.412	\N	56c0406f-537a-4983-ba32-6a60830f7fbb
1f999bad-23cf-4d05-90dd-0b7bea187b6c	INCOMPLETE_SENTENCES	Mr. Tanaka's team worked ------ for months to secure a lucrative government contract.	\N	\N	2022-11-05 04:15:47.227	2022-11-04 18:35:12.427	\N	dc7a4d8f-a1d2-4208-b169-4246a9c9c705
5af80246-3ea7-4ae8-8d3b-2de336ade171	INCOMPLETE_SENTENCES	Though Sendark Agency's travel insurance can be purchased over the phone, most of ------ plans are bought online	\N	\N	2022-11-05 04:15:47.341	2022-11-04 18:36:08.368	\N	58501e5e-fd7a-4157-bfc7-04900cdb64e8
bc903efa-20ee-4b11-8431-711c00e93a1c	INCOMPLETE_SENTENCES	Garstein Furniture specializes infunctional products that are inexpensive beautifully crafted	\N	\N	2022-11-05 04:15:47.455	2022-11-04 18:37:23.098	\N	cb053fa3-e48b-4ff4-9f86-60193232f8a6
2c369c9c-51e6-4dbc-91ca-1cb715ed310d	INCOMPLETE_SENTENCES	Garstein Furniture specializes infunctional products that are inexpensive ----- beautifully crafted	\N	\N	2022-11-05 04:15:47.569	2022-11-04 18:37:53.084	\N	d3b77c86-83f7-483d-a316-ef38a92a117e
\.


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

