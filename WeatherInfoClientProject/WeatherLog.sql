CREATE TABLE public.WeatherLog
(
  id integer NOT NULL DEFAULT nextval('company_id_seq'::regclass),
  responseId VARCHAR UNIQUE NOT NULL,
  location VARCHAR,
  actualWeather VARCHAR,
  temperature VARCHAR,
  dtimeInserted TIMESTAMP,
  CONSTRAINT weatherlog_pkey PRIMARY KEY (id)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE public.WeatherLog
  OWNER TO postgres;
  
  
  
testdb=# CREATE TABLE COMPANY(
   ID  SERIAL PRIMARY KEY,
   NAME           TEXT      NOT NULL,
   AGE            INT       NOT NULL,
   ADDRESS        CHAR(50),
   SALARY         REAL
);

CREATE TABLE WeatherLog(
  id  SERIAL PRIMARY KEY,
  responseId VARCHAR UNIQUE NOT NULL,
  location VARCHAR NOT NULL,
  actualWeather VARCHAR NOT NULL,
  temperature VARCHAR NOT NULL,
  dtimeInserted TIMESTAMP NOT NULL,
);