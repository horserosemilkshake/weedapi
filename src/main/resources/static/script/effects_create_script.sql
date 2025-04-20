-- Table: public.effects

-- DROP TABLE IF EXISTS public.effects;

CREATE TABLE IF NOT EXISTS public.effects
(
    effect_id integer NOT NULL DEFAULT nextval('effects_effect_id_seq'::regclass),
    effect character varying COLLATE pg_catalog."default"
)

TABLESPACE pg_default;

ALTER TABLE IF EXISTS public.effects
    OWNER to postgres;