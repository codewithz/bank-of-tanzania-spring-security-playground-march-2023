-- Table: public.users

-- DROP TABLE IF EXISTS public.users;

CREATE TABLE IF NOT EXISTS public.users
(
    id integer NOT NULL DEFAULT 'nextval('users_id_seq'::regclass)',
    username text COLLATE pg_catalog."default" NOT NULL,
    password text COLLATE pg_catalog."default" NOT NULL,
    enabled integer,
    CONSTRAINT users_pkey PRIMARY KEY (id)
    )

    TABLESPACE pg_default;

ALTER TABLE IF EXISTS public.users
    OWNER to postgres;



-- Table: public.authorities

-- DROP TABLE IF EXISTS public.authorities;

CREATE TABLE IF NOT EXISTS public.authorities
(
    id integer NOT NULL DEFAULT 'nextval('authorities_id_seq'::regclass)',
    username text COLLATE pg_catalog."default" NOT NULL,
    authority text COLLATE pg_catalog."default" NOT NULL,
    CONSTRAINT authorities_pkey PRIMARY KEY (id)
    )

    TABLESPACE pg_default;

ALTER TABLE IF EXISTS public.authorities
    OWNER to postgres;