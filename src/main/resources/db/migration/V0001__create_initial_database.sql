create sequence  sq_tb_usr;
create table public.tb_usr(
  id_usr         integer       default nextval('sq_tb_usr') not null,
  tx_usr_usn     varchar                                    not null,
  tx_usr_ema     varchar                                    not null,
  tx_usr_nam     varchar                                    not null,
  tx_usr_sur     varchar                                            ,
  tx_usr_cpf     varchar                                            ,  
  dt_usr_bir     date                                               ,
  tx_usr_psw     varchar                                    not null, 
  dt_ini         date          default current_date         not null,
  dt_end         date          default '9999-12-31'         not null,
  tx_ins         varchar       default current_user         not null,
  ts_ins         timestamp     default current_timestamp    not null,
  tx_upd         varchar       default current_user         not null,
  ts_upd         timestamp     default current_timestamp    not null,
  tx_sts         varchar(2)    default 'PD'                 not null,

  constraint pk_tb_usr primary key (id_usr),
  unique(tx_usr_usn),
  unique(tx_usr_ema),
  unique(tx_usr_cpf)
);
comment on sequence sq_tb_usr                    is 'Sequencial da Tabela de Usuarios';
comment on table    public.tb_usr                is 'Tabela de Usuarios';
comment on column   public.tb_usr.id_usr         is 'Id do Usuario';
comment on column   public.tb_usr.tx_usr_usn     is 'Username do Usuario';
comment on column   public.tb_usr.tx_usr_ema     is 'Email do Usuario';
comment on column   public.tb_usr.tx_usr_nam     is 'Nome do Usuario';
comment on column   public.tb_usr.tx_usr_sur     is 'Sobrenome do Usuario';
comment on column   public.tb_usr.tx_usr_cpf     is 'CPF do Usuario';
comment on column   public.tb_usr.dt_usr_bir     is 'Data Nascimento do Usuario';
comment on column   public.tb_usr.tx_usr_psw     is 'Senha do Usuario';
comment on column   public.tb_usr.dt_ini         is 'Data Inicial';
comment on column   public.tb_usr.dt_end         is 'Data Final';
comment on column   public.tb_usr.tx_ins         is 'Login Insercao';
comment on column   public.tb_usr.ts_ins         is 'Timestamp Insercao';
comment on column   public.tb_usr.tx_upd         is 'Login Atualizacao';
comment on column   public.tb_usr.ts_upd         is 'Timestamp Atualizacao';
comment on column   public.tb_usr.tx_sts         is 'Status do registro';

create sequence  sq_tb_wal;
create table public.tb_wal(
  id_wal         integer       default nextval('sq_tb_wal') not null,
  id_usr         integer                                    not null,
  tx_wal_dsc     varchar                                    not null,
  vl_wal_bal     decimal(11,2)                              not null,
  dt_ini         date          default current_date         not null,
  dt_end         date          default '9999-12-31'         not null,
  tx_ins         varchar       default current_user         not null,
  ts_ins         timestamp     default current_timestamp    not null,
  tx_upd         varchar       default current_user         not null,
  ts_upd         timestamp     default current_timestamp    not null,
  tx_sts         varchar(2)    default 'PD'                 not null,

  constraint pk_tb_wal primary key (id_wal),
  foreign key (id_usr) references tb_usr (id_usr) on delete cascade,
  unique(id_usr, tx_wal_dsc)
);
comment on sequence sq_tb_wal                    is 'Sequencial da Tabela de Carteiras/Contas';
comment on table    public.tb_wal                is 'Tabela de Carteiras/Contas';
comment on column   public.tb_wal.id_wal         is 'Id da Carteira/Conta';
comment on column   public.tb_wal.id_usr         is 'Id do Usuario';
comment on column   public.tb_wal.tx_wal_dsc     is 'Descricao da Carteira/Conta';
comment on column   public.tb_wal.vl_wal_bal     is 'Saldo da Carteira/Conta';
comment on column   public.tb_wal.dt_ini         is 'Data Inicial';
comment on column   public.tb_wal.dt_end         is 'Data Final';
comment on column   public.tb_wal.tx_ins         is 'Login Insercao';
comment on column   public.tb_wal.ts_ins         is 'Timestamp Insercao';
comment on column   public.tb_wal.tx_upd         is 'Login Atualizacao';
comment on column   public.tb_wal.ts_upd         is 'Timestamp Atualizacao';
comment on column   public.tb_wal.tx_sts         is 'Status do registro';

create sequence  sq_tb_goa;
create table public.tb_goa(
  id_goa         integer       default nextval('sq_tb_goa') not null,
  id_usr         integer                                    not null,
  tx_goa_dsc     varchar                                    not null,
  vl_goa         decimal(11,2)                              not null,
  dt_ini         date          default current_date         not null,
  dt_end         date          default '9999-12-31'         not null,
  tx_ins         varchar       default current_user         not null,
  ts_ins         timestamp     default current_timestamp    not null,
  tx_upd         varchar       default current_user         not null,
  ts_upd         timestamp     default current_timestamp    not null,
  tx_sts         varchar(2)    default 'PD'                 not null,

  constraint pk_tb_goa primary key (id_goa),
  foreign key (id_usr) references tb_usr (id_usr) on delete cascade,
  unique(id_usr, tx_goa_dsc)
);
comment on sequence sq_tb_goa                    is 'Sequencial da Tabela de Metas';
comment on table    public.tb_goa                is 'Tabela de Metas';
comment on column   public.tb_goa.id_goa         is 'Id da Meta';
comment on column   public.tb_goa.id_usr         is 'Id do Usuario';
comment on column   public.tb_goa.tx_goa_dsc     is 'Descricao da Meta';
comment on column   public.tb_goa.vl_goa         is 'Valor da Meta';
comment on column   public.tb_goa.dt_ini         is 'Data Inicial';
comment on column   public.tb_goa.dt_end         is 'Data Final';
comment on column   public.tb_goa.tx_ins         is 'Login Insercao';
comment on column   public.tb_goa.ts_ins         is 'Timestamp Insercao';
comment on column   public.tb_goa.tx_upd         is 'Login Atualizacao';
comment on column   public.tb_goa.ts_upd         is 'Timestamp Atualizacao';
comment on column   public.tb_goa.tx_sts         is 'Status do registro';

create sequence  sq_tb_cat;
create table public.tb_cat(
  id_cat         integer       default nextval('sq_tb_cat') not null,
  id_usr         integer                                    not null,
  tx_cat_dsc     varchar                                    not null,
  tx_cat_grp     varchar                                    not null,
  dt_ini         date          default current_date         not null,
  dt_end         date          default '9999-12-31'         not null,
  tx_ins         varchar       default current_user         not null,
  ts_ins         timestamp     default current_timestamp    not null,
  tx_upd         varchar       default current_user         not null,
  ts_upd         timestamp     default current_timestamp    not null,
  tx_sts         varchar(2)    default 'PD'                 not null,

  constraint pk_tb_cat primary key (id_cat),
  foreign key (id_usr) references tb_usr (id_usr) on delete cascade,
  unique(id_usr, tx_cat_dsc)
);
comment on sequence sq_tb_cat                    is 'Sequencial da Tabela de Categorias';
comment on table    public.tb_cat                is 'Tabela de Categorias';
comment on column   public.tb_cat.id_cat         is 'Id da Categoria';
comment on column   public.tb_cat.id_usr         is 'Id do Usuario';
comment on column   public.tb_cat.tx_cat_dsc     is 'Nome da Categoria';
comment on column   public.tb_cat.tx_cat_grp     is 'Grupo da Categoria';
comment on column   public.tb_cat.dt_ini         is 'Data Inicial';
comment on column   public.tb_cat.dt_end         is 'Data Final';
comment on column   public.tb_cat.tx_ins         is 'Login Insercao';
comment on column   public.tb_cat.ts_ins         is 'Timestamp Insercao';
comment on column   public.tb_cat.tx_upd         is 'Login Atualizacao';
comment on column   public.tb_cat.ts_upd         is 'Timestamp Atualizacao';
comment on column   public.tb_cat.tx_sts         is 'Status do registro';

create sequence  sq_tb_bdg;
create table public.tb_bdg(
  id_bdg         integer       default nextval('sq_tb_bdg') not null,
  id_cat         integer                                    not null,
  vl_bdg_yea     integer                                    not null,
  vl_bdg_jan     decimal(11,2)                                      ,
  vl_bdg_feb     decimal(11,2)                                      ,
  vl_bdg_mar     decimal(11,2)                                      ,
  vl_bdg_apr     decimal(11,2)                                      ,
  vl_bdg_may     decimal(11,2)                                      ,
  vl_bdg_jun     decimal(11,2)                                      ,
  vl_bdg_jul     decimal(11,2)                                      ,
  vl_bdg_aug     decimal(11,2)                                      ,
  vl_bdg_sep     decimal(11,2)                                      ,
  vl_bdg_oct     decimal(11,2)                                      ,
  vl_bdg_nov     decimal(11,2)                                      ,
  vl_bdg_dec     decimal(11,2)                                      ,
  dt_ini         date          default current_date         not null,
  dt_end         date          default '9999-12-31'         not null,
  tx_ins         varchar       default current_user         not null,
  ts_ins         timestamp     default current_timestamp    not null,
  tx_upd         varchar       default current_user         not null,
  ts_upd         timestamp     default current_timestamp    not null,
  tx_sts         varchar(2)    default 'PD'                 not null,

  constraint pk_tb_bdg primary key (id_bdg),
  foreign key (id_cat) references tb_cat (id_cat) on delete cascade,
  unique(id_cat, vl_bdg_yea)
);
comment on sequence sq_tb_bdg                    is 'Sequencial da Tabela de Orcamentos';
comment on table    public.tb_bdg                is 'Tabela de Orcamentos';
comment on column   public.tb_bdg.id_bdg         is 'Id do Orcamento';
comment on column   public.tb_bdg.id_cat         is 'Id da Categoria';
comment on column   public.tb_bdg.vl_bdg_yea     is 'Ano do Orcamento';
comment on column   public.tb_bdg.vl_bdg_jan     is 'Valor do Orcamento para Janeiro do Ano do Orcamento';
comment on column   public.tb_bdg.vl_bdg_feb     is 'Valor do Orcamento para Fevereiro do Ano do Orcamento';
comment on column   public.tb_bdg.vl_bdg_mar     is 'Valor do Orcamento para Marco do Ano do Orcamento';
comment on column   public.tb_bdg.vl_bdg_apr     is 'Valor do Orcamento para Abril do Ano do Orcamento';
comment on column   public.tb_bdg.vl_bdg_may     is 'Valor do Orcamento para Maio do Ano do Orcamento';
comment on column   public.tb_bdg.vl_bdg_jun     is 'Valor do Orcamento para Junho do Ano do Orcamento';
comment on column   public.tb_bdg.vl_bdg_jul     is 'Valor do Orcamento para Julho do Ano do Orcamento';
comment on column   public.tb_bdg.vl_bdg_aug     is 'Valor do Orcamento para Agosto do Ano do Orcamento';
comment on column   public.tb_bdg.vl_bdg_sep     is 'Valor do Orcamento para Setembro do Ano do Orcamento';
comment on column   public.tb_bdg.vl_bdg_oct     is 'Valor do Orcamento para Outubro do Ano do Orcamento';
comment on column   public.tb_bdg.vl_bdg_nov     is 'Valor do Orcamento para Novembro do Ano do Orcamento';
comment on column   public.tb_bdg.vl_bdg_dec     is 'Valor do Orcamento para Dezembro do Ano do Orcamento';
comment on column   public.tb_bdg.dt_ini         is 'Data Inicial';
comment on column   public.tb_bdg.dt_end         is 'Data Final';
comment on column   public.tb_bdg.tx_ins         is 'Login Insercao';
comment on column   public.tb_bdg.ts_ins         is 'Timestamp Insercao';
comment on column   public.tb_bdg.tx_upd         is 'Login Atualizacao';
comment on column   public.tb_bdg.ts_upd         is 'Timestamp Atualizacao';
comment on column   public.tb_bdg.tx_sts         is 'Status do registro';

create sequence  sq_tb_pay;
create table public.tb_pay(
  id_pay         integer       default nextval('sq_tb_pay') not null,
  id_cat         integer                                    not null,
  id_wal         integer                                    not null,
  tx_pay_dsc     varchar                                    not null,
  vl_pay         decimal(11,2)                              not null,
  dt_ini         date          default current_date         not null,
  dt_end         date          default current_date         not null,
  tx_ins         varchar       default current_user         not null,
  ts_ins         timestamp     default current_timestamp    not null,
  tx_upd         varchar       default current_user         not null,
  ts_upd         timestamp     default current_timestamp    not null,
  tx_sts         varchar(2)    default 'PD'                 not null,

  constraint pk_tb_pay primary key (id_pay),
  foreign key (id_cat) references tb_cat (id_cat),
  foreign key (id_wal) references tb_wal (id_wal)  on delete cascade
);
comment on sequence sq_tb_pay                    is 'Sequencial da Tabela de Pagamentos';
comment on table    public.tb_pay                is 'Tabela de Pagamentos';
comment on column   public.tb_pay.id_pay         is 'Id do Pagamento';
comment on column   public.tb_pay.id_cat         is 'ID da Categoria';
comment on column   public.tb_pay.id_wal         is 'ID da Carteira/Conta';
comment on column   public.tb_pay.tx_pay_dsc     is 'Descricao do Pagamento';
comment on column   public.tb_pay.vl_pay         is 'Valor do Pagamento';
comment on column   public.tb_pay.dt_ini         is 'Data Inicial';
comment on column   public.tb_pay.dt_end         is 'Data Final';
comment on column   public.tb_pay.tx_ins         is 'Login Insercao';
comment on column   public.tb_pay.ts_ins         is 'Timestamp Insercao';
comment on column   public.tb_pay.tx_upd         is 'Login Atualizacao';
comment on column   public.tb_pay.ts_upd         is 'Timestamp Atualizacao';
comment on column   public.tb_pay.tx_sts         is 'Status do registro';
