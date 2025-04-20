-- Table: public.strains

-- DROP TABLE IF EXISTS public.strains;

CREATE TABLE IF NOT EXISTS public.strains
(
    strain character varying COLLATE pg_catalog."default" NOT NULL,
    type character varying COLLATE pg_catalog."default",
    rating double precision,
    effects character varying[] COLLATE pg_catalog."default",
    flavours character varying[] COLLATE pg_catalog."default",
    description character varying COLLATE pg_catalog."default",
    CONSTRAINT strains_pkey PRIMARY KEY (strain)
)

TABLESPACE pg_default;

ALTER TABLE IF EXISTS public.strains
    OWNER to postgres;