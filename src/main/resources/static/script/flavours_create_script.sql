-- Table: public.flavours

-- DROP TABLE IF EXISTS public.flavours;

CREATE TABLE IF NOT EXISTS public.flavours
(
    flavour_id integer NOT NULL DEFAULT nextval('flavours_flavour_id_seq'::regclass),
    flavour character varying COLLATE pg_catalog."default",
    CONSTRAINT flavours_pkey PRIMARY KEY (flavour_id)
)

TABLESPACE pg_default;

ALTER TABLE IF EXISTS public.flavours
    OWNER to postgres;