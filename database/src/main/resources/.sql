CREATE DATABASE online_pharmacy;

CREATE SCHEMA online_pharmacy;


CREATE TABLE online_pharmacy."user" (
  id       BIGSERIAL PRIMARY KEY,
  login    CHARACTER VARYING(255) NOT NULL UNIQUE,
  password CHARACTER VARYING(255) NOT NULL
);

CREATE TABLE online_pharmacy.admin (
  role    CHARACTER VARYING(255) NOT NULL,
  user_id BIGINT                 NOT NULL REFERENCES online_pharmacy."user" (id)
);

CREATE TABLE online_pharmacy.client (
  last_name        CHARACTER VARYING(255) NOT NULL,
  first_name       CHARACTER VARYING(255) NOT NULL,
  patronymic       CHARACTER VARYING(255) NOT NULL,
  date_of_birth    DATE                   NOT NULL,
  telephone_number CHARACTER VARYING(255) NOT NULL,
  address          CHARACTER VARYING(255) NOT NULL,
  user_id          BIGINT                 NOT NULL REFERENCES online_pharmacy."user" (id)
);

CREATE TABLE online_pharmacy.pharmacy_group (
  id   BIGSERIAL PRIMARY KEY,
  name CHARACTER VARYING(255) NOT NULL UNIQUE
);

CREATE TABLE online_pharmacy.medicine (
  id                BIGSERIAL PRIMARY KEY,
  name              CHARACTER VARYING(255) NOT NULL UNIQUE,
  description       TEXT                   NOT NULL,
  price             NUMERIC(19, 2)         NOT NULL,
  quantity          BIGINT                 NOT NULL,
  need_prescription BOOLEAN                NOT NULL,
  pharmacy_group_id BIGINT                 NOT NULL    REFERENCES online_pharmacy.pharmacy_group (id)
);

CREATE TABLE online_pharmacy.ordering (
  id                     BIGSERIAL PRIMARY KEY,
  date_of_ordering       DATE                   NOT NULL DEFAULT now(),
  ordering_clothing_date DATE,
  status                 CHARACTER VARYING(255) NOT NULL,
  total_sum              NUMERIC(19, 2)         NOT NULL,
  user_id                BIGINT                 NOT NULL REFERENCES online_pharmacy."user" (id)
);

CREATE TABLE online_pharmacy.ordering_medicine (
  id          BIGSERIAL PRIMARY KEY,
  ordering_id BIGINT NOT NULL REFERENCES online_pharmacy.ordering (id),
  medicine_id BIGINT NOT NULL REFERENCES online_pharmacy.medicine (id),
  quantity    BIGINT NOT NULL
);

CREATE TABLE online_pharmacy.prescription (
  id          BIGSERIAL PRIMARY KEY,
  name        BIGINT NOT NULL UNIQUE,
  user_id     BIGINT NOT NULL REFERENCES online_pharmacy."user" (id),
  medicine_id BIGINT NOT NULL REFERENCES online_pharmacy.medicine (id),
  quantity    BIGINT NOT NULL,
  validity    DATE   NOT NULL
);

CREATE TABLE online_pharmacy.review (
  id          BIGSERIAL PRIMARY KEY,
  mark        CHARACTER VARYING(255) NOT NULL,
  comment     TEXT                   NOT NULL,
  date_time   TIMESTAMP              NOT NULL DEFAULT now(),
  user_id     BIGINT                 NOT NULL REFERENCES online_pharmacy."user" (id),
  medicine_id BIGINT                 NOT NULL REFERENCES online_pharmacy.medicine (id)
);

INSERT INTO online_pharmacy.pharmacy_group(name)VALUES
  ('антибиотики'),
  ('жаропонижающие препараты'),
  ('противовирусные препараты'),
  ('обезболивающие препараты'),
  ('сердечно-сосудистые препараты'),
  ('желудочно-кишечные препараты'),
  ('дерматологические препараты'),
  ('гормоны');



INSERT INTO online_pharmacy.medicine (name, description, price, quantity, need_prescription,pharmacy_group_id) VALUES
  ('Оспамокс','показан для пероральной терапии следующих бактериальных инфекций, вызванных амоксициллин-чувствительными грам-положитальными и грам-отрицательными патогенами: инфекции верхних дыхательных путей, включая инфекции уха, носа и горла: острый средний отит, острый синусит и бактериальный фарингит; инфекции нижних дыхательных путей: обострение хронического бронхита, внебольничная пневмония; инфекции желудочно-кишечного тракта: бактериальный энтерит, инфекции, вызванные Н. Pylori; инфекции мочеполовой системы: цистит, пиелонефрит, простатит, эпидидимит, уретрит, гонорея неосложненная','6.4','55',TRUE ,(SELECT id FROM online_pharmacy.pharmacy_group WHERE name = 'антибиотики')),
  ('Амоксиклав','предназначен для лечения следующих инфекций, вызванных чувствительными к комбинации амоксициллин/клавулановая кислота штаммами: инфекции верхних дыхательных путей (острый и хронический синусит, острый и хронический средний отит, тонзиллофарингит); инфекции нижних дыхательных путей (острые и хронические бронхиты и пневмонии, эмпиема плевры); инфекции мочевых путей (в том числе циститы, уретриты, пиелонефриты)','8.8','98',FALSE ,(SELECT id FROM online_pharmacy.pharmacy_group WHERE name = 'антибиотики')),
  ('Аспирин','для симптоматического облегчения головной боли, зубной боли, боли в горле, боли при менструациях, боли в мышцах и суставах, боли в спине; повышенная температура тела при простудных и других инфекционно-воспалительных заболеваниях (у взрослых и детей старше 15 лет) ','4.21','102',FALSE ,(SELECT id FROM online_pharmacy.pharmacy_group WHERE name = 'жаропонижающие препараты')),
  ('Циклоферон','в комплексной терапии:- гриппа и острых респираторных заболеваний;- герпетической инфекции','5.57','23',TRUE ,(SELECT id FROM online_pharmacy.pharmacy_group WHERE name = 'противовирусные препараты')),
  ('Моноинсулин','показания к применению: сахарный диабет 1 типа (инсулинзависимый);• сахарный диабет 2 типа (инсулиннезависимый): стадия резистентности пероральным гипогликемическим средствам, частичная резистентность к этим препаратам (при проведении комбинированной терапии), интеркуррентные заболевания, беременность;• некоторые неотложные состояния у больных сахарным диабетом','16.3','45',TRUE ,(SELECT id FROM online_pharmacy.pharmacy_group WHERE name = 'гормоны')),
  ('Но-шпа','показания к применению: спазмы гладкой мускулатуры, связанные с заболеваниями билиарного тракта: холецистолитиаз, холангиолитиаз, холецистит, перихолецистит, холангит, папиллит; • спазмы гладкой мускулатуры мочевого тракта: нефролитиаз, уретролитиаз, пиелит, цистит, тенезмы мочевого пузыря','5.3','250',FALSE ,(SELECT id FROM online_pharmacy.pharmacy_group WHERE name = 'обезболивающие препараты')),
  ('Цитрамон','симптоматическое средство при слабом или умеренно выраженном болевом синдроме, а также как жаропонижающее средство при лихорадке различной этиологии. Препарат назначают при заболеваниях, сопровождающихся болью и лихорадкой: головная и зубная боль, мигрень, невралгия, миалгия, артралгия, первичная дисменорея','2.69','58',TRUE ,(SELECT id FROM online_pharmacy.pharmacy_group WHERE name = 'обезболивающие препараты')),
  ('Валидол','легкие приступы стенокардии, кардиалгии, синдром «укачивания» (тошнота, рвота при морской и воздушной болезни), истерия, невроз, головная боль, связанная с приемом нитратов','0.57','123',FALSE ,(SELECT id FROM online_pharmacy.pharmacy_group WHERE name = 'сердечно-сосудистые препараты')),
  ('Корвалол','симптоматическое лечение неврозоподобных состояний; нарушения сна, связанные с острым и хроническим стрессом, возбуждением и беспокойством.Лекарственное средство показано для кратковременного лечения нарушений сна, так как эффек¬тивность терапии после двух недель приема препарата снижается','0.6','502',FALSE ,(SELECT id FROM online_pharmacy.pharmacy_group WHERE name = 'сердечно-сосудистые препараты')),
  ('Фуразолидон','применяется при пищевом типе токсикоинфекций, паратифе, дизентерии бациллярной формы, лямблиозах, инфекциях мочеполового тракта (пиелите, цистите, уретрите), вызванных трихомонадами, а также в терапии ожогов и инфицированных кожных поверхностей в оперативной медицине','0.23','94',FALSE ,(SELECT id FROM online_pharmacy.pharmacy_group WHERE name = 'желудочно-кишечные препараты')),
  ('Зинерит','лечение угревой сыпи','18','15',FALSE ,(SELECT id FROM online_pharmacy.pharmacy_group WHERE name = 'дерматологические препараты'));
